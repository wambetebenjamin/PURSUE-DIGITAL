package com.bytedance.ttgame.sdk.module.account.api;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Path;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
import gsdk.impl.account.toutiao.cd;
import gsdk.impl.account.toutiao.cg;
import java.util.Map;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J5\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J5\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J5\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J5\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J5\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u001b\b\u0001\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u000b\u0012\t\u0018\u00010\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J=\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0016\u001a\u00020\t2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'J3\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0019\b\u0001\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\t\u0012\u00070\u0001¢\u0006\u0002\b\n0\bH'¨\u0006\u001b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/api/AccountCodeApi;", "", "LoginAccountCode", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/account/toutiao/account/pojo/UserInfoResponse;", "addCommonPara", "", "map", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "LoginAccountCodePrivatization", "accountCodePasswordChange", "Lcom/bytedance/ttgame/base/BaseResponse;", "accountCodePasswordChangePrivatization", "accountOverseaInfo", "Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountPasswordResponse;", "accountOverseaInfoPrivatization", "createAccountCode", "Lcom/bytedance/ttgame/sdk/module/account/pojo/AccountCodeResponse;", "createAccountCodePrivatization", "modifyAccount", "path", "modifyAccountCode", "modifyAccountCodePrivatization", "queryAccountCode", "queryAccountCodePrivatization", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AccountCodeApi {
    @FormUrlEncoded
    @POST("sdk/account/account_code/login")
    Call<UserInfoResponse> LoginAccountCode(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/account_code/login")
    Call<UserInfoResponse> LoginAccountCodePrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sdk/account/account_code/password/change")
    Call<BaseResponse> accountCodePasswordChange(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/account_code/password/change")
    Call<BaseResponse> accountCodePasswordChangePrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @POST("sdk/account/oversea_info")
    Call<cg> accountOverseaInfo(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @POST("v3/account/oversea_info")
    Call<cg> accountOverseaInfoPrivatization(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sdk/account/account_code/create")
    Call<cd> createAccountCode(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/account_code/create")
    Call<cd> createAccountCodePrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("{path}/account/account_code/change_account_code")
    Call<BaseResponse> modifyAccount(@Path("path") String str, @AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("sdk/account/account_code/password/change")
    Call<cd> modifyAccountCode(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("v3/account/account_code/password/change")
    Call<cd> modifyAccountCodePrivatization(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @GET("sdk/account/account_code/query")
    Call<cd> queryAccountCode(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @GET("v3/account/account_code/query")
    Call<cd> queryAccountCodePrivatization(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);
}
