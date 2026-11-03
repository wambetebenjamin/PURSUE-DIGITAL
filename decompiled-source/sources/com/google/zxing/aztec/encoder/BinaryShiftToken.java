package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
/* loaded from: classes9.dex */
final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryShiftToken(Token token, int i2, int i3) {
        super(token);
        this.binaryShiftStart = (short) i2;
        this.binaryShiftByteCount = (short) i3;
    }

    @Override // com.google.zxing.aztec.encoder.Token
    public void appendTo(BitArray bitArray, byte[] bArr) {
        int i2 = 0;
        while (true) {
            short s = this.binaryShiftByteCount;
            if (i2 >= s) {
                return;
            }
            if (i2 == 0 || (i2 == 31 && s <= 62)) {
                bitArray.appendBits(31, 5);
                short s2 = this.binaryShiftByteCount;
                if (s2 > 62) {
                    bitArray.appendBits(s2 - 31, 16);
                } else if (i2 == 0) {
                    bitArray.appendBits(Math.min((int) s2, 31), 5);
                } else {
                    bitArray.appendBits(s2 - 31, 5);
                }
            }
            bitArray.appendBits(bArr[this.binaryShiftStart + i2], 8);
            i2++;
        }
    }

    public String toString() {
        return "<" + ((int) this.binaryShiftStart) + "::" + ((this.binaryShiftStart + this.binaryShiftByteCount) - 1) + '>';
    }
}
