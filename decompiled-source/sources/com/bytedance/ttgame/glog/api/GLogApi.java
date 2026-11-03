package com.bytedance.ttgame.glog.api;
/* loaded from: classes3.dex */
public interface GLogApi {
    void activeUploadLog(long j2, long j3, String str, IGLogUploadCallback iGLogUploadCallback);

    void changeLevel(int i2);

    @Deprecated
    void cloudCollect(String str, long j2, long j3);

    void d(String str, String str2);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void e(String str, Throwable th);

    @Deprecated
    String getLogDirPath();

    void header(int i2, String str, String str2);

    void i(String str, String str2);

    void init(GLogConfig gLogConfig);

    void log(int i2, String str, String str2, Object obj);

    void v(String str, String str2);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);

    void w(String str, Throwable th);
}
