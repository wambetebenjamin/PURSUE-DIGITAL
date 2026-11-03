package com.bytedance.applog.priority;
/* loaded from: classes.dex */
public interface PriorityCallback {
    PriorityHttpResponse doHttpPost(String str, byte[] bArr);

    String getHeaderJson();

    boolean isMonitorEnabled();

    boolean isNetworkAvailable();

    boolean isStageEnabled();

    void monitorError(String str, String str2);

    void monitorIncreaseStats(String str, int i2);

    void monitorStage(String str, String str2, int i2, int i3, String str3, String str4, long j2);

    void printLog(int i2, String str);
}
