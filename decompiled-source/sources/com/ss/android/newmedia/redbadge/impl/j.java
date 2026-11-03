package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
/* compiled from: NewHtcHomeBadger.java */
/* loaded from: classes9.dex */
public class j implements com.ss.android.newmedia.redbadge.a {
    public static final String COUNT = "count";
    public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
    public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
    public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
    public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
    public static final String PACKAGENAME = "packagename";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent(INTENT_SET_NOTIFICATION);
        intent.putExtra(EXTRA_COMPONENT, componentName.flattenToShortString());
        intent.putExtra(EXTRA_COUNT, i2);
        Intent intent2 = new Intent(INTENT_UPDATE_SHORTCUT);
        intent2.putExtra(PACKAGENAME, componentName.getPackageName());
        intent2.putExtra("count", i2);
        if (com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent) || com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent2)) {
            try {
                context.sendBroadcast(intent);
                context.sendBroadcast(intent2);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
            }
        }
        throw new com.ss.android.newmedia.redbadge.d("unable to resolve intent: " + intent2.toString());
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.htc.launcher");
    }
}
