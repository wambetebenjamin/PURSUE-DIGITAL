package com.bytedance.common.utility.collection;

import java.lang.ref.WeakReference;
/* compiled from: WeakEqualReference.java */
/* loaded from: classes2.dex */
public class g<T> extends WeakReference<T> {

    /* renamed from: a  reason: collision with root package name */
    int f5218a;

    public g(T t) {
        super(t);
        this.f5218a = t == null ? 0 : t.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof g) {
            Object obj2 = super.get();
            Object obj3 = ((g) obj).get();
            if (obj2 == null) {
                return obj2 == obj3;
            }
            return obj2.equals(obj3);
        }
        return false;
    }

    public int hashCode() {
        return this.f5218a;
    }
}
