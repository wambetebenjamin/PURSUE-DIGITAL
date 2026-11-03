package com.bytedance.ttgame.core.applog;

import com.bytedance.ttgame.main.internal.applog.IAppLogService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.util.Map;
/* loaded from: classes3.dex */
public class ApplogService implements IAppLogService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.applog.IAppLogService
    public void putCommonParams(Map<String, String> map, boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.applog.IAppLogService", "com.bytedance.ttgame.core.applog.ApplogService", "putCommonParams", new String[]{"java.util.Map", "boolean"}, Constants.VOID);
        BaseAppLogContextHolder.getInstance().fetchTeaAgent().putCommonParams(map, z);
    }

    @Override // com.bytedance.ttgame.main.internal.applog.IAppLogService
    public void updateCustomHeadersEmulator(int i2, String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.applog.IAppLogService", "com.bytedance.ttgame.core.applog.ApplogService", "updateCustomHeadersEmulator", new String[]{Constants.INT, "java.lang.String"}, Constants.VOID);
        ApplogUtils.updateCustomHeadersEmulator(i2, str);
    }
}
