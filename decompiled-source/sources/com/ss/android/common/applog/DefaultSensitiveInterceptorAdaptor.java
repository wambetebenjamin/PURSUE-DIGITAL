package com.ss.android.common.applog;

import android.util.Pair;
import com.bytedance.bdinstall.sensitive.a;
import com.ss.android.deviceregister.x;
/* loaded from: classes9.dex */
public class DefaultSensitiveInterceptorAdaptor extends a {
    private final x callback;

    public DefaultSensitiveInterceptorAdaptor(x xVar) {
        this.callback = xVar;
    }

    @Override // com.bytedance.bdinstall.sensitive.a, com.bytedance.bdinstall.sensitive.b
    public String getNetworkOperator() {
        return this.callback.b();
    }

    @Override // com.bytedance.bdinstall.sensitive.a, com.bytedance.bdinstall.sensitive.b
    public String getNetworkOperatorName() {
        return this.callback.c();
    }

    @Override // com.bytedance.bdinstall.sensitive.a, com.bytedance.bdinstall.sensitive.b
    public String getSimCountryIso() {
        return this.callback.d();
    }

    @Override // com.bytedance.bdinstall.sensitive.a, com.bytedance.bdinstall.sensitive.b
    public String getCountry() {
        return this.callback.o();
    }

    @Override // com.bytedance.bdinstall.sensitive.a, com.bytedance.bdinstall.sensitive.b
    public Pair<String, Boolean> getGAID() {
        return this.callback.n();
    }
}
