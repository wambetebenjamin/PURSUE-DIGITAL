package com.bytedance.push.settings.notification;

import com.bytedance.sdk.account.induce.a;
import org.json.JSONObject;
/* compiled from: MessageBlackTimeWindowConverter.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.common.push.c implements com.bytedance.push.settings.c<c>, com.bytedance.push.settings.d<c> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11817a = "need_intercept";

    /* renamed from: b  reason: collision with root package name */
    private final String f11818b = com.bytedance.apm.constant.a.BEGIN_TIME;

    /* renamed from: c  reason: collision with root package name */
    private final String f11819c = "end_time";

    /* renamed from: d  reason: collision with root package name */
    private final String f11820d = a.InterfaceC0192a.DAY;

    /* renamed from: e  reason: collision with root package name */
    private final String f11821e = a.InterfaceC0192a.MINUTE;

    /* renamed from: f  reason: collision with root package name */
    private final String f11822f = a.InterfaceC0192a.HOUR;

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public c create() {
        return new c();
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public c to(String str) {
        c create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11823a = jSONObject.optBoolean("need_intercept");
            JSONObject optJSONObject = jSONObject.optJSONObject(com.bytedance.apm.constant.a.BEGIN_TIME);
            if (optJSONObject != null) {
                create.f11824b = optJSONObject.optInt(a.InterfaceC0192a.HOUR, -1);
                create.f11825c = optJSONObject.optInt(a.InterfaceC0192a.MINUTE, -1);
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("end_time");
            if (optJSONObject2 != null) {
                create.f11826d = optJSONObject2.optInt(a.InterfaceC0192a.DAY, -1);
                create.f11827e = optJSONObject2.optInt(a.InterfaceC0192a.HOUR, -1);
                create.f11828f = optJSONObject2.optInt(a.InterfaceC0192a.MINUTE, -1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(c cVar) {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "need_intercept", cVar.f11823a);
        JSONObject jSONObject2 = new JSONObject();
        add(jSONObject2, a.InterfaceC0192a.HOUR, cVar.f11824b);
        add(jSONObject2, a.InterfaceC0192a.MINUTE, cVar.f11825c);
        add(jSONObject, com.bytedance.apm.constant.a.BEGIN_TIME, jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        add(jSONObject3, a.InterfaceC0192a.DAY, cVar.f11826d);
        add(jSONObject3, a.InterfaceC0192a.HOUR, cVar.f11827e);
        add(jSONObject3, a.InterfaceC0192a.MINUTE, cVar.f11828f);
        add(jSONObject, "end_time", jSONObject3);
        return null;
    }
}
