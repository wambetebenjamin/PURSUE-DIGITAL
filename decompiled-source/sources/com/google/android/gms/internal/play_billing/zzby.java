package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzby {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzbx zzc;

    public final zzby zza(Object obj, Object obj2) {
        int i2 = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i3 = i2 + i2;
        if (i3 > length) {
            if (i3 > length) {
                length = length + (length >> 1) + 1;
                if (length < i3) {
                    int highestOneBit = Integer.highestOneBit(i3 - 1);
                    length = highestOneBit + highestOneBit;
                }
                if (length < 0) {
                    length = Integer.MAX_VALUE;
                }
            }
            this.zza = Arrays.copyOf(objArr, length);
        }
        zzbr.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i4 = this.zzb;
        int i5 = i4 + i4;
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        this.zzb = i4 + 1;
        return this;
    }

    public final zzbz zzb() {
        zzbx zzbxVar = this.zzc;
        if (zzbxVar != null) {
            throw zzbxVar.zza();
        }
        zzci zzg = zzci.zzg(this.zzb, this.zza, this);
        zzbx zzbxVar2 = this.zzc;
        if (zzbxVar2 == null) {
            return zzg;
        }
        throw zzbxVar2.zza();
    }
}
