package tt.g.p.f.n;

import android.content.Context;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedByteArray;
import com.bytedance.retrofit2.mime.TypedInput;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class x {

    /* loaded from: classes10.dex */
    interface b {
        @POST
        Call<TypedInput> a(@Url String str, @Body TypedByteArray typedByteArray, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @AddCommonParam boolean z);

        @GET
        Call<TypedInput> a(@Url String str, @QueryMap(encode = true) Map<String, String> map, @HeaderList List<Header> list, @AddCommonParam boolean z);
    }

    public static void a() {
    }

    public static void a(Context context, String str, String str2, String str3) {
    }

    public static void a(String str, Map<String, String> map) {
    }

    public static void a(String str, Map<String, Object> map, Map<String, Object> map2) {
    }
}
