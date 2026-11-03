package com.bytedance.ug.sdk.share.api.depend;

import android.content.Context;
/* compiled from: IShareImageTokenConfig.java */
/* loaded from: classes8.dex */
public interface g {
    void checkImageToken();

    void checkSelectedMediaToken(String str);

    boolean showImageTokenDialog(Context context, com.bytedance.ug.sdk.share.api.entity.h hVar);
}
