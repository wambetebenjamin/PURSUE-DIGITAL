package com.bytedance.common.jato.memory.leakwatcher.autofix;

import android.os.SystemClock;
import com.bytedance.common.jato.memory.HeapReferenceCutter;
import com.bytedance.common.jato.memory.leakwatcher.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* compiled from: ARTLeakFixStrategy.java */
/* loaded from: classes2.dex */
public class a implements d {

    /* renamed from: b  reason: collision with root package name */
    private static final int f4959b = 5;

    /* renamed from: c  reason: collision with root package name */
    private static final int f4960c = 180000;

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, g> f4961a;

    /* renamed from: d  reason: collision with root package name */
    private final int f4962d;

    /* renamed from: e  reason: collision with root package name */
    private final int f4963e;

    /* renamed from: f  reason: collision with root package name */
    private final boolean f4964f;

    public a() {
        this(5, f4960c, false);
    }

    public a(int i2, int i3, boolean z) {
        this.f4961a = new HashMap();
        this.f4962d = i2;
        this.f4963e = i3;
        this.f4964f = z;
    }

    @Override // com.bytedance.common.jato.memory.leakwatcher.autofix.d
    public boolean a(List<g> list) {
        Object e2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        synchronized (this) {
            a();
            for (g gVar : list) {
                if (SystemClock.elapsedRealtime() - gVar.c() >= this.f4963e && (e2 = gVar.e()) != null) {
                    if (this.f4964f) {
                        arrayList.add(e2);
                    } else {
                        String name = e2.getClass().getName();
                        g gVar2 = this.f4961a.get(name);
                        if (gVar2 == null) {
                            this.f4961a.put(name, gVar);
                        } else {
                            Object e3 = gVar2.e();
                            if (e3 == null) {
                                this.f4961a.put(name, gVar);
                            } else if (!gVar.a().equals(gVar2.a()) && e3 != e2) {
                                arrayList.add(e2);
                                arrayList2.add(e3);
                            }
                        }
                    }
                }
            }
            if (arrayList.size() >= this.f4962d) {
                try {
                    if (arrayList2.isEmpty()) {
                        HeapReferenceCutter.a(arrayList.toArray());
                    } else {
                        HeapReferenceCutter.a(arrayList.toArray(), arrayList2.toArray());
                    }
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }
            return false;
        }
    }

    private void a() {
        Iterator<Map.Entry<String, g>> it = this.f4961a.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().e() == null) {
                it.remove();
            }
        }
    }
}
