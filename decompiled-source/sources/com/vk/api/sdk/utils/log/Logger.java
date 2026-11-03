package com.vk.api.sdk.utils.log;

import androidx.core.app.NotificationCompat;
import kotlin.Lazy;
import kotlin.Metadata;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0011J(\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/vk/api/sdk/utils/log/Logger;", "", "logLevel", "Lkotlin/Lazy;", "Lcom/vk/api/sdk/utils/log/Logger$LogLevel;", "getLogLevel", "()Lkotlin/Lazy;", "tag", "", "getTag", "()Ljava/lang/String;", "log", "", "level", "msg", NotificationCompat.CATEGORY_ERROR, "", "LogLevel", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public interface Logger {

    /* compiled from: Logger.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/vk/api/sdk/utils/log/Logger$LogLevel;", "", "(Ljava/lang/String;I)V", "VERBOSE", "DEBUG", "WARNING", "ERROR", "NONE", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public enum LogLevel {
        VERBOSE,
        DEBUG,
        WARNING,
        ERROR,
        NONE
    }

    Lazy<LogLevel> getLogLevel();

    String getTag();

    void log(LogLevel logLevel, String str, Throwable th);

    /* compiled from: Logger.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void log$default(Logger logger, LogLevel logLevel, String str, Throwable th, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
            }
            if ((i2 & 2) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                th = null;
            }
            logger.log(logLevel, str, th);
        }
    }
}
