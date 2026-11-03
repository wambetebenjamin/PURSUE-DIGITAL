package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import com.facebook.imageformat.ImageFormat;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes8.dex */
public class AvifFormatUtil {
    private static final String TAG = "AvifFormatUtil";
    private static Class sAvifDecodeClass;
    private static ImageFormat sAvifFormat;
    private static ImageFormat sAvifFormatAnimated;
    private static Method sParseMetaMethod;

    static {
        try {
            sAvifDecodeClass = Class.forName("com.bytedance.fresco.avif.AvifDecoder");
        } catch (Throwable unused) {
            FLog.e(TAG, "No AvifDecoder class");
        }
    }

    public static ImageFormat getAvifFormat() {
        ImageFormat imageFormat = sAvifFormat;
        if (imageFormat != null) {
            return imageFormat;
        }
        Class cls = sAvifDecodeClass;
        if (cls == null) {
            return ImageFormat.UNKNOWN;
        }
        try {
            sAvifFormat = (ImageFormat) cls.getDeclaredField("AVIF_FORMAT").get(null);
        } catch (IllegalAccessException unused) {
            FLog.e(TAG, "IllegalAccessException happened when invoke getAvifFormat");
        } catch (NoSuchFieldException unused2) {
            FLog.e(TAG, "No AVIF_FORMAT field In AvifDecoder");
        } catch (Throwable unused3) {
            FLog.e(TAG, "Exception happened when invoke getAvifFormat");
        }
        return sAvifFormat;
    }

    public static ImageFormat getAvifFormatAnimated() {
        ImageFormat imageFormat = sAvifFormatAnimated;
        if (imageFormat != null) {
            return imageFormat;
        }
        Class cls = sAvifDecodeClass;
        if (cls == null) {
            return ImageFormat.UNKNOWN;
        }
        try {
            sAvifFormatAnimated = (ImageFormat) cls.getDeclaredField("AVIF_FORMAT_ANIMATED").get(null);
        } catch (IllegalAccessException unused) {
            FLog.e(TAG, "IllegalAccessException happened when invoke getAvifFormatAnimated");
        } catch (NoSuchFieldException unused2) {
            FLog.e(TAG, "No AVIF_FORMAT_ANIMATED field In AvifDecoder");
        } catch (Throwable unused3) {
            FLog.e(TAG, "Exception happened when invoke getAvifFormatAnimated");
        }
        return sAvifFormatAnimated;
    }

    public static int[] parseSimpleMeta(byte[] bArr, int i2) {
        Class cls = sAvifDecodeClass;
        if (cls == null) {
            return null;
        }
        try {
            if (sParseMetaMethod == null) {
                sParseMetaMethod = cls.getDeclaredMethod("parseSimpleMeta", byte[].class, Integer.TYPE);
            }
            Method method = sParseMetaMethod;
            if (method != null) {
                method.setAccessible(true);
                return (int[]) sParseMetaMethod.invoke(null, bArr, Integer.valueOf(i2));
            }
        } catch (IllegalAccessException unused) {
            FLog.e(TAG, "IllegalAccessException happened when invoke parseSimpleMeta");
        } catch (NoSuchMethodException unused2) {
            FLog.e(TAG, "No parseSimpleMeta method AvifDecoder");
        } catch (InvocationTargetException unused3) {
            FLog.e(TAG, "InvocationTargetException happened when invoke parseSimpleMeta");
        } catch (Throwable unused4) {
            FLog.e(TAG, "Exception happened when invoke parseSimpleMeta");
        }
        return null;
    }

    public static int[] readAvifImageSize(InputStream inputStream) {
        int available = inputStream.available();
        byte[] bArr = new byte[available];
        inputStream.reset();
        if (inputStream.read(bArr, 0, available) != -1) {
            return parseSimpleMeta(bArr, available);
        }
        return null;
    }
}
