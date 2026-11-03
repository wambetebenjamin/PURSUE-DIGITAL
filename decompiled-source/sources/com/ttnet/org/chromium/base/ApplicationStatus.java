package com.ttnet.org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import com.bytedance.apm.constant.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.build.BuildConfig;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class ApplicationStatus {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TOOLBAR_CALLBACK_WRAPPER_CLASS = "androidx.appcompat.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static Activity sActivity;
    private static ObserverList<ApplicationStateListener> sApplicationStateListeners;
    private static ObserverList<ActivityStateListener> sGeneralActivityStateListeners;
    private static ApplicationStateListener sNativeApplicationStateListener;
    private static ObserverList<WindowFocusChangedListener> sWindowFocusListeners;
    private static final Map<Activity, ActivityInfo> sActivityInfo = Collections.synchronizedMap(new HashMap());
    private static int sCurrentApplicationState = 0;

    /* loaded from: classes9.dex */
    public interface ActivityStateListener {
        void onActivityStateChange(Activity activity, int i2);
    }

    /* loaded from: classes9.dex */
    public interface ApplicationStateListener {
        void onApplicationStateChange(int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void onApplicationStateChange(int i2);
    }

    /* loaded from: classes9.dex */
    public interface WindowFocusChangedListener {
        void onWindowFocusChanged(Activity activity, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class ActivityInfo {
        private ObserverList<ActivityStateListener> mListeners;
        private int mStatus;

        private ActivityInfo() {
            this.mStatus = 6;
            this.mListeners = new ObserverList<>();
        }

        public int getStatus() {
            return this.mStatus;
        }

        public void setStatus(int i2) {
            this.mStatus = i2;
        }

        public ObserverList<ActivityStateListener> getListeners() {
            return this.mListeners;
        }
    }

    private ApplicationStatus() {
    }

    public static void registerWindowFocusChangedListener(WindowFocusChangedListener windowFocusChangedListener) {
        if (sWindowFocusListeners == null) {
            sWindowFocusListeners = new ObserverList<>();
        }
        sWindowFocusListeners.addObserver(windowFocusChangedListener);
    }

    public static void unregisterWindowFocusChangedListener(WindowFocusChangedListener windowFocusChangedListener) {
        ObserverList<WindowFocusChangedListener> observerList = sWindowFocusListeners;
        if (observerList == null) {
            return;
        }
        observerList.removeObserver(windowFocusChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class WindowCallbackProxy implements InvocationHandler {
        private final Activity mActivity;
        private final Window.Callback mCallback;

        public WindowCallbackProxy(Activity activity, Window.Callback callback) {
            this.mCallback = callback;
            this.mActivity = activity;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getName().equals(a.ON_WINDOW_FOCUS_CHANGED) && objArr.length == 1 && (objArr[0] instanceof Boolean)) {
                onWindowFocusChanged(((Boolean) objArr[0]).booleanValue());
                return null;
            }
            try {
                return method.invoke(this.mCallback, objArr);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof AbstractMethodError) {
                    throw e2.getCause();
                }
                throw e2;
            }
        }

        public void onWindowFocusChanged(boolean z) {
            this.mCallback.onWindowFocusChanged(z);
            if (ApplicationStatus.sWindowFocusListeners != null) {
                Iterator it = ApplicationStatus.sWindowFocusListeners.iterator();
                while (it.hasNext()) {
                    ((WindowFocusChangedListener) it.next()).onWindowFocusChanged(this.mActivity, z);
                }
            }
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (sActivityInfo) {
            z = sCurrentApplicationState != 0;
        }
        return z;
    }

    public static void initialize(Application application) {
        synchronized (sActivityInfo) {
            sCurrentApplicationState = 4;
        }
        registerWindowFocusChangedListener(new WindowFocusChangedListener() { // from class: com.ttnet.org.chromium.base.ApplicationStatus.1
            @Override // com.ttnet.org.chromium.base.ApplicationStatus.WindowFocusChangedListener
            public void onWindowFocusChanged(Activity activity, boolean z) {
                int stateForActivity;
                if (!z || activity == ApplicationStatus.sActivity || (stateForActivity = ApplicationStatus.getStateForActivity(activity)) == 6 || stateForActivity == 5) {
                    return;
                }
                Activity unused = ApplicationStatus.sActivity = activity;
            }
        });
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ttnet.org.chromium.base.ApplicationStatus.2
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ApplicationStatus.onStateChange(activity, 1);
                activity.getWindow().setCallback(ApplicationStatus.createWindowCallbackProxy(activity, activity.getWindow().getCallback()));
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 6);
                checkCallback(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                ApplicationStatus.onStateChange(activity, 4);
                checkCallback(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                ApplicationStatus.onStateChange(activity, 3);
                checkCallback(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                checkCallback(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                ApplicationStatus.onStateChange(activity, 2);
                checkCallback(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                ApplicationStatus.onStateChange(activity, 5);
                checkCallback(activity);
            }

            private void checkCallback(Activity activity) {
                boolean z = BuildConfig.ENABLE_ASSERTS;
            }
        });
    }

    static Window.Callback createWindowCallbackProxy(Activity activity, Window.Callback callback) {
        return (Window.Callback) Proxy.newProxyInstance(Window.Callback.class.getClassLoader(), new Class[]{Window.Callback.class}, new WindowCallbackProxy(activity, callback));
    }

    static boolean reachesWindowCallback(Window.Callback callback) {
        Field[] declaredFields;
        if (callback == null) {
            return false;
        }
        if (callback.getClass().getName().equals(TOOLBAR_CALLBACK_WRAPPER_CLASS)) {
            return true;
        }
        if (Proxy.isProxyClass(callback.getClass())) {
            return Proxy.getInvocationHandler(callback) instanceof WindowCallbackProxy;
        }
        for (Class<?> cls = callback.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.getType().isAssignableFrom(Window.Callback.class)) {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    try {
                        Window.Callback callback2 = (Window.Callback) field.get(callback);
                        field.setAccessible(isAccessible);
                        if (reachesWindowCallback(callback2)) {
                            return true;
                        }
                    } catch (IllegalAccessException unused) {
                        field.setAccessible(isAccessible);
                    } catch (Throwable th) {
                        field.setAccessible(isAccessible);
                        throw th;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onStateChange(Activity activity, int i2) {
        ActivityInfo activityInfo;
        ObserverList<ApplicationStateListener> observerList;
        if (activity == null) {
            throw new IllegalArgumentException("null activity is not supported");
        }
        if (sActivity == null || i2 == 1 || i2 == 3 || i2 == 2) {
            sActivity = activity;
        }
        int stateForApplication = getStateForApplication();
        Map<Activity, ActivityInfo> map = sActivityInfo;
        synchronized (map) {
            if (!map.containsKey(activity)) {
                map.put(activity, new ActivityInfo());
            }
            activityInfo = map.get(activity);
            activityInfo.setStatus(i2);
            if (i2 == 6) {
                map.remove(activity);
                if (activity == sActivity) {
                    sActivity = null;
                }
            }
            sCurrentApplicationState = determineApplicationStateLocked();
        }
        Iterator<ActivityStateListener> it = activityInfo.getListeners().iterator();
        while (it.hasNext()) {
            it.next().onActivityStateChange(activity, i2);
        }
        ObserverList<ActivityStateListener> observerList2 = sGeneralActivityStateListeners;
        if (observerList2 != null) {
            Iterator<ActivityStateListener> it2 = observerList2.iterator();
            while (it2.hasNext()) {
                it2.next().onActivityStateChange(activity, i2);
            }
        }
        int stateForApplication2 = getStateForApplication();
        if (stateForApplication2 == stateForApplication || (observerList = sApplicationStateListeners) == null) {
            return;
        }
        Iterator<ApplicationStateListener> it3 = observerList.iterator();
        while (it3.hasNext()) {
            it3.next().onApplicationStateChange(stateForApplication2);
        }
    }

    public static void onStateChangeForTesting(Activity activity, int i2) {
        onStateChange(activity, i2);
    }

    public static Activity getLastTrackedFocusedActivity() {
        return sActivity;
    }

    public static List<Activity> getRunningActivities() {
        ArrayList arrayList;
        Map<Activity, ActivityInfo> map = sActivityInfo;
        synchronized (map) {
            arrayList = new ArrayList(map.keySet());
        }
        return arrayList;
    }

    public static int getStateForActivity(Activity activity) {
        ActivityInfo activityInfo;
        if (activity == null || (activityInfo = sActivityInfo.get(activity)) == null) {
            return 6;
        }
        return activityInfo.getStatus();
    }

    public static int getStateForApplication() {
        int i2;
        synchronized (sActivityInfo) {
            i2 = sCurrentApplicationState;
        }
        return i2;
    }

    public static boolean hasVisibleActivities() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    public static boolean isEveryActivityDestroyed() {
        return sActivityInfo.isEmpty();
    }

    public static void registerStateListenerForAllActivities(ActivityStateListener activityStateListener) {
        if (sGeneralActivityStateListeners == null) {
            sGeneralActivityStateListeners = new ObserverList<>();
        }
        sGeneralActivityStateListeners.addObserver(activityStateListener);
    }

    public static void registerStateListenerForActivity(ActivityStateListener activityStateListener, Activity activity) {
        sActivityInfo.get(activity).getListeners().addObserver(activityStateListener);
    }

    public static void unregisterActivityStateListener(ActivityStateListener activityStateListener) {
        ObserverList<ActivityStateListener> observerList = sGeneralActivityStateListeners;
        if (observerList != null) {
            observerList.removeObserver(activityStateListener);
        }
        Map<Activity, ActivityInfo> map = sActivityInfo;
        synchronized (map) {
            for (ActivityInfo activityInfo : map.values()) {
                activityInfo.getListeners().removeObserver(activityStateListener);
            }
        }
    }

    public static void registerApplicationStateListener(ApplicationStateListener applicationStateListener) {
        if (sApplicationStateListeners == null) {
            sApplicationStateListeners = new ObserverList<>();
        }
        sApplicationStateListeners.addObserver(applicationStateListener);
    }

    public static void unregisterApplicationStateListener(ApplicationStateListener applicationStateListener) {
        ObserverList<ApplicationStateListener> observerList = sApplicationStateListeners;
        if (observerList == null) {
            return;
        }
        observerList.removeObserver(applicationStateListener);
    }

    public static void destroyForJUnitTests() {
        Map<Activity, ActivityInfo> map = sActivityInfo;
        synchronized (map) {
            ObserverList<ApplicationStateListener> observerList = sApplicationStateListeners;
            if (observerList != null) {
                observerList.clear();
            }
            ObserverList<ActivityStateListener> observerList2 = sGeneralActivityStateListeners;
            if (observerList2 != null) {
                observerList2.clear();
            }
            map.clear();
            ObserverList<WindowFocusChangedListener> observerList3 = sWindowFocusListeners;
            if (observerList3 != null) {
                observerList3.clear();
            }
            sCurrentApplicationState = 0;
            sActivity = null;
            sNativeApplicationStateListener = null;
        }
    }

    public static void resetActivitiesForInstrumentationTests() {
        Map<Activity, ActivityInfo> map = sActivityInfo;
        synchronized (map) {
            Iterator it = new HashSet(map.keySet()).iterator();
            while (it.hasNext()) {
                onStateChangeForTesting((Activity) it.next(), 6);
            }
        }
    }

    private static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.runOnUiThread(new Runnable() { // from class: com.ttnet.org.chromium.base.ApplicationStatus.3
            @Override // java.lang.Runnable
            public void run() {
                if (ApplicationStatus.sNativeApplicationStateListener != null) {
                    return;
                }
                ApplicationStateListener unused = ApplicationStatus.sNativeApplicationStateListener = new ApplicationStateListener() { // from class: com.ttnet.org.chromium.base.ApplicationStatus.3.1
                    @Override // com.ttnet.org.chromium.base.ApplicationStatus.ApplicationStateListener
                    public void onApplicationStateChange(int i2) {
                        ApplicationStatusJni.get().onApplicationStateChange(i2);
                    }
                };
                ApplicationStatus.registerApplicationStateListener(ApplicationStatus.sNativeApplicationStateListener);
            }
        });
    }

    private static int determineApplicationStateLocked() {
        boolean z = false;
        boolean z2 = false;
        for (ActivityInfo activityInfo : sActivityInfo.values()) {
            int status = activityInfo.getStatus();
            if (status != 4 && status != 5 && status != 6) {
                return 1;
            }
            if (status == 4) {
                z = true;
            } else if (status == 5) {
                z2 = true;
            }
        }
        if (z) {
            return 2;
        }
        return z2 ? 3 : 4;
    }
}
