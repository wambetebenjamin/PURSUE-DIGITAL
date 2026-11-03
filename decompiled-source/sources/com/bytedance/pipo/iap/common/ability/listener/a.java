package com.bytedance.pipo.iap.common.ability.listener;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.model.AbsIapChannelOrderData;
import java.util.List;
/* compiled from: GpListener.java */
/* loaded from: classes2.dex */
public interface a extends com.bytedance.pipo.iap.listenter.b {
    void onPurchasesUpdated(e eVar, List<AbsIapChannelOrderData> list);
}
