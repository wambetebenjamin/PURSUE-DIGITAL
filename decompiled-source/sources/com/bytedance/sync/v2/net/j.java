package com.bytedance.sync.v2.net;

import com.bytedance.sync.interfaze.q;
import com.bytedance.sync.v2.intf.k;
import java.util.ArrayList;
import java.util.List;
/* compiled from: WsStatusKeeper.java */
/* loaded from: classes3.dex */
public class j implements q, k {

    /* renamed from: a  reason: collision with root package name */
    private final List<a> f13845a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.sync.f f13846b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.common.wschannel.event.b f13847c;

    /* compiled from: WsStatusKeeper.java */
    /* loaded from: classes3.dex */
    public interface a {
        void onWsStatusChanged(boolean z);
    }

    public j(com.bytedance.sync.f fVar) {
        this.f13846b = fVar;
        fVar.wsService.registerOnWsStatusChangedListener(this);
    }

    @Override // com.bytedance.sync.interfaze.q
    public void onReceiveConnectEvent(com.bytedance.common.wschannel.event.b bVar) {
        if (bVar == null || bVar.f5624d != this.f13846b.channelId) {
            return;
        }
        com.bytedance.common.wschannel.event.b bVar2 = this.f13847c;
        boolean z = bVar2 != null && bVar2.f5622b == com.bytedance.common.wschannel.event.c.CONNECTED;
        boolean z2 = bVar.f5622b == com.bytedance.common.wschannel.event.c.CONNECTED;
        this.f13847c = bVar;
        if (z != z2) {
            synchronized (this) {
                int size = this.f13845a.size();
                a[] aVarArr = new a[size];
                this.f13845a.toArray(aVarArr);
                for (int i2 = 0; i2 < size; i2++) {
                    aVarArr[i2].onWsStatusChanged(z2);
                }
            }
        }
    }

    @Override // com.bytedance.sync.v2.intf.k
    public void addWsStatusChangedListener(a aVar) {
        synchronized (this) {
            this.f13845a.add(aVar);
        }
    }

    @Override // com.bytedance.sync.v2.intf.k
    public void removeWsStatusChangedListener(a aVar) {
        synchronized (this) {
            this.f13845a.remove(aVar);
        }
    }

    @Override // com.bytedance.sync.v2.intf.k
    public com.bytedance.common.wschannel.event.b getCurrentStatus() {
        return this.f13847c;
    }

    @Override // com.bytedance.sync.v2.intf.k
    public boolean isConnect() {
        com.bytedance.common.wschannel.event.b bVar = this.f13847c;
        if (bVar == null || bVar.f5622b != com.bytedance.common.wschannel.event.c.CONNECTED) {
            return this.f13846b.wsService.isConnect();
        }
        return true;
    }
}
