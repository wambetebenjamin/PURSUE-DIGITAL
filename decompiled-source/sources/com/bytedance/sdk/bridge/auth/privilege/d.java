package com.bytedance.sdk.bridge.auth.privilege;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: BridgePrivilegeService.java */
/* loaded from: classes2.dex */
public abstract class d<T, B> {

    /* renamed from: a  reason: collision with root package name */
    protected Map<T, B> f13120a = new ConcurrentHashMap();

    public abstract boolean checkConfig(T t, String str);

    public abstract boolean checkSafeList(T t);

    public abstract void requestAuthApi();
}
