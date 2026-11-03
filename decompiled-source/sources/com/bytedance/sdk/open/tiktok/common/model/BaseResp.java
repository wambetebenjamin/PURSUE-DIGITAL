package com.bytedance.sdk.open.tiktok.common.model;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
/* loaded from: classes3.dex */
public abstract class BaseResp {
    public int errorCode;
    public String errorMsg;
    public Bundle extras;

    public boolean checkArgs() {
        return true;
    }

    public abstract int getType();

    public boolean isCancel() {
        return this.errorCode == -2;
    }

    public boolean isSuccess() {
        return this.errorCode == 0;
    }

    public void toBundle(Bundle bundle) {
        bundle.putInt(ParamKeyConstants.BaseParams.ERROR_CODE, this.errorCode);
        bundle.putString(ParamKeyConstants.BaseParams.ERROR_MSG, this.errorMsg);
        bundle.putInt(ParamKeyConstants.BaseParams.TYPE, getType());
        bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, this.extras);
    }

    public void fromBundle(Bundle bundle) {
        this.errorCode = bundle.getInt(ParamKeyConstants.BaseParams.ERROR_CODE);
        this.errorMsg = bundle.getString(ParamKeyConstants.BaseParams.ERROR_MSG);
        this.extras = bundle.getBundle(ParamKeyConstants.BaseParams.EXTRA);
    }
}
