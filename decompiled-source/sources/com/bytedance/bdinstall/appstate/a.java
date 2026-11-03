package com.bytedance.bdinstall.appstate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.bytedance.bdinstall.appstate.b;
/* compiled from: AppLifeCycleListener.java */
/* loaded from: classes.dex */
public class a implements Application.ActivityLifecycleCallbacks, b {

    /* renamed from: a  reason: collision with root package name */
    private int f3722a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3723b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f3724c;

    /* renamed from: d  reason: collision with root package name */
    private b.a f3725d;

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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (this.f3722a < 0) {
            this.f3722a = 0;
        }
        if (this.f3722a == 0) {
            d();
        }
        this.f3722a++;
        this.f3724c = true;
        b.a aVar = this.f3725d;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        int i2 = this.f3722a - 1;
        this.f3722a = i2;
        if (i2 <= 0) {
            c();
        }
        if (this.f3722a < 0) {
            this.f3722a = 0;
        }
    }

    @Override // com.bytedance.bdinstall.appstate.b
    public boolean a() {
        return this.f3723b;
    }

    private void c() {
        this.f3723b = false;
    }

    private void d() {
        this.f3723b = true;
    }

    @Override // com.bytedance.bdinstall.appstate.b
    public void a(b.a aVar) {
        this.f3725d = aVar;
    }

    @Override // com.bytedance.bdinstall.appstate.b
    public boolean b() {
        return this.f3724c;
    }
}
