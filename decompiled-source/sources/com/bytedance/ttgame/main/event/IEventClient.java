package com.bytedance.ttgame.main.event;

import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IEventClient {
    void init();

    void sendEventData(String str, JSONObject jSONObject);
}
