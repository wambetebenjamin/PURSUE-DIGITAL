package com.bytedance.applog.adaptor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* compiled from: EmptyDbOpenHelper.java */
/* loaded from: classes.dex */
class a extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private volatile int f2437a;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, Integer.MAX_VALUE);
        this.f2437a = Integer.MAX_VALUE;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        this.f2437a = i2;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        this.f2437a = i2;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        sQLiteDatabase.setVersion(this.f2437a);
    }
}
