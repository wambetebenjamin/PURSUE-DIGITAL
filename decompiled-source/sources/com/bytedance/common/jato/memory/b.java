package com.bytedance.common.jato.memory;

import com.bytedance.common.jato.boost.RSpaceLiveRatioOpt;
import com.bytedance.common.jato.memory.a;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: GcLiveRatioController.java */
/* loaded from: classes.dex */
public class b implements a.InterfaceC0064a {

    /* renamed from: b  reason: collision with root package name */
    private static final int f4891b = 10;

    /* renamed from: c  reason: collision with root package name */
    private static final int f4892c = 75;

    /* renamed from: d  reason: collision with root package name */
    private static final long f4893d = 180000;

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicBoolean f4894i = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    private final Queue<Long> f4895a;

    /* renamed from: e  reason: collision with root package name */
    private final NavigableMap<Float, Integer> f4896e;

    /* renamed from: f  reason: collision with root package name */
    private int f4897f;

    /* renamed from: g  reason: collision with root package name */
    private long f4898g;

    /* renamed from: h  reason: collision with root package name */
    private final long f4899h;

    public static void a(Map<Float, Integer> map) {
        a(map, f4893d);
    }

    public static void a(Map<Float, Integer> map, long j2) {
        if (f4894i.compareAndSet(false, true)) {
            a.a().a(new b(map, j2));
        }
    }

    public b(Map<Float, Integer> map) {
        this(map, f4893d);
    }

    public b(Map<Float, Integer> map, long j2) {
        this.f4895a = new LinkedList();
        TreeMap treeMap = new TreeMap();
        this.f4896e = treeMap;
        this.f4897f = 0;
        this.f4898g = 0L;
        treeMap.putAll(map);
        this.f4899h = j2;
    }

    @Override // com.bytedance.common.jato.memory.a.InterfaceC0064a
    public void a(long j2, long j3, long j4) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f4898g < this.f4899h) {
            if (this.f4895a.isEmpty()) {
                return;
            }
            this.f4895a.clear();
            return;
        }
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.totalMemory() - runtime.freeMemory();
        if (this.f4895a.size() >= 10) {
            this.f4895a.poll();
        }
        this.f4895a.offer(Long.valueOf(freeMemory));
        if (this.f4895a.size() < 10) {
            return;
        }
        long j5 = 0;
        for (Long l : this.f4895a) {
            j5 += l.longValue();
        }
        float size = (float) ((j5 / this.f4895a.size()) / maxMemory);
        boolean z = false;
        Iterator<Map.Entry<Float, Integer>> it = this.f4896e.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<Float, Integer> next = it.next();
            if (size <= next.getKey().floatValue()) {
                if (!next.getValue().equals(Integer.valueOf(this.f4897f))) {
                    int intValue = next.getValue().intValue();
                    RSpaceLiveRatioOpt.a(intValue, 2);
                    this.f4897f = intValue;
                    this.f4898g = currentTimeMillis;
                }
                z = true;
            }
        }
        if (z) {
            return;
        }
        int i2 = this.f4897f;
        if (i2 == 0 || i2 != 75) {
            RSpaceLiveRatioOpt.a();
            this.f4897f = 75;
            this.f4898g = currentTimeMillis;
        }
    }
}
