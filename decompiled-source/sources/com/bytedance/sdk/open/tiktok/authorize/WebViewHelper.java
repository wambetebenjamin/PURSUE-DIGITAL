package com.bytedance.sdk.open.tiktok.authorize;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.utils.Md5Utils;
import com.bytedance.sdk.open.tiktok.utils.SignatureUtils;
/* loaded from: classes3.dex */
public class WebViewHelper {
    public static String getLoadUrl(Context context, Authorization.Request request, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(request.optionalScope1)) {
            String[] split = request.optionalScope1.split(",");
            for (int i2 = 0; i2 < split.length; i2++) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(split[i2] + ",1");
            }
        }
        if (!TextUtils.isEmpty(request.optionalScope0)) {
            String[] split2 = request.optionalScope0.split(",");
            for (int i3 = 0; i3 < split2.length; i3++) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(split2[i3] + ",0");
            }
        }
        return new Uri.Builder().scheme("https").authority(str).path(str2).appendQueryParameter("response_type", "code").appendQueryParameter("redirect_uri", request.redirectUri).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_CLIENT_KEY, request.getClientKey()).appendQueryParameter("state", request.state).appendQueryParameter("from", ParamKeyConstants.WebViewConstants.VALUE_FROM_OPENSDK).appendQueryParameter("scope", request.scope).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_OPTIONAL_SCOPE, sb.toString()).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_SIGNATURE, SignatureUtils.packageSignature(SignatureUtils.getMd5Signs(context, request.getCallerPackage()))).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_ENCRIPTION_PACKAGE, Md5Utils.hexDigest(request.getCallerPackage())).appendQueryParameter("device_platform", "android").build().toString();
    }
}
