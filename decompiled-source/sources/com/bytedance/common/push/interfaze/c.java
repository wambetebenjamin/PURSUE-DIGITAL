package com.bytedance.common.push.interfaze;

import android.content.Context;
import org.json.JSONObject;
/* compiled from: IPushCommonEventSender.java */
/* loaded from: classes2.dex */
public interface c {
    void onEvent(Context context, String str, String str2, String str3, long j2, long j3, JSONObject jSONObject);

    void onEventV3(String str, JSONObject jSONObject);
}
