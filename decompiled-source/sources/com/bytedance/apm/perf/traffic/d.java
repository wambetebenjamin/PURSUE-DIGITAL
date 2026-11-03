package com.bytedance.apm.perf.traffic;

import java.util.HashMap;
import java.util.Map;
/* compiled from: LruKhighFreqHelper.java */
/* loaded from: classes.dex */
public class d {
    public Map<String, i> history;
    public long intervalStartTs;
    public Map<String, i> usageStore;
    public int historyLimitSize = 50;
    public int storeLimitSize = 20;
    public int highFreqThreshold = 50;
    public int historyFreqThreshold = Math.min(3, 50 / 2);

    /* renamed from: a  reason: collision with root package name */
    private int f1383a = 0;

    /* compiled from: LruKhighFreqHelper.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final d f1384a = new d();

        private a() {
        }
    }

    public static d getInstance() {
        return a.f1384a;
    }

    public void start() {
        this.intervalStartTs = System.currentTimeMillis();
    }

    public void clear() {
        this.intervalStartTs = 0L;
        this.f1383a = 0;
        Map<String, i> map = this.history;
        if (map != null) {
            map.clear();
            this.history = null;
        }
        Map<String, i> map2 = this.usageStore;
        if (map2 != null) {
            map2.clear();
            this.usageStore = null;
        }
    }

    public synchronized void add(long j2, String str, String str2) {
        if (this.usageStore == null) {
            this.usageStore = new HashMap();
        }
        if (this.usageStore.containsKey(str)) {
            i iVar = this.usageStore.get(str);
            iVar.requestCount++;
            iVar.requestTs = System.currentTimeMillis();
            if (iVar.requestCount > this.f1383a) {
                this.f1383a = iVar.requestCount;
            }
        } else {
            Map<String, i> map = this.history;
            if (map != null) {
                long j3 = Long.MAX_VALUE;
                String str3 = null;
                if (map.containsKey(str)) {
                    i iVar2 = this.history.get(str);
                    int i2 = iVar2.requestCount;
                    iVar2.requestCount = i2 + 1;
                    iVar2.requestTs = System.currentTimeMillis();
                    if (i2 > this.historyFreqThreshold) {
                        this.history.remove(str);
                        if (this.usageStore.size() >= this.storeLimitSize) {
                            long currentTimeMillis = this.intervalStartTs + ((System.currentTimeMillis() - this.intervalStartTs) / 2);
                            for (Map.Entry<String, i> entry : this.usageStore.entrySet()) {
                                if (entry.getValue().requestTs < currentTimeMillis && entry.getValue().requestCount < j3) {
                                    str3 = entry.getValue().path;
                                    j3 = entry.getValue().requestCount;
                                }
                            }
                            if (str3 != null) {
                                this.usageStore.remove(str3);
                            }
                        }
                        this.usageStore.put(str, iVar2);
                    }
                } else {
                    if (this.history.size() >= this.historyLimitSize) {
                        for (Map.Entry<String, i> entry2 : this.history.entrySet()) {
                            if (entry2.getValue().requestTs < j3) {
                                j3 = entry2.getValue().requestTs;
                                str3 = entry2.getValue().path;
                            }
                        }
                        if (str3 != null) {
                            this.history.remove(str3);
                        }
                    }
                    this.history.put(str, new i(str, j2, str2));
                }
            } else {
                HashMap hashMap = new HashMap();
                this.history = hashMap;
                hashMap.put(str, new i(str, j2, str2));
            }
        }
    }

    public synchronized Map<String, i> getHighFreqMap() {
        return this.usageStore;
    }

    public int getMaxFreq() {
        return this.f1383a;
    }
}
