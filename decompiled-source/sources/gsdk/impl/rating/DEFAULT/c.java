package gsdk.impl.rating.DEFAULT;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.FieldMap;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.MaxLength;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import java.util.Map;
/* compiled from: RatingApi.kt */
/* loaded from: classes10.dex */
public interface c {
    @FormUrlEncoded
    @POST
    Call<String> a(@MaxLength int i2, @Url String str, @QueryMap Map<String, String> map, @FieldMap(encode = true) Map<String, String> map2);

    @GET
    Call<String> a(@AddCommonParam Boolean bool, @Url String str, @MaxLength int i2);

    @GET("/gsdk/doc/comment")
    Call<e> a(@AddCommonParam boolean z);
}
