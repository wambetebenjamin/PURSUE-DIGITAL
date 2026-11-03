package com.bytedance.react.framework;

import android.util.Pair;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
/* loaded from: classes2.dex */
public class NetworkUtils {
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    public static String format(List<Pair<String, String>> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, String> pair : list) {
            String encode = encode((String) pair.first, str);
            String str2 = (String) pair.second;
            String encode2 = str2 != null ? encode(str2, str) : "";
            if (sb.length() > 0) {
                sb.append(PARAMETER_SEPARATOR);
            }
            sb.append(encode);
            sb.append(NAME_VALUE_SEPARATOR);
            sb.append(encode2);
        }
        return sb.toString();
    }

    private static String encode(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
