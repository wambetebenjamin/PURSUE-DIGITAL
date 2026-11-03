package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import java.util.Arrays;
import java.util.List;
/* compiled from: SamsungHomeBadger.java */
/* loaded from: classes9.dex */
public class m implements com.ss.android.newmedia.redbadge.a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18428a = "content://com.sec.badge/apps?notify=true";

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f18429b = {"_id", "class"};

    /* renamed from: c  reason: collision with root package name */
    private d f18430c;

    public m() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f18430c = new d();
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        d dVar = this.f18430c;
        if (dVar != null && dVar.a(context)) {
            this.f18430c.executeBadge(context, componentName, i2);
            return;
        }
        Uri parse = Uri.parse(f18428a);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(parse, f18429b, "package=?", new String[]{componentName.getPackageName()}, null);
            if (cursor != null) {
                String className = componentName.getClassName();
                boolean z = false;
                while (cursor.moveToNext()) {
                    contentResolver.update(parse, a(componentName, i2, false), "_id=?", new String[]{String.valueOf(cursor.getInt(0))});
                    if (className.equals(cursor.getString(cursor.getColumnIndex("class")))) {
                        z = true;
                    }
                }
                if (!z) {
                    contentResolver.insert(parse, a(componentName, i2, true));
                }
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
            } finally {
                com.ss.android.newmedia.redbadge.util.b.close(cursor);
            }
        }
    }

    private ContentValues a(ComponentName componentName, int i2, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(i2));
        return contentValues;
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sec.android.app.launcher", "com.sec.android.app.twlauncher");
    }
}
