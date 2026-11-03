package com.bytedance.ttgame.module.log.impl;

import com.bytedance.ttgame.main.module.log.api.ILogService;
import com.meituan.robust.Constants;
import java.util.List;
/* loaded from: classes6.dex */
public class Proxy__LogService implements ILogService {
    private LogService proxy = new LogService();

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void addExternalLogFilePathFromDirList() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "addExternalLogFilePathFromDirList", new String[0], Constants.VOID);
        this.proxy.addExternalLogFilePathFromDirList();
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void addExternalLogFilePathFromPathList(List list) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "addExternalLogFilePathFromPathList", new String[]{"java.util.List"}, Constants.VOID);
        this.proxy.addExternalLogFilePathFromPathList(list);
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public int log(ILogService.LogLevel logLevel, String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "log", new String[]{"com.bytedance.ttgame.main.module.log.api.ILogService$LogLevel", "java.lang.String"}, Constants.INT);
        return this.proxy.log(logLevel, str);
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void setExternalLogFileDirPathList(List list) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "setExternalLogFileDirPathList", new String[]{"java.util.List"}, Constants.VOID);
        this.proxy.setExternalLogFileDirPathList(list);
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void setExternalLogFilePathList(List list) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "setExternalLogFilePathList", new String[]{"java.util.List"}, Constants.VOID);
        this.proxy.setExternalLogFilePathList(list);
    }

    public ILogService getProxy() {
        return this.proxy;
    }
}
