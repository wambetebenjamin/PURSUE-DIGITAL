package com.ttnet.org.chromium.net.urlconnection;

import com.ttnet.org.chromium.base.Logger;
import java.io.EOFException;
/* loaded from: classes3.dex */
public final class TTBuffer {
    private Segment head = null;
    public int size = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment getWritableSegment() {
        Segment segment = this.head;
        if (segment == null) {
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment2 = segment.prev;
        if (!segment2.isWritable()) {
            segment2.push(SegmentPool.take());
        }
        return this.head.prev;
    }

    public byte get() {
        if (this.size <= 0) {
            return (byte) 0;
        }
        byte[] bArr = new byte[1];
        read(bArr, 0, 1);
        return bArr[0];
    }

    public int readToByteArray(byte[] bArr, int i2, int i3) {
        checkOffsetAndCount(bArr.length, i2, i3);
        int min = Math.min(this.size, i3);
        if (Logger.debug()) {
            Logger.d("TTBuffer", "start get buffer, size is " + this.size + " byteCount is " + i3);
        }
        int i4 = i2;
        while (min > 0) {
            int read = read(bArr, i4, min);
            if (read == -1) {
                throw new EOFException();
            }
            i4 += read;
            min -= read;
            if (Logger.debug()) {
                Logger.d("TTBuffer", "get segment buffer, n is " + read + " offset is " + i4 + " bytesRead is " + min);
            }
        }
        return i4 - i2;
    }

    private int read(byte[] bArr, int i2, int i3) {
        checkOffsetAndCount(bArr.length, i2, i3);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int innerBufferTo = segment.getInnerBufferTo(bArr, i2, i3);
        if (Logger.debug()) {
            Logger.d("TTBuffer", "head curReadLength is " + innerBufferTo);
        }
        this.size -= innerBufferTo;
        if (!segment.getInnerBuffer().hasRemaining()) {
            this.head = segment.release();
        }
        return innerBufferTo;
    }

    public void releaseAllSegments() {
        while (true) {
            Segment segment = this.head;
            if (segment == null) {
                return;
            }
            this.head = segment.release();
        }
    }

    public static void checkOffsetAndCount(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)));
        }
    }
}
