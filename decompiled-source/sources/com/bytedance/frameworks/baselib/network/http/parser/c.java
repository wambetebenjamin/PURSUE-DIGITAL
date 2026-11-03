package com.bytedance.frameworks.baselib.network.http.parser;

import android.text.TextUtils;
import com.meituan.robust.Constants;
import java.util.Map;
/* compiled from: HttpHeaderParser.java */
/* loaded from: classes2.dex */
public class c {
    public static String a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (TextUtils.isEmpty(str2)) {
            str2 = map.get("Content-Type".toLowerCase());
        }
        if (str2 != null) {
            String[] split = str2.split(Constants.PACKNAME_END);
            for (int i2 = 1; i2 < split.length; i2++) {
                String[] split2 = split[i2].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static String a(Map<String, String> map) {
        return a(map, "ISO-8859-1");
    }
}
