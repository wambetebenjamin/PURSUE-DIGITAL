package com.ss.android.message.log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.common.utility.o;
import com.bytedance.push.helper.d;
import com.bytedance.push.utils.h;
/* compiled from: DBHelper.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final String f18318a = "ss_push_log.db";

    /* renamed from: b  reason: collision with root package name */
    static final int f18319b = 1;

    /* renamed from: c  reason: collision with root package name */
    static final String f18320c = "event";

    /* renamed from: d  reason: collision with root package name */
    static final String f18321d = "_id";

    /* renamed from: e  reason: collision with root package name */
    static final String f18322e = "category";

    /* renamed from: f  reason: collision with root package name */
    static final String f18323f = "tag";

    /* renamed from: g  reason: collision with root package name */
    static final String f18324g = "label";

    /* renamed from: h  reason: collision with root package name */
    static final String f18325h = "value";

    /* renamed from: i  reason: collision with root package name */
    static final String f18326i = "ext_value";

    /* renamed from: j  reason: collision with root package name */
    static final String f18327j = "ext_json";
    private static final String l = "PushLog";
    private static final String m = "_id = ?";
    private static a o;
    private SQLiteDatabase p;
    static final String[] k = {"_id", "category", "tag", "label", "value", "ext_value", "ext_json"};
    private static final Object n = new Object();

    public static a getInstance(Context context) {
        synchronized (n) {
            if (o == null) {
                o = new a(context.getApplicationContext());
            }
        }
        return o;
    }

    public static void closeDB() {
        synchronized (n) {
            a aVar = o;
            if (aVar != null) {
                aVar.a();
            }
            o = null;
        }
    }

    protected static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    protected static void a(Cursor cursor, SQLiteDatabase sQLiteDatabase) {
        a(cursor);
        if (sQLiteDatabase != null) {
            try {
                if (sQLiteDatabase.inTransaction()) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (Exception unused) {
            }
        }
    }

    private a(Context context) {
        try {
            this.p = new C0331a(context).getWritableDatabase();
        } catch (Throwable th) {
            h.e("error when init DatabaseHelper:" + th.getLocalizedMessage());
            d.a(th, "error when init com.ss.android.message.log.DBHelper.DBHelper");
        }
    }

    private synchronized void a() {
        try {
            SQLiteDatabase sQLiteDatabase = this.p;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                this.p.close();
                this.p = null;
            }
        } finally {
        }
    }

    public synchronized long insertEvent(b bVar) {
        SQLiteDatabase sQLiteDatabase = this.p;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("category", bVar.category);
            contentValues.put("tag", bVar.tag);
            if (!o.isEmpty(bVar.label)) {
                contentValues.put("label", bVar.label);
            }
            contentValues.put("value", Long.valueOf(bVar.value));
            contentValues.put("ext_value", Long.valueOf(bVar.ext_value));
            if (!o.isEmpty(bVar.ext_json)) {
                contentValues.put("ext_json", bVar.ext_json);
            }
            return this.p.insert("event", null, contentValues);
        }
        h.d(l, "db not establish and open");
        return -1L;
    }

    public synchronized boolean deleteEvent(long j2) {
        SQLiteDatabase sQLiteDatabase = this.p;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            return this.p.delete("event", m, new String[]{String.valueOf(j2)}) > 0;
        }
        h.d(l, "db not establish and open");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x0101 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0103 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized org.json.JSONArray getEvents(long r21, int r23) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.message.log.a.getEvents(long, int):org.json.JSONArray");
    }

    /* compiled from: DBHelper.java */
    /* renamed from: com.ss.android.message.log.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    private static class C0331a extends SQLiteOpenHelper {

        /* renamed from: a  reason: collision with root package name */
        static final String f18328a = "CREATE TABLE event ( _id INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR, tag VARCHAR, label VARCHAR, value INTEGER, ext_value INTEGER, ext_json TEXT )";

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        }

        public C0331a(Context context) {
            super(context, a.f18318a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(f18328a);
            } catch (Exception e2) {
                h.e(a.l, "create db exception " + e2);
            }
        }
    }
}
