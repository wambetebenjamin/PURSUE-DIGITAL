package com.bytedance.applog.compress;

import java.io.Closeable;
/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class e {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
