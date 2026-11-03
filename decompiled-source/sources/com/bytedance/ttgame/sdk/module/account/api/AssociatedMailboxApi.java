package com.bytedance.ttgame.sdk.module.account.api;

import androidx.lifecycle.LiveData;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.main.internal.net.ApiResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import java.util.HashMap;
/* loaded from: classes3.dex */
public interface AssociatedMailboxApi {
    @FormUrlEncoded
    @POST("sdk/email/bind")
    LiveData<ApiResponse<UserInfoResponse>> bindEmail(@AddCommonParam boolean z, @FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("sdk/email/send_code")
    LiveData<ApiResponse<UserInfoResponse>> sendEmailCode(@AddCommonParam boolean z, @FieldMap HashMap<String, String> hashMap);
}
