package com.bytedance.push.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TokenCache.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f11449a;
    public String alias;

    /* renamed from: b  reason: collision with root package name */
    private String f11450b;
    public String token;
    public int type;
    public long updateTime;

    public d(int i2, String str, String str2, String str3, long j2, String str4) {
        this.type = i2;
        this.token = str;
        this.f11449a = str2;
        this.f11450b = str3;
        this.updateTime = j2;
        this.alias = str4;
    }

    public boolean isSame(d dVar) {
        if (dVar == this) {
            return true;
        }
        return dVar.type == this.type && TextUtils.equals(this.f11450b, dVar.f11450b) && TextUtils.equals(this.token, dVar.token) && TextUtils.equals(this.f11449a, dVar.f11449a) && TextUtils.equals(this.alias, dVar.alias);
    }

    public JSONObject toJson() {
        if (this.type <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.type);
            jSONObject.put("token", this.token);
            jSONObject.put("did", this.f11449a);
            jSONObject.put("vc", this.f11450b);
            jSONObject.put("t", this.updateTime);
            if (!TextUtils.isEmpty(this.alias)) {
                jSONObject.put(com.ss.android.pushmanager.e.KEY_ALIAS, this.alias);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static d parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        int optInt = jSONObject.optInt("type");
        String optString = jSONObject.optString("token");
        String optString2 = jSONObject.optString("did");
        String optString3 = jSONObject.optString("vc");
        long optLong = jSONObject.optLong("t");
        String optString4 = jSONObject.optString(com.ss.android.pushmanager.e.KEY_ALIAS, null);
        if (optInt <= 0) {
            return null;
        }
        return new d(optInt, optString, optString2, optString3, optLong, optString4);
    }

    public String toString() {
        return "{updateTime=" + this.updateTime + ", deviceId='" + this.f11449a + "', versionCode='" + this.f11450b + "', token='" + this.token + "', type=" + this.type + '}';
    }
}
