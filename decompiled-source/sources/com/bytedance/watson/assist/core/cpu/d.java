package com.bytedance.watson.assist.core.cpu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* compiled from: ProcTimeInStateInfo.java */
/* loaded from: classes8.dex */
public class d implements com.bytedance.watson.assist.file.b {

    /* renamed from: a  reason: collision with root package name */
    private List<LinkedHashMap<Long, Long>> f16243a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private List<LinkedHashMap<Long, Long>> f16244b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private List<LinkedHashMap<Long, Long>> f16245c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    private List<Integer> f16246d = com.bytedance.watson.assist.utils.a.getClusterCpuNum();

    /* renamed from: e  reason: collision with root package name */
    private long f16247e = 0;

    /* renamed from: f  reason: collision with root package name */
    private long f16248f = 0;

    /* renamed from: g  reason: collision with root package name */
    private long f16249g = 0;

    @Override // com.bytedance.watson.assist.file.b
    public void calculateDelta(com.bytedance.watson.assist.file.b bVar) {
        if (bVar == null) {
            this.f16244b.addAll(this.f16243a);
            return;
        }
        List<LinkedHashMap<Long, Long>> freqTimeMapList = ((d) bVar).getFreqTimeMapList();
        if (freqTimeMapList.isEmpty()) {
            return;
        }
        if (this.f16243a.size() != freqTimeMapList.size()) {
            com.bytedance.watson.assist.utils.b.w("calculate freqTimeDetla size error");
            return;
        }
        this.f16244b.clear();
        for (int i2 = 0; i2 != this.f16243a.size(); i2++) {
            LinkedHashMap<Long, Long> linkedHashMap = freqTimeMapList.get(i2);
            LinkedHashMap<Long, Long> linkedHashMap2 = new LinkedHashMap<>();
            for (Map.Entry<Long, Long> entry : this.f16243a.get(i2).entrySet()) {
                Long key = entry.getKey();
                Long value = entry.getValue();
                Long l = linkedHashMap.get(key);
                if (l != null) {
                    linkedHashMap2.put(key, Long.valueOf(value.longValue() - l.longValue()));
                } else {
                    com.bytedance.watson.assist.utils.b.e("calculate freqTimeDetla freq not found " + key);
                }
            }
            this.f16244b.add(linkedHashMap2);
        }
    }

    @Override // com.bytedance.watson.assist.file.b
    public void merge(com.bytedance.watson.assist.file.b bVar) {
        if (bVar == null) {
            return;
        }
        d dVar = (d) bVar;
        List<LinkedHashMap<Long, Long>> freqTimeMapList = dVar.getFreqTimeMapList();
        List<LinkedHashMap<Long, Long>> freqDeltaTimeMapList = dVar.getFreqDeltaTimeMapList();
        if (!freqDeltaTimeMapList.isEmpty()) {
            freqTimeMapList = freqDeltaTimeMapList;
        }
        if (this.f16245c.isEmpty()) {
            this.f16245c.addAll(freqTimeMapList);
            com.bytedance.watson.assist.utils.b.d("merge first");
            return;
        }
        int size = this.f16245c.size();
        if (size != freqTimeMapList.size()) {
            com.bytedance.watson.assist.utils.b.w("merge freqTime size error");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 != size; i2++) {
            LinkedHashMap<Long, Long> linkedHashMap = freqTimeMapList.get(i2);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry<Long, Long> entry : this.f16245c.get(i2).entrySet()) {
                Long key = entry.getKey();
                Long value = entry.getValue();
                Long l = linkedHashMap.get(key);
                if (l != null) {
                    linkedHashMap2.put(key, Long.valueOf(value.longValue() + l.longValue()));
                } else {
                    com.bytedance.watson.assist.utils.b.w("merge freqTimeDetla freq not found " + key);
                }
            }
            arrayList.add(linkedHashMap2);
        }
        this.f16245c = arrayList;
    }

    public void addFreqTimeItem(LinkedHashMap<Long, Long> linkedHashMap) {
        if (linkedHashMap != null) {
            this.f16243a.add(linkedHashMap);
        }
    }

    public List<LinkedHashMap<Long, Long>> getFreqTimeMapList() {
        return this.f16243a;
    }

    public List<LinkedHashMap<Long, Long>> getFreqDeltaTimeMapList() {
        return this.f16244b;
    }

    public List<LinkedHashMap<Long, Long>> getFreqMergeTimeMapList() {
        return this.f16245c;
    }

    private long a(List<LinkedHashMap<Long, Long>> list) {
        int size;
        long j2 = 0;
        if (list != null && !list.isEmpty() && (size = list.size()) == this.f16246d.size()) {
            for (int i2 = 0; i2 != size; i2++) {
                for (Map.Entry<Long, Long> entry : list.get(i2).entrySet()) {
                    j2 += entry.getValue().longValue();
                }
            }
        }
        return j2;
    }

    public long getTotalCpuTime() {
        if (this.f16247e == 0) {
            this.f16247e = a(this.f16243a);
        }
        return this.f16247e;
    }

    public long getTotalDeltaCpuTime() {
        if (this.f16248f == 0) {
            this.f16248f = a(this.f16244b);
        }
        return this.f16248f;
    }

    public long getTotalMergeDeltaCpuTime() {
        if (this.f16249g == 0) {
            this.f16249g = a(this.f16245c);
        }
        return this.f16249g;
    }

    public void reset() {
        this.f16243a.clear();
        this.f16244b.clear();
        this.f16245c.clear();
        this.f16247e = 0L;
        this.f16248f = 0L;
        this.f16249g = 0L;
    }

    public String toString() {
        return "ProcTimeInStateInfo{freqTimeMapList=" + this.f16243a + ", freqDeltaTimeMapList=" + this.f16244b + ", totalCpuTime=" + getTotalCpuTime() + ", totalDeltaCpuTime=" + getTotalDeltaCpuTime() + ", totalMergeCpuTime=" + getTotalMergeDeltaCpuTime() + '}';
    }
}
