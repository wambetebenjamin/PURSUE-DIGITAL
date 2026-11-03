package com.bytedance.frameworks.baselib.network.http.impl;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.meituan.robust.Constants;
import gsdk.impl.account.toutiao.da;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/* compiled from: HttpCookie.java */
/* loaded from: classes2.dex */
public class e implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f7854a;
    private static Pattern n = null;
    private static Pattern o = null;
    private static final String p = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String q = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
    private static final ThreadLocal<DateFormat> r;
    private static final String[] s;

    /* renamed from: b  reason: collision with root package name */
    private String f7855b;

    /* renamed from: c  reason: collision with root package name */
    private String f7856c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f7857d;

    /* renamed from: e  reason: collision with root package name */
    private String f7858e;

    /* renamed from: g  reason: collision with root package name */
    private final String f7860g;

    /* renamed from: h  reason: collision with root package name */
    private String f7861h;

    /* renamed from: i  reason: collision with root package name */
    private String f7862i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f7863j;
    private boolean k;
    private String l;

    /* renamed from: f  reason: collision with root package name */
    private long f7859f = -1;
    private int m = 0;

    static {
        HashSet hashSet = new HashSet();
        f7854a = hashSet;
        hashSet.add("comment");
        hashSet.add("commenturl");
        hashSet.add("discard");
        hashSet.add("domain");
        hashSet.add("expires");
        hashSet.add("httponly");
        hashSet.add("max-age");
        hashSet.add("path");
        hashSet.add(da.bc);
        hashSet.add("secure");
        hashSet.add("version");
        n = null;
        o = null;
        try {
            n = Pattern.compile(p, 2);
            o = Pattern.compile(q, 2);
        } catch (PatternSyntaxException unused) {
        }
        r = new ThreadLocal<DateFormat>() { // from class: com.bytedance.frameworks.baselib.network.http.impl.e.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            /* renamed from: a */
            public DateFormat initialValue() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat;
            }
        };
        s = new String[]{"EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        String lowerCase2 = str.toLowerCase(Locale.US);
        if (lowerCase.equals(lowerCase2) && (a(lowerCase, 0) || h(lowerCase))) {
            return true;
        }
        if (!a(lowerCase, 0)) {
            return lowerCase2.equals(".local");
        }
        if (lowerCase2.length() == lowerCase.length() + 1 && lowerCase2.startsWith(c.a.DOT) && lowerCase2.endsWith(lowerCase) && a(lowerCase2, 1)) {
            return true;
        }
        if (lowerCase.length() <= lowerCase2.length() || !lowerCase.endsWith(lowerCase2)) {
            return false;
        }
        return (lowerCase2.startsWith(c.a.DOT) && a(lowerCase2, 1)) || lowerCase2.equals(".local");
    }

    public static boolean a(e eVar, URI uri) {
        return j(uri.getPath()).startsWith(j(eVar.g()));
    }

    public static boolean b(e eVar, URI uri) {
        return !eVar.i() || "https".equalsIgnoreCase(uri.getScheme());
    }

    public static boolean c(e eVar, URI uri) {
        if (eVar.h() == null) {
            return true;
        }
        return Arrays.asList(eVar.h().split(",")).contains(Integer.toString(h.a(uri.getScheme(), uri.getPort())));
    }

    private static String j(String str) {
        return str == null ? c.a.SEPARATOR : str.endsWith(c.a.SEPARATOR) ? str : str + c.a.SEPARATOR;
    }

    private static boolean a(String str, int i2) {
        int indexOf = str.indexOf(46, i2 + 1);
        return indexOf != -1 && indexOf < str.length() - 1;
    }

    public static List<e> a(String str) {
        return new a(str).a();
    }

    /* compiled from: HttpCookie.java */
    /* loaded from: classes2.dex */
    static class a {

        /* renamed from: d  reason: collision with root package name */
        private static final String f7864d = ",;= \t";

        /* renamed from: e  reason: collision with root package name */
        private static final String f7865e = " \t";

        /* renamed from: f  reason: collision with root package name */
        private final String f7869f;

        /* renamed from: g  reason: collision with root package name */
        private final String f7870g;

        /* renamed from: h  reason: collision with root package name */
        private int f7871h = 0;

        /* renamed from: a  reason: collision with root package name */
        boolean f7866a = false;

        /* renamed from: b  reason: collision with root package name */
        boolean f7867b = false;

        /* renamed from: c  reason: collision with root package name */
        boolean f7868c = false;

        a(String str) {
            this.f7869f = str;
            this.f7870g = str.toLowerCase(Locale.US);
        }

        public List<e> a() {
            int i2;
            ArrayList arrayList = new ArrayList(2);
            if (this.f7870g.startsWith("set-cookie2:")) {
                this.f7871h += 12;
                this.f7868c = true;
                i2 = 0;
            } else {
                if (this.f7870g.startsWith("set-cookie:")) {
                    this.f7871h += 11;
                }
                i2 = 1;
            }
            while (true) {
                String a2 = a(false);
                if (a2 == null) {
                    if (arrayList.isEmpty()) {
                        throw new IllegalArgumentException("No cookies in " + this.f7869f);
                    }
                    return arrayList;
                } else if (!b()) {
                    throw new IllegalArgumentException("Expected '=' after " + a2 + " in " + this.f7869f);
                } else {
                    e eVar = new e(a2, a(i2 != 0 ? Constants.PACKNAME_END : ",;"));
                    eVar.m = i2 ^ 1;
                    arrayList.add(eVar);
                    while (true) {
                        c();
                        if (this.f7871h != this.f7869f.length()) {
                            if (this.f7869f.charAt(this.f7871h) == ',') {
                                this.f7871h++;
                                break;
                            }
                            if (this.f7869f.charAt(this.f7871h) == ';') {
                                this.f7871h++;
                            }
                            String a3 = a(true);
                            if (a3 != null) {
                                a(eVar, a3, b() ? a((i2 != 0 || "expires".equals(a3) || da.bc.equals(a3)) ? Constants.PACKNAME_END : ";,") : null);
                            }
                        }
                    }
                }
            }
        }

        private void a(e eVar, String str, String str2) {
            if (str.equals("comment") && eVar.f7855b == null) {
                eVar.f7855b = str2;
            } else if (str.equals("commenturl") && eVar.f7856c == null) {
                eVar.f7856c = str2;
            } else if (str.equals("discard")) {
                eVar.f7857d = true;
            } else if (str.equals("domain") && eVar.f7858e == null) {
                if (!TextUtils.isEmpty(str2) && str2.charAt(0) == '.') {
                    str2 = str2.substring(1);
                }
                eVar.f7858e = str2;
            } else if (str.equals("expires")) {
                this.f7866a = true;
                if (eVar.f7859f == -1) {
                    Date i2 = e.i(str2);
                    if (i2 != null) {
                        eVar.a(i2);
                    } else {
                        eVar.f7859f = 0L;
                    }
                }
            } else if (str.equals("max-age")) {
                try {
                    long parseLong = Long.parseLong(str2);
                    this.f7867b = true;
                    eVar.f7859f = parseLong;
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Invalid max-age: " + str2);
                }
            } else if (str.equals("path") && eVar.f7861h == null) {
                eVar.f7861h = str2;
            } else if (str.equals(da.bc) && eVar.f7862i == null) {
                if (str2 == null) {
                    str2 = "";
                }
                eVar.f7862i = str2;
            } else if (str.equals("secure")) {
                eVar.f7863j = true;
            } else if (str.equals("httponly")) {
                eVar.k = true;
            } else if (!str.equals("version") || this.f7868c) {
            } else {
                eVar.m = Integer.parseInt(str2);
            }
        }

        private String a(boolean z) {
            c();
            int b2 = b(f7864d);
            String str = z ? this.f7870g : this.f7869f;
            int i2 = this.f7871h;
            String substring = i2 < b2 ? str.substring(i2, b2) : null;
            this.f7871h = b2;
            return substring;
        }

        private boolean b() {
            c();
            if (this.f7871h >= this.f7869f.length() || this.f7869f.charAt(this.f7871h) != '=') {
                return false;
            }
            this.f7871h++;
            return true;
        }

        private String a(String str) {
            c();
            int b2 = b(str);
            String substring = this.f7869f.substring(this.f7871h, b2);
            this.f7871h = b2;
            return substring;
        }

        private int b(String str) {
            for (int i2 = this.f7871h; i2 < this.f7869f.length(); i2++) {
                if (str.indexOf(this.f7869f.charAt(i2)) != -1) {
                    return i2;
                }
            }
            return this.f7869f.length();
        }

        private void c() {
            while (this.f7871h < this.f7869f.length() && f7865e.indexOf(this.f7869f.charAt(this.f7871h)) != -1) {
                this.f7871h++;
            }
        }
    }

    public e(String str, String str2) {
        String trim = str.trim();
        if (!k(trim)) {
            throw new IllegalArgumentException("Invalid name: " + str);
        }
        this.f7860g = trim;
        this.l = str2;
    }

    private boolean k(String str) {
        boolean z = (str.length() == 0 || str.startsWith("$") || f7854a.contains(str.toLowerCase(Locale.US))) ? false : true;
        if (z) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (charAt < 0 || charAt >= 127 || charAt == ';' || charAt == ',') {
                    return false;
                }
                if (Character.isWhitespace(charAt) && charAt != ' ') {
                    return false;
                }
            }
        }
        return z;
    }

    public String a() {
        return this.f7855b;
    }

    public String b() {
        return this.f7856c;
    }

    public boolean c() {
        return this.f7857d;
    }

    public String d() {
        return this.f7858e;
    }

    public long e() {
        return this.f7859f;
    }

    public String f() {
        return this.f7860g;
    }

    public String g() {
        return this.f7861h;
    }

    public String h() {
        return this.f7862i;
    }

    public boolean i() {
        return this.f7863j;
    }

    public boolean j() {
        return this.k;
    }

    public String k() {
        return this.l;
    }

    public int l() {
        return this.m;
    }

    public boolean m() {
        long j2 = this.f7859f;
        return j2 != -1 && j2 <= 0;
    }

    public void b(String str) {
        this.f7855b = str;
    }

    public void c(String str) {
        this.f7856c = str;
    }

    public void a(boolean z) {
        this.f7857d = z;
    }

    public void d(String str) {
        this.f7858e = str == null ? null : str.toLowerCase(Locale.US);
    }

    public void a(long j2) {
        this.f7859f = j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Date date) {
        this.f7859f = (date.getTime() - System.currentTimeMillis()) / 1000;
    }

    public void e(String str) {
        this.f7861h = str;
    }

    public void f(String str) {
        this.f7862i = str;
    }

    public void b(boolean z) {
        this.f7863j = z;
    }

    public void c(boolean z) {
        this.k = z;
    }

    public void g(String str) {
        this.l = str;
    }

    public void a(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("Bad version: " + i2);
        }
        this.m = i2;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return this.f7860g.equalsIgnoreCase(eVar.f()) && ((str = this.f7858e) == null ? eVar.f7858e == null : str.equalsIgnoreCase(eVar.f7858e)) && a((Object) this.f7861h, (Object) eVar.f7861h);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f7860g.toLowerCase(Locale.US).hashCode();
        String str = this.f7858e;
        int hashCode2 = hashCode + (str == null ? 0 : str.toLowerCase(Locale.US).hashCode());
        String str2 = this.f7861h;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return this.f7860g + "=" + this.l;
    }

    private void a(StringBuilder sb, String str, String str2) {
        if (str2 == null || sb == null) {
            return;
        }
        sb.append(";$");
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append("\"");
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean h(String str) {
        try {
            if (n.matcher(str).matches()) {
                return true;
            }
            return o.matcher(str).matches();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static Date i(String str) {
        try {
            return r.get().parse(str);
        } catch (ParseException unused) {
            for (String str2 : s) {
                try {
                    return new SimpleDateFormat(str2, Locale.US).parse(str);
                } catch (ParseException unused2) {
                }
            }
            return null;
        }
    }
}
