package com.bytedance.crash.dumper;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.crash.Global;
import java.io.File;
/* compiled from: Storage.java */
/* loaded from: classes2.dex */
public class s {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6600a = 52428800;

    /* renamed from: b  reason: collision with root package name */
    private static final int f6601b = 104857600;

    /* renamed from: c  reason: collision with root package name */
    private static final int f6602c = 1073741824;

    public static String a(long j2) {
        return j2 <= 52428800 ? "0 - 50M" : j2 <= 104857600 ? "50M - 100M" : j2 <= 1073741824 ? "100M - 1G" : "1G - ";
    }

    public static long b(long j2) {
        return j2;
    }

    public static long a() {
        Context c2 = Global.c();
        if (c2 == null) {
            return 0L;
        }
        return b(c2.getFilesDir());
    }

    public static long b() {
        Context c2 = Global.c();
        if (c2 == null) {
            return 0L;
        }
        return a(c2.getFilesDir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public static Pair<Long, Long> c() {
        String str;
        long j2;
        long blockSize;
        long blockCount;
        long j3;
        try {
            str = Environment.getExternalStorageState();
        } catch (Throwable unused) {
            str = null;
        }
        long j4 = 0;
        if (TextUtils.equals(str, "mounted")) {
            try {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
                j2 = 18;
                try {
                    if (Build.VERSION.SDK_INT >= 18) {
                        long availableBytes = statFs.getAvailableBytes();
                        blockSize = statFs.getBlockSizeLong();
                        blockCount = statFs.getBlockCountLong();
                        j2 = availableBytes;
                    } else {
                        long blockSize2 = statFs.getBlockSize() * statFs.getAvailableBlocks();
                        blockSize = statFs.getBlockSize();
                        blockCount = statFs.getBlockCount();
                        j2 = blockSize2;
                    }
                    j3 = blockSize * blockCount;
                    j4 = j2;
                } catch (Throwable unused2) {
                }
            } catch (Throwable unused3) {
                j2 = 0;
            }
        } else {
            j3 = 0;
        }
        j2 = j4;
        j4 = j3;
        return new Pair<>(Long.valueOf(j4), Long.valueOf(j2));
    }

    public static String d() {
        return a(a());
    }

    private static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long b(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getFreeBytes();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
