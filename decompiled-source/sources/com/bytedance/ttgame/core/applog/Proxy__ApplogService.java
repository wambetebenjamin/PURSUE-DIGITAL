package com.bytedance.ttgame.core.applog;

import com.bytedance.ttgame.main.internal.applog.IAppLogService;
import com.meituan.robust.Constants;
import java.util.Map;
/* loaded from: classes3.dex */
public class Proxy__ApplogService implements IAppLogService {
    private ApplogService proxy = new ApplogService();

    @Override // com.bytedance.ttgame.main.internal.applog.IAppLogService
    public void putCommonParams(Map map, boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.applog.IAppLogService", "com.bytedance.ttgame.core.applog.ApplogService", "putCommonParams", new String[]{"java.util.Map", "boolean"}, Constants.VOID);
        this.proxy.putCommonParams(map, z);
    }

    @Override // com.bytedance.ttgame.main.internal.applog.IAppLogService
    public void updateCustomHeadersEmulator(int i2, String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.applog.IAppLogService", "com.bytedance.ttgame.core.applog.ApplogService", "updateCustomHeadersEmulator", new String[]{Constants.INT, "java.lang.String"}, Constants.VOID);
        this.proxy.updateCustomHeadersEmulator(i2, str);
    }

    public IAppLogService getProxy() {
        return this.proxy;
    }
}
