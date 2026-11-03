package com.bytedance.ttgame.module.gpm.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.Map;
/* loaded from: classes6.dex */
public interface IGPMService extends IModuleApi {
    String getCPUModel();

    String getGLExtensions();

    String getGPUModel();

    int getRAMSize();

    void logMemoryUsage();

    void logSceneEnd(String str, String str2);

    void logSceneEnd(String str, String str2, boolean z);

    void logSceneInfo(String str, String str2, String str3, int i2);

    void logSceneInfo(String str, String str2, String str3, String str4);

    void logSceneStart(String str, String str2, Map<String, Object> map);

    void monitorCost(String str, String str2, long j2, Map<String, Object> map);

    void monitorCostEnd(String str, String str2, String str3, Map<String, Object> map);

    void monitorCostStage(String str, String str2, String str3);

    void monitorCostStart(String str, String str2, String str3, Map<String, Object> map);

    void monitorRateFailed(String str, String str2, Map<String, Object> map);

    void monitorRateSuccess(String str, String str2, Map<String, Object> map);

    void monitorReport(String str, String str2, Map<String, Object> map);
}
