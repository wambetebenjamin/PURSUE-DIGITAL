package com.bytedance.geckox.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.librarian.c;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ResLoadUtils.java */
/* loaded from: classes2.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9371a = "gecko-debug-tag";

    public static boolean checkExist(File file, String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || file == null || !file.exists() || !file.isDirectory() || getLatestChannelVersion(file, str, str2) == null) ? false : true;
    }

    public static boolean checkChannelFileExist(File file, String str, String str2, String str3) {
        return searchChannelFile(file, str, str2, str3) != null;
    }

    public static String searchChannelFile(File file, String str, String str2, String str3) {
        File[] listFiles;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || file == null || !file.exists() || !file.isDirectory() || TextUtils.isEmpty(str3)) {
            return null;
        }
        File file2 = new File(file, str + File.separator + str2);
        if (file2.exists() && file2.listFiles().length > 0) {
            for (File file3 : file2.listFiles()) {
                if (file3.getName().endsWith(str3)) {
                    return file3.getName();
                }
            }
        }
        return null;
    }

    public static String getChannelPath(File file, String str, String str2) {
        Long latestChannelVersion;
        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getChannelPath(rootDir:" + file + ",accessKey:" + str + ",channel:" + str2 + ")");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && file != null && file.exists() && file.isDirectory()) {
            String a2 = a(str2);
            try {
                File file2 = new File(file, File.separator + str);
                if (file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    File file3 = new File(absolutePath, a2);
                    if (file3.exists() && (latestChannelVersion = o.getLatestChannelVersion(file3)) != null) {
                        String str3 = absolutePath + File.separator + a2 + File.separator + latestChannelVersion + File.separator + UriUtil.LOCAL_RESOURCE_SCHEME;
                        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getChannelPath:" + str3);
                        return str3;
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                com.bytedance.geckox.logger.b.w("gecko-debug-tag", "getChannelPath:error:", th);
            }
        }
        return null;
    }

    public static String getChannelPath(File file, String str, String str2, long j2) {
        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getChannelPath(rootDir:" + file + ",accessKey:" + str + ",channel:" + str2 + ")");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && file != null && j2 != 0 && file.exists() && file.isDirectory()) {
            String a2 = a(str2);
            try {
                File file2 = new File(file, File.separator + str);
                if (file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    if (new File(absolutePath, a2).exists()) {
                        String str3 = absolutePath + File.separator + a2 + File.separator + j2 + File.separator + UriUtil.LOCAL_RESOURCE_SCHEME;
                        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getChannelPath:" + str3);
                        return str3;
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                com.bytedance.geckox.logger.b.w("gecko-debug-tag", "getChannelPath:error:", th);
            }
        }
        return null;
    }

    public static boolean deleteChannel(File file, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && file != null && file.exists() && file.isDirectory()) {
            String a2 = a(str2);
            try {
                File file2 = new File(file, File.separator + str);
                if (file2.exists()) {
                    File file3 = new File(file2.getAbsolutePath(), a2);
                    if (file3.exists()) {
                        return f.delete(file3);
                    }
                    return false;
                }
                return false;
            } catch (Throwable th) {
                com.bytedance.geckox.logger.b.w("gecko-debug-tag", "deleteChannel:error:", th);
            }
        }
        return false;
    }

    private static String a(String str) {
        if (str.indexOf(c.a.SEPARATOR) == 0) {
            str = str.substring(1);
        }
        return str.lastIndexOf(c.a.SEPARATOR) == str.length() - 1 ? str.substring(0, str.lastIndexOf(c.a.SEPARATOR)) : str;
    }

    public static List<Pair<String, Long>> getAllLocalChannels(File file, String str) {
        Long latestChannelVersion;
        if (file == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file2 = new File(file, str);
        String[] list = file2.list();
        ArrayList arrayList = new ArrayList();
        if (list != null && list.length > 0) {
            for (String str2 : list) {
                File file3 = new File(file2, str2);
                if (file3.isDirectory() && (latestChannelVersion = o.getLatestChannelVersion(file3)) != null) {
                    arrayList.add(new Pair(str2, latestChannelVersion));
                }
            }
        }
        return arrayList;
    }

    public static Long getLatestChannelVersion(File file, String str, String str2) {
        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getLatestChannelVersion(rootDir:" + file + ",accessKey:" + str + ",channel:" + str2 + ")");
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && file != null && file.exists() && file.isDirectory()) {
            try {
                File file2 = new File(file, File.separator + str);
                if (file2.exists()) {
                    File file3 = new File(file2.getAbsolutePath(), str2);
                    if (file3.exists()) {
                        Long latestChannelVersion = o.getLatestChannelVersion(file3);
                        com.bytedance.geckox.logger.b.a("gecko-debug-tag", "getLatestChannelVersion:" + latestChannelVersion);
                        return latestChannelVersion;
                    }
                    return null;
                }
                return null;
            } catch (Throwable th) {
                com.bytedance.geckox.logger.b.w("gecko-debug-tag", "getLatestChannelVersion:error:", th);
            }
        }
        return null;
    }
}
