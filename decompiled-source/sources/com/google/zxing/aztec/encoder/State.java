package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.LinkedList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class State {
    static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token, int i2, int i3, int i4) {
        this.token = token;
        this.mode = i2;
        this.binaryShiftByteCount = i3;
        this.bitCount = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMode() {
        return this.mode;
    }

    Token getToken() {
        return this.token;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBinaryShiftByteCount() {
        return this.binaryShiftByteCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBitCount() {
        return this.bitCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State latchAndAppend(int i2, int i3) {
        int i4 = this.bitCount;
        Token token = this.token;
        if (i2 != this.mode) {
            int i5 = HighLevelEncoder.LATCH_TABLE[this.mode][i2];
            int i6 = 65535 & i5;
            int i7 = i5 >> 16;
            token = token.add(i6, i7);
            i4 += i7;
        }
        int i8 = i2 == 2 ? 4 : 5;
        return new State(token.add(i3, i8), i2, 0, i4 + i8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State shiftAndAppend(int i2, int i3) {
        Token token = this.token;
        int i4 = this.mode == 2 ? 4 : 5;
        return new State(token.add(HighLevelEncoder.SHIFT_TABLE[this.mode][i2], i4).add(i3, 5), this.mode, 0, this.bitCount + i4 + 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State addBinaryShiftChar(int i2) {
        Token token = this.token;
        int i3 = this.mode;
        int i4 = this.bitCount;
        if (i3 == 4 || i3 == 2) {
            int i5 = HighLevelEncoder.LATCH_TABLE[i3][0];
            int i6 = 65535 & i5;
            int i7 = i5 >> 16;
            token = token.add(i6, i7);
            i4 += i7;
            i3 = 0;
        }
        int i8 = this.binaryShiftByteCount;
        State state = new State(token, i3, this.binaryShiftByteCount + 1, i4 + ((i8 == 0 || i8 == 31) ? 18 : i8 == 62 ? 9 : 8));
        return state.binaryShiftByteCount == 2078 ? state.endBinaryShift(i2 + 1) : state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State endBinaryShift(int i2) {
        int i3 = this.binaryShiftByteCount;
        return i3 == 0 ? this : new State(this.token.addBinaryShift(i2 - i3, i3), this.mode, 0, this.bitCount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBetterThanOrEqualTo(State state) {
        int i2;
        int i3 = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][state.mode] >> 16);
        int i4 = state.binaryShiftByteCount;
        if (i4 > 0 && ((i2 = this.binaryShiftByteCount) == 0 || i2 > i4)) {
            i3 += 10;
        }
        return i3 <= state.bitCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BitArray toBitArray(byte[] bArr) {
        LinkedList<Token> linkedList = new LinkedList();
        for (Token token = endBinaryShift(bArr.length).token; token != null; token = token.getPrevious()) {
            linkedList.addFirst(token);
        }
        BitArray bitArray = new BitArray();
        for (Token token2 : linkedList) {
            token2.appendTo(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount));
    }
}
