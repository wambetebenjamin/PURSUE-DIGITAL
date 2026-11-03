package com.bytedance.compression.zstd;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
class ZstdOutputStreamNoFinalizer extends FilterOutputStream {
    private static final int dstSize;
    private final b bufferPool;
    private boolean closeFrameOnFlush;
    private final byte[] dst;
    private final ByteBuffer dstByteBuffer;
    private long dstPos;
    private boolean frameClosed;
    private boolean isClosed;
    private long srcPos;
    private final long stream;

    private native int compressStream(long j2, byte[] bArr, int i2, byte[] bArr2, int i3);

    private static native long createCStream();

    private native int endStream(long j2, byte[] bArr, int i2);

    private native int flushStream(long j2, byte[] bArr, int i2);

    private static native int freeCStream(long j2);

    public static native long recommendedCOutSize();

    private native int resetCStream(long j2);

    static {
        com.bytedance.compression.zstd.util.a.a();
        dstSize = (int) recommendedCOutSize();
    }

    public ZstdOutputStreamNoFinalizer(OutputStream outputStream, int i2) {
        this(outputStream, e.f6035a);
        ZstdCompress.setCompressionLevel(this.stream, i2);
    }

    public ZstdOutputStreamNoFinalizer(OutputStream outputStream) {
        this(outputStream, e.f6035a);
    }

    public ZstdOutputStreamNoFinalizer(OutputStream outputStream, b bVar, int i2) {
        this(outputStream, bVar);
        ZstdCompress.setCompressionLevel(this.stream, i2);
    }

    public ZstdOutputStreamNoFinalizer(OutputStream outputStream, b bVar) {
        super(outputStream);
        this.srcPos = 0L;
        this.dstPos = 0L;
        this.isClosed = false;
        this.closeFrameOnFlush = false;
        this.frameClosed = true;
        this.stream = createCStream();
        this.bufferPool = bVar;
        int i2 = dstSize;
        ByteBuffer a2 = bVar.a(i2);
        this.dstByteBuffer = a2;
        if (a2 == null) {
            throw new IOException("Cannot get ByteBuffer of size " + i2 + " from the BufferPool");
        }
        this.dst = Zstd.extractArray(a2);
    }

    public synchronized ZstdOutputStreamNoFinalizer setChecksum(boolean z) {
        long compressionChecksums;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.setCompressionChecksums(this.stream, z))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(compressionChecksums));
        }
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setLevel(int i2) {
        long compressionLevel;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.setCompressionLevel(this.stream, i2))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(compressionLevel));
        }
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setLong(int i2) {
        long compressionLong;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.setCompressionLong(this.stream, i2))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(compressionLong));
        }
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setWorkers(int i2) {
        long compressionWorkers;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.setCompressionWorkers(this.stream, i2))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(compressionWorkers));
        }
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setCloseFrameOnFlush(boolean z) {
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        this.closeFrameOnFlush = z;
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setDict(byte[] bArr) {
        long loadDictCompress;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.loadDictCompress(this.stream, bArr, bArr.length))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(loadDictCompress));
        }
        return this;
    }

    public synchronized ZstdOutputStreamNoFinalizer setDict(ZstdDictCompress zstdDictCompress) {
        long loadFastDictCompress;
        if (!this.frameClosed) {
            throw new IOException("Change of parameter on initialized stream");
        }
        if (Zstd.isError(ZstdCompress.loadFastDictCompress(this.stream, zstdDictCompress))) {
            throw new IOException("Compression param: " + Zstd.getErrorName(loadFastDictCompress));
        }
        return this;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] bArr, int i2, int i3) {
        long compressStream;
        long resetCStream;
        if (this.isClosed) {
            throw new IOException("Stream closed");
        }
        if (this.frameClosed) {
            if (Zstd.isError(resetCStream(this.stream))) {
                throw new IOException("Compression error: cannot create header: " + Zstd.getErrorName(resetCStream));
            }
            this.frameClosed = false;
        }
        int i4 = i3 + i2;
        this.srcPos = i2;
        while (this.srcPos < i4) {
            if (Zstd.isError(compressStream(this.stream, this.dst, dstSize, bArr, i4))) {
                throw new IOException("Compression error: " + Zstd.getErrorName(compressStream));
            }
            if (this.dstPos > 0) {
                this.out.write(this.dst, 0, (int) this.dstPos);
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        write(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public synchronized void flush() {
        int flushStream;
        long j2;
        int endStream;
        long j3;
        if (this.isClosed) {
            throw new IOException("Stream closed");
        }
        if (!this.frameClosed) {
            if (this.closeFrameOnFlush) {
                do {
                    endStream = endStream(this.stream, this.dst, dstSize);
                    if (Zstd.isError(endStream)) {
                        throw new IOException("Compression error: " + Zstd.getErrorName(j3));
                    }
                    this.out.write(this.dst, 0, (int) this.dstPos);
                } while (endStream > 0);
                this.frameClosed = true;
            } else {
                do {
                    flushStream = flushStream(this.stream, this.dst, dstSize);
                    if (Zstd.isError(flushStream)) {
                        throw new IOException("Compression error: " + Zstd.getErrorName(j2));
                    }
                    this.out.write(this.dst, 0, (int) this.dstPos);
                } while (flushStream > 0);
            }
            this.out.flush();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        close(true);
    }

    public synchronized void closeWithoutClosingParentStream() {
        close(false);
    }

    private void close(boolean z) {
        int endStream;
        long j2;
        if (this.isClosed) {
            return;
        }
        try {
            if (!this.frameClosed) {
                do {
                    endStream = endStream(this.stream, this.dst, dstSize);
                    if (Zstd.isError(endStream)) {
                        throw new IOException("Compression error: " + Zstd.getErrorName(j2));
                    }
                    this.out.write(this.dst, 0, (int) this.dstPos);
                } while (endStream > 0);
            }
            if (z) {
                this.out.close();
            }
        } finally {
            this.isClosed = true;
            this.bufferPool.a(this.dstByteBuffer);
            freeCStream(this.stream);
        }
    }
}
