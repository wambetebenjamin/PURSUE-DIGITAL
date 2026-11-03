package com.bytedance.sdk.account;
/* compiled from: ExtraNetConstants.java */
/* loaded from: classes2.dex */
public class k extends com.bytedance.sdk.account.api.f {
    public static final int API_EMAIL_CHANGE_BIND = 30002;
    public static final int API_EMAIL_CHANGE_PASSWORD = 30003;
    public static final int API_LOGIN_GUIDE_STRATEGY = 30001;
    public static final String EMAIL_CHECK_REGISTER_PATH = "/passport/user/check_email_registered";
    public static final String EMAIL_REGISTER_PATH_NEW = "/passport/email/register/v2/";
    public static final String EMAIL_REGISTER_VERIFY_LOGIN_PATH = "/passport/email/register_verify_login/";
    public static final String EMAIL_REGISTER_VERIFY_PATH = "/passport/email/register_verify/";
    public static final String EMAIL_SEND_CODE_PATH = "/passport/email/send_code/";
    public static final String LOGOUT_OTHERS_PATH = "/passport/account/logout_others/";
    public static final String SWITCH_AUTH_PATH = "/passport/account/switch/";
    public static final String USER_PWD_REGISTER_PATH = "/passport/username/register/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12595b = "/passport/account/remove/";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12596c = "/passport/email/bind/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12597d = "/passport/app/email/register/code_verify/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12598e = "/passport/app/email/register/ticket_register/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12599f = "/passport/email/verify/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12600g = "/passport/general/login_guiding_strategy/";

    /* renamed from: h  reason: collision with root package name */
    private static final String f12601h = "/passport/email/change/";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12602i = "/passport/email/change_password/";

    /* renamed from: j  reason: collision with root package name */
    public static final int f12603j = 30004;
    private static final String k = "/user/check/sensitive_info/";

    /* compiled from: ExtraNetConstants.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String EMAIL_CHANGE_BIND = "passport_email_change";
        public static final String EMAIL_CHANGE_PASSWORD = "passport_email_change_password";
        public static final String LOGIN_GUIDE_STRATEGY = "passport_general_login_guiding_strategy";
    }

    public static String getSwitchAuthHost() {
        return getUrl(SWITCH_AUTH_PATH);
    }

    public static String getSwitchAuthPath() {
        return getUrl(SWITCH_AUTH_PATH);
    }

    public static String getEmailVerifyPath() {
        return getUrl(f12599f);
    }

    public static String getEmailRegisterCodeVerifyPath() {
        return getUrl(f12597d);
    }

    public static String getEmailTicketRegisterPath() {
        return getUrl(f12598e);
    }

    public static String getEmailBindPath() {
        return getUrl(f12596c);
    }

    public static String getAccountRemovePath() {
        return getUrl(f12595b);
    }

    public static String getEmailRegister() {
        return getUrl(EMAIL_REGISTER_PATH_NEW);
    }

    public static String getEmailSendCode() {
        return getUrl(EMAIL_SEND_CODE_PATH);
    }

    public static String getEmailRegisterVerifyPath() {
        return getUrl(EMAIL_REGISTER_VERIFY_PATH);
    }

    public static String getEmailRegisterVerifyLoginPath() {
        return getUrl(EMAIL_REGISTER_VERIFY_LOGIN_PATH);
    }

    public static String getEmailCheckRegisterPath() {
        return getUrl(EMAIL_CHECK_REGISTER_PATH);
    }

    public static String getUserPasswordRegister() {
        return getUrl(USER_PWD_REGISTER_PATH);
    }

    public static String getLogoutOthersPath() {
        return getUrl(LOGOUT_OTHERS_PATH);
    }

    public static String getLoginGuideStrategyUrl() {
        return getUrl(f12600g);
    }

    public static String getEmailChangeBindUrl() {
        return getUrl(f12601h);
    }

    public static String getEmailChangePasswordUrl() {
        return getUrl(f12602i);
    }

    public static String b() {
        return getUrl(k);
    }
}
