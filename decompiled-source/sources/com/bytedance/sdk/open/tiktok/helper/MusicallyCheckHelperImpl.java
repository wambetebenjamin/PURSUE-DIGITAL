package com.bytedance.sdk.open.tiktok.helper;

import android.content.Context;
import com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl;
import com.bytedance.ug.sdk.share.channel.tiktok.impl.b;
/* loaded from: classes3.dex */
public class MusicallyCheckHelperImpl extends BaseCheckHelperImpl {
    @Override // com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl
    protected int getAuthRequestApi() {
        return 1;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public String getPackageName() {
        return b.PACKAGE_NAME_M;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl
    public String getSignature() {
        return "194326e82c84a639a52e5c023116f12a";
    }

    public MusicallyCheckHelperImpl(Context context) {
        super(context);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isShareSupportFileProvider() {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), 5);
    }
}
