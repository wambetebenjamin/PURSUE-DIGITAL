package com.ttnet.org.chromium.net;

import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/* loaded from: classes9.dex */
public final class UploadDataProviders {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface FileChannelProvider {
        FileChannel getChannel();
    }

    public static UploadDataProvider create(final File file) {
        return new FileUploadProvider(new FileChannelProvider() { // from class: com.ttnet.org.chromium.net.UploadDataProviders.1
            @Override // com.ttnet.org.chromium.net.UploadDataProviders.FileChannelProvider
            public FileChannel getChannel() {
                return new FileInputStream(file).getChannel();
            }
        });
    }

    public static UploadDataProvider create(final ParcelFileDescriptor parcelFileDescriptor) {
        return new FileUploadProvider(new FileChannelProvider() { // from class: com.ttnet.org.chromium.net.UploadDataProviders.2
            @Override // com.ttnet.org.chromium.net.UploadDataProviders.FileChannelProvider
            public FileChannel getChannel() {
                if (parcelFileDescriptor.getStatSize() != -1) {
                    return new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor).getChannel();
                }
                parcelFileDescriptor.close();
                throw new IllegalArgumentException("Not a file: " + parcelFileDescriptor);
            }
        });
    }

    public static UploadDataProvider create(ByteBuffer byteBuffer) {
        return new ByteBufferUploadProvider(byteBuffer.slice());
    }

    public static UploadDataProvider create(byte[] bArr, int i2, int i3) {
        return new ByteBufferUploadProvider(ByteBuffer.wrap(bArr, i2, i3).slice());
    }

    public static UploadDataProvider create(byte[] bArr) {
        return create(bArr, 0, bArr.length);
    }

    /* loaded from: classes9.dex */
    private static final class FileUploadProvider extends UploadDataProvider {
        private volatile FileChannel mChannel;
        private final Object mLock;
        private final FileChannelProvider mProvider;

        private FileUploadProvider(FileChannelProvider fileChannelProvider) {
            this.mLock = new Object();
            this.mProvider = fileChannelProvider;
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public long getLength() {
            return getChannel().size();
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            if (!byteBuffer.hasRemaining()) {
                throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
            }
            FileChannel channel = getChannel();
            int i2 = 0;
            while (i2 == 0) {
                int read = channel.read(byteBuffer);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void rewind(UploadDataSink uploadDataSink) {
            getChannel().position(0L);
            uploadDataSink.onRewindSucceeded();
        }

        private FileChannel getChannel() {
            if (this.mChannel == null) {
                synchronized (this.mLock) {
                    if (this.mChannel == null) {
                        this.mChannel = this.mProvider.getChannel();
                    }
                }
            }
            return this.mChannel;
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            FileChannel fileChannel = this.mChannel;
            if (fileChannel != null) {
                fileChannel.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ByteBufferUploadProvider extends UploadDataProvider {
        private final ByteBuffer mUploadBuffer;

        private ByteBufferUploadProvider(ByteBuffer byteBuffer) {
            this.mUploadBuffer = byteBuffer;
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public long getLength() {
            return this.mUploadBuffer.limit();
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            if (!byteBuffer.hasRemaining()) {
                throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
            }
            if (byteBuffer.remaining() >= this.mUploadBuffer.remaining()) {
                byteBuffer.put(this.mUploadBuffer);
            } else {
                int limit = this.mUploadBuffer.limit();
                ByteBuffer byteBuffer2 = this.mUploadBuffer;
                byteBuffer2.limit(byteBuffer2.position() + byteBuffer.remaining());
                byteBuffer.put(this.mUploadBuffer);
                this.mUploadBuffer.limit(limit);
            }
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // com.ttnet.org.chromium.net.UploadDataProvider
        public void rewind(UploadDataSink uploadDataSink) {
            this.mUploadBuffer.position(0);
            uploadDataSink.onRewindSucceeded();
        }
    }

    private UploadDataProviders() {
    }
}
