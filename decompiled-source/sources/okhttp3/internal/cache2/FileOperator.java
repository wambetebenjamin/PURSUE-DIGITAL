package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import okio.Buffer;
/* loaded from: classes10.dex */
final class FileOperator {
    private final FileChannel fileChannel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void write(long j2, Buffer buffer, long j3) throws IOException {
        if (j3 < 0 || j3 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j3 > 0) {
            long transferFrom = this.fileChannel.transferFrom(buffer, j2, j3);
            j2 += transferFrom;
            j3 -= transferFrom;
        }
    }

    public void read(long j2, Buffer buffer, long j3) throws IOException {
        if (j3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j3 > 0) {
            long transferTo = this.fileChannel.transferTo(j2, j3, buffer);
            j2 += transferTo;
            j3 -= transferTo;
        }
    }
}
