package com.bytedance.bdinstall.util;

import com.bytedance.bdinstall.ac;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: DataObserverInstance.java */
/* loaded from: classes.dex */
public class c implements ac {

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArraySet<ac> f4025a = new CopyOnWriteArraySet<>();

    @Override // com.bytedance.bdinstall.ac
    public void a(String str, String str2, String str3) {
        Iterator<ac> it = this.f4025a.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2, str3);
        }
    }

    @Override // com.bytedance.bdinstall.ac
    public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<ac> it = this.f4025a.iterator();
        while (it.hasNext()) {
            it.next().a(z, str, str2, str3, str4, str5, str6);
        }
    }

    public void a(ac acVar) {
        if (acVar != null) {
            this.f4025a.add(acVar);
        }
    }

    public void b(ac acVar) {
        if (acVar != null) {
            this.f4025a.remove(acVar);
        }
    }

    public void a() {
        this.f4025a.clear();
    }
}
