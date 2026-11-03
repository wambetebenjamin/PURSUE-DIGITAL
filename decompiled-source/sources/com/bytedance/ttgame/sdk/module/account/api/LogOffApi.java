package com.bytedance.ttgame.sdk.module.account.api;

import androidx.lifecycle.LiveData;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.main.internal.net.ApiResponse;
import gsdk.impl.account.toutiao.cm;
import java.util.Map;
/* loaded from: classes3.dex */
public interface LogOffApi {
    @FormUrlEncoded
    @POST("sdk/account/log_off")
    LiveData<ApiResponse<BaseResponse>> logOff(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("gsdk/compliance/pass/logoff/cancel")
    Call<cm> passLogoffCancel(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);
}
