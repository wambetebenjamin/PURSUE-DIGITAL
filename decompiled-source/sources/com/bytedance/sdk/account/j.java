package com.bytedance.sdk.account;
/* compiled from: CommonNetConstants.java */
/* loaded from: classes2.dex */
public class j extends com.bytedance.sdk.account.api.f {
    public static final int API_HISTORY_MOBILE_CARD_LOGIN = 40002;
    public static final int API_VALIDATE_MOBILE_LOGIN = 40001;
    public static final String AUTH_TICKET_PATH = "/passport/auth/auth_get_ticket/";
    public static final String AVAILABLE_WAYS_PATH = "/passport/auth/available_ways/";
    public static final String BIND_LOGIN = "/passport/mobile/bind_login/";
    public static final String CANCEL_CLOSE_ACCOUNT = "/passport/cancel/login/";
    public static final String CANCEL_DO = "/passport/cancel/do/";
    public static final String CANCEL_INDEX = "/passport/cancel/index/";
    public static final String CANCEL_POST = "/passport/cancel/post/";
    public static final String CAN_DEVICE_ONE_LOGIN_PATH = "/passport/device/can_one_login/";
    public static final String CHECK_MOBILE_REGISTERED_PATH = "/passport/user/check_mobile_registered";
    public static final String CHECK_MOBILE_UNUSABLE_PATH = "/passport/mobile/check_unusable/";
    public static final String DEVICE_ONE_LOGIN_CONTINUE_PATH = "/passport/device/one_login_continue/";
    public static final String EMAIL_LOGIN_PATH_NEW = "/passport/email/login/";
    public static final String LOGIN_BY_TICKET_PATH = "/passport/auth/one_login_by_ticket/";
    public static final String MASK_MOBILE_ONE_LOGIN_PATH = "/passport/auth/mask_mobile_one_login/";
    public static final String PWD_CHECK_PATH = "/passport/password/check/";
    public static final String PWD_UPDATE_PATH = "/passport/password/update/";
    public static final String QUICK_AUTH_LOGIN_CONTINUE_PATH = "/passport/auth/one_login_continue/";
    public static final String QUICK_AUTH_LOGIN_ONLY_PATH = "/passport/auth/one_login_only/";
    public static final String QUICK_AUTH_LOGIN_PATH = "/passport/auth/one_login/";
    public static final String SET_PASS_PATH = "/passport/password/set/";
    public static final String USER_BIND_MOBILE_V1_PATH_NEW = "/passport/mobile/bind/v1/";
    public static final String USER_BIND_MOBILE_V2_PATH_NEW = "/passport/mobile/bind/v2/";
    public static final String USER_CHANGE_MOBILE_PATH_NEW = "/passport/mobile/change/v1/";
    public static final String USER_CHANGE_PASSWORD_PATH_NEW = "/passport/password/change/";
    public static final String USER_LOGIN_PATH = "/passport/mobile/login/";
    public static final String USER_ONE_BIND_MOBILE_V1_PATH = "/passport/auth/one_bind_mobile/";
    public static final String USER_ONE_BIND_MOBILE_V2_PATH = "/passport/auth/one_bind_mobile/v2/";
    public static final String USER_QUICK_LOGIN_CONTINUE_PAH = "/passport/mobile/sms_login_continue/";
    public static final String USER_QUICK_LOGIN_ONLY_PAH = "/passport/mobile/sms_login_only/";
    public static final String USER_QUICK_LOGIN_PAH = "/passport/mobile/sms_login/";
    public static final String USER_REGISTER_PATH = "/passport/mobile/register/";
    public static final String USER_RESET_PASSWORD_PATH_NEW = "/passport/password/reset/";
    public static final String USER_SEND_CODE_PATH = "/passport/mobile/send_code/v1/";
    public static final String USER_SEN_VOICO_CODE_PATH = "/passport/mobile/send_voice_code/";
    public static final String USER_UNBIND_MOBILE_PATH_NEW = "/passport/mobile/unbind/";
    public static final String VALIDATE_SMS_CODE_PATH = "/passport/mobile/validate_code/v1/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12524b = "/passport/password/has_set_by_mobile/";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12525c = "/passport/device/one_login/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12526d = "/passport/mobile/chain_login/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12527e = "/passport/mobile/can_chain_login/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12528f = "/passport/auth/validate_mobile_login/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12529g = "/passport/mobile/mobile_card_login/";

    /* renamed from: h  reason: collision with root package name */
    private static final String f12530h = "/passport/shark/safe_verify/";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12531i = "/passport/user/login_by_verify_ticket/";

