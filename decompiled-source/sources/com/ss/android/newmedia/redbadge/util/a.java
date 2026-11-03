package com.ss.android.newmedia.redbadge.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;
/* compiled from: BroadcastHelper.java */
/* loaded from: classes9.dex */
public class a {
    public static boolean canResolveBroadcast(Context context, Intent intent) {
        PackageManager packageManager;
        List<ResolveInfo> queryBroadcastReceivers;
        return (context == null || intent == null || (packageManager = context.getPackageManager()) == null || (queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0)) == null || queryBroadcastReceivers.size() <= 0) ? false : true;
    }
}
