package com.google.zxing.aztec.decoder;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.apm.util.i;
import com.bytedance.crash.NpthBus;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.librarian.c;
import com.bytedance.ttgame.module.dynamic.api.DynamicPageState;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.meituan.robust.Constants;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import java.util.Arrays;
/* loaded from: classes9.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", NpthBus.UUID_SUFIX_3_1_3, "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", e.f7575a, "f", "g", "h", i.TAG, "j", "k", "l", "m", "n", "o", "p", "q", DownloadFileUtils.MODE_READ, "s", "t", "u", BaseSwitches.V, DownloadFileUtils.MODE_WRITE, WebViewConfig.LOCATION_X, WebViewConfig.LOCATION_Y, "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", c.a.DOT, c.a.SEPARATOR, CertificateUtil.DELIMITER, Constants.PACKNAME_END, "<", "=", ">", "?", Constants.ARRAY_TYPE, "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", DynamicPageState.NOT_FOUND, DynamicPageState.URL_EMPTY, DynamicPageState.BUNDLE_NOT_FOUND, "7", "8", "9", ",", c.a.DOT, "CTRL_UL", "CTRL_US"};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int i2, boolean z) {
        return ((z ? 88 : 112) + (i2 << 4)) * i2;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctBits), null, null);
        decoderResult.setNumBits(correctBits.length);
        return decoderResult;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        int i2 = 0;
        while (i2 < length) {
            if (table2 != Table.BINARY) {
                int i3 = table2 == Table.DIGIT ? 4 : 5;
                if (length - i2 < i3) {
                    break;
                }
                int readCode = readCode(zArr, i2, i3);
                i2 += i3;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    table = getTable(character.charAt(5));
                    if (character.charAt(6) != 'L') {
                        Table table3 = table2;
                        table2 = table;
                        table = table3;
                    }
                } else {
                    sb.append(character);
                }
                table2 = table;
            } else if (length - i2 < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, i2, 5);
                i2 += 5;
                if (readCode2 == 0) {
                    if (length - i2 < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, i2, 11) + 31;
                    i2 += 11;
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= readCode2) {
                        break;
                    } else if (length - i2 < 8) {
                        i2 = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, i2, 8));
                        i2 += 8;
                        i4++;
                    }
                }
                table2 = table;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c2) {
        if (c2 != 'B') {
            if (c2 != 'D') {
                if (c2 != 'P') {
                    if (c2 != 'L') {
                        if (c2 == 'M') {
                            return Table.MIXED;
                        }
                        return Table.UPPER;
                    }
                    return Table.LOWER;
                }
                return Table.PUNCT;
            }
            return Table.DIGIT;
        }
        return Table.BINARY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        static {
            int[] iArr = new int[Table.values().length];
            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = iArr;
            try {
                iArr[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static String getCharacter(Table table, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 == 5) {
                            return DIGIT_TABLE[i2];
                        }
                        throw new IllegalStateException("Bad table");
                    }
                    return PUNCT_TABLE[i2];
                }
                return MIXED_TABLE[i2];
            }
            return LOWER_TABLE[i2];
        }
        return UPPER_TABLE[i2];
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i2 = 8;
        if (this.ddata.getNbLayers() <= 2) {
            i2 = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            i2 = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i2 = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / i2;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % i2;
        int[] iArr = new int[length];
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = readCode(zArr, length2, i2);
            i3++;
            length2 += i2;
        }
        try {
            new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
            int i4 = (1 << i2) - 1;
            int i5 = 0;
            for (int i6 = 0; i6 < nbDatablocks; i6++) {
                int i7 = iArr[i6];
                if (i7 == 0 || i7 == i4) {
                    throw FormatException.getFormatInstance();
                }
                if (i7 == 1 || i7 == i4 - 1) {
                    i5++;
                }
            }
            boolean[] zArr2 = new boolean[(nbDatablocks * i2) - i5];
            int i8 = 0;
            for (int i9 = 0; i9 < nbDatablocks; i9++) {
                int i10 = iArr[i9];
                if (i10 == 1 || i10 == i4 - 1) {
                    Arrays.fill(zArr2, i8, (i8 + i2) - 1, i10 > 1);
                    i8 += i2 - 1;
                } else {
                    int i11 = i2 - 1;
                    while (i11 >= 0) {
                        int i12 = i8 + 1;
                        zArr2[i8] = ((1 << i11) & i10) != 0;
                        i11--;
                        i8 = i12;
                    }
                }
            }
            return zArr2;
        } catch (ReedSolomonException e2) {
            throw FormatException.getFormatInstance(e2);
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i2 = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i3 = 2;
        if (isCompact) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = (i7 / 15) + i7;
                iArr[(i5 - i7) - 1] = (i6 - i8) - 1;
                iArr[i5 + i7] = i8 + i6 + 1;
            }
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < nbLayers) {
            int i11 = ((nbLayers - i9) << i3) + (isCompact ? 9 : 12);
            int i12 = i9 << 1;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 << 1;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i10 + i15 + i16] = bitMatrix.get(iArr[i17], iArr[i18]);
                    int i19 = iArr[i18];
                    int i20 = i13 - i16;
                    boolean z = isCompact;
                    zArr[(i11 * 2) + i10 + i15 + i16] = bitMatrix.get(i19, iArr[i20]);
                    int i21 = i13 - i14;
                    zArr[(i11 * 4) + i10 + i15 + i16] = bitMatrix.get(iArr[i20], iArr[i21]);
                    zArr[(i11 * 6) + i10 + i15 + i16] = bitMatrix.get(iArr[i21], iArr[i17]);
                    i16++;
                    nbLayers = nbLayers;
                    isCompact = z;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i10 += i11 << 3;
            i9++;
            i3 = 2;
        }
        return zArr;
    }

    private static int readCode(boolean[] zArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 <<= 1;
            if (zArr[i5]) {
                i4 |= 1;
            }
        }
        return i4;
    }

    private static byte readByte(boolean[] zArr, int i2) {
        int readCode;
        int length = zArr.length - i2;
        if (length >= 8) {
            readCode = readCode(zArr, i2, 8);
        } else {
            readCode = readCode(zArr, i2, length) << (8 - length);
        }
        return (byte) readCode;
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = readByte(zArr, i2 << 3);
        }
        return bArr;
    }
}
