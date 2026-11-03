package com.bytedance.applog.params;

import androidx.lifecycle.LifecycleOwner;
import com.facebook.react.uimanager.ViewProps;
import gsdk.impl.account.toutiao.dh;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: EmptyCommonParams.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0011"}, d2 = {"Lcom/bytedance/applog/params/EmptyCommonParams;", "Lcom/bytedance/applog/params/CommonParamsApi;", "Lcom/bytedance/applog/params/CommonParamsHelper;", "()V", "destroy", "", "getBiz", "Lcom/bytedance/applog/params/BizCommonParamsApi;", com.bytedance.apm.perf.traffic.a.KEY_BIZ, "", "handleEvent", "event", "paramsJsonString", "setLogEnable", ViewProps.ENABLED, "", "EmptyBizCommonParams", "params_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class e implements c, d {

    /* renamed from: a  reason: collision with root package name */
    public static final e f3066a = new e();

    @Override // com.bytedance.applog.params.d
    public String a(String event, String str) {
        Intrinsics.checkNotNullParameter(event, "event");
        return str == null ? "" : str;
    }

    @Override // com.bytedance.applog.params.d
    public void a() {
    }

    @Override // com.bytedance.applog.params.d
    public void a(boolean z) {
    }

    private e() {
    }

    /* compiled from: EmptyCommonParams.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J,\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\bH\u0016J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J4\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¨\u0006\u0019"}, d2 = {"Lcom/bytedance/applog/params/EmptyCommonParams$EmptyBizCommonParams;", "Lcom/bytedance/applog/params/BizCommonParamsApi;", "()V", "clear", "", "get", "", "key", "", "remove", dh.z, "value", "context", "", "setCacheCapability", com.bytedance.applog.sampling.d.f3359b, "", "setLifecycleEnable", "enable", "", "setRuleConfig", "configJsonArray", "setWithLifecycle", "valueLifecycle", "Landroidx/lifecycle/LifecycleOwner;", "params_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class a implements com.bytedance.applog.params.a {

        /* renamed from: a  reason: collision with root package name */
        public static final a f3067a = new a();

        @Override // com.bytedance.applog.params.a
        public void a() {
        }

        @Override // com.bytedance.applog.params.a
        public void a(int i2) {
        }

        @Override // com.bytedance.applog.params.a
        public void a(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
        }

        @Override // com.bytedance.applog.params.a
        public void a(String key, Object value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        @Override // com.bytedance.applog.params.a
        public void a(String key, Object value, LifecycleOwner valueLifecycle) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(valueLifecycle, "valueLifecycle");
        }

        @Override // com.bytedance.applog.params.a
        public void a(String key, Object value, Map<String, ? extends Object> context) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(context, "context");
        }

        @Override // com.bytedance.applog.params.a
        public void a(String key, Object value, Map<String, ? extends Object> context, LifecycleOwner valueLifecycle) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(valueLifecycle, "valueLifecycle");
        }

        @Override // com.bytedance.applog.params.a
        public void a(boolean z) {
        }

        @Override // com.bytedance.applog.params.a
        public Object b(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return null;
        }

        @Override // com.bytedance.applog.params.a
        public void c(String configJsonArray) {
            Intrinsics.checkNotNullParameter(configJsonArray, "configJsonArray");
        }

        private a() {
        }
    }

    @Override // com.bytedance.applog.params.c
    public com.bytedance.applog.params.a a(String biz) {
        Intrinsics.checkNotNullParameter(biz, "biz");
        return a.f3067a;
    }
}
