package com.bytedance.frameworks.baselib.network.http.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: CacheControlParser.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8236a = "\\s*([\\w\\-]+)\\s*(=)?\\s*(\\d+|\\\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)+\\\")?\\s*";

    /* renamed from: b  reason: collision with root package name */
    private static final Pattern f8237b = Pattern.compile(f8236a);

    /* renamed from: c  reason: collision with root package name */
    private HashMap<EnumC0116a, String> f8238c = new HashMap<>();

    /* compiled from: CacheControlParser.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.parser.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0116a {
        MAXAGE,
        MAXSTALE,
        MINFRESH,
        NOCACHE,
        NOSTORE,
        NOTRANSFORM,
        ONLYIFCACHED,
        MUSTREVALIDATE,
        PRIVATE,
        PROXYREVALIDATE,
        PUBLIC,
        SMAXAGE,
        UNKNOWN;

        public static EnumC0116a a(String str) {
            try {
                return valueOf(str.toUpperCase().replaceAll("-", ""));
            } catch (Exception unused) {
                return UNKNOWN;
            }
        }
    }

    public a(String str) {
        try {
            Matcher matcher = f8237b.matcher(str);
            while (matcher.find()) {
                EnumC0116a a2 = EnumC0116a.a(matcher.group(1));
                if (a2 != EnumC0116a.UNKNOWN) {
                    this.f8238c.put(a2, matcher.group(3));
                }
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    public Map<EnumC0116a, String> a() {
        return this.f8238c;
    }

    public String a(EnumC0116a enumC0116a) {
        return this.f8238c.get(enumC0116a);
    }

    public Iterator<EnumC0116a> b() {
        return this.f8238c.keySet().iterator();
    }
}
