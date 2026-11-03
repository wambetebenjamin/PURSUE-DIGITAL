package com.bytedance.pipo.payment.common.lib.support.service;

import com.bytedance.pipo.payment.common.lib.settings.PaymentLocalSettings;
import com.bytedance.pipo.payment.common.lib.settings.PaymentOnlineSettings;
import org.json.JSONObject;
/* compiled from: ISettingService.java */
/* loaded from: classes2.dex */
public interface h extends com.bytedance.pipo.payment.common.lib.callback.g {
    void a();

    void a(JSONObject jSONObject);

    PaymentOnlineSettings b();

    void b(JSONObject jSONObject);

    PaymentLocalSettings c();

    long d();

    long e();

    long f();

    boolean g();

    boolean h();

    long i();

    boolean l();

    long m();
}
