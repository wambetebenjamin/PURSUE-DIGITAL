package com.bytedance.article.common.monitor.backfetch;

import android.content.Context;
import android.text.TextUtils;
import com.ttnet.org.chromium.base.TimeUtils;
import org.json.JSONObject;
/* compiled from: FetchUtils.java */
/* loaded from: classes.dex */
public class b {
    public static void activeUploadLegacyLog(Context context, long j2, long j3, boolean z) {
    }

    public static void fetchAllData() {
    }

    public static String getVersionName(JSONObject jSONObject) {
        if (jSONObject == null || TextUtils.isEmpty(jSONObject.optString("update_version_code"))) {
            return (jSONObject == null || TextUtils.isEmpty(jSONObject.optString("app_version"))) ? "" : jSONObject.optString("app_version");
        }
        return jSONObject.optString("update_version_code");
    }

    public static boolean checkFetchInterval(long j2, long j3) {
        return j2 < j3 && ((j3 - j2) / 1000) / TimeUtils.SECONDS_PER_HOUR > 1;
    }

    public static void activeUploadRecentLog(Context context, boolean z) {
        activeUploadLegacyLog(context, System.currentTimeMillis() - 21600000, System.currentTimeMillis(), z);
    }
}
