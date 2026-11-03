package com.bytedance.frameworks.baselib.network.http.util;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.common.utility.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
/* compiled from: NetworklibUtils.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8350a = "k";

    public static boolean a(String str, String str2, String str3, List<Pair<String, String>> list) {
        String[] split;
        String[] split2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || list == null || (split = str.split(str2)) == null) {
            return false;
        }
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!TextUtils.isEmpty(split[i2]) && (split2 = split[i2].trim().split(str3)) != null && split2.length == 2 && !TextUtils.isEmpty(split2[0]) && !TextUtils.isEmpty(split2[1])) {
                list.add(new Pair<>(split2[0].trim(), split2[1].trim()));
            }
        }
        return list.size() != 0;
    }

    public static boolean a(String str, List<Long> list) {
        if (!TextUtils.isEmpty(str) && str.contains("~")) {
            String[] split = str.split("~");
            if (split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                long a2 = a(split[0]);
                long a3 = a(split[1]);
                if (a2 > 0 && a3 > 0 && a2 < a3) {
                    list.add(Long.valueOf(a2));
                    list.add(Long.valueOf(a3));
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static long a(String str) {
        if (Logger.debug()) {
            Logger.d(f8350a, "utcTime " + str);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date parse = simpleDateFormat.parse(str);
            if (parse != null) {
                return parse.getTime();
            }
            return 0L;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }
}
