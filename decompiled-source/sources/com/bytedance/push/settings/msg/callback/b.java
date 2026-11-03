package com.bytedance.push.settings.msg.callback;

import com.bytedance.common.push.c;
import org.json.JSONObject;
/* compiled from: MsgCallbackSettingsModel.java */
/* loaded from: classes2.dex */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11812a;

    /* renamed from: b  reason: collision with root package name */
    public boolean f11813b;

    /* renamed from: c  reason: collision with root package name */
    private final String f11814c = "callback_msg_arrive";

    /* renamed from: d  reason: collision with root package name */
    private final String f11815d = "callback_msg_click";

    public b() {
        a();
    }

    private void a() {
        this.f11812a = false;
        this.f11813b = false;
    }

    public b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f11812a = jSONObject.optBoolean("callback_msg_arrive", false);
            this.f11813b = jSONObject.optBoolean("callback_msg_click", false);
        } catch (Throwable th) {
            th.printStackTrace();
            a();
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "callback_msg_arrive", this.f11812a);
        add(jSONObject, "callback_msg_click", this.f11813b);
        return jSONObject.toString();
    }
}
