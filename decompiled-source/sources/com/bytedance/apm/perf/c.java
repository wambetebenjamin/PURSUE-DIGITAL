package com.bytedance.apm.perf;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* compiled from: DataAggregator.java */
@Deprecated
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1259a = "DataAggregator";

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<String, a> f1260b = new HashMap<>();

    private void a(String str, double d2, double d3, double d4, double d5) {
    }

    public void init() {
    }

    public static c getInstance() {
        return b.f1270a;
    }

    /* compiled from: DataAggregator.java */
    /* loaded from: classes.dex */
    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final c f1270a = new c();

        private b() {
        }
    }

    /* compiled from: DataAggregator.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        String f1261a;

        /* renamed from: b  reason: collision with root package name */
        double f1262b;

        /* renamed from: c  reason: collision with root package name */
        double f1263c;

        /* renamed from: d  reason: collision with root package name */
        double f1264d;

        /* renamed from: e  reason: collision with root package name */
        double f1265e;

        /* renamed from: f  reason: collision with root package name */
        String f1266f;

        /* renamed from: g  reason: collision with root package name */
        Map<String, String> f1267g;

        /* renamed from: h  reason: collision with root package name */
        long f1268h;

        /* renamed from: i  reason: collision with root package name */
        int f1269i = 1;

        public a(String str, long j2, double d2, double d3, double d4, double d5) {
            this.f1261a = str;
            this.f1262b = d2;
            this.f1265e = d3;
            this.f1264d = d4;
            this.f1265e = d5;
            this.f1268h = j2;
        }

        public a injectScene(String str) {
            this.f1266f = str;
            return this;
        }

        private a a(Map<String, String> map) {
            this.f1267g = map;
            return this;
        }

        public String toString() {
            return "CpuItem{service='" + this.f1261a + "', metricRate=" + this.f1262b + ", metricMaxRate=" + this.f1263c + ", metricCpuStats=" + this.f1264d + ", metricMaxCpuStats=" + this.f1265e + ", sceneString='" + this.f1266f + "', tagMap=" + this.f1267g + ", firstTs=" + this.f1268h + ", times=" + this.f1269i + '}';
        }
    }

    public void enqueuePerfLog(double d2, double d3, double d4, double d5) {
        String str = g.getInstance().getSceneString();
        a aVar = this.f1260b.get(str);
        if (aVar == null) {
            a aVar2 = new a("cpu", System.currentTimeMillis(), d2, d3, d4, d5);
            aVar2.injectScene(g.getInstance().getSceneString());
            this.f1260b.put(str, aVar2);
            return;
        }
        aVar.f1269i++;
        aVar.f1264d += d4;
        aVar.f1262b += d2;
        if (aVar.f1262b < d2) {
            aVar.f1262b = d2;
        }
        if (aVar.f1264d < d4) {
            aVar.f1264d = d4;
        }
        this.f1260b.put(str, aVar);
    }

    public void handleCpuTimer(long j2) {
        long j3;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.f1260b) {
                if (!this.f1260b.isEmpty()) {
                    Iterator<Map.Entry<String, a>> it = this.f1260b.entrySet().iterator();
                    while (it.hasNext()) {
                        a value = it.next().getValue();
                        com.bytedance.apm.logging.e.d("CpuApm2", "cache data: " + value);
                        if (currentTimeMillis - value.f1268h > j2) {
                            it.remove();
                            double d2 = value.f1262b / value.f1269i;
                            double d3 = value.f1263c / value.f1269i;
                            double d4 = value.f1264d / value.f1269i;
                            double d5 = value.f1265e / value.f1269i;
                            j3 = currentTimeMillis;
                            com.bytedance.apm.logging.e.d("CpuApm2", "assemble cpu data, rate: " + d2 + " maxRate: " + d3 + " speed: " + d4 + " maxSpeed: " + d5);
                            a(value.f1266f, d2, d3, d4, d5);
                        } else {
                            j3 = currentTimeMillis;
                        }
                        currentTimeMillis = j3;
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(f1259a, "handleLogToQueue function failed :" + e2.toString());
        }
    }
}
