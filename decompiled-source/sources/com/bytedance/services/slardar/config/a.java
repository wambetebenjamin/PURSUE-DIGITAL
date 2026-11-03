package com.bytedance.services.slardar.config;

import org.json.JSONObject;
/* compiled from: IConfigListener.java */
/* loaded from: classes3.dex */
public interface a {
    void onReady();

    void onRefresh(JSONObject jSONObject, boolean z);
}
