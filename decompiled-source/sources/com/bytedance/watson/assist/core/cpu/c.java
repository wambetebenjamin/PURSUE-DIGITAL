package com.bytedance.watson.assist.core.cpu;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: ProcStatInfo.java */
/* loaded from: classes8.dex */
public class c implements com.bytedance.watson.assist.file.b {

    /* renamed from: a  reason: collision with root package name */
    protected String f16236a;

    /* renamed from: b  reason: collision with root package name */
    protected int f16237b;

    /* renamed from: c  reason: collision with root package name */
    protected long f16238c;

    /* renamed from: d  reason: collision with root package name */
    protected long f16239d;

    /* renamed from: e  reason: collision with root package name */
    protected long f16240e;

    /* renamed from: f  reason: collision with root package name */
    protected double f16241f;

    /* renamed from: g  reason: collision with root package name */
    protected double f16242g;

    public c() {
        this.f16236a = null;
        this.f16237b = -1;
        this.f16238c = 0L;
        this.f16239d = 0L;
        this.f16240e = 0L;
        this.f16241f = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f16242g = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public c(String str, int i2) {
        this.f16236a = null;
        this.f16237b = -1;
        this.f16238c = 0L;
        this.f16239d = 0L;
        this.f16240e = 0L;
        this.f16241f = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f16242g = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f16236a = str;
        this.f16237b = i2;
    }

    public long getDeltaCpuTime() {
        return this.f16239d;
    }

    public long getMergedCpuTime() {
        return this.f16240e;
    }

    public double getCpuUsage() {
        return this.f16241f;
    }

    public void setCpuUsage(long j2) {
        if (this.f16239d >= 0) {
            this.f16241f = (com.bytedance.watson.assist.utils.a.getCpuCoreNum() * getDeltaCpuTime()) / j2;
        }
    }

    public void setMergedCpuUsage(long j2) {
        if (this.f16240e >= 0) {
            this.f16242g = (com.bytedance.watson.assist.utils.a.getCpuCoreNum() * getMergedCpuTime()) / j2;
        }
    }

    @Override // com.bytedance.watson.assist.file.b
    public void calculateDelta(com.bytedance.watson.assist.file.b bVar) {
        long cpuTime = getCpuTime() - (bVar == null ? 0L : ((c) bVar).getCpuTime());
        this.f16239d = cpuTime;
        if (this.f16240e == 0) {
            this.f16240e = cpuTime;
        }
    }

    @Override // com.bytedance.watson.assist.file.b
    public void merge(com.bytedance.watson.assist.file.b bVar) {
        if (bVar == null) {
            return;
        }
        this.f16240e += ((c) bVar).getMergedCpuTime();
    }

    public int getPid() {
        return this.f16237b;
    }

    public void setPid(int i2) {
        this.f16237b = i2;
    }

    public String getProcessName() {
        return this.f16236a;
    }

    public void setProcessName(String str) {
        this.f16236a = str;
    }

    public long getCpuTime() {
        return this.f16238c;
    }

    public void setCpuTime(long j2) {
        this.f16238c = j2;
    }

    public void reset() {
        this.f16236a = null;
        this.f16237b = -1;
        this.f16238c = 0L;
        this.f16239d = 0L;
        this.f16240e = 0L;
        this.f16241f = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f16242g = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("proc_stat:{pid=").append(this.f16237b).append(" process_name:").append(this.f16236a).append(" history cpu_time:").append(getCpuTime()).append(" delta cpu_time:").append(getDeltaCpuTime()).append(" delta cpu_time * cpuNum:").append(getDeltaCpuTime() * com.bytedance.watson.assist.utils.a.getCpuCoreNum()).append(" cpu_usage:").append(this.f16241f * 100.0d).append("%}");
        return sb.toString();
    }

    public String toMergeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("proc_stat:{pid=").append(this.f16237b).append(" process_name:").append(this.f16236a).append(" merged cpu_time:").append(getMergedCpuTime()).append(" cpu_usage:").append(this.f16242g * 100.0d).append("%}");
        return sb.toString();
    }
}
