package com.google.android.play.core.assetpacks;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class co {

    /* renamed from: a  reason: collision with root package name */
    private final Map f16624a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double a(String str) {
        Double d2 = (Double) this.f16624a.get(str);
        if (d2 == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return d2.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized double b(String str, dg dgVar) {
        double d2;
        d2 = (((ce) dgVar).f16573f + 1.0d) / ((ce) dgVar).f16574g;
        this.f16624a.put(str, Double.valueOf(d2));
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void c(String str) {
        this.f16624a.put(str, Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    }
}
