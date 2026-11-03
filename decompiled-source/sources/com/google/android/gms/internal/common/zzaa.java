package com.google.android.gms.internal.common;

import java.util.Arrays;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
class zzaa extends zzab {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(int i2) {
    }

    public final zzaa zza(Object obj) {
        int i2;
        Objects.requireNonNull(obj);
        int length = this.zza.length;
        int i3 = this.zzb + 1;
        if (i3 >= 0) {
            if (i3 <= length) {
                i2 = length;
            } else {
                i2 = (length >> 1) + length + 1;
                if (i2 < i3) {
                    int highestOneBit = Integer.highestOneBit(i3 - 1);
                    i2 = highestOneBit + highestOneBit;
                }
                if (i2 < 0) {
                    i2 = Integer.MAX_VALUE;
                }
            }
            if (i2 > length || this.zzc) {
                this.zza = Arrays.copyOf(this.zza, i2);
                this.zzc = false;
            }
            Object[] objArr = this.zza;
            int i4 = this.zzb;
            this.zzb = i4 + 1;
            objArr[i4] = obj;
            return this;
        }
        throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
    }
}
