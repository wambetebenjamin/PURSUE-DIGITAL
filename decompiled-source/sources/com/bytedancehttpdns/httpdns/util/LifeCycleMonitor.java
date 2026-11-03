package com.bytedancehttpdns.httpdns.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedancehttpdns.httpdns.util.WeakHandler;
import java.lang.reflect.Field;
import java.util.Map;
/* loaded from: classes8.dex */
public class LifeCycleMonitor implements Application.ActivityLifecycleCallbacks {
    private static final int ENTER_BACKGROUND_DELAY_TIME = 30000;
    private static boolean mAppAlive;
    private static WeakHandler sHandler = new WeakHandler(Looper.getMainLooper(), new WeakHandler.IHandler() { // from class: com.bytedancehttpdns.httpdns.util.LifeCycleMonitor.1
        @Override // com.bytedancehttpdns.httpdns.util.WeakHandler.IHandler
        public void handleMsg(Message message) {
        }
    });
    private AppStateListener mListener;
    private Runnable mRunnable = new Runnable() { // from class: com.bytedancehttpdns.httpdns.util.LifeCycleMonitor.2
        @Override // java.lang.Runnable
        public void run() {
            if (LifeCycleMonitor.mAppAlive) {
                boolean unused = LifeCycleMonitor.mAppAlive = false;
                if (LifeCycleMonitor.this.mListener != null) {
                    LifeCycleMonitor.this.mListener.onEnterToBackground();
                }
            }
        }
    };

    /* loaded from: classes8.dex */
    public interface AppStateListener {
        void onEnterToBackground();

        void onEnterToForeground();
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public LifeCycleMonitor() {
        if (getActivity() != null) {
            mAppAlive = true;
        }
    }

    private static Activity getActivity() {
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

    public void setAppStateChangedListener(AppStateListener appStateListener) {
        this.mListener = appStateListener;
    }
}
