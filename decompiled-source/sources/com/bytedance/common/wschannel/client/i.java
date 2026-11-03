package com.bytedance.common.wschannel.client;

import android.content.Intent;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.client.b;
import java.util.ArrayList;
/* compiled from: SyncConnectStateParse.java */
/* loaded from: classes2.dex */
public class i extends a {
    public i(b.a aVar) {
        super(aVar);
    }

    @Override // com.bytedance.common.wschannel.client.c
    public void a(Intent intent, com.bytedance.common.wschannel.model.a aVar) {
        Logger.d(AbsWsClientService.TAG, "sync socket state");
        intent.setExtrasClassLoader(com.bytedance.common.wschannel.model.f.class.getClassLoader());
        ArrayList<com.bytedance.common.wschannel.model.f> parcelableArrayListExtra = intent.getParcelableArrayListExtra(WsConstants.KEY_CONNECTION);
        if (parcelableArrayListExtra != null) {
            for (com.bytedance.common.wschannel.model.f fVar : parcelableArrayListExtra) {
                if (fVar != null) {
                    a(fVar.d(), fVar.b(), fVar.e());
                }
            }
        }
    }
}
