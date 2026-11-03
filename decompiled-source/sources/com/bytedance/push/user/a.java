package com.bytedance.push.user;

import com.bytedance.push.interfaze.e;
import com.ss.android.ug.bus.account.a;
import com.ss.android.ug.bus.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
/* compiled from: AccountSDKImpl.java */
/* loaded from: classes2.dex */
public class a implements e {
    @Override // com.bytedance.push.interfaze.e
    public void registerOnLoginListener(c.a<com.ss.android.ug.bus.account.event.a> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.push.interfaze.e
    public void registerOnLogoutListener(c.a<com.ss.android.ug.bus.account.event.b> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.push.interfaze.e
    public void registerOnAccountSwitchListener(c.a<com.ss.android.ug.bus.account.event.c> aVar) {
        c.register(aVar);
    }

    @Override // com.bytedance.push.interfaze.e
    public String getSecUid() {
        com.ss.android.ug.bus.account.a aVar = (com.ss.android.ug.bus.account.a) com.ss.android.ug.bus.b.getService(com.ss.android.ug.bus.account.a.class);
        if (aVar == null) {
            return null;
        }
        return aVar.getSecUid();
    }

    @Override // com.bytedance.push.interfaze.e
    public List<String> getLoginHistoryUids() {
        com.ss.android.ug.bus.account.a aVar = (com.ss.android.ug.bus.account.a) com.ss.android.ug.bus.b.getService(com.ss.android.ug.bus.account.a.class);
        if (aVar == null) {
            return null;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        aVar.getHistoryLoginInfo(new a.InterfaceC0340a() { // from class: com.bytedance.push.user.a.1
            @Override // com.ss.android.ug.bus.account.a.InterfaceC0340a
            public void onSucc(List<com.ss.android.ug.bus.account.model.a> list) {
                if (list != null) {
                    for (com.ss.android.ug.bus.account.model.a aVar2 : list) {
                        arrayList.add(aVar2.getSecUid());
                    }
                }
                countDownLatch.countDown();
            }

            @Override // com.ss.android.ug.bus.account.a.InterfaceC0340a
            public void onError() {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }
}
