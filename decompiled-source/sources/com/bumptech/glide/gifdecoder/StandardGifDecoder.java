package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
/* loaded from: classes.dex */
public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = "StandardGifDecoder";
    private int[] act;
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i2) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, i2);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider;
        this.header = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.header.width;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.header.height;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public ByteBuffer getData() {
        return this.rawData;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int i2) {
        if (i2 < 0 || i2 >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i2).delay;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        int i2;
        if (this.header.frameCount <= 0 || (i2 = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i2);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            String str = TAG;
            if (Log.isLoggable(str, 3)) {
                Log.d(str, "Unable to decode frame, frameCount=" + this.header.frameCount + ", framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        int i2 = this.status;
        if (i2 != 1 && i2 != 2) {
            this.status = 0;
            if (this.block == null) {
                this.block = this.bitmapProvider.obtainByteArray(255);
            }
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int i3 = this.framePointer - 1;
            GifFrame gifFrame2 = i3 >= 0 ? this.header.frames.get(i3) : null;
            int[] iArr = gifFrame.lct != null ? gifFrame.lct : this.header.gct;
            this.act = iArr;
            if (iArr == null) {
                String str2 = TAG;
                if (Log.isLoggable(str2, 3)) {
                    Log.d(str2, "No valid color table found for frame #" + this.framePointer);
                }
                this.status = 1;
                return null;
            }
            if (gifFrame.transparency) {
                int[] iArr2 = this.act;
                System.arraycopy(iArr2, 0, this.pct, 0, iArr2.length);
                int[] iArr3 = this.pct;
                this.act = iArr3;
                iArr3[gifFrame.transIndex] = 0;
            }
            return setPixels(gifFrame, gifFrame2);
        }
        String str3 = TAG;
        if (Log.isLoggable(str3, 3)) {
            Log.d(str3, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(InputStream inputStream, int i2) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2 > 0 ? i2 + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, 16384);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e2) {
                Log.w(TAG, "Error reading data from stream", e2);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                Log.w(TAG, "Error closing stream", e3);
            }
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i2);
        }
        int highestOneBit = Integer.highestOneBit(i2);
        this.status = 0;
        this.header = gifHeader;
        this.framePointer = -1;
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.rawData = asReadOnlyBuffer;
        asReadOnlyBuffer.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.sampleSize = highestOneBit;
        this.downsampledWidth = gifHeader.width / highestOneBit;
        this.downsampledHeight = gifHeader.height / highestOneBit;
        this.mainPixels = this.bitmapProvider.obtainByteArray(gifHeader.width * gifHeader.height);
        this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(byte[] bArr) {
        GifHeader parseHeader = getHeaderParser().setData(bArr).parseHeader();
        this.header = parseHeader;
        if (bArr != null) {
            setData(parseHeader, bArr);
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.bitmapConfig = config;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        Bitmap bitmap;
        int[] iArr = this.mainScratch;
        int i2 = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                if (!gifFrame.transparency) {
                    int i3 = this.header.bgColor;
                    if (gifFrame.lct == null || this.header.bgIndex != gifFrame.transIndex) {
                        i2 = i3;
                    }
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
                int i4 = gifFrame2.ih / this.sampleSize;
                int i5 = gifFrame2.iy / this.sampleSize;
                int i6 = gifFrame2.iw / this.sampleSize;
                int i7 = gifFrame2.ix / this.sampleSize;
                int i8 = this.downsampledWidth;
                int i9 = (i5 * i8) + i7;
                int i10 = (i4 * i8) + i9;
                while (i9 < i10) {
                    int i11 = i9 + i6;
                    for (int i12 = i9; i12 < i11; i12++) {
                        iArr[i12] = i2;
                    }
                    i9 += this.downsampledWidth;
                }
            } else if (gifFrame2.dispose == 3 && (bitmap = this.previousImage) != null) {
                int i13 = this.downsampledWidth;
                bitmap.getPixels(iArr, 0, i13, 0, 0, i13, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i14 = this.downsampledWidth;
            bitmap3.setPixels(iArr, 0, i14, 0, 0, i14, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i15 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i15, 0, 0, i15, this.downsampledHeight);
        return nextBitmap;
    }

    private void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i2 = gifFrame2.ih;
        int i3 = gifFrame2.iy;
        int i4 = gifFrame2.iw;
        int i5 = gifFrame2.ix;
        boolean z = this.framePointer == 0;
        int i6 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        int i7 = 0;
        byte b2 = -1;
        while (i7 < i2) {
            int i8 = (i7 + i3) * i6;
            int i9 = i8 + i5;
            int i10 = i9 + i4;
            int i11 = i8 + i6;
            if (i11 < i10) {
                i10 = i11;
            }
            int i12 = gifFrame2.iw * i7;
            int i13 = i9;
            while (i13 < i10) {
                byte b3 = bArr[i12];
                int i14 = i2;
                int i15 = b3 & 255;
                if (i15 != b2) {
                    int i16 = iArr2[i15];
                    if (i16 != 0) {
                        iArr[i13] = i16;
                    } else {
                        b2 = b3;
                    }
                }
                i12++;
                i13++;
                i2 = i14;
            }
            i7++;
            gifFrame2 = gifFrame;
        }
        this.isFirstFrameTransparent = Boolean.valueOf(this.isFirstFrameTransparent == null && z && b2 != -1);
    }

    private void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr = this.mainScratch;
        int i7 = gifFrame.ih / this.sampleSize;
        int i8 = gifFrame.iy / this.sampleSize;
        int i9 = gifFrame.iw / this.sampleSize;
        int i10 = gifFrame.ix;
        int i11 = this.sampleSize;
        int i12 = i10 / i11;
        boolean z = this.framePointer == 0;
        int i13 = this.downsampledWidth;
        int i14 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int i15 = 8;
        int i16 = 1;
        int i17 = 0;
        int i18 = 0;
        while (i17 < i7) {
            Boolean bool2 = bool;
            if (gifFrame.interlace) {
                if (i18 >= i7) {
                    i2 = i7;
                    int i19 = i16 + 1;
                    if (i19 == 2) {
                        i16 = i19;
                        i18 = 4;
                    } else if (i19 == 3) {
                        i16 = i19;
                        i15 = 4;
                        i18 = 2;
                    } else if (i19 != 4) {
                        i16 = i19;
                    } else {
                        i16 = i19;
                        i18 = 1;
                        i15 = 2;
                    }
                } else {
                    i2 = i7;
                }
                i3 = i18 + i15;
            } else {
                i2 = i7;
                i3 = i18;
                i18 = i17;
            }
            int i20 = i18 + i8;
            boolean z2 = i11 == 1;
            if (i20 < i14) {
                int i21 = i20 * i13;
                int i22 = i21 + i12;
                int i23 = i22 + i9;
                int i24 = i21 + i13;
                if (i24 < i23) {
                    i23 = i24;
                }
                i4 = i3;
                int i25 = i17 * i11 * gifFrame.iw;
                if (z2) {
                    int i26 = i22;
                    while (i26 < i23) {
                        int i27 = i8;
                        int i28 = iArr2[bArr[i25] & 255];
                        if (i28 != 0) {
                            iArr[i26] = i28;
                        } else if (z && bool2 == null) {
                            bool2 = true;
                        }
                        i25 += i11;
                        i26++;
                        i8 = i27;
                    }
                } else {
                    i6 = i8;
                    int i29 = ((i23 - i22) * i11) + i25;
                    int i30 = i22;
                    while (true) {
                        i5 = i9;
                        if (i30 < i23) {
                            int averageColorsNear = averageColorsNear(i25, i29, gifFrame.iw);
                            if (averageColorsNear != 0) {
                                iArr[i30] = averageColorsNear;
                            } else if (z && bool2 == null) {
                                bool2 = true;
                            }
                            i25 += i11;
                            i30++;
                            i9 = i5;
                        }
                    }
                    bool = bool2;
                    i17++;
                    i8 = i6;
                    i9 = i5;
                    i7 = i2;
                    i18 = i4;
                }
            } else {
                i4 = i3;
            }
            i6 = i8;
            i5 = i9;
            bool = bool2;
            i17++;
            i8 = i6;
            i9 = i5;
            i7 = i2;
            i18 = i4;
        }
        Boolean bool3 = bool;
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(bool3 == null ? false : bool3.booleanValue());
        }
    }

    private int averageColorsNear(int i2, int i3, int i4) {
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = i2; i10 < this.sampleSize + i2; i10++) {
            byte[] bArr = this.mainPixels;
            if (i10 >= bArr.length || i10 >= i3) {
                break;
            }
            int i11 = this.act[bArr[i10] & 255];
            if (i11 != 0) {
                i5 += (i11 >> 24) & 255;
                i6 += (i11 >> 16) & 255;
                i7 += (i11 >> 8) & 255;
                i8 += i11 & 255;
                i9++;
            }
        }
        int i12 = i2 + i4;
        for (int i13 = i12; i13 < this.sampleSize + i12; i13++) {
            byte[] bArr2 = this.mainPixels;
            if (i13 >= bArr2.length || i13 >= i3) {
                break;
            }
            int i14 = this.act[bArr2[i13] & 255];
            if (i14 != 0) {
                i5 += (i14 >> 24) & 255;
                i6 += (i14 >> 16) & 255;
                i7 += (i14 >> 8) & 255;
                i8 += i14 & 255;
                i9++;
            }
        }
        if (i9 == 0) {
            return 0;
        }
        return ((i5 / i9) << 24) | ((i6 / i9) << 16) | ((i7 / i9) << 8) | (i8 / i9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void decodeBitmapData(GifFrame gifFrame) {
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (gifFrame != null) {
            standardGifDecoder.rawData.position(gifFrame.bufferFrameStart);
        }
        int i2 = gifFrame == null ? standardGifDecoder.header.width * standardGifDecoder.header.height : gifFrame.ih * gifFrame.iw;
        byte[] bArr = standardGifDecoder.mainPixels;
        if (bArr == null || bArr.length < i2) {
            standardGifDecoder.mainPixels = standardGifDecoder.bitmapProvider.obtainByteArray(i2);
        }
        byte[] bArr2 = standardGifDecoder.mainPixels;
        if (standardGifDecoder.prefix == null) {
            standardGifDecoder.prefix = new short[4096];
        }
        short[] sArr = standardGifDecoder.prefix;
        if (standardGifDecoder.suffix == null) {
            standardGifDecoder.suffix = new byte[4096];
        }
        byte[] bArr3 = standardGifDecoder.suffix;
        if (standardGifDecoder.pixelStack == null) {
            standardGifDecoder.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        byte[] bArr4 = standardGifDecoder.pixelStack;
        int readByte = readByte();
        int i3 = 1 << readByte;
        int i4 = i3 + 1;
        int i5 = i3 + 2;
        int i6 = readByte + 1;
        int i7 = (1 << i6) - 1;
        int i8 = 0;
        for (int i9 = 0; i9 < i3; i9++) {
            sArr[i9] = 0;
            bArr3[i9] = (byte) i9;
        }
        byte[] bArr5 = standardGifDecoder.block;
        int i10 = i6;
        int i11 = i5;
        int i12 = i7;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = -1;
        while (true) {
            if (i8 >= i2) {
                break;
            }
            if (i13 == 0) {
                i13 = readBlock();
                if (i13 <= 0) {
                    standardGifDecoder.status = 3;
                    break;
                }
                i14 = 0;
            }
            i16 += (bArr5[i14] & 255) << i15;
            i14++;
            i13--;
            int i21 = i15 + 8;
            int i22 = i11;
            int i23 = i10;
            int i24 = i20;
            int i25 = i6;
            int i26 = i18;
            while (true) {
                if (i21 < i23) {
                    i20 = i24;
                    i11 = i22;
                    i15 = i21;
                    standardGifDecoder = this;
                    i18 = i26;
                    i6 = i25;
                    i10 = i23;
                    break;
                }
                int i27 = i5;
                int i28 = i16 & i12;
                i16 >>= i23;
                i21 -= i23;
                if (i28 == i3) {
                    i12 = i7;
                    i23 = i25;
                    i22 = i27;
                    i5 = i22;
                    i24 = -1;
                } else if (i28 == i4) {
                    i15 = i21;
                    i18 = i26;
                    i11 = i22;
                    i6 = i25;
                    i5 = i27;
                    i20 = i24;
                    i10 = i23;
                    standardGifDecoder = this;
                    break;
                } else if (i24 == -1) {
                    bArr2[i17] = bArr3[i28];
                    i17++;
                    i8++;
                    i24 = i28;
                    i26 = i24;
                    i5 = i27;
                    i21 = i21;
                } else {
                    if (i28 >= i22) {
                        bArr4[i19] = (byte) i26;
                        i19++;
                        s = i24;
                    } else {
                        s = i28;
                    }
                    while (s >= i3) {
                        bArr4[i19] = bArr3[s];
                        i19++;
                        s = sArr[s];
                    }
                    i26 = bArr3[s] & 255;
                    byte b2 = (byte) i26;
                    bArr2[i17] = b2;
                    while (true) {
                        i17++;
                        i8++;
                        if (i19 <= 0) {
                            break;
                        }
                        i19--;
                        bArr2[i17] = bArr4[i19];
                    }
                    byte[] bArr6 = bArr4;
                    if (i22 < 4096) {
                        sArr[i22] = (short) i24;
                        bArr3[i22] = b2;
                        i22++;
                        if ((i22 & i12) == 0 && i22 < 4096) {
                            i23++;
                            i12 += i22;
                        }
                    }
                    i24 = i28;
                    i5 = i27;
                    i21 = i21;
                    bArr4 = bArr6;
                }
            }
        }
        Arrays.fill(bArr2, i17, i2, (byte) 0);
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(readByte, byteBuffer.remaining()));
        return readByte;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }
}
