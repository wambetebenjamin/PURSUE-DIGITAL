package com.bytedance.watson.assist.core.cpu;
/* compiled from: ThreadStatInfo.java */
/* loaded from: classes8.dex */
public class e extends c {
    @Override // com.bytedance.watson.assist.core.cpu.c
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("thread_stat:{tid=").append(this.f16237b).append(" thread_name:").append(this.f16236a).append(" cpu_time:").append(getDeltaCpuTime()).append(" cpu_usage:").append(this.f16241f * 100.0d).append("%}");
        return sb.toString();
    }

    @Override // com.bytedance.watson.assist.core.cpu.c
    public String toMergeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("thread_stat:{tid=").append(this.f16237b).append(" thread_name:").append(this.f16236a).append(" cpu_time:").append(getMergedCpuTime()).append(" cpu_usage:").append(this.f16242g * 100.0d).append("%}");
        return sb.toString();
    }
}
