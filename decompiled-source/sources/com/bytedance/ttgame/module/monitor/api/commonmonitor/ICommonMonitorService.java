package com.bytedance.ttgame.module.monitor.api.commonmonitor;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public interface ICommonMonitorService extends IModuleApi {

    /* loaded from: classes6.dex */
    public interface AlogUploadCallback {
        void flushAlogDataToFile();
    }

    /* loaded from: classes6.dex */
    public interface FileUploadCallback {
        void onFail(String str);

        void onSuccess();
    }

    /* loaded from: classes6.dex */
    public interface LibraryLoader {
        void loadLibrary(String str);
    }

    /* loaded from: classes6.dex */
    public interface UploadCallback {
        void onCallback(boolean z, int i2);
    }

    void activeUploadLog(long j2, long j3, String str, UploadCallback uploadCallback);

    void addHeaderInfo(String str, String str2);

    void apmMonitorApiError(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject);

    void apmMonitorCommonLog(String str, JSONObject jSONObject);

    void apmMonitorDirectOnTimer(String str, String str2, float f2);

    void apmMonitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void apmMonitorSLA(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject);

    void apmMonitorStatusRate(String str, int i2, JSONObject jSONObject);

    void cancelTrace();

    void endSpan(String str, String str2);

    void endTrace(String str, String str2, long j2);

    boolean getLogTypeSwitch(String str);

    void initApmMonitor(Context context, boolean z, LibraryLoader libraryLoader);

    boolean isMonitorReady();

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorDuration(String str, JSONObject jSONObject, JSONObject jSONObject2);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2);

    void reportThreadCount(String str);

    void setDebugMode();

    void startApmMonitor(Context context, int i2, String str, String str2, JSONObject jSONObject, boolean z, boolean z2, boolean z3, long j2, String str3, String str4, String str5);

    void startCollectCurrent(String str);

    void startSpan(String str, String str2);

    void startTrace();

    void stopCollectCurrent(String str);

    boolean uploadAlog(String str, AlogUploadCallback alogUploadCallback);

    void uploadFiles(boolean z, String str, String str2, String str3, List<String> list, String str4, JSONObject jSONObject, FileUploadCallback fileUploadCallback);
}
