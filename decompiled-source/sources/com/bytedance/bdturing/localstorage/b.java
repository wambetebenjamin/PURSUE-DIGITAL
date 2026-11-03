package com.bytedance.bdturing.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.bytedance.bdturing.h;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DbManager.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004J\u0018\u0010\"\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002J\u0010\u0010$\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006%"}, d2 = {"Lcom/bytedance/bdturing/localstorage/DbManager;", "", "()V", "KEY_SEAL_EVENT", "", "KEY_SETTINGS", "KEY_TURING_EVENT", "TAG", "instance", "getInstance$annotations", "getInstance", "()Lcom/bytedance/bdturing/localstorage/DbManager;", "mOpenHelper", "Lcom/bytedance/bdturing/localstorage/DbHelper;", "value", "settingsStr", "getSettingsStr", "()Ljava/lang/String;", "setSettingsStr", "(Ljava/lang/String;)V", "closeDB", "", "delete", "", "id", "getDb", "Landroid/database/sqlite/SQLiteDatabase;", "init", "context", "Landroid/content/Context;", "insert", "", "key", "content", "insertOrUpdate", "query", "queryAndDelete", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class b {
    public static final b INSTANCE = new b();
    public static final String KEY_SEAL_EVENT = "seal_event";
    public static final String KEY_SETTINGS = "settings";
    public static final String KEY_TURING_EVENT = "turing_event";

    /* renamed from: a  reason: collision with root package name */
    private static final String f4224a = "DbManager";

    /* renamed from: d  reason: collision with root package name */
    private static a f4225d;

    @JvmStatic
    public static /* synthetic */ void b() {
    }

    private b() {
    }

    public static final b getInstance() {
        return INSTANCE;
    }

    public final void setSettingsStr(String str) {
        if (str != null) {
            a(KEY_SETTINGS, str);
        }
    }

    public final String getSettingsStr() {
        return a(KEY_SETTINGS);
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        synchronized (this) {
            f4225d = new a(context);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final long insert(String key, String content) {
        long longValue;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(content, "content");
        synchronized (this) {
            h.i(f4224a, Intrinsics.stringPlus("insert: ", key));
            h.d(f4224a, Intrinsics.stringPlus("content: ", content));
            SQLiteDatabase a2 = INSTANCE.a();
            Long l = null;
            if (a2 != null) {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(a.COL_KEY, key);
                    contentValues.put(a.COL_TIME_STAMP, Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("content", content);
                    Unit unit = Unit.INSTANCE;
                    l = Long.valueOf(a2.insert(a.TABLE_H5_STORAGE, null, contentValues));
                } catch (SQLException unused) {
                    h.e(f4224a, "insert fail!");
                }
            }
            longValue = l == null ? -1L : l.longValue();
        }
        return longValue;
    }

    public final void closeDB() {
        synchronized (this) {
            try {
                a aVar = f4225d;
                if (aVar != null) {
                    aVar.close();
                }
                f4225d = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final SQLiteDatabase a() {
        try {
            a aVar = f4225d;
            if (aVar == null) {
                return null;
            }
            return aVar.getWritableDatabase();
        } catch (SQLiteException e2) {
            h.printException(e2);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0078 A[Catch: all -> 0x007e, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:10:0x0056, B:14:0x0061, B:21:0x0078, B:7:0x0040, B:17:0x006d), top: B:32:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long a(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "DbManager"
            java.lang.String r1 = "insertOrUpdate: "
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r9)     // Catch: java.lang.Throwable -> L7e
            com.bytedance.bdturing.h.i(r0, r1)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r0 = "DbManager"
            java.lang.String r1 = "content: "
            java.lang.String r1 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r10)     // Catch: java.lang.Throwable -> L7e
            com.bytedance.bdturing.h.d(r0, r1)     // Catch: java.lang.Throwable -> L7e
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L7e
            r0.<init>()     // Catch: java.lang.Throwable -> L7e
            java.lang.String r1 = "primary_key"
            r0.put(r1, r9)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r1 = "time_stamp"
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L7e
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L7e
            r0.put(r1, r2)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r1 = "content"
            r0.put(r1, r10)     // Catch: java.lang.Throwable -> L7e
            com.bytedance.bdturing.localstorage.b r10 = com.bytedance.bdturing.localstorage.b.INSTANCE     // Catch: java.lang.Throwable -> L7e
            android.database.sqlite.SQLiteDatabase r10 = r10.a()     // Catch: java.lang.Throwable -> L7e
            r1 = 0
            r3 = 0
            if (r10 != 0) goto L40
            r9 = r3
            goto L53
        L40:
            java.lang.String r4 = "h5_storage"
            java.lang.String r5 = "primary_key=?"
            r6 = 1
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch: android.database.SQLException -> L6d java.lang.Throwable -> L7e
            r7 = 0
            r6[r7] = r9     // Catch: android.database.SQLException -> L6d java.lang.Throwable -> L7e
            int r9 = r10.update(r4, r0, r5, r6)     // Catch: android.database.SQLException -> L6d java.lang.Throwable -> L7e
            long r4 = (long) r9     // Catch: android.database.SQLException -> L6d java.lang.Throwable -> L7e
            java.lang.Long r9 = java.lang.Long.valueOf(r4)     // Catch: android.database.SQLException -> L6d java.lang.Throwable -> L7e
        L53:
            if (r9 != 0) goto L56
            goto L75
        L56:
            long r4 = r9.longValue()     // Catch: android.database.SQLException -> L6c java.lang.Throwable -> L7e
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 != 0) goto L75
            if (r10 != 0) goto L61
            goto L74
        L61:
            java.lang.String r4 = "h5_storage"
            long r3 = r10.insert(r4, r3, r0)     // Catch: android.database.SQLException -> L6c java.lang.Throwable -> L7e
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: android.database.SQLException -> L6c java.lang.Throwable -> L7e
            goto L74
        L6c:
            r3 = r9
        L6d:
            java.lang.String r9 = "DbManager"
            java.lang.String r10 = "insertOrUpdate fail!"
            com.bytedance.bdturing.h.e(r9, r10)     // Catch: java.lang.Throwable -> L7e
        L74:
            r9 = r3
        L75:
            if (r9 != 0) goto L78
            goto L7c
        L78:
            long r1 = r9.longValue()     // Catch: java.lang.Throwable -> L7e
        L7c:
            monitor-exit(r8)
            return r1
        L7e:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdturing.localstorage.b.a(java.lang.String, java.lang.String):long");
    }

    private final String a(String str) {
        String string;
        synchronized (this) {
            h.d(f4224a, "query");
            SQLiteDatabase a2 = INSTANCE.a();
            Cursor query = a2 == null ? null : a2.query(a.TABLE_H5_STORAGE, new String[]{"content"}, "primary_key=?", new String[]{str}, null, null, null, "1");
            string = query != null && query.moveToFirst() ? query.getString(0) : null;
            if (query != null) {
                query.close();
            }
        }
        return string;
    }

    public final String queryAndDelete(String key) {
        String str;
        Intrinsics.checkNotNullParameter(key, "key");
        synchronized (this) {
            h.d(f4224a, "queryAndDelete");
            b bVar = INSTANCE;
            SQLiteDatabase a2 = bVar.a();
            str = null;
            Cursor query = a2 == null ? null : a2.query(a.TABLE_H5_STORAGE, new String[]{"id", "content"}, "primary_key=?", new String[]{key}, null, null, null, "1");
            if (query != null && query.moveToFirst()) {
                str = query.getString(1);
                bVar.a(query.getInt(0));
            }
            if (query != null) {
                query.close();
            }
        }
        return str;
    }

    private final int a(int i2) {
        int i3;
        synchronized (this) {
            h.i(f4224a, Intrinsics.stringPlus("delete: ", Integer.valueOf(i2)));
            SQLiteDatabase a2 = INSTANCE.a();
            i3 = 0;
            Integer num = null;
            if (a2 != null) {
                try {
                    num = Integer.valueOf(a2.delete(a.TABLE_H5_STORAGE, "id=?", new String[]{String.valueOf(i2)}));
                } catch (SQLException unused) {
                    h.e(f4224a, "delete fail!");
                }
            }
            if (num != null) {
                i3 = num.intValue();
            }
        }
        return i3;
    }
}
