package com.bytedance.sdk.account.platform.base;
/* compiled from: OnekeyLoginConstants.java */
/* loaded from: classes2.dex */
public interface i {
    public static final String CM_KEY_AUTH_TYPE = "authType";
    public static final String CM_KEY_AUTH_TYPE_DES = "authTypeDes";
    public static final String CM_KEY_OPEN_ID = "openId";
    public static final String CM_KEY_PRE_PHONE_SCRIP = "securityphone";
    public static final String CM_KEY_TOKEN = "token";
    public static final String CM_RESULT_CODE = "resultCode";
    public static final String CM_RESULT_DESC = "resultDesc";
    public static final String CM_RESULT_SUCCESS = "103000";
    public static final String CT_KEY_ACCESS_CODE = "accessCode";
    public static final String CT_KEY_ACCESS_TOKEN = "accessToken";
    public static final String CT_KEY_DATA = "data";
    public static final String CT_KEY_MSG = "msg";
    public static final String CT_KEY_OPEN_ID = "openId";
    public static final String CT_KEY_PHONE = "number";
    public static final String CT_KEY_PRE_PHONE = "desenPhone";
    public static final String CT_KEY_REFRESH_TOKEN = "refreshToken";
    public static final String CT_KEY_RESULT = "result";
    public static final String CT_KEY_RSP_DATA = "responseData";
    public static final int CT_RESULT_SUCCESS = 0;
    public static final String CU_KEY_ACCESS_CODE = "accessCode";
    public static final String CU_KEY_ACCESS_TOKEN = "access_token";
    public static final String CU_KEY_OPEN_ID = "open_id";
    public static final String CU_KEY_PHONE = "mobile";
    public static final String CU_KEY_RESULT_CODE = "resultCode";
    public static final String CU_KEY_RESULT_DATA = "resultData";
    public static final String CU_KEY_RESULT_MSG = "resultMsg";
    public static final String CU_KEY_TOKEN_EXPIRES = "expires_in";
    public static final String CU_RESULT_SUCCESS = "0";
    public static final String MOBILE = "mobile";
    public static final String MOBILE_TYPE = "1";
    public static final int MSG_FAILURE = 1012;
    public static final int MSG_SUCCESS = 1011;
    public static final int NETWORK_TYPE_MOBILE = 1;
    public static final String NETWORK_TYPE_MOBILE_STR = "cellular";
    public static final int NETWORK_TYPE_NONE = 0;
    public static final String NETWORK_TYPE_NONE_STR = "no_network";
    public static final int NETWORK_TYPE_UNKNOWN = -1;
    public static final String NETWORK_TYPE_UNKNOWN_STR = "error";
    public static final int NETWORK_TYPE_WIFI = 2;
    public static final int NETWORK_TYPE_WIFI_AND_MOBILE = 3;
    public static final String NETWORK_TYPE_WIFI_AND_MOBILE_STR = "cellular&wifi";
    public static final String NETWORK_TYPE_WIFI_STR = "wifi";
    public static final String NET_TYPE = "netType";
    public static final String OPERATOR_TYPE = "operatortype";
    public static final String OTHERS = "others";
    public static final String TELECOM = "telecom";
    public static final String TELECOM_TYPE = "3";
    public static final String UNICOM = "unicom";
    public static final String UNICOM_TYPE = "2";

    /* renamed from: a  reason: collision with root package name */
    public static final String f12796a = "scripExpiresIn";

    /* renamed from: b  reason: collision with root package name */
    public static final String f12797b = "tokenExpiresIn";

    /* renamed from: c  reason: collision with root package name */
    public static final String f12798c = "telecom_v2";

    /* compiled from: OnekeyLoginConstants.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final String ERROR_CARRIER_DATA_MOBILE_DISABLED = "-6";
        public static final String ERROR_CARRIER_DISABLED = "-5";
        public static final String ERROR_CODE_NOT_SUPPORT = "-2";
        public static final String ERROR_CODE_UNKNOW = "-1";
        public static final String ERROR_CU_REQUEST_TIMEOUT = "-8";
        public static final String ERROR_NO_PHONE_STATE_PERMISSON = "-7";
        public static final String ERROR_SDK_INIT = "-3";
        public static final String ERROR_WEAK_NETWORK = "-8";
    }

    /* compiled from: OnekeyLoginConstants.java */
    /* loaded from: classes2.dex */
    public interface b {
        public static final String ERROR_CARRIER_DATA_MOBILE_DISABLED_MSG = "no_mobile_data_error";
        public static final String ERROR_CARRIER_DISABLED_MSG = "carrier_disable_error";
        public static final String ERROR_CU_REQUEST_TIMEOUT_MSG = "cu_request_time_out";
        public static final String ERROR_INVALID_RESPONSE = "invalid_response";
        public static final String ERROR_NOT_SUPPORT = "not support operator";
        public static final String ERROR_NO_PHONE_STATE_PERMISSON_MSG = "no_read_phone_state_permission_error";
        public static final String ERROR_SDK_INIT_MSG = "sdk_init_error";
        public static final String ERROR_UNKNOW_MSG = "unknown";
        public static final String ERROR_WEAK_NETWORK_MSG = "weak_network_error";
    }

    /* compiled from: OnekeyLoginConstants.java */
    /* loaded from: classes2.dex */
    public interface c {
        public static final int ERROR_GET_ACCESS_CODE = 3;
        public static final int ERROR_GET_PHONE = 1;
        public static final int ERROR_GET_TOKEN = 2;
        public static final int ERROR_LOGIN_BY_TOKEN = 4;
    }
}
