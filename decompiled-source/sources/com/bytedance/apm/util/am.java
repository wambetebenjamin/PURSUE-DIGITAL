package com.bytedance.apm.util;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
/* compiled from: UrlUtils.java */
/* loaded from: classes.dex */
public class am {
    public static String addParamsToURL(String str, Map<String, String> map) {
        if (!TextUtils.isDigitsOnly(str) && map != null && !map.isEmpty()) {
            if (str.indexOf("?") < 0) {
                str = str + "?";
            }
            if (str.endsWith("?")) {
                str = str + a("sdk_version", "UTF-8") + "=" + a(String.valueOf(400), "UTF-8");
            } else {
                str = str + "&" + a("sdk_version", "UTF-8") + "=" + a(String.valueOf(400), "UTF-8");
            }
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (map.get(entry.getKey()) != null) {
                        if (str.endsWith("?")) {
                            str = str + a(entry.getKey().toString(), "UTF-8") + "=" + a(map.get(entry.getKey()).toString(), "UTF-8");
                        } else {
                            str = str + "&" + a(entry.getKey().toString(), "UTF-8") + "=" + a(map.get(entry.getKey()).toString(), "UTF-8");
                        }
                    }
                }
            }
        }
        return str;
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "UTF-8";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static String sanitizeUrl(String str) {
        if (str == null) {
            return null;
        }
        try {
            URL url = new URL(str);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(url.getProtocol());
            stringBuffer.append("://");
            stringBuffer.append(url.getHost());
            if (url.getPort() != -1) {
                stringBuffer.append(CertificateUtil.DELIMITER);
                stringBuffer.append(url.getPort());
            }
            stringBuffer.append(url.getPath());
            return stringBuffer.toString();
        } catch (MalformedURLException unused) {
            return null;
        }
    }
}
