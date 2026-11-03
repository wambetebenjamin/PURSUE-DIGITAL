package com.bytedance.pipo.iap.listenter;

import com.bytedance.pipo.iap.model.AbsIapChannelOrderData;
import com.bytedance.pipo.iap.model.AbsResult;
import com.bytedance.pipo.iap.model.IapPaymentMethod;
import java.util.List;
/* compiled from: QueryUnAckOrderListener.java */
/* loaded from: classes2.dex */
public interface b {
    void onQueryFinished(IapPaymentMethod iapPaymentMethod, AbsResult absResult, List<AbsIapChannelOrderData> list);
}
