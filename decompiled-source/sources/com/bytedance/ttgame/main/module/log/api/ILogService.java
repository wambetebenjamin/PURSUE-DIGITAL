package com.bytedance.ttgame.main.module.log.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ILogService.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0012J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH&J\u0016\u0010\u0010\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH&¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/main/module/log/api/ILogService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "addExternalLogFilePathFromDirList", "", "addExternalLogFilePathFromPathList", "logFilePathList", "", "", "log", "", "level", "Lcom/bytedance/ttgame/main/module/log/api/ILogService$LogLevel;", "content", "setExternalLogFileDirPathList", "externalLogFileDirPathList", "", "setExternalLogFilePathList", "externalLogFilePathList", "LogLevel", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ILogService extends IModuleApi {

    /* compiled from: ILogService.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/main/module/log/api/ILogService$LogLevel;", "", "(Ljava/lang/String;I)V", "Warn", "Error", "Exception", "Assert", "ProcessInfo", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public enum LogLevel {
        Warn,
        Error,
        Exception,
        Assert,
        ProcessInfo
    }

    void addExternalLogFilePathFromDirList();

    void addExternalLogFilePathFromPathList(List<String> list);

    int log(LogLevel logLevel, String str);

    void setExternalLogFileDirPathList(List<String> list);

    void setExternalLogFilePathList(List<String> list);
}
