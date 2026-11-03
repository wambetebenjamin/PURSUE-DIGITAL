package com.bytedance.ttgame.module.loccom.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.module.loccom.model.BaseResponse;
/* loaded from: classes6.dex */
public interface LocationIpApi {
    @GET("gsdk/misc/location/ipinfo")
    Call<BaseResponse> getLocationByIp(@Query("ISOLanguage") String str, @Query("IP") String str2, @Query("StrategyVersion") int i2);
}
