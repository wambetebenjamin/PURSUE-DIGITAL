package com.bytedance.apm6.util;

import android.os.Build;
import android.os.StatFs;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/* compiled from: FileUtils.java */
/* loaded from: classes.dex */
public class d {
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x003c: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:28:0x003c */
    public static byte[] readFileToByteArray(File file) {
        BufferedInputStream bufferedInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    bufferedInputStream.read(bArr, 0, length);
                    bufferedInputStream.close();
                    e.closeQuietly(bufferedInputStream);
                    return bArr;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    e.closeQuietly(bufferedInputStream);
                    return null;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    e.closeQuietly(bufferedInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeable2 = closeable;
                e.closeQuietly(closeable2);
                throw th;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            bufferedInputStream = null;
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            e.closeQuietly(closeable2);
            throw th;
        }
    }

    public static boolean a(File file) {
        if (file != null && file.exists()) {
            try {
                return file.delete();
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            try {
                file.delete();
            } catch (Throwable unused) {
            }
        }
    }

    public static long getAvailableStorage(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBytes();
        }
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long getTotalStorageSize(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getTotalBytes();
        }
        return statFs.getBlockCount() * statFs.getBlockSize();
    }
}
