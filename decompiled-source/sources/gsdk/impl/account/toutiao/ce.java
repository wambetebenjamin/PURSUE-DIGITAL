package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTAccountCodeResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"isSharkProcessingError", "", "Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeResponse;", "toTTAccountCodeResult", "Lcom/bytedance/ttgame/module/account/toutiao/account/api/TTAccountCodeResult;", "errCode", "", "account_impl_toutiao_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ce {
    public static final TTAccountCodeResult a(cd cdVar, int i2) {
        Intrinsics.checkNotNullParameter(cdVar, "<this>");
        GSDKError gSDKError = new GSDKError(i2, cdVar.message, cdVar.code, cdVar.message);
        cc a2 = cdVar.a();
        return new TTAccountCodeResult(gSDKError, (a2 == null || (r4 = a2.a()) == null) ? "" : "");
    }

    public static final boolean a(cd cdVar) {
        Intrinsics.checkNotNullParameter(cdVar, "<this>");
        return cdVar.code == -30001 || cdVar.code == -30002;
    }
}
