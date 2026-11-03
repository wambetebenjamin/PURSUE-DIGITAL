package gsdk.impl.compliance.protocol;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.framework.module.util.FlavorUtilKt;
import com.bytedance.ttgame.framework.module.util.SpUtil;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IGameSdkConfigService;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.module.account.toutiao.account.utils.LoginErrorCode;
import com.bytedance.ttgame.sdk.module.ui.NBDialogManagerKt;
import java.util.Arrays;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: ProtocolUtils.kt */
/* loaded from: classes10.dex */
public final class k {

    /* renamed from: a  reason: collision with root package name */
    public static final k f19988a = new k();

    private k() {
    }

    public final void a() {
        a.f19946a.a(LoginErrorCode.GSDK_PANEL_CLOSED, 19, "", 4);
        NBDialogManagerKt.clearAllDialog();
    }

    public final void b() {
        a.f19946a.a(LoginErrorCode.GSDK_PANEL_CLOSED, 19, "", 4);
    }

    public final String c() {
        String sdkVersion;
        if (FlavorUtilKt.isI18nFlavor()) {
            IModuleApi service$default = ModuleManager.getService$default(ModuleManager.INSTANCE, IGameSdkConfigService.class, false, (String) null, 6, (Object) null);
            Intrinsics.checkNotNull(service$default);
            sdkVersion = ((IGameSdkConfigService) service$default).getGlobalSdkVersion();
        } else {
            IModuleApi service$default2 = ModuleManager.getService$default(ModuleManager.INSTANCE, IGameSdkConfigService.class, false, (String) null, 6, (Object) null);
            Intrinsics.checkNotNull(service$default2);
            sdkVersion = ((IGameSdkConfigService) service$default2).getSdkVersion();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = new Object[2];
        IMainInternalService iMainInternalService = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        objArr[0] = iMainInternalService != null ? iMainInternalService.getDeviceId() : null;
        objArr[1] = sdkVersion;
        String format = String.format("did:%s,sdkVersion:%s", Arrays.copyOf(objArr, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @JvmStatic
    public static final String d() {
        IModuleApi service$default = ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        Intrinsics.checkNotNull(service$default);
        String sharedPreferences = SpUtil.getSharedPreferences("policy_client_code", ((IMainInternalService) service$default).getAppContext(), "");
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(\"po…s.java)!!.appContext, \"\")");
        return sharedPreferences;
    }
}
