package com.ttnet.org.chromium.net.urlconnection;

import com.ttnet.org.chromium.net.UploadDataProvider;
import com.ttnet.org.chromium.net.UploadDataSink;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class CronetBufferedOutputStream extends CronetOutputStream {
    private static final int INITIAL_BUFFER_SIZE = 16384;
    private ByteBuffer mBuffer;
    private boolean mConnected;
    private final CronetHttpURLConnection mConnection;
    private final int mInitialContentLength;
    private final UploadDataProvider mUploadDataProvider = new UploadDataProviderImpl();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.ttnet.org.chromium.net.urlconnection.CronetOutputStream
    public void checkReceivedEnoughContent() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetBufferedOutputStream(CronetHttpURLConnection cronetHttpURLConnection, long j2) {
        Objects.requireNonNull(cronetHttpURLConnection, "Argument connection cannot be null.");
        if (j2 > 2147483647L) {
            throw new IllegalArgumentException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2GB.");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("Content length < 0.");
        }
        this.mConnection = cronetHttpURLConnection;
        int i2 = (int) j2;
        this.mInitialContentLength = i2;
        this.mBuffer = ByteBuffer.allocate(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CronetBufferedOutputStream(CronetHttpURLConnection cronetHttpURLConnection) {
        Objects.requireNonNull(cronetHttpURLConnection);
        this.mConnection = cronetHttpURLConnection;
        this.mInitialContentLength = -1;
        this.mBuffer = ByteBuffer.allocate(16384);
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        checkNotClosed();
        ensureCanWrite(1);
        this.mBuffer.put((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        checkNotClosed();
        ensureCanWrite(i3);
        this.mBuffer.put(bArr, i2, i3);
    }

    private void ensureCanWrite(int i2) {
        if (this.mInitialContentLength != -1 && this.mBuffer.position() + i2 > this.mInitialContentLength) {
            throw new ProtocolException("exceeded content-length limit of " + this.mInitialContentLength + " bytes");
        }
        if (this.mConnected) {
            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for writing after connect");
        }
        if (this.mInitialContentLength == -1 && this.mBuffer.limit() - this.mBuffer.position() <= i2) {
            ByteBuffer allocate = ByteBuffer.allocate(Math.max(this.mBuffer.capacity() * 2, this.mBuffer.capacity() + i2));
            this.mBuffer.flip();
            allocate.put(this.mBuffer);
            this.mBuffer = allocate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.ttnet.org.chromium.net.urlconnection.CronetOutputStream
    public void setConnected() {
        this.mConnected = true;
        if (this.mBuffer.position() < this.mInitialContentLength) {
            throw new ProtocolException("Content received is less than Content-Length");
        }
        this.mBuffer.flip();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.ttnet.org.chromium.net.urlconnection.CronetOutputStream
    public UploadDataProvider getUploadDataProvider() {
        return this.mUploadDataProvider;
    }

    /* loaded from: classes3.dex */
    private class UploadDataProviderImpl extends UploadDataProvider {
        private UploadDataProviderImpl() {
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public long getLength() {
            if (CronetBufferedOutputStream.this.mInitialContentLength == -1) {
                return CronetBufferedOutputStream.this.mConnected ? CronetBufferedOutputStream.this.mBuffer.limit() : CronetBufferedOutputStream.this.mBuffer.position();
            }
            return CronetBufferedOutputStream.this.mInitialContentLength;
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (remaining < CronetBufferedOutputStream.this.mBuffer.remaining()) {
                byteBuffer.put(CronetBufferedOutputStream.this.mBuffer.array(), CronetBufferedOutputStream.this.mBuffer.position(), remaining);
                CronetBufferedOutputStream.this.mBuffer.position(CronetBufferedOutputStream.this.mBuffer.position() + remaining);
            } else {
                byteBuffer.put(CronetBufferedOutputStream.this.mBuffer);
            }
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void rewind(UploadDataSink uploadDataSink) {
            CronetBufferedOutputStream.this.mBuffer.position(0);
            uploadDataSink.onRewindSucceeded();
        }
    }
}
