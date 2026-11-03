package com.monitor.cloudmessage.callback;

import org.json.JSONObject;
/* compiled from: IMonitorLogConsumer.java */
/* loaded from: classes9.dex */
public interface f {
    void handleLogData(long j2, long j3, String str, com.bytedance.services.apm.api.i iVar);

    void notifyLogDataConsumed(String str, String str2);

    String[] parseLogType(JSONObject jSONObject);
}
