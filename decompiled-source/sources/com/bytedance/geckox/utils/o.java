package com.bytedance.geckox.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ResVersionUtils.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9372a = "gecko-debug-tag";

    private synchronized File a(File file, String str, String str2) {
        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getLatestChannelVersionSync(rootDir:" + file + ",accessKey:" + str + ",channel:" + str2 + ")");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && file != null) {
            if (file.exists() && file.isDirectory()) {
                com.bytedance.geckox.lock.b a2 = com.bytedance.geckox.lock.b.a(file + File.separator + str2 + File.separator + com.bytedance.geckox.lock.b.f9207a);
                Long latestChannelVersion = getLatestChannelVersion(new File(str, str2));
                if (latestChannelVersion != null) {
                    File file2 = new File(file, str + File.separator + str2 + File.separator + latestChannelVersion + File.separator + com.bytedance.geckox.lock.c.f9212a);
                    File parentFile = file2.getParentFile();
                    com.bytedance.geckox.lock.c.a(file2.getAbsolutePath());
                    a2.a();
                    return parentFile;
                }
                a2.a();
                return null;
            }
            return null;
        }
        return null;
    }

    public static Long getLatestChannelVersion(File file) {
        Long l = null;
        if (file == null) {
            return null;
        }
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.bytedance.geckox.utils.o.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory();
            }
        });
        if (listFiles != null && listFiles.length != 0) {
            for (File file2 : listFiles) {
                try {
                    long longValue = Long.valueOf(file2.getName()).longValue();
                    if (l == null) {
                        l = Long.valueOf(longValue);
                    } else if (longValue > l.longValue()) {
                        l = Long.valueOf(longValue);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return l;
    }

    public static List<Long> getAllVersion(File file) {
        ArrayList arrayList = null;
        if (file == null) {
            return null;
        }
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.bytedance.geckox.utils.o.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory();
            }
        });
        if (listFiles != null && listFiles.length != 0) {
            arrayList = new ArrayList();
            for (File file2 : listFiles) {
                try {
                    arrayList.add(Long.valueOf(Long.valueOf(file2.getName()).longValue()));
                } catch (Exception unused) {
                }
            }
        }
        return arrayList;
    }
}
