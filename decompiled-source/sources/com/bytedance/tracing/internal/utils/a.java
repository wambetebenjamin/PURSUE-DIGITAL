package com.bytedance.tracing.internal.utils;

import java.util.Random;
/* compiled from: RandomUtil.java */
/* loaded from: classes3.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<Random> f14067a = new ThreadLocal<Random>() { // from class: com.bytedance.tracing.internal.utils.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Random initialValue() {
            return new Random();
        }
    };

    public static long uniqueId() {
        long j2 = 0;
        while (j2 == 0) {
            j2 = current().nextLong();
        }
        return Math.abs(j2);
    }

    public static Random current() {
        return f14067a.get();
    }
}
