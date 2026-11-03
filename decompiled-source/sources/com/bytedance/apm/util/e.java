package com.bytedance.apm.util;

import android.database.Cursor;
/* compiled from: DbUtils.java */
/* loaded from: classes.dex */
public class e {
    public static void safeCloseCursor(Cursor cursor) {
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
}
