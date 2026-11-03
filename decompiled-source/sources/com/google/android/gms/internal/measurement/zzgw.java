package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
final class zzgw extends ContentObserver {
    private final /* synthetic */ zzgu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgw(zzgu zzguVar, Handler handler) {
        super(null);
        this.zza = zzguVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzd();
    }
}
