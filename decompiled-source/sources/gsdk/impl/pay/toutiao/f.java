package gsdk.impl.pay.toutiao;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Field;
import com.bytedance.retrofit2.http.FormUrlEncoded;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.channel.adapter.v4.V4Response;
/* loaded from: classes10.dex */
public interface f {
    @GET("gsdk/v4/payment/query_goods_with_activity")
    Call<V4Response> a(@Query("encrypt_msg") String str);

    @FormUrlEncoded
    @POST("gsdk/v4/payment/create_order")
    Call<V4Response> b(@Field("encrypt_msg") String str);

    @FormUrlEncoded
    @POST("gsdk/v4/payment/minors")
    Call<V4Response> c(@Field("encrypt_msg") String str);

    @GET("gsdk/v4/payment/minors")
    Call<V4Response> d(@Query("encrypt_msg") String str);
}
