package com.bytedance.ug.sdk.share.channel.whatsapp.impl;

import android.content.Context;
import com.bytedance.share_channel_whatsapp.R;
import com.bytedance.ug.sdk.share.impl.share.api.c;
import com.bytedance.ug.sdk.share.impl.utils.n;
/* compiled from: WhatsAppShareDependImpl.java */
/* loaded from: classes8.dex */
public class b implements com.bytedance.ug.sdk.share.impl.share.api.b {
    public static final String PACKAGE_NAME = "com.whatsapp";
    private Context mContext;

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public c getChannelHandler() {
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getPackageName() {
        return PACKAGE_NAME;
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
        return R.drawable.share_sdk_share_icon_whatsapp;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getChannelName() {
        return this.mContext.getString(R.string.share_sdk_action_whatsapp_share);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public boolean needFiltered() {
        return !n.isInstalledApp(PACKAGE_NAME);
    }
}
