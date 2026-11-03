package com.bytedance.common.wschannel.heartbeat.monitor;

import com.bytedance.common.wschannel.heartbeat.smart.state.g;
/* compiled from: HeartBeatHprof.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private g f5660a;

    /* renamed from: b  reason: collision with root package name */
    private long f5661b;

    public a(g gVar, long j2) {
        this.f5660a = gVar;
        this.f5661b = j2;
    }

    public g a() {
        return this.f5660a;
    }

    public long b() {
        return this.f5661b;
    }

    public String toString() {
        return "HeartBeatHprof{currentStateType=" + this.f5660a + ", currentPingInterval=" + this.f5661b + '}';
    }
}
