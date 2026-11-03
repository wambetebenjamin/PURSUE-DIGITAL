package com.bytedance.sdk.account.save.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ss.android.e;
/* compiled from: DBHelper.java */
/* loaded from: classes2.dex */
public class b extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12998a = "DBHelper";

    public b(Context context) {
        super(context, "account_db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS login_info (time integer primary key , type integer , info text , uid integer , avatar_url text , screen_name text , platform_avatar_url text, platform_screen_name text,sec_uid text,ext text);");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 2) {
            sQLiteDatabase.execSQL("alter TABLE login_info add COLUMN sec_uid text ");
            sQLiteDatabase.execSQL("alter TABLE login_info add COLUMN ext text ");
            e.b("DBHelper", "update db ");
        }
    }
}
