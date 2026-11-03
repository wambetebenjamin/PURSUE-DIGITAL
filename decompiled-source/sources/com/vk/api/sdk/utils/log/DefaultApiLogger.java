package com.vk.api.sdk.utils.log;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.vk.api.sdk.utils.log.Logger;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DefaultApiLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/vk/api/sdk/utils/log/DefaultApiLogger;", "Lcom/vk/api/sdk/utils/log/Logger;", "logLevel", "Lkotlin/Lazy;", "Lcom/vk/api/sdk/utils/log/Logger$LogLevel;", "tag", "", "(Lkotlin/Lazy;Ljava/lang/String;)V", "getLogLevel", "()Lkotlin/Lazy;", "setLogLevel", "(Lkotlin/Lazy;)V", "getTag", "()Ljava/lang/String;", "checkLevel", "", "messageLevel", "log", "", "level", "msg", NotificationCompat.CATEGORY_ERROR, "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class DefaultApiLogger implements Logger {
    private Lazy<? extends Logger.LogLevel> logLevel;
    private final String tag;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Logger.LogLevel.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Logger.LogLevel.NONE.ordinal()] = 1;
            iArr[Logger.LogLevel.VERBOSE.ordinal()] = 2;
            iArr[Logger.LogLevel.DEBUG.ordinal()] = 3;
            iArr[Logger.LogLevel.WARNING.ordinal()] = 4;
            iArr[Logger.LogLevel.ERROR.ordinal()] = 5;
        }
    }

    public DefaultApiLogger(Lazy<? extends Logger.LogLevel> logLevel, String tag) {
        Intrinsics.checkParameterIsNotNull(logLevel, "logLevel");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        this.logLevel = logLevel;
        this.tag = tag;
    }

    @Override // com.vk.api.sdk.utils.log.Logger
    public Lazy<Logger.LogLevel> getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(Lazy<? extends Logger.LogLevel> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.logLevel = lazy;
    }

    @Override // com.vk.api.sdk.utils.log.Logger
    public String getTag() {
        return this.tag;
    }

    @Override // com.vk.api.sdk.utils.log.Logger
    public void log(Logger.LogLevel level, String str, Throwable th) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        if (checkLevel(level)) {
            return;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
        if (i2 == 2) {
            Log.v(getTag(), str, th);
        } else if (i2 == 3) {
            Log.d(getTag(), str, th);
        } else if (i2 == 4) {
            Log.w(getTag(), str, th);
        } else if (i2 != 5) {
        } else {
            Log.e(getTag(), str, th);
        }
    }

    private final boolean checkLevel(Logger.LogLevel logLevel) {
        return getLogLevel().getValue().ordinal() > logLevel.ordinal();
    }
}
