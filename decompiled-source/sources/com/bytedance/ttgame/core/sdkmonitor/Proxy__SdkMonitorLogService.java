package com.bytedance.ttgame.core.sdkmonitor;

import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.main.internal.sdkmonitor.IModuleLogger;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import com.meituan.robust.Constants;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class Proxy__SdkMonitorLogService implements ISdkMonitorLogService {
    private SdkMonitorLogService proxy = new SdkMonitorLogService();

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public Boolean getLogTypeSwitch(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "getLogTypeSwitch", new String[]{"java.lang.String"}, Constants.LANG_BOOLEAN);
        return this.proxy.getLogTypeSwitch(str);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void initMonitor(Context context, String str, SdkConfig sdkConfig, Map map, ISdkMonitorLogService.DataProvider dataProvider) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "initMonitor", new String[]{"android.content.Context", "java.lang.String", "com.bytedance.ttgame.core.SdkConfig", "java.util.Map", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService$DataProvider"}, Constants.VOID);
        this.proxy.initMonitor(context, str, sdkConfig, map, dataProvider);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorApiError(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorApiError", new String[]{"long", "long", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "org.json.JSONObject"}, Constants.VOID);
        this.proxy.monitorApiError(j2, j3, str, str2, str3, i2, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorCommonLog(String str, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorCommonLog", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.monitorCommonLog(str, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorEvent", new String[]{"java.lang.String", "org.json.JSONObject", "org.json.JSONObject", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorSLA(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorSLA", new String[]{"long", "long", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "org.json.JSONObject"}, Constants.VOID);
        this.proxy.monitorSLA(j2, j3, str, str2, str3, i2, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorStatusAndDuration", new String[]{"java.lang.String", Constants.INT, "org.json.JSONObject", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.monitorStatusAndDuration(str, i2, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public IModuleLogger newModuleLogger(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "newModuleLogger", new String[]{"java.lang.String"}, "com.bytedance.ttgame.main.internal.sdkmonitor.IModuleLogger");
        return this.proxy.newModuleLogger(str);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void setModuleLoggerDebug(Boolean bool) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "setModuleLoggerDebug", new String[]{Constants.LANG_BOOLEAN}, Constants.VOID);
        this.proxy.setModuleLoggerDebug(bool);
    }

    public ISdkMonitorLogService getProxy() {
        return this.proxy;
    }
}
