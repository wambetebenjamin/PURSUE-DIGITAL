package com.facebook.common.util;
/* loaded from: classes8.dex */
public class Lazy<T> {
    private volatile boolean hasInitialized = false;
    private volatile boolean hasSetInitialValue = false;
    private volatile T value;

    /* JADX INFO: Access modifiers changed from: protected */
    public T initialValue() {
        return null;
    }

    public T get() {
        if (this.value != null) {
            return this.value;
        }
        return setInitialValue();
    }

    public void set(T t) {
        this.value = t;
        this.hasInitialized = true;
    }

    public boolean hasInitialized() {
        return this.hasInitialized;
    }

    private T setInitialValue() {
        if (this.hasSetInitialValue) {
            return this.value;
        }
        synchronized (this) {
            if (this.hasSetInitialValue) {
                return this.value;
            }
            this.value = initialValue();
            if (this.value != null) {
                this.hasSetInitialValue = true;
            }
            return this.value;
        }
    }
}
