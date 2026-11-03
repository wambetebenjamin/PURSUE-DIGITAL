package com.bytedance.ttgame.main.internal.sdkmonitor;

import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.Map;
import org.json.JSONObject;
@InternalApi
/* loaded from: classes3.dex */
public interface ISdkMonitorLogService extends IModuleApi {

    /* loaded from: classes3.dex */
    public interface DataProvider {
        String provideSessionId();
    }

    Boolean getLogTypeSwitch(String str);

    void initMonitor(Context context, String str, SdkConfig sdkConfig, Map<String, String> map, DataProvider dataProvider);

    void monitorApiError(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject);

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void monitorSLA(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject);

    void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2);

    IModuleLogger newModuleLogger(String str);

    void setModuleLoggerDebug(Boolean bool);
}
