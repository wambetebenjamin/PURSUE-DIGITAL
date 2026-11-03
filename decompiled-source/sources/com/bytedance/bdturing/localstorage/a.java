package com.bytedance.bdturing.localstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DbHelper.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¨\u0006\u000f"}, d2 = {"Lcom/bytedance/bdturing/localstorage/DbHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onDowngrade", "oldVersion", "", "newVersion", "onUpgrade", "Companion", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class a extends SQLiteOpenHelper {
    public static final String COL_CONTENT = "content";
    public static final String COL_ID = "id";
    public static final String COL_KEY = "primary_key";
    public static final String COL_TIME_STAMP = "time_stamp";
    public static final C0051a Companion = new C0051a(null);
    public static final String DATABASE_NAME = "verifystorage.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_H5_STORAGE = "h5_storage";

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase db, int i2, int i3) {
        Intrinsics.checkNotNullParameter(db, "db");
    }

    public a(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("CREATE TABLE IF NOT EXISTS h5_storage (\n    id INTEGER PRIMARY KEY AUTOINCREMENT,\n    primary_key TEXT,\n    time_stamp LONG, \n    content TEXT\n    )");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int i2, int i3) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("DROP TABLE h5_storage");
        onCreate(db);
    }

    /* compiled from: DbHelper.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/bytedance/bdturing/localstorage/DbHelper$Companion;", "", "()V", "COL_CONTENT", "", "COL_ID", "COL_KEY", "COL_TIME_STAMP", "DATABASE_NAME", "DATABASE_VERSION", "", "TABLE_H5_STORAGE", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* renamed from: com.bytedance.bdturing.localstorage.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0051a {
        public /* synthetic */ C0051a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C0051a() {
        }
    }
}
