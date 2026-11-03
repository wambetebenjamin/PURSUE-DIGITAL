package com.bytedance.sdk.account.api.call;

import android.os.Bundle;
import java.util.List;
import org.json.JSONObject;
/* compiled from: BaseApiResponse.java */
/* loaded from: classes2.dex */
public class b {
    public static final int API_ACCOUNT_AUTHORIZE = 10047;
    public static final int API_ACCOUNT_REMOVE = 10048;
    public static final int API_AUTH = 10003;
    public static final int API_AUTHORIZE_QR_CODE_LOGIN = 10021;
    public static final int API_BIND_EMAIL_FOR_DEVICE_LOGIN = 10045;
    public static final int API_BIND_LOGIN = 10005;
    public static final int API_BIND_VISITOR_ACCOUNT = 10043;
    public static final int API_CANCEL_CLOSE_ACCOUNT = 1032;
    public static final int API_CANCEL_DO = 10008;
    public static final int API_CANCEL_INDEX = 10009;
    public static final int API_CANCEL_POST = 10007;
    public static final int API_CAN_AWEME_QUICK_LOGIN = 10038;
    public static final int API_CAN_DEVICE_ONE_LOGIN = 10035;
    public static final int API_CHAIN_LOGIN = 10051;
    public static final int API_CHECK_CHAIN_LOGIN = 10050;
    public static final int API_CHECK_CODE = 1019;
    public static final int API_CHECK_EVN = 10010;
    public static final int API_CHECK_MOBILE_REGISTER = 10034;
    public static final int API_CHECK_MOBILE_UNUSABLE = 10029;
    public static final int API_CHECK_PWD = 10011;
    public static final int API_CHECK_TV_QR_CONNECT = 1031;
    public static final int API_CHECK_VISITOR_UPGRADE = 10056;
    public static final int API_COMMON_REQUEST = 10055;
    public static final int API_DELETE_DEVICE = 10016;
    public static final int API_DEVICE_LOGIN_INFO = 10041;
    public static final int API_DEVICE_ONE_LOGIN = 10037;
    public static final int API_DEVICE_ONE_LOGIN_CONTINUE = 10036;
    public static final int API_EMAIL_BIND = 10040;
    public static final int API_EMAIL_CHECK_REGISTER = 1026;
    public static final int API_EMAIL_REGISTER_CODE_VERIFY = 10052;
    public static final int API_EMAIL_REGISTER_VERIFY = 1025;
    public static final int API_EMAIL_SEND_CODE = 1024;
    public static final int API_EMAIL_TICKET_REGISTER = 10053;
    public static final int API_GENERATE_USER_INFO_TICKET = 10032;
    public static final int API_GET_ACCOUNT_INFO = 10017;
    public static final int API_GET_AUTH_TICKET = 10013;
    public static final int API_GET_AVAILABLE_WAYS = 10030;
    public static final int API_GET_LOGIN_DEVICES = 10015;
    public static final int API_GET_QR_CODE = 10018;
    public static final int API_GET_QR_CODE_STATUS = 10019;
    public static final int API_GET_TV_QR_CODE = 1030;
    public static final int API_LOGIN_BY_TICKET = 10014;
    public static final int API_LOGIN_BY_TICKET_AFTER_REGISTER = 10033;
    public static final int API_LOGOUT = 10001;
    public static final int API_LOGOUT_OTHERS = 10027;
    public static final int API_MASK_MOBILE_ONE_LOGIN = 10057;
    public static final int API_MERGE = 10004;
    public static final int API_MOBILE_BIND_MOBILE = 1013;
    public static final int API_MOBILE_BIND_MOBILE_NO_PASS = 1012;
    public static final int API_MOBILE_CHANGE_PASS = 1010;
    public static final int API_MOBILE_CHECK_USER_NAME = 1008;
    public static final int API_MOBILE_EMAIL_LOGIN = 1009;
    public static final int API_MOBILE_LOGIN = 1007;
    public static final int API_MOBILE_MOBILE_NUM = 1011;
    public static final int API_MOBILE_QUEICK_LOGIN = 1006;
    public static final int API_MOBILE_QUEICK_LOGIN_CONTINUE = 1022;
    public static final int API_MOBILE_QUEICK_LOGIN_ONLY = 1021;
    public static final int API_MOBILE_REFRESH = 1000;
    public static final int API_MOBILE_REGISTER = 1004;
    public static final int API_MOBILE_RESET_WORD = 1003;
    public static final int API_MOBILE_SEND_CODE = 1002;
    public static final int API_MOBILE_UNBIND_MOBILE = 1001;
    public static final int API_OAUTH_PROFILE = 10025;
    public static final int API_ONE_MOBILE_BIND_MOBILE = 1027;
    public static final int API_PASSWORD_HAS_SET_BY_MOBILE = 10044;
    public static final int API_PASS_SET_PASS_STRING = 10002;
    public static final int API_PLATFORM_TOKEN = 10006;
    public static final int API_QUICK_AUTH_LOGIN_CONTINUE = 10049;
    public static final int API_SAFE_VERIFY = 10039;
    public static final int API_SCAN_QR_CODE = 10020;
    public static final int API_SSO_BIND = 0;
    public static final int API_SSO_CHECK_BIND_LOGIN = 10054;
    public static final int API_SSO_LOGIN = 1;
    public static final int API_SSO_SWITCH_BIND = 3;
    public static final int API_SSO_UNBIND = 2;
    public static final int API_SWITCH_AUTH = 101;
    public static final int API_SWITCH_TICKET = 1020;
    public static final int API_THIRD_AUTHORIZE = 30000;
    public static final int API_TICKET_RESET_PASSWORD = 1018;
    public static final int API_UPDATE_PWD = 10012;
    public static final int API_UPDATE_TOKEN = 10022;
    public static final int API_USER_DEVICE_LOGIN = 10042;
    public static final int API_USER_PASSWORD_LOGIN = 1016;
    public static final int API_USER_PASSWORD_REGISTER = 1023;
    public static final int API_VALIDATE_CODE = 1015;
    public static final int API_VERIFY_ACCOUNT_PASSWORD = 10031;
    public static final int API_VERIFY_DEVICE = 1014;
    public static final int API_VERIFY_EMAIL = 10046;

