package gsdk.impl.compliance.realname;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.module.compliance.impl.realname.IdentityInfoResponse;
import kotlin.Metadata;
/* compiled from: RealNameApi.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\bH'J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\bH'¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/impl/realname/RealNameApi;", "", "sdkAccountIdentityInfo", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/compliance/impl/realname/IdentityInfoResponse;", "addCommonParam", "", "ttToken", "", "sdkAccountIdentityInfoPrivatization", "compliance_impl_realname_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public interface b {
    @GET("/sdk/account/identity/info")
    Call<IdentityInfoResponse> a(@AddCommonParam boolean z, @Query("token") String str);

    @GET("/v3/account/identity/info")
    Call<IdentityInfoResponse> b(@AddCommonParam boolean z, @Query("token") String str);
}
