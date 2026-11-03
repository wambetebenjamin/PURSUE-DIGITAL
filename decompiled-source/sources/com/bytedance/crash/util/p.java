package com.bytedance.crash.util;

import java.io.Closeable;
/* compiled from: IoUtil.java */
/* loaded from: classes2.dex */
public final class p {
    private p() {
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
