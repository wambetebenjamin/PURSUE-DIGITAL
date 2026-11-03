package com.bytedance.geckox.utils;

import com.bytedance.geckox.statistic.model.EventMessageModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* compiled from: UnZipUtils.java */
/* loaded from: classes2.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private static byte[] f9377a = {80, 75, 3, 4};

    /* renamed from: b  reason: collision with root package name */
    private static byte[] f9378b = {80, 75, 5, 6};

    public static void a(File file, String str, String str2) {
        ZipInputStream zipInputStream;
        FileOutputStream fileOutputStream;
        String str3;
        boolean z;
        boolean z2 = true;
        String str4 = com.bytedance.geckox.d.f8984a;
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "file+ZipInputStream unzip,dir:" + str + ",channel:" + str2);
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            ZipInputStream zipInputStream2 = new ZipInputStream(new FileInputStream(file));
            boolean z3 = false;
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        if (!z3) {
                            if (!a(file)) {
                                throw new RuntimeException("not zip file，file:" + file.getName() + " channel:" + str2);
                            }
                            throw new RuntimeException("unzip file:" + file.getName() + " channel:" + str2);
                        }
                        d.close(zipInputStream2);
                        return;
                    }
                    String name = nextEntry.getName();
                    if (!a(name)) {
                        if (!name.startsWith(str2 + File.separator)) {
                            com.bytedance.geckox.clean.c.a(new File(canonicalPath));
                            throw new RuntimeException("the zip package outermost folder is not named by channel, file:" + file.getName() + " channel:" + str2);
                        } else if (nextEntry.isDirectory()) {
                            File file2 = new File(canonicalPath, name);
                            if (!file2.getCanonicalPath().startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, dir:" + name + ", file:" + file.getAbsolutePath());
                            }
                            file2.mkdirs();
                        } else {
                            String canonicalPath2 = new File(canonicalPath, name).getCanonicalPath();
                            if (!canonicalPath2.startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, file name:" + name + ", file:" + file.getAbsolutePath());
                            }
                            File file3 = new File(canonicalPath2);
                            file3.getParentFile().mkdirs();
                            try {
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                                try {
                                    long a2 = f.a(zipInputStream2, fileOutputStream2);
                                    long size = nextEntry.getSize();
                                    str3 = str4;
                                    long length = file3.exists() ? file3.length() : -1L;
                                    if (size != 0 && a2 != 0 && length > 0) {
                                        z = z2;
                                        d.close(fileOutputStream2);
                                        z2 = z;
                                        z3 = z2;
                                        str4 = str3;
                                    }
                                    String str5 = "channel:" + str2 + ",file :" + name + ",entry size:" + size + ",local file size:" + length + ",stream size:" + a2 + ",pid:" + a.a() + ",thread id:" + Thread.currentThread().getId() + ",space left:" + a.c();
                                    z = true;
                                    com.bytedance.geckox.logger.b.a(str3, "file+ZipInputStream unzip size 0 warning", str5);
                                    EventMessageModel eventMessageModel = new EventMessageModel();
                                    eventMessageModel.setEventType(3);
                                    eventMessageModel.setSubType(31);
                                    eventMessageModel.setErrMsg("unzip size 0 warning," + str5);
                                    eventMessageModel.setExtra("file+ZipInputStream");
                                    com.bytedance.geckox.statistic.e.a(eventMessageModel);
                                    d.close(fileOutputStream2);
                                    z2 = z;
                                    z3 = z2;
                                    str4 = str3;
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream = fileOutputStream2;
                                    d.close(fileOutputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = null;
                            }
                        }
                    }
                    str3 = str4;
                    z = z2;
                    z2 = z;
                    z3 = z2;
                    str4 = str3;
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream = zipInputStream2;
                    d.close(zipInputStream);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            zipInputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.OutputStream, java.io.Closeable, java.io.FileOutputStream] */
    public static void a(InputStream inputStream, String str) {
        ZipInputStream zipInputStream = null;
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            ZipInputStream zipInputStream2 = new ZipInputStream(inputStream);
            boolean z = false;
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        if (!z) {
                            inputStream.reset();
                            if (!a(inputStream)) {
                                throw new RuntimeException("not zip file  channel:");
                            }
                            throw new RuntimeException("unzip file: channel:");
                        }
                        d.close(zipInputStream2);
                        return;
                    }
                    z = true;
                    String name = nextEntry.getName();
                    if (!a(name)) {
                        if (nextEntry.isDirectory()) {
                            File file = new File(canonicalPath, name);
                            if (!file.getCanonicalPath().startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, dir:" + name);
                            }
                            file.mkdirs();
                        } else {
                            String canonicalPath2 = new File(canonicalPath, name).getCanonicalPath();
                            if (!canonicalPath2.startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, file name:" + name);
                            }
                            File file2 = new File(canonicalPath2);
                            file2.getParentFile().mkdirs();
                            try {
                                ?? fileOutputStream = new FileOutputStream(file2);
                                try {
                                    f.a(zipInputStream2, (OutputStream) fileOutputStream);
                                    d.close(fileOutputStream);
                                } catch (Throwable th) {
                                    th = th;
                                    zipInputStream = fileOutputStream;
                                    d.close(zipInputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream = zipInputStream2;
                    d.close(zipInputStream);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static void a(InputStream inputStream, String str, String str2) {
        ZipInputStream zipInputStream;
        FileOutputStream fileOutputStream;
        String str3;
        boolean z;
        boolean z2 = true;
        String str4 = com.bytedance.geckox.d.f8984a;
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "mmap+ZipInputStream unzip,dir:" + str + ",channel:" + str2);
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            ZipInputStream zipInputStream2 = new ZipInputStream(inputStream);
            boolean z3 = false;
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        if (!z3) {
                            inputStream.reset();
                            if (!a(inputStream)) {
                                throw new RuntimeException("not zip file  channel:" + str2);
                            }
                            throw new RuntimeException("unzip file: channel:" + str2);
                        }
                        d.close(zipInputStream2);
                        return;
                    }
                    String name = nextEntry.getName();
                    if (!a(name)) {
                        if (!name.startsWith(str2 + File.separator)) {
                            com.bytedance.geckox.clean.c.a(new File(canonicalPath));
                            throw new RuntimeException("the zip package outermost folder is not named by channel channel:" + str2);
                        } else if (nextEntry.isDirectory()) {
                            File file = new File(canonicalPath, name);
                            if (!file.getCanonicalPath().startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, dir:" + name + ", channel:" + str2);
                            }
                            file.mkdirs();
                        } else {
                            String canonicalPath2 = new File(canonicalPath, name).getCanonicalPath();
                            if (!canonicalPath2.startsWith(canonicalPath)) {
                                throw new RuntimeException("directory traversal, file name:" + name);
                            }
                            File file2 = new File(canonicalPath2);
                            file2.getParentFile().mkdirs();
                            try {
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                                try {
                                    long a2 = f.a(zipInputStream2, fileOutputStream2);
                                    long size = nextEntry.getSize();
                                    str3 = str4;
                                    long length = file2.exists() ? file2.length() : -1L;
                                    if (size != 0 && a2 != 0 && length > 0) {
                                        z = z2;
                                        d.close(fileOutputStream2);
                                        z2 = z;
                                        z3 = z2;
                                        str4 = str3;
                                    }
                                    String str5 = "channel:" + str2 + ",file :" + name + ",entry size:" + size + ",local file size:" + length + ",stream size:" + a2 + ",pid:" + a.a() + ",thread id:" + Thread.currentThread().getId() + ",space left:" + a.c();
                                    z = true;
                                    com.bytedance.geckox.logger.b.a(str3, "mmap+ZipInputStream unzip size 0 warning", str5);
                                    EventMessageModel eventMessageModel = new EventMessageModel();
                                    eventMessageModel.setEventType(3);
                                    eventMessageModel.setSubType(31);
                                    eventMessageModel.setErrMsg("unzip size 0 warning," + str5);
                                    eventMessageModel.setExtra("mmap+ZipInputStream");
                                    com.bytedance.geckox.statistic.e.a(eventMessageModel);
                                    d.close(fileOutputStream2);
                                    z2 = z;
                                    z3 = z2;
                                    str4 = str3;
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream = fileOutputStream2;
                                    d.close(fileOutputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = null;
                            }
                        }
                    }
                    str3 = str4;
                    z = z2;
                    z2 = z;
                    z3 = z2;
                    str4 = str3;
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream = zipInputStream2;
                    d.close(zipInputStream);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            zipInputStream = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x0236  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(java.io.File r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.geckox.utils.r.b(java.io.File, java.lang.String, java.lang.String):void");
    }

    private static boolean a(String str) {
        return str.startsWith("__MACOSX/") || str.equals(".DS_Store");
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (java.util.Arrays.equals(com.bytedance.geckox.utils.r.f9378b, r1) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.io.InputStream r4) {
        /*
            r0 = 4
            byte[] r1 = new byte[r0]     // Catch: java.lang.Throwable -> L23
            r2 = 0
            int r3 = r4.read(r1, r2, r0)     // Catch: java.lang.Throwable -> L23
            if (r3 != r0) goto L1f
            byte[] r0 = com.bytedance.geckox.utils.r.f9377a     // Catch: java.lang.Throwable -> L23
            boolean r0 = java.util.Arrays.equals(r0, r1)     // Catch: java.lang.Throwable -> L23
            if (r0 != 0) goto L1a
            byte[] r0 = com.bytedance.geckox.utils.r.f9378b     // Catch: java.lang.Throwable -> L23
            boolean r0 = java.util.Arrays.equals(r0, r1)     // Catch: java.lang.Throwable -> L23
            if (r0 == 0) goto L1b
        L1a:
            r2 = 1
        L1b:
            com.bytedance.geckox.utils.d.close(r4)
            return r2
        L1f:
            com.bytedance.geckox.utils.d.close(r4)
            return r2
        L23:
            r0 = move-exception
            com.bytedance.geckox.utils.d.close(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.geckox.utils.r.a(java.io.InputStream):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
        if (java.util.Arrays.equals(com.bytedance.geckox.utils.r.f9378b, r1) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.io.File r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L40
            boolean r1 = r4.exists()
            if (r1 == 0) goto L40
            boolean r1 = r4.isDirectory()
            if (r1 == 0) goto L10
            goto L40
        L10:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3b
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L3b
            r4 = 4
            byte[] r1 = new byte[r4]     // Catch: java.lang.Throwable -> L38
            int r3 = r2.read(r1, r0, r4)     // Catch: java.lang.Throwable -> L38
            if (r3 != r4) goto L34
            byte[] r4 = com.bytedance.geckox.utils.r.f9377a     // Catch: java.lang.Throwable -> L38
            boolean r4 = java.util.Arrays.equals(r4, r1)     // Catch: java.lang.Throwable -> L38
            if (r4 != 0) goto L2f
            byte[] r4 = com.bytedance.geckox.utils.r.f9378b     // Catch: java.lang.Throwable -> L38
            boolean r4 = java.util.Arrays.equals(r4, r1)     // Catch: java.lang.Throwable -> L38
            if (r4 == 0) goto L30
        L2f:
            r0 = 1
        L30:
            com.bytedance.geckox.utils.d.close(r2)
            return r0
        L34:
            com.bytedance.geckox.utils.d.close(r2)
            return r0
        L38:
            r4 = move-exception
            r1 = r2
            goto L3c
        L3b:
            r4 = move-exception
        L3c:
            com.bytedance.geckox.utils.d.close(r1)
            throw r4
        L40:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.geckox.utils.r.a(java.io.File):boolean");
    }
}
