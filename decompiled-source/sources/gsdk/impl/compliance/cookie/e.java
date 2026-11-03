package gsdk.impl.compliance.cookie;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.compliance.api.cookie.CookieResult;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CookieInfo.kt */
/* loaded from: classes10.dex */
public final class e {
    public static final CookieResult a(d dVar, int i2) {
        Intrinsics.checkNotNullParameter(dVar, "<this>");
        GSDKError gSDKError = new GSDKError(i2, dVar.message, dVar.code, dVar.message);
        c a2 = dVar.a();
        return new CookieResult(gSDKError, (a2 != null ? a2.a() : 0) == 1);
    }
}
