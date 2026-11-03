package com.bytedance.ttgame.sdk.module.account.api;

import androidx.lifecycle.LiveData;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Path;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.main.internal.net.ApiResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import gsdk.impl.account.toutiao.ci;
import java.util.HashMap;
/* loaded from: classes3.dex */
public interface AccountManagementApi {
    @FormUrlEncoded
    @POST("sdk/bind/do_v3")
    LiveData<ApiResponse<UserInfoResponse>> bindOrChangeBind(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("{path}/bind/do_v3")
    Call<UserInfoResponse> bindOrChangeBindCall(@Path("path") String str, @AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("v3/bind/do_v3")
    LiveData<ApiResponse<UserInfoResponse>> bindOrChangeBindPrivatization(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("sdk/mobile/change_v2")
    Call<ci> changeMobile(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("sdk/mobile/password")
    Call<BaseResponse> setMobilePassword(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("sdk/bind/unbind")
    LiveData<ApiResponse<UserInfoResponse>> unBind(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("{path}/bind/unbind")
    Call<UserInfoResponse> unBindCall(@Path("path") String str, @AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("v3/bind/unbind")
    LiveData<ApiResponse<UserInfoResponse>> unBindPrivatization(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);
}
