package com.bytedance.hades.downloader.impl.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.bytedance.hades.downloader.impl.notification.NotificationClickActivity;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class AppStatusManager {
    public static final int STATUS_BACKGROUND = 0;
    public static final int STATUS_FOREGROUND = 1;
    private static final String TAG = "AppStatusManager";
    private final List<b> mAppStatusChangeListeners;
    private Application mApplication;
    private final Application.ActivityLifecycleCallbacks mCallbacks;
    private AppStatus mStatus;
    private int mTopActivityHashCode;

    /* loaded from: classes2.dex */
    public enum AppStatus {
        BACKGROUND,
        FOREGROUND
    }

    /* loaded from: classes2.dex */
    public class a implements Application.ActivityLifecycleCallbacks {
        public a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (AppStatusManager.this.mTopActivityHashCode != 0 || activity == null) {
                return;
            }
            AppStatusManager.this.mTopActivityHashCode = activity.hashCode();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            int i2 = AppStatusManager.this.mTopActivityHashCode;
            AppStatusManager.this.mTopActivityHashCode = activity != null ? activity.hashCode() : i2;
            if (i2 == 0) {
                AppStatusManager.this.dispatchAppForeground(activity);
            }
            NotificationClickActivity.f9407a = activity.getComponentName();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            int i2 = AppStatusManager.this.mTopActivityHashCode;
            AppStatusManager.this.mTopActivityHashCode = activity != null ? activity.hashCode() : i2;
            if (i2 == 0) {
                AppStatusManager.this.dispatchAppForeground(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity == null || activity.hashCode() != AppStatusManager.this.mTopActivityHashCode) {
                return;
            }
            AppStatusManager.this.mTopActivityHashCode = 0;
            AppStatusManager.this.dispatchAppBackground(activity);
        }
    }

    /* loaded from: classes2.dex */
    public interface b {
        void a(Activity activity);

        void b(Activity activity);
    }

    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public static final AppStatusManager f9430a = new AppStatusManager(null);
    }

    public /* synthetic */ AppStatusManager(a aVar) {
        this();
    }

    private Object[] collectAppSwitchListeners() {
        Object[] array;
        synchronized (this.mAppStatusChangeListeners) {
            array = this.mAppStatusChangeListeners.size() > 0 ? this.mAppStatusChangeListeners.toArray() : null;
        }
        return array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppBackground(Activity activity) {
        this.mStatus = AppStatus.BACKGROUND;
        Object[] collectAppSwitchListeners = collectAppSwitchListeners();
        if (collectAppSwitchListeners != null) {
            for (Object obj : collectAppSwitchListeners) {
                ((b) obj).a(activity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppForeground(Activity activity) {
        this.mStatus = AppStatus.FOREGROUND;
        Object[] collectAppSwitchListeners = collectAppSwitchListeners();
        if (collectAppSwitchListeners != null) {
            for (Object obj : collectAppSwitchListeners) {
                ((b) obj).b(activity);
            }
        }
    }

    public static AppStatusManager getInstance() {
        return c.f9430a;
    }

    public AppStatus getAppStatus() {
        return this.mStatus;
    }

    public void init(Context context) {
        if (this.mApplication == null && (context instanceof Application)) {
            synchronized (this) {
                if (this.mApplication == null) {
                    Application application = (Application) context;
                    this.mApplication = application;
                    application.registerActivityLifecycleCallbacks(this.mCallbacks);
                }
            }
        }
    }

    public void registerAppSwitchListener(b bVar) {
        if (bVar == null) {
            return;
        }
        synchronized (this.mAppStatusChangeListeners) {
            if (!this.mAppStatusChangeListeners.contains(bVar)) {
                this.mAppStatusChangeListeners.add(bVar);
            }
        }
    }

    public void unregisterAppSwitchListener(b bVar) {
        synchronized (this.mAppStatusChangeListeners) {
            this.mAppStatusChangeListeners.remove(bVar);
        }
    }

    private AppStatusManager() {
        this.mAppStatusChangeListeners = new ArrayList();
        this.mStatus = AppStatus.FOREGROUND;
        this.mCallbacks = new a();
    }
}
