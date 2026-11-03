package com.ss.android.deviceregister.utils;
/* compiled from: Singleton.java */
/* loaded from: classes9.dex */
public abstract class f<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f18285a;

    protected abstract T b(Object... objArr);

    public final T get(Object... objArr) {
        if (this.f18285a == null) {
            synchronized (this) {
                if (this.f18285a == null) {
                    this.f18285a = b(objArr);
                }
            }
        }
        return this.f18285a;
    }
}
