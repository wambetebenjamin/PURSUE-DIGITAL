package com.google.common.hash;

import com.google.common.base.Preconditions;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();
    private static final long K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    private static long hashLength16(long j2, long j3, long j4) {
        long j5 = (j2 ^ j3) * j4;
        long j6 = ((j5 ^ (j5 >>> 47)) ^ j3) * j4;
        return (j6 ^ (j6 >>> 47)) * j4;
    }

    private static long shiftMix(long j2) {
        return j2 ^ (j2 >>> 47);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    FarmHashFingerprint64() {
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i2, i3));
    }

    static long fingerprint(byte[] bArr, int i2, int i3) {
        if (i3 <= 32) {
            if (i3 <= 16) {
                return hashLength0to16(bArr, i2, i3);
            }
            return hashLength17to32(bArr, i2, i3);
        } else if (i3 <= 64) {
            return hashLength33To64(bArr, i2, i3);
        } else {
            return hashLength65Plus(bArr, i2, i3);
        }
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i2, long j2, long j3, long[] jArr) {
        long load64 = LittleEndianByteArray.load64(bArr, i2);
        long load642 = LittleEndianByteArray.load64(bArr, i2 + 8);
        long load643 = LittleEndianByteArray.load64(bArr, i2 + 16);
        long load644 = LittleEndianByteArray.load64(bArr, i2 + 24);
        long j4 = j2 + load64;
        long j5 = load642 + j4 + load643;
        jArr[0] = j5 + load644;
        jArr[1] = Long.rotateRight(j3 + j4 + load644, 21) + Long.rotateRight(j5, 44) + j4;
    }

    private static long hashLength0to16(byte[] bArr, int i2, int i3) {
        if (i3 >= 8) {
            long j2 = (i3 * 2) - 7286425919675154353L;
            long load64 = LittleEndianByteArray.load64(bArr, i2) - 7286425919675154353L;
            long load642 = LittleEndianByteArray.load64(bArr, (i2 + i3) - 8);
            return hashLength16((Long.rotateRight(load642, 37) * j2) + load64, (Long.rotateRight(load64, 25) + load642) * j2, j2);
        } else if (i3 >= 4) {
            return hashLength16(i3 + ((LittleEndianByteArray.load32(bArr, i2) & 4294967295L) << 3), LittleEndianByteArray.load32(bArr, (i2 + i3) - 4) & 4294967295L, (i3 * 2) - 7286425919675154353L);
        } else if (i3 > 0) {
            return shiftMix((((bArr[i2] & 255) + ((bArr[(i3 >> 1) + i2] & 255) << 8)) * (-7286425919675154353L)) ^ ((i3 + ((bArr[i2 + (i3 - 1)] & 255) << 2)) * (-4348849565147123417L))) * (-7286425919675154353L);
        } else {
            return -7286425919675154353L;
        }
    }

    private static long hashLength17to32(byte[] bArr, int i2, int i3) {
        long j2 = (i3 * 2) - 7286425919675154353L;
        long load64 = LittleEndianByteArray.load64(bArr, i2) * (-5435081209227447693L);
        long load642 = LittleEndianByteArray.load64(bArr, i2 + 8);
        int i4 = i2 + i3;
        long load643 = LittleEndianByteArray.load64(bArr, i4 - 8) * j2;
        return hashLength16((LittleEndianByteArray.load64(bArr, i4 - 16) * (-7286425919675154353L)) + Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30), load64 + Long.rotateRight(load642 - 7286425919675154353L, 18) + load643, j2);
    }

    private static long hashLength33To64(byte[] bArr, int i2, int i3) {
        long j2 = (i3 * 2) - 7286425919675154353L;
        long load64 = LittleEndianByteArray.load64(bArr, i2) * (-7286425919675154353L);
        long load642 = LittleEndianByteArray.load64(bArr, i2 + 8);
        int i4 = i2 + i3;
        long load643 = LittleEndianByteArray.load64(bArr, i4 - 8) * j2;
        long rotateRight = Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30) + (LittleEndianByteArray.load64(bArr, i4 - 16) * (-7286425919675154353L));
        long hashLength16 = hashLength16(rotateRight, load643 + Long.rotateRight(load642 - 7286425919675154353L, 18) + load64, j2);
        long load644 = LittleEndianByteArray.load64(bArr, i2 + 16) * j2;
        long load645 = LittleEndianByteArray.load64(bArr, i2 + 24);
        long load646 = (rotateRight + LittleEndianByteArray.load64(bArr, i4 - 32)) * j2;
        return hashLength16(((hashLength16 + LittleEndianByteArray.load64(bArr, i4 - 24)) * j2) + Long.rotateRight(load644 + load645, 43) + Long.rotateRight(load646, 30), load644 + Long.rotateRight(load645 + load64, 18) + load646, j2);
    }

    private static long hashLength65Plus(byte[] bArr, int i2, int i3) {
        long j2 = 81;
        long j3 = (j2 * (-5435081209227447693L)) + 113;
        long shiftMix = shiftMix((j3 * (-7286425919675154353L)) + 113) * (-7286425919675154353L);
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long load64 = (j2 * (-7286425919675154353L)) + LittleEndianByteArray.load64(bArr, i2);
        int i4 = i3 - 1;
        int i5 = i2 + ((i4 / 64) * 64);
        int i6 = i4 & 63;
        int i7 = (i5 + i6) - 63;
        int i8 = i2;
        while (true) {
            long rotateRight = (Long.rotateRight(((load64 + j3) + jArr[0]) + LittleEndianByteArray.load64(bArr, i8 + 8), 37) * (-5435081209227447693L)) ^ jArr2[1];
            long rotateRight2 = (Long.rotateRight(j3 + jArr[1] + LittleEndianByteArray.load64(bArr, i8 + 48), 42) * (-5435081209227447693L)) + jArr[0] + LittleEndianByteArray.load64(bArr, i8 + 40);
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * (-5435081209227447693L);
            weakHashLength32WithSeeds(bArr, i8, jArr[1] * (-5435081209227447693L), rotateRight + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i8 + 32, rotateRight3 + jArr2[1], rotateRight2 + LittleEndianByteArray.load64(bArr, i8 + 16), jArr2);
            int i9 = i8 + 64;
            if (i9 == i5) {
                long j4 = (-5435081209227447693L) + ((rotateRight & 255) << 1);
                jArr2[0] = jArr2[0] + i6;
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + rotateRight2) + jArr[0]) + LittleEndianByteArray.load64(bArr, i7 + 8), 37) * j4) ^ (jArr2[1] * 9);
                long rotateRight5 = (Long.rotateRight(rotateRight2 + jArr[1] + LittleEndianByteArray.load64(bArr, i7 + 48), 42) * j4) + (jArr[0] * 9) + LittleEndianByteArray.load64(bArr, i7 + 40);
                long rotateRight6 = Long.rotateRight(rotateRight + jArr2[0], 33) * j4;
                weakHashLength32WithSeeds(bArr, i7, jArr[1] * j4, rotateRight4 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i7 + 32, rotateRight6 + jArr2[1], rotateRight5 + LittleEndianByteArray.load64(bArr, i7 + 16), jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j4) + (shiftMix(rotateRight5) * (-4348849565147123417L)) + rotateRight4, hashLength16(jArr[1], jArr2[1], j4) + rotateRight6, j4);
            }
            i8 = i9;
            shiftMix = rotateRight;
            j3 = rotateRight2;
            load64 = rotateRight3;
        }
    }
}
