package com.bytedance.retrofit2.http.ext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kotlin.text.Typography;
/* loaded from: classes2.dex */
public class QueryParamObjectUtils {
    private QueryParamObjectUtils() {
    }

    public static void addQuery(StringBuilder sb, String str, String str2) {
        try {
            if (str == null) {
                throw new IllegalArgumentException("Query param name must not be null.");
            }
            sb.append(sb.length() > 0 ? Typography.amp : '?');
            String encode = URLEncoder.encode(str, "UTF-8");
            String encode2 = URLEncoder.encode(str2, "UTF-8");
            if (encode2 != null && !encode2.isEmpty()) {
                sb.append(encode).append('=').append(encode2);
                return;
            }
            sb.append(encode);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Unable to convert query parameter \"" + str + "\" value to UTF-8: " + str2, e2);
        }
    }
}
