package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageutils.HeifFormatUtil;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes8.dex */
public class AlphaChecker {
    private AlphaChecker() {
    }

    public static boolean hasAlpha(ImageFormat imageFormat, InputStream inputStream, boolean z) {
        try {
            if (imageFormat != DefaultImageFormats.JPEG && imageFormat != DefaultImageFormats.WEBP_SIMPLE && imageFormat != DefaultImageFormats.WEBP_EXTENDED) {
                if (z && imageFormat != null && (imageFormat == DefaultImageFormats.HEIF_FORMAT || imageFormat == DefaultImageFormats.HEIF || "HEIF_FORMAT".equals(imageFormat.toString()) || "HEIF".equals(imageFormat.toString()) || imageFormat == DefaultImageFormats.VVIC_FORMAT || "VVIC_FORMAT".equals(imageFormat.toString()))) {
                    return hasAlphaForHeif(inputStream);
                }
                if (imageFormat != DefaultImageFormats.HEIF_FORMAT && imageFormat != DefaultImageFormats.GIF && imageFormat != DefaultImageFormats.HEIF && imageFormat != DefaultImageFormats.BMP && imageFormat != DefaultImageFormats.ICO && imageFormat != DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
                    if (imageFormat == DefaultImageFormats.PNG) {
                        return hasAlphaForPNG(inputStream);
                    }
                    if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                        return hasAlphaForAWebp(inputStream);
                    }
                }
                return true;
            }
            return false;
        } catch (IOException unused) {
            return true;
        }
    }

    private static boolean hasAlphaForAWebp(InputStream inputStream) {
        int maxHeaderLength = ImageFormatChecker.getInstance().getMaxHeaderLength();
        byte[] bArr = new byte[maxHeaderLength];
        readHeaderFromStream(maxHeaderLength, inputStream, bArr);
        return WebpSupportStatus.isExtendedWebpHeaderWithAlpha(bArr, 0);
    }

    private static boolean hasAlphaForPNG(InputStream inputStream) {
        byte[] bArr = new byte[29];
        readHeaderFromStream(29, inputStream, bArr);
        byte b2 = bArr[25];
        return b2 == 4 || b2 == 6;
    }

    public static boolean hasAlphaForHeif(InputStream inputStream) {
        boolean z = true;
        try {
            int[] readHeifFormatImageSizeForSimple = HeifFormatUtil.readHeifFormatImageSizeForSimple(inputStream);
            if (readHeifFormatImageSizeForSimple != null && readHeifFormatImageSizeForSimple[8] == 0) {
                z = false;
            }
            inputStream.reset();
        } catch (IOException unused) {
        }
        return z;
    }

    private static int readHeaderFromStream(int i2, InputStream inputStream, byte[] bArr) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(bArr.length >= i2);
        if (inputStream.markSupported()) {
            try {
                inputStream.mark(i2);
                return ByteStreams.read(inputStream, bArr, 0, i2);
            } finally {
                inputStream.reset();
            }
        }
        return ByteStreams.read(inputStream, bArr, 0, i2);
    }
}
