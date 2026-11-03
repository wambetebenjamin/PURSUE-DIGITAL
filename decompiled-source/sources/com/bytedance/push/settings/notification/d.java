package com.bytedance.push.settings.notification;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: NotificationMonitorSettingsConverter.java */
/* loaded from: classes2.dex */
public class d extends com.bytedance.common.push.c implements com.bytedance.push.settings.c<e>, com.bytedance.push.settings.d<e> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11829a = "enable_monitor_notification_show";

    /* renamed from: b  reason: collision with root package name */
    private final String f11830b = "need_intercept_group";

    /* renamed from: c  reason: collision with root package name */
    private final String f11831c = "need_intercept_top";

    /* renamed from: d  reason: collision with root package name */
    private final String f11832d = "need_intercept_stick";

    /* renamed from: e  reason: collision with root package name */
    private final String f11833e = "need_intercept_cancel_error";

    /* renamed from: f  reason: collision with root package name */
    private final String f11834f = "need_intercept_pending_intent_error";

    /* renamed from: g  reason: collision with root package name */
    private final String f11835g = "intercept_stack";

    /* renamed from: h  reason: collision with root package name */
    private final String f11836h = "need_intercept_style_list";

    /* renamed from: i  reason: collision with root package name */
    private final String f11837i = "black_field_list";

    /* renamed from: j  reason: collision with root package name */
    private final String f11838j = "white_group_list";

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public e create() {
        return new e();
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public e to(String str) {
        e create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11839a = jSONObject.optBoolean("enable_monitor_notification_show");
            create.f11840b = jSONObject.optBoolean("need_intercept_group");
            create.f11842d = jSONObject.optBoolean("need_intercept_top");
            create.f11843e = jSONObject.optBoolean("need_intercept_stick");
            create.f11844f = jSONObject.optBoolean("need_intercept_cancel_error");
            create.f11845g = jSONObject.optBoolean("need_intercept_pending_intent_error");
            JSONArray optJSONArray = jSONObject.optJSONArray("need_intercept_style_list");
            if (optJSONArray != null) {
                create.f11847i = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    create.f11847i.add(optJSONArray.getString(i2));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("black_field_list");
            create.f11848j = new ArrayList();
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    create.f11848j.add(optJSONArray2.getString(i3));
                }
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("white_group_list");
            create.f11841c = new ArrayList();
            if (optJSONArray3 != null) {
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    create.f11841c.add(optJSONArray3.getString(i4));
                }
            }
            JSONArray optJSONArray4 = jSONObject.optJSONArray("intercept_stack");
            if (optJSONArray4 != null) {
                create.f11846h = new ArrayList();
                for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                    JSONArray jSONArray = (JSONArray) optJSONArray4.get(i5);
                    if (jSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        for (int i6 = 0; i6 < jSONArray.length(); i6++) {
                            arrayList.add((String) jSONArray.get(i6));
                        }
                        create.f11846h.add(arrayList);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(e eVar) {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "enable_monitor_notification_show", eVar.f11839a);
        add(jSONObject, "need_intercept_group", eVar.f11840b);
        add(jSONObject, "need_intercept_top", eVar.f11842d);
        add(jSONObject, "need_intercept_stick", eVar.f11843e);
        add(jSONObject, "need_intercept_cancel_error", eVar.f11844f);
        add(jSONObject, "need_intercept_pending_intent_error", eVar.f11845g);
        if (eVar.f11847i != null) {
            JSONArray jSONArray = new JSONArray();
            for (Object obj : eVar.f11847i) {
                jSONArray.put(obj);
            }
            add(jSONObject, "need_intercept_style_list", jSONArray);
        }
        if (eVar.f11848j != null) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object obj2 : eVar.f11848j) {
                jSONArray2.put(obj2);
            }
            add(jSONObject, "black_field_list", jSONArray2);
        }
        if (eVar.f11841c != null) {
            JSONArray jSONArray3 = new JSONArray();
            for (Object obj3 : eVar.f11841c) {
                jSONArray3.put(obj3);
            }
            add(jSONObject, "white_group_list", jSONArray3);
        }
        if (eVar.f11846h != null) {
            JSONArray jSONArray4 = new JSONArray();
            for (List<String> list : eVar.f11846h) {
                JSONArray jSONArray5 = new JSONArray();
                for (String str : list) {
                    jSONArray5.put(str);
                }
                jSONArray4.put(jSONArray5);
            }
            add(jSONObject, "intercept_stack", jSONArray4);
        }
        return jSONObject.toString();
    }
}
