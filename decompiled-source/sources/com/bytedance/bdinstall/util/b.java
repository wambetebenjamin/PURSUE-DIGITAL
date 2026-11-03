package com.bytedance.bdinstall.util;

import com.bytedance.bdinstall.ac;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: DataObserverHolder.java */
/* loaded from: classes.dex */
public class b implements ac {

    /* renamed from: a  reason: collision with root package name */
    private static volatile b f4023a;

    /* renamed from: b  reason: collision with root package name */
    private final CopyOnWriteArraySet<ac> f4024b = new CopyOnWriteArraySet<>();

    public static b a() {
        if (f4023a == null) {
            synchronized (b.class) {
                if (f4023a == null) {
                    f4023a = new b();
                }
            }
        }
        return f4023a;
    }

    private b() {
    }

    @Override // com.bytedance.bdinstall.ac
    public void a(String str, String str2, String str3) {
        Iterator<ac> it = this.f4024b.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2, str3);
        }
    }

    @Override // com.bytedance.bdinstall.ac
    public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<ac> it = this.f4024b.iterator();
        while (it.hasNext()) {
            it.next().a(z, str, str2, str3, str4, str5, str6);
        }
    }

    public void a(ac acVar) {
        if (acVar != null) {
            this.f4024b.add(acVar);
        }
    }

    public void b(ac acVar) {
        if (acVar != null) {
            this.f4024b.remove(acVar);
        }
    }

    public void b() {
        this.f4024b.clear();
    }
}
