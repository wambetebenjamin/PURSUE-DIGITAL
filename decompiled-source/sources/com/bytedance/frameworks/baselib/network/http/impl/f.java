package com.bytedance.frameworks.baselib.network.http.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.librarian.c;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: PersistentCookieStore.java */
/* loaded from: classes2.dex */
public class f implements c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7872a = "f";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7873b = "cookieStore";

    /* renamed from: c  reason: collision with root package name */
    private static final String f7874c = "|";

    /* renamed from: d  reason: collision with root package name */
    private static final String f7875d = "\\|";

    /* renamed from: f  reason: collision with root package name */
    private static final Map<String, String> f7876f = new LinkedHashMap();

    /* renamed from: g  reason: collision with root package name */
    private static String f7877g;

    /* renamed from: e  reason: collision with root package name */
    private final SharedPreferences f7878e;

    /* renamed from: h  reason: collision with root package name */
    private final Map<URI, Set<h>> f7879h = new LinkedHashMap();

    public static void d() {
        f7877g = "ttnetCookieStore";
    }

    public f(Context context) {
        String str = f7877g;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str == null ? f7873b : str, Build.VERSION.SDK_INT < 11 ? 0 : 4);
        this.f7878e = sharedPreferences;
        a(sharedPreferences.getAll(), true);
        a((Map<String, ?>) f7876f, false);
    }

    private synchronized void a(Map<String, ?> map, boolean z) {
        if (map != null) {
            if (!map.isEmpty()) {
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    try {
                        URI uri = new URI(entry.getKey().split(f7875d, 2)[0]);
                        h a2 = h.a((String) entry.getValue());
                        Set<h> set = this.f7879h.get(uri);
                        if (set == null) {
                            set = new HashSet<>();
                            this.f7879h.put(uri, set);
                        }
                        if (a2 != null) {
                            set.add(a2);
                        }
                    } catch (URISyntaxException e2) {
                        e2.printStackTrace();
                    }
                }
                if (z) {
                    com.bytedance.common.utility.concurrent.e.submitRunnable(new Runnable() { // from class: com.bytedance.frameworks.baselib.network.http.impl.f.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                f.this.e();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        Map<URI, Set<h>> map = this.f7879h;
        if (map != null && !map.isEmpty()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<URI, Set<h>> entry : this.f7879h.entrySet()) {
                if (entry != null) {
                    URI key = entry.getKey();
                    if (key.getScheme() != null && key.getScheme().equals("https")) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (linkedHashMap.isEmpty()) {
                return;
            }
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                if (entry2 != null) {
                    URI uri = (URI) entry2.getKey();
                    Set<h> set = (Set) entry2.getValue();
                    URI uri2 = new URI(uri.toString().replace("https:", "http:"));
                    Set<h> set2 = this.f7879h.get(uri2);
                    if (set2 != null && !set2.isEmpty()) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        for (h hVar : set2) {
                            boolean z = false;
                            for (h hVar2 : set) {
                                if (hVar != null && hVar2 != null && hVar.b().equals(hVar2.b()) && hVar2.c().longValue() >= hVar.c().longValue()) {
                                    linkedHashSet.add(hVar2);
                                    z = true;
                                }
                            }
                            if (!z) {
                                linkedHashSet.add(hVar);
                            }
                        }
                        for (h hVar3 : set) {
                            if (!linkedHashSet.contains(hVar3)) {
                                linkedHashSet.add(hVar3);
                            }
                        }
                        this.f7879h.remove(uri);
                        this.f7879h.put(uri2, linkedHashSet);
                    }
                    this.f7879h.remove(uri);
                    this.f7879h.put(uri2, set);
                }
            }
            Map<URI, Set<h>> map2 = this.f7879h;
            if (map2 != null && !map2.isEmpty()) {
                SharedPreferences.Editor edit = this.f7878e.edit();
                edit.clear();
                for (Map.Entry<URI, Set<h>> entry3 : this.f7879h.entrySet()) {
                    URI key2 = entry3.getKey();
                    for (h hVar4 : entry3.getValue()) {
                        String str = key2.toString() + f7874c + hVar4.b().f();
                        String d2 = hVar4.d();
                        if (hVar4.b().e() > 0) {
                            edit.putString(str, d2);
                        } else {
                            f7876f.put(str, d2);
                        }
                    }
                }
                com.bytedance.common.utility.persistent.b.apply(edit);
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized void a(URI uri, e eVar) {
        URI c2 = c(uri, eVar);
        Set<h> set = this.f7879h.get(c2);
        h hVar = new h(eVar);
        if (set == null) {
            set = new HashSet<>();
            this.f7879h.put(c2, set);
        } else {
            set.remove(hVar);
        }
        set.add(hVar);
        a(c2, hVar);
    }

    private static URI c(URI uri, e eVar) {
        if (eVar.d() != null) {
            String d2 = eVar.d();
            if (d2.charAt(0) == '.') {
                d2 = d2.substring(1);
            }
            try {
                return new URI("http", d2, eVar.g() == null ? c.a.SEPARATOR : eVar.g(), null);
            } catch (URISyntaxException e2) {
                Log.w(f7872a, e2);
                return uri;
            }
        }
        return uri;
    }

    private void a(URI uri, h hVar) {
        String str = uri.toString() + f7874c + hVar.b().f();
        String d2 = hVar.d();
        if (hVar.b().e() > 0) {
            SharedPreferences.Editor edit = this.f7878e.edit();
            edit.putString(str, d2);
            edit.apply();
            return;
        }
        f7876f.put(str, d2);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<e> a(URI uri) {
        return b(uri);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<e> a() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (URI uri : this.f7879h.keySet()) {
            arrayList.addAll(b(uri));
        }
        return arrayList;
    }

    private List<e> b(URI uri) {
        ArrayList<h> arrayList = new ArrayList();
        for (URI uri2 : this.f7879h.keySet()) {
            if (a(uri2.getHost(), uri.getHost())) {
                String path = uri.getPath();
                if (TextUtils.isEmpty(path)) {
                    path = c.a.SEPARATOR;
                }
                if (b(uri2.getPath(), path)) {
                    arrayList.addAll(this.f7879h.get(uri2));
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (h hVar : arrayList) {
            if (hVar.a()) {
                arrayList3.add(hVar);
            } else {
                arrayList2.add(hVar.b());
            }
        }
        if (!arrayList3.isEmpty()) {
            a(uri, arrayList3);
        }
        return arrayList2;
    }

    private boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (str2.length() < str.length()) {
            return str.equalsIgnoreCase(c.a.DOT + str2);
        }
        return str2.equalsIgnoreCase(str) || str2.toLowerCase().endsWith(new StringBuilder().append(c.a.DOT).append(str.toLowerCase()).toString());
    }

    private boolean b(String str, String str2) {
        return str2.equals(str) || (str2.startsWith(str) && str.charAt(str.length() - 1) == '/') || (str2.startsWith(str) && str2.substring(str.length()).charAt(0) == '/');
    }

    private void a(URI uri, List<h> list) {
        SharedPreferences.Editor edit = this.f7878e.edit();
        Iterator<h> it = list.iterator();
        while (it.hasNext()) {
            String str = uri.toString() + f7874c + it.next().b().f();
            edit.remove(str);
            f7876f.remove(str);
        }
        edit.apply();
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<URI> b() {
        return new ArrayList(this.f7879h.keySet());
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized boolean b(URI uri, e eVar) {
        boolean remove;
        Set<h> set = this.f7879h.get(uri);
        h hVar = new h(eVar);
        remove = set != null ? set.remove(hVar) : false;
        if (remove) {
            b(uri, hVar);
        }
        return remove;
    }

    private void b(URI uri, h hVar) {
        SharedPreferences.Editor edit = this.f7878e.edit();
        String str = uri.toString() + f7874c + hVar.b().f();
        edit.remove(str);
        edit.apply();
        f7876f.remove(str);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized boolean c() {
        this.f7879h.clear();
        f();
        return true;
    }

    private void f() {
        this.f7878e.edit().clear().apply();
        f7876f.clear();
    }
}
