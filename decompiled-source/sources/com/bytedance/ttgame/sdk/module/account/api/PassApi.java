package com.bytedance.ttgame.sdk.module.account.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import gsdk.impl.account.toutiao.cn;
import java.util.HashMap;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JJ\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062,\b\u0001\u0010\u0007\u001a&\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bj\u0012\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u0001`\nH'¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/api/PassApi;", "", "isPassRegistered", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/sdk/module/account/pojo/PassRegisterResponse;", "addCommonPara", "", "map", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface PassApi {
    @FormUrlEncoded
    @POST("/sdk/account/pass/is_registered")
    Call<cn> isPassRegistered(@AddCommonParam boolean z, @FieldMap HashMap<String, Object> hashMap);
}
