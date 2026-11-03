package com.ss.android.deviceregister.utils;
/* compiled from: Lazy.java */
/* loaded from: classes9.dex */
public abstract class h<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f18287a = false;

    /* renamed from: b  reason: collision with root package name */
    private volatile T f18288b;

    protected abstract T c();

    public T a() {
        if (this.f18288b != null) {
            return this.f18288b;
        }
        return d();
    }

    public void a(T t) {
        this.f18288b = t;
        this.f18287a = true;
    }

    public boolean b() {
        return this.f18287a;
    }

    private T d() {
        if (this.f18288b != null) {
            return this.f18288b;
        }
        synchronized (this) {
            if (this.f18288b != null) {
                return this.f18288b;
            }
            this.f18288b = c();
            return this.f18288b;
        }
    }
}
