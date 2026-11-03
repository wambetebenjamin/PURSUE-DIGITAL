package com.bytedance.ttgame.channel.pay.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.channel.pay.entity.RestoreSubscriptionResponse;
/* loaded from: classes3.dex */
public interface IRestoreSubscriptionApi {
    @FormUrlEncoded
    @POST("v3/m_pay/restore_subscription")
    Call<RestoreSubscriptionResponse> restoreSubscription(@Field("encrypt_msg") String str);
}
