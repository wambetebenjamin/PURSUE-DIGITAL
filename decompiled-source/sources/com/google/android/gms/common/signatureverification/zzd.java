package com.google.android.gms.common.signatureverification;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzd {
    private static SignatureVerificationConfiguration zza;

    public static synchronized void zza(SignatureVerificationConfiguration signatureVerificationConfiguration) {
        synchronized (zzd.class) {
            if (zza != null) {
                throw new IllegalStateException("Redundantly setting SignatureVerificationConfiguration");
            }
            zza = signatureVerificationConfiguration;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized SignatureVerificationConfiguration zzc() {
        SignatureVerificationConfiguration signatureVerificationConfiguration;
        synchronized (zzd.class) {
            if (zza == null) {
                zza(new zzb());
            }
            signatureVerificationConfiguration = zza;
        }
        return signatureVerificationConfiguration;
    }
}
