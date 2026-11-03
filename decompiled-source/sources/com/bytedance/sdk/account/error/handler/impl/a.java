package com.bytedance.sdk.account.error.handler.impl;

import android.text.TextUtils;
import com.bytedance.bdturing.methods.l;
import com.bytedance.sdk.account.error.handler.a;
import com.google.android.datatransport.BuildConfig;
import com.ss.android.ShowDialogActivity;
import com.ss.android.account.d;
import com.ss.android.account.dbtring.b;
import com.ss.android.e;
import com.ss.android.f;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: CaptchaErrorHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J8\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0011"}, d2 = {"Lcom/bytedance/sdk/account/error/handler/impl/CaptchaErrorHandler;", "Lcom/bytedance/sdk/account/error/handler/ErrorHandler;", "()V", "checkBdTuringVersion", "", "handle", "", "error", "", "requestParams", "", "", "responseRawJson", "Lorg/json/JSONObject;", l.VALUE_CALLBACK, "Lcom/bytedance/sdk/account/error/handler/ErrorHandler$HandlerCallback;", "Companion", "account-sdk-core_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class a implements com.bytedance.sdk.account.error.handler.a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f12350b = "CaptchaErrorHandler";

    /* renamed from: c  reason: collision with root package name */
    private static boolean f12351c;

    /* renamed from: a  reason: collision with root package name */
    public static final C0187a f12349a = new C0187a(null);

    /* renamed from: d  reason: collision with root package name */
    private static boolean f12352d = true;

    /* compiled from: CaptchaErrorHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/bytedance/sdk/account/error/handler/impl/CaptchaErrorHandler$Companion;", "", "()V", "TAG", "", "hadCheckBDTuringVersion", "", "needAccountSDKShowCaptcha", "account-sdk-core_release"}, k = 1, mv = {1, 1, 15})
    /* renamed from: com.bytedance.sdk.account.error.handler.impl.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0187a {
        private C0187a() {
        }

        public /* synthetic */ C0187a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.bytedance.sdk.account.error.handler.a
    public boolean a(int i2, Map<String, String> map, JSONObject jSONObject, a.InterfaceC0186a callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (jSONObject == null) {
            return false;
        }
        a();
        String optString = jSONObject.optString("verify_center_decision_conf");
        if (TextUtils.isEmpty(optString)) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            optString = optJSONObject != null ? optJSONObject.optString("verify_center_decision_conf") : null;
        }
        d inst = d.getInst();
        Intrinsics.checkExpressionValueIsNotNull(inst, "BdTruingManager.getInst()");
        com.ss.android.account.dbtring.b bdTruing = inst.getBdTruing();
        if (!f12352d || (!(i2 == 1104 || i2 == 1105 || !TextUtils.isEmpty(optString)) || bdTruing == null || bdTruing.forceDisable())) {
            return false;
        }
        bdTruing.showVerifyDialog(i2, optString, new b(callback));
        return true;
    }

    /* compiled from: CaptchaErrorHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/bytedance/sdk/account/error/handler/impl/CaptchaErrorHandler$handle$1", "Lcom/ss/android/account/dbtring/IBdTruing$IAccountBdTuringCallback;", "onFail", "", "onSuccess", "account-sdk-core_release"}, k = 1, mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class b implements b.a {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ a.InterfaceC0186a f12353a;

        b(a.InterfaceC0186a interfaceC0186a) {
            this.f12353a = interfaceC0186a;
        }

        @Override // com.ss.android.account.dbtring.b.a
        public void onFail() {
            this.f12353a.a(false, false, null);
        }

        @Override // com.ss.android.account.dbtring.b.a
        public void onSuccess() {
            this.f12353a.a(true, true, null);
        }
    }

    private final void a() {
        String str;
        if (f12351c) {
            return;
        }
        boolean z = false;
        try {
            Class<?> cls = Class.forName("com.bytedance.bdturing.reflect.VersionUtils");
            str = cls.getMethod("getVersionName", new Class[0]).invoke(cls.newInstance(), new Object[0]).toString();
        } catch (Throwable th) {
            th.printStackTrace();
            str = "";
        }
        if (TextUtils.isEmpty(str) || com.bytedance.sdk.account.bus.util.a.a(BuildConfig.VERSION_NAME, str) > 0) {
            z = true;
        }
        f12352d = z;
        if (z) {
            d inst = d.getInst();
            Intrinsics.checkExpressionValueIsNotNull(inst, "BdTruingManager.getInst()");
            if (inst.getBdTruing() != null) {
                d inst2 = d.getInst();
                Intrinsics.checkExpressionValueIsNotNull(inst2, "BdTruingManager.getInst()");
                if (inst2.getBdTruing().a()) {
                    f config = com.ss.android.account.f.getConfig();
                    Intrinsics.checkExpressionValueIsNotNull(config, "TTAccountInit.getConfig()");
                    if (config.isLocalTest()) {
                        f config2 = com.ss.android.account.f.getConfig();
                        Intrinsics.checkExpressionValueIsNotNull(config2, "TTAccountInit.getConfig()");
                        ShowDialogActivity.showDialog(config2.getApplicationContext(), "未接入验证码SDK或版本过低", "请升级验证码SDK到3.1.0版本以上！");
                    } else {
                        e.e(f12350b, "未接入验证码SDK或版本过低，请升级验证码SDK到3.1.0版本以上！");
                    }
                }
            }
        }
        f12351c = true;
    }
}
