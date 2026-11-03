package com.bytedance.ttgame.channel.account.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.ttgame.channel.entity.OSAccountInfoResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface IChannelLoginParamApi {
    @GET("gsdk/account/all_os_users")
    Call<OSAccountInfoResponse> getAccountsWithSamePuid(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @GET("gsdk/account/login_params")
    Call<ChannelLoginParamResponse> loginParmas(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);
}
