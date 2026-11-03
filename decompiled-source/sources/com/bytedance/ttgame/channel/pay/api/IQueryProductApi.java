package com.bytedance.ttgame.channel.pay.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.ttgame.channel.pay.entity.ProductResponse;
import com.bytedance.ttgame.channel.pay.entity.RocketGoodsResponse;
import com.bytedance.ttgame.channel.pay.entity.RocketGoodsResponseV3;
import com.bytedance.ttgame.channel.pay.entity.RocketGoodsWebResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface IQueryProductApi {
    @GET("gsdk/v3/payment/query_goods_with_activity")
    Call<RocketGoodsResponseV3> queryActivityGoods(@Query("encrypt_msg") String str);

    @Headers({"x-scene: 2"})
    @POST
    Call<RocketGoodsWebResponse> queryActivityGoods(@Url String str, @QueryMap Map<String, String> map);

    @GET("gsdk/payment/query_goods_with_activity")
    Call<RocketGoodsResponse> queryActivityGoods(@QueryMap Map<String, String> map);

    @GET("v3/m_pay/products")
    Call<ProductResponse> queryProducts(@Query("encrypt_msg") String str);
}
