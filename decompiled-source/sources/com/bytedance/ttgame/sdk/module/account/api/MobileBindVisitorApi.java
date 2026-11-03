package com.bytedance.ttgame.sdk.module.account.api;

import androidx.lifecycle.LiveData;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.main.internal.net.ApiResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import java.util.Map;
/* loaded from: classes3.dex */
public interface MobileBindVisitorApi {
    @FormUrlEncoded
    @POST("sdk/bind/visitor_bind")
    LiveData<ApiResponse<UserInfoResponse>> bindVisitor(@AddCommonParam boolean z, @FieldMap Map<String, Integer> map);

    @FormUrlEncoded
    @POST("v3/bind/visitor_bind")
    LiveData<ApiResponse<UserInfoResponse>> bindVisitorPrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Integer> map);
}
