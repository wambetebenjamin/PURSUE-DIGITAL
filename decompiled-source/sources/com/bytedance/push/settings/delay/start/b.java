package com.bytedance.push.settings.delay.start;

import com.bytedance.common.push.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: DelayStartChildProcessSettingsModel.java */
/* loaded from: classes2.dex */
public class b extends c {

    /* renamed from: a  reason: collision with root package name */
    public int f11763a;

    /* renamed from: b  reason: collision with root package name */
    public long f11764b;

    /* renamed from: c  reason: collision with root package name */
    public List<Integer> f11765c;

    /* renamed from: d  reason: collision with root package name */
    private final String f11766d = "delay_start_child_process_mode";

    /* renamed from: e  reason: collision with root package name */
    private final String f11767e = "delay_start_child_process_timeout_in_mill";

    /* renamed from: f  reason: collision with root package name */
    private final String f11768f = "need_disable_channel";

    public b() {
        a();
    }

    private void a() {
        this.f11763a = 0;
        this.f11764b = 300000L;
        this.f11765c = new ArrayList();
    }

    public b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f11763a = jSONObject.optInt("delay_start_child_process_mode", 0);
            this.f11764b = jSONObject.optLong("delay_start_child_process_timeout_in_mill", 300000L);
            JSONArray optJSONArray = jSONObject.optJSONArray("need_disable_channel");
            this.f11765c = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.f11765c.add((Integer) optJSONArray.get(i2));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            a();
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "delay_start_child_process_mode", this.f11763a);
        add(jSONObject, "delay_start_child_process_timeout_in_mill", this.f11764b);
        JSONArray jSONArray = new JSONArray();
        for (Integer num : this.f11765c) {
            jSONArray.put(num);
        }
        add(jSONObject, "need_disable_channel", jSONArray);
        return jSONObject.toString();
    }
}
