package com.bytedance.common.wschannel.client;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.client.b;
/* compiled from: BaseIntentParse.java */
/* loaded from: classes2.dex */
public abstract class a implements c {

    /* renamed from: a  reason: collision with root package name */
    protected final b.a f5580a;

    public a(b.a aVar) {
        this.f5580a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.bytedance.common.wschannel.event.c a(int i2, int i3, boolean z) {
        com.bytedance.common.wschannel.event.c cVar = com.bytedance.common.wschannel.event.c.CONNECTION_UNKNOWN;
        if (i3 == 0) {
            cVar = com.bytedance.common.wschannel.event.c.CONNECTION_UNKNOWN;
        } else if (i3 == 1) {
            cVar = com.bytedance.common.wschannel.event.c.CONNECTING;
        } else if (i3 == 2) {
            cVar = com.bytedance.common.wschannel.event.c.CONNECT_FAILED;
        } else if (i3 == 3) {
            cVar = com.bytedance.common.wschannel.event.c.CONNECT_CLOSED;
        } else if (i3 == 4) {
            cVar = com.bytedance.common.wschannel.event.c.CONNECTED;
        }
        if (Logger.debug()) {
            Logger.d(AbsWsClientService.TAG, "state = " + i3 + " connectionState = " + cVar);
        }
        this.f5580a.syncState(i2, cVar, z);
        return cVar;
    }
}
