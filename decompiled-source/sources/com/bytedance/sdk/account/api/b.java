package com.bytedance.sdk.account.api;
/* compiled from: AccountErrorCode.java */
/* loaded from: classes2.dex */
public class b {
    public static final int ACCOUNT_BIND_CONFLICT = 1030;
    public static final int ACCOUNT_LOGIN_BLOCKED = 1091;
    public static final int ACCOUNT_MODIFY_BLOCKED = 1092;
    public static final int AUTHORIZATION_FAILED = 1045;
    public static final int AUTH_EXPIRED = 8;
    public static final int BIND_PHONE_ERROR = 1004;
    public static final int BIND_PHONE_NOT_EXIST = 1006;
    public static final int CANCEL_ACCOUNT_ERROR_CODE = 1075;
    public static final int CAPTCHA_CEILING = 1199;
    public static final int CAPTCHA_ERROR = 1102;
    public static final int CAPTCHA_EXPIRED = 1103;
    public static final int CAPTCHA_FLOOR = 1100;
    public static final int CAPTCHA_MISSING = 1101;
    public static final int DEVICE_MODIFY_BLOCK = 1093;
    public static final int EMAIL_ERROR = 1031;
    public static final int EMAIL_EXISTED = 1023;
    public static final int EMAIL_IS_EMPTY = 1014;
    public static final int ERROR_CODE_UNKNOWN = -1;
    public static final int ERROR_IN_PHONE_OPERATOR = 1076;
    public static final int FORBID_WHEN_UNBIND_LAST_CONNECT = 1038;
    public static final int HAS_REGISTERED = 1001;
    public static final int NAME_EXISTED = 1024;
    public static final int NEED_SEC_CLICK_CAPTCHA = 1104;
    public static final int NEED_SEC_SCROLL_CAPTCHA = 1105;
    public static final int NEED_SMS_CODE_VERIFY_DEVICE = 1039;
    public static final int NO_AUTHORIZE = 2;
    public static final int OLDPHONEERROR = 1036;
    public static final int OP_FREQ_ERROR = 7;
    public static final int PARAMATER_ERROR = 4;
    public static final int PARAMATER_LACK_ = 3;
    public static final int PASSWORD_CHECK_FAILED = 1012;
    public static final int PASSWORD_ERROR = 1009;
    public static final int PASSWORD_ERROR_LEVEL2 = 1033;
    public static final int PASSWORD_ERROR_LEVEL3 = 1034;
    public static final int PASSWORD_ISEMPTY = 1010;
    public static final int PHONE_ERROR = 1003;
    public static final int PHONE_EXISTS_BINDING = 1035;
    public static final int PHONE_HASBOUND = 1007;
    public static final int PHONE_IS_EMPTY = 1002;
    public static final int PHONE_LOCKED = 2004;
    public static final int PLATFORM_BIND_CONFLICT = 1041;
    public static final int QR_CODE_CHECK_FAILED = 1072;
    public static final int QR_CODE_EXPIRES = 1071;
    public static final int QR_CODE_SCANNED = 1070;
    public static final int SEND_CODE_BIND_CONFLICT_ERROR = 1057;
    public static final int SERVER_EXCEPTION = 6;
    public static final int SESSION_EXPIRED = 1;
    public static final int SIGNATURE_ERROR = 5;
    public static final int SMS_CODE_ERROR = 1202;
    public static final int SMS_CODE_EXPIRED = 1203;
    public static final int SMS_CODE_FREQ_ERROR = 1206;
    public static final int SMS_CODE_MISSING = 1201;
    public static final int SMS_CODE_SEND_ERROR = 1205;
    public static final int SMS_CODE_TYPE_ERROR = 1204;
    public static final int SUCCESS = 0;
    public static final int TEMPCLOSE_UNBINDING = 1040;
    public static final int TWOSTEPREGISTERTICK_ETEXPIRED = 1042;
    public static final int UNBIND_PHONE_ERROR = 1005;
    public static final int UNREGISTERED = 1008;
    public static final int USERID_IS_EMPTY = 1013;
    public static final int USER_NOTEXIST = 1011;
    public static final int USER_NOTLOGIN = 9;
    public static final int VCD_VERIFY_AUTH_ERROR = 4000;

    /* renamed from: a  reason: collision with root package name */
    public static final int f12238a = 1056;

    /* renamed from: b  reason: collision with root package name */
    public static final int f12239b = 1058;

    /* renamed from: c  reason: collision with root package name */
    public static final int f12240c = 1059;

    /* compiled from: AccountErrorCode.java */
    /* loaded from: classes2.dex */
    public interface a {
        public static final int OP_ERROR_API_ERROR = -17;
        public static final int OP_ERROR_CONNECT_TIMEOUT = -13;
        public static final int OP_ERROR_HIJACK_DATA = -23;
        public static final int OP_ERROR_INTERUPTED = -22;
        public static final int OP_ERROR_NETWORK_ERROR = -15;
        public static final int OP_ERROR_NETWORK_TIMEOUT = -14;
        public static final int OP_ERROR_NO_CONNECTION = -12;
        public static final int OP_ERROR_NULL_CONTEXT = -24;
        public static final int OP_ERROR_NULL_RESPONSE = -25;
        public static final int OP_ERROR_RESPONSE_LENGTH_EXCEED = -20;
        public static final int OP_ERROR_SERVER_ERROR = -16;
        public static final int OP_ERROR_SERVICE_UNAVAILABLE = -19;
        public static final int OP_ERROR_SSL = -21;
        public static final int OP_ERROR_UNKNOWN = -18;
    }

    /* compiled from: AccountErrorCode.java */
    /* renamed from: com.bytedance.sdk.account.api.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0184b {
        public static final int COMMON_ERROR_AUTHORIZE = -1004;
        public static final int COMMON_ERROR_CANCEL_AUTHORIZE = -1001;
        public static final int COMMON_ERROR_ONKEY_GET_TOKEN = -1003;
        public static final int COMMON_ERROR_ONKEY_PRE_PHONE = -1002;
        public static final int COMMON_ERROR_REQUEST_API = -1005;
        public static final int COMMON_UNKNOWN_ERROR = -1006;

        /* renamed from: a  reason: collision with root package name */
        public static final int f12241a = -1007;

        /* renamed from: b  reason: collision with root package name */
        public static final int f12242b = -1008;
    }

    /* compiled from: AccountErrorCode.java */
    /* loaded from: classes2.dex */
    public interface c {
        public static final String ERROR_INVALID_RESPONSE = "response null";
        public static final String ERROR_NO_CONNECTION = "no net work";
        public static final String ERROR_NULL_CONTEXT = "context_is_null";
        public static final String ERROR_UNKNOW_MSG = "unknown";

        /* renamed from: a  reason: collision with root package name */
        public static final String f12243a = "fail to parse data";
    }
}
