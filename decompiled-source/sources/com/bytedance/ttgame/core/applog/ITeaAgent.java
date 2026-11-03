package com.bytedance.ttgame.core.applog;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface ITeaAgent {
    String addCommonParams(String str, boolean z);

    String fetchAppLogOpenUdid();

    String getInstallId();

    String getOaid(Context context);

    String getPangoLinkChannel(Context context);

    void getSSIDs(Map<String, String> map);

    String getServerDeviceId();

    String getUserId();

    void onNetConfigUpdate(JSONObject jSONObject, boolean z);

    void putCommonParams(Map<String, String> map, boolean z);

    void tryWaitDeviceInit();
}
