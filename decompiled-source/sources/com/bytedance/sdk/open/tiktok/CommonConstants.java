package com.bytedance.sdk.open.tiktok;
/* loaded from: classes3.dex */
public interface CommonConstants {
    public static final String ACTION_STAY_IN_TT = "com.aweme.opensdk.action.stay.in.dy";

    /* loaded from: classes3.dex */
    public interface AuthErrorCode {
        public static final int ERROR_AUTHORIZATION_NO_PERMISSION = 10011;
        public static final int ERROR_CODE_EXPIRED = 10007;
        public static final int ERROR_CONFIG = 10003;
        public static final int ERROR_NETWORK_CONNECT_TIMEOUT = -13;
        public static final int ERROR_NETWORK_IO = -15;
        public static final int ERROR_NETWORK_NO_CONNECTION = -12;
        public static final int ERROR_NETWORK_SSL = -21;
        public static final int ERROR_NETWORK_TIMEOUT = -14;
        public static final int ERROR_NETWORK_UNKNOWN_HOST_ERROR = -16;
        public static final int ERROR_N_PARAMS = 10005;
        public static final int ERROR_PARAM = 10002;
        public static final int ERROR_REDIRECT_URL = 10006;
        public static final int ERROR_REFRESH_TOKEN = 10010;
        public static final int ERROR_SCOPE = 10004;
        public static final int ERROR_SYSTEM = 10001;
        public static final int ERROR_TICKET = 10009;
        public static final int ERROR_TOKEN = 10008;
    }

    /* loaded from: classes3.dex */
    public interface BaseErrorCode {
        public static final int ERROR_CANCEL = -2;
        public static final int ERROR_UNKNOW = -1;
        public static final int OK = 0;
    }

    /* loaded from: classes3.dex */
    public interface ModeType {
        public static final int SEND_AUTH_REQUEST = 1;
        public static final int SEND_AUTH_RESPONSE = 2;
        public static final int SHARE_CONTENT_TO_TT = 3;
        public static final int SHARE_CONTENT_TO_TT_RESP = 4;
        public static final int SHARE_TO_CONTACTS = 5;
        public static final int SHARE_TO_CONTACT_RESP = 6;
    }

    /* loaded from: classes3.dex */
    public interface ShareErrorCode {
        public static final int CANCEL_LOGIN = 20004;
        public static final int CANCEL_PUBLISH = 20013;
        public static final int ERROR_CODE_AUTH_DENIED = -4;
        public static final int ERROR_CODE_SEND_FAIL = -3;
        public static final int ERROR_CODE_UNSUPPORT = -5;
        public static final int GALLERY_PERMISSION_ERROR = 20005;
        public static final int GRANT_NETWORK_ERR = 20006;
        public static final int INVALID_GRANT = 20003;
        public static final int INVALID_PHOTO = 20008;
        public static final int INVALID_VIDEO_LENGTH = 20007;
        public static final int INVALID_VIDEO_RESOLUTION = 22001;
        public static final int INVALID_VIDEO_SIZE_RATIO = 20011;
        public static final int INVALID_VIDEO_TYPE = 20012;
        public static final int LAST_PUBLISH_NOT_FINISH = 20014;
        public static final int PARSE_MEDIA_FAIL = 20010;
        public static final int PUBLISH_FAIL_UNKNOWN = 20016;
        public static final int SAVE_TO_DRAFT = 20015;
        public static final int TIME_STAMP_INVALID = 20009;
    }
}
