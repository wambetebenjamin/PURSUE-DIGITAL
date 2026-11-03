package com.bytedance.applog.util;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;
/* compiled from: DataObserverHolder.java */
/* loaded from: classes.dex */
public class b implements com.bytedance.applog.k {

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArraySet<com.bytedance.applog.k> f3548a = new CopyOnWriteArraySet<>();

    @Override // com.bytedance.applog.k
    public void a(String str, String str2, String str3) {
        Iterator<com.bytedance.applog.k> it = this.f3548a.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2, str3);
        }
    }

    @Override // com.bytedance.applog.k
    public void a(boolean z, String str, String str2, String str3, String str4, String str5, String str6) {
        Iterator<com.bytedance.applog.k> it = this.f3548a.iterator();
        while (it.hasNext()) {
            it.next().a(z, str, str2, str3, str4, str5, str6);
        }
    }

    @Override // com.bytedance.applog.k
    public void a(boolean z, JSONObject jSONObject) {
        Iterator<com.bytedance.applog.k> it = this.f3548a.iterator();
        while (it.hasNext()) {
            it.next().a(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.k
    public void b(boolean z, JSONObject jSONObject) {
        Iterator<com.bytedance.applog.k> it = this.f3548a.iterator();
        while (it.hasNext()) {
            it.next().b(z, jSONObject);
        }
    }

    @Override // com.bytedance.applog.k
    public void a(String str, String str2) {
        Iterator<com.bytedance.applog.k> it = this.f3548a.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2);
        }
    }

    public void a(com.bytedance.applog.k kVar) {
        if (kVar != null) {
            this.f3548a.add(kVar);
        }
    }

    public void b(com.bytedance.applog.k kVar) {
        if (kVar != null) {
            this.f3548a.remove(kVar);
        }
    }

    public void a() {
        this.f3548a.clear();
    }
}
