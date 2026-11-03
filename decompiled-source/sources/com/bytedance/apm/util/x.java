package com.bytedance.apm.util;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
/* compiled from: PermissionUtils.java */
/* loaded from: classes.dex */
public class x {
    public static boolean checkSelfPermission(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) ? false : true;
    }

    public static boolean isGrantSDCardWritePermission(Context context) {
        return checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }
}
