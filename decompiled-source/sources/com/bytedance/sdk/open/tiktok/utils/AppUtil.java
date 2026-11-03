package com.bytedance.sdk.open.tiktok.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
/* loaded from: classes3.dex */
public class AppUtil {
    public static String buildComponentClassName(String str, String str2) {
        return str + c.a.DOT + str2;
    }

    public static int getPlatformSDKVersion(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(str, buildComponentClassName(str, str2)), 128);
                if (activityInfo != null && activityInfo.metaData != null) {
                    return activityInfo.metaData.getInt(ParamKeyConstants.META_PLATFORM_SDK_VERSION, -1);
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return -1;
    }
}
