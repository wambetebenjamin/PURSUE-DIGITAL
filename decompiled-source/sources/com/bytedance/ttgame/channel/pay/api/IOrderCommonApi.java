package com.bytedance.ttgame.channel.pay.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.module.pay.api.OrderResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface IOrderCommonApi {
    @FormUrlEncoded
    @POST("/gsdk/v3/payment/create_order")
    Call<OrderResponse> createOrderV3(@AddCommonParam boolean z, @FieldMap Map<String, String> map);
}
