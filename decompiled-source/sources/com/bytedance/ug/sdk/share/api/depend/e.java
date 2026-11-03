package com.bytedance.ug.sdk.share.api.depend;

import android.content.Context;
import java.util.List;
import org.json.JSONObject;
/* compiled from: IShareEventConfig.java */
/* loaded from: classes8.dex */
public interface e {
    void onALogEvent(int i2, String str, String str2);

    void onAppLogEvent(String str, JSONObject jSONObject);

    void onSDKMonitorEvent(String str, com.bytedance.ug.sdk.share.api.entity.k kVar);

    void onSDKMonitorInit(Context context, String str, JSONObject jSONObject, List<String> list, List<String> list2);
}
