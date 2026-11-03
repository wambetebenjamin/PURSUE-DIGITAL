package com.bytedance.common.jato.memory.leakwatcher;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
/* compiled from: KeyedWeakReference.java */
/* loaded from: classes2.dex */
public class g extends WeakReference<Object> {

    /* renamed from: a  reason: collision with root package name */
    private final String f5002a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5003b;

    /* renamed from: c  reason: collision with root package name */
    private final long f5004c;

    public boolean d() {
        return true;
    }

    public g(Object obj, String str, String str2, long j2, ReferenceQueue<Object> referenceQueue) {
        super(obj, referenceQueue);
        this.f5002a = str;
        this.f5003b = str2;
        this.f5004c = j2;
    }

    public String a() {
        return this.f5002a;
    }

    public String b() {
        return this.f5003b;
    }

    public long c() {
        return this.f5004c;
    }

    @Override // java.lang.ref.Reference
    public Object get() {
        throw new IllegalStateException("Calling KeyedWeakReference.get() is a mistake as it revives the reference");
    }

    public Object e() {
        return super.get();
    }
}
