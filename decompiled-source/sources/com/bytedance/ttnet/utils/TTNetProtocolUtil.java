package com.bytedance.ttnet.utils;

import android.util.Pair;
import com.bytedance.frameworks.baselib.network.http.util.p;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.Callback;
import com.bytedance.retrofit2.Retrofit;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.HeaderList;
import com.bytedance.retrofit2.http.Priority;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.retrofit2.http.Url;
import com.bytedance.retrofit2.mime.TypedInput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public class TTNetProtocolUtil {

    /* loaded from: classes8.dex */
    public interface Service {
        @GET
        @Priority(3)
        Call<TypedInput> getCall(@Url String str, @QueryMap(encode = true) Map<String, String> map, @AddCommonParam boolean z, @HeaderList List<Header> list);
    }

    public static void a(String str, String str2, Map<String, String> map, Map<String, String> map2) {
        Pair<String, String> a2 = p.a("https://" + str + str2, map);
        String str3 = (String) a2.second;
        Retrofit b2 = d.b((String) a2.first);
        if (b2 == null) {
            return;
        }
        Service service = (Service) b2.create(Service.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header("x-ttnet-protocol-handler", "1"));
        arrayList.add(new Header(Request.KEY_BYPASS_TTNET_FEATURES, "1"));
        if (map2 != null && !map2.isEmpty()) {
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        Call<TypedInput> call = service.getCall(str3, map, false, arrayList);
        try {
            try {
                call.execute();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            call.cancel();
        }
    }

    public static void a(String str, String str2, Map<String, String> map, Map<String, String> map2, Callback<TypedInput> callback) {
        Pair<String, String> a2 = p.a("https://" + str + str2, map);
        String str3 = (String) a2.second;
        Retrofit b2 = d.b((String) a2.first);
        if (b2 == null) {
            return;
        }
        Service service = (Service) b2.create(Service.class);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header("x-ttnet-protocol-handler", "1"));
        arrayList.add(new Header(Request.KEY_BYPASS_TTNET_FEATURES, "1"));
        if (map2 != null && !map2.isEmpty()) {
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                arrayList.add(new Header(entry.getKey(), entry.getValue()));
            }
        }
        service.getCall(str3, map, false, arrayList).enqueue(callback);
    }
}
