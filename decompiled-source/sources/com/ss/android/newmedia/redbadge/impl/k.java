package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.bytedance.librarian.c;
import java.util.Arrays;
import java.util.List;
/* compiled from: NovaHomeBadger.java */
/* loaded from: classes9.dex */
public class k implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18417a = "content://com.teslacoilsw.notifier/unread_count";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18418b = "count";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18419c = "tag";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", componentName.getPackageName() + c.a.SEPARATOR + componentName.getClassName());
        contentValues.put("count", Integer.valueOf(i2));
        try {
            context.getContentResolver().insert(Uri.parse(f18417a), contentValues);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }
}
