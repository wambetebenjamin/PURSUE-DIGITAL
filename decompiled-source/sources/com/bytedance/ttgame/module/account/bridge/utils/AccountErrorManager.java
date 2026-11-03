package com.bytedance.ttgame.module.account.bridge.utils;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.rocketapi.account.AccountCodeResult;
import com.bytedance.ttgame.rocketapi.account.CookieResult;
import com.bytedance.ttgame.rocketapi.account.PeerNetworkUserInfoResult;
import com.bytedance.ttgame.rocketapi.account.ReleaseResult;
import com.bytedance.ttgame.rocketapi.account.SuccessionCodeResult;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public class AccountErrorManager {
    public static final int GSDK_ACCOUNT_CLIENT_OTHER = -105999;

    public static String gsdkErrorToStr(GSDKError gSDKError) {
        return gSDKError != null ? "code=" + gSDKError.getCode() + ",message=" + gSDKError.getMessage() + ",extraErrorCode=" + gSDKError.getExtraErrorCode() + ",extraErrorMessage=" + gSDKError.getExtraErrorMessage() + ",additionalInfo:" + gSDKError.getAdditionalInfo() : "";
    }

    public static JSONObject convertFailInfoToJson(UserInfoResult userInfoResult, String str) {
        if (userInfoResult != null) {
            return convertGSDKErrorToJson(userInfoResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertFailInfoToJson(SuccessionCodeResult successionCodeResult, String str) {
        if (successionCodeResult != null) {
            return convertGSDKErrorToJson(successionCodeResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertFailInfoToJson(PeerNetworkUserInfoResult peerNetworkUserInfoResult, String str) {
        if (peerNetworkUserInfoResult != null) {
            return convertGSDKErrorToJson(peerNetworkUserInfoResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertFailInfoToJson(ReleaseResult releaseResult, String str) {
        if (releaseResult != null) {
            return convertGSDKErrorToJson(releaseResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertFailInfoToJson(CookieResult cookieResult, String str) {
        if (cookieResult != null) {
            return convertGSDKErrorToJson(cookieResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertFailInfoToJson(AccountCodeResult accountCodeResult, String str) {
        if (accountCodeResult != null) {
            return convertGSDKErrorToJson(accountCodeResult.gsdkError, str);
        }
        return convertGSDKErrorToJson((GSDKError) null, str);
    }

    public static JSONObject convertGSDKErrorToJson(GSDKError gSDKError) {
        return convertGSDKErrorToJson(gSDKError, "A NullPointerException occurred.");
    }

    public static JSONObject convertGSDKErrorToJson(GSDKError gSDKError, String str) {
        JSONObject jSONObject = new JSONObject();
        convertGSDKErrorToJson(jSONObject, gSDKError, str);
        return jSONObject;
    }

    public static void convertGSDKErrorToJson(JSONObject jSONObject, GSDKError gSDKError) {
        convertGSDKErrorToJson(jSONObject, gSDKError, "A NullPointerException occurred.");
    }

    public static void convertGSDKErrorToJson(JSONObject jSONObject, GSDKError gSDKError, String str) {
        if (gSDKError != null) {
            BaseModule.add(jSONObject, "code", gSDKError.getCode());
            BaseModule.add(jSONObject, "message", gSDKError.getMessage());
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
            BaseModule.add(jSONObject, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
            return;
        }
        BaseModule.add(jSONObject, "code", -105999);
        BaseModule.add(jSONObject, "message", str);
        BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_CODE, 0);
        BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_MESSAGE, "");
        BaseModule.add(jSONObject, ErrorConstants.ADDITIONAL_INFO, "");
    }
}
