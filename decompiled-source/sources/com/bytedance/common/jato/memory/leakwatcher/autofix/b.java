package com.bytedance.common.jato.memory.leakwatcher.autofix;

import java.util.concurrent.TimeUnit;
/* compiled from: DefaultLeakFixConditionStrategy.java */
/* loaded from: classes2.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private static final float f4965a = 0.95f;

    /* renamed from: b  reason: collision with root package name */
    private static final long f4966b = TimeUnit.MINUTES.toMillis(3);

    /* renamed from: c  reason: collision with root package name */
    private final float f4967c;

    /* renamed from: d  reason: collision with root package name */
    private final long f4968d;

    public b() {
        this(f4965a, f4966b);
    }

    public b(float f2, long j2) {
        this.f4967c = f2;
        this.f4968d = j2;
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.autofix.c
    public boolean a(long j2) {
        if (j2 < this.f4968d) {
            return false;
        }
        Runtime runtime = Runtime.getRuntime();
        return ((float) (runtime.totalMemory() - runtime.freeMemory())) >= ((float) runtime.maxMemory()) * this.f4967c;
    }
}
