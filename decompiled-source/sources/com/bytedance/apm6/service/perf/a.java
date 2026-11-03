package com.bytedance.apm6.service.perf;

import android.util.Pair;
import java.util.LinkedList;
/* compiled from: ICpuDataService.java */
/* loaded from: classes.dex */
public interface a {
    double getCpuRate();

    double getCpuSpeed();

    Pair<Long, LinkedList<C0033a>> getExceptionThreadList();

    Pair<Long, LinkedList<C0033a>> getThreadList();

    /* compiled from: ICpuDataService.java */
    /* renamed from: com.bytedance.apm6.service.perf.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0033a {

        /* renamed from: a  reason: collision with root package name */
        private String f2370a;

        /* renamed from: b  reason: collision with root package name */
        private double f2371b;

        /* renamed from: c  reason: collision with root package name */
        private int f2372c;

        public C0033a(String str, double d2, int i2) {
            this.f2370a = str;
            this.f2371b = d2;
            this.f2372c = i2;
        }

        public String getThreadName() {
            return this.f2370a;
        }

        public double getCpuUsage() {
            return this.f2371b;
        }

        public int getTid() {
            return this.f2372c;
        }
    }
}
