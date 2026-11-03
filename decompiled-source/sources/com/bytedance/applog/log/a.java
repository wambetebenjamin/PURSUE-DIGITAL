package com.bytedance.applog.log;
/* compiled from: AbsSingleton.java */
/* loaded from: classes.dex */
public abstract class a<T> {
    private volatile T mInstance;

    protected abstract T create(Object... objArr);

    public final T get(Object... objArr) {
        if (this.mInstance == null) {
            synchronized (this) {
                if (this.mInstance == null) {
                    this.mInstance = create(objArr);
                }
            }
        }
        return this.mInstance;
    }
}
