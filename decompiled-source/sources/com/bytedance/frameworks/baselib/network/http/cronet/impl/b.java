package com.bytedance.frameworks.baselib.network.http.cronet.impl;

import com.bytedance.retrofit2.ttnet.TTNetExceptionStorage;
/* compiled from: CronetIOException.java */
/* loaded from: classes2.dex */
public class b extends TTNetExceptionStorage {

    /* renamed from: a  reason: collision with root package name */
    private l f7700a;

    /* renamed from: c  reason: collision with root package name */
    private int f7701c;

    /* renamed from: d  reason: collision with root package name */
    private final int f7702d;

    public b(Exception exc, com.bytedance.frameworks.baselib.network.http.a aVar, int i2) {
        super(exc.getMessage(), exc.getCause() == null ? exc : exc.getCause());
        super.setInfo(false, true, aVar.Z != null ? aVar.Z.toString() : "", aVar.ae, aVar);
        this.f7702d = i2;
        if (exc instanceof com.bytedance.frameworks.baselib.network.http.exception.c) {
            this.f7701c = ((com.bytedance.frameworks.baselib.network.http.exception.c) exc).a();
        }
        if (aVar.Y.networklib.misc.requestExtraInfo instanceof l) {
            this.f7700a = (l) aVar.Y.networklib.misc.requestExtraInfo;
        }
    }

    public com.bytedance.frameworks.baselib.network.http.a c() {
        return (com.bytedance.frameworks.baselib.network.http.a) this.infoObj;
    }

    public String a() {
        return c().F;
    }

    public int d() {
        return this.f7701c;
    }

    public int e() {
        return this.f7702d;
    }

    public l b() {
        return this.f7700a;
    }
}
