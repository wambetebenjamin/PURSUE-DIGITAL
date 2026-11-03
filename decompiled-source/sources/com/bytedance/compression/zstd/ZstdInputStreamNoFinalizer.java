package com.bytedance.compression.zstd;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
class ZstdInputStreamNoFinalizer extends FilterInputStream {
    private static final int srcBuffSize;
    private final b bufferPool;
    private long dstPos;
    private boolean frameFinished;
    private boolean isClosed;
    private boolean isContinuous;
    private boolean needRead;
    private final byte[] src;
    private final ByteBuffer srcByteBuffer;
    private long srcPos;
    private long srcSize;
    private final long stream;

    private static native long createDStream();

    private native int decompressStream(long j2, byte[] bArr, int i2, byte[] bArr2, int i3);

    private static native int freeDStream(long j2);

    private native int initDStream(long j2);

    public static native long recommendedDInSize();

    public static native long recommendedDOutSize();

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    static {
        com.bytedance.compression.zstd.util.a.a();
        srcBuffSize = (int) recommendedDInSize();
    }

    public ZstdInputStreamNoFinalizer(InputStream inputStream) {
        this(inputStream, e.f6035a);
    }

    public ZstdInputStreamNoFinalizer(InputStream inputStream, b bVar) {
        super(inputStream);
        this.dstPos = 0L;
        this.srcPos = 0L;
        this.srcSize = 0L;
        this.needRead = true;
        this.isContinuous = false;
        this.frameFinished = true;
        this.isClosed = false;
        this.bufferPool = bVar;
        int i2 = srcBuffSize;
        ByteBuffer a2 = bVar.a(i2);
        this.srcByteBuffer = a2;
        if (a2 == null) {
            throw new IOException("Cannot get ByteBuffer of size " + i2 + " from the BufferPool");
        }
        this.src = Zstd.extractArray(a2);
        synchronized (this) {
            long createDStream = createDStream();
            this.stream = createDStream;
            initDStream(createDStream);
        }
    }

    public synchronized ZstdInputStreamNoFinalizer setContinuous(boolean z) {
        this.isContinuous = z;
        return this;
    }

    public synchronized boolean getContinuous() {
        return this.isContinuous;
    }

    public synchronized ZstdInputStreamNoFinalizer setDict(byte[] bArr) {
        long loadDictDecompress;
        if (Zstd.isError(ZstdDecompress.loadDictDecompress(this.stream, bArr, bArr.length))) {
            throw new IOException("Decompression error: " + Zstd.getErrorName(loadDictDecompress));
        }
        return this;
    }

    public synchronized ZstdInputStreamNoFinalizer setDict(ZstdDictDecompress zstdDictDecompress) {
        long loadFastDictDecompress;
        zstdDictDecompress.acquireSharedLock();
        if (Zstd.isError(ZstdDecompress.loadFastDictDecompress(this.stream, zstdDictDecompress))) {
            throw new IOException("Decompression error: " + Zstd.getErrorName(loadFastDictDecompress));
        }
        zstdDictDecompress.releaseSharedLock();
        return this;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i2, int i3) {
        if (i2 >= 0) {
            if (i3 <= bArr.length - i2) {
                int i4 = 0;
                if (i3 == 0) {
                    return 0;
                }
                while (i4 == 0) {
                    i4 = readInternal(bArr, i2, i3);
                }
                return i4;
            }
        }
        throw new IndexOutOfBoundsException("Requested length " + i3 + " from offset " + i2 + " in buffer of size " + bArr.length);
    }

    int readInternal(byte[] bArr, int i2, int i3) {
        long j2;
        long j3;
        if (this.isClosed) {
            throw new IOException("Stream closed");
        }
        if (i2 < 0 || i3 > bArr.length - i2) {
            throw new IndexOutOfBoundsException("Requested length " + i3 + " from offset " + i2 + " in buffer of size " + bArr.length);
        }
        int i4 = i2 + i3;
        long j4 = i2;
        this.dstPos = j4;
        long j5 = -1;
        while (true) {
            j2 = this.dstPos;
            long j6 = i4;
            if (j2 >= j6 || j5 >= j2) {
                break;
            }
            if (this.needRead && (this.in.available() > 0 || this.dstPos == j4)) {
                long read = this.in.read(this.src, 0, srcBuffSize);
                this.srcSize = read;
                this.srcPos = 0L;
                if (read < 0) {
                    this.srcSize = 0L;
                    if (this.frameFinished) {
                        return -1;
                    }
                    if (this.isContinuous) {
                        long j7 = (int) (this.dstPos - j4);
                        this.srcSize = j7;
                        if (j7 > 0) {
                            return (int) j7;
                        }
                        return -1;
                    }
                    throw new IOException("Read error or truncated source");
                }
                this.frameFinished = false;
            }
            long j8 = this.dstPos;
            int decompressStream = decompressStream(this.stream, bArr, i4, this.src, (int) this.srcSize);
            if (Zstd.isError(decompressStream)) {
                throw new IOException("Decompression error: " + Zstd.getErrorName(j3));
            }
            if (decompressStream == 0) {
                this.frameFinished = true;
                this.needRead = this.srcPos == this.srcSize;
                return (int) (this.dstPos - j4);
            }
            if (this.dstPos < j6) {
                r14 = true;
            }
            this.needRead = r14;
            j5 = j8;
        }
        return (int) (j2 - j4);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        byte[] bArr = new byte[1];
        int i2 = 0;
        while (i2 == 0) {
            i2 = readInternal(bArr, 0, 1);
        }
        if (i2 == 1) {
            return bArr[0] & 255;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        if (this.isClosed) {
            throw new IOException("Stream closed");
        }
        if (this.needRead) {
            return this.in.available();
        }
        return 1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j2) {
        int read;
        if (this.isClosed) {
            throw new IOException("Stream closed");
        }
        if (j2 <= 0) {
            return 0L;
        }
        int recommendedDOutSize = (int) recommendedDOutSize();
        if (recommendedDOutSize > j2) {
            recommendedDOutSize = (int) j2;
        }
        ByteBuffer a2 = this.bufferPool.a(recommendedDOutSize);
        byte[] extractArray = Zstd.extractArray(a2);
        long j3 = j2;
        while (j3 > 0 && (read = read(extractArray, 0, (int) Math.min(recommendedDOutSize, j3))) >= 0) {
            j3 -= read;
        }
        this.bufferPool.a(a2);
        return j2 - j3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        this.bufferPool.a(this.srcByteBuffer);
        freeDStream(this.stream);
        this.in.close();
    }
}
