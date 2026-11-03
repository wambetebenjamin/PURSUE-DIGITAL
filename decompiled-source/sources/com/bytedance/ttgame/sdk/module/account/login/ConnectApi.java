package com.bytedance.ttgame.sdk.module.account.login;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface ConnectApi {
    @FormUrlEncoded
    @POST("sdk/bind/connect")
    Call<UserInfoResponse> bindConnect(@AddCommonParam boolean z, @FieldMap Map<String, Long> map);
}
