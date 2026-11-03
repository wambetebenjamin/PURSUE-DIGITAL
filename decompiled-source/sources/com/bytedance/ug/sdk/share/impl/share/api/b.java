package com.bytedance.ug.sdk.share.impl.share.api;

import android.content.Context;
/* compiled from: IShareChannelDepend.java */
/* loaded from: classes8.dex */
public interface b {
    a getChannel(Context context);

    c getChannelHandler();

    int getChannelIcon();

    String getChannelName();

    String getPackageName();

    boolean needFiltered();
}
