package com.facebook.imageformat;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.logging.FLog;
import com.facebook.imageutils.AvifFormatUtil;
import com.facebook.imageutils.HeifFormatUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class DefaultImageFormats {
    private static ImmutableList<ImageFormat> sAllDefaultFormats;
    public static final ImageFormat JPEG = new ImageFormat("JPEG", "jpeg");
    public static final ImageFormat PNG = new ImageFormat("PNG", "png");
    public static final ImageFormat GIF = new ImageFormat("GIF", "gif");
    public static final ImageFormat BMP = new ImageFormat("BMP", "bmp");
    public static final ImageFormat ICO = new ImageFormat("ICO", "ico");
    public static final ImageFormat WEBP_SIMPLE = new ImageFormat("WEBP_SIMPLE", "webp");
    public static final ImageFormat WEBP_LOSSLESS = new ImageFormat("WEBP_LOSSLESS", "webp");
    public static final ImageFormat WEBP_EXTENDED = new ImageFormat("WEBP_EXTENDED", "webp");
    public static final ImageFormat WEBP_EXTENDED_WITH_ALPHA = new ImageFormat("WEBP_EXTENDED_WITH_ALPHA", "webp");
    public static final ImageFormat WEBP_ANIMATED = new ImageFormat("WEBP_ANIMATED", "webp");
    public static final ImageFormat HEIF = new ImageFormat("HEIF", "heif");
    public static final ImageFormat HEIF_FORMAT = new ImageFormat("HEIF_FORMAT", "heic");
    public static final ImageFormat VVIC_FORMAT = new ImageFormat("VVIC_FORMAT", "vvic");
    public static final ImageFormat DNG = new ImageFormat("DNG", "dng");

    public static boolean isWebpFormat(ImageFormat imageFormat) {
        return isStaticWebpFormat(imageFormat) || imageFormat == WEBP_ANIMATED;
    }

    public static boolean isStaticWebpFormat(ImageFormat imageFormat) {
        return imageFormat == WEBP_SIMPLE || imageFormat == WEBP_LOSSLESS || imageFormat == WEBP_EXTENDED || imageFormat == WEBP_EXTENDED_WITH_ALPHA;
    }

    public static boolean isHeifFormat(ImageFormat imageFormat) {
        try {
            return imageFormat == HeifFormatUtil.getHeifFormat();
        } catch (Throwable th) {
            FLog.e("DefaultImageFormats", "isHeifFormat error", th);
            return false;
        }
    }

    public static boolean isVvifFormat(ImageFormat imageFormat) {
        try {
            return imageFormat == HeifFormatUtil.getVvifFormat();
        } catch (Throwable th) {
            FLog.e("DefaultImageFormats", "isVvifFormat error", th);
            return false;
        }
    }

    public static boolean isHeifFormatAnimated(ImageFormat imageFormat) {
        ImageFormat heifFormatAnimated = HeifFormatUtil.getHeifFormatAnimated();
        return heifFormatAnimated != null && imageFormat == heifFormatAnimated;
    }

    public static boolean isAvifFormat(ImageFormat imageFormat) {
        return imageFormat == AvifFormatUtil.getAvifFormat();
    }

    public static boolean isAvifFormatAnimated(ImageFormat imageFormat) {
        return imageFormat == AvifFormatUtil.getAvifFormatAnimated();
    }

    public static boolean isAvifOrAvis(ImageFormat imageFormat) {
        return isAvifFormat(imageFormat) || isAvifFormatAnimated(imageFormat);
    }

    public static List<ImageFormat> getDefaultFormats() {
        if (sAllDefaultFormats == null) {
            ArrayList arrayList = new ArrayList(9);
            arrayList.add(JPEG);
            arrayList.add(PNG);
            arrayList.add(GIF);
            arrayList.add(BMP);
            arrayList.add(ICO);
            arrayList.add(WEBP_SIMPLE);
            arrayList.add(WEBP_LOSSLESS);
            arrayList.add(WEBP_EXTENDED);
            arrayList.add(WEBP_EXTENDED_WITH_ALPHA);
            arrayList.add(WEBP_ANIMATED);
            arrayList.add(HEIF);
            sAllDefaultFormats = ImmutableList.copyOf((List) arrayList);
        }
        return sAllDefaultFormats;
    }

    private DefaultImageFormats() {
    }
}
