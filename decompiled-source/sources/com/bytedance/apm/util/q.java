package com.bytedance.apm.util;

import android.text.TextUtils;
import org.json.JSONObject;
/* compiled from: MonitorVersionUtils.java */
/* loaded from: classes.dex */
public class q {
    public static JSONObject assembleVersionInfo(JSONObject jSONObject, com.bytedance.apm.entity.g gVar) {
        if (gVar == null) {
            return jSONObject;
        }
        if (!TextUtils.isEmpty(gVar.versionCode)) {
            jSONObject.put("version_code", gVar.versionCode);
        }
        if (!TextUtils.isEmpty(gVar.versionName)) {
            jSONObject.put("version_name", gVar.versionName);
        }
        if (!TextUtils.isEmpty(gVar.manifestVersionCode)) {
            jSONObject.put("manifest_version_code", gVar.manifestVersionCode);
        }
        if (!TextUtils.isEmpty(gVar.updateVersionCode)) {
            jSONObject.put("update_version_code", gVar.updateVersionCode);
        }
        if (!TextUtils.isEmpty(gVar.appVersion)) {
            jSONObject.put("app_version", gVar.appVersion);
        }
        return jSONObject;
    }
}
