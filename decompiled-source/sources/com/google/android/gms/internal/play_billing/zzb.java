package com.google.android.gms.internal.play_billing;

import com.ss.android.ugc.quota.tag.a;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public enum zzb {
    RESPONSE_CODE_UNSPECIFIED(a.UNSPECIFIED),
    SERVICE_TIMEOUT(-3),
    FEATURE_NOT_SUPPORTED(-2),
    SERVICE_DISCONNECTED(-1),
    OK(0),
    USER_CANCELED(1),
    SERVICE_UNAVAILABLE(2),
    BILLING_UNAVAILABLE(3),
    ITEM_UNAVAILABLE(4),
    DEVELOPER_ERROR(5),
    ERROR(6),
    ITEM_ALREADY_OWNED(7),
    ITEM_NOT_OWNED(8),
    EXPIRED_OFFER_TOKEN(11),
    NETWORK_ERROR(12);
    
    private static final zzbz zzp;
    private final int zzr;

    static {
        zzb[] values;
        zzby zzbyVar = new zzby();
        for (zzb zzbVar : values()) {
            zzbyVar.zza(Integer.valueOf(zzbVar.zzr), zzbVar);
        }
        zzp = zzbyVar.zzb();
    }

    zzb(int i2) {
        this.zzr = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzb zza(int i2) {
        zzbz zzbzVar = zzp;
        Integer valueOf = Integer.valueOf(i2);
        return !zzbzVar.containsKey(valueOf) ? RESPONSE_CODE_UNSPECIFIED : (zzb) zzbzVar.get(valueOf);
    }
}
