package com.bytedance.bdinstall.util;

import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: Singleton.java */
/* loaded from: classes.dex */
public abstract class r<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile T f4078a;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f4079b = new AtomicBoolean(false);

    protected abstract T b(Object... objArr);

    public final T c(Object... objArr) {
        if (!this.f4079b.get() && this.f4078a == null) {
            synchronized (this) {
                if (!this.f4079b.get() && this.f4078a == null) {
                    this.f4078a = b(objArr);
                    this.f4079b.set(true);
                }
            }
        }
        return this.f4078a;
    }
}
