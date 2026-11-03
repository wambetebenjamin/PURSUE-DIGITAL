package com.bytedance.common.jato.boost;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
/* loaded from: classes.dex */
public class CommonIoOpt {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4533a = 2;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4534b = 3;

    /* renamed from: c  reason: collision with root package name */
    private static final String f4535c = "iopriority_opt";

    private static native void nResetIoPriority();

    private static native void nResetIoPriorityByTid(int i2);

    private static native int nSetIoPriority(int i2, int i3);

    private static native int nSetIoPriorityByTid(int i2, int i3, int i4);

    private static native void nativeInit(String[] strArr, String[] strArr2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(String[] strArr, String[] strArr2) {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        try {
            nativeInit(strArr, strArr2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int a(int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 29 && i2 >= 2 && i2 <= 3 && i3 >= 0 && i3 <= 7) {
            return nSetIoPriority(i2, i3);
        }
        return 0;
    }

    public static int a(int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 29 && i3 >= 2 && i3 <= 3 && i4 >= 0 && i4 <= 7) {
            return nSetIoPriorityByTid(i2, i3, i4);
        }
        return 0;
    }

    public static void a() {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        nResetIoPriority();
    }

    public static void a(int i2) {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        nResetIoPriorityByTid(i2);
    }

    public static void a(final Context context) {
        Thread thread = new Thread(new Runnable() { // from class: com.bytedance.common.jato.boost.CommonIoOpt.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i2 = 0; i2 < 10; i2++) {
                    CommonIoOpt.c(context);
                }
            }
        });
        thread.setName("test");
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir, "largefile.data");
        File file2 = new File(cacheDir, "largefile2.data");
        int i2 = -1;
        try {
            try {
                i2 = a(3, 4);
                a(file, 1073741824L);
                long nanoTime = System.nanoTime();
                a(file.toPath(), file2.toPath());
                Log.d(f4535c, "iopriority_opt File copied successfully. dir: " + cacheDir + ". Time taken: " + (System.nanoTime() - nanoTime) + " nanoseconds. ret: " + i2);
                if (i2 < 0) {
                    return;
                }
            } catch (IOException e2) {
                Log.e(f4535c, "iopriority_opt File operations failed: " + e2.getMessage(), e2);
                if (i2 < 0) {
                    return;
                }
            }
            a();
        } catch (Throwable th) {
            if (i2 >= 0) {
                a();
            }
            throw th;
        }
    }

    private static void a(File file, long j2) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (j2 > 0) {
                int min = (int) Math.min(1024, j2);
                fileOutputStream.write(bArr, 0, min);
                j2 -= min;
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static void a(Path path, Path path2) {
        Files.copy(path, path2, StandardCopyOption.REPLACE_EXISTING);
    }
}
