package com.bytedance.common.wschannel.client;

import android.content.Intent;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ClientMsgParser.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.common.wschannel.client.a {

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, c> f5581b;

    /* compiled from: ClientMsgParser.java */
    /* loaded from: classes2.dex */
    public interface a {
        void onReceive(com.bytedance.common.wschannel.model.i iVar);

        void onReceiveConnectEvent(com.bytedance.common.wschannel.event.b bVar, JSONObject jSONObject);

        void onReceiveServiceEvent(com.bytedance.common.wschannel.model.d dVar);

        void onSendResult(String str, boolean z);

        void replySendMsgResult(com.bytedance.common.wschannel.model.i iVar, boolean z);

        void syncState(int i2, com.bytedance.common.wschannel.event.c cVar, boolean z);
    }

    public b(a aVar) {
        super(aVar);
        HashMap hashMap = new HashMap();
        hashMap.put(WsConstants.RECEIVE_CONNECTION_ACTION, new d(aVar));
        hashMap.put(WsConstants.SYNC_CONNECT_STATE, new i(aVar));
        hashMap.put(WsConstants.RECEIVE_PROGRESS_ACTION, new f(aVar));
        hashMap.put(WsConstants.RECEIVE_PAYLOAD_ACTION, new e(aVar));
        hashMap.put(WsConstants.SEND_PAYLOAD_ACTION, new h(aVar));
        hashMap.put(WsConstants.RECEIVE_SERVICE_ACTION, new g(aVar));
        this.f5581b = Collections.unmodifiableMap(hashMap);
    }

    @Override // com.bytedance.common.wschannel.client.c
    public void a(Intent intent, com.bytedance.common.wschannel.model.a aVar) {
        try {
            c cVar = this.f5581b.get(intent.getAction());
            if (cVar != null) {
                cVar.a(intent, aVar);
            } else {
                Logger.e("error action!");
            }
        } catch (Throwable th) {
            Logger.e(AbsWsClientService.TAG, th.toString());
        }
        if (Logger.debug()) {
            Logger.d(AbsWsClientService.TAG, "count = " + intent.getIntExtra(WsConstants.MSG_COUNT, -1));
        }
    }
}
