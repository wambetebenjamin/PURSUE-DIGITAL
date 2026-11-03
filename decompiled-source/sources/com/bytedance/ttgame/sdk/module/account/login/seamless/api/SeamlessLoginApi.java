package com.bytedance.ttgame.sdk.module.account.login.seamless.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.base.BaseResponse;
import gsdk.impl.account.toutiao.bc;
import java.util.Map;
/* loaded from: classes3.dex */
public interface SeamlessLoginApi {
    @GET("sdk/account/gps/link_data")
    Call<bc> getSeamlessLoginAccounts(@AddCommonParam boolean z, @Query("session_id") String str, @Query("login_id") String str2);

    @FormUrlEncoded
    @POST("sdk/account/gps/link")
    Call<BaseResponse> linkSeamlessAccount(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sdk/account/gps/unlink")
    Call<BaseResponse> unlinkSeamlessAccount(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);
}
