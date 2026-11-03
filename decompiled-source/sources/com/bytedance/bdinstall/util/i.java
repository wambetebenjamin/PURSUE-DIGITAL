package com.bytedance.bdinstall.util;
/* compiled from: InstanceHolder.java */
/* loaded from: classes.dex */
public abstract class i<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f4050a;

    protected abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f4050a == null) {
            synchronized (this) {
                if (this.f4050a == null) {
                    this.f4050a = a(objArr);
                }
            }
        }
        return this.f4050a;
    }
}
