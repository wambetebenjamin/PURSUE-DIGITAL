package com.ss.android.newmedia.redbadge.util;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;
/* compiled from: CloseHelper.java */
/* loaded from: classes9.dex */
public class b {
    public static void close(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
