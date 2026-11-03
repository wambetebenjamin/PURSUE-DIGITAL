package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
/* compiled from: ApexHomeBadger.java */
/* loaded from: classes9.dex */
public class b implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18398a = "com.anddoes.launcher.COUNTER_CHANGED";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18399b = "package";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18400c = "count";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18401d = "badgecount";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18402e = "class";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent(f18398a);
        intent.putExtra("package", componentName.getPackageName());
        intent.putExtra("count", i2);
        intent.putExtra(f18401d, i2);
        intent.putExtra(f18402e, componentName.getClassName());
        context.sendBroadcast(intent);
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
        return Arrays.asList("com.anddoes.launcher");
    }
}
