package com.bytedance.ttgame.main.internal.realtime;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.List;
import org.json.JSONObject;
@InternalApi
/* loaded from: classes3.dex */
public interface IRealtimeEventService extends IModuleApi {
    public static final String EVENT_INIT_SUCCESS = "mgame_gsdk_initialization_success_flash";
    public static final String EVENT_LOGIN = "mgame_login_flash";
    public static final String EVENT_LOGIN_CALL = "mgame_login_call_flash";

    List<JSONObject> dumpPendingRealtimeEvents();

    void flushPendingAfterDidReady();

    void report(String str, JSONObject jSONObject);
}
