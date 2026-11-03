package com.bytedance.sdk.open.tiktok.common.model;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.BuildConfig;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
/* loaded from: classes3.dex */
public abstract class BaseReq {
    public String callerLocalEntry;
    public String callerPackage;
    public String callerVersion;
    public Bundle extras;

    public boolean checkArgs() {
        return true;
    }

    public abstract int getType();

    public String getCallerLocalEntry() {
        return this.callerLocalEntry;
    }

    public String getCallerPackage() {
        return this.callerPackage;
    }

    public String getCallerVersion() {
        return this.callerVersion;
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(ParamKeyConstants.BaseParams.TYPE, getType());
        bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, this.extras);
        bundle.putString(ParamKeyConstants.BaseParams.FROM_ENTRY, this.callerLocalEntry);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_COMMON_NAME, BuildConfig.SDK_OVERSEA_NAME);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_SDK_COMMON_VERSION, BuildConfig.SDK_OVERSEA_VERSION);
    }

    public void fromBundle(Bundle bundle) {
        this.callerPackage = bundle.getString(ParamKeyConstants.BaseParams.CALLER_PKG);
        this.callerVersion = bundle.getString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_VERSION);
        this.extras = bundle.getBundle(ParamKeyConstants.BaseParams.EXTRA);
        this.callerLocalEntry = bundle.getString(ParamKeyConstants.BaseParams.FROM_ENTRY);
    }
}
