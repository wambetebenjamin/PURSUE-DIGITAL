package com.bytedance.watson.assist.core.cpu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* compiled from: CpuTimeStatInfo.java */
/* loaded from: classes8.dex */
public class b implements com.bytedance.watson.assist.file.b {

    /* renamed from: a  reason: collision with root package name */
    private List<LinkedHashMap<Long, Long>> f16232a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private long f16233b = 0;

    /* renamed from: c  reason: collision with root package name */
    private long f16234c = 0;

    /* renamed from: d  reason: collision with root package name */
    private long f16235d = 0;

    public long getDeltaCpuTime() {
        return this.f16234c;
    }

    public long getTotalCpuTime() {
        if (this.f16233b == 0 && !this.f16232a.isEmpty()) {
            for (LinkedHashMap<Long, Long> linkedHashMap : this.f16232a) {
                for (Map.Entry<Long, Long> entry : linkedHashMap.entrySet()) {
                    this.f16233b += entry.getValue().longValue();
                }
            }
        }
        return this.f16233b;
    }

    public long getMergedDeltaCpuTime() {
        return this.f16235d;
    }

    public void putFreqTimeMap(LinkedHashMap<Long, Long> linkedHashMap) {
        this.f16232a.add(linkedHashMap);
    }

    @Override // com.bytedance.watson.assist.file.b
    public void calculateDelta(com.bytedance.watson.assist.file.b bVar) {
        if (bVar == null) {
            return;
        }
        long totalCpuTime = getTotalCpuTime() - ((b) bVar).getTotalCpuTime();
        this.f16234c = totalCpuTime;
        if (this.f16235d == 0) {
            this.f16235d = totalCpuTime;
        }
    }

    @Override // com.bytedance.watson.assist.file.b
    public void merge(com.bytedance.watson.assist.file.b bVar) {
        if (bVar == null) {
            return;
        }
        this.f16235d += ((b) bVar).getMergedDeltaCpuTime();
    }

    public void reset() {
        this.f16232a.clear();
        this.f16233b = 0L;
        this.f16234c = 0L;
        this.f16235d = 0L;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cputime:{").append("deltaTime:").append(getDeltaCpuTime()).append(" totaltime:").append(getTotalCpuTime()).append("}");
        return sb.toString();
    }

    public String toMergeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cputime:{").append("deltaTime:").append(getMergedDeltaCpuTime()).append("}");
        return sb.toString();
    }
}
