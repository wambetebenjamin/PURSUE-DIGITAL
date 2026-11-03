package com.bytedance.retrofit2.mime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public final class MimeUtil {
    private static final Pattern CHARSET = Pattern.compile("\\Wcharset=([^\\s;]+)", 2);

    @Deprecated
    public static String parseCharset(String str) {
        return parseCharset(str, "UTF-8");
    }

    public static String parseCharset(String str, String str2) {
        try {
            Matcher matcher = CHARSET.matcher(str);
            if (matcher.find()) {
                return matcher.group(1).replaceAll("[\"\\\\]", "");
            }
        } catch (IllegalArgumentException unused) {
        }
        return str2;
    }

    private MimeUtil() {
    }
}
