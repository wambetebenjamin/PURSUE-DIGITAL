package com.bytedance.bdturing.setting;

import com.bytedance.sdk.account.monitor.a;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONObject;
/* compiled from: DefaultSettings.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u0013\u0010\u0003\u001a\u00020\u00048F¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/bytedance/bdturing/setting/DefaultSettings;", "", "()V", "SETTINGS", "Lorg/json/JSONObject;", "getSETTINGS", "()Lorg/json/JSONObject;", "SETTINGS_UPDATED", a.InterfaceC0193a.RAW, "", "getRaw", "()Ljava/lang/String;", "raw$delegate", "Lkotlin/Lazy;", "updateSettings", "", com.bytedance.bdturing.localstorage.b.KEY_SETTINGS, "Lcom/bytedance/bdturing/net/models/DefaultSettingsModel;", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class d {
    public static final d INSTANCE;

    /* renamed from: a  reason: collision with root package name */
    private static final Lazy f4300a;

    /* renamed from: b  reason: collision with root package name */
    private static final JSONObject f4301b;

    /* renamed from: c  reason: collision with root package name */
    private static JSONObject f4302c;

    /* compiled from: DefaultSettings.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes.dex */
    static final class a extends Lambda implements Function0<String> {
        public static final a INSTANCE = new a();

        a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "{\n    \"common\": {\n        \"period\": 300000,\n        \"retry_count\": 3,\n        \"retry_interval\": 2000,\n        \"use_dialog_size_v2\": 1,\n        \"alpha\": \"0.5\",\n        \"rgb\": \"000000\",\n        \"skip_launch\": 0,\n        \"use_jsb_request\": 1,\n        \"use_native_report\": 1,\n        \"pre_create\": 0,\n        \"request_encrypt\": 0,\n        \"host\": {},\n        \"back_up_host\": {}\n    },\n    \"verify\": {\n        \"host\": {},\n        \"back_up_host\": {},\n        \"url\": {},\n        \"height\": 303,\n        \"width\": 300\n    }\n}";
        }
    }

    private d() {
    }

    static {
        d dVar = new d();
        INSTANCE = dVar;
        f4300a = LazyKt.lazy(a.INSTANCE);
        f4302c = new JSONObject();
        f4301b = new JSONObject(dVar.a());
    }

    private final String a() {
        return (String) f4300a.getValue();
    }

    public final JSONObject getSETTINGS() {
        return f4302c.length() > 0 ? f4302c : f4301b;
    }

    public final void a(com.bytedance.bdturing.net.models.a settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        JSONObject a2 = settings.a();
        JSONObject b2 = settings.b();
        JSONObject c2 = settings.c();
        JSONObject jSONObject = getSETTINGS().getJSONObject("common");
        jSONObject.put(g.HOST_KEY, a2);
        JSONObject jSONObject2 = getSETTINGS().getJSONObject(g.VERIFY_SERVICE);
        jSONObject2.put("url", b2);
        jSONObject2.put(g.HOST_KEY, c2);
        getSETTINGS().put("common", jSONObject);
        getSETTINGS().put(g.VERIFY_SERVICE, jSONObject2);
        f4302c = getSETTINGS();
    }
}
