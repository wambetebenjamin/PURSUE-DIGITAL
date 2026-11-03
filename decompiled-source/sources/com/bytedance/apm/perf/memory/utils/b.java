package com.bytedance.apm.perf.memory.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* compiled from: KeyedWeakReference.java */
/* loaded from: classes.dex */
public class b extends WeakReference<Object> {
    public final String key;
    public final String name;

    public b(Object obj, String str, String str2, ReferenceQueue<Object> referenceQueue) {
        super(a(obj, "referent"), (ReferenceQueue) a(referenceQueue, "referenceQueue"));
        this.key = (String) a(str, "key");
        this.name = (String) a(str2, "name");
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str + " must not be null");
    }
}
