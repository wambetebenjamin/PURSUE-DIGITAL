package com.bytedance.sync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: SyncBusiness.java */
/* loaded from: classes3.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private final n f13507a;

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.sync.interfaze.n f13508b;

    /* renamed from: c  reason: collision with root package name */
    private final List<com.bytedance.sync.interfaze.r> f13509c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    private final a f13510d;

    /* compiled from: SyncBusiness.java */
    /* loaded from: classes3.dex */
    public interface a {
        void onAdd(long j2);
    }

    public o(n nVar, com.bytedance.sync.interfaze.n nVar2, a aVar) {
        this.f13507a = nVar;
        this.f13508b = nVar2;
        this.f13510d = aVar;
    }

    public long getBusiness() {
        return this.f13507a.bizId;
    }

    public com.bytedance.sync.interfaze.n getSyncClient() {
        return this.f13508b;
    }

    public Object[] connectOnDataUpdateListener() {
        Object[] array;
        synchronized (this.f13509c) {
            array = this.f13509c.size() > 0 ? this.f13509c.toArray() : null;
        }
        return array;
    }

    public void addOnDataUpdateListener(com.bytedance.sync.interfaze.r rVar) {
        if (rVar == null) {
            return;
        }
        addOnDataUpdateListeners(Collections.singletonList(rVar));
    }

    public void removeOnDataUpdateListener(com.bytedance.sync.interfaze.r rVar) {
        synchronized (this.f13509c) {
            this.f13509c.remove(rVar);
        }
    }

    public void addOnDataUpdateListeners(List<com.bytedance.sync.interfaze.r> list) {
        boolean z;
        a aVar;
        synchronized (this.f13509c) {
            ArrayList<com.bytedance.sync.interfaze.r> arrayList = new ArrayList(list);
            synchronized (this.f13509c) {
                z = false;
                for (com.bytedance.sync.interfaze.r rVar : arrayList) {
                    if (rVar != null && !this.f13509c.contains(rVar)) {
                        this.f13509c.add(rVar);
                        z = true;
                    }
                }
            }
        }
        if (!z || (aVar = this.f13510d) == null) {
            return;
        }
        aVar.onAdd(this.f13507a.bizId);
    }

    public i getCommonParamProvider() {
        return this.f13507a.f13480a;
    }
}
