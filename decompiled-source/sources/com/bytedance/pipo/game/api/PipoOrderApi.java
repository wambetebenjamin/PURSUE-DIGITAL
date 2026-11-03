package com.bytedance.pipo.game.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
/* loaded from: classes2.dex */
public interface PipoOrderApi {
    @FormUrlEncoded
    @POST("v3/m_pay/order")
    Call<m> createOrder(@Field("encrypt_msg") String str);
}
