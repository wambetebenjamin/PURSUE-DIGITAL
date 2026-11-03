package com.bytedance.push.settings.client.intelligence;

import com.bytedance.android.service.manager.alliance.IAllianceService;
import com.bytedance.push.settings.d;
import org.json.JSONObject;
/* compiled from: ClientIntelligenceSettingsConverter.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.common.push.c implements com.bytedance.push.settings.c<b>, d<b> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11737a = "enable_feature_report";

    /* renamed from: b  reason: collision with root package name */
    private final String f11738b = "allow_collect_client_feature";

    /* renamed from: c  reason: collision with root package name */
    private final String f11739c = "enable_client_intelligence_push_show";

    /* renamed from: d  reason: collision with root package name */
    private final String f11740d = "feature_collect_time_out_in_mill";

    /* renamed from: e  reason: collision with root package name */
    private final String f11741e = "check_client_feature_interval_in_mill";

    /* renamed from: f  reason: collision with root package name */
    private final String f11742f = "max_show_delay_time_in_mill";

    /* renamed from: g  reason: collision with root package name */
    private final String f11743g = "min_message_show_interval_in_mill";

    /* renamed from: h  reason: collision with root package name */
    private final String f11744h = "max_number_of_message_show_at_the_same_time";

    /* renamed from: i  reason: collision with root package name */
    private final String f11745i = "client_intelligence_push_show_mode";
    private final String k = "client_intelligence_push_show_sub_mode";
    private final String l = "max_number_of_allow_cache";
    private final String m = "improve_push_arrival_rate";
    private final String n = "min_interval_from_launch_in_mill";

    /* renamed from: j  reason: collision with root package name */
    private final String f11746j = IAllianceService.CONTENT_TYPE_LOCAL_PUSH;

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public b to(String str) {
        b create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11749a = jSONObject.optBoolean("enable_feature_report");
            create.f11750b = jSONObject.optBoolean("allow_collect_client_feature");
            create.f11751c = jSONObject.optBoolean("enable_client_intelligence_push_show");
            create.f11752d = jSONObject.optLong("feature_collect_time_out_in_mill");
            create.f11753e = jSONObject.optLong("check_client_feature_interval_in_mill");
            create.f11754f = jSONObject.optLong("max_show_delay_time_in_mill");
            create.f11755g = jSONObject.optLong("min_message_show_interval_in_mill");
            create.q = jSONObject.optLong("min_interval_from_launch_in_mill");
            create.f11756h = jSONObject.optInt("max_number_of_message_show_at_the_same_time");
            create.l = jSONObject.optInt("client_intelligence_push_show_mode", 1);
            create.r = jSONObject.optInt("client_intelligence_push_show_sub_mode", 0);
            create.s = jSONObject.optInt("max_number_of_allow_cache");
            create.t = jSONObject.optBoolean("improve_push_arrival_rate");
            create.m = new c(jSONObject.optJSONObject(IAllianceService.CONTENT_TYPE_LOCAL_PUSH));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(b bVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("enable_feature_report", bVar.f11749a);
            jSONObject.put("allow_collect_client_feature", bVar.f11750b);
            jSONObject.put("enable_client_intelligence_push_show", bVar.f11751c);
            jSONObject.put("feature_collect_time_out_in_mill", bVar.f11752d);
            jSONObject.put("check_client_feature_interval_in_mill", bVar.f11753e);
            jSONObject.put("max_show_delay_time_in_mill", bVar.f11754f);
            jSONObject.put("min_message_show_interval_in_mill", bVar.f11755g);
            jSONObject.put("max_number_of_message_show_at_the_same_time", bVar.f11756h);
            jSONObject.put("client_intelligence_push_show_mode", bVar.l);
            jSONObject.put("client_intelligence_push_show_sub_mode", bVar.r);
            jSONObject.put("max_number_of_allow_cache", bVar.s);
            jSONObject.put("improve_push_arrival_rate", bVar.t);
            jSONObject.put("min_interval_from_launch_in_mill", bVar.q);
            jSONObject.put(IAllianceService.CONTENT_TYPE_LOCAL_PUSH, bVar.m.a());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public b create() {
        return new b();
    }
}
