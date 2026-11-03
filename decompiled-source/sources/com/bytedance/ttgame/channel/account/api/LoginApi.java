package com.bytedance.ttgame.channel.account.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Path;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.channel.account.SdkUserInfo;
import com.bytedance.ttgame.channel.account.pojo.QuerySdkOpenIdResponse;
import com.bytedance.ttgame.rocketapi.account.ExchangeTokenResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface LoginApi {
    @FormUrlEncoded
    @POST("{subPath}/compliance/logoff/cancel_logoff")
    Call<BaseResponse> cancelLogoff(@Path("subPath") String str, @AddCommonParam boolean z, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("gsdk/account/exchange_token")
    Call<ExchangeTokenResponse> exchangeAccessToken(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("gsdk/account/login")
    Call<SdkUserInfo> login(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/g_login")
    Call<SdkUserInfo> loginPrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("gsdk/account/logout")
    Call<SdkUserInfo> logout(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/g_logout")
    Call<SdkUserInfo> logoutPrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @GET("gsdk/account/query_open_id")
    Call<QuerySdkOpenIdResponse> querySdkOpenIdByUserId(@AddCommonParam boolean z, @Query("access_token") String str, @Query("user_id") long j2);

    @GET("v3/account/query_open_id")
    Call<QuerySdkOpenIdResponse> querySdkOpenIdByUserIdPrivatization(@AddCommonParam boolean z, @Query("access_token") String str, @Query("user_id") long j2);
}
