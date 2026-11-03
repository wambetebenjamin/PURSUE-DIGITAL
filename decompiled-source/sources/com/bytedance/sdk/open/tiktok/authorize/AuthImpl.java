package com.bytedance.sdk.open.tiktok.authorize;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.utils.AppUtil;
/* loaded from: classes3.dex */
public class AuthImpl {
    private Activity mActivity;
    private String mClientKey;

    public AuthImpl(Activity activity, String str) {
        this.mActivity = activity;
        this.mClientKey = str;
    }

    public boolean authorizeWeb(Class cls, Authorization.Request request) {
        if (request == null || this.mActivity == null || !request.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        request.toBundle(bundle);
        bundle.putString(ParamKeyConstants.AuthParams.CLIENT_KEY, this.mClientKey);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_PKG, this.mActivity.getPackageName());
        Intent intent = new Intent(this.mActivity, cls);
        intent.putExtras(bundle);
        try {
            this.mActivity.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean authorizeNative(Authorization.Request request, String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || request == null || this.mActivity == null || !request.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        request.toBundle(bundle);
        bundle.putString(ParamKeyConstants.AuthParams.CLIENT_KEY, this.mClientKey);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_PKG, this.mActivity.getPackageName());
        if (TextUtils.isEmpty(request.callerLocalEntry)) {
            bundle.putString(ParamKeyConstants.BaseParams.FROM_ENTRY, AppUtil.buildComponentClassName(this.mActivity.getPackageName(), str3));
        }
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_NAME, str4);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_VERSION, str5);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)));
        intent.putExtras(bundle);
        try {
            this.mActivity.startActivityForResult(intent, 100);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
