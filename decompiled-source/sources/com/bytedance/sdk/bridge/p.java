package com.bytedance.sdk.bridge;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/* compiled from: SubscriberInfo.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, g> f13263a = new HashMap();

    public void putMethodInfo(String str, g gVar) {
        this.f13263a.put(str, gVar);
    }

    public g getMethodInfo(String str) {
        return this.f13263a.get(str);
    }

    public Collection<g> getMethodInfos() {
        return this.f13263a.values();
    }

    public boolean hasBridgeMethod(String str) {
        return this.f13263a.containsKey(str);
    }
}
