package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.bytedance.applog.forward.b;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
/* loaded from: classes9.dex */
public final class zzd implements AppMeasurementSdk.OnEventListener {
    private final /* synthetic */ zze zza;

    public zzd(zze zzeVar) {
        this.zza = zzeVar;
    }

    @Override // com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener, com.google.android.gms.measurement.internal.zzjl
    public final void onEvent(String str, String str2, Bundle bundle, long j2) {
        AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener;
        if (this.zza.zza.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(b.f2687d, zzb.zza(str2));
            analyticsConnectorListener = this.zza.zzb;
            analyticsConnectorListener.onMessageTriggered(2, bundle2);
        }
    }
}
