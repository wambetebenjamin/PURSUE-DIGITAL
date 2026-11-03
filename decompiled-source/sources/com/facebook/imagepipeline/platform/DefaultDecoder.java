package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import androidx.core.util.Pools;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class DefaultDecoder implements PlatformDecoder {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private static boolean optPngDecode;
    private final BitmapPool mBitmapPool;
    final Pools.SynchronizedPool<ByteBuffer> mDecodeBuffers;
    private static final Class<?> TAG = DefaultDecoder.class;
    private static final byte[] EOI_TAIL = {-1, -39};

    public abstract int getBitmapSize(int i2, int i3, BitmapFactory.Options options);

    public static void setOptPngDecode(boolean z) {
        optPngDecode = z;
    }

    public DefaultDecoder(BitmapPool bitmapPool, int i2, Pools.SynchronizedPool synchronizedPool) {
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = synchronizedPool;
        for (int i3 = 0; i3 < i2; i3++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect) {
        return decodeFromEncodedImageWithColorSpace(encodedImage, config, rect, false);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i2) {
        return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, config, rect, i2, false);
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, boolean z) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        boolean z2 = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            if (optPngDecode && "PNG".equalsIgnoreCase(encodedImage.getImageFormat().toString()) && (encodedImage.hasAlpha() || encodedImage.is10Bit())) {
                decodeOptionsForStream.inPreferredConfig = Bitmap.Config.ARGB_8888;
            }
            return decodeFromStream(encodedImage.getInputStream(), decodeOptionsForStream, rect, z);
        } catch (RuntimeException e2) {
            if (z2) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, z);
            }
            throw e2;
        }
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, @Nullable Rect rect, int i2, boolean z) {
        boolean isCompleteAt = encodedImage.isCompleteAt(i2);
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        TailAppendingInputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > i2) {
            inputStream = new LimitedInputStream(inputStream, i2);
        }
        if (!isCompleteAt) {
            inputStream = new TailAppendingInputStream(inputStream, EOI_TAIL);
        }
        boolean z2 = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream(inputStream, decodeOptionsForStream, rect, z);
        } catch (RuntimeException e2) {
            if (z2) {
                return decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, i2, z);
            }
            throw e2;
        }
    }

    protected CloseableReference<Bitmap> decodeStaticImageFromStream(InputStream inputStream, BitmapFactory.Options options, @Nullable Rect rect) {
        return decodeFromStream(inputStream, options, rect, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0082 A[Catch: all -> 0x00b5, OutOfMemoryError -> 0x00b7, RuntimeException -> 0x00be, IllegalArgumentException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00be, blocks: (B:13:0x004e, B:18:0x0065, B:32:0x0089, B:36:0x0091, B:35:0x008e, B:25:0x007b, B:28:0x0082, B:29:0x0085), top: B:63:0x004e, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.facebook.common.references.CloseableReference<android.graphics.Bitmap> decodeFromStream(java.io.InputStream r11, android.graphics.BitmapFactory.Options r12, @javax.annotation.Nullable android.graphics.Rect r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeFromStream(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, boolean):com.facebook.common.references.CloseableReference");
    }

    protected Bitmap decodeBitmap(InputStream inputStream, BitmapFactory.Options options, int i2, int i3, Bitmap bitmap) {
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException(DefaultImageDecoder.parseEncodedImageInfo(encodedImage));
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }
}
