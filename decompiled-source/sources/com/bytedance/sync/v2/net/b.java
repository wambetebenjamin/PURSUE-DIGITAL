package com.bytedance.sync.v2.net;

import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.sync.t;
import com.bytedance.sync.v2.net.g;
import org.json.JSONObject;
/* compiled from: CompensatorTrace.java */
/* loaded from: classes3.dex */
public final class b extends g {

    /* renamed from: c  reason: collision with root package name */
    private final boolean f13823c;

    /* renamed from: d  reason: collision with root package name */
    private final int f13824d;

    public b(boolean z, g.d dVar) {
        this.f13823c = z;
        this.f13824d = dVar != null ? dVar.getCurrentStrategy() : 0;
    }

    @Override // com.bytedance.sync.v2.net.g
    public void send() {
        JSONObject jSONObject = new JSONObject();
        com.bytedance.sync.util.a.safePutParam(jSONObject, "send_method", this.f13838a);
        com.bytedance.sync.util.a.safePutParam(jSONObject, WsConstants.KEY_SEND_RESULT, this.f13839b);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "is_foreground", this.f13823c);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "strategy", this.f13824d);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "send_msg_type", this.mSendMsgType);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "ws_connect", this.mIsConnect);
        t.monitor("sync_sdk_compensator_net_monitor", jSONObject, null, null);
    }
}
