package com.bytedance.sdk.account.api;

import com.bytedance.sdk.account.api.callback.ad;
import com.bytedance.sdk.account.api.callback.w;
import java.util.List;
import java.util.Map;
/* compiled from: IBDAccountCoreApi.java */
/* loaded from: classes2.dex */
public interface l {
    void getDeviceLoginInfo(boolean z, List<com.bytedance.sdk.account.save.entity.c> list, int i2, ad adVar);

    void getNewAccountInfo(String str, w wVar);

    void getRecentLoginInfo(ad adVar);

    void logout(String str, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.d> aVar);
}
