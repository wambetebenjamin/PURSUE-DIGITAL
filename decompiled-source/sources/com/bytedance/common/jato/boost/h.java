package com.bytedance.common.jato.boost;

import com.bytedance.common.jato.memory.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: BlackDenseRegionThresholdController.java */
/* loaded from: classes.dex */
public class h implements a.InterfaceC0064a {

    /* renamed from: b  reason: collision with root package name */
    private static final int f4565b = 10;

    /* renamed from: c  reason: collision with root package name */
    private static final int f4566c = 95;

    /* renamed from: d  reason: collision with root package name */
    private static final long f4567d = 180000;

    /* renamed from: e  reason: collision with root package name */
    private static final int f4568e = 2;

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicBoolean f4569j = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    private final Queue<Long> f4570a;

    /* renamed from: f  reason: collision with root package name */
    private final NavigableMap<Float, Integer> f4571f;

    /* renamed from: g  reason: collision with root package name */
    private int f4572g;

    /* renamed from: h  reason: collision with root package name */
    private long f4573h;

    /* renamed from: i  reason: collision with root package name */
    private final long f4574i;

    public static void a(Map<Float, Integer> map) {
        a(map, f4567d);
    }

    public static void a(Map<Float, Integer> map, long j2) {
        if (f4569j.compareAndSet(false, true)) {
            a.a().a(new h(map, j2));
        }
    }

    public static Map<Float, Integer> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(Float.valueOf(0.2f), 90);
        hashMap.put(Float.valueOf(0.5f), 95);
        hashMap.put(Float.valueOf(0.8f), 98);
        return hashMap;
    }

    public h(Map<Float, Integer> map) {
        this(map, f4567d);
    }

    public h(Map<Float, Integer> map, long j2) {
        this.f4570a = new LinkedList();
        TreeMap treeMap = new TreeMap();
        this.f4571f = treeMap;
        this.f4572g = 0;
        this.f4573h = 0L;
        treeMap.putAll(map);
        this.f4574i = j2;
    }

    @Override // com.bytedance.common.jato.memory.a.InterfaceC0064a
    public void a(long j2, long j3, long j4) {
        int intValue;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f4573h < this.f4574i) {
            if (this.f4570a.isEmpty()) {
                return;
            }
            this.f4570a.clear();
            return;
        }
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.totalMemory() - runtime.freeMemory();
        if (this.f4570a.size() >= 10) {
            this.f4570a.poll();
        }
        this.f4570a.offer(Long.valueOf(freeMemory));
        if (this.f4570a.size() < 10) {
            return;
        }
        long j5 = 0;
        for (Long l : this.f4570a) {
            j5 += l.longValue();
        }
        float size = (float) ((j5 / this.f4570a.size()) / maxMemory);
        boolean z = false;
        Iterator<Map.Entry<Float, Integer>> it = this.f4571f.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Float, Integer> next = it.next();
            if (size <= next.getKey().floatValue() && (intValue = next.getValue().intValue()) >= 0 && intValue <= 100) {
                if (this.f4572g != intValue && BlackDenseRegionThresholdOpt.a(intValue, 2) == 1) {
                    this.f4572g = intValue;
                    this.f4573h = currentTimeMillis;
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        int i2 = this.f4572g;
        if (i2 == 0 || i2 != 95) {
            BlackDenseRegionThresholdOpt.a();
            this.f4572g = 95;
            this.f4573h = currentTimeMillis;
        }
    }
}
