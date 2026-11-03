package com.bytedance.android.service.manager.push.interfaze;

import org.json.JSONObject;
/* loaded from: classes.dex */
public interface IMultiProcessEventSenderService {
    void onEventV3(String str, JSONObject jSONObject);

    void onEventV3(boolean z, String str, JSONObject jSONObject);
}
