package gsdk.impl.compliance.protocol;

import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.log.IGLogService;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import java.util.Arrays;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONObject;
/* compiled from: ProtocolLogger.kt */
/* loaded from: classes10.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final j f19985a = new j();

    /* renamed from: b  reason: collision with root package name */
    private static final IGLogService f19986b = (IGLogService) ModuleManager.getService$default(ModuleManager.INSTANCE, IGLogService.class, false, (String) null, 6, (Object) null);

    /* renamed from: c  reason: collision with root package name */
    private static final String f19987c = "gsdk_protocol";

    private j() {
    }

    @JvmStatic
    public static final void a(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        IGLogService iGLogService = f19986b;
        if (iGLogService != null) {
            iGLogService.d(f19987c, tag + msg);
        }
        f19985a.a("DEBUG", tag, msg);
    }

    @JvmStatic
    public static final void b(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        IGLogService iGLogService = f19986b;
        if (iGLogService != null) {
            iGLogService.e(f19987c, tag + msg);
        }
        f19985a.a("ERROR", tag, msg);
    }

    @JvmStatic
    public static final void a(String tag, String format, Object... args) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(args, "args");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(args, args.length);
        String format2 = String.format(format, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        b(tag, format2);
    }

    private final void a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("logType", str);
        jSONObject.putOpt("module", "gsdk_permission");
        jSONObject.putOpt("tag", str2);
        jSONObject.putOpt("message", str3);
        ISdkMonitorLogService iSdkMonitorLogService = (ISdkMonitorLogService) ModuleManager.getService$default(ModuleManager.INSTANCE, ISdkMonitorLogService.class, false, (String) null, 6, (Object) null);
        if (iSdkMonitorLogService != null) {
            iSdkMonitorLogService.monitorCommonLog("game_debug_monitor", jSONObject);
        }
    }
}
