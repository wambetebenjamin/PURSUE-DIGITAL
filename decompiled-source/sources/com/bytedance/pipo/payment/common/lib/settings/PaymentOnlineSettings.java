package com.bytedance.pipo.payment.common.lib.settings;

import com.bytedance.push.settings.ISettings;
import com.bytedance.push.settings.annotation.f;
import org.json.JSONObject;
@f(storageKey = "global_payment_online_settings")
/* loaded from: classes2.dex */
public interface PaymentOnlineSettings extends ISettings {
    void a(boolean z);

    boolean a();

    JSONObject b();

    JSONObject c();

    boolean d();

    long e();

    JSONObject f();

    boolean h();
}
