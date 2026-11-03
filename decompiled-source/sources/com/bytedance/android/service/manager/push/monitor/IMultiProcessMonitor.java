package com.bytedance.android.service.manager.push.monitor;

import android.content.Context;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface IMultiProcessMonitor {
    void init(Context context);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);
}
