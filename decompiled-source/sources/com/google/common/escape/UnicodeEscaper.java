package com.google.common.escape;

import com.google.common.base.Preconditions;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public abstract char[] escape(int i2);

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    protected int nextEscapeIndex(CharSequence charSequence, int i2, int i3) {
        while (i2 < i3) {
            int codePointAt = codePointAt(charSequence, i2, i3);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            i2 += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String escapeSlow(String str, int i2) {
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int codePointAt = codePointAt(str, i2, length);
            if (codePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escape = escape(codePointAt);
            int i5 = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i2;
            if (escape != null) {
                int i6 = i2 - i3;
                int i7 = i4 + i6;
                int length2 = escape.length + i7;
                if (charBufferFromThreadLocal.length < length2) {
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i4, length2 + (length - i2) + 32);
                }
                if (i6 > 0) {
                    str.getChars(i3, i2, charBufferFromThreadLocal, i4);
                    i4 = i7;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, i4, escape.length);
                    i4 += escape.length;
                }
                i3 = i5;
            }
            i2 = nextEscapeIndex(str, i5, length);
        }
        int i8 = length - i3;
        if (i8 > 0) {
            int i9 = i8 + i4;
            if (charBufferFromThreadLocal.length < i9) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i4, i9);
            }
            str.getChars(i3, length, charBufferFromThreadLocal, i4);
            i4 = i9;
        }
        return new String(charBufferFromThreadLocal, 0, i4);
    }

    protected static int codePointAt(CharSequence charSequence, int i2, int i3) {
        Preconditions.checkNotNull(charSequence);
        if (i2 < i3) {
            int i4 = i2 + 1;
            char charAt = charSequence.charAt(i2);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                String valueOf = String.valueOf(charSequence);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 88).append("Unexpected low surrogate character '").append(charAt).append("' with value ").append((int) charAt).append(" at index ").append(i4 - 1).append(" in '").append(valueOf).append("'").toString());
            } else if (i4 == i3) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i4);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                String valueOf2 = String.valueOf(charSequence);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + 89).append("Expected low surrogate but got char '").append(charAt2).append("' with value ").append((int) charAt2).append(" at index ").append(i4).append(" in '").append(valueOf2).append("'").toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i2, int i3) {
        if (i3 < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr2 = new char[i3];
        if (i2 > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i2);
        }
        return cArr2;
    }
}
