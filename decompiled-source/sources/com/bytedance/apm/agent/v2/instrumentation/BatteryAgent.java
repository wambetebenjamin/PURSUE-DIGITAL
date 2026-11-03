package com.bytedance.apm.agent.v2.instrumentation;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class BatteryAgent {
    private static final Map<String, a> sExecutedRecord = new HashMap();
    private static ThreadLocal<HashMap<String, b>> sExecutingRecord = new ThreadLocal<>();
    private static boolean hasHook = false;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private int f355a = 0;

        /* renamed from: b  reason: collision with root package name */
        private int f356b = 0;

        public void a(b bVar) {
            this.f355a += bVar.f360d;
            this.f356b = (int) (this.f356b + (bVar.f358b - bVar.f357a));
        }

        public String toString() {
            return "MethodExecutedValue{exeCount=" + this.f355a + ", cpuCost=" + this.f356b + '}';
        }

        public int a() {
            return this.f355a;
        }

        public int b() {
            return this.f356b;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private long f357a = 0;

        /* renamed from: b  reason: collision with root package name */
        private long f358b = 0;

        /* renamed from: c  reason: collision with root package name */
        private int f359c = 0;

        /* renamed from: d  reason: collision with root package name */
        private int f360d = 0;

        public void a() {
            if (this.f360d == 0) {
                this.f357a = SystemClock.currentThreadTimeMillis();
            }
            this.f359c++;
            this.f360d++;
        }

        public boolean b() {
            int i2 = this.f359c - 1;
            this.f359c = i2;
            if (i2 == 0) {
                this.f358b = SystemClock.currentThreadTimeMillis();
                return true;
            }
            return false;
        }
    }

    public static boolean hasHook() {
        return hasHook;
    }

    public static void s(String str) {
        hasHook = true;
        HashMap<String, b> hashMap = sExecutingRecord.get();
        if (hashMap == null) {
            hashMap = new HashMap<>();
            sExecutingRecord.set(hashMap);
        }
        b bVar = hashMap.get(str);
        if (bVar == null) {
            bVar = new b();
            hashMap.put(str, bVar);
        }
        bVar.a();
    }

    public static void e(String str) {
        b bVar;
        HashMap<String, b> hashMap = sExecutingRecord.get();
        if (hashMap == null || (bVar = hashMap.get(str)) == null || !bVar.b()) {
            return;
        }
        hashMap.remove(str);
        Map<String, a> map = sExecutedRecord;
        synchronized (map) {
            a aVar = map.get(str);
            if (aVar == null) {
                aVar = new a();
                map.put(str, aVar);
            }
            aVar.a(bVar);
        }
    }

    public static Map<String, a> filterResultAndClear(long j2) {
        HashMap hashMap = new HashMap();
        Map<String, a> map = sExecutedRecord;
        if (map.isEmpty()) {
            return hashMap;
        }
        synchronized (map) {
            for (Map.Entry<String, a> entry : map.entrySet()) {
                if (entry.getValue().f356b > j2) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
            sExecutedRecord.clear();
        }
        return hashMap;
    }
}
