package com.google.zxing.qrcode.decoder;

import com.facebook.imageutils.JfifUtil;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes9.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode forBits;
        Mode mode;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        boolean z = true;
        ArrayList arrayList = new ArrayList(1);
        boolean z2 = false;
        int i2 = -1;
        int i3 = -1;
        CharacterSetECI characterSetECI = null;
        while (true) {
            try {
                if (bitSource.available() < 4) {
                    forBits = Mode.TERMINATOR;
                } else {
                    forBits = Mode.forBits(bitSource.readBits(4));
                }
                Mode mode2 = forBits;
                switch (mode2) {
                    case TERMINATOR:
                        mode = mode2;
                        break;
                    case FNC1_FIRST_POSITION:
                    case FNC1_SECOND_POSITION:
                        z2 = z;
                        mode = mode2;
                        break;
                    case STRUCTURED_APPEND:
                        if (bitSource.available() < 16) {
                            throw FormatException.getFormatInstance();
                        }
                        int readBits = bitSource.readBits(8);
                        i3 = bitSource.readBits(8);
                        i2 = readBits;
                        mode = mode2;
                        break;
                    case ECI:
                        characterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                        if (characterSetECI != null) {
                            mode = mode2;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case HANZI:
                        int readBits2 = bitSource.readBits(4);
                        int readBits3 = bitSource.readBits(mode2.getCharacterCountBits(version));
                        if (readBits2 == z) {
                            decodeHanziSegment(bitSource, sb, readBits3);
                        }
                        mode = mode2;
                        break;
                    default:
                        int readBits4 = bitSource.readBits(mode2.getCharacterCountBits(version));
                        int i4 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[mode2.ordinal()];
                        if (i4 == z) {
                            mode = mode2;
                            decodeNumericSegment(bitSource, sb, readBits4);
                            break;
                        } else if (i4 == 2) {
                            mode = mode2;
                            decodeAlphanumericSegment(bitSource, sb, readBits4, z2);
                            break;
                        } else if (i4 == 3) {
                            mode = mode2;
                            decodeByteSegment(bitSource, sb, readBits4, characterSetECI, arrayList, map);
                            break;
                        } else if (i4 == 4) {
                            decodeKanjiSegment(bitSource, sb, readBits4);
                            mode = mode2;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                }
                if (mode == Mode.TERMINATOR) {
                    return new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i2, i3);
                }
                z = true;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        if (i2 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i2 * 2];
        int i3 = 0;
        while (i2 > 0) {
            int readBits = bitSource.readBits(13);
            int i4 = (readBits % 96) | ((readBits / 96) << 8);
            int i5 = i4 + (i4 < 959 ? 41377 : 42657);
            bArr[i3] = (byte) (i5 >> 8);
            bArr[i3 + 1] = (byte) i5;
            i3 += 2;
            i2--;
        }
        try {
            sb.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        if (i2 * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i2 * 2];
        int i3 = 0;
        while (i2 > 0) {
            int readBits = bitSource.readBits(13);
            int i4 = (readBits % JfifUtil.MARKER_SOFn) | ((readBits / JfifUtil.MARKER_SOFn) << 8);
            int i5 = i4 + (i4 < 7936 ? 33088 : 49472);
            bArr[i3] = (byte) (i5 >> 8);
            bArr[i3 + 1] = (byte) i5;
            i3 += 2;
            i2--;
        }
        try {
            sb.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuilder sb, int i2, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String name;
        if ((i2 << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) bitSource.readBits(8);
        }
        if (characterSetECI == null) {
            name = StringUtils.guessEncoding(bArr, map);
        } else {
            name = characterSetECI.name();
        }
        try {
            sb.append(new String(bArr, name));
            collection.add(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static char toAlphaNumericChar(int i2) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i2 >= cArr.length) {
            throw FormatException.getFormatInstance();
        }
        return cArr[i2];
    }

    private static void decodeAlphanumericSegment(BitSource bitSource, StringBuilder sb, int i2, boolean z) throws FormatException {
        while (i2 > 1) {
            if (bitSource.available() < 11) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(11);
            sb.append(toAlphaNumericChar(readBits / 45));
            sb.append(toAlphaNumericChar(readBits % 45));
            i2 -= 2;
        }
        if (i2 == 1) {
            if (bitSource.available() < 6) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bitSource.readBits(6)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i3 = length + 1;
                        if (sb.charAt(i3) == '%') {
                            sb.deleteCharAt(i3);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i2) throws FormatException {
        while (i2 >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int readBits = bitSource.readBits(10);
            if (readBits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits / 100));
            sb.append(toAlphaNumericChar((readBits / 10) % 10));
            sb.append(toAlphaNumericChar(readBits % 10));
            i2 -= 3;
        }
        if (i2 == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int readBits2 = bitSource.readBits(7);
            if (readBits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits2 / 10));
            sb.append(toAlphaNumericChar(readBits2 % 10));
        } else if (i2 == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int readBits3 = bitSource.readBits(4);
            if (readBits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(readBits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int readBits = bitSource.readBits(8);
        if ((readBits & 128) == 0) {
            return readBits & 127;
        }
        if ((readBits & JfifUtil.MARKER_SOFn) == 128) {
            return bitSource.readBits(8) | ((readBits & 63) << 8);
        }
        if ((readBits & 224) == 192) {
            return bitSource.readBits(16) | ((readBits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }
}
