package com.bytedance.common.jato.memory.leakwatcher;

import android.app.Application;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* compiled from: AndroidLeakMonitor.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4989a = "AndroidLeakMonitor";

    /* renamed from: b  reason: collision with root package name */
    private static b f4990b;

    /* renamed from: c  reason: collision with root package name */
    private h f4991c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4992d = false;

    /* renamed from: e  reason: collision with root package name */
    private final List<f> f4993e = new ArrayList();

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (f4990b == null) {
                f4990b = new b();
            }
            bVar = f4990b;
        }
        return bVar;
    }

    public void a(Application application, boolean z, boolean z2, String[] strArr) {
        a(b(application, z, z2, strArr));
    }

    public void a(List<f> list) {
        if (this.f4992d) {
            Log.w(f4989a, "AndroidLeakMonitor already installed");
            return;
        }
        this.f4991c = new h();
        for (f fVar : list) {
            fVar.a();
            this.f4993e.add(fVar);
        }
        this.f4992d = true;
    }

    public h b() {
        if (!this.f4992d) {
            throw new IllegalStateException("AndroidLeakMonitor not installed, cannot get LeakTracker");
        }
        return this.f4991c;
    }

    public void a(Object obj, String str) {
        if (!this.f4992d) {
            Log.w(f4989a, "AndroidLeakMonitor not installed, ignoring watch request");
        } else if (obj == null) {
        } else {
            this.f4991c.a(obj, str);
        }
    }

    public List<f> b(Application application, boolean z, boolean z2, String[] strArr) {
        c cVar = new c() { // from class: com.bytedance.common.jato.memory.leakwatcher.b$$ExternalSyntheticLambda0
            @Override // com.bytedance.common.jato.memory.leakwatcher.c
            public final void addForLeakDetection(Object obj, String str) {
                b.this.a(obj, str);
            }
        };
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(new a(application, cVar));
        }
        if (z2) {
            arrayList.add(new e(application, cVar, strArr));
        }
        if (arrayList.isEmpty()) {
            Log.e(f4989a, "Leak watcher is empty, AndroidLeakMonitor will not watch any leaks !!!");
        }
        return arrayList;
    }

    public int c() {
        h hVar;
        if (!this.f4992d || (hVar = this.f4991c) == null) {
            return 0;
        }
        return hVar.c();
    }

    public List<g> d() {
        h hVar;
        if (!this.f4992d || (hVar = this.f4991c) == null) {
            return null;
        }
        return hVar.a();
    }

    public void e() {
        if (this.f4992d) {
            for (f fVar : this.f4993e) {
                fVar.b();
            }
            this.f4993e.clear();
            h hVar = this.f4991c;
            if (hVar != null) {
                hVar.d();
                this.f4991c = null;
            }
            this.f4992d = false;
        }
    }
}
