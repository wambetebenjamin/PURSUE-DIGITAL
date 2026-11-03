package com.bytedance.ttgame.sdk.module.account.login.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import java.util.Map;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH'J2\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH'¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/api/ISwitchLoginApi;", "", "switchOSLoginCn", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/account/toutiao/account/pojo/UserInfoResponse;", "addCommonPara", "", "map", "", "", "switchOSLoginI18n", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ISwitchLoginApi {
    @FormUrlEncoded
    @POST("/sdk/account/v3/login/switch_os_login")
    Call<UserInfoResponse> switchOSLoginCn(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/sdk/account/switch_os_login")
    Call<UserInfoResponse> switchOSLoginI18n(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);
}
