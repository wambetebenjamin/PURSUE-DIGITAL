package com.bytedance.sdk.account;

import android.net.Uri;
/* compiled from: ThirdPartyNetConstants.java */
/* renamed from: com.bytedance.sdk.account.r  reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public class C0392r extends com.bytedance.sdk.account.api.f {
    public static final int API_AUTH_BIND_LOGIN = 70001;
    public static final String AUTH_PATH = "/passport/auth/authorize/";
    public static final String CAN_AWEME_QUICK_LOGIN_PATH = "/passport/can_aweme_quick_login/";
    public static String OAUTH_PROFILE_PATH = "/user/get/oauth_profile/";
    public static final String SSO_LOGIN_ONLY_PATH = "/passport/auth/login_only/";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12947b = "/passport/auth/wap_login/";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12948c = "/passport/auth/unbind/";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12949d = "/passport/auth/switch_bind/";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12950e = "/passport/auth/login/";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12951f = "/passport/auth/bind/";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12952g = "/passport/auth/register/";

    /* renamed from: h  reason: collision with root package name */
    private static final String f12953h = "/2/user/info/";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12954i = "/passport/auth/bind_with_mobile_login/";

    /* renamed from: j  reason: collision with root package name */
    private static final String f12955j = "/passport/auth/bind_with_mobile/";
    private static final String k = "/passport/auth/share_login/";
    private static final String l = "/passport/auth/check_mobile_login/";
    private static final String m = "/passport/auth/bind_login/";
    public static final int n = 70002;
    public static final int o = 70003;
    public static final int p = 70004;
    public static final int q = 70005;
    public static final int r = 70006;
    public static final int s = 70007;
    public static String t = "/passport/auth/change_bind/";
    public static String u = "/passport/auth/login_authorize/";
    public static String v = "/passport/auth/code_access_token/";
    public static String w = "/passport/open/token_info/";
    private static final String x = "/passport/auth/get_oauth_token/";
    private static final String y = "/passport/auth/update_authorize_info/";
    private static final String z = "/passport/auth/get_oauth_token/v2/";

    /* compiled from: ThirdPartyNetConstants.java */
    /* renamed from: com.bytedance.sdk.account.r$a */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String AUTH_BIND_LOGIN = "passport_auth_bind_login";

        /* renamed from: a  reason: collision with root package name */
        public static final String f12956a = "passport_auth_get_oauth_token";

        /* renamed from: b  reason: collision with root package name */
        public static final String f12957b = "update_authorize_info";

        /* renamed from: c  reason: collision with root package name */
        public static final String f12958c = "passport_auth_change_bind";

        /* renamed from: d  reason: collision with root package name */
        public static final String f12959d = "passport_auth_login_authorize";

        /* renamed from: e  reason: collision with root package name */
        public static final String f12960e = "passport_auth_code_access_token";

        /* renamed from: f  reason: collision with root package name */
        public static final String f12961f = "passport_open_token_info";
    }

    public static String getLoginUrl() {
        return getUrl(f12947b);
    }

    public static String getUnbindUrl() {
        return getUrl(f12948c);
    }

    public static String getSsoSwitchBind() {
        return getUrl(f12949d);
    }

    public static String getSsoCallbackUrl() {
        return getUrl(f12950e);
    }

    public static String getSsoOnlyLoginUrl() {
        return getUrl(SSO_LOGIN_ONLY_PATH);
    }

    public static String getSsoAuthRegister() {
        return getUrl(f12952g);
    }

    public static String getSsoCallbackBind() {
        return getUrl(f12951f);
    }

    public static String getUserinfoUrl() {
        return getUrl(f12953h);
    }

    public static String getAuthBindWithMobileLogin() {
        return getUrl(f12954i);
    }

    public static String getBindWithMobilePath() {
        return getUrl(f12955j);
    }

    public static String getShareLoginPath() {
        return getUrl(k);
    }

    public static String getAuthCheckBindLoginPath() {
        return getUrl(l);
    }

    public static String b() {
        return getUrl(w);
    }

    public static String getLoginUrl(String str) {
        StringBuilder sb = new StringBuilder(getLoginUrl());
        sb.append("?platform=").append(Uri.encode(str));
        return sb.toString();
    }

    public static String getOauthProfilePath() {
        return getUrl(OAUTH_PROFILE_PATH);
    }

    public static String getCanAwemeQuickLoginPath() {
        return getUrl(CAN_AWEME_QUICK_LOGIN_PATH);
    }

    public static String getMergeAuthPath() {
        return getUrl(AUTH_PATH);
    }

    public static String getAuthBindLoginUrl() {
        return getUrl(m);
    }

    public static String c() {
        return getUrl(x);
    }

    public static String h() {
        return getUrl(z);
    }

    public static String d() {
        return getUrl(y);
    }

    public static String e() {
        return getUrl(t);
    }

    public static String f() {
        return getUrl(u);
    }

    public static String g() {
        return getUrl(v);
    }
}
