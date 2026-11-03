package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitMatrix;
/* loaded from: classes9.dex */
public final class AztecCode {
    private int codeWords;
    private boolean compact;
    private int layers;
    private BitMatrix matrix;
    private int size;

    public boolean isCompact() {
        return this.compact;
    }

    public void setCompact(boolean z) {
        this.compact = z;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i2) {
        this.size = i2;
    }

    public int getLayers() {
        return this.layers;
    }

    public void setLayers(int i2) {
        this.layers = i2;
    }

    public int getCodeWords() {
        return this.codeWords;
    }

    public void setCodeWords(int i2) {
        this.codeWords = i2;
    }

    public BitMatrix getMatrix() {
        return this.matrix;
    }

    public void setMatrix(BitMatrix bitMatrix) {
        this.matrix = bitMatrix;
    }
}
