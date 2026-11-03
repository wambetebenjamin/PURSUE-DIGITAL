package com.bytedance.pipo.game.impl;
/* compiled from: Singleton.java */
/* loaded from: classes2.dex */
public abstract class h<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f10337a;

    protected abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f10337a == null) {
            synchronized (this) {
                if (this.f10337a == null) {
                    this.f10337a = a(objArr);
                }
            }
        }
        return this.f10337a;
    }
}
