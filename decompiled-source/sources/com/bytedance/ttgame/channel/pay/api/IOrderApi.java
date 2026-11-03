package com.bytedance.ttgame.channel.pay.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import gsdk.impl.pay.DEFAULT.f;
/* loaded from: classes3.dex */
public interface IOrderApi {
    @FormUrlEncoded
    @POST("v3/m_pay/order")
    Call<f> createOrder(@AddCommonParam boolean z, @Field("encrypt_msg") String str);
}
