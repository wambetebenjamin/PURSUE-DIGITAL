package com.bytedance.ttgame.module.abtest.impl.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.core.cache.SettingsResponse;
/* loaded from: classes2.dex */
public interface UpdateSettingsApi {
    @GET("/service/settings/v3/")
    Call<SettingsResponse> fetchSettings(@AddCommonParam boolean z, @Query("user_unique_id") String str, @Query("user_register_time") String str2);
}
