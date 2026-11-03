package com.bytedance.frameworks.baselib.network.http.exception;

import com.bytedance.retrofit2.ttnet.TTNetExceptionStorage;
/* compiled from: NetworkNotAvailabeException.java */
/* loaded from: classes2.dex */
public class d extends TTNetExceptionStorage {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7834a = -7281385706782665299L;

    /* renamed from: b  reason: collision with root package name */
    private static final int f7835b = -106;

    public int a() {
        return -106;
    }

    public d() {
    }

    public d(String str) {
        super(str);
    }

    public d(Throwable th) {
        initCause(th);
    }

    public d(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    @Override // com.bytedance.retrofit2.ttnet.TTNetExceptionStorage
    public void setInfo(boolean z, boolean z2, String str, String str2, Object obj) {
        super.setInfo(z, z2, str, str2, obj);
    }
}
