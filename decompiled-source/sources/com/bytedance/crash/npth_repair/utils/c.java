package com.bytedance.crash.npth_repair.utils;

import com.facebook.internal.security.CertificateUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: RepairPortrait.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private String[] f6963a;

    /* renamed from: b  reason: collision with root package name */
    private String[] f6964b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f6965c;

    public static c a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 2) {
            return null;
        }
        return new c(jSONArray);
    }

    public c(JSONArray jSONArray) {
        try {
            this.f6963a = jSONArray.optString(0).split(",");
            this.f6964b = jSONArray.optString(1).split(",");
            this.f6965c = new JSONObject();
            for (int i2 = 2; i2 < jSONArray.length(); i2++) {
                String optString = jSONArray.optString(i2);
                if (!"".equals(optString)) {
                    String[] split = optString.split(CertificateUtil.DELIMITER);
                    if (split.length == 2) {
                        this.f6965c.putOpt(split[0], split[1]);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static boolean a(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0 || "none".equals(strArr[0])) {
            return false;
        }
        if ("all".equals(strArr[0])) {
            return true;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(int i2) {
        return a(this.f6963a, String.valueOf(i2));
    }

    public boolean a(String str) {
        return a(this.f6964b, str);
    }

    public String b(String str) {
        return (this.f6965c == null || str == null || "".equals(str)) ? "" : this.f6965c.optString(str);
    }

    public JSONArray c(String str) {
        if (this.f6965c == null || str == null) {
            return null;
        }
        if (!"".equals(str)) {
            try {
            } catch (JSONException unused) {
                return null;
            }
        }
        return new JSONArray(this.f6965c.optString(str));
    }

    public boolean a(String str, String str2) {
        return b(str).equals(str2);
    }
}
