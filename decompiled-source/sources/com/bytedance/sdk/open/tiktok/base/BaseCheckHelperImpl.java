package com.bytedance.sdk.open.tiktok.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.utils.AppUtil;
import com.bytedance.sdk.open.tiktok.utils.SignatureUtils;
/* loaded from: classes3.dex */
public abstract class BaseCheckHelperImpl implements IAPPCheckHelper {
    private Context mContext;

    protected abstract int getAuthRequestApi();

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public String getRemoteAuthEntryActivity() {
        return "openauthorize.AwemeAuthorizedActivity";
    }

    public abstract String getSignature();

    public BaseCheckHelperImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isAppSupportAuthorization() {
        return isAppSupportAuthApi() && SignatureUtils.validateSign(this.mContext, getPackageName(), getSignature());
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isAppSupportShare() {
        return isAppSupportShareApi(getPackageName(), getRemoteAuthEntryActivity(), 3);
    }

    public boolean isAppSupportAPI(String str, String str2, int i2) {
        if (this.mContext == null || TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)));
        ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(this.mContext.getPackageManager(), 65536);
        return resolveActivityInfo != null && resolveActivityInfo.exported && getPlatformSDKVersion(str, str2) >= i2;
    }

    private boolean isAppSupportAuthApi() {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), getAuthRequestApi());
    }

    public int getPlatformSDKVersion(String str, String str2) {
        if (this.mContext != null && !TextUtils.isEmpty(str)) {
            try {
                ActivityInfo activityInfo = this.mContext.getPackageManager().getActivityInfo(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)), 128);
                if (activityInfo != null && activityInfo.metaData != null) {
                    return activityInfo.metaData.getInt(ParamKeyConstants.META_PLATFORM_SDK_VERSION, -1);
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }

    private boolean isAppSupportShareApi(String str, String str2, int i2) {
        return isAppSupportAPI(str, str2, i2);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isAppInstalled() {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), getAuthRequestApi());
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isAppSupportAPI(int i2) {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), i2);
    }
}
