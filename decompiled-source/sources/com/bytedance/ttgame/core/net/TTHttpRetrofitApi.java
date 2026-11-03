package com.bytedance.ttgame.core.net;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.ExtraInfo;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public interface TTHttpRetrofitApi {
    @POST
    Call<String> doPost(@Url String str, @HeaderList List<Header> list, @Body TypedOutput typedOutput);

    @POST
    Call<String> doPost(@Url String str, @HeaderList List<Header> list, @Body TypedOutput typedOutput, @AddCommonParam boolean z);

    @FormUrlEncoded
    @POST
    Call<String> doPost(@Url String str, @HeaderList List<Header> list, @FieldMap Map<String, String> map);

    @GET
    Call<String> get(@AddCommonParam boolean z, @Url String str, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @ExtraInfo Object obj);

    @POST
    Call<TypedInput> postDataStream(@Url String str, @HeaderList List<Header> list, @Body TypedOutput typedOutput, @AddCommonParam boolean z);
}
