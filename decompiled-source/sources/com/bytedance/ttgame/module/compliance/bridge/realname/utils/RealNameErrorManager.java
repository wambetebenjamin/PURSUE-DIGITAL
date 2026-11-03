package com.bytedance.ttgame.module.compliance.bridge.realname.utils;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public class RealNameErrorManager {
    public static final int GSDK_ACCOUNT_CLIENT_OTHER = -105999;

    public static String gsdkErrorToStr(GSDKError gSDKError) {
        return gSDKError != null ? "code=" + gSDKError.getCode() + ",message=" + gSDKError.getMessage() + ",extraErrorCode=" + gSDKError.getExtraErrorCode() + ",extraErrorMessage=" + gSDKError.getExtraErrorMessage() + ",additionalInfo:" + gSDKError.getAdditionalInfo() : "";
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
