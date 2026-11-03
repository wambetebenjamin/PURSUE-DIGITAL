package com.google.common.hash;

import com.google.common.base.Preconditions;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class Fingerprint2011 extends AbstractNonStreamingHashFunction {
    static final HashFunction FINGERPRINT_2011 = new Fingerprint2011();
    private static final long K0 = -6505348102511208375L;
    private static final long K1 = -8261664234251669945L;
    private static final long K2 = -4288712594273399085L;
    private static final long K3 = -4132994306676758123L;

    static long hash128to64(long j2, long j3) {
        long j4 = (j3 ^ j2) * K3;
        long j5 = (j2 ^ (j4 ^ (j4 >>> 47))) * K3;
        return (j5 ^ (j5 >>> 47)) * K3;
    }

    private static long shiftMix(long j2) {
        return j2 ^ (j2 >>> 47);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public String toString() {
        return "Hashing.fingerprint2011()";
    }

    Fingerprint2011() {
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i2, i3));
    }

    static long fingerprint(byte[] bArr, int i2, int i3) {
        long fullFingerprint;
        if (i3 <= 32) {
            fullFingerprint = murmurHash64WithSeed(bArr, i2, i3, -1397348546323613475L);
        } else if (i3 <= 64) {
            fullFingerprint = hashLength33To64(bArr, i2, i3);
        } else {
            fullFingerprint = fullFingerprint(bArr, i2, i3);
        }
        long j2 = K0;
        long load64 = i3 >= 8 ? LittleEndianByteArray.load64(bArr, i2) : -6505348102511208375L;
        if (i3 >= 9) {
            j2 = LittleEndianByteArray.load64(bArr, (i2 + i3) - 8);
        }
        long hash128to64 = hash128to64(fullFingerprint + j2, load64);
        return (hash128to64 == 0 || hash128to64 == 1) ? hash128to64 - 2 : hash128to64;
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i2, long j2, long j3, long[] jArr) {
        long load64 = LittleEndianByteArray.load64(bArr, i2);
        long load642 = LittleEndianByteArray.load64(bArr, i2 + 8);
        long load643 = LittleEndianByteArray.load64(bArr, i2 + 16);
        long load644 = LittleEndianByteArray.load64(bArr, i2 + 24);
        long j4 = j2 + load64;
        long j5 = load642 + j4 + load643;
        jArr[0] = j5 + load644;
        jArr[1] = Long.rotateRight(j3 + j4 + load644, 51) + Long.rotateRight(j5, 23) + j4;
    }

    private static long fullFingerprint(byte[] bArr, int i2, int i3) {
        long load64 = LittleEndianByteArray.load64(bArr, i2);
        int i4 = i2 + i3;
        long load642 = LittleEndianByteArray.load64(bArr, i4 - 16) ^ K1;
        long load643 = LittleEndianByteArray.load64(bArr, i4 - 56) ^ K0;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long j2 = i3;
        weakHashLength32WithSeeds(bArr, i4 - 64, j2, load642, jArr);
        weakHashLength32WithSeeds(bArr, i4 - 32, j2 * K1, K0, jArr2);
        long shiftMix = load643 + (shiftMix(jArr[1]) * K1);
        long rotateRight = Long.rotateRight(shiftMix + load64, 39) * K1;
        long rotateRight2 = Long.rotateRight(load642, 33) * K1;
        int i5 = i2;
        int i6 = (i3 - 1) & (-64);
        while (true) {
            long rotateRight3 = Long.rotateRight(rotateRight + rotateRight2 + jArr[0] + LittleEndianByteArray.load64(bArr, i5 + 16), 37) * K1;
            long rotateRight4 = Long.rotateRight(rotateRight2 + jArr[1] + LittleEndianByteArray.load64(bArr, i5 + 48), 42) * K1;
            long j3 = rotateRight3 ^ jArr2[1];
            long j4 = rotateRight4 ^ jArr[0];
            long rotateRight5 = Long.rotateRight(shiftMix ^ jArr2[0], 33);
            weakHashLength32WithSeeds(bArr, i5, jArr[1] * K1, j3 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i5 + 32, jArr2[1] + rotateRight5, j4, jArr2);
            i5 += 64;
            i6 -= 64;
            if (i6 == 0) {
                return hash128to64(hash128to64(jArr[0], jArr2[0]) + (shiftMix(j4) * K1) + j3, hash128to64(jArr[1], jArr2[1]) + rotateRight5);
            }
            rotateRight = rotateRight5;
            shiftMix = j3;
            rotateRight2 = j4;
        }
    }

    private static long hashLength33To64(byte[] bArr, int i2, int i3) {
        long load64 = LittleEndianByteArray.load64(bArr, i2 + 24);
        int i4 = i2 + i3;
        int i5 = i4 - 16;
        long load642 = LittleEndianByteArray.load64(bArr, i2) + ((i3 + LittleEndianByteArray.load64(bArr, i5)) * K0);
        long rotateRight = Long.rotateRight(load642 + load64, 52);
        long rotateRight2 = Long.rotateRight(load642, 37);
        long load643 = load642 + LittleEndianByteArray.load64(bArr, i2 + 8);
        int i6 = i2 + 16;
        long load644 = load643 + LittleEndianByteArray.load64(bArr, i6);
        long j2 = load64 + load644;
        long rotateRight3 = rotateRight + Long.rotateRight(load644, 31) + rotateRight2 + Long.rotateRight(load643, 7);
        long load645 = LittleEndianByteArray.load64(bArr, i6) + LittleEndianByteArray.load64(bArr, i4 - 32);
        long load646 = LittleEndianByteArray.load64(bArr, i4 - 8);
        long rotateRight4 = Long.rotateRight(load645 + load646, 52);
        long rotateRight5 = Long.rotateRight(load645, 37);
        long load647 = load645 + LittleEndianByteArray.load64(bArr, i4 - 24);
        long load648 = load647 + LittleEndianByteArray.load64(bArr, i5);
        return shiftMix((shiftMix(((j2 + rotateRight4 + Long.rotateRight(load648, 31) + rotateRight5 + Long.rotateRight(load647, 7)) * K2) + ((load646 + load648 + rotateRight3) * K0)) * K0) + rotateRight3) * K2;
    }

    static long murmurHash64WithSeed(byte[] bArr, int i2, int i3, long j2) {
        int i4 = i3 & (-8);
        int i5 = i3 & 7;
        long j3 = j2 ^ (i3 * K3);
        for (int i6 = 0; i6 < i4; i6 += 8) {
            j3 = (j3 ^ (shiftMix(LittleEndianByteArray.load64(bArr, i2 + i6) * K3) * K3)) * K3;
        }
        if (i5 != 0) {
            j3 = (LittleEndianByteArray.load64Safely(bArr, i2 + i4, i5) ^ j3) * K3;
        }
        return shiftMix(shiftMix(j3) * K3);
    }
}
