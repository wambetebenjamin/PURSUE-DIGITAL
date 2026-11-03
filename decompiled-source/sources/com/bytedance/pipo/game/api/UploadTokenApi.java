package com.bytedance.pipo.game.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import kotlin.Metadata;
/* compiled from: UploadTokenApi.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'¨\u0006\u0006"}, d2 = {"Lcom/bytedance/pipo/game/api/UploadTokenApi;", "", "uploadToken", "Lcom/bytedance/retrofit2/Call;", "", "encryptMessage", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UploadTokenApi {
    @FormUrlEncoded
    @POST("v3/m_pay/receipt")
    Call<String> uploadToken(@Field("encrypt_msg") String str);
}
