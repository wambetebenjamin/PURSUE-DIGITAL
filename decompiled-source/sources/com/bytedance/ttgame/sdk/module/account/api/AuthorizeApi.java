package com.bytedance.ttgame.sdk.module.account.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.ExtraInfo;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public interface AuthorizeApi {
    @GET
    Call<String> ttAccountWithPassportGet(@AddCommonParam boolean z, @Url String str, @QueryMap Map<String, String> map, @HeaderList List<Header> list, @ExtraInfo Object obj);

    @FormUrlEncoded
    @POST
    Call<String> ttAccountWithPassportPost(@AddCommonParam boolean z, @Url String str, @HeaderList List<Header> list, @FieldMap Map<String, String> map);
}
