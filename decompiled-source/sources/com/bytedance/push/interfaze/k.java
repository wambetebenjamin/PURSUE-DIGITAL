package com.bytedance.push.interfaze;

import android.content.Context;
import android.content.Intent;
import org.json.JSONObject;
/* compiled from: IPushMsgHandler.java */
/* loaded from: classes2.dex */
public interface k {
    com.bytedance.push.client.intelligence.h a(com.bytedance.push.l lVar);

    void a(Context context, long j2, String str, String str2, boolean z, JSONObject jSONObject, boolean z2);

    boolean a(int i2, com.bytedance.push.g gVar, boolean z, boolean z2, boolean z3, String str, long j2);

    void handlePassThroughMsg(String str, int i2, String str2);

    void handlePassThroughMsg(JSONObject jSONObject, int i2, String str);

    void handlePassThroughMsg(JSONObject jSONObject, int i2, String str, boolean z);

    void onClickMsg(Context context, String str, int i2);

    void trackClickPush(Context context, Intent intent, String str, JSONObject jSONObject);

    void trackClickPush(Context context, com.bytedance.push.g gVar, boolean z, JSONObject jSONObject);
}
