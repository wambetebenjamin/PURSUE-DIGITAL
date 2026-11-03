package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: LocationMonitorUtil.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/bytedance/ttgame/module/loccom/util/LocationMonitorUtil;", "", "()V", "LOCATION_ERROR", "", "LOCATION_SUCCESS", "MONITOR_CATEGORY_NAME", "", "MONITOR_EXTRA_ERROR_CODE_KEY", "MONITOR_EXTRA_ERROR_MSG_KEY", "MONITOR_EXTRA_NAME", "MONITOR__EXTRA_DEVICE_ID_KEY", "PARAM_INVALID_ERROR", "monitorEvent", "", "serviceName", "status", "errorCode", "errorMsg", "(Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;)V", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ad {

    /* renamed from: a  reason: collision with root package name */
    public static final ad f20392a = new ad();

    /* renamed from: b  reason: collision with root package name */
    public static final int f20393b = -211007;

    /* renamed from: c  reason: collision with root package name */
    public static final int f20394c = 1;

    /* renamed from: d  reason: collision with root package name */
    public static final int f20395d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static final String f20396e = "status";

    /* renamed from: f  reason: collision with root package name */
    private static final String f20397f = "extra";

    /* renamed from: g  reason: collision with root package name */
    private static final String f20398g = "device_id";

    /* renamed from: h  reason: collision with root package name */
    private static final String f20399h = "error_code";

    /* renamed from: i  reason: collision with root package name */
    private static final String f20400i = "error_msg";

    private ad() {
    }

    public static /* synthetic */ void a(ad adVar, String str, int i2, Integer num, String str2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            str2 = null;
        }
        adVar.a(str, i2, num, str2);
    }

    public final void a(String serviceName, int i2, Integer num, String str) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        JSONObject put = new JSONObject().put("status", i2);
        IMainInternalService iMainInternalService = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        String deviceId = iMainInternalService != null ? iMainInternalService.getDeviceId() : null;
        JSONObject jSONObject = new JSONObject();
        if (i2 == 1) {
            jSONObject.put("device_id", deviceId);
            jSONObject.put("error_code", num);
            jSONObject.put("error_msg", str);
        }
        JSONObject put2 = new JSONObject().put("extra", jSONObject);
        IMainInternalService iMainInternalService2 = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        if (iMainInternalService2 != null) {
            iMainInternalService2.monitorEvent(serviceName, put, null, put2);
        }
        ISdkMonitorLogService iSdkMonitorLogService = (ISdkMonitorLogService) ModuleManager.getService$default(ModuleManager.INSTANCE, ISdkMonitorLogService.class, false, (String) null, 6, (Object) null);
        if (iSdkMonitorLogService != null) {
            iSdkMonitorLogService.monitorEvent(serviceName, put, null, put2);
        }
    }
}
