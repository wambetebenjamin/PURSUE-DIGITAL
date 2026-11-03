package com.bytedance.sdk.open.tiktok.helper;

import android.content.Context;
import com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl;
import com.bytedance.ug.sdk.share.channel.tiktok.impl.b;
/* loaded from: classes3.dex */
public class TikTokCheckHelperImpl extends BaseCheckHelperImpl {
    @Override // com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl
    protected int getAuthRequestApi() {
        return 1;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public String getPackageName() {
        return b.PACKAGE_NAME_T;
    }

    @Override // com.bytedance.sdk.open.tiktok.base.BaseCheckHelperImpl
    public String getSignature() {
        return "aea615ab910015038f73c47e45d21466";
    }

    public TikTokCheckHelperImpl(Context context) {
        super(context);
    }

    @Override // com.bytedance.sdk.open.tiktok.base.IAPPCheckHelper
    public boolean isShareSupportFileProvider() {
        return isAppSupportAPI(getPackageName(), getRemoteAuthEntryActivity(), 5);
    }
}