    /* renamed from: j  reason: collision with root package name */
    public static final int f12246j = 10058;
    public final int api;
    public Bundle dataTip;
    public int error;
    public String errorMsg;
    public List<com.ss.android.h> l;
    public String logId;
    public int mDetailErrorCode;
    public String mDetailErrorMsg;
    public String mRequestUrl;
    public JSONObject result;
    public String secondaryDecisionConf;
    public boolean success;
    @Deprecated
    public String url;

    public b(boolean z, int i2) {
        this.success = z;
        this.api = i2;
    }

    public boolean isBindExist() {
        int i2 = this.error;
        return i2 == 1030 || i2 == 1041;
    }

    public boolean needCaptcha() {
        int i2 = this.error;
        return i2 > 1100 && i2 < 1199;
    }

    public boolean needPicCaptcha() {
        int i2 = this.error;
        return i2 == 1101 || i2 == 1102 || i2 == 1103;
    }

    public boolean needSecureCaptcha() {
        int i2 = this.error;
        return i2 == 1104 || i2 == 1105;
    }

    public String toString() {
        return "BaseApiResponse{logId='" + this.logId + "', secondaryDecisionConf='" + this.secondaryDecisionConf + "', success=" + this.success + ", mRequestUrl='" + this.mRequestUrl + "', dataTip=" + this.dataTip + ", error=" + this.error + ", mDetailErrorCode=" + this.mDetailErrorCode + ", errorMsg='" + this.errorMsg + "', mDetailErrorMsg='" + this.mDetailErrorMsg + "', url='" + this.url + "', headers=" + this.l + ", result=" + this.result + ", api=" + this.api + '}';
    }
}
