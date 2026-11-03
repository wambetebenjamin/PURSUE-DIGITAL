package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ImageFormatChecker {
    private static ImageFormatChecker sInstance;
    @Nullable
    private List<ImageFormat.FormatChecker> mCustomImageFormatCheckers;
    private final ImageFormat.FormatChecker mDefaultFormatChecker = new DefaultImageFormatChecker();
    private int mMaxHeaderLength;

    private ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public void setCustomImageFormatCheckers(@Nullable List<ImageFormat.FormatChecker> list) {
        this.mCustomImageFormatCheckers = list;
        updateMaxHeaderLength();
    }

    public ImageFormat determineImageFormat(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream);
        int i2 = this.mMaxHeaderLength;
        byte[] bArr = new byte[i2];
        int readHeaderFromStream = readHeaderFromStream(i2, inputStream, bArr);
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker formatChecker : list) {
                ImageFormat determineFormat = formatChecker.determineFormat(bArr, readHeaderFromStream);
                if (determineFormat != null && determineFormat != ImageFormat.UNKNOWN) {
                    return determineFormat;
                }
            }
        }
        ImageFormat determineFormat2 = this.mDefaultFormatChecker.determineFormat(bArr, readHeaderFromStream);
        return (determineFormat2 == null || determineFormat2 == ImageFormat.UNKNOWN) ? ImageFormat.UNKNOWN : determineFormat2;
    }

    public int getMaxHeaderLength() {
        return this.mMaxHeaderLength;
    }

    private void updateMaxHeaderLength() {
        this.mMaxHeaderLength = this.mDefaultFormatChecker.getHeaderSize();
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker formatChecker : list) {
                this.mMaxHeaderLength = Math.max(this.mMaxHeaderLength, formatChecker.getHeaderSize());
            }
        }
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

    public static synchronized ImageFormatChecker getInstance() {
        ImageFormatChecker imageFormatChecker;
        synchronized (ImageFormatChecker.class) {
            if (sInstance == null) {
                sInstance = new ImageFormatChecker();
            }
            imageFormatChecker = sInstance;
        }
        return imageFormatChecker;
    }

    public static ImageFormat getImageFormat(InputStream inputStream) {
        return getInstance().determineImageFormat(inputStream);
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        } catch (IOException e2) {
            throw Throwables.propagate(e2);
        }
    }

    public static ImageFormat getImageFormat(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException unused) {
        }
        try {
            ImageFormat imageFormat = getImageFormat(fileInputStream);
            Closeables.closeQuietly(fileInputStream);
            return imageFormat;
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            ImageFormat imageFormat2 = ImageFormat.UNKNOWN;
            Closeables.closeQuietly(fileInputStream2);
            return imageFormat2;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            Closeables.closeQuietly(fileInputStream2);
            throw th;
        }
    }
}
