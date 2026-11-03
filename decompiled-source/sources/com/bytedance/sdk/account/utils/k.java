package com.bytedance.sdk.account.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: UrlBuilder.java */
/* loaded from: classes2.dex */
public class k {
    public static final String ASCII = "ASCII";
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_PROTOCOL_CHARSET = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_8 = "UTF-8";

    /* renamed from: a  reason: collision with root package name */
    private static final String f13091a = "&";

    /* renamed from: b  reason: collision with root package name */
    private static final String f13092b = "=";

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, String> f13093c;

    /* renamed from: d  reason: collision with root package name */
    private String f13094d;

    public k(String str) {
        this.f13093c = new LinkedHashMap();
        this.f13094d = str;
    }

    public k() {
        this.f13093c = new LinkedHashMap();
        this.f13094d = null;
    }

    public void setUrl(String str) {
        this.f13094d = str;
    }

    public String getUrl() {
        return this.f13094d;
    }

    public void addParam(String str, int i2) {
        this.f13093c.put(str, String.valueOf(i2));
    }

    public void addParam(String str, long j2) {
        this.f13093c.put(str, String.valueOf(j2));
    }

    public void addParam(String str, double d2) {
        this.f13093c.put(str, String.valueOf(d2));
    }

    public void addParam(String str, String str2) {
        this.f13093c.put(str, str2);
    }

    public Map<String, String> getParamList() {
        return this.f13093c;
    }

    public String build() {
        if (this.f13093c.isEmpty()) {
            return this.f13094d;
        }
        String format = format(this.f13093c, "UTF-8");
        String str = this.f13094d;
        if (str == null || str.length() == 0) {
            return format;
        }
        if (this.f13094d.indexOf(63) >= 0) {
            return this.f13094d + f13091a + format;
        }
        return this.f13094d + "?" + format;
    }

    public static String format(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String b2 = b(entry.getKey(), str);
            String value = entry.getValue();
            String b3 = value != null ? b(value, str) : "";
            if (!TextUtils.isEmpty(b3)) {
                b3 = b3.replace("+", "%20");
            }
            if (sb.length() > 0) {
                sb.append(f13091a);
            }
            sb.append(b2);
            sb.append(f13092b);
            sb.append(b3);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private static String b(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public String toString() {
        return build();
    }
}
