package com.bytedance.sync.v2.topic;

import com.bytedance.sync.logger.c;
import com.bytedance.sync.model.e;
import com.bytedance.sync.t;
import org.json.JSONObject;
/* compiled from: SubscribeTopicCallbackWrapper.java */
/* loaded from: classes3.dex */
public class b implements com.bytedance.sync.interfaze.a<Void> {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.sync.interfaze.a<Void> f13971a;

    /* renamed from: b  reason: collision with root package name */
    private final e f13972b;

    /* renamed from: c  reason: collision with root package name */
    private final String f13973c;

    public b(e eVar, String str, com.bytedance.sync.interfaze.a<Void> aVar) {
        this.f13971a = aVar;
        this.f13972b = eVar;
        this.f13973c = str;
    }

    @Override // com.bytedance.sync.interfaze.a
    public void onSuccess(Void r3) {
        c.d(this.f13972b + " " + this.f13973c + " success");
        com.bytedance.sync.interfaze.a<Void> aVar = this.f13971a;
        if (aVar != null) {
            aVar.onSuccess(r3);
        }
        a(true, 0, null);
    }

    private void a(boolean z, int i2, String str) {
        JSONObject jSONObject = new JSONObject();
        com.bytedance.sync.util.a.safePutParam(jSONObject, "success", z);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "method", this.f13973c);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "business", this.f13972b.getBusiness());
        if (!z) {
            com.bytedance.sync.util.a.safePutParam(jSONObject, "error_code", i2);
            com.bytedance.sync.util.a.safePutParam(jSONObject, "error_msg", str);
        }
        t.monitor("sync_sdk_custom_topic", jSONObject, null, null);
    }

    @Override // com.bytedance.sync.interfaze.a
    public void onFailed(com.bytedance.sync.exc.b bVar) {
        c.d(this.f13972b + " " + this.f13973c + " failed. reason :code-> " + bVar.getErrorCode() + ", msg = " + bVar.getErrorMsg());
        com.bytedance.sync.interfaze.a<Void> aVar = this.f13971a;
        if (aVar != null) {
            aVar.onFailed(bVar);
        }
        a(false, bVar.getErrorCode(), bVar.getErrorMsg());
    }
}
