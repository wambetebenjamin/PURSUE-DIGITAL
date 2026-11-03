package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes5.dex */
final class zzhl extends zzhk {
    @Override // com.google.android.gms.internal.auth.zzhk
    final int zza(int i2, byte[] bArr, int i3, int i4) {
        while (i3 < i4 && bArr[i3] >= 0) {
            i3++;
        }
        if (i3 >= i4) {
            return 0;
        }
        while (i3 < i4) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            if (b2 < 0) {
                if (b2 < -32) {
                    if (i5 < i4) {
                        if (b2 >= -62) {
                            i3 = i5 + 1;
                            if (bArr[i5] > -65) {
                            }
                        }
                        return -1;
                    }
                    return b2;
                }
                if (b2 < -16) {
                    if (i5 < i4 - 1) {
                        int i6 = i5 + 1;
                        byte b3 = bArr[i5];
                        if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                            i3 = i6 + 1;
                            if (bArr[i6] > -65) {
                            }
                        }
                    } else {
                        return zzhm.zza(bArr, i5, i4);
                    }
                } else if (i5 < i4 - 2) {
                    int i7 = i5 + 1;
                    byte b4 = bArr[i5];
                    if (b4 <= -65 && (((b2 << Ascii.FS) + (b4 + 112)) >> 30) == 0) {
                        int i8 = i7 + 1;
                        if (bArr[i7] <= -65) {
                            i5 = i8 + 1;
                            if (bArr[i8] > -65) {
                            }
                        }
                    }
                } else {
                    return zzhm.zza(bArr, i5, i4);
                }
                return -1;
            }
            i3 = i5;
        }
        return 0;
    }
}
