package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.module.cdkey.api.CDKErrorCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CDKeyInnerTools.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"convertCode", "Lcom/bytedance/ttgame/base/GSDKError;", "extraCode", "", "extraMsg", "", ErrorConstants.ADDITIONAL_INFO, "main_impl_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class j {
    public static final GSDKError a(int i2, String str, String additionalInfo) {
        int i3;
        Intrinsics.checkNotNullParameter(additionalInfo, "additionalInfo");
        if (i2 == -1) {
            i3 = CDKErrorCode.CDKEY_NETWORK_ERROR;
        } else if (i2 == 1) {
            i3 = CDKErrorCode.CDKEY_PARAMS_ERROR;
        } else if (i2 == 2) {
            i3 = CDKErrorCode.CDKEY_SERVER_ERROR;
        } else if (i2 == 3) {
            i3 = CDKErrorCode.CDKEY_VALIDATE_ERROR;
        } else if (i2 == 4) {
            i3 = CDKErrorCode.CDKEY_USE_ERROR;
        } else if (i2 != 5) {
            switch (i2) {
                case 100:
                    i3 = CDKErrorCode.CDKEY_IS_USERD_ERROR;
                    break;
                case 101:
                    i3 = CDKErrorCode.CDKEY_IS_SAME_BATCH_ERROR;
                    break;
                case 102:
                    i3 = CDKErrorCode.CDKEY_NOT_STARTER_ERROR;
                    break;
                case 103:
                    i3 = CDKErrorCode.CDKEY_ALREADY_ENDED_ERROR;
                    break;
                case 104:
                    i3 = CDKErrorCode.CDKEY_NOT_EXIST_ERROR;
                    break;
                case 105:
                    i3 = CDKErrorCode.CDKEY_SHARK_ERROR;
                    break;
                case 106:
                    i3 = CDKErrorCode.CDKEY_NOT_ISSUED_ERROR;
                    break;
                case 107:
                    i3 = CDKErrorCode.CDKEY_USE_CONDITION_FAIL_ERROR;
                    break;
                default:
                    i3 = CDKErrorCode.CDKEY_UNKNOWN_ERROR;
                    break;
            }
        } else {
            i3 = CDKErrorCode.CDKEY_TEMPORARY_ERROR;
        }
        return new GSDKError(i3, str == null ? "failed" : str, i2, str == null ? "failed" : str, additionalInfo);
    }
}
