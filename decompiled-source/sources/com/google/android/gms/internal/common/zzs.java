package com.google.android.gms.internal.common;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzs extends zzv {
    final /* synthetic */ zzp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzw zzwVar, CharSequence charSequence, zzp zzpVar) {
        super(zzwVar, charSequence);
        this.zza = zzpVar;
    }

    @Override // com.google.android.gms.internal.common.zzv
    final int zzc(int i2) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzr.zzc(i2, length, "index");
        while (i2 < length) {
            if (this.zza.zza(charSequence.charAt(i2))) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.common.zzv
    final int zzd(int i2) {
        return i2 + 1;
    }
}
