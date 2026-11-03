package com.bytedance.sync;
/* compiled from: Singleton.java */
/* loaded from: classes3.dex */
public abstract class m<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f13470a;

    protected abstract T b(Object... objArr);

    public final T get(Object... objArr) {
        if (this.f13470a == null) {
            synchronized (this) {
                if (this.f13470a == null) {
                    this.f13470a = b(objArr);
                }
            }
        }
        return this.f13470a;
    }
}
