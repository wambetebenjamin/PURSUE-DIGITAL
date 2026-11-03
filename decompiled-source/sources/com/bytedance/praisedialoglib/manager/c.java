package com.bytedance.praisedialoglib.manager;

import android.app.Application;
/* compiled from: PraiseSdkManager.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private Application f11170a;

    private c() {
    }

    /* compiled from: PraiseSdkManager.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static c f11171a = new c();

        private a() {
        }
    }

    public static c getInstance() {
        return a.f11171a;
    }

    public void init(com.bytedance.praisedialoglib.depend.b bVar, Application application) {
        this.f11170a = application;
        com.bytedance.praisedialoglib.manager.a.getInstance().init(bVar);
    }

    public Application getApplication() {
        return this.f11170a;
    }
}
