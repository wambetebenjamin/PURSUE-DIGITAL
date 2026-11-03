package com.bytedance.ttgame.sdk.module.core.internal;

import com.bytedance.ttgame.channel.account.ChannelLoginErrorCode;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.module.account.toutiao.account.utils.LoginErrorCode;
import com.bytedance.ttgame.module.pay.api.PayResult;
/* loaded from: classes8.dex */
public class ServerErrorCodeMapping {
    public static final int AccountActiveAboutError = -101013;
    public static final int AccountActiveCodeRegisterLimitError = -101024;
    public static final int AccountBindCountLimitError = -101009;
    public static final int AccountChannelError = -104998;
    public static final int AccountCreateGuestLimitError = -101005;
    public static final int AccountDeviceOrAccountBanError = -101004;
    public static final int AccountGuestBindThirdInGuestLoginError = -101003;
    public static final int AccountHasBound = -101028;
    public static final int AccountIsBeingCancelError = -101022;
    public static final int AccountLinkAuthCodeExpiredError = -101030;
    public static final int AccountLinkAuthCommonError = -101029;
    public static final int AccountLinkAuthNotMatchError = -101031;
    public static final int AccountLinkDeveloperAccountError = -101034;
    public static final int AccountLinkNotSupportMultiPackage = -101033;
    public static final int AccountLinkReleaseNotHaveInfoError = -101032;
    public static final int AccountMultipleDeviceLoginReachedTheLimit = -101027;
    public static final int AccountNeedAuthLoginAgainError = -101035;
    public static final int AccountNotConfigParamError = -101011;
    public static final int AccountNotFoundThirdPlatfromError = -101012;
    public static final int AccountNotStartRegisterError = -101017;
    public static final int AccountNotSupportUnBindError = -101010;
    public static final int AccountParameterError = -101000;
    public static final int AccountPassportTokenError = -101025;
    public static final int AccountQuerySdkOpenIdNotUseUserIDError = -101018;
    public static final int AccountRealNameHasVerifiedError = -101019;
    public static final int AccountRegisterLimitError = -101007;
    public static final int AccountRishControlForPhoneError = -101015;
    public static final int AccountRishControllerPickError = -101020;
    public static final int AccountRishControllerSliderError = -101021;
    public static final int AccountRiskControlError = -101014;
    public static final int AccountServerError = -101016;
    public static final int AccountServerOther = -104999;
    public static final int AccountSuccess = 0;
    public static final int AccountThirdAleardyBindError = -101006;
    public static final int AccountThirdAlreadyBindNoUnBindError = -101008;
    public static final int AccountTokenError = -101001;
    public static final int AccountUserNoHaveGuestForResetError = -101023;
    public static final int AccountVisitorExistError = -101026;
    public static final int AccountWithUINoSupportBindError = -101002;

    public static int convertServerErrorCode(int i2) {
        switch (i2) {
            case -32099:
            case -5000:
                return AccountServerError;
            case -32051:
            case -4003:
                return AccountRishControlForPhoneError;
            case -32050:
            case -30103:
            case -4204:
            case -4010:
            case -4001:
                return AccountRiskControlError;
            case -32033:
            case -1503:
                return AccountIsBeingCancelError;
            case -32012:
                return AccountMultipleDeviceLoginReachedTheLimit;
            case -32007:
            case LoginErrorCode.CREATE_VISITOR_EXCEED_LIMIT /* -1013 */:
                return AccountCreateGuestLimitError;
            case LoginErrorCode.BSDK_SERVER_HAS_VISITOR /* -32006 */:
                return -101026;
            case -32005:
                return AccountHasBound;
            case -32004:
            case -30204:
                return AccountUserNoHaveGuestForResetError;
            case -32003:
            case -1004:
            case -1003:
                return AccountTokenError;
            case -32002:
            case -1002:
                return -101025;
            case -32001:
            case -1001:
                return AccountParameterError;
            case -31108:
                return AccountLinkDeveloperAccountError;
            case -31107:
                return AccountLinkNotSupportMultiPackage;
            case -31104:
                return AccountLinkReleaseNotHaveInfoError;
            case -31103:
                return AccountLinkAuthNotMatchError;
            case -31102:
                return AccountLinkAuthCodeExpiredError;
            case -31101:
                return AccountLinkAuthCommonError;
            case -30234:
            case -30106:
                return AccountNeedAuthLoginAgainError;
            case -30102:
                return AccountQuerySdkOpenIdNotUseUserIDError;
            case ErrorConstants.ERROR_USER_ID_NOT_EXIST /* -30101 */:
                return AccountNotStartRegisterError;
            case LoginErrorCode.SUCCESSION_CODE_SHARK_CLICK /* -30002 */:
                return AccountRishControllerSliderError;
            case LoginErrorCode.SUCCESSION_CODE_SHARK_SLIDER /* -30001 */:
                return AccountRishControllerPickError;
            case PayResult.IABHELPER_UNKNOWN_PURCHASE_RESPONSE /* -3006 */:
            case PayResult.IABHELPER_USER_CANCELLED /* -3005 */:
            case PayResult.IABHELPER_SEND_INTENT_FAILED /* -3004 */:
            case PayResult.IABHELPER_VERIFICATION_FAILED /* -3003 */:
            case PayResult.IABHELPER_BAD_RESPONSE /* -3002 */:
                return AccountActiveAboutError;
            case PayResult.IABHELPER_REMOTE_EXCEPTION /* -3001 */:
                return AccountActiveCodeRegisterLimitError;
            case -1399:
            case -1398:
            case LoginErrorCode.VERIFICATION_CODE_EXPIRED /* -1303 */:
            case LoginErrorCode.REQUEST_CODE_NOT_COOLED /* -1302 */:
            case LoginErrorCode.USER_TYPE_ERROR /* -1301 */:
                return AccountChannelError;
            case -1204:
                return AccountNotFoundThirdPlatfromError;
            case -1202:
                return AccountRealNameHasVerifiedError;
            case LoginErrorCode.NOT_SET_MULTI_VISITOR_MODE /* -1024 */:
                return AccountNotConfigParamError;
            case -1023:
                return AccountNotSupportUnBindError;
            case LoginErrorCode.BIND_LIMIT /* -1022 */:
                return AccountBindCountLimitError;
            case LoginErrorCode.BIND_CONFLICT_ACCOUNT_CREATED /* -1020 */:
                return AccountThirdAlreadyBindNoUnBindError;
            case ChannelLoginErrorCode.REGISTRATION_RESTRICTIONS /* -1016 */:
                return AccountRegisterLimitError;
            case LoginErrorCode.BIND_CONFLICT /* -1014 */:
                return -101006;
            case -1011:
                return AccountDeviceOrAccountBanError;
            case -1007:
                return AccountGuestBindThirdInGuestLoginError;
            case -1005:
                return AccountWithUINoSupportBindError;
            case 0:
                return 0;
            default:
                return -104999;
        }
    }
}
