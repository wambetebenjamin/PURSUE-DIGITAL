package com.bytedance.ug.sdk.share.impl.share.api;

import com.bytedance.ug.sdk.share.api.entity.h;
/* compiled from: IShare.java */
/* loaded from: classes8.dex */
public interface a {
    boolean canShare(h hVar);

    boolean doShare(h hVar);

    String getPackageName();

    boolean isInstalled();
}
