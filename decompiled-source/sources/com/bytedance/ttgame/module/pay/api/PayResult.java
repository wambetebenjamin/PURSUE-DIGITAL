package com.bytedance.ttgame.module.pay.api;

import com.bytedance.ttgame.base.GSDKError;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class PayResult {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_UPLOAD_TOKEN = 9;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final int DETAIL_SERVER_RESPONSE_ERROR = 2021;
    public static final int ERROR_API_PARAMS = -1001;
    public static final int ERROR_CREATE_ORDER = -2011;
    public static final int ERROR_GET_GOODS_INFO = -2008;
    public static final int ERROR_GOODS_VERYFIY = -2009;
    public static final int ERROR_LOGIN_STATE = -1002;
    public static final int ERROR_NETWORK_UNAVILABLE = -104;
    public static final int ERROR_NOT_BIND = -102;
    public static final int ERROR_NOT_LOGIN = -101;
    public static final int ERROR_ORDER_HAS_CREATE = -2010;
    public static final int ERROR_ORDER_NOT_EXIST = -2013;
    public static final int ERROR_OTHER = -103;
    public static final int ERROR_QUERY_ORDER = -2014;
    public static final int ERROR_SIGN_ERROR = -1009;
    public static final int ERROR_SYSTEM = -5000;
    public static final int ERROR_UPLOAD_TOKEN = -2012;
    public static final int ERROR_VISITOR_NOT_ALLOW_PAY = -2019;
    public static final int IABHELPER_BAD_RESPONSE = -3002;
    public static final int IABHELPER_INVALID_CONSUMPTION = -3010;
    public static final int IABHELPER_MISSING_TOKEN = -3007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -3001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -3004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -3009;
    public static final int IABHELPER_SUBSCRIPTION_UPDATE_NOT_AVAILABLE = -3011;
    public static final int IABHELPER_UNKNOWN_ERROR = -3008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -3006;
    public static final int IABHELPER_USER_CANCELLED = -3005;
    public static final int IABHELPER_VERIFICATION_FAILED = -3003;
    public static final int M_BIND_GOOGLE_SERVICE_ERROR = -6001;
    public static final int M_BUY_COINS = -6007;
    public static final int M_IABHELPER_ERROR_BILLING_NOT_SUPPORTED_IN_APP = -6002;
    public static final int M_IABHELPER_ERROR_BILLING_NOT_SUPPORTED_SUBS = -6003;
    public static final int M_IABHELPER_REMOTE_EXCEPTION = -6004;
    public static final int M_MAX_TIMES_OF_CHECK_ORDER = -6008;
    public static final int M_QUERY_GOOGLE_SERVICE_ERROR = -6000;
    public static final int M_QUERY_PRODUCT = -6006;
    public static final int M_QUERY_WALLET = -6005;
    public static final int PAY_RESULT_GOOGLE_ERROR = 203;
    public static final int PAY_RESULT_PARAMS_ERROR = 201;
    public static final int PAY_RESULT_UPLOAD_TOKEN_ERROR = 205;
    public static final int PAY_RESULT_USER_CANCEL = 206;
    public static final int SHARK_BLOCKING = -4001;
    public static final int SHARK_LOCAL_BLOCKING = -4005;
    public static final int SUCCESS = 0;
    private int code;
    private int detailCode;
    private Map<String, String> extraInfo = new HashMap();
    private GSDKError gsdkError;
    private String msg;
    private String orderId;
    private String outSideOrderId;
    private String productId;

    public PayResult() {
    }

    public PayResult(int i2, String str) {
        this.code = i2;
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public int getDetailCode() {
        return this.detailCode;
    }

    public void setDetailCode(int i2) {
        this.detailCode = i2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getOutSideOrderId() {
        return this.outSideOrderId;
    }

    public void setOutSideOrderId(String str) {
        this.outSideOrderId = str;
    }

    public Map<String, String> getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(HashMap<String, String> hashMap) {
        this.extraInfo = hashMap;
    }

    public void setGsdkError(GSDKError gSDKError) {
        this.gsdkError = gSDKError;
    }

    public GSDKError getGsdkError() {
        return this.gsdkError;
    }
}
