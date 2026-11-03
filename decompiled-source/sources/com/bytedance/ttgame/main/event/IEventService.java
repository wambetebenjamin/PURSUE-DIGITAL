package com.bytedance.ttgame.main.event;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IEventService extends IModuleApi {
    public static final String TAG = "EventService";

    void onEvent(String str, JSONObject jSONObject);

    void onEvent(boolean z, String str, JSONObject jSONObject);

    void setEventClient(IEventClient iEventClient);
}
