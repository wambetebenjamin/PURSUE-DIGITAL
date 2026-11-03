package com.bytedance.ttgame.channel.utils;

import java.io.File;
/* loaded from: classes3.dex */
public class DataCleanCustomUtil {
    private DataCleanCustomUtil() {
    }

    public static void cleanApplicationData(String... strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            cleanCustomCache(str);
        }
    }

    public static void cleanCustomCache(String str) {
        deleteDirectory(new File(str));
    }

    public static void deleteDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteDirectory(file2);
                }
                file.delete();
                return;
            }
            file.delete();
        }
    }
}
