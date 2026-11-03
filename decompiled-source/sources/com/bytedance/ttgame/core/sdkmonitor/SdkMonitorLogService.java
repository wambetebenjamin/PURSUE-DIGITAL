package com.bytedance.ttgame.core.sdkmonitor;

import android.content.Context;
import com.bytedance.sdk.account.save.database.a;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.main.internal.sdkmonitor.IModuleLogger;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: SdkMonitorLogService.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0007J>\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016JH\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001c\u0010\u001e\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0016J0\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u001d2\b\u0010#\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0016JF\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J.\u0010&\u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0006H\u0016J\u0010\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\u0004H\u0016¨\u0006,"}, d2 = {"Lcom/bytedance/ttgame/core/sdkmonitor/SdkMonitorLogService;", "Lcom/bytedance/ttgame/main/internal/sdkmonitor/ISdkMonitorLogService;", "()V", "getLogTypeSwitch", "", "logType", "", "(Ljava/lang/String;)Ljava/lang/Boolean;", "initMonitor", "", "context", "Landroid/content/Context;", "did", "config", "Lcom/bytedance/ttgame/core/SdkConfig;", a.FIELD_EXT, "", "dataProvider", "Lcom/bytedance/ttgame/main/internal/sdkmonitor/ISdkMonitorLogService$DataProvider;", "monitorApiError", "duration", "", "sendTime", "sendUrl", "sendIp", "traceCode", "status", "", "extJson", "Lorg/json/JSONObject;", "monitorCommonLog", "logExtr", "monitorEvent", "serviceName", "category", "metric", "monitorSLA", "sendDuration", "monitorStatusAndDuration", "newModuleLogger", "Lcom/bytedance/ttgame/main/internal/sdkmonitor/IModuleLogger;", "moduleFilter", "setModuleLoggerDebug", "debug", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SdkMonitorLogService implements ISdkMonitorLogService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public /* synthetic */ void setModuleLoggerDebug(Boolean bool) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "setModuleLoggerDebug", new String[]{Constants.LANG_BOOLEAN}, Constants.VOID);
        setModuleLoggerDebug(bool.booleanValue());
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public IModuleLogger newModuleLogger(String moduleFilter) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "newModuleLogger", new String[]{"java.lang.String"}, "com.bytedance.ttgame.main.internal.sdkmonitor.IModuleLogger");
        Intrinsics.checkNotNullParameter(moduleFilter, "moduleFilter");
        return new ModuleLogger(moduleFilter);
    }

    public void setModuleLoggerDebug(boolean z) {
        ModuleLogger.Companion.setDebug(z);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void initMonitor(Context context, String did, SdkConfig config, Map<String, String> map, ISdkMonitorLogService.DataProvider dataProvider) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "initMonitor", new String[]{"android.content.Context", "java.lang.String", "com.bytedance.ttgame.core.SdkConfig", "java.util.Map", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService$DataProvider"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(did, "did");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(dataProvider, "dataProvider");
        SdkMonitorManager.INSTANCE.initMonitor(context, did, config, map, dataProvider);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorStatusAndDuration", new String[]{"java.lang.String", Constants.INT, "org.json.JSONObject", "org.json.JSONObject"}, Constants.VOID);
        SdkMonitorManager.INSTANCE.monitorStatusAndDuration(str, Integer.valueOf(i2), jSONObject, jSONObject2);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorCommonLog(String str, JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorCommonLog", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        SdkMonitorManager.INSTANCE.monitorCommonLog(str, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorEvent", new String[]{"java.lang.String", "org.json.JSONObject", "org.json.JSONObject", "org.json.JSONObject"}, Constants.VOID);
        SdkMonitorManager.INSTANCE.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public Boolean getLogTypeSwitch(String logType) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "getLogTypeSwitch", new String[]{"java.lang.String"}, Constants.LANG_BOOLEAN);
        Intrinsics.checkNotNullParameter(logType, "logType");
        return Boolean.valueOf(SdkMonitorManager.INSTANCE.getLogTypeSwitch(logType));
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorApiError(long j2, long j3, String str, String str2, String str3, int i2, JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorApiError", new String[]{"long", "long", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "org.json.JSONObject"}, Constants.VOID);
        SdkMonitorManager.INSTANCE.monitorApiError(j2, j3, str, str2, str3, i2, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService
    public void monitorSLA(long j2, long j3, String str, String str2, String str3, int i2, JSONObject extJson) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", "com.bytedance.ttgame.core.sdkmonitor.SdkMonitorLogService", "monitorSLA", new String[]{"long", "long", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "org.json.JSONObject"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(extJson, "extJson");
        SdkMonitorManager.INSTANCE.monitorSLA(j2, j3, str, str2, str3, i2, extJson);
    }
}
