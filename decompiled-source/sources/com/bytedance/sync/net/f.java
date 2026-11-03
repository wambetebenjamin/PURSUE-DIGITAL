package com.bytedance.sync.net;

import com.bytedance.sync.interfaze.q;
import java.util.ArrayList;
import java.util.List;
/* compiled from: WsStatusKeeper.java */
/* loaded from: classes3.dex */
public class f implements q {

    /* renamed from: a  reason: collision with root package name */
    private final List<a> f13504a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.sync.f f13505b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.common.wschannel.event.b f13506c;

    /* compiled from: WsStatusKeeper.java */
    /* loaded from: classes3.dex */
    public interface a {
        void onWsStatusChanged(boolean z);
    }

    public f(com.bytedance.sync.f fVar) {
        this.f13505b = fVar;
        fVar.wsService.registerOnWsStatusChangedListener(this);
    }

    @Override // com.bytedance.sync.interfaze.q
    public void onReceiveConnectEvent(com.bytedance.common.wschannel.event.b bVar) {
        if (bVar == null || bVar.f5624d != this.f13505b.channelId) {
            return;
        }
        com.bytedance.common.wschannel.event.b bVar2 = this.f13506c;
        boolean z = true;
        boolean z2 = bVar2 != null && bVar2.f5622b == com.bytedance.common.wschannel.event.c.CONNECTED;
        z = (bVar == null || bVar.f5622b != com.bytedance.common.wschannel.event.c.CONNECTED) ? false : false;
        this.f13506c = bVar;
        if (z2 != z) {
            synchronized (this) {
                for (a aVar : this.f13504a) {
                    aVar.onWsStatusChanged(z);
                }
            }
        }
    }

    public void addWsStatusChangedListener(a aVar) {
        synchronized (this) {
            this.f13504a.add(aVar);
        }
    }

    public void removeWsStatusChangedListener(a aVar) {
        synchronized (this) {
            this.f13504a.remove(aVar);
        }
    }

    public com.bytedance.common.wschannel.event.b getCurrentStatus() {
        return this.f13506c;
    }

    public boolean isConnect() {
        com.bytedance.common.wschannel.event.b bVar = this.f13506c;
        if (bVar == null || bVar.f5622b != com.bytedance.common.wschannel.event.c.CONNECTED) {
            return this.f13505b.wsService.isConnect();
        }
        return true;
    }
}
