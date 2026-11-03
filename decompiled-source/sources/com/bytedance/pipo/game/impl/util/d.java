package com.bytedance.pipo.game.impl.util;

import android.text.TextUtils;
import com.bytedance.pipo.game.n;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: GooglePayLog.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10536a = "payment_log_event";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10537b = "payment_callback_event";

    public static void a(String str, String str2) {
        e("info", str2);
    }

    public static void a(String str, String str2, Object... objArr) {
        a(str, String.format(str2, objArr));
    }

    public static void b(String str, String str2) {
        e("warning", str2);
    }

    public static void b(String str, String str2, Object... objArr) {
        b(str, String.format(str2, objArr));
    }

    public static void c(String str, String str2) {
        e("error", str2);
    }

    public static void c(String str, String str2, Object... objArr) {
        c(str, String.format(str2, objArr));
    }

    private static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("log_type", str);
            jSONObject.put("message", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.bytedance.pipo.game.impl.event.a.a().a(f10536a, str2);
    }

    public static void d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("call_back_type", str);
            jSONObject.put("message", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        n.a(f10537b, null, null, jSONObject);
    }
}
