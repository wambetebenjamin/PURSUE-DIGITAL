package com.bytedance.ug.sdk.share.channel.facebook.impl;

import android.content.Context;
import com.bytedance.share_channel_facebook.R;
import com.bytedance.ug.sdk.share.impl.utils.n;
/* compiled from: FacebookShareDependImpl.java */
/* loaded from: classes8.dex */
public class c implements com.bytedance.ug.sdk.share.impl.share.api.b {
    public static final String PACKAGE_NAME = "com.facebook.katana";
    private Context mContext;

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getPackageName() {
        return "com.facebook.katana";
    }

    public c(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public com.bytedance.ug.sdk.share.impl.share.api.a getChannel(Context context) {
        return new b(context);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public int getChannelIcon() {
        return R.drawable.share_sdk_share_icon_facebook;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getChannelName() {
        return this.mContext.getString(R.string.share_sdk_action_facebook_share);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public com.bytedance.ug.sdk.share.impl.share.api.c getChannelHandler() {
        return new d();
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public boolean needFiltered() {
        return !n.isInstalledApp("com.facebook.katana");
    }
}
