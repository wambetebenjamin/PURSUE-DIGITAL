package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8;
import com.bytedance.apm.battery.config.a;
import com.facebook.imageutils.JfifUtil;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public final class Utf8Safe extends Utf8 {
    private static int computeEncodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 < length) {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 2048) {
                    i3 += encodedLengthGeneral(charSequence, i2);
                    break;
                }
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                break;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i3 + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
            } else {
                i3 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i2) < 65536) {
                        throw new UnpairedSurrogateException(i2, length);
                    }
                    i2++;
                }
            }
            i2++;
        }
        return i3;
    }

    public static String decodeUtf8Array(byte[] bArr, int i2, int i3) {
        if ((i2 | i3 | ((bArr.length - i2) - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i4 = i2 + i3;
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i2 < i4) {
            byte b2 = bArr[i2];
            if (!Utf8.DecodeUtil.isOneByte(b2)) {
                break;
            }
            i2++;
            Utf8.DecodeUtil.handleOneByte(b2, cArr, i5);
            i5++;
        }
        int i6 = i5;
        while (i2 < i4) {
            int i7 = i2 + 1;
            byte b3 = bArr[i2];
            if (Utf8.DecodeUtil.isOneByte(b3)) {
                int i8 = i6 + 1;
                Utf8.DecodeUtil.handleOneByte(b3, cArr, i6);
                while (i7 < i4) {
                    byte b4 = bArr[i7];
                    if (!Utf8.DecodeUtil.isOneByte(b4)) {
                        break;
                    }
                    i7++;
                    Utf8.DecodeUtil.handleOneByte(b4, cArr, i8);
                    i8++;
                }
                i2 = i7;
                i6 = i8;
            } else if (Utf8.DecodeUtil.isTwoBytes(b3)) {
                if (i7 >= i4) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                Utf8.DecodeUtil.handleTwoBytes(b3, bArr[i7], cArr, i6);
                i2 = i7 + 1;
                i6++;
            } else if (Utf8.DecodeUtil.isThreeBytes(b3)) {
                if (i7 >= i4 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int i9 = i7 + 1;
                Utf8.DecodeUtil.handleThreeBytes(b3, bArr[i7], bArr[i9], cArr, i6);
                i2 = i9 + 1;
                i6++;
            } else if (i7 >= i4 - 2) {
                throw new IllegalArgumentException("Invalid UTF-8");
            } else {
                int i10 = i7 + 1;
                byte b5 = bArr[i7];
                int i11 = i10 + 1;
                Utf8.DecodeUtil.handleFourBytes(b3, b5, bArr[i10], bArr[i11], cArr, i6);
                i2 = i11 + 1;
                i6 = i6 + 1 + 1;
            }
        }
        return new String(cArr, 0, i6);
    }

    public static String decodeUtf8Buffer(ByteBuffer byteBuffer, int i2, int i3) {
        if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i4 = i2 + i3;
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i2 < i4) {
            byte b2 = byteBuffer.get(i2);
            if (!Utf8.DecodeUtil.isOneByte(b2)) {
                break;
            }
            i2++;
            Utf8.DecodeUtil.handleOneByte(b2, cArr, i5);
            i5++;
        }
        int i6 = i5;
        while (i2 < i4) {
            int i7 = i2 + 1;
            byte b3 = byteBuffer.get(i2);
            if (Utf8.DecodeUtil.isOneByte(b3)) {
                int i8 = i6 + 1;
                Utf8.DecodeUtil.handleOneByte(b3, cArr, i6);
                while (i7 < i4) {
                    byte b4 = byteBuffer.get(i7);
                    if (!Utf8.DecodeUtil.isOneByte(b4)) {
                        break;
                    }
                    i7++;
                    Utf8.DecodeUtil.handleOneByte(b4, cArr, i8);
                    i8++;
                }
                i2 = i7;
                i6 = i8;
            } else if (Utf8.DecodeUtil.isTwoBytes(b3)) {
                if (i7 >= i4) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                Utf8.DecodeUtil.handleTwoBytes(b3, byteBuffer.get(i7), cArr, i6);
                i2 = i7 + 1;
                i6++;
            } else if (Utf8.DecodeUtil.isThreeBytes(b3)) {
                if (i7 >= i4 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int i9 = i7 + 1;
                Utf8.DecodeUtil.handleThreeBytes(b3, byteBuffer.get(i7), byteBuffer.get(i9), cArr, i6);
                i2 = i9 + 1;
                i6++;
            } else if (i7 >= i4 - 2) {
                throw new IllegalArgumentException("Invalid UTF-8");
            } else {
                int i10 = i7 + 1;
                byte b5 = byteBuffer.get(i7);
                int i11 = i10 + 1;
                Utf8.DecodeUtil.handleFourBytes(b3, b5, byteBuffer.get(i10), byteBuffer.get(i11), cArr, i6);
                i2 = i11 + 1;
                i6 = i6 + 1 + 1;
            }
        }
        return new String(cArr, 0, i6);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence) {
        return computeEncodedLength(charSequence);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer, int i2, int i3) throws IllegalArgumentException {
        if (byteBuffer.hasArray()) {
            return decodeUtf8Array(byteBuffer.array(), byteBuffer.arrayOffset() + i2, i3);
        }
        return decodeUtf8Buffer(byteBuffer, i2, i3);
    }

    private static void encodeUtf8Buffer(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i2 = 0;
        while (i2 < length) {
            try {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i2, (byte) charAt);
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
            }
        }
        if (i2 == length) {
            byteBuffer.position(position + i2);
            return;
        }
        position += i2;
        while (i2 < length) {
            char charAt2 = charSequence.charAt(i2);
            if (charAt2 < 128) {
                byteBuffer.put(position, (byte) charAt2);
            } else if (charAt2 < 2048) {
                int i3 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt2 >>> 6) | JfifUtil.MARKER_SOFn));
                    byteBuffer.put(i3, (byte) ((charAt2 & '?') | 128));
                    position = i3;
                } catch (IndexOutOfBoundsException unused2) {
                    position = i3;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                }
            } else if (charAt2 < 55296 || 57343 < charAt2) {
                int i4 = position + 1;
                byteBuffer.put(position, (byte) ((charAt2 >>> '\f') | 224));
                position = i4 + 1;
                byteBuffer.put(i4, (byte) (((charAt2 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
            } else {
                int i5 = i2 + 1;
                if (i5 != length) {
                    try {
                        char charAt3 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i6 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | a.MAX_TOTAL_LOC_REQUEST_TIME_10_MINS_SECOND));
                                int i7 = i6 + 1;
                                byteBuffer.put(i6, (byte) (((codePoint >>> 12) & 63) | 128));
                                int i8 = i7 + 1;
                                byteBuffer.put(i7, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(i8, (byte) ((codePoint & 63) | 128));
                                position = i8;
                                i2 = i5;
                            } catch (IndexOutOfBoundsException unused3) {
                                position = i6;
                                i2 = i5;
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (position - byteBuffer.position()) + 1)));
                            }
                        } else {
                            i2 = i5;
                        }
                    } catch (IndexOutOfBoundsException unused4) {
                    }
                }
                throw new UnpairedSurrogateException(i2, length);
            }
            i2++;
            position++;
        }
        byteBuffer.position(position);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int encodeUtf8Array(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.flatbuffer.Utf8Safe.encodeUtf8Array(java.lang.CharSequence, byte[], int, int):int");
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int arrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(encodeUtf8Array(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            return;
        }
        encodeUtf8Buffer(charSequence, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i2, int i3) {
            super("Unpaired surrogate at index " + i2 + " of " + i3);
        }
    }
}
