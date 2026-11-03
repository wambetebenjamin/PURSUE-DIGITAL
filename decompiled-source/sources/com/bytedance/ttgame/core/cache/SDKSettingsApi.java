package com.bytedance.ttgame.core.cache;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import java.util.HashMap;
/* loaded from: classes3.dex */
public interface SDKSettingsApi {
    @GET("/service/settings/v3/")
    Call<SettingsResponse> fetchSettings(@AddCommonParam boolean z, @QueryMap HashMap<String, Object> hashMap);
}
