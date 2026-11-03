package com.bytedance.ttgame.module.push.network;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.util.List;
import java.util.Map;
/* compiled from: PushNetwork.kt */
/* loaded from: classes6.dex */
public interface PushNetwork {
    @GET
    Call<String> doGet(@AddCommonParam Boolean bool, @Url String str, @HeaderList List<Header> list);

    @POST
    Call<String> doPost(@Url String str, @HeaderList List<Header> list, @Body TypedOutput typedOutput);

    @FormUrlEncoded
    @POST
    Call<String> doPost(@Url String str, @HeaderList List<Header> list, @FieldMap Map<String, String> map);
}
