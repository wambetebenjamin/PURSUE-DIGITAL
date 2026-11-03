package com.bytedance.frameworks.baselib.network.http.ok3.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.client.SsCall;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Locale;
import okhttp3.OkHttpClient;
import okhttp3.internal.Version;
/* compiled from: SsOkHttp3Client.java */
/* loaded from: classes2.dex */
public class o extends com.bytedance.frameworks.baselib.network.http.h {
    private static e A = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f8116a = "SsOkHttp3Client";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8117b = "tt-ok/3.12.13.2-rc.2";

    /* renamed from: c  reason: collision with root package name */
    public static final int f8118c = 0;

    /* renamed from: d  reason: collision with root package name */
    public static final int f8119d = -1;

    /* renamed from: e  reason: collision with root package name */
    public static final int f8120e = 2;

    /* renamed from: f  reason: collision with root package name */
    public static final int f8121f = 3;

    /* renamed from: g  reason: collision with root package name */
    public static final int f8122g = 10;

    /* renamed from: h  reason: collision with root package name */
    private static volatile Proxy f8123h = null;

    /* renamed from: i  reason: collision with root package name */
    public static final int f8124i = 6;

    /* renamed from: j  reason: collision with root package name */
    public static final int f8125j = 7;
    public static final int k = 8;
    public static final int l = 9;
    private static volatile o m = null;
    private static volatile boolean n = false;
    private static volatile int o = -1;
    private static volatile String p = "";
    private static final Object q = new Object();
    private static final int r = 0;
    private static int s = 0;
    private static volatile String t = "";
    private static String u = "tt-ok/3.10.0.2";
    private static volatile com.bytedance.frameworks.baselib.network.http.ok3.b v;
    private static Context y;
    private static g z;

    public static o a(Context context) {
        if (m == null) {
            synchronized (o.class) {
                if (m == null) {
                    m = new o(context);
                }
            }
        }
        return m;
    }

    private o(Context context) {
        String str;
        String str2 = null;
        try {
            try {
                str2 = Version.userAgent();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable unused) {
            Field declaredField = Class.forName("okhttp3.internal.Util").getDeclaredField("userAgent");
            declaredField.setAccessible(true);
            str2 = (String) declaredField.get(null);
        }
        if (!TextUtils.isEmpty(str2)) {
            if (str2.startsWith("okhttp/")) {
                str = str2.replaceFirst("okhttp/", "tt-ok/");
            } else {
                str = "tt-ok/" + str2;
            }
            u = str;
        }
        y = context.getApplicationContext();
        z = new g();
        com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.k.a().b(y);
        com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.e.a().a(new com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h() { // from class: com.bytedance.frameworks.baselib.network.http.ok3.impl.o.1
            @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h
            public Context a() {
                return o.y;
            }

            @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h
            public String[] b() {
                if (o.v != null) {
                    return o.v.c();
                }
                return null;
            }

            @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h
            public String c() {
                if (o.v != null) {
                    return String.valueOf(o.v.a());
                }
                return null;
            }

            @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h
            public String d() {
                if (o.v != null) {
                    return o.v.b();
                }
                return null;
            }

            @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns.h
            public String[] e() {
                if (o.v != null) {
                    return o.v.d();
                }
                return null;
            }
        });
    }

    public OkHttpClient a() {
        return a(true);
    }

    public static OkHttpClient d() {
        return z.a(false);
    }

    public OkHttpClient a(boolean z2) {
        g gVar = z;
        if (gVar == null) {
            throw new IllegalArgumentException("SsOkHttp3Client is not init !!!");
        }
        return gVar.a(z2);
    }

    @Override // com.bytedance.retrofit2.client.Client
    public SsCall newSsCall(Request request) {
        try {
            Request a2 = com.bytedance.frameworks.baselib.network.http.storeregion.b.a().a(request);
            if (a2 != null) {
                request = a2;
            }
        } catch (Throwable unused) {
        }
        Request a3 = com.bytedance.frameworks.baselib.network.queryfilter.d.a().a(request);
        if (a3 != null) {
            request = a3;
        }
        return new u(request);
    }

    public static String e() {
        if (TextUtils.isEmpty(t)) {
            StringBuilder sb = new StringBuilder();
            sb.append(y.getPackageName());
            sb.append('/');
            sb.append(c(y));
            sb.append(" (Linux; U; Android ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("; ");
            sb.append(Locale.getDefault().toString());
            String str = Build.MODEL;
            if (str.length() > 0) {
                sb.append("; ");
                sb.append(str);
            }
            String str2 = Build.ID;
            if (str2.length() > 0) {
                sb.append("; Build/");
                sb.append(str2);
            }
            sb.append(Constants.PACKNAME_END);
            sb.append(u);
            sb.append(')');
            t = sb.toString();
        }
        return t;
    }

    private static int c(Context context) {
        int i2;
        synchronized (q) {
            if (s == 0) {
                try {
                    s = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            i2 = s;
        }
        return i2;
    }

    public void a(e eVar) {
        A = eVar;
    }

    public static e i() {
        return A;
    }

    public static void b(boolean z2) {
        n = z2;
    }

    public static boolean f() {
        return n;
    }

    public static void a(int i2) {
        o = i2;
    }

    public static int b() {
        return o;
    }

    public static void a(String str) {
        p = str;
    }

    public static String g() {
        return p;
    }

    public static void b(String str) {
        if (str == null) {
            f8123h = null;
            return;
        }
        String[] split = str.split(Constants.PACKNAME_END);
        if (split.length <= 0) {
            throw new IllegalArgumentException("Invalid proxy rule:" + str);
        }
        String[] split2 = split[0].trim().split("=");
        if (split2.length != 2) {
            throw new IllegalArgumentException("Invalid proxy rule:" + str);
        }
        String lowerCase = split2[0].trim().toLowerCase();
        String[] split3 = split2[1].trim().split(CertificateUtil.DELIMITER);
        if (split3.length != 2) {
            throw new IllegalArgumentException("Invalid proxy rule:" + str);
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(split3[0], Integer.parseInt(split3[1]));
        if (lowerCase.equals("http") || lowerCase.equals("https")) {
            f8123h = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
        } else if (lowerCase.equals("socks") || lowerCase.equals("socks4") || lowerCase.equals("socks5")) {
            f8123h = new Proxy(Proxy.Type.SOCKS, inetSocketAddress);
        }
    }

    public static Proxy h() {
        return f8123h;
    }

    public static void a(com.bytedance.frameworks.baselib.network.http.ok3.b bVar) {
        v = bVar;
    }

    public static String j() {
        return u;
    }

    public static com.bytedance.frameworks.baselib.network.http.ok3.b k() {
        return v;
    }

    public static Context l() {
        return y;
    }

    public static String a(Request request) {
        if (request == null || request.getMetrics() == null) {
            return "null-metrics";
        }
        return "fallback_reason=" + o + ",fallback-message=" + Base64.encodeToString(p.getBytes(), 2) + ",request-fallback-reason=" + request.getMetrics().requestFallbackReason + ",request-fallback-message=" + Base64.encodeToString(request.getMetrics().requestFallbackMessage.getBytes(), 2);
    }
}
