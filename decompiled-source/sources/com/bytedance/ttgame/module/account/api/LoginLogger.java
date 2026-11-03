package com.bytedance.ttgame.module.account.api;

import com.bytedance.apm.util.i;
import com.bytedance.common.process.cross.a;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.log.GLogPathData;
import com.bytedance.ttgame.main.internal.log.IGLogService;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import java.util.Arrays;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONObject;
/* compiled from: LoginLogger.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J1\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J$\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J$\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J1\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0013J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J$\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J1\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0013J\u0018\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0007J1\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0013J\u0018\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J$\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001d"}, d2 = {"Lcom/bytedance/ttgame/module/account/api/LoginLogger;", "", "()V", "gLogService", "Lcom/bytedance/ttgame/main/internal/log/IGLogService;", "getGLogService", "()Lcom/bytedance/ttgame/main/internal/log/IGLogService;", "gLogService$delegate", "Lkotlin/Lazy;", "d", "", "tag", "", "msg", "pathData", "Lcom/bytedance/ttgame/main/internal/log/GLogPathData;", "format", a.COL_ARGS, "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V", e.f7575a, "t", "", i.TAG, "monitorLog", "logType", "message", BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginLogger {
    public static final LoginLogger INSTANCE = new LoginLogger();
    private static final Lazy gLogService$delegate = LazyKt.lazy(new Function0<IGLogService>() { // from class: com.bytedance.ttgame.module.account.api.LoginLogger$gLogService$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IGLogService invoke() {
            return (IGLogService) ModuleManager.getService$default(ModuleManager.INSTANCE, IGLogService.class, false, (String) null, 6, (Object) null);
        }
    });

    private LoginLogger() {
    }

    public final IGLogService getGLogService() {
        return (IGLogService) gLogService$delegate.getValue();
    }

    public static /* synthetic */ void v$default(String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            gLogPathData = null;
        }
        v(str, str2, gLogPathData);
    }

    @JvmStatic
    public static final void v(String tag, String msg, GLogPathData gLogPathData) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("VERBOSE", tag, msg);
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.v(tag, msg, gLogPathData);
        }
    }

    @JvmStatic
    public static final void v(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        v(tag, msg, (GLogPathData) null);
    }

    @JvmStatic
    public static final void v(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        v(tag, format2);
    }

    @JvmStatic
    public static final void d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        d(tag, msg, (GLogPathData) null);
    }

    public static /* synthetic */ void d$default(String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            gLogPathData = null;
        }
        d(str, str2, gLogPathData);
    }

    @JvmStatic
    public static final void d(String tag, String msg, GLogPathData gLogPathData) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("DEBUG", tag, msg);
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.d(tag, msg, gLogPathData);
        }
    }

    @JvmStatic
    public static final void d(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        d(tag, format2);
    }

    public static /* synthetic */ void i$default(String str, String str2, GLogPathData gLogPathData, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            gLogPathData = null;
        }
        i(str, str2, gLogPathData);
    }

    @JvmStatic
    public static final void i(String tag, String msg, GLogPathData gLogPathData) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("INFO", tag, msg);
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.i(tag, msg, null);
        }
    }

    @JvmStatic
    public static final void i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        i(tag, msg, (GLogPathData) null);
    }

    @JvmStatic
    public static final void i(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        i(tag, format2);
    }

    public static /* synthetic */ void w$default(String str, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            gLogPathData = null;
        }
        w(str, th, gLogPathData);
    }

    @JvmStatic
    public static final void w(String tag, Throwable t, GLogPathData gLogPathData) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(t, "t");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("WARN", tag, ExceptionsKt.stackTraceToString(t));
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.w(tag, t, gLogPathData);
        }
    }

    @JvmStatic
    public static final void w(String tag, Throwable t) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(t, "t");
        w(tag, t, (GLogPathData) null);
    }

    @JvmStatic
    public static final void w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("WARN", tag, msg);
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.w(tag, msg);
        }
    }

    @JvmStatic
    public static final void w(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        w(tag, format2);
    }

    public static /* synthetic */ void e$default(String str, Throwable th, GLogPathData gLogPathData, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            gLogPathData = null;
        }
        e(str, th, gLogPathData);
    }

    @JvmStatic
    public static final void e(String tag, Throwable t, GLogPathData gLogPathData) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(t, "t");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("ERROR", tag, ExceptionsKt.stackTraceToString(t));
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.e(tag, t, gLogPathData);
        }
    }

    @JvmStatic
    public static final void e(String tag, Throwable t) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(t, "t");
        e(tag, t, (GLogPathData) null);
    }

    @JvmStatic
    public static final void e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        LoginLogger loginLogger = INSTANCE;
        loginLogger.monitorLog("ERROR", tag, msg);
        IGLogService gLogService = loginLogger.getGLogService();
        if (gLogService != null) {
            gLogService.e(tag, msg);
        }
    }

    @JvmStatic
    public static final void e(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        e(tag, format2);
    }

    private final void monitorLog(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("logType", str);
        jSONObject.putOpt("module", Constant.TAG);
        jSONObject.putOpt("tag", str2);
        jSONObject.putOpt("message", str3);
        ICoreInternalService iCoreInternalService = (ICoreInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, ICoreInternalService.class, false, (String) null, 6, (Object) null);
        if (iCoreInternalService != null) {
            iCoreInternalService.monitorCommonLog("game_debug_monitor", jSONObject);
        }
        ISdkMonitorLogService iSdkMonitorLogService = (ISdkMonitorLogService) ModuleManager.getService$default(ModuleManager.INSTANCE, ISdkMonitorLogService.class, false, (String) null, 6, (Object) null);
        if (iSdkMonitorLogService != null) {
            iSdkMonitorLogService.monitorCommonLog("game_debug_monitor", jSONObject);
        }
    }
}
