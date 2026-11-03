package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.nio.charset.StandardCharsets;
/* loaded from: classes9.dex */
final class EncoderContext {
    private final StringBuilder codewords;
    private Dimension maxSize;
    private Dimension minSize;
    private final String msg;
    private int newEncoding;
    int pos;
    private SymbolShapeHint shape;
    private int skipAtEnd;
    private SymbolInfo symbolInfo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EncoderContext(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = (char) (bytes[i2] & 255);
            if (c2 == '?' && str.charAt(i2) != '?') {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
            sb.append(c2);
        }
        this.msg = sb.toString();
        this.shape = SymbolShapeHint.FORCE_NONE;
        this.codewords = new StringBuilder(str.length());
        this.newEncoding = -1;
    }

    public void setSymbolShape(SymbolShapeHint symbolShapeHint) {
        this.shape = symbolShapeHint;
    }

    public void setSizeConstraints(Dimension dimension, Dimension dimension2) {
        this.minSize = dimension;
        this.maxSize = dimension2;
    }

    public String getMessage() {
        return this.msg;
    }

    public void setSkipAtEnd(int i2) {
        this.skipAtEnd = i2;
    }

    public char getCurrentChar() {
        return this.msg.charAt(this.pos);
    }

    public char getCurrent() {
        return this.msg.charAt(this.pos);
    }

    public StringBuilder getCodewords() {
        return this.codewords;
    }

    public void writeCodewords(String str) {
        this.codewords.append(str);
    }

    public void writeCodeword(char c2) {
        this.codewords.append(c2);
    }

    public int getCodewordCount() {
        return this.codewords.length();
    }

    public int getNewEncoding() {
        return this.newEncoding;
    }

    public void signalEncoderChange(int i2) {
        this.newEncoding = i2;
    }

    public void resetEncoderSignal() {
        this.newEncoding = -1;
    }

    public boolean hasMoreCharacters() {
        return this.pos < getTotalMessageCharCount();
    }

    private int getTotalMessageCharCount() {
        return this.msg.length() - this.skipAtEnd;
    }

    public int getRemainingCharacters() {
        return getTotalMessageCharCount() - this.pos;
    }

    public SymbolInfo getSymbolInfo() {
        return this.symbolInfo;
    }

    public void updateSymbolInfo() {
        updateSymbolInfo(getCodewordCount());
    }

    public void updateSymbolInfo(int i2) {
        SymbolInfo symbolInfo = this.symbolInfo;
        if (symbolInfo == null || i2 > symbolInfo.getDataCapacity()) {
            this.symbolInfo = SymbolInfo.lookup(i2, this.shape, this.minSize, this.maxSize, true);
        }
    }

    public void resetSymbolInfo() {
        this.symbolInfo = null;
    }
}
