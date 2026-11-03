package com.bytedance.bdinstall.util;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: MultiSingleton.java */
/* loaded from: classes.dex */
public abstract class k<T> {

    /* renamed from: a  reason: collision with root package name */
    private volatile ConcurrentHashMap<String, T> f4057a;

    protected abstract T b(String str, Object... objArr);

    public final T c(String str, Object... objArr) {
        T t = null;
        T t2 = this.f4057a == null ? null : this.f4057a.get(str);
        if (t2 == null) {
            synchronized (this) {
                if (this.f4057a != null) {
                    t = this.f4057a.get(str);
                }
                if (t == null) {
                    T b2 = b(str, objArr);
                    if (b2 != null) {
                        if (this.f4057a == null) {
                            this.f4057a = new ConcurrentHashMap<>(4);
                        }
                        this.f4057a.put(str, b2);
                    }
                    t2 = b2;
                } else {
                    t2 = t;
                }
            }
        }
        return t2;
    }
}
