package com.bytedance.applog.params;

import androidx.lifecycle.LifecycleOwner;
import gsdk.impl.account.toutiao.dh;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: BizCommonParamsApi.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0001H&J,\u0010\b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0006H&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H&J4\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u0016H&¨\u0006\u0017"}, d2 = {"Lcom/bytedance/applog/params/BizCommonParamsApi;", "", "clear", "", "get", "key", "", "remove", dh.z, "value", "context", "", "setCacheCapability", com.bytedance.applog.sampling.d.f3359b, "", "setLifecycleEnable", "enable", "", "setRuleConfig", "configJsonArray", "setWithLifecycle", "valueLifecycle", "Landroidx/lifecycle/LifecycleOwner;", "params_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public interface a {
    void a();

    void a(int i2);

    void a(String str);

    void a(String str, Object obj);

    void a(String str, Object obj, LifecycleOwner lifecycleOwner);

    void a(String str, Object obj, Map<String, ? extends Object> map);

    void a(String str, Object obj, Map<String, ? extends Object> map, LifecycleOwner lifecycleOwner);

    void a(boolean z);

    Object b(String str);

    void c(String str);
}
