package com.bytedance.pipo.payment.common.lib.settings;

import com.bytedance.push.settings.ILocalSettings;
import com.bytedance.push.settings.annotation.f;
@f(storageKey = "global_payment_local_settings")
/* loaded from: classes2.dex */
public interface PaymentLocalSettings extends ILocalSettings {
    String a();

    void a(long j2);

    void a(String str);

    long b();

    void b(long j2);

    void b(String str);

    String c();

    void c(String str);

    String d();

    void d(String str);

    String e();

    void e(String str);

    String f();

    long g();
}
