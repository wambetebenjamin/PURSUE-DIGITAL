package com.bytedance.sdk.account.platform.api;
/* compiled from: IOnekeyLoginService.java */
/* loaded from: classes2.dex */
public interface l extends d {

    /* compiled from: IOnekeyLoginService.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String CM_AUTH_TYPE = "authType";
        public static final String CM_AUTH_TYPE_DES = "authTypeDes";
        public static final String CT_REFRESH_TOKEN = "refreshToken";
        public static final String NET_STATUS = "net_status";
        public static final String NET_TYPE = "net_type";
        public static final String OPEN_ID = "openId";
        public static final String RAW_RESULT = "raw_result";
        public static final String SECURITY_PHONE = "security_phone";
        public static final String TOKEN_EXPIRES_IN = "expires_in";

        /* renamed from: a  reason: collision with root package name */
        public static final String f12766a = "scripExpiresIn";
    }

    void a(String str, boolean z, com.bytedance.sdk.account.platform.base.b bVar);

    void cancel();

    void getAuthToken(com.bytedance.sdk.account.platform.base.b bVar);

    String getCacheMaskPhone();

    String getCarrier();

    int getNetworkStatus();

    void getPhoneInfo(com.bytedance.sdk.account.platform.base.b bVar);

    void getPhoneInfo(String str, com.bytedance.sdk.account.platform.base.b bVar);

    void getValidateToken(com.bytedance.sdk.account.platform.base.b bVar);
}
