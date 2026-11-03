package com.bytedance.ttgame.main.internal.log;

import android.content.Context;
import com.bytedance.apm.util.i;
import com.bytedance.bdturing.methods.l;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IGLogService.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bf\u0018\u0000 %2\u00020\u0001:\u0001%J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0016J$\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0016J$\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J,\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J$\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\b\u0010\u0017\u001a\u00020\bH&J \u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH&J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0016J$\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001aH&J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0016J$\u0010#\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0016J$\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J \u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J,\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J$\u0010$\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&¨\u0006&"}, d2 = {"Lcom/bytedance/ttgame/main/internal/log/IGLogService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "activeUploadLog", "", "startTime", "", "endTime", "scene", "", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/main/internal/log/IGLogUploadCallback;", "clearPathInfo", RNConfig.MODULE_NAME, "currentStep", "cloudCollect", "d", "tag", "withPathInfo", "Lcom/bytedance/ttgame/main/internal/log/GLogPathData;", "msg", e.f7575a, "tr", "", "getLogDirPath", "header", "priority", "", i.TAG, "init", "context", "Landroid/content/Context;", "isDebug", "", "enableI18n", "logLevel", BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "Companion", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IGLogService extends IModuleApi {
    public static final int ALL = 2;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = 7;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    void activeUploadLog(long j2, long j3, String str, IGLogUploadCallback iGLogUploadCallback);

    void clearPathInfo(String str, String str2);

    void cloudCollect();

    void d(String str, GLogPathData gLogPathData);

    void d(String str, String str2);

    void d(String str, String str2, GLogPathData gLogPathData);

    void e(String str, GLogPathData gLogPathData);

    void e(String str, String str2);

    void e(String str, String str2, GLogPathData gLogPathData);

    void e(String str, String str2, Throwable th);

    void e(String str, String str2, Throwable th, GLogPathData gLogPathData);

    void e(String str, Throwable th);

    void e(String str, Throwable th, GLogPathData gLogPathData);

    String getLogDirPath();

    void header(int i2, String str, String str2);

    void i(String str, GLogPathData gLogPathData);

    void i(String str, String str2);

    void i(String str, String str2, GLogPathData gLogPathData);

    void init(Context context, boolean z, boolean z2, int i2);

    void v(String str, GLogPathData gLogPathData);

    void v(String str, String str2);

    void v(String str, String str2, GLogPathData gLogPathData);

    void w(String str, GLogPathData gLogPathData);

    void w(String str, String str2);

    void w(String str, String str2, GLogPathData gLogPathData);

    void w(String str, String str2, Throwable th);

    void w(String str, String str2, Throwable th, GLogPathData gLogPathData);

    void w(String str, Throwable th);

    void w(String str, Throwable th, GLogPathData gLogPathData);

    /* compiled from: IGLogService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/main/internal/log/IGLogService$Companion;", "", "()V", "ALL", "", "DEBUG", "ERROR", "INFO", "NONE", "VERBOSE", "WARN", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int ALL = 2;
        public static final int DEBUG = 3;
        public static final int ERROR = 6;
        public static final int INFO = 4;
        public static final int NONE = 7;
        public static final int VERBOSE = 2;
        public static final int WARN = 5;

        private Companion() {
        }
    }

    /* compiled from: IGLogService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void v$default(IGLogService iGLogService, String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: v");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.v(str, str2, gLogPathData);
        }

        public static void v(IGLogService iGLogService, String tag, String msg) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            iGLogService.v(tag, msg, null);
        }

        public static void v(IGLogService iGLogService, String tag, GLogPathData withPathInfo) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(withPathInfo, "withPathInfo");
            iGLogService.v(tag, "", withPathInfo);
        }

        public static /* synthetic */ void d$default(IGLogService iGLogService, String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: d");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.d(str, str2, gLogPathData);
        }

        public static void d(IGLogService iGLogService, String tag, String msg) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            iGLogService.d(tag, msg, null);
        }

        public static void d(IGLogService iGLogService, String tag, GLogPathData withPathInfo) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(withPathInfo, "withPathInfo");
            iGLogService.d(tag, "", withPathInfo);
        }

        public static /* synthetic */ void i$default(IGLogService iGLogService, String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: i");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.i(str, str2, gLogPathData);
        }

        public static void i(IGLogService iGLogService, String tag, String msg) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            iGLogService.i(tag, msg, null);
        }

        public static void i(IGLogService iGLogService, String tag, GLogPathData withPathInfo) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(withPathInfo, "withPathInfo");
            iGLogService.i(tag, "", withPathInfo);
        }

        public static /* synthetic */ void w$default(IGLogService iGLogService, String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: w");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.w(str, str2, gLogPathData);
        }

        public static void w(IGLogService iGLogService, String tag, String msg) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            iGLogService.w(tag, msg, (GLogPathData) null);
        }

        public static void w(IGLogService iGLogService, String tag, GLogPathData withPathInfo) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(withPathInfo, "withPathInfo");
            iGLogService.w(tag, "", withPathInfo);
        }

        public static /* synthetic */ void w$default(IGLogService iGLogService, String str, String str2, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: w");
            }
            if ((i2 & 8) != 0) {
                gLogPathData = null;
            }
            iGLogService.w(str, str2, th, gLogPathData);
        }

        public static void w(IGLogService iGLogService, String tag, String msg, Throwable tr) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            Intrinsics.checkNotNullParameter(tr, "tr");
            iGLogService.w(tag, msg, tr, null);
        }

        public static /* synthetic */ void w$default(IGLogService iGLogService, String str, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: w");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.w(str, th, gLogPathData);
        }

        public static void w(IGLogService iGLogService, String tag, Throwable tr) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(tr, "tr");
            iGLogService.w(tag, tr, (GLogPathData) null);
        }

        public static /* synthetic */ void e$default(IGLogService iGLogService, String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: e");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.e(str, str2, gLogPathData);
        }

        public static void e(IGLogService iGLogService, String tag, String msg) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            iGLogService.e(tag, msg, (GLogPathData) null);
        }

        public static void e(IGLogService iGLogService, String tag, GLogPathData withPathInfo) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(withPathInfo, "withPathInfo");
            iGLogService.e(tag, "", withPathInfo);
        }

        public static /* synthetic */ void e$default(IGLogService iGLogService, String str, String str2, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: e");
            }
            if ((i2 & 8) != 0) {
                gLogPathData = null;
            }
            iGLogService.e(str, str2, th, gLogPathData);
        }

        public static void e(IGLogService iGLogService, String tag, String msg, Throwable tr) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(msg, "msg");
            Intrinsics.checkNotNullParameter(tr, "tr");
            iGLogService.e(tag, msg, tr, null);
        }

        public static /* synthetic */ void e$default(IGLogService iGLogService, String str, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: e");
            }
            if ((i2 & 4) != 0) {
                gLogPathData = null;
            }
            iGLogService.e(str, th, gLogPathData);
        }

        public static void e(IGLogService iGLogService, String tag, Throwable tr) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(tr, "tr");
            iGLogService.e(tag, tr, (GLogPathData) null);
        }
    }
}
