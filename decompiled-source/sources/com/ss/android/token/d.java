package com.ss.android.token;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.ss.android.token.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
/* compiled from: TTTokenManager.java */
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f18539a = false;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f18540b = true;

    /* renamed from: c  reason: collision with root package name */
    private static Set<String> f18541c = Collections.synchronizedSet(new HashSet());

    /* renamed from: d  reason: collision with root package name */
    private static List<h> f18542d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private static boolean f18543e = false;

    /* renamed from: f  reason: collision with root package name */
    private static k f18544f = null;

    /* renamed from: g  reason: collision with root package name */
    private static a f18545g = null;

    /* renamed from: h  reason: collision with root package name */
    private static c f18546h = null;

    /* renamed from: i  reason: collision with root package name */
    private static volatile b f18547i = null;

    /* renamed from: j  reason: collision with root package name */
    private static com.ss.android.d f18548j = null;
    private static final String k = "TTTokenManager";

    /* compiled from: TTTokenManager.java */
    /* loaded from: classes9.dex */
    public interface a {
        void a(Throwable th);
    }

    /* compiled from: TTTokenManager.java */
    /* loaded from: classes9.dex */
    public interface b {
        void a(int i2, String str, String str2);
    }

    /* compiled from: TTTokenManager.java */
    /* loaded from: classes9.dex */
    public interface c {
        void a(String str);

        void a(boolean z);
    }

    public static synchronized void initialize(Context context, com.ss.android.token.b bVar) {
        synchronized (d.class) {
            if (!f18539a) {
                if (f18544f == null) {
                    throw new IllegalStateException("did not call TokenManager.setTokenService()!");
                }
                g.a(context, bVar);
                g.a().a(f18540b);
                f18539a = true;
                if (f18541c.size() != 0) {
                    g.a().a(f18541c);
                    f18541c.clear();
                    f18541c = null;
                }
                e.b();
            }
        }
    }

    public static void a(String str, List<com.ss.android.token.c> list, k.a aVar) {
        if (f18539a) {
            g.a().a(str, list, true, true, aVar);
        }
    }

    public static void a(String str, List<com.ss.android.token.c> list, boolean z, k.a aVar) {
        if (f18539a) {
            g.a().a(str, list, z, true, aVar);
        }
    }

    public static void a(String str, List<com.ss.android.token.c> list, boolean z, boolean z2, k.a aVar) {
        if (f18539a) {
            g.a().a(str, list, z, z2, aVar);
        }
    }

    public static void onSessionDrop(String str, List<com.ss.android.token.c> list, boolean z) {
        if (f18539a) {
            g.a().a(str, list, z);
        }
    }

    public static void setEnableToken(boolean z) {
        if (!f18539a || z == f18540b) {
            return;
        }
        g.a().a(z);
        f18540b = z;
    }

    public static void clearToken() {
        if (f18539a) {
            g.a().c();
        }
    }

    public static Map<String, String> addRequestHeader(String str) {
        Map<String, String> processRequest;
        if (f18539a) {
            Map<String, String> c2 = g.a().c(str);
            if (f18542d != null) {
                if (c2 == null) {
                    c2 = new HashMap<>();
                }
                for (h hVar : f18542d) {
                    if (hVar != null && (processRequest = hVar.processRequest(str)) != null) {
                        c2.putAll(processRequest);
                    }
                }
            }
            return c2;
        }
        return null;
    }

    public static void processResponseHeader(String str, List<com.ss.android.token.c> list) {
        if (f18539a) {
            g.a().a(str, list);
            List<h> list2 = f18542d;
            if (list2 != null) {
                for (h hVar : list2) {
                    if (hVar != null) {
                        hVar.processResponse(str, list);
                    }
                }
            }
        }
    }

    public static boolean isInited() {
        return f18539a;
    }

    public static boolean isTokenEnable() {
        return f18540b;
    }

    public static String getTokenBeatUrl(boolean z, boolean z2, String str) {
        return g.a().a(z, z2, str);
    }

    public static void addConfigHost(Collection<String> collection) {
        if (f18539a) {
            g.a().a(collection);
            return;
        }
        Set<String> set = f18541c;
        if (set != null) {
            set.addAll(collection);
        }
    }

    public static void stopUpdateToken() {
        if (f18539a) {
            g.a().stopUpdateToken();
        }
    }

    public static void startUpdateToken() {
        if (f18539a) {
            g.a().e();
        }
    }

    public static void updateToken() {
        if (f18539a) {
            g.a().a(false, false);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (f18539a) {
            a(k, String.format("session expired, requestHost=%s, requestPath=%s, responseLogid=%s", str, str2, str3));
            g.a().a(false, false, "frontier", str, str2, str3);
        }
    }

    public static String getXTTToken() {
        if (f18539a) {
            return g.a().b();
        }
        return null;
    }

    public static void addTokenProcessor(h hVar) {
        if (f18542d == null) {
            f18542d = new ArrayList();
        }
        f18542d.add(hVar);
    }

    public static void removeTokenProcessor(h hVar) {
        List<h> list = f18542d;
        if (list != null) {
            list.remove(hVar);
        }
    }

    public static void removeAllTokenProcessors() {
        List<h> list = f18542d;
        if (list != null) {
            list.clear();
        }
    }

    public static void a(a aVar) {
        f18545g = aVar;
    }

    public static void a(Throwable th) {
        a aVar = f18545g;
        if (aVar != null) {
            aVar.a(th);
        }
    }

    public static void a(c cVar) {
        f18546h = cVar;
    }

    public static void a(b bVar) {
        f18547i = bVar;
    }

    public static boolean a() {
        if (b() == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) b().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(String str) {
        return "https://" + d() + str;
    }

    public static void a(com.ss.android.d dVar) {
        f18548j = dVar;
    }

    public static void a(String str, Map<String, String> map, Map<String, String> map2, boolean z, k.a aVar) {
        k kVar = f18544f;
        if (kVar != null) {
            kVar.a(str, map, map2, z, aVar);
        }
    }

    public static void a(String str, k.a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("scene", str);
        HashMap hashMap2 = new HashMap();
        com.ss.android.h b2 = b(true);
        if (b2 != null) {
            hashMap2.put(b2.getName(), b2.getValue());
        }
        a("https://" + d() + f.A, (Map<String, String>) hashMap2, (Map<String, String>) hashMap, true, aVar);
    }

    public static void b(final String str, final k.a aVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("logout_from", str);
        a("https://" + d() + f.z, (Map<String, String>) null, (Map<String, String>) hashMap, false, new k.a() { // from class: com.ss.android.token.d.1
            @Override // com.ss.android.token.k.a
            public void a(k.b bVar) {
                k.a aVar2 = k.a.this;
                if (aVar2 != null) {
                    aVar2.a(bVar);
                }
                if (d.f18546h != null) {
                    d.f18546h.a(str);
                }
            }

            @Override // com.ss.android.token.k.a
            public void b(k.b bVar) {
                k.a aVar2 = k.a.this;
                if (aVar2 != null) {
                    aVar2.b(bVar);
                }
            }
        });
    }

    public static Context b() {
        k kVar = f18544f;
        if (kVar != null) {
            return kVar.a();
        }
        return null;
    }

    public static boolean c() {
        k kVar = f18544f;
        if (kVar == null) {
            return false;
        }
        return kVar.b();
    }

    public static void a(String str, String str2) {
        a(3, str, str2);
    }

    public static void a(int i2, String str, String str2) {
        if (f18547i != null) {
            f18547i.a(i2, str, str2);
        }
    }

    public static void a(String str, JSONObject jSONObject) {
        k kVar = f18544f;
        if (kVar != null) {
            kVar.a(str, jSONObject);
        }
    }

    public static void a(boolean z) {
        c cVar = f18546h;
        if (cVar != null) {
            cVar.a(z);
        }
    }

    public static String d() {
        return f18544f.c();
    }

    public static com.ss.android.h b(boolean z) {
        com.ss.android.d dVar = f18548j;
        if (dVar != null) {
            return dVar.getRequestTagHeader(z);
        }
        return null;
    }

    public static void a(k kVar) {
        f18544f = kVar;
    }

    public static boolean e() {
        return f18543e;
    }

    public static void c(boolean z) {
        f18543e = z;
    }

    public static void b(String str, String str2) {
        k kVar = f18544f;
        if (kVar != null) {
            kVar.a(str, str2);
        }
    }
}
