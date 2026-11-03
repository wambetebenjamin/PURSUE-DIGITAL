package com.bytedance.apm6.consumer.slardar;

import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.util.UUID;
/* compiled from: DiskEnvironmentHelper.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1746a = "apm6";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1747b = "flush";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1748c = "persistent";

    /* renamed from: d  reason: collision with root package name */
    private static final String f1749d = "child_process_persistent";

    /* renamed from: e  reason: collision with root package name */
    private static File f1750e;

    /* renamed from: f  reason: collision with root package name */
    private static File f1751f;

    /* renamed from: g  reason: collision with root package name */
    private static File f1752g;

    /* renamed from: h  reason: collision with root package name */
    private static File f1753h;

    public static synchronized File getWorkingDirectory() {
        File file;
        synchronized (c.class) {
            if (f1753h == null) {
                File file2 = new File(com.bytedance.apm6.foundation.context.a.getContext().getFilesDir(), f1746a);
                f1753h = file2;
                if (!file2.exists()) {
                    f1753h.mkdirs();
                }
            }
            file = f1753h;
        }
        return file;
    }

    public static synchronized File getPersistentDirectory() {
        File file;
        File file2;
        synchronized (c.class) {
            if (f1752g == null) {
                if (com.bytedance.apm6.foundation.context.a.isMainProcess()) {
                    file2 = new File(getWorkingDirectory(), f1748c);
                } else {
                    file2 = new File(getWorkingDirectory(), f1749d);
                }
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                f1752g = file2;
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    com.bytedance.apm6.util.log.b.d(b.TAG, "prepare PersistentDirectory success. name=" + f1752g);
                }
            }
            file = f1752g;
        }
        return file;
    }

    public static synchronized File getPersistentFile() {
        File file;
        synchronized (c.class) {
            if (f1750e == null) {
                try {
                    String str = com.bytedance.apm6.foundation.context.a.getCurrentProcessName().replace(c.a.DOT, "_").replace(CertificateUtil.DELIMITER, "-") + ".bin";
                    if (!com.bytedance.apm6.foundation.context.a.isMainProcess()) {
                        str = com.bytedance.apm6.foundation.context.a.getInitTimeStamp() + "_" + str;
                    }
                    File file2 = new File(getPersistentDirectory(), str);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    f1750e = file2;
                    if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                        com.bytedance.apm6.util.log.b.d(b.TAG, "prepare PersistentFile success. fileName=" + f1750e);
                    }
                } catch (Exception e2) {
                    com.bytedance.apm6.util.log.b.e(b.TAG, "prepare PersistentFile fail.", e2);
                }
            }
            file = f1750e;
        }
        return file;
    }

    public static synchronized File getFlushDirectory() {
        File file;
        synchronized (c.class) {
            if (f1751f == null) {
                File file2 = new File(getWorkingDirectory(), f1747b);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                f1751f = file2;
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    com.bytedance.apm6.util.log.b.d(b.TAG, "prepare FlushDirectory success. name=" + f1751f);
                }
            }
            file = f1751f;
        }
        return file;
    }

    public static File getSubProcessPersistentDirectory() {
        return new File(getWorkingDirectory(), f1749d);
    }

    public static String generateFlushFileName() {
        return System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + ".log";
    }
}
