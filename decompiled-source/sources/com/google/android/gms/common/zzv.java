package com.google.android.gms.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzv {
    private String zza = null;
    private Boolean zzb = null;
    private Boolean zzc = null;

    private zzv() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzv(byte[] bArr) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzv zza(String str) {
        this.zza = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzv zzb(boolean z) {
        this.zzb = Boolean.valueOf(z);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzv zzc(boolean z) {
        this.zzc = Boolean.valueOf(z);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzw zzd() {
        Boolean bool = this.zzb;
        if (bool != null) {
            if (this.zzc != null) {
                return new zzw(this.zza, bool.booleanValue(), false, false, this.zzc.booleanValue(), false, null);
            }
            throw new IllegalStateException("isGoogleOrPlatformOnly must be set");
        }
        throw new IllegalStateException("allowTestKeys must be set");
    }
}
