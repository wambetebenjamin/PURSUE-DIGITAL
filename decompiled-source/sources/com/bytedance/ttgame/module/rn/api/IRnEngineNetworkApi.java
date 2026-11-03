package com.bytedance.ttgame.module.rn.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.ExtraInfo;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.MaxLength;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Streaming;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedInput;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
/* loaded from: classes6.dex */
public interface IRnEngineNetworkApi {
    @GET
    @Streaming
    Call<TypedInput> downloadFile(@AddCommonParam boolean z, @MaxLength long j2, @Url String str, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @ExtraInfo Object obj);

    @GET
    Call<String> getRequestGet(@AddCommonParam boolean z, @Url String str, @QueryMap Map<String, String> map, @HeaderList List<Header> list, @ExtraInfo Object obj, @QueryMap Map<String, Object> map2);

    @GET
    Call<String> getRequestGetV2(@Url String str, @QueryMap Map<String, Object> map, @QueryMap Map<String, Object> map2);

    @FormUrlEncoded
    @POST
    Call<String> getRequestPost(@AddCommonParam boolean z, @Url String str, @HeaderList List<Header> list, @FieldMap Map<String, String> map, @ExtraInfo Object obj, @QueryMap Map<String, Object> map2);

    @POST
    Call<String> getRequestPostJSON(@AddCommonParam boolean z, @Url String str, @HeaderList List<Header> list, @Body RequestBody requestBody, @ExtraInfo Object obj, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    Call<String> getRequestPostV2(@Url String str, @FieldMap Map<String, String> map, @QueryMap Map<String, Object> map2);
}
