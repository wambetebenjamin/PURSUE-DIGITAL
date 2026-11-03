package com.facebook.soloader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class ElfZipFileChannel implements ElfByteChannel {
    @Nullable
    private InputStream mIs;
    private final long mLength;
    private boolean mOpened = true;
    private long mPos = 0;
    private final ZipEntry mZipEntry;
    private final ZipFile mZipFile;

    public ElfZipFileChannel(ZipFile zipFile, ZipEntry zipEntry) throws IOException {
        this.mZipFile = zipFile;
        this.mZipEntry = zipEntry;
        this.mLength = zipEntry.getSize();
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        this.mIs = inputStream;
        if (inputStream == null) {
            throw new IOException(zipEntry.getName() + "'s InputStream is null");
        }
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long position() throws IOException {
        return this.mPos;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel position(long j2) throws IOException {
        InputStream inputStream = this.mIs;
        if (inputStream == null) {
            throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
        }
        long j3 = this.mPos;
        if (j2 == j3) {
            return this;
        }
        long j4 = this.mLength;
        if (j2 > j4) {
            j2 = j4;
        }
        if (j2 >= j3) {
            inputStream.skip(j2 - j3);
        } else {
            inputStream.close();
            InputStream inputStream2 = this.mZipFile.getInputStream(this.mZipEntry);
            this.mIs = inputStream2;
            if (inputStream2 == null) {
                throw new IOException(this.mZipEntry.getName() + "'s InputStream is null");
            }
            inputStream2.skip(j2);
        }
        this.mPos = j2;
        return this;
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        return read(byteBuffer, this.mPos);
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public int read(ByteBuffer byteBuffer, long j2) throws IOException {
        if (this.mIs == null) {
            throw new IOException("InputStream is null");
        }
        int remaining = byteBuffer.remaining();
        long j3 = this.mLength - j2;
        if (j3 <= 0) {
            return -1;
        }
        int i2 = (int) j3;
        if (remaining > i2) {
            remaining = i2;
        }
        position(j2);
        if (byteBuffer.hasArray()) {
            this.mIs.read(byteBuffer.array(), 0, remaining);
            byteBuffer.position(byteBuffer.position() + remaining);
        } else {
            byte[] bArr = new byte[remaining];
            this.mIs.read(bArr, 0, remaining);
            byteBuffer.put(bArr, 0, remaining);
        }
        this.mPos += remaining;
        return remaining;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long size() throws IOException {
        return this.mLength;
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel truncate(long j2) throws IOException {
        throw new UnsupportedOperationException("ElfZipFileChannel doesn't support truncate");
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        throw new UnsupportedOperationException("ElfZipFileChannel doesn't support write");
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mOpened;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.mIs;
        if (inputStream != null) {
            inputStream.close();
            this.mOpened = false;
        }
    }
}
