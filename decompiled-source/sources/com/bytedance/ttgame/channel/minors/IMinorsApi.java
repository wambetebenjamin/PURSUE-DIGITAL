package com.bytedance.ttgame.channel.minors;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import kotlin.Metadata;
/* compiled from: MinorsLimit.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/channel/minors/IMinorsApi;", "", "getMinorsLimit", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/channel/minors/MinorsLimitResponse;", "encryptMessage", "", "setMinorsLimit", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IMinorsApi {
    @GET("v3/m_pay/minors")
    Call<MinorsLimitResponse> getMinorsLimit(@Query("encrypt_msg") String str);

    @FormUrlEncoded
    @POST("v3/m_pay/minors")
    Call<MinorsLimitResponse> setMinorsLimit(@Field("encrypt_msg") String str);
}
