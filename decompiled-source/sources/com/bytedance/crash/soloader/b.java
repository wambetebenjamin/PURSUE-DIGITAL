package com.bytedance.crash.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.bytedance.crash.util.k;
import com.bytedance.librarian.c;
import com.facebook.internal.FacebookRequestErrorClassification;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* compiled from: SafelyLibraryLoader.java */
/* loaded from: classes2.dex */
public class b {
    public static String a(Context context, String str, File file) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String a2 = a(applicationInfo.sourceDir, str, file);
        if (a2 == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 21) {
            return a2;
        }
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr != null) {
            for (String str2 : strArr) {
                a2 = a(str2, str, file);
                if (a2 == null) {
                    return null;
                }
            }
        }
        return a2;
    }

    private static String a(String str, String str2, File file) {
        InputStream inputStream;
        ZipFile zipFile;
        String message;
        ZipEntry entry;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            zipFile = new ZipFile(new File(str), 1);
            try {
                entry = zipFile.getEntry("lib/" + Build.CPU_ABI + c.a.SEPARATOR + System.mapLibraryName(str2));
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
            String str3 = Build.CPU_ABI;
            if (indexOf <= 0) {
                indexOf = Build.CPU_ABI.length();
            }
            String sb = append.append(str3.substring(0, indexOf)).append(c.a.SEPARATOR).append(System.mapLibraryName(str2)).toString();
            ZipEntry entry2 = zipFile.getEntry(sb);
            if (entry2 == null) {
                message = "Library entry not found:" + sb;
                k.a((Closeable) null);
                k.a((Closeable) null);
                k.a(zipFile);
                return message;
            }
            entry = entry2;
        }
        file.createNewFile();
        InputStream inputStream2 = zipFile.getInputStream(entry);
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th3) {
            inputStream = inputStream2;
            th = th3;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream2.read(bArr);
                if (read <= 0) {
                    k.a(file.getAbsolutePath(), (int) FacebookRequestErrorClassification.ESC_APP_INACTIVE);
                    k.a(fileOutputStream);
                    k.a(inputStream2);
                    k.a(zipFile);
                    return null;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th4) {
            fileOutputStream2 = fileOutputStream;
            inputStream = inputStream2;
            th = th4;
            try {
                message = th.getMessage();
                k.a(fileOutputStream2);
                k.a(inputStream);
                k.a(zipFile);
                return message;
            } catch (Throwable th5) {
                k.a(fileOutputStream2);
                k.a(inputStream);
                k.a(zipFile);
                throw th5;
            }
        }
    }
}
