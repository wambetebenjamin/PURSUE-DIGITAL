package com.bytedance.bdinstall.sensitive;

import android.util.Pair;
/* compiled from: SensitiveInterceptor.java */
/* loaded from: classes.dex */
abstract class b {
    abstract String getCountry();

    abstract Pair<String, Boolean> getGAID();

    abstract String getNetworkOperator();

    abstract String getNetworkOperatorName();

    abstract String getSimCountryIso();
}
