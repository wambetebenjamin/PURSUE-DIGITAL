package com.bytedance.watson.assist.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
/* compiled from: ProcessUtils.java */
/* loaded from: classes8.dex */
public class d {
    private static ArrayList<Long> a(String str) {
        ArrayList<Long> arrayList = new ArrayList<>();
        for (File file : new File(str).listFiles(new FilenameFilter() { // from class: com.bytedance.watson.assist.utils.d.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                try {
                    Long.parseLong(str2);
                    return true;
                } catch (NumberFormatException unused) {
                    return false;
                }
            }
        })) {
            arrayList.add(Long.valueOf(Long.parseLong(file.getName())));
        }
        return arrayList;
    }

    public static ArrayList<Long> threadListFromProcFs() {
        return a("/proc/self/task/");
    }
}
