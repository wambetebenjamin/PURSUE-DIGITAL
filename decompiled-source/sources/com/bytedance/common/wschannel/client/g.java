package com.bytedance.common.wschannel.client;

import android.content.Intent;
import com.bytedance.common.wschannel.client.b;
/* compiled from: ReceiveServiceActionParse.java */
/* loaded from: classes2.dex */
public class g extends a {
    public g(b.a aVar) {
        super(aVar);
    }

    @Override // com.bytedance.common.wschannel.client.c
    public void a(Intent intent, com.bytedance.common.wschannel.model.a aVar) {
        intent.setExtrasClassLoader(com.bytedance.common.wschannel.model.d.class.getClassLoader());
        try {
            this.f5580a.onReceiveServiceEvent((com.bytedance.common.wschannel.model.d) intent.getParcelableExtra("service"));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
