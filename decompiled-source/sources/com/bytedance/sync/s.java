package com.bytedance.sync;

import com.ss.android.common.lib.AppLogNewUtils;
import org.json.JSONObject;
/* compiled from: SyncEventCompat.java */
/* loaded from: classes3.dex */
public class s {
    public static void onEvent(String str, JSONObject jSONObject) {
        try {
            AppLogNewUtils.onEventV3(str, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
