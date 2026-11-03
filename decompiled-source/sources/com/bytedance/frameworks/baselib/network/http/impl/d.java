package com.bytedance.frameworks.baselib.network.http.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* compiled from: CookieStoreImpl.java */
/* loaded from: classes2.dex */
public class d implements c {

    /* renamed from: a  reason: collision with root package name */
    private final Map<URI, List<e>> f7853a = new HashMap();

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized void a(URI uri, e eVar) {
        if (eVar == null) {
            throw new NullPointerException("cookie == null");
        }
        URI b2 = b(uri);
        List<e> list = this.f7853a.get(b2);
        if (list == null) {
            list = new ArrayList<>();
            this.f7853a.put(b2, list);
        } else {
            list.remove(eVar);
        }
        list.add(eVar);
    }

    private URI b(URI uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URI("http", uri.getHost(), null, null);
        } catch (URISyntaxException unused) {
            return uri;
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<e> a(URI uri) {
        ArrayList arrayList;
        if (uri == null) {
            throw new NullPointerException("uri == null");
        }
        arrayList = new ArrayList();
        List<e> list = this.f7853a.get(uri);
        if (list != null) {
            Iterator<e> it = list.iterator();
            while (it.hasNext()) {
                e next = it.next();
                if (next.m()) {
                    it.remove();
                } else {
                    arrayList.add(next);
                }
            }
        }
        for (Map.Entry<URI, List<e>> entry : this.f7853a.entrySet()) {
            if (!uri.equals(entry.getKey())) {
                Iterator<e> it2 = entry.getValue().iterator();
                while (it2.hasNext()) {
                    e next2 = it2.next();
                    if (e.a(next2.d(), uri.getHost())) {
                        if (next2.m()) {
                            it2.remove();
                        } else if (!arrayList.contains(next2)) {
                            arrayList.add(next2);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<e> a() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (List<e> list : this.f7853a.values()) {
            Iterator<e> it = list.iterator();
            while (it.hasNext()) {
                e next = it.next();
                if (next.m()) {
                    it.remove();
                } else if (!arrayList.contains(next)) {
                    arrayList.add(next);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized List<URI> b() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.f7853a.keySet());
        arrayList.remove((Object) null);
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized boolean b(URI uri, e eVar) {
        if (eVar == null) {
            throw new NullPointerException("cookie == null");
        }
        List<e> list = this.f7853a.get(b(uri));
        if (list != null) {
            return list.remove(eVar);
        }
        return false;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.impl.c
    public synchronized boolean c() {
        boolean z;
        z = !this.f7853a.isEmpty();
        this.f7853a.clear();
        return z;
    }
}
