package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
/* compiled from: AsusHomeBadger.java */
/* loaded from: classes9.dex */
public class c implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18403a = "android.intent.action.BADGE_COUNT_UPDATE";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18404b = "badge_count";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18405c = "badge_count_package_name";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18406d = "badge_count_class_name";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent(f18403a);
        intent.putExtra(f18404b, i2);
        intent.putExtra(f18405c, componentName.getPackageName());
        intent.putExtra(f18406d, componentName.getClassName());
        intent.putExtra("badge_vip_count", 0);
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
        return Arrays.asList("com.asus.launcher", "com.lge.launcher", "com.lge.launcher2", "com.lge.launcher3");
    }
}
