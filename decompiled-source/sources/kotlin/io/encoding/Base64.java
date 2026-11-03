package kotlin.io.encoding;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
/* compiled from: Base64.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u0000 22\u00020\u0001:\u00012B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ%\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0013J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011H\u0002J%\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001bJ\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J0\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J4\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J4\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J \u0010 \u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\"\u0010!\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J4\u0010\"\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J5\u0010#\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b$J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0002J=\u0010&\u001a\u0002H'\"\f\b\u0000\u0010'*\u00060(j\u0002`)2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001e\u001a\u0002H'2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010*J\"\u0010+\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J%\u0010,\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0002\b-J(\u0010.\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010/\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0011H\u0002J \u00101\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u00063"}, d2 = {"Lkotlin/io/encoding/Base64;", "", "isUrlSafe", "", "isMimeScheme", "(ZZ)V", "isMimeScheme$kotlin_stdlib", "()Z", "isUrlSafe$kotlin_stdlib", "bytesToStringImpl", "", "source", "", "bytesToStringImpl$kotlin_stdlib", "charsToBytesImpl", "", "startIndex", "", "endIndex", "charsToBytesImpl$kotlin_stdlib", "checkDestinationBounds", "", "destinationSize", "destinationOffset", "capacityNeeded", "checkSourceBounds", "sourceSize", "checkSourceBounds$kotlin_stdlib", "decode", "decodeImpl", "destination", "decodeIntoByteArray", "decodeSize", "encode", "encodeIntoByteArray", "encodeIntoByteArrayImpl", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeSize", "encodeToAppendable", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "encodeToByteArray", "encodeToByteArrayImpl", "encodeToByteArrayImpl$kotlin_stdlib", "handlePaddingSymbol", "padIndex", "byteStart", "skipIllegalSymbolsIfMime", "Default", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes10.dex */
public class Base64 {
    private static final int bitsPerByte = 8;
    private static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    private static final int mimeGroupsPerLine = 19;
    public static final int mimeLineLength = 76;
    public static final byte padSymbol = 61;
    public static final int symbolsPerGroup = 4;
    private final boolean isMimeScheme;
    private final boolean isUrlSafe;
    public static final Default Default = new Default(null);
    private static final byte[] mimeLineSeparatorSymbols = {Ascii.CR, 10};
    private static final Base64 UrlSafe = new Base64(true, false);
    private static final Base64 Mime = new Base64(false, true);

    public /* synthetic */ Base64(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }

