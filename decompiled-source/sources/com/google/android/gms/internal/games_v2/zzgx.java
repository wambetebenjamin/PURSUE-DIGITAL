package com.google.android.gms.internal.games_v2;

import java.util.Arrays;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzgx extends zzgp {
    static final zzgp zza = new zzgx(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient Object zzc;
    private final transient int zzd;

    private zzgx(Object obj, Object[] objArr, int i2) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v7 */
    public static zzgx zzd(int i2, Object[] objArr, zzgo zzgoVar) {
        short[] sArr;
        byte[] bArr;
        Object[] objArr2;
        int i3 = i2;
        Object[] objArr3 = objArr;
        if (i3 == 0) {
            return (zzgx) zza;
        }
        Object obj = null;
        if (i3 == 1) {
            zzge.zza(Objects.requireNonNull(objArr3[0]), Objects.requireNonNull(objArr3[1]));
            return new zzgx(null, objArr3, 1);
        }
        zzfz.zzb(i3, objArr3.length >> 1, "index");
        char c2 = 2;
        int max = Math.max(i3, 2);
        int i4 = 1073741824;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            i4 = highestOneBit + highestOneBit;
            while (i4 * 0.7d < max) {
                i4 += i4;
            }
        } else if (max >= 1073741824) {
            throw new IllegalArgumentException("collection too large");
        }
        if (i3 == 1) {
            zzge.zza(Objects.requireNonNull(objArr3[0]), Objects.requireNonNull(objArr3[1]));
            i3 = 1;
        } else {
            int i5 = i4 - 1;
            char c3 = 65535;
            if (i4 <= 128) {
                byte[] bArr2 = new byte[i4];
                Arrays.fill(bArr2, (byte) -1);
                int i6 = 0;
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = i6 + i6;
                    int i9 = i7 + i7;
                    Object requireNonNull = Objects.requireNonNull(objArr3[i9]);
                    Object requireNonNull2 = Objects.requireNonNull(objArr3[i9 ^ 1]);
                    zzge.zza(requireNonNull, requireNonNull2);
                    int zza2 = zzgf.zza(requireNonNull.hashCode());
                    while (true) {
                        int i10 = zza2 & i5;
                        int i11 = bArr2[i10] & 255;
                        if (i11 != 255) {
                            if (requireNonNull.equals(objArr3[i11])) {
                                int i12 = i11 ^ 1;
                                zzgn zzgnVar = new zzgn(requireNonNull, requireNonNull2, Objects.requireNonNull(objArr3[i12]));
                                objArr3[i12] = requireNonNull2;
                                obj = zzgnVar;
                                break;
                            }
                            zza2 = i10 + 1;
                        } else {
                            bArr2[i10] = (byte) i8;
                            if (i6 < i7) {
                                objArr3[i8] = requireNonNull;
                                objArr3[i8 ^ 1] = requireNonNull2;
                            }
                            i6++;
                        }
                    }
                }
                if (i6 == i3) {
                    bArr = bArr2;
                    c2 = 2;
                    obj = bArr;
                } else {
                    obj = new Object[]{bArr2, Integer.valueOf(i6), obj};
                    c2 = 2;
                }
            } else if (i4 <= 32768) {
                sArr = new short[i4];
                Arrays.fill(sArr, (short) -1);
                int i13 = 0;
                for (int i14 = 0; i14 < i3; i14++) {
                    int i15 = i13 + i13;
                    int i16 = i14 + i14;
                    Object requireNonNull3 = Objects.requireNonNull(objArr3[i16]);
                    Object requireNonNull4 = Objects.requireNonNull(objArr3[i16 ^ 1]);
                    zzge.zza(requireNonNull3, requireNonNull4);
                    int zza3 = zzgf.zza(requireNonNull3.hashCode());
                    while (true) {
                        int i17 = zza3 & i5;
                        char c4 = (char) sArr[i17];
                        if (c4 != 65535) {
                            if (requireNonNull3.equals(objArr3[c4])) {
                                int i18 = c4 ^ 1;
                                zzgn zzgnVar2 = new zzgn(requireNonNull3, requireNonNull4, Objects.requireNonNull(objArr3[i18]));
                                objArr3[i18] = requireNonNull4;
                                obj = zzgnVar2;
                                break;
                            }
                            zza3 = i17 + 1;
                        } else {
                            sArr[i17] = (short) i15;
                            if (i13 < i14) {
                                objArr3[i15] = requireNonNull3;
                                objArr3[i15 ^ 1] = requireNonNull4;
                            }
                            i13++;
                        }
                    }
                }
                if (i13 != i3) {
                    c2 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i13), obj};
                    obj = objArr2;
                }
                bArr = sArr;
                c2 = 2;
                obj = bArr;
            } else {
                sArr = new int[i4];
                Arrays.fill((int[]) sArr, -1);
                int i19 = 0;
                int i20 = 0;
                while (i19 < i3) {
                    int i21 = i20 + i20;
                    int i22 = i19 + i19;
                    Object requireNonNull5 = Objects.requireNonNull(objArr3[i22]);
                    Object requireNonNull6 = Objects.requireNonNull(objArr3[i22 ^ 1]);
                    zzge.zza(requireNonNull5, requireNonNull6);
                    int zza4 = zzgf.zza(requireNonNull5.hashCode());
                    while (true) {
                        int i23 = zza4 & i5;
                        ?? r15 = sArr[i23];
                        if (r15 != c3) {
                            if (requireNonNull5.equals(objArr3[r15])) {
                                int i24 = r15 ^ 1;
                                zzgn zzgnVar3 = new zzgn(requireNonNull5, requireNonNull6, Objects.requireNonNull(objArr3[i24]));
                                objArr3[i24] = requireNonNull6;
                                obj = zzgnVar3;
                                break;
                            }
                            zza4 = i23 + 1;
                            c3 = 65535;
                        } else {
                            sArr[i23] = i21;
                            if (i20 < i19) {
                                objArr3[i21] = requireNonNull5;
                                objArr3[i21 ^ 1] = requireNonNull6;
                            }
                            i20++;
                        }
                    }
                    i19++;
                    c3 = 65535;
                }
                if (i20 != i3) {
                    c2 = 2;
                    objArr2 = new Object[]{sArr, Integer.valueOf(i20), obj};
                    obj = objArr2;
                }
                bArr = sArr;
                c2 = 2;
                obj = bArr;
            }
        }
        if (obj instanceof Object[]) {
            Object[] objArr4 = (Object[]) obj;
            zzgoVar.zzc = (zzgn) objArr4[c2];
            Object obj2 = objArr4[0];
            int intValue = ((Integer) objArr4[1]).intValue();
            objArr3 = Arrays.copyOf(objArr3, intValue + intValue);
            obj = obj2;
            i3 = intValue;
        }
        return new zzgx(obj, objArr3, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a1 A[RETURN] */
    @Override // com.google.android.gms.internal.games_v2.zzgp, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L6
        L3:
            r10 = r0
            goto L9e
        L6:
            int r1 = r9.zzd
            java.lang.Object[] r2 = r9.zzb
            r3 = 1
            if (r1 != r3) goto L22
            r1 = 0
            r1 = r2[r1]
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L3
            r10 = r2[r3]
            java.lang.Object r10 = java.util.Objects.requireNonNull(r10)
            goto L9e
        L22:
            java.lang.Object r1 = r9.zzc
            if (r1 != 0) goto L27
            goto L3
        L27:
            boolean r4 = r1 instanceof byte[]
            r5 = -1
            if (r4 == 0) goto L53
            r4 = r1
            byte[] r4 = (byte[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.android.gms.internal.games_v2.zzgf.zza(r1)
        L3a:
            r1 = r1 & r6
            r5 = r4[r1]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L43
            goto L3
        L43:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L50
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9e
        L50:
            int r1 = r1 + 1
            goto L3a
        L53:
            boolean r4 = r1 instanceof short[]
            if (r4 == 0) goto L7f
            r4 = r1
            short[] r4 = (short[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.android.gms.internal.games_v2.zzgf.zza(r1)
        L65:
            r1 = r1 & r6
            short r5 = r4[r1]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L6f
            goto L3
        L6f:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L7c
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9e
        L7c:
            int r1 = r1 + 1
            goto L65
        L7f:
            int[] r1 = (int[]) r1
            int r4 = r1.length
            int r4 = r4 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.android.gms.internal.games_v2.zzgf.zza(r6)
        L8b:
            r6 = r6 & r4
            r7 = r1[r6]
            if (r7 != r5) goto L92
            goto L3
        L92:
            r8 = r2[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto La2
            r10 = r7 ^ 1
            r10 = r2[r10]
        L9e:
            if (r10 != 0) goto La1
            return r0
        La1:
            return r10
        La2:
            int r6 = r6 + 1
            goto L8b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.games_v2.zzgx.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.games_v2.zzgp, java.util.Map
    /* renamed from: zza */
    public final zzgq entrySet() {
        return new zzgu(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.games_v2.zzgp, java.util.Map
    /* renamed from: zzb */
    public final zzgq keySet() {
        return new zzgv(this, new zzgw(this.zzb, 0, this.zzd));
    }

    @Override // com.google.android.gms.internal.games_v2.zzgp, java.util.Map
    /* renamed from: zzc */
    public final zzgi values() {
        return new zzgw(this.zzb, 1, this.zzd);
    }
}
