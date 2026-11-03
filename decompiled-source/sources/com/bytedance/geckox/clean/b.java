package com.bytedance.geckox.clean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.geckox.statistic.model.StatisticModel;
import com.bytedance.geckox.utils.d;
import java.util.ArrayList;
import java.util.List;
/* compiled from: AbandonChannelSQLiteHelper.java */
/* loaded from: classes2.dex */
class b extends SQLiteOpenHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8963a = "geckox_clean_statistic";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8964b = "access_key";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8965c = "channel";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8966d = "clean_type";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8967e = "status";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8968f = "pkg_id";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8969g = "err_code";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8970h = "err_msg";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8971i = "clean_strategy";

    /* renamed from: j  reason: collision with root package name */
    private static final String f8972j = "clean_duration";
    private static volatile b k;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }

    private b(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2) {
        super(context, str, cursorFactory, i2);
    }

    public static b a(Context context) {
        if (k == null) {
            synchronized (b.class) {
                if (k == null) {
                    k = new b(context.getApplicationContext(), f8963a + com.bytedance.geckox.utils.a.c(context) + ".db", null, 1);
                }
            }
        }
        return k;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists geckox_clean_statistic (id integer primary key autoincrement,access_key text,channel text,clean_type integer,status integer,pkg_id integer,err_code integer,clean_strategy integer,clean_duration integer,err_msg text)");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, int i2, int i3, long j2, int i4, String str3, long j3, int i5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(f8964b, str);
        contentValues.put("channel", str2);
        contentValues.put(f8966d, Integer.valueOf(i2));
        contentValues.put("status", Integer.valueOf(i3));
        contentValues.put(f8968f, Long.valueOf(j2));
        contentValues.put(f8969g, Integer.valueOf(i4));
        contentValues.put(f8970h, str3);
        contentValues.put(f8972j, Long.valueOf(j3));
        contentValues.put(f8971i, Integer.valueOf(i5));
        try {
            getWritableDatabase().insert(f8963a, null, contentValues);
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.w("clean-channel", "insert failed", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<StatisticModel.PackageStatisticModel> a() {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor = getWritableDatabase().query(f8963a, null, null, null, null, null, null);
        } catch (Exception e2) {
            e = e2;
        }
        if (cursor == null) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            try {
                String string = cursor.getString(cursor.getColumnIndex(f8964b));
                String string2 = cursor.getString(cursor.getColumnIndex("channel"));
                int i2 = cursor.getInt(cursor.getColumnIndex(f8966d));
                int i3 = cursor.getInt(cursor.getColumnIndex("status"));
                int i4 = cursor.getInt(cursor.getColumnIndex(f8969g));
                String string3 = cursor.getString(cursor.getColumnIndex(f8970h));
                int i5 = cursor.getInt(cursor.getColumnIndex(f8971i));
                long j2 = cursor.getLong(cursor.getColumnIndex(f8972j));
                StatisticModel.PackageStatisticModel packageStatisticModel = new StatisticModel.PackageStatisticModel();
                packageStatisticModel.accessKey = string;
                packageStatisticModel.channel = string2;
                packageStatisticModel.statsType = Integer.valueOf(i3);
                packageStatisticModel.id = Long.valueOf(cursor.getInt(cursor.getColumnIndex(f8968f)));
                packageStatisticModel.errCode = i4 == 0 ? null : i4 + "";
                packageStatisticModel.errMsg = string3;
                packageStatisticModel.cleanType = Integer.valueOf(i2);
                packageStatisticModel.cleanDuration = Long.valueOf(j2);
                packageStatisticModel.cleanStrategy = Integer.valueOf(i5);
                arrayList.add(packageStatisticModel);
            } catch (Exception e3) {
                e = e3;
                cursor2 = cursor;
                com.bytedance.geckox.logger.b.w("clean-channel", "get all statistic failed!", e);
                cursor = cursor2;
                d.a(cursor);
                return arrayList;
            }
        }
        getWritableDatabase().delete(f8963a, null, null);
        d.a(cursor);
        return arrayList;
    }
}
