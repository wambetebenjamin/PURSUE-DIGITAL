package com.bytedance.pipo.game.impl.net.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
/* loaded from: classes2.dex */
public interface IQueryProductApi {
    @GET("v3/m_pay/products")
    Call<d> queryProducts(@Query("encrypt_msg") String str);
}
