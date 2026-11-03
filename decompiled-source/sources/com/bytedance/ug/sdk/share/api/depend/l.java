package com.bytedance.ug.sdk.share.api.depend;

import android.app.Activity;
import android.content.Context;
/* compiled from: ISharePermissionConfig.java */
/* loaded from: classes8.dex */
public interface l {
    boolean hasPermission(Context context, String str);

    void requestPermissions(Activity activity, String[] strArr, com.bytedance.ug.sdk.share.api.entity.h hVar, com.bytedance.ug.sdk.share.api.callback.k kVar);
}
