package com.bytedance.apm.perf.traffic;
/* compiled from: TrafficUsageModel.java */
/* loaded from: classes.dex */
public class i implements Comparable {
    public String clientType;
    public String path;
    public long trafficBytes;
    public long requestTs = System.currentTimeMillis();
    public int requestCount = 1;

    public i(String str, long j2, String str2) {
        this.path = str;
        this.trafficBytes = j2;
        this.clientType = str2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        long j2 = this.trafficBytes;
        long j3 = ((i) obj).trafficBytes;
        if (j2 == j3) {
            return 0;
        }
        return j2 > j3 ? 1 : -1;
    }
}
