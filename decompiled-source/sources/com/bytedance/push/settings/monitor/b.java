package com.bytedance.push.settings.monitor;

import com.bytedance.common.push.c;
import org.json.JSONObject;
/* compiled from: PushMonitorSettingsModel.java */
/* loaded from: classes2.dex */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11802a;

    /* renamed from: b  reason: collision with root package name */
    public boolean f11803b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f11804c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f11805d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f11806e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f11807f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f11808g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f11809h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f11810i;

    /* renamed from: j  reason: collision with root package name */
    private final String f11811j = "enable_message_show_monitor";
    private final String k = "enable_badge_apply_monitor";
    private final String l = "enable_real_badge_set_monitor";
    private final String m = "enable_dau_monitor";
    private final String n = "enable_network_request_monitor";
    private final String o = "enable_content_show_monitor";
    private final String p = "enable_content_request_monitor";
    private final String q = "enable_keep_alive_from_monitor";
    private final String r = "enable_transmit_strategy_request_monitor";

    public b() {
        a();
    }

    private void a() {
        this.f11803b = false;
    }

    public b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f11802a = jSONObject.optBoolean("enable_message_show_monitor", false);
            this.f11803b = jSONObject.optBoolean("enable_badge_apply_monitor", false);
            this.f11804c = jSONObject.optBoolean("enable_real_badge_set_monitor", false);
            this.f11805d = jSONObject.optBoolean("enable_dau_monitor", false);
            this.f11806e = jSONObject.optBoolean("enable_network_request_monitor", false);
            this.f11807f = jSONObject.optBoolean("enable_content_show_monitor", false);
            this.f11808g = jSONObject.optBoolean("enable_content_request_monitor", false);
            this.f11809h = jSONObject.optBoolean("enable_keep_alive_from_monitor", false);
            this.f11810i = jSONObject.optBoolean("enable_transmit_strategy_request_monitor", false);
        } catch (Throwable th) {
            th.printStackTrace();
            a();
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "enable_message_show_monitor", this.f11802a);
        add(jSONObject, "enable_badge_apply_monitor", this.f11803b);
        add(jSONObject, "enable_real_badge_set_monitor", this.f11804c);
        add(jSONObject, "enable_dau_monitor", this.f11805d);
        add(jSONObject, "enable_network_request_monitor", this.f11806e);
        add(jSONObject, "enable_content_show_monitor", this.f11807f);
        add(jSONObject, "enable_content_request_monitor", this.f11808g);
        add(jSONObject, "enable_keep_alive_from_monitor", this.f11809h);
        add(jSONObject, "enable_transmit_strategy_request_monitor", this.f11810i);
        return jSONObject.toString();
    }
}
