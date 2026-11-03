package com.bytedance.push.utils;
/* compiled from: Singleton.java */
/* loaded from: classes2.dex */
public abstract class p<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f12070a;

    protected abstract T b(Object... objArr);

    public final void resetInstance() {
        this.f12070a = null;
    }

    public final T get(Object... objArr) {
        if (this.f12070a == null) {
            synchronized (this) {
                if (this.f12070a == null) {
                    this.f12070a = b(objArr);
                }
            }
        }
        return this.f12070a;
    }
}
