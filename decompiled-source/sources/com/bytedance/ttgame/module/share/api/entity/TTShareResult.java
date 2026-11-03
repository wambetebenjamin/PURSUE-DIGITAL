package com.bytedance.ttgame.module.share.api.entity;

import android.os.Bundle;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.share.api.panel.TTShareItemType;
/* loaded from: classes7.dex */
public class TTShareResult {
    public static final int ERROR_BEHIND_AUTH_ERROR = 11000;
    public static final int ERROR_BEHIND_NOT_LOGIN_DOUYIN = 11004;
    public static final int ERROR_BEHIND_NOT_TOUTIAO = 11003;
    public static final int ERROR_BEHIND_OPEN_CREATE = 11001;
    public static final int ERROR_BEHIND_OPEN_UPLOAD = 11002;
    public static final int ERROR_DATA_NULL = 10010;
    public static final int ERROR_DOUYIN_UNAUTHORIZED = 2190003;
    public static final int ERROR_FILE_TOO_LARGE = 10090;
    public static final int ERROR_IMAGE_TOO_LARGE = 10091;
    public static final int ERROR_SDK_APP_INIT_FAILED = 10016;
    public static final int ERROR_SDK_APP_NOT_INSTALL = 10011;
    public static final int ERROR_SDK_APP_VERSION_NOT_SUPPORT = 10015;
    public static final int ERROR_SDK_CONTEXT_NULL = 10012;
    public static final int ERROR_SDK_DATA_NULL = 10013;
    public static final int ERROR_SDK_FILE_FETCH_FAILED = 10073;
    public static final int ERROR_SDK_FILE_NAME_EMPTY = 10072;
    public static final int ERROR_SDK_FILE_NOT_SUPPORT = 10070;
    public static final int ERROR_SDK_FILE_URL_EMPTY = 10071;
    public static final int ERROR_SDK_H5_IMAGE_EMPTY = 10023;
    public static final int ERROR_SDK_H5_NOT_SUPPORT = 10020;
    public static final int ERROR_SDK_H5_TITLE_EMPTY = 10021;
    public static final int ERROR_SDK_H5_URL_EMPTY = 10022;
    public static final int ERROR_SDK_IMAGE_AND_TEXT_NOT_SUPPORT = 10030;
    public static final int ERROR_SDK_IMAGE_AND_TEXT_TITLE_EMPTY = 10031;
    public static final int ERROR_SDK_IMAGE_AND_TEXT_URL_EMPTY = 10032;
    public static final int ERROR_SDK_IMAGE_FETCH_FAILED = 10055;
    public static final int ERROR_SDK_IMAGE_LOCAL_URL_ERROR = 10053;
    public static final int ERROR_SDK_IMAGE_NOT_SUPPORT = 10050;
    public static final int ERROR_SDK_IMAGE_RESP_DATA_EMPTY = 10052;
    public static final int ERROR_SDK_IMAGE_URL_EMPTY = 10051;
    public static final int ERROR_SDK_IMAGE_URL_NOT_SUPPORT = 10054;
    public static final int ERROR_SDK_MINIAPP_DATA_ERROR = 10080;
    public static final int ERROR_SDK_MINIAPP_ID_EMPTY = 10084;
    public static final int ERROR_SDK_MINIAPP_IMAGE_EMPTY = 10082;
    public static final int ERROR_SDK_MINIAPP_NOT_SUPPORT = 10085;
    public static final int ERROR_SDK_MINIAPP_PATH_EMPTY = 10086;
    public static final int ERROR_SDK_MINIAPP_TITLE_EMPTY = 10081;
    public static final int ERROR_SDK_MINIAPP_WEB_URL_EMPTY = 10083;
    public static final int ERROR_SDK_SUPER_GROUP_NOT_SUPPORT = 10200;
    public static final int ERROR_SDK_SUPER_GROUP_TITLE_EMPTY = 10201;
    public static final int ERROR_SDK_TEXT_NOT_SUPPORT = 10040;
    public static final int ERROR_SDK_TEXT_TITLE_EMPTY = 10041;
    public static final int ERROR_SDK_UNKNOWN = 10014;
    public static final int ERROR_SDK_VIDEO_FETCH_FAILED = 10066;
    public static final int ERROR_SDK_VIDEO_FILE_NULL = 10063;
    public static final int ERROR_SDK_VIDEO_NOT_SUPPORT = 10060;
    public static final int ERROR_SDK_VIDEO_TARGET_URL_EMPTY = 10065;
    public static final int ERROR_SDK_VIDEO_TITLE_EMPTY = 10064;
    public static final int ERROR_SDK_VIDEO_TOO_LONG = 10062;
    public static final int ERROR_SDK_VIDEO_URL_EMPTY = 10061;
    public static final int ERROR_VIDEO_TOO_LARGE = 10092;
    public static final int SHARE_CANCEL = 10001;
    public static final int SHARE_FAILED = 10002;
    public static final int SHARE_SUCCESS = 10000;
    public int detailErrorCode;
    public GSDKError error;
    public Bundle extraBundle;
    public TTShareModel shareModel;
    public TTShareItemType shareType;
    public String transaction;

    public TTShareResult(GSDKError gSDKError, TTShareItemType tTShareItemType) {
        this.error = gSDKError;
        this.shareType = tTShareItemType;
    }

    public String toString() {
        return "TTShareResult{error=" + this.error + ", detailErrorCode=" + this.detailErrorCode + ", extraBundle=" + this.extraBundle + ", transaction='" + this.transaction + "', shareType=" + this.shareType + ", shareModel=" + this.shareModel + '}';
    }
}
