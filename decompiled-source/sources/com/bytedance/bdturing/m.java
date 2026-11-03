package com.bytedance.bdturing;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
/* compiled from: OnDialogBackgroundListener.java */
/* loaded from: classes.dex */
public class m implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a  reason: collision with root package name */
    private Activity f4226a;

    /* renamed from: b  reason: collision with root package name */
    private int f4227b = 1;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Activity activity) {
        this.f4226a = activity;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (activity == this.f4226a) {
            this.f4227b++;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity == this.f4226a) {
            int i2 = this.f4227b - 1;
            this.f4227b = i2;
            if (i2 == 0) {
                f.b();
            }
        }
    }

    public void a() {
        Activity activity = this.f4226a;
        if (activity != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public void b() {
        Activity activity = this.f4226a;
        if (activity != null) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }
}
