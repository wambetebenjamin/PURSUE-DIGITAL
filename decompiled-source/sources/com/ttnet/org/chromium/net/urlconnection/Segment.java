package com.ttnet.org.chromium.net.urlconnection;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes3.dex */
public final class Segment {
    private static int sSize = 32768;
    private static boolean sSizeInited = false;
    private static final ReentrantReadWriteLock sSizeLock = new ReentrantReadWriteLock();
    private ByteBuffer bb;
    private boolean bufferFlipped = false;
    Segment next;
    Segment prev;

    public static int getSize() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSizeLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            sSizeInited = true;
            int i2 = sSize;
            reentrantReadWriteLock.readLock().unlock();
            return i2;
        } catch (Throwable th) {
            sSizeLock.readLock().unlock();
            throw th;
        }
    }

    public static void setSize(int i2) {
        if (sSizeInited) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSizeLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            if (!sSizeInited) {
                sSizeInited = true;
                sSize = i2;
            }
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            sSizeLock.writeLock().unlock();
            throw th;
        }
    }

    public ByteBuffer getInnerBuffer() {
        if (this.bb == null) {
            this.bb = ByteBuffer.allocateDirect(getSize());
        }
        return this.bb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment() {
        getInnerBuffer();
    }

    public Segment pop() {
        Segment segment = this.next;
        Segment segment2 = segment != this ? segment : null;
        Segment segment3 = this.prev;
        segment3.next = segment;
        this.next.prev = segment3;
        this.next = null;
        this.prev = null;
        return segment2;
    }

    public Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public void completeRead() {
        this.bb.flip();
        this.bufferFlipped = true;
    }

    public Segment release() {
        this.bb.clear();
        this.bufferFlipped = false;
        Segment pop = pop();
        if (!SegmentPool.recycle(this)) {
            this.bb = null;
        }
        return pop;
    }

    public boolean isWritable() {
        return !this.bufferFlipped && this.bb.hasRemaining();
    }

    public int getInnerBufferTo(byte[] bArr, int i2, int i3) {
        if (this.bufferFlipped) {
            int min = Math.min(this.bb.remaining(), i3);
            this.bb.get(bArr, i2, min);
            return min;
        }
        return -1;
    }
}
