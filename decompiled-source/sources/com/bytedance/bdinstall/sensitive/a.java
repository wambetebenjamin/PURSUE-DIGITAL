package com.bytedance.bdinstall.sensitive;

import android.util.Pair;
import com.bytedance.bdinstall.am;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.Locale;
/* compiled from: DefaultSensitiveInterceptor.java */
/* loaded from: classes.dex */
public class a extends b {
    private am options;

    public void setOptions(am amVar) {
        this.options = amVar;
    }

    @Override // com.bytedance.bdinstall.sensitive.b
    public String getNetworkOperatorName() {
        return this.options.ae().getNetworkOperatorName();
    }

    @Override // com.bytedance.bdinstall.sensitive.b
    public String getNetworkOperator() {
        return this.options.ae().getNetworkOperator();
    }

    @Override // com.bytedance.bdinstall.sensitive.b
    public String getSimCountryIso() {
        return this.options.ae().getSimCountryIso();
    }

    @Override // com.bytedance.bdinstall.sensitive.b
    public String getCountry() {
        return Locale.getDefault().getCountry();
    }

    @Override // com.bytedance.bdinstall.sensitive.b
    public Pair<String, Boolean> getGAID() {
        String str;
        boolean z;
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.options.d());
        if (advertisingIdInfo != null) {
            str = advertisingIdInfo.getId();
            z = advertisingIdInfo.isLimitAdTrackingEnabled();
        } else {
            str = null;
            z = false;
        }
        return new Pair<>(str, Boolean.valueOf(z));
    }
}
