package gsdk.impl.compliance.agegate;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.ttgame.module.compliance.impl.agegate.entity.ServerTimestampResponse;
/* compiled from: IAgeGateApi.kt */
/* loaded from: classes10.dex */
public interface f {
    @GET("/gsdk/client-ip")
    Call<ServerTimestampResponse> a();
}
