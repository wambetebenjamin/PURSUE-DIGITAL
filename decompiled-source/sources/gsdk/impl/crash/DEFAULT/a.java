package gsdk.impl.crash.DEFAULT;

import com.bytedance.apm.constant.k;
import com.bytedance.crash.config.g;
import com.bytedance.ttgame.sdk.module.utils.I18nUtils;
import kotlin.Metadata;
/* compiled from: Constant.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/crash/Constant;", "", "()V", "getAlogUploadUrl", "", "gpmDomain", "", "getJavaCrashUploadUrl", "getLaunchCrashUploadUrl", "getNativeCrashUploadUrl", "getSgDomain", "getVaDomain", "crash_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f20046a = new a();

    private final String e(boolean z) {
        return z ? "https://gpm-mon-va.bytegsdk.com" : "https://gpm-mon-va.byteoversea.com";
    }

    private final String f(boolean z) {
        return z ? "https://gpm-mon-sg.bytegsdk.com" : "https://gpm-mon-sg.byteoversea.com";
    }

    private a() {
    }

    public final String a(boolean z) {
        if (I18nUtils.isAmerica()) {
            return e(z) + g.f6368h;
        }
        return f(z) + g.f6368h;
    }

    public final String b(boolean z) {
        if (I18nUtils.isAmerica()) {
            return e(z) + k.FILE_COLLECT_PATH;
        }
        return f(z) + k.FILE_COLLECT_PATH;
    }

    public final String c(boolean z) {
        if (I18nUtils.isAmerica()) {
            return e(z) + "/monitor/collect/c/exception";
        }
        return f(z) + "/monitor/collect/c/exception";
    }

    public final String d(boolean z) {
        if (I18nUtils.isAmerica()) {
            return e(z) + g.f6369i;
        }
        return f(z) + g.f6369i;
    }
}
