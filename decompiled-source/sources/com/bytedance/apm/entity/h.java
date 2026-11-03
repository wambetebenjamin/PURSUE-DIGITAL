package com.bytedance.apm.entity;
/* compiled from: ReportTrafficEntity.java */
/* loaded from: classes.dex */
public class h extends k {

    /* renamed from: a  reason: collision with root package name */
    private long f1035a;

    public h(long j2, int i2, int i3, int i4, long j3, long j4, long j5) {
        super(j2, i2, i3, i4, j3, j5);
        this.f1035a = j4;
    }

    public long getEndTime() {
        return this.f1035a;
    }

    public long getStartTime() {
        return getTime();
    }
}
