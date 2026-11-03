package com.bytedance.sync;

import android.os.Handler;
import android.os.Message;
import com.bytedance.sync.o;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: BusinessManager.java */
/* loaded from: classes3.dex */
public class c implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.sync.interfaze.s f13359a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<Long, o> f13360b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    private final o.a f13361c;

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    public c(o.a aVar, com.bytedance.sync.interfaze.s sVar) {
        this.f13361c = aVar;
        this.f13359a = sVar;
    }

    public com.bytedance.sync.interfaze.n registerBusiness(n nVar) {
        com.bytedance.sync.interfaze.n nVar2;
        synchronized (this) {
            o oVar = this.f13360b.get(Long.valueOf(nVar.bizId));
            if (oVar != null) {
                oVar.addOnDataUpdateListeners(nVar.f13481b);
                nVar2 = this.f13360b.get(Long.valueOf(nVar.bizId)).getSyncClient();
            } else {
                com.bytedance.sync.interfaze.n create = this.f13359a.create(Long.valueOf(nVar.bizId));
                o oVar2 = new o(nVar, create, this.f13361c);
                oVar2.addOnDataUpdateListeners(nVar.f13481b);
                this.f13360b.put(Long.valueOf(nVar.bizId), oVar2);
                nVar2 = create;
            }
        }
        return nVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.f13360b.remove(Long.valueOf(j2));
    }

    public o getBusinessById(long j2) {
        return this.f13360b.get(Long.valueOf(j2));
    }

    public Collection<o> getBusinesses() {
        return new ArrayList(this.f13360b.values());
    }
}
