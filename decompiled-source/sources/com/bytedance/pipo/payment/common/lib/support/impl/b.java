package com.bytedance.pipo.payment.common.lib.support.impl;

import org.json.JSONObject;
/* compiled from: EventService.java */
/* loaded from: classes2.dex */
public class b implements com.bytedance.pipo.payment.common.lib.support.service.b {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.pipo.payment.common.lib.interfaze.b f10951a = new com.bytedance.pipo.payment.common.lib.defaultimpl.a();

    @Override // com.bytedance.pipo.payment.common.lib.support.service.b
    public void a(com.bytedance.pipo.payment.common.lib.interfaze.b bVar) {
        if (bVar != null) {
            this.f10951a = bVar;
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.interfaze.b
    public void a(String str, JSONObject jSONObject) {
        this.f10951a.a(str, jSONObject);
    }
}
