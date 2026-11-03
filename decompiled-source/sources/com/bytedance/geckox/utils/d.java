package com.bytedance.geckox.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.Closeable;
import java.io.IOException;
/* compiled from: CloseableUtils.java */
/* loaded from: classes2.dex */
public class d {
    public static boolean close(Closeable closeable) {
        if (closeable == null) {
            return true;
        }
        try {
            closeable.close();
            return true;
        } catch (IOException e2) {
            com.bytedance.geckox.logger.b.e(com.bytedance.geckox.d.f8984a, "close:", e2);
            return false;
        }
    }

    public static boolean a(Cursor cursor) {
        if (cursor == null) {
            return true;
        }
        try {
            cursor.close();
            return true;
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.e(com.bytedance.geckox.d.f8984a, "close:", e2);
            return false;
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return true;
        }
        try {
            sQLiteDatabase.close();
            return true;
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.e(com.bytedance.geckox.d.f8984a, "close:", e2);
            return false;
        }
    }
}
