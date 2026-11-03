package com.bytedance.bdinstall.callback;

import android.os.Looper;
import com.bytedance.bdinstall.x;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: CallbackCenter.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: b  reason: collision with root package name */
    private final Object f3774b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private final Object f3775c = new Object();

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentHashMap<Type, a> f3776d = new ConcurrentHashMap<>();

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap<c, Object> f3773a = new ConcurrentHashMap<>();

    /* renamed from: e  reason: collision with root package name */
    private String f3777e = null;

    public void a(String str) {
        this.f3777e = str;
    }

    public <EVENT> void a(com.bytedance.bdinstall.callback.a<EVENT> aVar) {
        a(false, aVar);
    }

    public <EVENT> void a(boolean z, com.bytedance.bdinstall.callback.a<EVENT> aVar) {
        if (aVar == null) {
            return;
        }
        Type type = ((ParameterizedType) aVar.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        synchronized (this.f3774b) {
            a aVar2 = this.f3776d.get(type);
            if (aVar2 == null) {
                aVar2 = new a();
                this.f3776d.put(type, aVar2);
            }
            aVar2.a(aVar, this.f3775c);
            if (aVar instanceof c) {
                this.f3773a.put((c) aVar, this.f3775c);
            }
            if (z && aVar2.f3784a != null) {
                a(aVar, aVar2, aVar2.f3784a);
            }
        }
    }

    private <EVENT> void a(final com.bytedance.bdinstall.callback.a<EVENT> aVar, final a aVar2, final Object obj) {
        x.c(this.f3777e, new Runnable() { // from class: com.bytedance.bdinstall.callback.b.1
            @Override // java.lang.Runnable
            public void run() {
                for (com.bytedance.bdinstall.callback.a aVar3 : aVar2.b()) {
                    if (aVar == aVar3) {
                        aVar.a(obj);
                        return;
                    }
                }
            }
        });
    }

    public <EVENT> void b(com.bytedance.bdinstall.callback.a<EVENT> aVar) {
        if (aVar == null) {
            return;
        }
        Type type = ((ParameterizedType) aVar.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        synchronized (this.f3774b) {
            a aVar2 = this.f3776d.get(type);
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        }
    }

    public void a(Object obj) {
        if (obj == null) {
            return;
        }
        synchronized (this.f3774b) {
            c cVar = null;
            Iterator<c> it = this.f3773a.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                c next = it.next();
                if (next.a() == obj) {
                    cVar = next;
                    break;
                }
            }
            if (cVar != null) {
                a(cVar);
                this.f3773a.remove(cVar);
            }
        }
    }

    public <Event> void b(final Event event) {
        a aVar;
        if (Looper.myLooper() != x.a(this.f3777e)) {
            x.c(this.f3777e, new Runnable() { // from class: com.bytedance.bdinstall.callback.b.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.b((b) event);
                }
            });
            return;
        }
        synchronized (this.f3774b) {
            aVar = this.f3776d.get(event.getClass());
            if (aVar == null) {
                aVar = new a();
                this.f3776d.put(event.getClass(), aVar);
            }
        }
        aVar.f3784a = event;
        for (com.bytedance.bdinstall.callback.a aVar2 : aVar.b()) {
            if (aVar2 != null) {
                aVar2.a(event);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CallbackCenter.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Object f3784a;

        /* renamed from: b  reason: collision with root package name */
        ConcurrentHashMap<com.bytedance.bdinstall.callback.a, Object> f3785b;

        private a() {
            this.f3785b = new ConcurrentHashMap<>();
        }

        void a(com.bytedance.bdinstall.callback.a aVar) {
            this.f3785b.remove(aVar);
        }

        public boolean a() {
            return this.f3785b.isEmpty();
        }

        Set<com.bytedance.bdinstall.callback.a> b() {
            return this.f3785b.keySet();
        }

        public void a(com.bytedance.bdinstall.callback.a aVar, Object obj) {
            this.f3785b.put(aVar, obj);
        }
    }
}
