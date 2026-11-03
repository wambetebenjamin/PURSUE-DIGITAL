package com.bytedance.common.wschannel.channel;

import com.bytedance.common.wschannel.model.d;
import com.bytedance.common.wschannel.model.f;
import com.bytedance.common.wschannel.model.i;
import org.json.JSONObject;
/* compiled from: IMessageHandler.java */
/* loaded from: classes2.dex */
public interface a {
    void a();

    void a(int i2, i iVar);

    void a(int i2, byte[] bArr);

    void a(IWsChannelClient iWsChannelClient, int i2, JSONObject jSONObject);

    void a(IWsChannelClient iWsChannelClient, f fVar);

    void a(d dVar);

    void a(i iVar, boolean z);

    void a(String str, JSONObject jSONObject);

    void b();
}
