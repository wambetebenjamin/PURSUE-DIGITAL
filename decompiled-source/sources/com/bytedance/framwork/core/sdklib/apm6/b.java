package com.bytedance.framwork.core.sdklib.apm6;

import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
/* compiled from: DiskEnvironmentHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8587a = "apm6_sdk";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8588b = "flush";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8589c = "persistent";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8590d = "log";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8591e = "header";

    /* renamed from: f  reason: collision with root package name */
    private static File f8592f;

    /* renamed from: g  reason: collision with root package name */
    private static File f8593g;

    /* renamed from: h  reason: collision with root package name */
    private static File f8594h;

    /* renamed from: i  reason: collision with root package name */
    private static File f8595i;

    /* renamed from: j  reason: collision with root package name */
    private static File f8596j;
    private static File k;

    public static synchronized File a() {
        File file;
        synchronized (b.class) {
            if (f8595i == null) {
                File file2 = new File(l.a().getFilesDir(), f8587a);
                f8595i = file2;
                if (!file2.exists()) {
                    f8595i.mkdirs();
                }
            }
            file = f8595i;
        }
        return file;
    }

    public static synchronized File b() {
        File file;
        synchronized (b.class) {
            if (f8594h == null) {
                File file2 = new File(a(), f8589c);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                f8594h = file2;
                if (l.e()) {
                    com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "prepare PersistentDirectory success. name=" + f8594h);
                }
            }
            file = f8594h;
        }
        return file;
    }

    public static synchronized File c() {
        File file;
        synchronized (b.class) {
            if (f8592f == null) {
                try {
                    File file2 = new File(b(), com.ss.android.common.util.d.getCurProcessName(l.a()).replace(c.a.DOT, "_").replace(CertificateUtil.DELIMITER, "-") + ".bin");
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    f8592f = file2;
                    if (l.e()) {
                        com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "prepare PersistentFile success. fileName=" + f8592f);
                    }
                } catch (Exception e2) {
                    com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "prepare PersistentFile fail.", e2);
                }
            }
            file = f8592f;
        }
        return file;
    }

    public static synchronized File d() {
        File file;
        synchronized (b.class) {
            if (f8593g == null) {
                File file2 = new File(a(), f8588b);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                f8593g = file2;
                if (l.e()) {
                    com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "prepare FlushDirectory success. name=" + f8593g);
                }
            }
            file = f8593g;
        }
        return file;
    }

    public static synchronized File e() {
        File file;
        synchronized (b.class) {
            if (f8596j == null) {
                File file2 = new File(a(), f8590d);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                f8596j = file2;
            }
            file = f8596j;
        }
        return file;
    }

    public static synchronized File f() {
        File file;
        synchronized (b.class) {
            try {
                if (k == null) {
                    File file2 = new File(a(), "header");
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    k = file2;
                }
            } catch (Exception e2) {
                com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "header", e2);
            }
            file = k;
        }
        return file;
    }

    public static File g() {
        return new File(a(), "file.lock");
    }
}
