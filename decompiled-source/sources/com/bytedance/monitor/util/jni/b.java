package com.bytedance.monitor.util.jni;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.bytedance.librarian.c;
import com.bytedance.monitor.util.c;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.meituan.robust.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* compiled from: SafelyLibraryLoader.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9681a = "libso";

    /* renamed from: b  reason: collision with root package name */
    private static List<String> f9682b = new ArrayList();

    public static synchronized boolean loadLibrary(Context context, String str) {
        synchronized (b.class) {
            if (f9682b.contains(str)) {
                return true;
            }
            try {
                System.loadLibrary(str);
                f9682b.add(str);
            } catch (UnsatisfiedLinkError e2) {
                File a2 = a(context, str);
                if (a2 == null) {
                    return false;
                }
                if (a2.exists()) {
                    a2.delete();
                }
                String unpackLibrary = unpackLibrary(context, str, a2);
                if (unpackLibrary != null) {
                    Log.e("loadLibrary", e2.getMessage() + Constants.ARRAY_TYPE + unpackLibrary + "]");
                    return false;
                }
                try {
                    System.load(a2.getAbsolutePath());
                    f9682b.add(str);
                } catch (Throwable unused) {
                    return false;
                }
            } catch (Throwable unused2) {
                return false;
            }
            return true;
        }
    }

    private static File a(Context context) {
        if (context == null || context.getFilesDir() == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), f9681a);
        if (!file.exists()) {
            c.mkdir(file.getAbsolutePath());
        }
        return file;
    }

    private static File a(Context context, String str) {
        String mapLibraryName = System.mapLibraryName(str);
        File a2 = a(context);
        if (a2 != null) {
            return new File(a2, mapLibraryName);
        }
        return null;
    }

    public static String unpackLibrary(Context context, String str, File file) {
        InputStream inputStream;
        ZipFile zipFile;
        String message;
        ZipEntry entry;
        FileOutputStream fileOutputStream = null;
        try {
            zipFile = new ZipFile(new File(context.getApplicationInfo().sourceDir), 1);
            try {
                entry = zipFile.getEntry("lib/" + Build.CPU_ABI + c.a.SEPARATOR + System.mapLibraryName(str));
            } catch (Throwable th) {
                th = th;
                inputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            zipFile = null;
        }
        if (entry == null) {
            int indexOf = Build.CPU_ABI.indexOf(45);
            StringBuilder append = new StringBuilder().append("lib/");
            String str2 = Build.CPU_ABI;
            if (indexOf <= 0) {
                indexOf = Build.CPU_ABI.length();
            }
            String sb = append.append(str2.substring(0, indexOf)).append(c.a.SEPARATOR).append(System.mapLibraryName(str)).toString();
            ZipEntry entry2 = zipFile.getEntry(sb);
            if (entry2 == null) {
                message = "Library entry not found:" + sb;
                com.bytedance.monitor.util.c.safeClose((Closeable) null);
                com.bytedance.monitor.util.c.safeClose((Closeable) null);
                com.bytedance.monitor.util.c.safeClose(zipFile);
                return message;
            }
            entry = entry2;
        }
        file.createNewFile();
        InputStream inputStream2 = zipFile.getInputStream(entry);
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read <= 0) {
                        com.bytedance.monitor.util.c.setPermissions(file.getAbsolutePath(), FacebookRequestErrorClassification.ESC_APP_INACTIVE);
                        com.bytedance.monitor.util.c.safeClose(fileOutputStream2);
                        com.bytedance.monitor.util.c.safeClose(inputStream2);
                        com.bytedance.monitor.util.c.safeClose(zipFile);
                        return null;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                }
            } catch (Throwable th3) {
                fileOutputStream = fileOutputStream2;
                inputStream = inputStream2;
                th = th3;
                try {
                    message = th.getMessage();
                    com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                    com.bytedance.monitor.util.c.safeClose(inputStream);
                    com.bytedance.monitor.util.c.safeClose(zipFile);
                    return message;
                } catch (Throwable th4) {
                    com.bytedance.monitor.util.c.safeClose(fileOutputStream);
                    com.bytedance.monitor.util.c.safeClose(inputStream);
                    com.bytedance.monitor.util.c.safeClose(zipFile);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            inputStream = inputStream2;
            th = th5;
        }
    }
}
