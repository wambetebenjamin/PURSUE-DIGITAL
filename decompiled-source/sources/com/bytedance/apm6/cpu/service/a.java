package com.bytedance.apm6.cpu.service;

import android.util.Pair;
import com.bytedance.apm6.service.perf.a;
import java.util.Collection;
import java.util.LinkedList;
/* compiled from: CurrentCpuDataHolder.java */
/* loaded from: classes.dex */
public class a implements com.bytedance.apm6.service.perf.a {

    /* renamed from: a  reason: collision with root package name */
    private double f2115a;

    /* renamed from: b  reason: collision with root package name */
    private double f2116b;

    /* renamed from: c  reason: collision with root package name */
    private Pair<Long, LinkedList<a.C0033a>> f2117c;

    /* renamed from: d  reason: collision with root package name */
    private Pair<Long, LinkedList<a.C0033a>> f2118d;

    private a() {
        this.f2115a = -1.0d;
        this.f2116b = -1.0d;
        this.f2117c = new Pair<>(0L, new LinkedList());
        this.f2118d = new Pair<>(0L, new LinkedList());
    }

    /* compiled from: CurrentCpuDataHolder.java */
    /* renamed from: com.bytedance.apm6.cpu.service.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0032a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f2119a = new a();

        private C0032a() {
        }
    }

    public static a getInstance() {
        return C0032a.f2119a;
    }

    public void refreshData(double d2, double d3) {
        this.f2115a = d2;
        this.f2116b = d3;
    }

    public synchronized void refreshTopThreadData(LinkedList<a.C0033a> linkedList) {
        this.f2117c = new Pair<>(Long.valueOf(System.currentTimeMillis()), linkedList);
    }

    public synchronized void refreshExceptionTopThreadData(LinkedList<a.C0033a> linkedList) {
        this.f2118d = new Pair<>(Long.valueOf(System.currentTimeMillis()), linkedList);
    }

    @Override // com.bytedance.apm6.service.perf.a
    public double getCpuRate() {
        return this.f2115a;
    }

    @Override // com.bytedance.apm6.service.perf.a
    public double getCpuSpeed() {
        return this.f2116b;
    }

    @Override // com.bytedance.apm6.service.perf.a
    public Pair<Long, LinkedList<a.C0033a>> getThreadList() {
        if (((LinkedList) this.f2117c.second).isEmpty()) {
            return this.f2117c;
        }
        return new Pair<>(this.f2117c.first, new LinkedList((Collection) this.f2117c.second));
    }

    @Override // com.bytedance.apm6.service.perf.a
    public Pair<Long, LinkedList<a.C0033a>> getExceptionThreadList() {
        if (((LinkedList) this.f2118d.second).isEmpty()) {
            return this.f2118d;
        }
        return new Pair<>(this.f2118d.first, new LinkedList((Collection) this.f2118d.second));
    }
}
