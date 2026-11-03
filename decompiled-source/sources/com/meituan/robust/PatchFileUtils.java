package com.meituan.robust;

import java.io.File;
/* loaded from: classes9.dex */
public class PatchFileUtils {
    public static boolean deleteDir(File file) {
        if (file == null) {
            return true;
        }
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
