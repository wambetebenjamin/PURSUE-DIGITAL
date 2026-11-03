package com.bytedance.android.alog;

import android.text.TextUtils;
import com.meituan.robust.Constants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: FileManager.java */
/* loaded from: classes.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f175a = "time interval is invalid";

    /* renamed from: b  reason: collision with root package name */
    private static final String f176b = "log dir not exists";

    /* renamed from: c  reason: collision with root package name */
    private static final String f177c = "log file not found";

    /* renamed from: d  reason: collision with root package name */
    private static final String f178d = ".alog.hot";

    /* renamed from: e  reason: collision with root package name */
    private static final int f179e = 9;

    /* renamed from: f  reason: collision with root package name */
    private static long f180f;

    /* renamed from: g  reason: collision with root package name */
    private static long f181g;

    /* renamed from: h  reason: collision with root package name */
    private static ArrayList<String> f182h;

    /* renamed from: i  reason: collision with root package name */
    private static String f183i;

    a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File[] a(String str, String str2, String str3, final long j2, final long j3) {
        f180f = j2;
        f181g = j3;
        f183i = null;
        f182h = null;
        if (j2 > j3) {
            f183i = f175a;
            return new File[0];
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            f183i = f176b;
            return new File[0];
        }
        if (!TextUtils.isEmpty(str2)) {
            str2 = str2.replace(':', '-');
        }
        final Pattern compile = Pattern.compile("^\\d{4}_\\d{2}_\\d{2}_(\\d+)__" + (TextUtils.isEmpty(str2) ? "\\S+" : Pattern.quote(str2)) + "__" + (TextUtils.isEmpty(str3) ? "\\S+" : Pattern.quote(str3)) + "\\.alog\\.hot$");
        final ArrayList<String> arrayList = new ArrayList<>();
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.bytedance.android.alog.a.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str4) {
                String group;
                arrayList.add(str4);
                Matcher matcher = compile.matcher(str4);
                if (matcher.find() && matcher.groupCount() == 1 && (group = matcher.group(1)) != null) {
                    long parseLong = Long.parseLong(group);
                    if (parseLong > 0 && parseLong <= j3) {
                        long lastModified = new File(file2, str4).lastModified();
                        if (lastModified > 0 && lastModified >= j2) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            f183i = f177c;
            f182h = arrayList;
        }
        return listFiles == null ? new File[0] : listFiles;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("start", Long.toString(f180f));
        hashMap.put("end", Long.toString(f181g));
        hashMap.put(com.bytedance.pipo.payment.common.lib.c.de, f183i);
        if (f182h != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = f182h.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next.endsWith(f178d)) {
                    next = next.substring(0, next.length() - f179e);
                }
                sb.append(next).append(Constants.PACKNAME_END);
            }
            hashMap.put("file", sb.toString());
        }
        f183i = null;
        f182h = null;
        return hashMap;
    }
}
