package com.bytedance.apm.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/* compiled from: FileUtils.java */
/* loaded from: classes.dex */
public class i {
    public static final String TAG = "i";

    /* renamed from: a  reason: collision with root package name */
    static final int f1699a = 8192;

    public static String getFormatSize(double d2) {
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return d2 + "Byte(s)";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "KB";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            return new BigDecimal(Double.toString(d4)).setScale(2, 4).toPlainString() + "MB";
        }
        double d6 = d5 / 1024.0d;
        if (d6 < 1.0d) {
            return new BigDecimal(Double.toString(d5)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d6).setScale(2, 4).toPlainString() + "TB";
    }

    public static void removeDir(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath());
                } else {
                    listFiles[i2].delete();
                }
            }
            file.delete();
        }
    }

    public static boolean copyFile(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean saveInputStream = saveInputStream(fileInputStream, str2, str3);
            com.bytedance.monitor.util.c.safeClose(fileInputStream);
            return saveInputStream;
        } catch (Exception e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            com.bytedance.monitor.util.c.safeClose(fileInputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            com.bytedance.monitor.util.c.safeClose(fileInputStream2);
            throw th;
        }
    }

    public static boolean saveInputStream(InputStream inputStream, String str, String str2) {
        if (inputStream == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (file.exists() || file.mkdirs()) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read != -1) {
                                fileOutputStream2.write(bArr, 0, read);
                            } else {
                                fileOutputStream2.flush();
                                fileOutputStream2.close();
                                inputStream.close();
                                com.bytedance.monitor.util.c.safeClose((Closeable) null);
                                com.bytedance.monitor.util.c.safeClose(inputStream);
                                return true;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        Log.d(TAG, "save inputstream error: " + e);
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                        com.bytedance.monitor.util.c.safeClose(inputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                        com.bytedance.monitor.util.c.safeClose(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
            com.bytedance.monitor.util.c.safeClose(fileOutputStream);
            com.bytedance.monitor.util.c.safeClose(inputStream);
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public static void compress(String str, String... strArr) {
        FileOutputStream fileOutputStream;
        Closeable closeable;
        Exception e2;
        ?? file = new File(str);
        try {
            try {
                fileOutputStream = new FileOutputStream((File) file);
                try {
                    CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
                    try {
                        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
                        try {
                            for (String str2 : strArr) {
                                File file2 = new File(str2);
                                if (!file2.exists()) {
                                    throw new RuntimeException(str2 + "not exist！");
                                }
                                a(file2, zipOutputStream, "");
                            }
                            com.bytedance.monitor.util.c.safeClose(zipOutputStream);
                            com.bytedance.monitor.util.c.safeClose(checkedOutputStream);
                            com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                        } catch (Exception e3) {
                            e2 = e3;
                            throw new RuntimeException(e2);
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e2 = e;
                        throw new RuntimeException(e2);
                    } catch (Throwable th) {
                        th = th;
                        closeable = null;
                        file = checkedOutputStream;
                        th = th;
                        com.bytedance.monitor.util.c.safeClose(closeable);
                        com.bytedance.monitor.util.c.safeClose((Closeable) file);
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                } catch (Throwable th2) {
                    th = th2;
                    file = 0;
                    closeable = null;
                }
            } catch (Throwable th3) {
                th = th3;
                com.bytedance.monitor.util.c.safeClose(closeable);
                com.bytedance.monitor.util.c.safeClose((Closeable) file);
                com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
        } catch (Throwable th4) {
            th = th4;
            file = 0;
            fileOutputStream = null;
            closeable = null;
        }
    }

    private static void a(File file, ZipOutputStream zipOutputStream, String str) {
        if (file.isDirectory()) {
            b(file, zipOutputStream, str + file.getName() + File.separator);
            return;
        }
        System.out.println("compress：" + str + file.getName());
        c(file, zipOutputStream, str);
    }

    private static void b(File file, ZipOutputStream zipOutputStream, String str) {
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                a(file2, zipOutputStream, str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedInputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v6 */
    private static void c(File file, ZipOutputStream zipOutputStream, String str) {
        FileInputStream fileInputStream;
        ?? r2;
        if (!file.exists()) {
            return;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            e = e2;
            r2 = 0;
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            r2 = new BufferedInputStream(fileInputStream);
        } catch (Exception e3) {
            e = e3;
            r2 = 0;
        } catch (Throwable th2) {
            th = th2;
            com.bytedance.monitor.util.c.safeClose(fileInputStream2);
            com.bytedance.monitor.util.c.safeClose(fileInputStream);
            throw th;
        }
        try {
            zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = r2.read(bArr, 0, 8192);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    com.bytedance.monitor.util.c.safeClose((Closeable) r2);
                    com.bytedance.monitor.util.c.safeClose(fileInputStream);
                    return;
                }
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream2 = fileInputStream;
            r2 = r2;
            try {
                throw new RuntimeException(e);
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                fileInputStream2 = r2;
                com.bytedance.monitor.util.c.safeClose(fileInputStream2);
                com.bytedance.monitor.util.c.safeClose(fileInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream2 = r2;
            com.bytedance.monitor.util.c.safeClose(fileInputStream2);
            com.bytedance.monitor.util.c.safeClose(fileInputStream);
            throw th;
        }
    }

    public static String getFileNameWithoutSuffix(File file) {
        String[] split = file.getName().split("\\.");
        return split.length > 1 ? split[split.length - 2] : "";
    }

    public static long getSize(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                return file.length();
            }
            long j2 = 0;
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    j2 += getSize(file2);
                }
                return j2;
            }
            return -1L;
        }
        return -1L;
    }

    public static File getExternalRootDir(Context context) {
        File file = null;
        if (context == null) {
            return null;
        }
        try {
            file = context.getExternalFilesDir(null);
            do {
                file = file.getParentFile();
            } while (file.getAbsolutePath().contains("/Android"));
        } catch (Throwable unused) {
        }
        return file;
    }
}
