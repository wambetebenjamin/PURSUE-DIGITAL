package com.bytedance.ug.sdk.share.channel.vk.impl;

import android.content.Context;
import com.bytedance.share_channel_vk.R;
import com.bytedance.ug.sdk.share.impl.share.api.c;
import com.bytedance.ug.sdk.share.impl.utils.n;
/* compiled from: VKShareDependImpl.java */
/* loaded from: classes8.dex */
public class b implements com.bytedance.ug.sdk.share.impl.share.api.b {
    public static final String PACKAGE_NAME = "com.vkontakte.android";
    private Context mContext;

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public c getChannelHandler() {
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getPackageName() {
        return "com.vkontakte.android";
    }

    public b(Context context) {
        this.mContext = context;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public com.bytedance.ug.sdk.share.impl.share.api.a getChannel(Context context) {
        return new a(context);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public int getChannelIcon() {
        return R.drawable.share_sdk_share_icon_vk;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getChannelName() {
        return this.mContext.getString(R.string.share_sdk_action_vk_share);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public boolean needFiltered() {
        return !n.isInstalledApp("com.vkontakte.android");
    }
}
