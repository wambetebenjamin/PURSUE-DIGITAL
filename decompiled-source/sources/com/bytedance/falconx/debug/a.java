package com.bytedance.falconx.debug;

import com.bytedance.falconx.g;
import java.util.ArrayList;
import java.util.List;
/* compiled from: WebOfflineAnalyze.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static List<com.bytedance.falconx.debug.model.a> f7349a;

    /* renamed from: b  reason: collision with root package name */
    private static final List<C0105a> f7350b = new ArrayList();

    public static void a(g gVar) {
        if (b.a()) {
            if (f7349a == null) {
                f7349a = new ArrayList();
            }
            com.bytedance.falconx.debug.model.a aVar = new com.bytedance.falconx.debug.model.a(gVar.d(), gVar.b(), gVar.c(), gVar.i(), gVar.e(), gVar.j(), gVar.f(), gVar.k());
            if (f7349a.contains(aVar)) {
                return;
            }
            f7349a.add(aVar);
        }
    }

    public static void a(String str, String str2, long j2) {
        if (b.a()) {
            List<C0105a> list = f7350b;
            synchronized (list) {
                list.add(new C0105a(false, str, str2, j2));
            }
        }
    }

    public static void b(String str, String str2, long j2) {
        if (b.a()) {
            List<C0105a> list = f7350b;
            synchronized (list) {
                list.add(new C0105a(true, str, str2, j2));
            }
        }
    }

    public static List<C0105a> a() {
        return f7350b;
    }

    public static List<com.bytedance.falconx.debug.model.a> b() {
        return f7349a;
    }

    /* compiled from: WebOfflineAnalyze.java */
    /* renamed from: com.bytedance.falconx.debug.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0105a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7351a;

        /* renamed from: b  reason: collision with root package name */
        public String f7352b;

        /* renamed from: c  reason: collision with root package name */
        public String f7353c;

        /* renamed from: d  reason: collision with root package name */
        public long f7354d;

        public C0105a(boolean z, String str, String str2, long j2) {
            this.f7351a = z;
            this.f7352b = str;
            this.f7353c = str2;
            this.f7354d = j2;
        }
    }

    public static void c() {
        List<com.bytedance.falconx.debug.model.a> list = f7349a;
        if (list == null) {
            return;
        }
        list.clear();
        f7349a = null;
        f7350b.clear();
    }
}