    /* compiled from: CommonNetConstants.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String HISTORY_MOBILE_CARD_LOGIN = "passport_mobile_mobile_card_login";
        public static final String VALIDATE_MOBILE_LOGIN = "passport_auth_validate_mobile_login";
    }

    public static String getCancelPost() {
        return getUrl(CANCEL_POST);
    }

    public static String getCancelCLoseAccountPath() {
        return getUrl(CANCEL_CLOSE_ACCOUNT);
    }

    public static String getCancelDo() {
        return getUrl(CANCEL_DO);
    }

    public static String getCancelIndex() {
        return getUrl(CANCEL_INDEX);
    }

    public static String getCanDeviceOneLoginPath() {
        return getUrl(CAN_DEVICE_ONE_LOGIN_PATH);
    }

    public static String getPwdCheckPath() {
        return getUrl(PWD_CHECK_PATH);
    }

    public static String getAuthTicketPath() {
        return getUrl(AUTH_TICKET_PATH);
    }

    public static String getLoginByTicketPath() {
        return getUrl(LOGIN_BY_TICKET_PATH);
    }

    public static String getQuickAuthLoginPath() {
        return getUrl(QUICK_AUTH_LOGIN_PATH);
    }

    public static String getQuickAuthLoginOnlyPath() {
        return getUrl(QUICK_AUTH_LOGIN_ONLY_PATH);
    }

    public static String getQuickAuthLoginContinuePath() {
        return getUrl(QUICK_AUTH_LOGIN_CONTINUE_PATH);
    }

    public static String getMaskMobileOneLoginPath() {
        return getUrl(MASK_MOBILE_ONE_LOGIN_PATH);
    }

    public static String getUpdatePwdPath() {
        return getUrl(PWD_UPDATE_PATH);
    }

    public static String getCheckMobileUnusablePath() {
        return getUrl(CHECK_MOBILE_UNUSABLE_PATH);
    }

    public static String getDeviceOneLoginContinuePath() {
        return getUrl(DEVICE_ONE_LOGIN_CONTINUE_PATH);
    }

    public static String getPasswordHasSetByMobilePath() {
        return getUrl(f12524b);
    }

    public static String getUserBindMobileV2() {
        return getUrl(USER_BIND_MOBILE_V2_PATH_NEW);
    }

    public static String getUserBindMobileV1() {
        return getUrl(USER_BIND_MOBILE_V1_PATH_NEW);
    }

    public static String getUserChangeMobile() {
        return getUrl(USER_CHANGE_MOBILE_PATH_NEW);
    }

    public static String getOneBindMobileV1() {
        return getUrl(USER_ONE_BIND_MOBILE_V1_PATH);
    }

    public static String getOneBindMobileV2() {
        return getUrl(USER_ONE_BIND_MOBILE_V2_PATH);
    }

    public static String getBindLogin() {
        return getUrl(BIND_LOGIN);
    }

    public static String getUserChangePassword() {
        return getUrl(USER_CHANGE_PASSWORD_PATH_NEW);
    }

    public static String getCheckMobileRegisteredPath() {
        return getUrl(CHECK_MOBILE_REGISTERED_PATH);
    }

    public static String getUserLogin() {
        return getUrl(USER_LOGIN_PATH);
    }

    public static String getEmailLogin() {
        return getUrl(EMAIL_LOGIN_PATH_NEW);
    }

    public static String getUserSendCode() {
        return getUrl(USER_SEND_CODE_PATH);
    }

    public static String getUserRegister() {
        return getUrl(USER_REGISTER_PATH);
    }

    public static String getUserQuickLogin() {
        return getUrl(USER_QUICK_LOGIN_PAH);
    }

    public static String getUserQuickOnlyLogin() {
        return getUrl(USER_QUICK_LOGIN_ONLY_PAH);
    }

    public static String getUserQuickContinueLogin() {
        return getUrl(USER_QUICK_LOGIN_CONTINUE_PAH);
    }

    public static String getUserResetPassword() {
        return getUrl(USER_RESET_PASSWORD_PATH_NEW);
    }

    public static String getUserUnbindMobile() {
        return getUrl(USER_UNBIND_MOBILE_PATH_NEW);
    }

    public static String getSetPass() {
        return getUrl(SET_PASS_PATH);
    }

    public static String getValidateCodePath() {
        return getUrl(VALIDATE_SMS_CODE_PATH);
    }

    public static String getVoiceCodePath() {
        return getUrl(USER_SEN_VOICO_CODE_PATH);
    }

    public static String getDeviceOneLoginPath() {
        return getUrl(f12525c);
    }

    public static String getChainLoginPath() {
        return getUrl(f12526d);
    }

    public static String getCheckChainLoginPath() {
        return getUrl(f12527e);
    }

    public static String getValidateMobileLoginUrl() {
        return getUrl(f12528f);
    }

    public static String getHistoryMobileCardLoginUrl() {
        return getUrl(f12529g);
    }

    public static String getSharkSafeVerifyPath() {
        return getUrl(f12530h);
    }

    public static String getAvailableWaysPath() {
        return getUrl(AVAILABLE_WAYS_PATH);
    }

    public static String b() {
        return getUrl(f12531i);
    }
}
