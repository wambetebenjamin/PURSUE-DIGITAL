package com.facebook.imagepipeline.image;

import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.AlphaChecker;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imageutils.AvifFormatUtil;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.DngExifUtil;
import com.facebook.imageutils.HeifExifUtil;
import com.facebook.imageutils.HeifFormatUtil;
import com.facebook.imageutils.ImageMetaData;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.WebpUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class EncodedImage implements Closeable {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final String REGION_TO_DECODE = "regionToDecode";
    public static final int UNKNOWN_HEIGHT = -1;
    public static final int UNKNOWN_ROTATION_ANGLE = -1;
    public static final int UNKNOWN_STREAM_SIZE = -1;
    public static final int UNKNOWN_WIDTH = -1;
    public static final String URL = "url";
    public static final String VIEW_HEIGHT = "viewHeight";
    public static final String VIEW_WIDTH = "viewWidth";
    @Nullable
    private int heicCustomDecoder;
    @Nullable
    private boolean heicSysFirst;
    private boolean isDecodeThumb;
    private boolean isHitDiskCache;
    private boolean isHitMemoryCache;
    private boolean isRequestNetwork;
    @Nullable
    private BytesRange mBytesRange;
    @Nullable
    private ColorSpace mColorSpace;
    private int mDecodeStatus;
    private int mExifOrientation;
    @Nullable
    private Map<String, String> mExtraInfo;
    private boolean mHasAlpha;
    private int mHeifDecError;
    private int mHeight;
    private ImageFormat mImageFormat;
    @Nullable
    private final Supplier<FileInputStream> mInputStreamSupplier;
    private boolean mIs10Bit;
    @Nullable
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    @Nullable
    private Rect mRegionToDecode;
    private int mRotationAngle;
    private int mSampleSize;
    private int mStreamSize;
    private Uri mUri;
    private int mWidth;
    @Nullable
    private long thumbDecodeDuration;
    @Nullable
    private String uri;
    private int viewHeight;
    private int viewWidth;

    public EncodedImage(CloseableReference<PooledByteBuffer> closeableReference) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        this.mHasAlpha = true;
        this.mIs10Bit = false;
        this.mDecodeStatus = 0;
        this.isRequestNetwork = false;
        this.isHitDiskCache = false;
        this.isHitMemoryCache = false;
        this.thumbDecodeDuration = -1L;
        Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
        PooledByteBuffer pooledByteBuffer = closeableReference.get();
        if (pooledByteBuffer instanceof EncodeImagePooledByteBuffer) {
            this.mPooledByteBufferRef = closeableReference.m410clone();
            EncodeImagePooledByteBuffer encodeImagePooledByteBuffer = (EncodeImagePooledByteBuffer) pooledByteBuffer;
            this.mExtraInfo = encodeImagePooledByteBuffer.getExtraInfo();
            if (encodeImagePooledByteBuffer.isHitMemoryCache) {
                hitMemoryCache();
                encodeImagePooledByteBuffer.setHitMemoryCache(false);
            }
        } else {
            this.mPooledByteBufferRef = CloseableReference.of(new EncodeImagePooledByteBuffer(closeableReference.m410clone()));
        }
        this.mInputStreamSupplier = null;
    }

    public EncodedImage(Supplier<FileInputStream> supplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        this.mHasAlpha = true;
        this.mIs10Bit = false;
        this.mDecodeStatus = 0;
        this.isRequestNetwork = false;
        this.isHitDiskCache = false;
        this.isHitMemoryCache = false;
        this.thumbDecodeDuration = -1L;
        Preconditions.checkNotNull(supplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = supplier;
    }

    public EncodedImage(Supplier<FileInputStream> supplier, int i2) {
        this(supplier);
        this.mStreamSize = i2;
    }

    public static EncodedImage cloneOrNull(EncodedImage encodedImage) {
        if (encodedImage != null) {
            return encodedImage.cloneOrNull();
        }
        return null;
    }

    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        EncodedImage encodedImage2;
        if (this.mInputStreamSupplier != null) {
            encodedImage2 = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
        } else {
            CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (cloneOrNull == null) {
                encodedImage = null;
            } else {
                try {
                    encodedImage = new EncodedImage(cloneOrNull);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(cloneOrNull);
                    throw th;
                }
            }
            CloseableReference.closeSafely(cloneOrNull);
            encodedImage2 = encodedImage;
        }
        if (encodedImage2 != null) {
            encodedImage2.copyMetaDataFrom(this);
        }
        return encodedImage2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }

    public synchronized boolean isValid() {
        boolean z;
        if (!CloseableReference.isValid(this.mPooledByteBufferRef)) {
            z = this.mInputStreamSupplier != null;
        }
        return z;
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    public InputStream getInputStream() {
        Supplier<FileInputStream> supplier = this.mInputStreamSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (cloneOrNull != null) {
            try {
                return new PooledByteBufferInputStream((PooledByteBuffer) cloneOrNull.get());
            } finally {
                CloseableReference.closeSafely(cloneOrNull);
            }
        }
        return null;
    }

    public boolean isRequestNetwork() {
        return this.isRequestNetwork;
    }

    public void setRequestNetwork() {
        this.isHitMemoryCache = false;
        this.isHitDiskCache = false;
        this.isRequestNetwork = true;
    }

    public boolean isHitDiskCache() {
        return this.isHitDiskCache;
    }

    public void hitDisCache() {
        this.isHitMemoryCache = false;
        this.isHitDiskCache = true;
        this.isRequestNetwork = false;
    }

    public boolean isHitMemoryCache() {
        return this.isHitMemoryCache;
    }

    public void hitMemoryCache() {
        this.isHitMemoryCache = true;
        this.isHitDiskCache = false;
        this.isRequestNetwork = false;
    }

    public void setImageFormat(ImageFormat imageFormat) {
        this.mImageFormat = imageFormat;
    }

    public void setHeight(int i2) {
        this.mHeight = i2;
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
    }

    public void setRotationAngle(int i2) {
        this.mRotationAngle = i2;
    }

    public void setExifOrientation(int i2) {
        this.mExifOrientation = i2;
    }

    public void setSampleSize(int i2) {
        this.mSampleSize = i2;
    }

    public void setStreamSize(int i2) {
        this.mStreamSize = i2;
    }

    public void setBytesRange(@Nullable BytesRange bytesRange) {
        this.mBytesRange = bytesRange;
    }

    public void setDecodeStatus(int i2) {
        this.mDecodeStatus = i2;
    }

    public void setRegionToDecode(@Nullable Rect rect) {
        this.mRegionToDecode = rect;
    }

    public void setExtraInfo(@Nullable Map<String, String> map) {
        this.mExtraInfo = map;
        CloseableReference<PooledByteBuffer> closeableReference = this.mPooledByteBufferRef;
        if (closeableReference == null || !(closeableReference.get() instanceof EncodeImagePooledByteBuffer)) {
            return;
        }
        ((EncodeImagePooledByteBuffer) this.mPooledByteBufferRef.get()).setExtraInfo(map);
    }

    public ImageFormat getImageFormat() {
        parseMetaDataIfNeeded();
        return this.mImageFormat;
    }

    public int getRotationAngle() {
        parseMetaDataIfNeeded();
        return this.mRotationAngle;
    }

    public int getExifOrientation() {
        parseMetaDataIfNeeded();
        return this.mExifOrientation;
    }

    public int getWidth() {
        parseMetaDataIfNeeded();
        return this.mWidth;
    }

    public int getHeight() {
        parseMetaDataIfNeeded();
        return this.mHeight;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        parseMetaDataIfNeeded();
        return this.mColorSpace;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }

    @Nullable
    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public boolean isCompleteAt(int i2) {
        if ((this.mImageFormat == DefaultImageFormats.JPEG || this.mImageFormat == DefaultImageFormats.WEBP_ANIMATED || this.mImageFormat == DefaultImageFormats.DNG) && this.mInputStreamSupplier == null) {
            Preconditions.checkNotNull(this.mPooledByteBufferRef);
            PooledByteBuffer pooledByteBuffer = this.mPooledByteBufferRef.get();
            if (this.mImageFormat == DefaultImageFormats.JPEG) {
                return pooledByteBuffer.read(i2 + (-2)) == -1 && pooledByteBuffer.read(i2 - 1) == -39;
            } else if (this.mImageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                return isCompleteForStatus();
            } else {
                return true;
            }
        }
        return true;
    }

    private boolean isCompleteForStatus() {
        int i2 = this.mDecodeStatus;
        return i2 == 0 || i2 == 3;
    }

    public int getSize() {
        CloseableReference<PooledByteBuffer> closeableReference = this.mPooledByteBufferRef;
        if (closeableReference != null && closeableReference.get() != null) {
            return this.mPooledByteBufferRef.get().size();
        }
        return this.mStreamSize;
    }

    @Nullable
    public Rect getRegionToDecode() {
        return this.mRegionToDecode;
    }

    @Nullable
    public Map<String, String> getExtraInfo() {
        return this.mExtraInfo;
    }

    public String getFirstBytesAsHexString(int i2) {
        CloseableReference<PooledByteBuffer> byteBufferRef = getByteBufferRef();
        if (byteBufferRef == null) {
            return "";
        }
        int min = Math.min(getSize(), i2);
        byte[] bArr = new byte[min];
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer == null) {
                return "";
            }
            pooledByteBuffer.read(0, bArr, 0, min);
            byteBufferRef.close();
            StringBuilder sb = new StringBuilder(min * 2);
            for (int i3 = 0; i3 < min; i3++) {
                sb.append(String.format("%02X", Byte.valueOf(bArr[i3])));
            }
            return sb.toString();
        } finally {
            byteBufferRef.close();
        }
    }

    private void parseMetaDataIfNeeded() {
        if (this.mWidth < 0 || this.mHeight < 0) {
            parseMetaData();
        }
    }

    public void parseMetaData() {
        Pair<Integer, Integer> readHeifFormatImageSizeForSimple;
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
        this.mImageFormat = imageFormat_WrapIOException;
        if (DefaultImageFormats.isWebpFormat(imageFormat_WrapIOException)) {
            readHeifFormatImageSizeForSimple = readWebPImageSize();
        } else if (DefaultImageFormats.isHeifFormat(imageFormat_WrapIOException) || DefaultImageFormats.isVvifFormat(imageFormat_WrapIOException)) {
            readHeifFormatImageSizeForSimple = readHeifFormatImageSizeForSimple();
        } else if (DefaultImageFormats.isAvifOrAvis(imageFormat_WrapIOException)) {
            readHeifFormatImageSizeForSimple = readAvifImageMetaData();
        } else {
            readHeifFormatImageSizeForSimple = readImageMetaData().getDimensions();
        }
        if (imageFormat_WrapIOException == DefaultImageFormats.JPEG && this.mRotationAngle == -1) {
            if (readHeifFormatImageSizeForSimple != null) {
                int orientation = JfifUtil.getOrientation(getInputStream());
                this.mExifOrientation = orientation;
                this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(orientation);
            }
        } else if (DefaultImageFormats.isHeifFormat(imageFormat_WrapIOException) && this.mRotationAngle == -1) {
            int orientation2 = HeifExifUtil.getOrientation(getInputStream());
            this.mExifOrientation = orientation2;
            this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(orientation2);
        } else if (imageFormat_WrapIOException == DefaultImageFormats.DNG && this.mRotationAngle == -1) {
            int orientation3 = DngExifUtil.getOrientation(getInputStream());
            this.mExifOrientation = orientation3;
            this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(orientation3);
        } else if (this.mRotationAngle == -1) {
            this.mRotationAngle = 0;
        }
        this.mHasAlpha = AlphaChecker.hasAlpha(imageFormat_WrapIOException, getInputStream(), true);
        this.mRegionToDecode = parseRegionToDecode(this.mExtraInfo);
    }

    private Pair<Integer, Integer> readWebPImageSize() {
        Pair<Integer, Integer> size = WebpUtil.getSize(getInputStream());
        if (size != null) {
            this.mWidth = ((Integer) size.first).intValue();
            this.mHeight = ((Integer) size.second).intValue();
        }
        return size;
    }

    private Pair<Integer, Integer> readHeifFormatImageSizeForSimple() {
        int[] readHeifFormatImageSizeForSimple;
        InputStream inputStream = getInputStream();
        try {
            try {
                readHeifFormatImageSizeForSimple = HeifFormatUtil.readHeifFormatImageSizeForSimple(inputStream);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream == null) {
                    return null;
                }
            }
            if (readHeifFormatImageSizeForSimple != null) {
                this.mWidth = readHeifFormatImageSizeForSimple[0];
                this.mHeight = readHeifFormatImageSizeForSimple[1];
                this.mHasAlpha = readHeifFormatImageSizeForSimple[8] == 1;
                this.mIs10Bit = readHeifFormatImageSizeForSimple[9] == 10;
                int transformFromClockWiseToAntiClockWise = JfifUtil.transformFromClockWiseToAntiClockWise(readHeifFormatImageSizeForSimple[2]);
                this.mRotationAngle = transformFromClockWiseToAntiClockWise;
                if (readHeifFormatImageSizeForSimple != null && readHeifFormatImageSizeForSimple.length > 10) {
                    this.mExifOrientation = readHeifFormatImageSizeForSimple[10];
                } else {
                    this.mExifOrientation = JfifUtil.getExifOrientationFromAutoRotateAngle(transformFromClockWiseToAntiClockWise);
                }
                if (readHeifFormatImageSizeForSimple[3] == 0) {
                    this.mImageFormat = HeifFormatUtil.getHeifFormatAnimated();
                }
                Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(readHeifFormatImageSizeForSimple[0]), Integer.valueOf(readHeifFormatImageSizeForSimple[1]));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                return pair;
            }
            if (inputStream == null) {
                return null;
            }
            try {
                inputStream.close();
            } catch (IOException unused2) {
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    private Pair<Integer, Integer> readAvifImageMetaData() {
        InputStream inputStream = getInputStream();
        try {
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedImage#readAvifImageMetaData");
                }
                int[] readAvifImageSize = AvifFormatUtil.readAvifImageSize(inputStream);
                if (readAvifImageSize == null) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return null;
                }
                this.mWidth = readAvifImageSize[0];
                this.mHeight = readAvifImageSize[1];
                int transformStandarExifOrientationForAvif = JfifUtil.transformStandarExifOrientationForAvif(readAvifImageSize[2]);
                this.mExifOrientation = transformStandarExifOrientationForAvif;
                this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(transformStandarExifOrientationForAvif);
                Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(readAvifImageSize[0]), Integer.valueOf(readAvifImageSize[1]));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return pair;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private Rect parseRegionToDecode(Map<String, String> map) {
        String str;
        if (map == null || map.isEmpty() || (str = map.get("regionToDecode")) == null) {
            return null;
        }
        return Rect.unflattenFromString(str);
    }

    private ImageMetaData readImageMetaData() {
        InputStream inputStream;
        try {
            inputStream = getInputStream();
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            ImageMetaData decodeDimensionsAndColorSpace = BitmapUtil.decodeDimensionsAndColorSpace(inputStream);
            this.mColorSpace = decodeDimensionsAndColorSpace.getColorSpace();
            Pair<Integer, Integer> dimensions = decodeDimensionsAndColorSpace.getDimensions();
            if (dimensions != null) {
                this.mWidth = ((Integer) dimensions.first).intValue();
                this.mHeight = ((Integer) dimensions.second).intValue();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            return decodeDimensionsAndColorSpace;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mExifOrientation = encodedImage.getExifOrientation();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
        this.mBytesRange = encodedImage.getBytesRange();
        this.mColorSpace = encodedImage.getColorSpace();
        this.mHasAlpha = encodedImage.hasAlpha();
        this.mDecodeStatus = encodedImage.getDecodeStatus();
        this.mRegionToDecode = encodedImage.getRegionToDecode();
        this.mExtraInfo = encodedImage.getExtraInfo();
        this.isRequestNetwork = encodedImage.isRequestNetwork;
        this.isHitDiskCache = encodedImage.isHitDiskCache;
        this.isHitMemoryCache = encodedImage.isHitMemoryCache;
        this.uri = encodedImage.uri;
        this.viewWidth = encodedImage.viewWidth;
        this.viewHeight = encodedImage.viewHeight;
        this.mIs10Bit = encodedImage.is10Bit();
    }

    public boolean hasAlpha() {
        return this.mHasAlpha;
    }

    public boolean is10Bit() {
        return this.mIs10Bit;
    }

    public int getDecodeStatus() {
        return this.mDecodeStatus;
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    public static void closeSafely(@Nullable EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public static boolean isValid(@Nullable EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    public synchronized SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly() {
        CloseableReference<PooledByteBuffer> closeableReference;
        closeableReference = this.mPooledByteBufferRef;
        return closeableReference != null ? closeableReference.getUnderlyingReferenceTestOnly() : null;
    }

    /* loaded from: classes8.dex */
    public static class EncodeImagePooledByteBuffer implements PooledByteBuffer, ResourceReleaser<EncodeImagePooledByteBuffer>, MemoryCacheHitTracker {
        public final CloseableReference<PooledByteBuffer> byteBufferRef;
        private boolean isHitMemoryCache;
        private Map<String, String> mExtraInfo;

        public EncodeImagePooledByteBuffer(CloseableReference<PooledByteBuffer> closeableReference) {
            this.byteBufferRef = closeableReference;
        }

        public Map<String, String> getExtraInfo() {
            return this.mExtraInfo;
        }

        @Override // com.facebook.imagepipeline.image.MemoryCacheHitTracker
        public void hitMemoryCache() {
            setHitMemoryCache(true);
        }

        public void setExtraInfo(Map<String, String> map) {
            this.mExtraInfo = map;
        }

        public void setHitMemoryCache(boolean z) {
            this.isHitMemoryCache = z;
        }

        public boolean isHitMemoryCache() {
            return this.isHitMemoryCache;
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        public int size() {
            return this.byteBufferRef.get().size();
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        public byte read(int i2) {
            return this.byteBufferRef.get().read(i2);
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        public int read(int i2, byte[] bArr, int i3, int i4) {
            return this.byteBufferRef.get().read(i2, bArr, i3, i4);
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        public long getNativePtr() {
            return this.byteBufferRef.get().getNativePtr();
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        @Nullable
        public ByteBuffer getByteBuffer() {
            return this.byteBufferRef.get().getByteBuffer();
        }

        @Override // com.facebook.common.memory.PooledByteBuffer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.byteBufferRef.get().close();
        }

        @Override // com.facebook.common.memory.PooledByteBuffer
        public boolean isClosed() {
            return this.byteBufferRef.get().isClosed();
        }

        @Override // com.facebook.common.references.ResourceReleaser
        public void release(EncodeImagePooledByteBuffer encodeImagePooledByteBuffer) {
            encodeImagePooledByteBuffer.byteBufferRef.close();
        }
    }

    public void setIsDecodeThumb(boolean z) {
        this.isDecodeThumb = z;
    }

    public boolean isDecodeThumb() {
        return this.isDecodeThumb;
    }

    public boolean isHeicSysFirst() {
        return this.heicSysFirst;
    }

    public void setHeicSysFirst(boolean z) {
        this.heicSysFirst = z;
    }

    public int getHeicCustomDecoder() {
        return this.heicCustomDecoder;
    }

    public void setHeicCustomDecoder(int i2) {
        this.heicCustomDecoder = i2;
    }

    public long getThumbDecodeDuration() {
        return this.thumbDecodeDuration;
    }

    public void setThumbDecodeDuration(long j2) {
        this.thumbDecodeDuration = j2;
    }

    public Uri getImageUri() {
        return this.mUri;
    }

    public void setImageRequestUri(Uri uri) {
        this.mUri = uri;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public void setViewWidth(int i2) {
        this.viewWidth = i2;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public void setViewHeight(int i2) {
        this.viewHeight = i2;
    }

    public void setHeifDecError(int i2) {
        this.mHeifDecError = i2;
    }

    public int getHeifDecError() {
        return this.mHeifDecError;
    }
}
