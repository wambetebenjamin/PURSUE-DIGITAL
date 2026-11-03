package com.bytedance.ug.sdk.share.channel.tiktok.impl;

import android.content.Context;
import com.bytedance.share_channel_tiktok.R;
import com.bytedance.ug.sdk.share.impl.share.api.c;
import com.bytedance.ug.sdk.share.impl.utils.n;
/* compiled from: TiktokShareDependImpl.java */
/* loaded from: classes8.dex */
public class b implements com.bytedance.ug.sdk.share.impl.share.api.b {
    public static final String PACKAGE_NAME_M = "com.zhiliaoapp.musically";
    public static final String PACKAGE_NAME_T = "com.ss.android.ugc.trill";
    private Context mContext;

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public c getChannelHandler() {
        return null;
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
        return R.drawable.share_sdk_share_icon_tiktok;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getChannelName() {
        return this.mContext.getString(R.string.share_sdk_action_tiktok_share);
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public String getPackageName() {
        return n.isInstalledApp(PACKAGE_NAME_M) ? PACKAGE_NAME_M : PACKAGE_NAME_T;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.b
    public boolean needFiltered() {
        return (n.isInstalledApp(PACKAGE_NAME_T) || n.isInstalledApp(PACKAGE_NAME_M)) ? false : true;
    }
}
