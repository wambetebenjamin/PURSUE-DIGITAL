package com.bytedance.ttgame.module.share.api.depend;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.module.share.api.callback.TTRequestPermissionCallback;
import com.bytedance.ttgame.module.share.api.entity.TTShareModel;
/* loaded from: classes7.dex */
public interface ITTSharePermissionConfig {
    boolean hasPermission(Context context, String str);

    void requestPermissions(Activity activity, String[] strArr, TTShareModel tTShareModel, TTRequestPermissionCallback tTRequestPermissionCallback);
}
