package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public final class zzin {
    public static final /* synthetic */ int zza = 0;

    static {
        try {
            if (System.getenv("PROTOBUF_DISABLE_UNSAFE_UTF8_PROCESSOR_FOR_TESTING") != null) {
                return;
            }
        } catch (SecurityException unused) {
        }
        if (zzii.zzx() && zzii.zzy()) {
            int i2 = zzei.zza;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
        return r12 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int zza(java.lang.String r10, byte[] r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzin.zza(java.lang.String, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(String str) {
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length && str.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            char charAt = str.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                try {
                    int length2 = str.length();
                    while (i3 < length2) {
                        char charAt2 = str.charAt(i3);
                        if (charAt2 < 2048) {
                            i2 += (127 - charAt2) >>> 31;
                        } else {
                            i2 += 2;
                            if (charAt2 >= 55296 && charAt2 <= 57343) {
                                if (Character.codePointAt(str, i3) < 65536) {
                                    throw new zzim(i3, length2);
                                }
                                i3++;
                            }
                        }
                        i3++;
                    }
                    i4 += i2;
                } catch (zzim unused) {
                    return str.getBytes(zzga.zza).length;
                }
            }
        }
        if (i4 >= length) {
            return i4;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i4 + 4294967296L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(byte[] bArr, int i2, int i3) {
        while (i2 < i3 && bArr[i2] >= 0) {
            i2++;
        }
        if (i2 >= i3) {
            return true;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 < 0) {
                if (b2 < -32) {
                    if (i4 < i3 && b2 >= -62) {
                        i2 = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return false;
                }
                if (b2 < -16) {
                    if (i4 < i3 - 1) {
                        int i5 = i4 + 1;
                        byte b3 = bArr[i4];
                        if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                            i2 = i5 + 1;
                            if (bArr[i5] > -65) {
                            }
                        }
                    }
                } else if (i4 < i3 - 2) {
                    int i6 = i4 + 1;
                    byte b4 = bArr[i4];
                    if (b4 <= -65 && (((b2 << Ascii.FS) + (b4 + 112)) >> 30) == 0) {
                        int i7 = i6 + 1;
                        if (bArr[i6] <= -65) {
                            i4 = i7 + 1;
                            if (bArr[i7] > -65) {
                            }
                        }
                    }
                }
                return false;
            }
            i2 = i4;
        }
        return true;
    }
}
