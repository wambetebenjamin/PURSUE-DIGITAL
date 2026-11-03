package com.bytedance.push.settings.notification;

import org.json.JSONObject;
/* compiled from: NotificationSettings.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11849a;

    /* renamed from: b  reason: collision with root package name */
    public int f11850b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f() {
        this.f11849a = false;
        this.f11850b = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f11849a = jSONObject.optBoolean("enable_harmony_os4_importance_category", false);
            this.f11850b = jSONObject.optInt("harmony_os4_importance_category_intercept_strategy", 0);
        } catch (Throwable unused) {
        }
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("enable_harmony_os4_importance_category", this.f11849a);
            jSONObject.put("harmony_os4_importance_category_intercept_strategy", this.f11850b);
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }
}
