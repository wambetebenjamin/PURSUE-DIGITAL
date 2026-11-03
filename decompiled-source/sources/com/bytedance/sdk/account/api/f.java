package com.bytedance.sdk.account.api;
/* compiled from: BDAccountNetApi.java */
/* loaded from: classes2.dex */
public class f {
    public static final String CHECK_CODE = "/passport/mobile/check_code/";
    public static final String CHECK_USER_NAME = "/2/user/check_name/";
    public static final String EMAIL_CHECK_CODE = "/passport/email/check_code/";
    public static final String EMAIL_RESET_PASSWORD = "/passport/password/reset_by_email_ticket/";
    public static final String SHARE_LOGIN_PATH = "/passport/share_login/";
    public static final String TICKET_RESET_PASSOWRD = "/passport/password/reset_by_ticket/";
    public static final String USER_PWD_LOGIN_PATH = "/passport/user/login/";

    /* renamed from: a  reason: collision with root package name */
    protected static final String f12260a = "/passport/token/beat/v2/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12261b = "https://";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12262c = "/passport/account/info/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12263d = "/passport/account/info/v2/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12264e = "/passport/user/logout/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12265f = "/2/user/logout/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12266g = "/passport/device/login_info/";

    protected static String a() {
        return com.ss.android.account.f.getConfig().host();
    }

    public static String getUrl(String str) {
        return "https://" + a() + str;
    }

    public static String getAccountUserInfo() {
        return getUrl(f12262c);
    }

    public static String getNewAccountUserInfo() {
        return getUrl("/passport/account/info/v2/");
    }

    public static String getUserLogoutUrl() {
        return getUrl("/passport/user/logout/");
    }

    public static String getTokenBeatHost() {
        return "https://" + a();
    }

    private static String a(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String getCheckCode() {
        return getUrl(CHECK_CODE);
    }

    public static String getTicketResetPassowrd() {
        return getUrl(TICKET_RESET_PASSOWRD);
    }

    public static String getEmailCheckCode() {
        return getUrl(EMAIL_CHECK_CODE);
    }

    public static String getEmailTicketResetPassword() {
        return getUrl(EMAIL_RESET_PASSWORD);
    }

    public static String getCheckUserName() {
        return getUrl(CHECK_USER_NAME);
    }

    public static String getUserPasswordLogin() {
        return getUrl(USER_PWD_LOGIN_PATH);
    }

    public static String getDeviceLoginInfoPath() {
        return getUrl(f12266g);
    }
}
