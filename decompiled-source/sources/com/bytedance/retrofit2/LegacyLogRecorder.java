package com.bytedance.retrofit2;

import org.json.JSONObject;
@Deprecated
/* loaded from: classes2.dex */
public interface LegacyLogRecorder {
    @Deprecated
    void generateOldFormatLog(JSONObject jSONObject);

    @Deprecated
    void setAccountRetryCallbackDuration(long j2);

    @Deprecated
    void setBdTuringCallbackDuration(long j2);

    @Deprecated
    void setKernelDns(long j2);

    @Deprecated
    void setKernelPush(long j2);

    @Deprecated
    void setKernelReceiveBody(long j2);

    @Deprecated
    void setKernelSend(long j2);

    @Deprecated
    void setKernelTCPConnect(long j2);

    @Deprecated
    void setKernelTTFB(long j2);

    @Deprecated
    void setKernelTls(long j2);

    @Deprecated
    void setKernelTotal(long j2);

    @Deprecated
    void setNetworkKernelRawRequestLog(String str);

    @Deprecated
    void setRequestEnd(long j2);

    @Deprecated
    void setRequestEndAppResumeState(long j2);
}
