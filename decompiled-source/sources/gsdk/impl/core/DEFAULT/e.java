package gsdk.impl.core.DEFAULT;

import com.bytedance.ttgame.sdk.module.utils.I18nUtils;
import kotlin.Metadata;
/* compiled from: MonitorDomain.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/main/internal/init/MonitorDomain;", "", "()V", "getConfigUrl", "", "gpmDomain", "", "getExceptionUrl", "getReportUrl", "getSgDomain", "getVaDomain", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final e f20039a = new e();

    private final String d(boolean z) {
        return z ? "https://gpm-mon-va.bytegsdk.com" : "https://gpm-mon-va.byteoversea.com";
    }

    private final String e(boolean z) {
        return z ? "https://gpm-mon-sg.bytegsdk.com" : "https://gpm-mon-sg.byteoversea.com";
    }

    private e() {
    }

    public final String a(boolean z) {
        if (I18nUtils.isAmerica()) {
            return d(z) + "/monitor/appmonitor/v2/settings";
        }
        return e(z) + "/monitor/appmonitor/v2/settings";
    }

    public final String b(boolean z) {
        if (I18nUtils.isAmerica()) {
            return d(z) + com.bytedance.framwork.core.sdkmonitor.h.COLLECT_PATH;
        }
        return e(z) + com.bytedance.framwork.core.sdkmonitor.h.COLLECT_PATH;
    }

    public final String c(boolean z) {
        if (I18nUtils.isAmerica()) {
            return d(z) + "/monitor/collect/c/exception";
        }
        return e(z) + "/monitor/collect/c/exception";
    }
}
