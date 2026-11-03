package com.ss.android.ug.bus.account;

import java.util.List;
/* compiled from: IAccountService.java */
/* loaded from: classes9.dex */
public interface a extends com.ss.android.ug.bus.a {

    /* compiled from: IAccountService.java */
    /* renamed from: com.ss.android.ug.bus.account.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0340a {
        void onError();

        void onSucc(List<com.ss.android.ug.bus.account.model.a> list);
    }

    void getHistoryLoginInfo(InterfaceC0340a interfaceC0340a);

    String getSecUid();
}
