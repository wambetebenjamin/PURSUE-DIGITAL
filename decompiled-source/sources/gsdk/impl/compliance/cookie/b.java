package gsdk.impl.compliance.cookie;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import java.util.Map;
/* compiled from: CookieApi.kt */
/* loaded from: classes10.dex */
public interface b {
    @GET("/gsdk/misc/get_user_parameter")
    Call<d> a(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/gsdk/misc/update_user_parameter")
    Call<d> b(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);

    @GET("/v3/misc/get_user_parameter")
    Call<d> c(@AddCommonParam boolean z, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/v3/misc/update_user_parameter")
    Call<d> d(@AddCommonParam boolean z, @FieldMap Map<String, Object> map);
}
