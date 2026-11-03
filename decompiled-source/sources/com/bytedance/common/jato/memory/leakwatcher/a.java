package com.bytedance.common.jato.memory.leakwatcher;

import android.app.Activity;
import android.app.Application;
/* compiled from: ActivityWatcher.java */
/* loaded from: classes2.dex */
public class a implements f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4954a = "ActivityWatcher";

    /* renamed from: b  reason: collision with root package name */
    private final Application f4955b;

    /* renamed from: c  reason: collision with root package name */
    private final c f4956c;

    /* renamed from: d  reason: collision with root package name */
    private final C0066a f4957d = new C0066a();

    public a(Application application, c cVar) {
        this.f4955b = application;
        this.f4956c = cVar;
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.f
    public void a() {
        this.f4955b.registerActivityLifecycleCallbacks(this.f4957d);
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.f
    public void b() {
        this.f4955b.unregisterActivityLifecycleCallbacks(this.f4957d);
    }

    /* compiled from: ActivityWatcher.java */
    /* renamed from: com.bytedance.common.jato.memory.leakwatcher.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private class C0066a extends d {
        private C0066a() {
        }

        @Override // com.bytedance.common.jato.memory.leakwatcher.d, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            a.this.f4956c.addForLeakDetection(activity, activity.getClass().getName() + " received Activity#onDestroy()");
        }
    }
}
