package com.facebook.imageformat;

import com.facebook.common.internal.Ints;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.ImageFormat;
import com.google.common.base.Ascii;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class DefaultImageFormatChecker implements ImageFormat.FormatChecker {
    private static final byte[] BMP_HEADER;
    private static final int BMP_HEADER_LENGTH;
    private static final byte[] DNG_HEADER_II;
    private static final byte[] DNG_HEADER_MM;
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final byte[] GIF_HEADER_87A;
    private static final byte[] GIF_HEADER_89A;
    private static final int GIF_HEADER_LENGTH = 6;
    private static final int HEIF_HEADER_LENGTH;
    private static final String HEIF_HEADER_PREFIX = "ftyp";
    private static final String[] HEIF_HEADER_SUFFIXES;
    private static final byte[] ICO_HEADER;
    private static final int ICO_HEADER_LENGTH;
    private static final byte[] JPEG_HEADER;
    private static final int JPEG_HEADER_LENGTH;
    private static final byte[] PNG_HEADER;
    private static final int PNG_HEADER_LENGTH;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    final int MAX_HEADER_LENGTH = Ints.max(21, 20, JPEG_HEADER_LENGTH, PNG_HEADER_LENGTH, 6, BMP_HEADER_LENGTH, ICO_HEADER_LENGTH, HEIF_HEADER_LENGTH);

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    public int getHeaderSize() {
        return this.MAX_HEADER_LENGTH;
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    @Nullable
    public final ImageFormat determineFormat(byte[] bArr, int i2) {
        Preconditions.checkNotNull(bArr);
        if (WebpSupportStatus.isWebpHeader(bArr, 0, i2)) {
            return getWebpFormat(bArr, i2);
        }
        if (isJpegHeader(bArr, i2)) {
            return DefaultImageFormats.JPEG;
        }
        if (isPngHeader(bArr, i2)) {
            return DefaultImageFormats.PNG;
        }
        if (isGifHeader(bArr, i2)) {
            return DefaultImageFormats.GIF;
        }
        if (isBmpHeader(bArr, i2)) {
            return DefaultImageFormats.BMP;
        }
        if (isIcoHeader(bArr, i2)) {
            return DefaultImageFormats.ICO;
        }
        if (isHeifHeader(bArr, i2)) {
            return DefaultImageFormats.HEIF;
        }
        if (isDngHeader(bArr, i2)) {
            return DefaultImageFormats.DNG;
        }
        return ImageFormat.UNKNOWN;
    }

    private static ImageFormat getWebpFormat(byte[] bArr, int i2) {
        Preconditions.checkArgument(WebpSupportStatus.isWebpHeader(bArr, 0, i2));
        if (WebpSupportStatus.isSimpleWebpHeader(bArr, 0)) {
            return DefaultImageFormats.WEBP_SIMPLE;
        }
        if (WebpSupportStatus.isLosslessWebpHeader(bArr, 0)) {
            return DefaultImageFormats.WEBP_LOSSLESS;
        }
        if (WebpSupportStatus.isExtendedWebpHeader(bArr, 0, i2)) {
            if (WebpSupportStatus.isAnimatedWebpHeader(bArr, 0)) {
                return DefaultImageFormats.WEBP_ANIMATED;
            }
            if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(bArr, 0)) {
                return DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA;
            }
            return DefaultImageFormats.WEBP_EXTENDED;
        }
        return ImageFormat.UNKNOWN;
    }

    static {
        byte[] bArr = {-1, -40, -1};
        JPEG_HEADER = bArr;
        JPEG_HEADER_LENGTH = bArr.length;
        byte[] bArr2 = {-119, 80, 78, 71, Ascii.CR, 10, Ascii.SUB, 10};
        PNG_HEADER = bArr2;
        PNG_HEADER_LENGTH = bArr2.length;
        GIF_HEADER_87A = ImageFormatCheckerUtils.asciiBytes("GIF87a");
        GIF_HEADER_89A = ImageFormatCheckerUtils.asciiBytes("GIF89a");
        byte[] asciiBytes = ImageFormatCheckerUtils.asciiBytes("BM");
        BMP_HEADER = asciiBytes;
        BMP_HEADER_LENGTH = asciiBytes.length;
        byte[] bArr3 = {0, 0, 1, 0};
        ICO_HEADER = bArr3;
        ICO_HEADER_LENGTH = bArr3.length;
        String[] strArr = {"heic", "heix", "hevc", "hevx", "mif1", "msf1"};
        HEIF_HEADER_SUFFIXES = strArr;
        HEIF_HEADER_LENGTH = ImageFormatCheckerUtils.asciiBytes(HEIF_HEADER_PREFIX + strArr[0]).length;
        DNG_HEADER_II = new byte[]{73, 73, 42, 0};
        DNG_HEADER_MM = new byte[]{77, 77, 0, 42};
    }

    private static boolean isJpegHeader(byte[] bArr, int i2) {
        byte[] bArr2 = JPEG_HEADER;
        return i2 >= bArr2.length && ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isPngHeader(byte[] bArr, int i2) {
        byte[] bArr2 = PNG_HEADER;
        return i2 >= bArr2.length && ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isGifHeader(byte[] bArr, int i2) {
        if (i2 < 6) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, GIF_HEADER_87A) || ImageFormatCheckerUtils.startsWithPattern(bArr, GIF_HEADER_89A);
    }

    private static boolean isBmpHeader(byte[] bArr, int i2) {
        byte[] bArr2 = BMP_HEADER;
        if (i2 < bArr2.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isIcoHeader(byte[] bArr, int i2) {
        byte[] bArr2 = ICO_HEADER;
        if (i2 < bArr2.length) {
            return false;
        }
        return ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2);
    }

    private static boolean isHeifHeader(byte[] bArr, int i2) {
        String[] strArr;
        if (i2 >= HEIF_HEADER_LENGTH && bArr[3] >= 8) {
            int length = HEIF_HEADER_SUFFIXES.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (ImageFormatCheckerUtils.indexOfPattern(bArr, bArr.length, ImageFormatCheckerUtils.asciiBytes(HEIF_HEADER_PREFIX + strArr[i3]), HEIF_HEADER_LENGTH) > -1) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static boolean isDngHeader(byte[] bArr, int i2) {
        byte[] bArr2 = DNG_HEADER_II;
        if (i2 >= bArr2.length || i2 >= DNG_HEADER_MM.length) {
            return ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2) || ImageFormatCheckerUtils.startsWithPattern(bArr, DNG_HEADER_MM);
        }
        return false;
    }
}
