package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
/* loaded from: classes9.dex */
final class DecodedNumeric extends DecodedObject {
    static final int FNC1 = 10;
    private final int firstDigit;
    private final int secondDigit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedNumeric(int i2, int i3, int i4) throws FormatException {
        super(i2);
        if (i3 < 0 || i3 > 10 || i4 < 0 || i4 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.firstDigit = i3;
        this.secondDigit = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFirstDigit() {
        return this.firstDigit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSecondDigit() {
        return this.secondDigit;
    }

    int getValue() {
        return (this.firstDigit * 10) + this.secondDigit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFirstDigitFNC1() {
        return this.firstDigit == 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSecondDigitFNC1() {
        return this.secondDigit == 10;
    }

    boolean isAnyFNC1() {
        return this.firstDigit == 10 || this.secondDigit == 10;
    }
}
