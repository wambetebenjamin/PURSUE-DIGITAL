package com.google.android.gms.internal.games_v2;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public class zzgh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i2, int i3) {
        if (i3 >= 0) {
            if (i3 <= i2) {
                return i2;
            }
            int i4 = i2 + (i2 >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i3 - 1);
                i4 = highestOneBit + highestOneBit;
            }
            if (i4 < 0) {
                return Integer.MAX_VALUE;
            }
            return i4;
        }
        throw new IllegalArgumentException("cannot store more than Integer.MAX_VALUE elements");
    }
}
