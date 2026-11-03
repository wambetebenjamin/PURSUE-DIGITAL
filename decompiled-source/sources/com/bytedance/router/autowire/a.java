package com.bytedance.router.autowire;

import android.util.LruCache;
import java.util.ArrayList;
import java.util.List;
/* compiled from: AutowiredService.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private LruCache<String, c> f12153a;

    /* renamed from: b  reason: collision with root package name */
    private List<String> f12154b;

    public static a inst() {
        return C0182a.f12155a;
    }

    /* compiled from: AutowiredService.java */
    /* renamed from: com.bytedance.router.autowire.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static final class C0182a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f12155a = new a();

        private C0182a() {
        }
    }

    private a() {
        this.f12153a = new LruCache<>(66);
        this.f12154b = new ArrayList();
    }

    public void autowire(Object obj) {
        String name = obj.getClass().getName();
        try {
            if (this.f12154b.contains(name)) {
                return;
            }
            c cVar = this.f12153a.get(name);
            if (cVar == null) {
                cVar = (c) Class.forName(obj.getClass().getName() + "$$SmartRouter$$Autowired").getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            cVar.inject(obj);
            this.f12153a.put(name, cVar);
        } catch (Exception unused) {
            this.f12154b.add(name);
        }
    }
}
