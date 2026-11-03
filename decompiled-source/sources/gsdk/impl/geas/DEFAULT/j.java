package gsdk.impl.geas.DEFAULT;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
/* compiled from: UdpApi.kt */
/* loaded from: classes10.dex */
public interface j {
    @GET("get_strategy")
    Call<i> a(@Query("scene") String str, @Query("ip") String str2);
}
