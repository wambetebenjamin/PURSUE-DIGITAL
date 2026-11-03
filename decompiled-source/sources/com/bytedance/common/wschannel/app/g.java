package com.bytedance.common.wschannel.app;

import com.bytedance.common.wschannel.model.i;
import org.json.JSONObject;
/* compiled from: OnMessageReceiveListener.java */
/* loaded from: classes2.dex */
public interface g {
    void onReceiveConnectEvent(com.bytedance.common.wschannel.event.b bVar, JSONObject jSONObject);

    void onReceiveMsg(i iVar);
}
