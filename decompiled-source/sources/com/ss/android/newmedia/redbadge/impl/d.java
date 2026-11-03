package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
/* compiled from: DefaultBadger.java */
/* loaded from: classes9.dex */
public class d implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18407a = "android.intent.action.BADGE_COUNT_UPDATE";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18408b = "badge_count";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18409c = "badge_count_package_name";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18410d = "badge_count_class_name";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent(f18407a);
        intent.putExtra(f18408b, i2);
        intent.putExtra(f18409c, componentName.getPackageName());
        intent.putExtra(f18410d, componentName.getClassName());
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
        return new ArrayList(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Context context) {
        return com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, new Intent(f18407a));
    }
}
