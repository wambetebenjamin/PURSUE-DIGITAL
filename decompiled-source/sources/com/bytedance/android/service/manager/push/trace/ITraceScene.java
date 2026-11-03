package com.bytedance.android.service.manager.push.trace;

import org.json.JSONObject;
/* loaded from: classes.dex */
public interface ITraceScene {
    void enterNode(String str);

    void enterNode(String str, String str2);

    void enterNode(String str, String str2, JSONObject jSONObject);
}
