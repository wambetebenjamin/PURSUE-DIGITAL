package com.bytedance.frameworks.baselib.network.http.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.just.agentweb.DefaultWebClient;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/* compiled from: UrlUtils.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8359a = "UTF-8";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8360b = "Content-Type";

    /* renamed from: c  reason: collision with root package name */
    public static final String f8361c = "ISO-8859-1";

    /* renamed from: d  reason: collision with root package name */
    public static final String f8362d = "User-Agent";

    /* renamed from: e  reason: collision with root package name */
    public static final int f8363e = 13;

    /* renamed from: f  reason: collision with root package name */
    public static final int f8364f = 10;

    /* renamed from: g  reason: collision with root package name */
    public static final int f8365g = 32;

    /* renamed from: h  reason: collision with root package name */
    public static final int f8366h = 9;

    /* renamed from: i  reason: collision with root package name */
    public static final Pattern f8367i = Pattern.compile("(((?:(?i:http|https|rtsp|ws|wss)://(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?(?:(([a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]](?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]_\\-]{0,61}[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]){0,1}\\.)+(xn\\-\\-[\\w\\-]{0,58}\\w|[a-zA-Z[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]{2,63})|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))))(?:\\:\\d{1,5})?)([/\\?](?:(?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]];/\\?:@&=#~\\-\\.\\+!\\*'\\(\\),_\\$])|(?:%[a-fA-F0-9]{2}))*)?(?:\\b|$|^))");

    /* renamed from: j  reason: collision with root package name */
    private static final String f8368j = "&";
    private static final String k = "=";
    private static final String l = "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9]))";
    private static final String m = "[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]";
    private static final String n = "a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]";
    private static final String o = "a-zA-Z[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]";
    private static final String p = "[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]](?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]_\\-]{0,61}[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]){0,1}";
    private static final String q = "xn\\-\\-[\\w\\-]{0,58}\\w";
    private static final String r = "(xn\\-\\-[\\w\\-]{0,58}\\w|[a-zA-Z[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]{2,63})";
    private static final String s = "([a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]](?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]_\\-]{0,61}[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]){0,1}\\.)+(xn\\-\\-[\\w\\-]{0,58}\\w|[a-zA-Z[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]{2,63})";
    private static final String t = "(([a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]](?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]_\\-]{0,61}[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]){0,1}\\.)+(xn\\-\\-[\\w\\-]{0,58}\\w|[a-zA-Z[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]]]{2,63})|((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))";
    private static final String u = "(?i:http|https|rtsp|ws|wss)://";
    private static final String v = "(?:\\b|$|^)";
    private static final String w = "(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@";
    private static final String x = "\\:\\d{1,5}";
    private static final String y = "[/\\?](?:(?:[a-zA-Z0-9[ -\ud7ff豈-﷏ﷰ-\uffef𐀀-\u1fffd𠀀-\u2fffd𰀀-\u3fffd\u40000-\u4fffd\u50000-\u5fffd\u60000-\u6fffd\u70000-\u7fffd\u80000-\u8fffd\u90000-\u9fffd\ua0000-\uafffd\ub0000-\ubfffd\uc0000-\ucfffd\ud0000-\udfffd\ue1000-\uefffd&&[^ [\u2000-\u200a]\u2028\u2029 \u3000]];/\\?:@&=#~\\-\\.\\+!\\*'\\(\\),_\\$])|(?:%[a-fA-F0-9]{2}))*";

    public static boolean a(char c2) {
        return c2 == ' ' || c2 == '\t' || c2 == '\r' || c2 == '\n';
    }

    @Deprecated
    public static Pair<String, String> a(String str, Map<String, String> map) {
        String encodedQuery;
        String[] split;
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            throw new IOException("parseUrl url is null !!!");
        }
        try {
            Uri parse = Uri.parse(str);
            StringBuilder sb = new StringBuilder();
            String scheme = parse.getScheme();
            String host = parse.getHost();
            int port = parse.getPort();
            if (host != null) {
                if (scheme != null) {
                    sb.append(scheme);
                    sb.append("://");
                }
                sb.append(host);
                if (port > 0) {
                    sb.append(':');
                    sb.append(port);
                }
            }
            String sb2 = sb.toString();
            String encodedPath = parse.getEncodedPath();
            if (map != null && (encodedQuery = parse.getEncodedQuery()) != null) {
                for (String str2 : encodedQuery.split(f8368j)) {
                    int indexOf = str2.indexOf(k);
                    if (indexOf >= 0) {
                        map.put(URLDecoder.decode(str2.substring(0, indexOf), "UTF-8"), URLDecoder.decode(str2.substring(indexOf + 1), "UTF-8"));
                    } else {
                        map.put(URLDecoder.decode(str2, "UTF-8"), "");
                    }
                }
            }
            return new Pair<>(sb2, encodedPath);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new IOException("parseUrl url is fail !!!");
        }
    }

    public static Pair<String, String> b(String str, Map<String, List<String>> map) {
        String encodedQuery;
        String[] split;
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            throw new IOException("parseUrl url is null !!!");
        }
        try {
            Uri parse = Uri.parse(str);
            StringBuilder sb = new StringBuilder();
            String scheme = parse.getScheme();
            String host = parse.getHost();
            int port = parse.getPort();
            if (host != null) {
                if (scheme != null) {
                    sb.append(scheme);
                    sb.append("://");
                }
                sb.append(host);
                if (port > 0) {
                    sb.append(':');
                    sb.append(port);
                }
            }
            String sb2 = sb.toString();
            String encodedPath = parse.getEncodedPath();
            if (map != null && (encodedQuery = parse.getEncodedQuery()) != null) {
                for (String str2 : encodedQuery.split(f8368j)) {
                    int indexOf = str2.indexOf(k);
                    if (indexOf >= 0) {
                        String decode = URLDecoder.decode(str2.substring(0, indexOf), "UTF-8");
                        List<String> list = map.get(decode);
                        if (list == null) {
                            list = new LinkedList<>();
                        }
                        list.add(URLDecoder.decode(str2.substring(indexOf + 1), "UTF-8"));
                        map.put(decode, list);
                    } else {
                        String decode2 = URLDecoder.decode(str2, "UTF-8");
                        List<String> list2 = map.get(decode2);
                        if (list2 == null) {
                            list2 = new LinkedList<>();
                        }
                        list2.add("");
                        map.put(decode2, list2);
                    }
                }
            }
            return new Pair<>(sb2, encodedPath);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new IOException("parseUrl url is fail !!!");
        }
    }

    public static String a(Map<String, List<String>> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String b2 = b(entry.getKey(), str);
            List<String> value = entry.getValue();
            if (value != null && value.size() > 0) {
                Iterator<String> it = value.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    String b3 = next != null ? b(next, str) : "";
                    if (sb.length() > 0) {
                        sb.append(f8368j);
                    }
                    sb.append(b2);
                    if (!b3.isEmpty()) {
                        sb.append(k);
                        sb.append(b3);
                    }
                }
            }
        }
        return sb.toString();
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

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        int length = str.length();
        int length2 = str2.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        int i5 = -1;
        while (i2 < length) {
            if (i3 < length2 && (str2.charAt(i3) == '?' || str2.charAt(i3) == str.charAt(i2))) {
                i2++;
                i3++;
            } else if (i3 < length2 && str2.charAt(i3) == '*') {
                i5 = i2;
                i4 = i3;
                i3++;
            } else if (i4 == -1) {
                return false;
            } else {
                i3 = i4 + 1;
                i5++;
                i2 = i5;
            }
        }
        while (i3 < length2) {
            if (str2.charAt(i3) != '*') {
                return false;
            }
            i3++;
        }
        return true;
    }

    public static URI a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            try {
                return new URI(str);
            } catch (URISyntaxException unused) {
                return URI.create(str.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            }
        } catch (Exception unused2) {
            return c(str);
        }
    }

    private static URI c(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            int indexOf = str.indexOf("?");
            if (indexOf > 0) {
                str = str.substring(0, indexOf);
            }
            return URI.create(str);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (d(str) || e(str) || f(str) || g(str)) {
            try {
                return f8367i.matcher(str).matches();
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return false;
    }

    private static boolean d(String str) {
        return str != null && str.length() > 7 && str.substring(0, 8).equalsIgnoreCase("https://");
    }

    private static boolean e(String str) {
        return str != null && str.length() > 6 && str.substring(0, 7).equalsIgnoreCase(DefaultWebClient.HTTP_SCHEME);
    }

    private static boolean f(String str) {
        return str != null && str.length() > 5 && str.substring(0, 6).equalsIgnoreCase("wss://");
    }

    private static boolean g(String str) {
        return str != null && str.length() > 4 && str.substring(0, 5).equalsIgnoreCase("ws://");
    }

    public static String h(String str) {
        int indexOf = str.indexOf("?");
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            return substring + str.substring(indexOf).replaceAll("\\+", "%20");
        }
        return str;
    }
}
