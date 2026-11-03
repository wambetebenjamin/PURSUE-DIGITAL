package com.oasis.android;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
/* loaded from: classes9.dex */
public final class DiskTool {
    public static long getFreeSpace() {
        try {
            StatFs statFs = new StatFs(ActivityManager.getActivity().getExternalFilesDir(null).getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getAvailableBytes();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e2) {
            e2.printStackTrace();
            return Long.MAX_VALUE;
        }
    }

    public static long getTotalSpace() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Exception e2) {
            e2.printStackTrace();
            return Long.MAX_VALUE;
        }
    }
}
