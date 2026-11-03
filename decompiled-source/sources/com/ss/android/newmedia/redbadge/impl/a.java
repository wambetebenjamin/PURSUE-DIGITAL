package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
/* compiled from: AdwHomeBadger.java */
/* loaded from: classes9.dex */
public class a implements com.ss.android.newmedia.redbadge.a {
    public static final String CLASSNAME = "CNAME";
    public static final String COUNT = "COUNT";
    public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
    public static final String PACKAGENAME = "PNAME";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent(INTENT_UPDATE_COUNTER);
        intent.putExtra(PACKAGENAME, componentName.getPackageName());
        intent.putExtra(CLASSNAME, componentName.getClassName());
        intent.putExtra(COUNT, i2);
        if (com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent)) {
            try {
                context.sendBroadcast(intent);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
            }
        }
        throw new com.ss.android.newmedia.redbadge.d("unable to resolve intent: " + intent.toString());
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("org.adw.launcher", "org.adwfreak.launcher", "org.adw.launcher.one");
    }
}