    private Base64(boolean z, boolean z2) {
        this.isUrlSafe = z;
        this.isMimeScheme = z2;
        if (!((z && z2) ? false : true)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final boolean isUrlSafe$kotlin_stdlib() {
        return this.isUrlSafe;
    }

    public final boolean isMimeScheme$kotlin_stdlib() {
        return this.isMimeScheme;
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(Base64 base64, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = bArr.length;
            }
            return base64.encodeToByteArray(bArr, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToByteArray");
    }

    public final byte[] encodeToByteArray(byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        return encodeToByteArrayImpl$kotlin_stdlib(source, i2, i3);
    }

    public static /* synthetic */ int encodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5, Object obj) {
        if (obj == null) {
            int i6 = (i5 & 4) != 0 ? 0 : i2;
            int i7 = (i5 & 8) != 0 ? 0 : i3;
            if ((i5 & 16) != 0) {
                i4 = bArr.length;
            }
            return base64.encodeIntoByteArray(bArr, bArr2, i6, i7, i4);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
    }

    public final int encodeIntoByteArray(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, i2, i3, i4);
    }

    public static /* synthetic */ String encode$default(Base64 base64, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = bArr.length;
            }
            return base64.encode(bArr, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encode");
    }

    public final String encode(byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new String(encodeToByteArrayImpl$kotlin_stdlib(source, i2, i3), Charsets.ISO_8859_1);
    }

    public static /* synthetic */ Appendable encodeToAppendable$default(Base64 base64, byte[] bArr, Appendable appendable, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 4) != 0) {
                i2 = 0;
            }
            if ((i4 & 8) != 0) {
                i3 = bArr.length;
            }
            return base64.encodeToAppendable(bArr, appendable, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeToAppendable");
    }

    public final <A extends Appendable> A encodeToAppendable(byte[] source, A destination, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        destination.append(new String(encodeToByteArrayImpl$kotlin_stdlib(source, i2, i3), Charsets.ISO_8859_1));
        return destination;
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = bArr.length;
            }
            return base64.decode(bArr, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
    }

    public final byte[] decode(byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, i2, i3);
        int decodeSize = decodeSize(source, i2, i3);
        byte[] bArr = new byte[decodeSize];
        if (decodeImpl(source, bArr, 0, i2, i3) == decodeSize) {
            return bArr;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5, Object obj) {
        if (obj == null) {
            int i6 = (i5 & 4) != 0 ? 0 : i2;
            int i7 = (i5 & 8) != 0 ? 0 : i3;
            if ((i5 & 16) != 0) {
                i4 = bArr.length;
            }
            return base64.decodeIntoByteArray(bArr, bArr2, i6, i7, i4);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
    }

    public final int decodeIntoByteArray(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, i3, i4);
        checkDestinationBounds(destination.length, i2, decodeSize(source, i3, i4));
        return decodeImpl(source, destination, i2, i3, i4);
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, CharSequence charSequence, int i2, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                i2 = 0;
            }
            if ((i4 & 4) != 0) {
                i3 = charSequence.length();
            }
            return base64.decode(charSequence, i2, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decode");
    }

    public final byte[] decode(CharSequence source, int i2, int i3) {
        byte[] charsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source instanceof String) {
            checkSourceBounds$kotlin_stdlib(source.length(), i2, i3);
            String substring = ((String) source).substring(i2, i3);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            Charset charset = Charsets.ISO_8859_1;
            Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
            charsToBytesImpl$kotlin_stdlib = substring.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(charsToBytesImpl$kotlin_stdlib, "this as java.lang.String).getBytes(charset)");
        } else {
            charsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, i2, i3);
        }
        return decode$default(this, charsToBytesImpl$kotlin_stdlib, 0, 0, 6, (Object) null);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, CharSequence charSequence, byte[] bArr, int i2, int i3, int i4, int i5, Object obj) {
        if (obj == null) {
            int i6 = (i5 & 4) != 0 ? 0 : i2;
            int i7 = (i5 & 8) != 0 ? 0 : i3;
            if ((i5 & 16) != 0) {
                i4 = charSequence.length();
            }
            return base64.decodeIntoByteArray(charSequence, bArr, i6, i7, i4);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
    }

    public final int decodeIntoByteArray(CharSequence source, byte[] destination, int i2, int i3, int i4) {
        byte[] charsToBytesImpl$kotlin_stdlib;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (source instanceof String) {
            checkSourceBounds$kotlin_stdlib(source.length(), i3, i4);
            String substring = ((String) source).substring(i3, i4);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            Charset charset = Charsets.ISO_8859_1;
            Intrinsics.checkNotNull(substring, "null cannot be cast to non-null type java.lang.String");
            charsToBytesImpl$kotlin_stdlib = substring.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(charsToBytesImpl$kotlin_stdlib, "this as java.lang.String).getBytes(charset)");
        } else {
            charsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, i3, i4);
        }
        return decodeIntoByteArray$default(this, charsToBytesImpl$kotlin_stdlib, destination, i2, 0, 0, 24, (Object) null);
    }

    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length, i2, i3);
        byte[] bArr = new byte[encodeSize(i3 - i2)];
        encodeIntoByteArrayImpl$kotlin_stdlib(source, bArr, 0, i2, i3);
        return bArr;
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(byte[] source, byte[] destination, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        checkSourceBounds$kotlin_stdlib(source.length, i3, i4);
        checkDestinationBounds(destination.length, i2, encodeSize(i4 - i3));
        byte[] access$getBase64UrlEncodeMap$p = this.isUrlSafe ? Base64Kt.access$getBase64UrlEncodeMap$p() : Base64Kt.access$getBase64EncodeMap$p();
        int i5 = this.isMimeScheme ? 19 : Integer.MAX_VALUE;
        int i6 = i2;
        while (true) {
            if (i3 + 2 >= i4) {
                break;
            }
            int min = Math.min((i4 - i3) / 3, i5);
            int i7 = 0;
            while (i7 < min) {
                int i8 = i3 + 1;
                int i9 = i8 + 1;
                int i10 = ((source[i3] & 255) << 16) | ((source[i8] & 255) << 8) | (source[i9] & 255);
                int i11 = i6 + 1;
                destination[i6] = access$getBase64UrlEncodeMap$p[i10 >>> 18];
                int i12 = i11 + 1;
                destination[i11] = access$getBase64UrlEncodeMap$p[(i10 >>> 12) & 63];
                int i13 = i12 + 1;
                destination[i12] = access$getBase64UrlEncodeMap$p[(i10 >>> 6) & 63];
                i6 = i13 + 1;
                destination[i13] = access$getBase64UrlEncodeMap$p[i10 & 63];
                i7++;
                i3 = i9 + 1;
            }
            if (min == i5 && i3 != i4) {
                int i14 = i6 + 1;
                byte[] bArr = mimeLineSeparatorSymbols;
                destination[i6] = bArr[0];
                i6 = i14 + 1;
                destination[i14] = bArr[1];
            }
        }
        int i15 = i4 - i3;
        if (i15 == 1) {
            int i16 = i3 + 1;
            int i17 = (source[i3] & 255) << 4;
            int i18 = i6 + 1;
            destination[i6] = access$getBase64UrlEncodeMap$p[i17 >>> 6];
            int i19 = i18 + 1;
            destination[i18] = access$getBase64UrlEncodeMap$p[i17 & 63];
            int i20 = i19 + 1;
            destination[i19] = padSymbol;
            i6 = i20 + 1;
            destination[i20] = padSymbol;
            i3 = i16;
        } else if (i15 == 2) {
            int i21 = i3 + 1;
            int i22 = i21 + 1;
            int i23 = ((source[i21] & 255) << 2) | ((source[i3] & 255) << 10);
            int i24 = i6 + 1;
            destination[i6] = access$getBase64UrlEncodeMap$p[i23 >>> 12];
            int i25 = i24 + 1;
            destination[i24] = access$getBase64UrlEncodeMap$p[(i23 >>> 6) & 63];
            int i26 = i25 + 1;
            destination[i25] = access$getBase64UrlEncodeMap$p[i23 & 63];
            i6 = i26 + 1;
            destination[i26] = padSymbol;
            i3 = i22;
        }
        if (i3 == i4) {
            return i6 - i2;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final int encodeSize(int i2) {
        int i3 = ((i2 + 3) - 1) / 3;
        int i4 = (i3 * 4) + ((this.isMimeScheme ? (i3 - 1) / 19 : 0) * 2);
        if (i4 >= 0) {
            return i4;
        }
        throw new IllegalArgumentException("Input is too big");
    }

    private final int decodeImpl(byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        int[] access$getBase64UrlDecodeMap$p = this.isUrlSafe ? Base64Kt.access$getBase64UrlDecodeMap$p() : Base64Kt.access$getBase64DecodeMap$p();
        int i5 = -8;
        int i6 = i2;
        int i7 = 0;
        int i8 = -8;
        int i9 = i3;
        while (true) {
            if (i9 >= i4) {
                break;
            }
            if (i8 == i5 && i9 + 3 < i4) {
                int i10 = i9 + 1;
                int i11 = access$getBase64UrlDecodeMap$p[bArr[i9] & 255];
                int i12 = i10 + 1;
                int i13 = access$getBase64UrlDecodeMap$p[bArr[i10] & 255];
                int i14 = i12 + 1;
                int i15 = access$getBase64UrlDecodeMap$p[bArr[i12] & 255];
                int i16 = i14 + 1;
                int i17 = (i11 << 18) | (i13 << 12) | (i15 << 6) | access$getBase64UrlDecodeMap$p[bArr[i14] & 255];
                if (i17 >= 0) {
                    int i18 = i6 + 1;
                    bArr2[i6] = (byte) (i17 >> 16);
                    int i19 = i18 + 1;
                    bArr2[i18] = (byte) (i17 >> 8);
                    bArr2[i19] = (byte) i17;
                    i6 = i19 + 1;
                    i9 = i16;
                    i5 = -8;
                } else {
                    i9 = i16 - 4;
                }
            }
            int i20 = bArr[i9] & 255;
            int i21 = access$getBase64UrlDecodeMap$p[i20];
            if (i21 >= 0) {
                i9++;
                i7 = (i7 << 6) | i21;
                i8 += 6;
                if (i8 >= 0) {
                    bArr2[i6] = (byte) (i7 >>> i8);
                    i7 &= (1 << i8) - 1;
                    i8 -= 8;
                    i6++;
                }
            } else if (i21 == -2) {
                i9 = handlePaddingSymbol(bArr, i9, i4, i8);
                break;
            } else if (!this.isMimeScheme) {
                StringBuilder append = new StringBuilder().append("Invalid symbol '").append((char) i20).append("'(");
                String num = Integer.toString(i20, CharsKt.checkRadix(8));
                Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                throw new IllegalArgumentException(append.append(num).append(") at index ").append(i9).toString());
            } else {
                i9++;
            }
            i5 = -8;
        }
        if (i8 == -2) {
            throw new IllegalArgumentException("The last unit of input does not have enough bits");
        }
        int skipIllegalSymbolsIfMime = skipIllegalSymbolsIfMime(bArr, i9, i4);
        if (skipIllegalSymbolsIfMime >= i4) {
            return i6 - i2;
        }
        int i22 = bArr[skipIllegalSymbolsIfMime] & 255;
        StringBuilder append2 = new StringBuilder().append("Symbol '").append((char) i22).append("'(");
        String num2 = Integer.toString(i22, CharsKt.checkRadix(8));
        Intrinsics.checkNotNullExpressionValue(num2, "toString(this, checkRadix(radix))");
        throw new IllegalArgumentException(append2.append(num2).append(") at index ").append(skipIllegalSymbolsIfMime - 1).append(" is prohibited after the pad character").toString());
    }

    private final int decodeSize(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 == 0) {
            return 0;
        }
        if (i4 == 1) {
            throw new IllegalArgumentException("Input should have at list 2 symbols for Base64 decoding, startIndex: " + i2 + ", endIndex: " + i3);
        }
        if (this.isMimeScheme) {
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                int i5 = Base64Kt.access$getBase64DecodeMap$p()[bArr[i2] & 255];
                if (i5 < 0) {
                    if (i5 == -2) {
                        i4 -= i3 - i2;
                        break;
                    }
                    i4--;
                }
                i2++;
            }
        } else if (bArr[i3 - 1] == 61) {
            i4--;
            if (bArr[i3 - 2] == 61) {
                i4--;
            }
        }
        return (int) ((i4 * 6) / 8);
    }

    public final byte[] charsToBytesImpl$kotlin_stdlib(CharSequence source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        checkSourceBounds$kotlin_stdlib(source.length(), i2, i3);
        byte[] bArr = new byte[i3 - i2];
        int i4 = 0;
        while (i2 < i3) {
            char charAt = source.charAt(i2);
            if (charAt <= 255) {
                bArr[i4] = (byte) charAt;
                i4++;
            } else {
                bArr[i4] = 63;
                i4++;
            }
            i2++;
        }
        return bArr;
    }

    public final String bytesToStringImpl$kotlin_stdlib(byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        StringBuilder sb = new StringBuilder(source.length);
        for (byte b2 : source) {
            sb.append((char) b2);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "stringBuilder.toString()");
        return sb2;
    }

    private final int handlePaddingSymbol(byte[] bArr, int i2, int i3, int i4) {
        if (i4 != -8) {
            if (i4 != -6) {
                if (i4 == -4) {
                    i2 = skipIllegalSymbolsIfMime(bArr, i2 + 1, i3);
                    if (i2 == i3 || bArr[i2] != 61) {
                        throw new IllegalArgumentException("Missing one pad character at index " + i2);
                    }
                } else if (i4 != -2) {
                    throw new IllegalStateException("Unreachable".toString());
                }
            }
            return i2 + 1;
        }
        throw new IllegalArgumentException("Redundant pad character at index " + i2);
    }

    private final int skipIllegalSymbolsIfMime(byte[] bArr, int i2, int i3) {
        if (this.isMimeScheme) {
            while (i2 < i3) {
                if (Base64Kt.access$getBase64DecodeMap$p()[bArr[i2] & 255] != -1) {
                    return i2;
                }
                i2++;
            }
            return i2;
        }
        return i2;
    }

    public final void checkSourceBounds$kotlin_stdlib(int i2, int i3, int i4) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(i3, i4, i2);
    }

    private final void checkDestinationBounds(int i2, int i3, int i4) {
        if (i3 < 0 || i3 > i2) {
            throw new IndexOutOfBoundsException("destination offset: " + i3 + ", destination size: " + i2);
        }
        int i5 = i3 + i4;
        if (i5 < 0 || i5 > i2) {
            throw new IndexOutOfBoundsException("The destination array does not have enough capacity, destination offset: " + i3 + ", destination size: " + i2 + ", capacity needed: " + i4);
        }
    }

    /* compiled from: Base64.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", "()V", "Mime", "getMime", "()Lkotlin/io/encoding/Base64;", "UrlSafe", "getUrlSafe", "bitsPerByte", "", "bitsPerSymbol", "bytesPerGroup", "mimeGroupsPerLine", "mimeLineLength", "mimeLineSeparatorSymbols", "", "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "padSymbol", "", "symbolsPerGroup", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Default extends Base64 {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Default() {
            super(false, false, null);
        }

        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
            return Base64.mimeLineSeparatorSymbols;
        }

        public final Base64 getUrlSafe() {
            return Base64.UrlSafe;
        }

        public final Base64 getMime() {
            return Base64.Mime;
        }
    }
}
