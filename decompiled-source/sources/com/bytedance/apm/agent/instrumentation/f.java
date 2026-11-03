package com.bytedance.apm.agent.instrumentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CancellationSignal;
import java.util.Arrays;
/* compiled from: SQLiteInstrumentation.java */
/* loaded from: classes.dex */
public class f {
    public static final String SQLITE = "sqlite";

    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "rawQuery", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
        com.bytedance.apm.agent.tracing.e.exitMethod(str);
        return rawQuery;
    }

    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, CancellationSignal cancellationSignal) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "rawQuery", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, strArr);
        com.bytedance.apm.agent.tracing.e.exitMethod(str);
        return rawQuery;
    }

    private static String a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return "distinct: " + z + " table: " + str + " columns: " + Arrays.toString(strArr) + " selection: " + str2 + " selectionArgs: " + Arrays.toString(strArr2);
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "query", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor query = sQLiteDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
        com.bytedance.apm.agent.tracing.e.exitMethod(a(z, str, strArr, str2, strArr2, str3, str4, str5, str6));
        return query;
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "query", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5);
        com.bytedance.apm.agent.tracing.e.exitMethod(a(false, str, strArr, str2, strArr2, str3, str4, str5, null));
        return query;
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "query", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor query = sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        com.bytedance.apm.agent.tracing.e.exitMethod(a(false, str, strArr, str2, strArr2, str3, str4, str5, str6));
        return query;
    }

    public static Cursor queryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, CancellationSignal cancellationSignal) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "queryWithFactory", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, z, str, strArr, str2, strArr2, str3, str4, str5, str6, cancellationSignal);
        com.bytedance.apm.agent.tracing.e.exitMethod(a(false, str, strArr, str2, strArr2, str3, str4, str5, str6));
        return queryWithFactory;
    }

    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, String str, String[] strArr, String str2) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "rawQueryWithFactory", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2);
        com.bytedance.apm.agent.tracing.e.exitMethod(str + Arrays.toString(strArr));
        return rawQueryWithFactory;
    }

    public static Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, SQLiteDatabase.CursorFactory cursorFactory, String str, String[] strArr, String str2, CancellationSignal cancellationSignal) {
        com.bytedance.apm.agent.tracing.e.enterMethod(SQLITE, "rawQueryWithFactory", com.bytedance.apm.constant.a.TRACE_TYPE_DB);
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, str2, cancellationSignal);
        com.bytedance.apm.agent.tracing.e.exitMethod(str + Arrays.toString(strArr));
        return rawQueryWithFactory;
    }
}
