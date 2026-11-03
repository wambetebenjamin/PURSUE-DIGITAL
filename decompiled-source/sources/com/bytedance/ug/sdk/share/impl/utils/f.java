package com.bytedance.ug.sdk.share.impl.utils;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Set;
/* compiled from: HttpUtils.java */
/* loaded from: classes8.dex */
public class f {
    public static boolean isUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("http:") || str.startsWith("ftp:") || str.startsWith("https:");
    }

    public static String addUniqueParam(String str, String str2, String str3) {
        try {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str);
                Set<String> queryParameterNames = parse.getQueryParameterNames();
                Uri.Builder clearQuery = parse.buildUpon().clearQuery();
                for (String str4 : queryParameterNames) {
                    if (!TextUtils.equals(str4, str2)) {
                        clearQuery.appendQueryParameter(str4, parse.getQueryParameter(str4));
                    }
                }
                clearQuery.appendQueryParameter(str2, str3);
                return clearQuery.build().toString();
            }
            return str;
        } catch (Exception unused) {
            return str;
        }
    }
}
