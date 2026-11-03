package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes4.dex */
final class zzu extends zzs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(int i2, int i3, Bundle bundle) {
        super(i2, i3, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzs
    public final void zza(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        zzd(bundle2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.cloudmessaging.zzs
    public final boolean zzb() {
        return false;
    }
}
