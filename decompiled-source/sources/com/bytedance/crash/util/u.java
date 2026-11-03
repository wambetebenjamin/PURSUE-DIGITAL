package com.bytedance.crash.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
/* compiled from: SerializeFactory.java */
/* loaded from: classes2.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7314a = ".bk";

    public static Object a(File file) {
        FileInputStream fileInputStream;
        try {
            if (!file.exists()) {
                File file2 = new File(file.getAbsolutePath() + f7314a);
                if (!file2.exists()) {
                    return null;
                }
                file2.renameTo(file);
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        Object readObject = new ObjectInputStream(fileInputStream).readObject();
                        k.a(fileInputStream);
                        return readObject;
                    } catch (Exception e2) {
                        e = e2;
                        m.deleteFile(file);
                        com.bytedance.crash.diagnose.b.a(e);
                        k.a(fileInputStream);
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    k.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                k.a(fileInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            com.bytedance.crash.diagnose.b.a(th3);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r4, java.lang.Object r5) {
        /*
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L7b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b
            r1.<init>()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = r4.getAbsolutePath()     // Catch: java.lang.Throwable -> L7b
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = ".bk"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7b
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L7b
            boolean r1 = r4.isDirectory()     // Catch: java.lang.Throwable -> L7b
            if (r1 == 0) goto L26
            com.bytedance.crash.util.m.deleteFile(r4)     // Catch: java.lang.Throwable -> L7b
            goto L38
        L26:
            boolean r1 = r4.exists()     // Catch: java.lang.Throwable -> L7b
            if (r1 == 0) goto L38
            boolean r1 = r0.exists()     // Catch: java.lang.Throwable -> L7b
            if (r1 == 0) goto L35
            r0.delete()     // Catch: java.lang.Throwable -> L7b
        L35:
            r4.renameTo(r0)     // Catch: java.lang.Throwable -> L7b
        L38:
            r1 = 0
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L54
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r2.writeObject(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r1 = 1
            com.bytedance.crash.util.k.a(r3)     // Catch: java.lang.Throwable -> L7b
            goto L5e
        L4c:
            r4 = move-exception
            r2 = r3
            goto L77
        L4f:
            r5 = move-exception
            r2 = r3
            goto L55
        L52:
            r4 = move-exception
            goto L77
        L54:
            r5 = move-exception
        L55:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L52
            com.bytedance.crash.diagnose.b.a(r5)     // Catch: java.lang.Throwable -> L52
            com.bytedance.crash.util.k.a(r2)     // Catch: java.lang.Throwable -> L7b
        L5e:
            boolean r5 = r0.exists()     // Catch: java.lang.Throwable -> L7b
            if (r5 == 0) goto L7f
            if (r1 == 0) goto L6a
            com.bytedance.crash.util.m.deleteFile(r0)     // Catch: java.lang.Throwable -> L7b
            goto L7f
        L6a:
            boolean r5 = r4.exists()     // Catch: java.lang.Throwable -> L7b
            if (r5 == 0) goto L73
            com.bytedance.crash.util.m.deleteFile(r4)     // Catch: java.lang.Throwable -> L7b
        L73:
            r0.renameTo(r4)     // Catch: java.lang.Throwable -> L7b
            goto L7f
        L77:
            com.bytedance.crash.util.k.a(r2)     // Catch: java.lang.Throwable -> L7b
            throw r4     // Catch: java.lang.Throwable -> L7b
        L7b:
            r4 = move-exception
            com.bytedance.crash.diagnose.b.a(r4)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.util.u.a(java.io.File, java.lang.Object):void");
    }
}
