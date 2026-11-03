package com.bytedance.bdinstall.storage;

import android.text.TextUtils;
import com.bytedance.bdinstall.ax;
import java.util.List;
/* compiled from: Cache.java */
/* loaded from: classes.dex */
public abstract class c implements g {

    /* renamed from: a  reason: collision with root package name */
    private c f3994a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Cache.java */
    /* loaded from: classes.dex */
    public interface a<L> {
        L a(L l, L l2, c cVar);

        void a(L l);

        boolean a(L l, L l2);

        L b();

        boolean b(L l);
    }

    @Override // com.bytedance.bdinstall.storage.g
    public abstract String a(String str);

    @Override // com.bytedance.bdinstall.storage.g
    public abstract void a(String str, String str2);

    private c a() {
        return this.f3994a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(c cVar) {
        this.f3994a = cVar;
    }

    @Override // com.bytedance.bdinstall.storage.g
    public String b(String str, String str2) {
        return (String) a(str, str2, new a<String>() { // from class: com.bytedance.bdinstall.storage.c.1
            @Override // com.bytedance.bdinstall.storage.c.a
            /* renamed from: a */
            public String b() {
                return c.this.a("device_id");
            }

            @Override // com.bytedance.bdinstall.storage.c.a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public boolean b(String str3) {
                return !TextUtils.isEmpty(str3);
            }

            @Override // com.bytedance.bdinstall.storage.c.a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public void a(String str3) {
                c.this.a("device_id", str3);
            }

            @Override // com.bytedance.bdinstall.storage.c.a
            public String a(String str3, String str4, c cVar) {
                return cVar == null ? str3 : cVar.b(str3, str4);
            }

            @Override // com.bytedance.bdinstall.storage.c.a
            public boolean a(String str3, String str4) {
                return ax.a(str3, str4);
            }
        });
    }

    private <T> T a(T t, T t2, a<T> aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("agent == null");
        }
        c a2 = a();
        T b2 = aVar.b();
        boolean b3 = aVar.b(t);
        boolean b4 = aVar.b(b2);
        if (!b3 && b4) {
            t = b2;
        }
        if (a2 != null) {
            T a3 = aVar.a(t, t2, a2);
            if (!aVar.a(a3, b2)) {
                aVar.a(a3);
            }
            return a3;
        }
        boolean z = false;
        if (b3 || b4) {
            t2 = t;
        } else {
            z = true;
        }
        if ((z && aVar.b(t2)) || (b3 && !aVar.a(t2, b2))) {
            aVar.a(t2);
        }
        return t2;
    }

    @Override // com.bytedance.bdinstall.storage.g
    public void a(List<String> list) {
        c a2 = a();
        if (a2 != null) {
            a2.a(list);
        }
    }
}
