package com.bytedance.frameworks.baselib.network.http.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: CookieParser.java */
/* loaded from: classes2.dex */
public class b {
    public static String a(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile(".*(((" + str2 + "=[^;]*)|(" + str2 + "=\"[\";]*))|(" + str2 + "=.*$)).*").matcher(str);
            if (matcher.matches()) {
                return matcher.group(1);
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
