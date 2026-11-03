package com.bytedance.push.monitor.multiprocess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bytedance.push.utils.h;
import java.util.ArrayList;
import java.util.List;
/* compiled from: MultiProcessMonitorDBHelper.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static final String f11499a = "ss_push_monitor.db";

    /* renamed from: b  reason: collision with root package name */
    static final int f11500b = 1;

    /* renamed from: c  reason: collision with root package name */
    static final String f11501c = "event";

    /* renamed from: d  reason: collision with root package name */
    static final String f11502d = "_id";

    /* renamed from: e  reason: collision with root package name */
    static final String f11503e = "category";

    /* renamed from: g  reason: collision with root package name */
    static final String f11505g = "metric";

    /* renamed from: h  reason: collision with root package name */
    static final String f11506h = "extra";

    /* renamed from: j  reason: collision with root package name */
    private static final String f11508j = "MultiProcessMonitorDBHelper";
    private static final String l = "_id = ?";
    private static volatile c n;
    private final String k = "push_monitor_db.lock";
    private SQLiteDatabase o;
    private Context p;
    private a q;

    /* renamed from: f  reason: collision with root package name */
    static final String f11504f = "service_name";

    /* renamed from: i  reason: collision with root package name */
    static final String[] f11507i = {"_id", f11504f, "category", "metric", "extra"};
    private static final Object m = new Object();

    public static c a(Context context) {
        if (n == null) {
            synchronized (m) {
                if (n == null) {
                    n = new c(context.getApplicationContext());
                }
            }
        }
        return n;
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

    private c(Context context) {
        this.p = context;
        this.q = new a(context);
    }

    private synchronized SQLiteDatabase b() {
        if (this.o == null) {
            synchronized (this) {
                if (this.o == null) {
                    h.d(f11508j, "lock file for open db");
                    com.bytedance.push.helper.a.a("push_monitor_db.lock").a(this.p);
                    this.o = this.q.getWritableDatabase();
                    h.d(f11508j, "success lock file and open db");
                }
            }
        }
        return this.o;
    }

    public synchronized void a() {
        try {
            SQLiteDatabase sQLiteDatabase = this.o;
            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                h.d(f11508j, "close db and  unlock file");
                this.o.close();
                this.o = null;
                com.bytedance.push.helper.a.a("push_monitor_db.lock").b();
                h.d(f11508j, "success close db and unlock file");
            }
        } finally {
        }
    }

    public synchronized long a(b bVar) {
        SQLiteDatabase b2 = b();
        if (b2 != null && b2.isOpen() && bVar != null) {
            long insert = b2.insert("event", null, bVar.b());
            a();
            return insert;
        }
        h.w(f11508j, "[insertEvent] db not establish and open");
        a();
        return -1L;
    }

    public synchronized boolean a(long j2) {
        SQLiteDatabase b2 = b();
        if (b2 != null && b2.isOpen()) {
            boolean z = b2.delete("event", l, new String[]{String.valueOf(j2)}) > 0;
            a();
            return z;
        }
        h.w(f11508j, "[deleteEvent] db not establish and open");
        a();
        return false;
    }

    public synchronized List<b> a(int i2) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase b2 = b();
        if (b2 != null && b2.isOpen()) {
            Cursor query = b2.query("event", f11507i, null, null, null, null, "_id ASC", "" + i2);
            while (query.moveToNext()) {
                arrayList.add(new b(query));
            }
            a(query);
            a();
            return arrayList;
        }
        h.w(f11508j, "[deleteEvent] db not establish and open");
        a();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MultiProcessMonitorDBHelper.java */
    /* loaded from: classes2.dex */
    public static class a extends SQLiteOpenHelper {

        /* renamed from: a  reason: collision with root package name */
        static final String f11509a = "CREATE TABLE event ( _id INTEGER PRIMARY KEY AUTOINCREMENT, service_name VARCHAR, category VARCHAR, metric VARCHAR, extra VARCHAR )";

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        }

        public a(Context context) {
            super(context, c.f11499a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(f11509a);
            } catch (Exception e2) {
                h.e(c.f11508j, "create db exception " + e2);
            }
        }
    }
}
