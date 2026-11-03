package com.bytedance.ttgame.module.compliance.bridge;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.module.compliance.api.cookie.CookieResult;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CookieErrorManager {
    public static final int COOKIE_CLIENT_OTHER = -475999;

    public static JSONObject convertFailInfoToJson(CookieResult cookieResult, String str) {
        if (cookieResult != null) {
            return convertGSDKErrorToJson(cookieResult.gsdkError, str);
        }
        return convertGSDKErrorToJson(null, str);
    }

    public static JSONObject convertGSDKErrorToJson(GSDKError gSDKError, String str) {
        JSONObject jSONObject = new JSONObject();
        if (gSDKError != null) {
            BaseModule.add(jSONObject, "code", gSDKError.getCode());
            BaseModule.add(jSONObject, "message", gSDKError.getMessage());
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_CODE, gSDKError.getExtraErrorCode());
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_MESSAGE, gSDKError.getExtraErrorMessage());
            BaseModule.add(jSONObject, ErrorConstants.ADDITIONAL_INFO, gSDKError.getAdditionalInfo());
        } else {
            BaseModule.add(jSONObject, "code", -475999);
            BaseModule.add(jSONObject, "message", str);
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_CODE, 0);
            BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_MESSAGE, "");
            BaseModule.add(jSONObject, ErrorConstants.ADDITIONAL_INFO, "");
        }
        return jSONObject;
    }
}
