package com.bytedance.sync.v2.net;

import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.sync.t;
import org.json.JSONObject;
/* compiled from: CommonNetTrace.java */
/* loaded from: classes3.dex */
public class a extends g {
    @Override // com.bytedance.sync.v2.net.g
    public void send() {
        JSONObject jSONObject = new JSONObject();
        com.bytedance.sync.util.a.safePutParam(jSONObject, "send_method", this.f13838a);
        com.bytedance.sync.util.a.safePutParam(jSONObject, WsConstants.KEY_SEND_RESULT, this.f13839b);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "send_msg_type", this.mSendMsgType);
        com.bytedance.sync.util.a.safePutParam(jSONObject, "ws_connect", this.mIsConnect);
        t.monitor("sync_sdk_socket_percentage", jSONObject, null, null);
    }
}
