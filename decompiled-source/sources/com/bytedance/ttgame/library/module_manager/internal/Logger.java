package com.bytedance.ttgame.library.module_manager.internal;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/Logger;", "", "()V", "TAG", "", "level", "Lcom/bytedance/ttgame/library/module_manager/internal/Logger$LEVEL;", "getLevel", "()Lcom/bytedance/ttgame/library/module_manager/internal/Logger$LEVEL;", "setLevel", "(Lcom/bytedance/ttgame/library/module_manager/internal/Logger$LEVEL;)V", "logError", "", "msg", "logInfo", "logSuccess", "logVerbose", "logWarning", "LEVEL", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Logger {
    private static final String TAG = "ModuleManager";
    public static final Logger INSTANCE = new Logger();
    private static LEVEL level = LEVEL.IMPORTANT;

    /* compiled from: Logger.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/Logger$LEVEL;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "VERBOSE", "INFO", "IMPORTANT", "NONE", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public enum LEVEL {
        VERBOSE(0),
        INFO(1),
        IMPORTANT(2),
        NONE(Integer.MAX_VALUE);
        
        private final int value;

        LEVEL(int i2) {
            this.value = i2;
        }

        public final int getValue() {
            return this.value;
        }
    }

    private Logger() {
    }

    public final LEVEL getLevel() {
        return level;
    }

    public final void setLevel(LEVEL level2) {
        Intrinsics.checkNotNullParameter(level2, "<set-?>");
        level = level2;
    }

    public final void logVerbose(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (level.getValue() <= LEVEL.VERBOSE.getValue()) {
            Log.v(TAG, msg);
        }
    }

    public final void logInfo(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (level.getValue() <= LEVEL.INFO.getValue()) {
            Log.i(TAG, msg);
        }
    }

    public final void logSuccess(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (level.getValue() <= LEVEL.IMPORTANT.getValue()) {
            Log.d(TAG, msg);
        }
    }

    public final void logWarning(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (level.getValue() <= LEVEL.IMPORTANT.getValue()) {
            Log.w(TAG, msg);
        }
    }

    public final void logError(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (level.getValue() <= LEVEL.IMPORTANT.getValue()) {
            Log.e(TAG, msg);
        }
    }
}
