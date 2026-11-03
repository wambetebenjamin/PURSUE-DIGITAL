package com.bytedance.ttgame.channel.pay;

import android.content.Context;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.rocketapi.pay.QueryGoodsParams;
import com.bytedance.ttgame.rocketapi.pay.RocketGoods;
import java.util.HashMap;
/* loaded from: classes3.dex */
public interface IActivityGoodsDataSource {
    void queryActivityGoods(Context context, QueryGoodsParams queryGoodsParams, HashMap<String, String> hashMap, ICallback<RocketGoods> iCallback);
}
