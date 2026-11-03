package com.bytedance.services.apm.api;

import android.content.Context;
import com.bytedance.news.common.service.manager.IService;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public interface IApmAgent extends IService {
    void monitorCommonLog(String str, JSONObject jSONObject);

    @Deprecated
    void monitorDuration(String str, JSONObject jSONObject, JSONObject jSONObject2);

    void monitorEvent(c cVar);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void monitorExceptionLog(String str, JSONObject jSONObject);

    @Deprecated
    void monitorLog(String str, JSONObject jSONObject);

    @Deprecated
    void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2);

    @Deprecated
    void monitorStatusRate(String str, int i2, JSONObject jSONObject);

    void reportLegacyMonitorLog(Context context, long j2, long j3, boolean z);
}
