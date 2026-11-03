package com.bytedance.ttgame.module.main.bridge;

import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.main.module.log.api.ILogService;
import java.util.ArrayList;
/* loaded from: classes6.dex */
public class LogModule implements BaseModule {
    private String mTunnel;

    public LogModule(String str) {
        this.mTunnel = str;
    }

    @GBridgeMethod(callName = "requestGMLog")
    public void log(@GBridgeParam("level") int i2, @GBridgeParam("content") String str) {
        ILogService.LogLevel logLevel;
        SdkLog.v(BaseModule.TAG, "log");
        ILogService iLogService = (ILogService) ServiceManager.get().getService(ILogService.class);
        if (i2 == 1) {
            logLevel = ILogService.LogLevel.Error;
        } else if (i2 == 2) {
            logLevel = ILogService.LogLevel.Exception;
        } else if (i2 == 3) {
            logLevel = ILogService.LogLevel.Assert;
        } else if (i2 == 4) {
            logLevel = ILogService.LogLevel.ProcessInfo;
        } else {
            logLevel = ILogService.LogLevel.Warn;
        }
        iLogService.log(logLevel, str);
    }

    @GBridgeMethod(callName = "registerGMLocalCrashFile")
    public void registerLocalCrashFile(@GBridgeParam("path") String str) {
        SdkLog.v(BaseModule.TAG, "registerLocalCrashFile：" + str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        ((ILogService) ServiceManager.get().getService(ILogService.class)).setExternalLogFilePathList(arrayList);
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
