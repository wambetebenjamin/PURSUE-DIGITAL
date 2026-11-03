package com.ss.android.agilelogger.utils;

import android.content.Context;
import java.io.File;
/* compiled from: FileUtils.java */
/* loaded from: classes9.dex */
public class a {
    public static File getDefaultLogDir(Context context) {
        File file = new File(context.getFilesDir(), com.bytedance.tracing.internal.e.KEY_LOGS);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static String getBufferDirPath(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            File file = new File(filesDir.getAbsolutePath(), "ALOG");
            if (!file.exists()) {
                file.mkdir();
            }
            return file.getAbsolutePath();
        }
        return null;
    }
}
