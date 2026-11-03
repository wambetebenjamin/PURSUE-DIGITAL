package com.vk.api.sdk.internal;

import android.os.Bundle;
import com.bytedance.apm.perf.traffic.a;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: VKErrorUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\fJ\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0002J\u001a\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007¨\u0006\u0017"}, d2 = {"Lcom/vk/api/sdk/internal/VKErrorUtils;", "", "()V", "executeErrorsSet", "", "", "jsonString", "", "hasExecuteError", "", a.TTNET_RESPONSE, "ignoreErrors", "", "hasSimpleError", "parseExecuteError", "Lcom/vk/api/sdk/exceptions/VKApiException;", "method", "ignoredErrors", "errorsJson", "Lorg/json/JSONArray;", "parseSimpleError", "errorJson", "Lorg/json/JSONObject;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKErrorUtils {
    public static final VKErrorUtils INSTANCE = new VKErrorUtils();

    private VKErrorUtils() {
    }

    public final boolean hasSimpleError(String response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        return JsonUtils.INSTANCE.containsElement(response, "error");
    }

    public final boolean hasExecuteError(String response, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (JsonUtils.INSTANCE.containsElement(response, VKApiCodes.PARAM_EXECUTE_ERRORS)) {
            if (iArr == null) {
                return true;
            }
            Set<Integer> executeErrorsSet = executeErrorsSet(response);
            for (int i2 : iArr) {
                executeErrorsSet.remove(Integer.valueOf(i2));
            }
            if (!executeErrorsSet.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private final Set<Integer> executeErrorsSet(String str) {
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = new JSONObject(str).getJSONArray(VKApiCodes.PARAM_EXECUTE_ERRORS);
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            hashSet.add(Integer.valueOf(jSONArray.getJSONObject(i2).getInt("error_code")));
        }
        return hashSet;
    }

    public static /* synthetic */ VKApiException parseSimpleError$default(VKErrorUtils vKErrorUtils, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = null;
        }
        return vKErrorUtils.parseSimpleError(str, str2);
    }

    public final VKApiException parseSimpleError(String errorJson, String str) {
        Intrinsics.checkParameterIsNotNull(errorJson, "errorJson");
        JSONObject optJSONObject = new JSONObject(errorJson).optJSONObject("error");
        Intrinsics.checkExpressionValueIsNotNull(optJSONObject, "JSONObject(errorJson).op…t(VKApiCodes.PARAM_ERROR)");
        return parseSimpleError(optJSONObject, str);
    }

    public static /* synthetic */ VKApiException parseSimpleError$default(VKErrorUtils vKErrorUtils, JSONObject jSONObject, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        return vKErrorUtils.parseSimpleError(jSONObject, str);
    }

    public final VKApiException parseSimpleError(JSONObject errorJson, String str) {
        Intrinsics.checkParameterIsNotNull(errorJson, "errorJson");
        try {
            int i2 = errorJson.getInt("error_code");
            Bundle bundle = null;
            if (i2 == 5) {
                JSONObject optJSONObject = errorJson.optJSONObject(VKApiCodes.PARAM_BAN_INFO);
                if (optJSONObject != null) {
                    bundle = new Bundle();
                    bundle.putString(VKApiCodes.EXTRA_USER_BAN_INFO, optJSONObject.toString());
                }
            } else if (i2 == 14) {
                bundle = new Bundle();
                bundle.putString(VKApiCodes.EXTRA_CAPTCHA_SID, errorJson.getString(VKApiCodes.EXTRA_CAPTCHA_SID));
                bundle.putString(VKApiCodes.EXTRA_CAPTCHA_IMG, errorJson.getString(VKApiCodes.EXTRA_CAPTCHA_IMG));
            } else if (i2 == 17) {
                bundle = new Bundle();
                bundle.putString(VKApiCodes.EXTRA_VALIDATION_URL, errorJson.getString("redirect_uri"));
            } else if (i2 == 24) {
                bundle = new Bundle();
                bundle.putString("confirmation_text", errorJson.getString("confirmation_text"));
            }
            return VKApiExecutionException.Companion.parse(errorJson, str, bundle);
        } catch (Exception e2) {
            return new VKApiIllegalResponseException(e2);
        }
    }

    public final VKApiException parseExecuteError(String response, String method, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        Intrinsics.checkParameterIsNotNull(method, "method");
        JSONArray jSONArray = new JSONObject(response).getJSONArray(VKApiCodes.PARAM_EXECUTE_ERRORS);
        Intrinsics.checkExpressionValueIsNotNull(jSONArray, "JSONObject(response).get…des.PARAM_EXECUTE_ERRORS)");
        return parseExecuteError(jSONArray, method, iArr);
    }

    private final VKApiException parseExecuteError(JSONArray jSONArray, String str, int[] iArr) {
        int code;
        try {
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "errorsJson.getJSONObject(i)");
                VKApiException parseSimpleError$default = parseSimpleError$default(this, jSONObject, (String) null, 2, (Object) null);
                if (!(parseSimpleError$default instanceof VKApiExecutionException) || (code = ((VKApiExecutionException) parseSimpleError$default).getCode()) == 1 || code == 14 || code == 17 || code == 4 || code == 5 || code == 6 || code == 9 || code == 10 || code == 24 || code == 25) {
                    return parseSimpleError$default;
                }
                if (iArr == null || !ArraysKt.contains(iArr, ((VKApiExecutionException) parseSimpleError$default).getCode())) {
                    arrayList.add(parseSimpleError$default);
                }
            }
            return new VKApiExecutionException(Integer.MIN_VALUE, str, false, "", null, arrayList, null, 64, null);
        } catch (JSONException e2) {
            return new VKApiIllegalResponseException(e2);
        }
    }
}
