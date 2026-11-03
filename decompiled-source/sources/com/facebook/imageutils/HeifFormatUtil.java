package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes8.dex */
public class HeifFormatUtil {
    private static final String TAG = "HeifFormatUtil";
    private static volatile Class sHeifClass;
    private static Class sHeifDecodeClass;
    private static ImageFormat sHeifFormat;
    private static ImageFormat sHeifFormatAnimated;
    private static Method sParseMetaMethod;
    private static Method sParseThumbMetaMethod;
    private static ImageFormat sVvifFormat;

    static {
        try {
            sHeifDecodeClass = Class.forName("com.bytedance.fresco.heif.HeifDecoder");
        } catch (ClassNotFoundException e2) {
            FLog.e(TAG, "Heif init ", e2);
        }
    }

    public static int[] parseSimpleMeta(byte[] bArr, int i2) {
        Class heifClass = getHeifClass();
        if (heifClass == null) {
            return null;
        }
        try {
            if (sParseMetaMethod == null) {
                sParseMetaMethod = heifClass.getDeclaredMethod("parseSimpleMeta", byte[].class, Integer.TYPE);
            }
            Method method = sParseMetaMethod;
            if (method != null) {
                method.setAccessible(true);
                return (int[]) sParseMetaMethod.invoke(null, bArr, Integer.valueOf(i2));
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return null;
    }

    public static int[] parseThumbMeta(byte[] bArr, int i2) {
        Class heifClass = getHeifClass();
        if (heifClass == null) {
            return null;
        }
        try {
            if (sParseThumbMetaMethod == null) {
                sParseThumbMetaMethod = heifClass.getDeclaredMethod("parseThumbMeta", byte[].class, Integer.TYPE);
            }
            Method method = sParseThumbMetaMethod;
            if (method != null) {
                method.setAccessible(true);
                return (int[]) sParseThumbMetaMethod.invoke(null, bArr, Integer.valueOf(i2));
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return null;
    }

    public static ImageFormat getHeifFormat() {
        ImageFormat imageFormat = sHeifFormat;
        if (imageFormat != null) {
            return imageFormat;
        }
        Class cls = sHeifDecodeClass;
        if (cls == null) {
            return null;
        }
        try {
            sHeifFormat = (ImageFormat) cls.getDeclaredField("HEIF_FORMAT").get(null);
        } catch (IllegalAccessException e2) {
            FLog.e(TAG, "HeifFormat init ", e2);
        } catch (NoSuchFieldException e3) {
            FLog.e(TAG, "HeifFormat init ", e3);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return sHeifFormat;
    }

    public static ImageFormat getVvifFormat() {
        ImageFormat imageFormat = sVvifFormat;
        if (imageFormat != null) {
            return imageFormat;
        }
        Class cls = sHeifDecodeClass;
        if (cls == null) {
            return null;
        }
        try {
            sVvifFormat = (ImageFormat) cls.getDeclaredField("VVIC_FORMAT").get(null);
        } catch (IllegalAccessException e2) {
            FLog.e(TAG, "VvifFormat init ", e2);
        } catch (NoSuchFieldException e3) {
            FLog.e(TAG, "VvifFormat init ", e3);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return sVvifFormat;
    }

    public static ImageFormat getHeifFormatAnimated() {
        ImageFormat imageFormat = sHeifFormatAnimated;
        if (imageFormat != null) {
            return imageFormat;
        }
        Class cls = sHeifDecodeClass;
        if (cls == null) {
            return null;
        }
        try {
            sHeifFormatAnimated = (ImageFormat) cls.getDeclaredField("HEIF_FORMAT_ANIMATED").get(null);
        } catch (IllegalAccessException e2) {
            FLog.e(TAG, "HEIF_FORMAT_ANIMATED init ", e2);
        } catch (NoSuchFieldException e3) {
            FLog.e(TAG, "HEIF_FORMAT_ANIMATED init ", e3);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return sHeifFormatAnimated;
    }

    public static boolean canParseThumbDataAndSet(EncodedImage encodedImage) {
        if (encodedImage != null && DefaultImageFormats.isHeifFormat(encodedImage.getImageFormat())) {
            try {
                int available = encodedImage.getInputStream().available();
                int[] readHeifThumbData = readHeifThumbData(encodedImage.getInputStream());
                if (readHeifThumbData != null && readHeifThumbData.length >= 8 && readHeifThumbData[5] == 1) {
                    FLog.d(TAG, "fresco_parseThumbData available len:" + available + " thumb:" + (readHeifThumbData[6] + readHeifThumbData[7]));
                    if (readHeifThumbData[6] + readHeifThumbData[7] < available) {
                        encodedImage.setIsDecodeThumb(true);
                    }
                    return true;
                }
            } catch (IOException e2) {
                FLog.e(TAG, "canParseThumbData ", e2);
            }
        }
        return false;
    }

    public static int[] readHeifThumbData(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        int available = inputStream.available();
        byte[] bArr = new byte[available];
        inputStream.reset();
        if (inputStream.read(bArr, 0, available) != -1) {
            return parseThumbMeta(bArr, available);
        }
        return null;
    }

    public static int[] readHeifFormatImageSizeForSimple(InputStream inputStream) {
        int available = inputStream.available();
        byte[] bArr = new byte[available];
        inputStream.reset();
        if (inputStream.read(bArr, 0, available) != -1) {
            return parseSimpleMeta(bArr, available);
        }
        return null;
    }

    private static synchronized Class getHeifClass() {
        Class cls;
        synchronized (HeifFormatUtil.class) {
            if (sHeifClass == null) {
                try {
                    sHeifClass = Class.forName("com.bytedance.fresco.nativeheif.Heif");
                } catch (ClassNotFoundException e2) {
                    FLog.e(TAG, "Heif init ", e2);
                }
            }
            cls = sHeifClass;
        }
        return cls;
    }
}
