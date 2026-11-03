package com.bytedance.sdk.bridge.js.auth;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import java.util.Map;
/* loaded from: classes3.dex */
public interface ConfigRequestApi {
    public static final String CONFIG_BASE = "http://ib.snssdk.com";
    public static final String CONFIG_URL = "/client_auth/js_sdk/config/v1/";

    @GET(CONFIG_URL)
    Call<String> getConfig(@QueryMap Map<String, String> map);
}
