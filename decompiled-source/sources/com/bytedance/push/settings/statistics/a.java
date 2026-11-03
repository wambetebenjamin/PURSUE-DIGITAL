package com.bytedance.push.settings.statistics;

import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.push.settings.c;
import com.bytedance.push.settings.d;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: StatisticsSettingsConverter.java */
/* loaded from: classes2.dex */
public class a implements c<b>, d<b> {
    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public b to(String str) {
        b create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11860a = jSONObject.optBoolean("enable_statistics");
            create.f11861b = jSONObject.optLong("fg_loop_interval", 300L);
            create.f11862c = jSONObject.optLong("bg_loop_interval", 5L);
            create.f11863d = jSONObject.optInt("network_detect_mode");
            create.f11864e = jSONObject.optInt("smp_network_detect_mode", create.f11863d);
            create.f11865f = jSONObject.optInt("max_sp_count", 15);
            create.f11866g = jSONObject.optLong("api_request_timeout_in_mill", WsConstants.EXIT_DELAY_TIME);
            create.f11867h = jSONObject.optLong("loop_gap_in_mill", 4000L);
            JSONArray optJSONArray = jSONObject.optJSONArray("enable_statistics_process");
            if (optJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add((String) optJSONArray.get(i2));
                }
                create.f11868i = arrayList;
            }
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
            jSONObject.put("enable_statistics", bVar.f11860a);
            jSONObject.put("fg_loop_interval", bVar.f11861b);
            jSONObject.put("bg_loop_interval", bVar.f11862c);
            jSONObject.put("network_detect_mode", bVar.f11863d);
            jSONObject.put("smp_network_detect_mode", bVar.f11864e);
            jSONObject.put("max_sp_count", bVar.f11865f);
            jSONObject.put("api_request_timeout_in_mill", bVar.f11866g);
            jSONObject.put("loop_gap_in_mill", bVar.f11867h);
            JSONArray jSONArray = new JSONArray();
            for (String str : bVar.f11868i) {
                jSONArray.put(str);
            }
            jSONObject.put("enable_statistics_process", jSONArray.toString());
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
