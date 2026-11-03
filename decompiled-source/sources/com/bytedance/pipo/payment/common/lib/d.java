package com.bytedance.pipo.payment.common.lib;
/* compiled from: Singleton.java */
/* loaded from: classes2.dex */
public abstract class d<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f10908a;

    protected abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f10908a == null) {
            synchronized (this) {
                if (this.f10908a == null) {
                    this.f10908a = a(objArr);
                }
            }
        }
        return this.f10908a;
    }
}
