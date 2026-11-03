package com.bytedance.ttgame.module.share.request;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import gsdk.impl.share.DEFAULT.ad;
import gsdk.impl.share.DEFAULT.af;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\tH'¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/share/request/Request;", "", "requestAweShareId", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/share/request/AweShareIdResponse;", "body", "Lcom/bytedance/ttgame/module/share/request/IAweShareIdRequestBody;", "requestAweShareVideoInfo", "Lcom/bytedance/ttgame/module/share/request/AweShareVideoInfoResponse;", "Lcom/bytedance/ttgame/module/share/request/IAweShareVideoInfoRequestBody;", "share_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface Request {
    @Headers({"Content-Type: application/json"})
    @POST("/gsdk/share/douyin_video")
    Call<ad> requestAweShareId(@Body IAweShareIdRequestBody iAweShareIdRequestBody);

    @Headers({"Content-Type: application/json"})
    @POST("/gsdk/share/douyin_video/search")
    Call<af> requestAweShareVideoInfo(@Body IAweShareVideoInfoRequestBody iAweShareVideoInfoRequestBody);
}
