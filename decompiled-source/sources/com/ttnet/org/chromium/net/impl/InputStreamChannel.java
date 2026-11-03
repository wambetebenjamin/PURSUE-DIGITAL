package com.ttnet.org.chromium.net.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
final class InputStreamChannel implements ReadableByteChannel {
    private static final int MAX_TMP_BUFFER_SIZE = 16384;
    private static final int MIN_TMP_BUFFER_SIZE = 4096;
    private final InputStream mInputStream;
    private final AtomicBoolean mIsOpen = new AtomicBoolean(true);

    private InputStreamChannel(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadableByteChannel wrap(InputStream inputStream) {
        if (inputStream instanceof FileInputStream) {
            return ((FileInputStream) inputStream).getChannel();
        }
        return new InputStreamChannel(inputStream);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int read = this.mInputStream.read(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            if (read > 0) {
                byteBuffer.position(byteBuffer.position() + read);
                return read;
            }
            return read;
        }
        byte[] bArr = new byte[Math.min(16384, Math.min(Math.max(this.mInputStream.available(), 4096), byteBuffer.remaining()))];
        int read2 = this.mInputStream.read(bArr);
        if (read2 > 0) {
            byteBuffer.put(bArr, 0, read2);
        }
        return read2;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mIsOpen.get();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.mIsOpen.compareAndSet(true, false)) {
            this.mInputStream.close();
        }
    }
}
