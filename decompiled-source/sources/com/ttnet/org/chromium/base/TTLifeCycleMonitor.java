package com.ttnet.org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.brentvatne.react.ReactVideoViewManager;
import com.ttnet.org.chromium.base.TTWeakHandler;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class TTLifeCycleMonitor implements Application.ActivityLifecycleCallbacks {
    private static final int ENTER_BACKGROUND_DELAY_TIME = 30000;
    private static final String TAG = "TTLifeCycleMonitor";
    private static boolean mAppAlive;
    private static TTWeakHandler sHandler = new TTWeakHandler(Looper.getMainLooper(), new TTWeakHandler.IHandler() { // from class: com.ttnet.org.chromium.base.TTLifeCycleMonitor.1
        @Override // com.ttnet.org.chromium.base.TTWeakHandler.IHandler
        public void handleMsg(Message message) {
        }
    });
    private int mActivityCount;
    private AppStateListener mListener;
    private Runnable mRunnable = new Runnable() { // from class: com.ttnet.org.chromium.base.TTLifeCycleMonitor.2
        @Override // java.lang.Runnable
        public void run() {
            if (TTLifeCycleMonitor.mAppAlive) {
                boolean unused = TTLifeCycleMonitor.mAppAlive = false;
                if (TTLifeCycleMonitor.this.mListener != null) {
                    TTLifeCycleMonitor.this.mListener.onEnterToBackground();
                }
            }
        }
    };

    /* loaded from: classes9.dex */
    public interface AppStateListener {
        void onEnterToBackground();

        void onEnterToForeground();

        void onInstantEnterToBackground();

        void onInstantEnterToForeground();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public TTLifeCycleMonitor() {
        if (getActivity() != null) {
            mAppAlive = true;
        }
        this.mActivityCount = 0;
        List<Activity> allActivities = getAllActivities();
        if (allActivities != null) {
            this.mActivityCount = allActivities.size();
        }
    }

    private static List<Activity> getAllActivities() {
        ArrayList arrayList = new ArrayList();
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                Field declaredField2 = value.getClass().getDeclaredField("activity");
                declaredField2.setAccessible(true);
                arrayList.add((Activity) declaredField2.get(value));
            }
            return arrayList;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Activity getActivity() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField(ReactVideoViewManager.PROP_PAUSED);
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        AppStateListener appStateListener;
        if (this.mActivityCount == 0 && (appStateListener = this.mListener) != null) {
            appStateListener.onInstantEnterToForeground();
        }
        this.mActivityCount++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (!mAppAlive) {
            mAppAlive = true;
            AppStateListener appStateListener = this.mListener;
            if (appStateListener != null) {
                appStateListener.onEnterToForeground();
            }
        }
        sHandler.removeCallbacks(this.mRunnable);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (mAppAlive) {
            sHandler.postDelayed(this.mRunnable, 30000L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        AppStateListener appStateListener;
        int i2 = this.mActivityCount - 1;
        this.mActivityCount = i2;
        if (i2 != 0 || (appStateListener = this.mListener) == null) {
            return;
        }
        appStateListener.onInstantEnterToBackground();
    }

    public void setAppStateChangedListener(AppStateListener appStateListener) {
        this.mListener = appStateListener;
    }
}
