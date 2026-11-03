package com.bytedance.sdk.bridge;

import com.bytedance.sdk.bridge.annotation.BridgePrivilege;
import com.bytedance.sdk.bridge.annotation.BridgeSyncType;
import java.lang.reflect.Method;
/* compiled from: BridgeMethodInfo.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private final Method f13156a;

    /* renamed from: b  reason: collision with root package name */
    private final String f13157b;
    @BridgePrivilege

    /* renamed from: c  reason: collision with root package name */
    private final String f13158c;
    @BridgeSyncType

    /* renamed from: d  reason: collision with root package name */
    private final String f13159d;

    /* renamed from: e  reason: collision with root package name */
    private final h[] f13160e;

    public g(Method method, String str, String str2, String str3, h[] hVarArr) {
        this.f13156a = method;
        this.f13157b = str;
        this.f13158c = str2;
        this.f13159d = str3;
        this.f13160e = hVarArr;
    }

    public Method getMethod() {
        return this.f13156a;
    }

    public String getBridgeMethodName() {
        return this.f13157b;
    }

    public String getMethodPrivilege() {
        return this.f13158c;
    }

    public String getSyncType() {
        return this.f13159d;
    }

    public h[] getParamInfos() {
        return this.f13160e;
    }
}
