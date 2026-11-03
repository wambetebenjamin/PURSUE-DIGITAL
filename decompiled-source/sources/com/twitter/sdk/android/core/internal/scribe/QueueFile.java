package com.twitter.sdk.android.core.internal.scribe;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes10.dex */
public class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    /* loaded from: classes10.dex */
    public interface ElementReader {
        void read(InputStream inputStream, int i2) throws IOException;
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    QueueFile(RandomAccessFile randomAccessFile) throws IOException {
        this.buffer = new byte[16];
        this.raf = randomAccessFile;
        readHeader();
    }

    private static void writeInt(byte[] bArr, int i2, int i3) {
        bArr[i2] = (byte) (i3 >> 24);
        bArr[i2 + 1] = (byte) (i3 >> 16);
        bArr[i2 + 2] = (byte) (i3 >> 8);
        bArr[i2 + 3] = (byte) i3;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            writeInt(bArr, i2, i3);
            i2 += 4;
        }
    }

    private static int readInt(byte[] bArr, int i2) {
        return ((bArr[i2] & 255) << 24) + ((bArr[i2 + 1] & 255) << 16) + ((bArr[i2 + 2] & 255) << 8) + (bArr[i2 + 3] & 255);
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int readInt = readInt(this.buffer, 0);
        this.fileLength = readInt;
        if (readInt > this.raf.length()) {
            throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
        }
        this.elementCount = readInt(this.buffer, 4);
        int readInt2 = readInt(this.buffer, 8);
        int readInt3 = readInt(this.buffer, 12);
        this.first = readElement(readInt2);
        this.last = readElement(readInt3);
    }

    private void writeHeader(int i2, int i3, int i4, int i5) throws IOException {
        writeInts(this.buffer, i2, i3, i4, i5);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private Element readElement(int i2) throws IOException {
        if (i2 == 0) {
            return Element.NULL;
        }
        this.raf.seek(i2);
        return new Element(i2, this.raf.readInt());
    }

    private static void initialize(File file) throws IOException {
        File file2 = new File(file.getPath() + DefaultDiskStorage.FileType.TEMP);
        RandomAccessFile open = open(file2);
        try {
            open.setLength(4096L);
            open.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            open.write(bArr);
            open.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            open.close();
            throw th;
        }
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int i2) {
        int i3 = this.fileLength;
        return i2 < i3 ? i2 : (i2 + 16) - i3;
    }

    private void ringWrite(int i2, byte[] bArr, int i3, int i4) throws IOException {
        int wrapPosition = wrapPosition(i2);
        int i5 = wrapPosition + i4;
        int i6 = this.fileLength;
        if (i5 <= i6) {
            this.raf.seek(wrapPosition);
            this.raf.write(bArr, i3, i4);
            return;
        }
        int i7 = i6 - wrapPosition;
        this.raf.seek(wrapPosition);
        this.raf.write(bArr, i3, i7);
        this.raf.seek(16L);
        this.raf.write(bArr, i3 + i7, i4 - i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int i2, byte[] bArr, int i3, int i4) throws IOException {
        int wrapPosition = wrapPosition(i2);
        int i5 = wrapPosition + i4;
        int i6 = this.fileLength;
        if (i5 <= i6) {
            this.raf.seek(wrapPosition);
            this.raf.readFully(bArr, i3, i4);
            return;
        }
        int i7 = i6 - wrapPosition;
        this.raf.seek(wrapPosition);
        this.raf.readFully(bArr, i3, i7);
        this.raf.seek(16L);
        this.raf.readFully(bArr, i3 + i7, i4 - i7);
    }

    public void add(byte[] bArr) throws IOException {
        add(bArr, 0, bArr.length);
    }

    public synchronized void add(byte[] bArr, int i2, int i3) throws IOException {
        nonNull(bArr, "buffer");
        if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary(i3);
        boolean isEmpty = isEmpty();
        Element element = new Element(isEmpty ? 16 : wrapPosition(this.last.position + 4 + this.last.length), i3);
        writeInt(this.buffer, 0, i3);
        ringWrite(element.position, this.buffer, 0, 4);
        ringWrite(element.position + 4, bArr, i2, i3);
        writeHeader(this.fileLength, this.elementCount + 1, isEmpty ? element.position : this.first.position, element.position);
        this.last = element;
        this.elementCount++;
        if (isEmpty) {
            this.first = element;
        }
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        if (this.last.position >= this.first.position) {
            return (this.last.position - this.first.position) + 4 + this.last.length + 16;
        }
        return (((this.last.position + 4) + this.last.length) + this.fileLength) - this.first.position;
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    private void expandIfNecessary(int i2) throws IOException {
        int i3 = i2 + 4;
        int remainingBytes = remainingBytes();
        if (remainingBytes >= i3) {
            return;
        }
        int i4 = this.fileLength;
        do {
            remainingBytes += i4;
            i4 <<= 1;
        } while (remainingBytes < i3);
        setLength(i4);
        int wrapPosition = wrapPosition(this.last.position + 4 + this.last.length);
        if (wrapPosition < this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            long j2 = wrapPosition - 4;
            if (channel.transferTo(16L, j2, channel) != j2) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        if (this.last.position < this.first.position) {
            int i5 = (this.fileLength + this.last.position) - 16;
            writeHeader(i4, this.elementCount, this.first.position, i5);
            this.last = new Element(i5, this.last.length);
        } else {
            writeHeader(i4, this.elementCount, this.first.position, this.last.position);
        }
        this.fileLength = i4;
    }

    private void setLength(int i2) throws IOException {
        this.raf.setLength(i2);
        this.raf.getChannel().force(true);
    }

    public synchronized byte[] peek() throws IOException {
        if (isEmpty()) {
            return null;
        }
        int i2 = this.first.length;
        byte[] bArr = new byte[i2];
        ringRead(this.first.position + 4, bArr, 0, i2);
        return bArr;
    }

    public synchronized void peek(ElementReader elementReader) throws IOException {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.length);
        }
    }

    public synchronized void forEach(ElementReader elementReader) throws IOException {
        int i2 = this.first.position;
        for (int i3 = 0; i3 < this.elementCount; i3++) {
            Element readElement = readElement(i2);
            elementReader.read(new ElementInputStream(readElement), readElement.length);
            i2 = wrapPosition(readElement.position + 4 + readElement.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            QueueFile.nonNull(bArr, "buffer");
            if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = this.remaining;
            if (i4 > 0) {
                if (i3 > i4) {
                    i3 = i4;
                }
                QueueFile.this.ringRead(this.position, bArr, i2, i3);
                this.position = QueueFile.this.wrapPosition(this.position + i3);
                this.remaining -= i3;
                return i3;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int read = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return read;
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public synchronized void remove() throws IOException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.elementCount == 1) {
            clear();
        } else {
            int wrapPosition = wrapPosition(this.first.position + 4 + this.first.length);
            ringRead(wrapPosition, this.buffer, 0, 4);
            int readInt = readInt(this.buffer, 0);
            writeHeader(this.fileLength, this.elementCount - 1, wrapPosition, this.last.position);
            this.elementCount--;
            this.first = new Element(wrapPosition, readInt);
        }
    }

    public synchronized void clear() throws IOException {
        writeHeader(4096, 0, 0, 0);
        this.elementCount = 0;
        this.first = Element.NULL;
        this.last = Element.NULL;
        if (this.fileLength > 4096) {
            setLength(4096);
        }
        this.fileLength = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.raf.close();
    }

    public boolean hasSpaceFor(int i2, int i3) {
        return (usedBytes() + 4) + i2 <= i3;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('[');
        sb.append("fileLength=").append(this.fileLength);
        sb.append(", size=").append(this.elementCount);
        sb.append(", first=").append(this.first);
        sb.append(", last=").append(this.last);
        sb.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: com.twitter.sdk.android.core.internal.scribe.QueueFile.1
                boolean first = true;

                @Override // com.twitter.sdk.android.core.internal.scribe.QueueFile.ElementReader
                public void read(InputStream inputStream, int i2) throws IOException {
                    if (this.first) {
                        this.first = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i2);
                }
            });
        } catch (IOException e2) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e2);
        }
        sb.append("]]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        Element(int i2, int i3) {
            this.position = i2;
            this.length = i3;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }
}
