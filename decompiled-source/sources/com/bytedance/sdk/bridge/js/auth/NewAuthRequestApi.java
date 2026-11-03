package com.bytedance.sdk.bridge.js.auth;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.google.gson.JsonObject;
/* loaded from: classes3.dex */
public interface NewAuthRequestApi {
    public static final String AUTH_BASE = "https://jsb.snssdk.com/";
    public static final String AUTH_URL = "/src/server/v2/package";

    @Headers({"Content-Type:application/json"})
    @POST(AUTH_URL)
    Call<String> requesAuthInfo(@Body JsonObject jsonObject);
}
