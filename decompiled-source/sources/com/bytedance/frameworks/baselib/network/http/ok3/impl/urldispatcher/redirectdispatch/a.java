package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.redirectdispatch;
/* compiled from: TTRedirectDispatchConfig.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.frameworks.baselib.network.config.hostpathmatch.b<c> {
    @Override // com.bytedance.frameworks.baselib.network.config.hostpathmatch.b
    public String a() {
        return "ttnet_redirect_dp";
    }

    @Override // com.bytedance.frameworks.baselib.network.config.hostpathmatch.b
    /* renamed from: b */
    public c a(Object obj) {
        Integer num = obj instanceof Integer ? (Integer) obj : null;
        if (num == null) {
            return null;
        }
        c cVar = new c();
        cVar.f8230b = num.intValue() == 1;
        return cVar;
    }
}
