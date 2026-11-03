package com.bytedance.ttgame.module.log.impl;

import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.main.internal.log.LogManager;
import com.bytedance.ttgame.main.module.log.api.ILogService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LogService.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0016\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0016\u0010\u0010\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\u0016¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/log/impl/LogService;", "Lcom/bytedance/ttgame/main/module/log/api/ILogService;", "()V", "addExternalLogFilePathFromDirList", "", "addExternalLogFilePathFromPathList", "logFilePathList", "", "", "getLevel", "Lcom/bytedance/ttgame/main/internal/IMainInternalService$LogLevel;", "level", "Lcom/bytedance/ttgame/main/module/log/api/ILogService$LogLevel;", "log", "", "content", "setExternalLogFileDirPathList", "externalLogFileDirPathList", "", "setExternalLogFilePathList", "externalLogFilePathList", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LogService implements ILogService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    /* compiled from: LogService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f14573a;

        static {
            int[] iArr = new int[ILogService.LogLevel.values().length];
            iArr[ILogService.LogLevel.Warn.ordinal()] = 1;
            iArr[ILogService.LogLevel.Error.ordinal()] = 2;
            iArr[ILogService.LogLevel.Exception.ordinal()] = 3;
            iArr[ILogService.LogLevel.Assert.ordinal()] = 4;
            iArr[ILogService.LogLevel.ProcessInfo.ordinal()] = 5;
            f14573a = iArr;
        }
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public int log(ILogService.LogLevel level, String content) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "log", new String[]{"com.bytedance.ttgame.main.module.log.api.ILogService$LogLevel", "java.lang.String"}, Constants.INT);
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(content, "content");
        return LogManager.INSTANCE.log(getLevel(level), "CP-" + level.name(), content);
    }

    private final IMainInternalService.LogLevel getLevel(ILogService.LogLevel logLevel) {
        int i2 = a.f14573a[logLevel.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return IMainInternalService.LogLevel.INFO;
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    return IMainInternalService.LogLevel.DEBUG;
                }
                return IMainInternalService.LogLevel.ERROR;
            }
            return IMainInternalService.LogLevel.ERROR;
        }
        return IMainInternalService.LogLevel.WARN;
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void setExternalLogFilePathList(List<String> externalLogFilePathList) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "setExternalLogFilePathList", new String[]{"java.util.List"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(externalLogFilePathList, "externalLogFilePathList");
        LogManager.INSTANCE.setExternalLogFilePathList(externalLogFilePathList);
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void setExternalLogFileDirPathList(List<String> externalLogFileDirPathList) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "setExternalLogFileDirPathList", new String[]{"java.util.List"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(externalLogFileDirPathList, "externalLogFileDirPathList");
        LogManager.INSTANCE.setExternalLogFileDirPathList(externalLogFileDirPathList);
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void addExternalLogFilePathFromDirList() {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "addExternalLogFilePathFromDirList", new String[0], Constants.VOID);
        LogManager.INSTANCE.addExternalLogFilePathFromDirList();
    }

    @Override // com.bytedance.ttgame.main.module.log.api.ILogService
    public void addExternalLogFilePathFromPathList(List<String> logFilePathList) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.main.module.log.api.ILogService", "com.bytedance.ttgame.module.log.impl.LogService", "addExternalLogFilePathFromPathList", new String[]{"java.util.List"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(logFilePathList, "logFilePathList");
        LogManager.INSTANCE.addExternalLogFilePathFromPathList(logFilePathList);
    }
}
