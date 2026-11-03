package com.google.android.gms.internal.play_billing;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzet extends zzes {
    private final byte[] zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzet(byte[] bArr) {
        super(null);
        Objects.requireNonNull(bArr);
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final byte zza(int i2) {
        return this.zzb[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzev
    public final byte zzb(int i2) {
        return this.zzb[i2];
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    protected final int zzd(int i2, int i3, int i4) {
        return zzga.zzb(i2, this.zzb, 0, i4);
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final int zze() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.play_billing.zzev
    public final zzev zzf(int i2, int i3) {
        byte[] bArr = this.zzb;
        int zzj = zzj(0, i3, bArr.length);
        return zzj == 0 ? zzev.zza : new zzep(bArr, 0, zzj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzev
    public final void zzg(zzem zzemVar) throws IOException {
        byte[] bArr = this.zzb;
        ((zzez) zzemVar).zzc(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.play_billing.zzev
    public final boolean zzh(zzev zzevVar) {
        boolean z = zzevVar instanceof zzet;
        if (z) {
            return Arrays.equals(this.zzb, ((zzet) zzevVar).zzb);
        }
        boolean z2 = zzevVar instanceof zzep;
        if (z2) {
            byte[] bArr = this.zzb;
            int zze = zzevVar.zze();
            int length = bArr.length;
            if (length > zze) {
                throw new IllegalArgumentException("Length too large: " + length + length);
            } else if (length <= zzevVar.zze()) {
                if (z) {
                    return zzev.zzl(bArr, 0, ((zzet) zzevVar).zzb, 0, length);
                }
                if (z2) {
                    zzep zzepVar = (zzep) zzevVar;
                    return zzev.zzl(bArr, 0, zzep.zzi(zzepVar), zzep.zzc(zzepVar), length);
                }
                return zzevVar.zzf(0, length).equals(zzf(0, length));
            } else {
                int zze2 = zzevVar.zze();
                throw new IllegalArgumentException("Ran off end of other: 0, " + length + ", " + zze2);
            }
        }
        return zzevVar.zzh(this);
    }
}
