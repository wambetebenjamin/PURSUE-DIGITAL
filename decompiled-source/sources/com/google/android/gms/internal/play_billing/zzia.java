package com.google.android.gms.internal.play_billing;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzia extends RuntimeException {
    public zzia(zzhb zzhbVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzgc zza() {
        return new zzgc(getMessage());
    }
}
