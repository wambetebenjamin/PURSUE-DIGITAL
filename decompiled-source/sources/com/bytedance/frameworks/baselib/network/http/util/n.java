package com.bytedance.frameworks.baselib.network.http.util;

import com.bytedance.librarian.c;
import java.net.URI;
import java.net.URISyntaxException;
/* compiled from: URIUtils.java */
/* loaded from: classes2.dex */
public class n {
    public static URI a(String str, String str2, int i2, String str3, String str4, String str5) {
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
        return new URI(sb.toString());
    }

    public static URI a(URI uri, e eVar, boolean z) {
        if (uri != null) {
            if (eVar != null) {
                return a(eVar.c(), eVar.a(), eVar.b(), uri.getRawPath(), uri.getRawQuery(), z ? null : uri.getRawFragment());
            }
            return a(null, null, -1, uri.getRawPath(), uri.getRawQuery(), z ? null : uri.getRawFragment());
        }
        throw new IllegalArgumentException("URI may nor be null");
    }

    public static URI a(URI uri, e eVar) {
        return a(uri, eVar, false);
    }

    public static URI a(URI uri, String str) {
        return a(uri, URI.create(str));
    }

    public static URI a(URI uri, URI uri2) {
        if (uri != null) {
            if (uri2 == null) {
                throw new IllegalArgumentException("Reference URI may nor be null");
            }
            boolean z = uri2.toString().length() == 0;
            if (z) {
                uri2 = URI.create("#");
            }
            URI resolve = uri.resolve(uri2);
            if (z) {
                String uri3 = resolve.toString();
                return URI.create(uri3.substring(0, uri3.indexOf(35)));
            }
            return resolve;
        }
        throw new IllegalArgumentException("Base URI may nor be null");
    }

    private n() {
    }

    public static URI a(String str) {
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            return null;
        }
        try {
            try {
                return new URI(str);
            } catch (Exception unused) {
                return b(str);
            }
        } catch (URISyntaxException unused2) {
            return URI.create(str.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
        }
    }

    public static URI b(String str) {
        if (com.bytedance.common.utility.o.isEmpty(str)) {
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
}
