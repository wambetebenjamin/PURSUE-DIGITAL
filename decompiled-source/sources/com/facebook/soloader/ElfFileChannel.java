package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/* loaded from: classes8.dex */
public class ElfFileChannel implements ElfByteChannel {
    private FileChannel mFc;
    private File mFile;
    private FileInputStream mIs;

    public ElfFileChannel(File file) throws IOException {
        this.mFile = file;
        openChannel();
    }

    public void openChannel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.mFile);
        this.mIs = fileInputStream;
        this.mFc = fileInputStream.getChannel();
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long position() throws IOException {
        return this.mFc.position();
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel position(long j2) throws IOException {
        this.mFc.position(j2);
        return this;
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.mFc.read(byteBuffer);
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public int read(ByteBuffer byteBuffer, long j2) throws IOException {
        return this.mFc.read(byteBuffer, j2);
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public long size() throws IOException {
        return this.mFc.size();
    }

    @Override // com.facebook.soloader.ElfByteChannel
    public ElfByteChannel truncate(long j2) throws IOException {
        this.mFc.truncate(j2);
        return this;
    }

    @Override // com.facebook.soloader.ElfByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.mFc.write(byteBuffer);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mIs.close();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.mFc.isOpen();
    }
}
