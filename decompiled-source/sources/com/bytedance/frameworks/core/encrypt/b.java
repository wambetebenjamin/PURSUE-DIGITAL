package com.bytedance.frameworks.core.encrypt;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.frameworks.encryptor.EncryptorUtil;
import com.bytedance.librarian.c;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/* compiled from: RequestEncryptUtils.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8528a = "RequestEncryptUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8529b = "device_id";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8530c = "device_type";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8531d = "device_brand";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8532e = "uuid";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8533f = "openudid";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8534g = "ss_queries";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8535h = "X-SS-QUERIES";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8536i = "ss_encrypt";

    /* renamed from: j  reason: collision with root package name */
    private static a f8537j = null;
    private static boolean k = false;
    private static final String l = "&";
    private static final String m = "=";
    private static final String n = "ISO-8859-1";

    /* compiled from: RequestEncryptUtils.java */
    /* loaded from: classes2.dex */
    public interface a {
        boolean isSsQueriesHeaderOpen();

        boolean isSsQueriesOpen();

        boolean isSsQueriesPlaintextOpen();
    }

    public static void setEncryptConfig(a aVar) {
        f8537j = aVar;
    }

    public static void setDebug(boolean z) {
        k = z;
    }

    public static String tryEncryptRequest(String str, List<Pair<String, String>> list) {
        if (TextUtils.isEmpty(str) || f8537j == null || k) {
            return str;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            Uri parse = Uri.parse(str);
            parseQueries(parse, linkedHashMap);
            LinkedList linkedList = new LinkedList();
            a(linkedHashMap, linkedList, "device_id");
            a(linkedHashMap, linkedList, "device_type");
            a(linkedHashMap, linkedList, "device_brand");
            a(linkedHashMap, linkedList, "uuid");
            a(linkedHashMap, linkedList, "openudid");
            String a2 = a((List<Pair<String, List<String>>>) linkedList, false, "UTF-8");
            if (TextUtils.isEmpty(a2)) {
                return str;
            }
            byte[] bytes = a2.getBytes();
            byte[] encrypt = EncryptorUtil.encrypt(bytes, bytes.length);
            if (encrypt == null) {
                return str;
            }
            String encodeToString = Base64.encodeToString(encrypt, 2);
            LinkedList linkedList2 = new LinkedList();
            if (f8537j.isSsQueriesOpen()) {
                LinkedList linkedList3 = new LinkedList();
                linkedList3.add(encodeToString);
                linkedList2.add(new Pair(f8534g, linkedList3));
            }
            if (f8537j.isSsQueriesHeaderOpen() && list != null) {
                list.add(new Pair<>(f8535h, a(encodeToString, "UTF-8")));
            }
            if (!linkedHashMap.isEmpty()) {
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    if (entry != null) {
                        linkedList2.add(new Pair(entry.getKey(), entry.getValue()));
                    }
                }
            }
            return a(parse.getScheme(), parse.getHost(), parse.getPort(), parse.getPath(), a((List<Pair<String, List<String>>>) linkedList2, true, "UTF-8"), parse.getFragment()).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static byte[] tryEncryptRequestBody(byte[] bArr) {
        if (bArr == null || bArr.length == 0 || k) {
            return bArr;
        }
        try {
            return EncryptorUtil.encrypt(bArr, bArr.length);
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private static boolean a(Map<String, List<String>> map, List<Pair<String, List<String>>> list, String str) {
        if (TextUtils.isEmpty(str) || list == null || !map.containsKey(str)) {
            return false;
        }
        list.add(new Pair<>(str, map.get(str)));
        if (f8537j.isSsQueriesPlaintextOpen()) {
            return true;
        }
        map.remove(str);
        return true;
    }

    public static void parseQueries(Uri uri, Map<String, List<String>> map) {
        String[] split;
        if (uri == null) {
            throw new IOException("parseUrl url is null !!!");
        }
        if (map != null) {
            try {
                String encodedQuery = uri.getEncodedQuery();
                if (encodedQuery != null) {
                    for (String str : encodedQuery.split(l)) {
                        int indexOf = str.indexOf(m);
                        if (indexOf >= 0) {
                            String decode = URLDecoder.decode(str.substring(0, indexOf), "UTF-8");
                            List<String> list = map.get(decode);
                            if (list == null) {
                                list = new LinkedList<>();
                            }
                            list.add(URLDecoder.decode(str.substring(indexOf + 1), "UTF-8"));
                            map.put(decode, list);
                        } else {
                            String decode2 = URLDecoder.decode(str, "UTF-8");
                            List<String> list2 = map.get(decode2);
                            if (list2 == null) {
                                list2 = new LinkedList<>();
                            }
                            list2.add("");
                            map.put(decode2, list2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                throw new IOException("parseUrl url is null !!!");
            }
        }
    }

    private static Uri a(String str, String str2, int i2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            if (str != null) {
                sb.append(str);
                sb.append("://");
            }
            sb.append(str2);
            if (i2 > 0) {
                sb.append(':');
                sb.append(i2);
            }
        }
        if (str3 == null || !str3.startsWith(c.a.SEPARATOR)) {
            sb.append('/');
        }
        if (str3 != null) {
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append('?');
            sb.append(str4);
        }
        if (str5 != null) {
            sb.append('#');
            sb.append(str5);
        }
        return Uri.parse(sb.toString());
    }

    private static String a(List<Pair<String, List<String>>> list, boolean z, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, List<String>> pair : list) {
            String str2 = (String) pair.first;
            if (z) {
                str2 = a((String) pair.first, str);
            }
            List<String> list2 = (List) pair.second;
            if (list2 != null && list2.size() > 0) {
                for (String str3 : list2) {
                    if (z) {
                        str3 = str3 != null ? a(str3, str) : "";
                    }
                    if (sb.length() > 0) {
                        sb.append(l);
                    }
                    sb.append(str2);
                    sb.append(m);
                    sb.append(str3);
                }
            }
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
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
