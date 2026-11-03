package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
/* compiled from: EverythingMeHomeBadger.java */
/* loaded from: classes9.dex */
public class e implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18411a = "content://me.everything.badger/apps";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18412b = "package_name";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18413c = "activity_name";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18414d = "count";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put(f18413c, componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i2));
        try {
            context.getContentResolver().insert(Uri.parse(f18411a), contentValues);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("me.everything.launcher");
    }
}
