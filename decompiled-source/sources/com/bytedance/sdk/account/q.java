package com.bytedance.sdk.account;
/* compiled from: SpecialNetConstants.java */
/* loaded from: classes2.dex */
public class q extends com.bytedance.sdk.account.api.f {
    public static final String AFTER_REGISTER_LOGIN_BY_TICKET_PATH = "/passport/mobile/login_by_ticket/";
    public static final String CHECK_TV_QRCONNECT = "/passport/related_login/check_qrconnect/";
    public static final String GENERATE_USER_INFO_TICKET_PATH = "/passport/mobile/generate_user_info_ticket/";
    public static final String GET_TV_QRCODE = "/passport/related_login/get_qrcode/";
    public static final String VERIFY_ACCOUNT_PASSWORD_PATH = "/passport/account/verify/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12938b = "/passport/account/authorize/";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12939c = "/passport/mobile/get_qrcode/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12940d = "/passport/mobile/check_qrconnect/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12941e = "/passport/mobile/scan_qrcode/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12942f = "/passport/mobile/confirm_qrcode/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12943g = "/passport/email/bind_email_for_device_login/";

    /* renamed from: h  reason: collision with root package name */
    private static final String f12944h = "/passport/user/bind_visitor_account/";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12945i = "/passport/user/check_visitor_upgraded/";

    /* renamed from: j  reason: collision with root package name */
    private static final String f12946j = "/passport/user/device_login/";
    private static final String k = "/passport/sso/scan_qrcode/";
    private static final String l = "/passport/sso/confirm_qrcode/";

    public static String getAccountAuthorizePath() {
        return getUrl(f12938b);
    }

    public static String getUserDeviceLogin() {
        return getUrl(f12946j);
    }

    public static String getBindVisitorAccount() {
        return getUrl(f12944h);
    }

    public static String getBindEmailForDeviceLoginPath() {
        return getUrl(f12943g);
    }

    public static String getCheckVisitorUpgradePath() {
        return getUrl(f12945i);
    }

    public static String getVerifyAccountPasswordPath() {
        return getUrl(VERIFY_ACCOUNT_PASSWORD_PATH);
    }

    public static String getGenerateUserInfoTicketPath() {
        return getUrl(GENERATE_USER_INFO_TICKET_PATH);
    }

    public static String getAfterRegisterLoginByTicketPath() {
        return getUrl(AFTER_REGISTER_LOGIN_BY_TICKET_PATH);
    }

    public static String getTVQrcodePath() {
        return getUrl(GET_TV_QRCODE);
    }

    public static String getTVCheckQrconnectPath() {
        return getUrl(CHECK_TV_QRCONNECT);
    }

    public static String getQRCodePath() {
        return getUrl(f12939c);
    }

    public static String getQRCodeStatusPath() {
        return getUrl(f12940d);
    }

    public static String getAuthorizeScanQRCodePath() {
        return getUrl(f12941e);
    }

    public static String getAuthorizeQRCodeToLoginPath() {
        return getUrl(f12942f);
    }

    public static String b() {
        return getUrl(k);
    }

    public static String c() {
        return getUrl(l);
    }
}
