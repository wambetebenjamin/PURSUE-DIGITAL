package com.ttnet.org.chromium.net.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class CronetInputStream extends InputStream {
    private static final int READ_BUFFER_SIZE = 32768;
    private static final int READ_BUFFER_SIZE_MIN = 8192;
    private ByteBuffer mBuffer;
    protected IOException mException;
    protected final CronetHttpURLConnection mHttpURLConnection;
    private boolean mResponseDataCompleted;

    public CronetInputStream(CronetHttpURLConnection cronetHttpURLConnection) {
        this.mHttpURLConnection = cronetHttpURLConnection;
    }

    @Override // java.io.InputStream
    public int read() {
        getMoreDataIfNeeded();
        if (hasUnreadData()) {
            return this.mBuffer.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (i2 < 0 || i3 < 0 || i2 + i3 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return 0;
        }
        getMoreDataIfNeeded();
        if (hasUnreadData()) {
            int min = Math.min(this.mBuffer.limit() - this.mBuffer.position(), i3);
            this.mBuffer.get(bArr, i2, min);
            return min;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int available() {
        if (this.mResponseDataCompleted) {
            IOException iOException = this.mException;
            if (iOException == null) {
                return 0;
            }
            throw iOException;
        } else if (hasUnreadData()) {
            return this.mBuffer.remaining();
        } else {
            return 0;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CronetHttpURLConnection cronetHttpURLConnection = this.mHttpURLConnection;
        if (cronetHttpURLConnection != null) {
            cronetHttpURLConnection.disconnect();
        }
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResponseDataCompleted(IOException iOException) {
        this.mException = iOException;
        this.mResponseDataCompleted = true;
        this.mBuffer = null;
    }

    private void getMoreDataIfNeeded() {
        if (this.mResponseDataCompleted) {
            IOException iOException = this.mException;
            if (iOException != null) {
                throw iOException;
            }
        } else if (hasUnreadData()) {
        } else {
            if (this.mBuffer == null) {
                this.mBuffer = ByteBuffer.allocateDirect(this.mHttpURLConnection.getInputStreamBufferSize() >= 8192 ? this.mHttpURLConnection.getInputStreamBufferSize() : 32768);
            }
            this.mBuffer.clear();
            this.mHttpURLConnection.getMoreData(this.mBuffer);
            IOException iOException2 = this.mException;
            if (iOException2 != null) {
                throw iOException2;
            }
            ByteBuffer byteBuffer = this.mBuffer;
            if (byteBuffer != null) {
                byteBuffer.flip();
            }
        }
    }

    private boolean hasUnreadData() {
        ByteBuffer byteBuffer = this.mBuffer;
        return byteBuffer != null && byteBuffer.hasRemaining();
    }
}
