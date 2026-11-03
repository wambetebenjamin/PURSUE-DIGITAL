package com.bytedance.applog.holder;

import com.bytedance.applog.q;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;
/* compiled from: SessionObserverHolder.java */
/* loaded from: classes.dex */
public class c implements q {

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArraySet<q> f2716a = new CopyOnWriteArraySet<>();

    @Override // com.bytedance.applog.q
    public void onSessionStart(long j2, String str) {
        Iterator<q> it = this.f2716a.iterator();
        while (it.hasNext()) {
            it.next().onSessionStart(j2, str);
        }
    }

    @Override // com.bytedance.applog.q
    public void onSessionTerminate(long j2, String str, JSONObject jSONObject) {
        Iterator<q> it = this.f2716a.iterator();
        while (it.hasNext()) {
            it.next().onSessionTerminate(j2, str, jSONObject);
        }
    }

    @Override // com.bytedance.applog.q
    public void onSessionBatchEvent(long j2, String str, JSONObject jSONObject) {
        Iterator<q> it = this.f2716a.iterator();
        while (it.hasNext()) {
            it.next().onSessionBatchEvent(j2, str, jSONObject);
        }
    }

    public void a(q qVar) {
        if (qVar != null) {
            this.f2716a.add(qVar);
        }
    }

    public void b(q qVar) {
        if (qVar != null) {
            this.f2716a.remove(qVar);
        }
    }

    public int a() {
        return this.f2716a.size();
    }

    public void b() {
        this.f2716a.clear();
    }
}
