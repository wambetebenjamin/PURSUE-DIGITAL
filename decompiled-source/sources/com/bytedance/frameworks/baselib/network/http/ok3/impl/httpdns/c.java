package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns;

import java.io.Serializable;
/* compiled from: HostResolveJob.java */
/* loaded from: classes2.dex */
public class c implements Serializable, Comparable<c> {

    /* renamed from: a  reason: collision with root package name */
    public g f8021a;

    /* renamed from: b  reason: collision with root package name */
    private String f8022b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f8023c;

    /* renamed from: d  reason: collision with root package name */
    private long f8024d = System.currentTimeMillis();

    public c(String str, g gVar, boolean z) {
        this.f8022b = str;
        this.f8021a = gVar;
        this.f8023c = z;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(c cVar) {
        long j2 = cVar.f8024d;
        long j3 = this.f8024d;
        if (j2 == j3) {
            return 0;
        }
        return j2 < j3 ? -1 : 1;
    }

    public String a() {
        return this.f8022b;
    }

    public boolean b() {
        return this.f8023c;
    }
}
