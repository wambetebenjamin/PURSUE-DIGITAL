package gsdk.impl.dynamic.DEFAULT;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.AddCommonParam;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.QueryMap;
import com.bytedance.ttgame.module.dynamic.api.BundlePackage;
import com.bytedance.ttgame.module.dynamic.api.ModuleRules;
import java.util.Map;
/* compiled from: IDynamicApi.java */
/* loaded from: classes10.dex */
public interface g {
    @GET("sdk_dynamic/api/v2/module")
    Call<BundlePackage> a(@AddCommonParam(level = 31) boolean z, @QueryMap Map<String, Object> map, @QueryMap Map<String, Object> map2);

    @GET("sdk_dynamic/api/v1/module_rule")
    Call<ModuleRules> b(@AddCommonParam(level = 31) boolean z, @QueryMap Map<String, Object> map, @QueryMap Map<String, Object> map2);
}
