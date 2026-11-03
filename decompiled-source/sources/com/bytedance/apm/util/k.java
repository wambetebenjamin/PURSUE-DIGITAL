package com.bytedance.apm.util;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import com.bytedance.common.utility.reflect.Reflect;
/* compiled from: IntentUtils.java */
/* loaded from: classes.dex */
public class k {
    public static String parsePendingIntent(PendingIntent pendingIntent) {
        return (Build.VERSION.SDK_INT > 23 || pendingIntent == null) ? "" : ((Intent) Reflect.on(pendingIntent).call("getIntent").get()).toString();
    }
}
