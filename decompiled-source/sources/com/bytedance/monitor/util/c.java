package com.bytedance.monitor.util;

import java.io.Closeable;
import java.io.File;
import java.util.zip.ZipFile;
/* compiled from: IoUtil.java */
/* loaded from: classes2.dex */
public class c {
    private c() {
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void safeClose(ZipFile zipFile) {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (Throwable unused) {
        }
    }

    public static boolean mkdir(String str) {
        return new File(str).mkdirs();
    }

    public static void setPermissions(String str, int i2) {
        com.bytedance.monitor.util.jni.a.callStaticMethod("android.os.FileUtils", "setPermissions", str, Integer.valueOf(i2), -1, -1);
    }
}
