package com.bytedance.ttgame.module.cdkey;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Path;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.module.cdkey.api.CDkeyExchangeResult;
import com.bytedance.ttgame.module.cdkey.api.GMGiftExchangeResult;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006H'J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u000fH'¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/Request;", "", "cdkeyExchange", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/cdkey/api/CDkeyExchangeResult;", RemoteConfigConstants.RequestFieldKey.APP_ID, "", "aid", "cdkey", "charId", "areaId", "serverId", "gmGiftExchange", "Lcom/bytedance/ttgame/module/cdkey/api/GMGiftExchangeResult;", "params", "Lcom/bytedance/ttgame/module/cdkey/GiftExchangeRequest;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface Request {
    @GET("/game/cdkey/api/use/{app_id}")
    Call<CDkeyExchangeResult> cdkeyExchange(@Path("app_id") String str, @Query("aid") String str2, @Query("cdkey") String str3, @Query("access_token") String str4, @Query("role_id") String str5, @Query("server_id") String str6);

    @Headers({"Content-Type: application/json"})
    @POST("/act/{appid}/process/exec")
    Call<GMGiftExchangeResult> gmGiftExchange(@Path("appid") String str, @Body GiftExchangeRequest giftExchangeRequest);
}
