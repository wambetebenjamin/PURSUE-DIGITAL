package com.bytedance.ttgame.module.account.bridge.model;

import com.bytedance.ttgame.rocketapi.pay.RocketProductInfo;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMProductInfo.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001¨\u0006\u0005"}, d2 = {ViewProps.TRANSFORM, "", "Lcom/bytedance/ttgame/module/account/bridge/model/GMProductInfo;", "rocketProductInfos", "Lcom/bytedance/ttgame/rocketapi/pay/RocketProductInfo;", "pay_bridge_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductInfoKt {
    public static final List<GMProductInfo> transform(List<? extends RocketProductInfo> rocketProductInfos) {
        Intrinsics.checkNotNullParameter(rocketProductInfos, "rocketProductInfos");
        List<? extends RocketProductInfo> list = rocketProductInfos;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (RocketProductInfo rocketProductInfo : list) {
            arrayList.add(new GMProductInfo(rocketProductInfo.getProductId(), rocketProductInfo.getPrice(), rocketProductInfo.getCurrency(), rocketProductInfo.getPriceAmountMicros(), rocketProductInfo.getPrice(), Long.valueOf(rocketProductInfo.getDiscountPrice())));
        }
        return arrayList;
    }
}
