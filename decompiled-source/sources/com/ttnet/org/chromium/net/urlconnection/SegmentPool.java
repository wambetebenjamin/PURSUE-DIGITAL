package com.ttnet.org.chromium.net.urlconnection;

import com.ttnet.org.chromium.base.Logger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes3.dex */
public final class SegmentPool {
    static long byteCount = 0;
    static Segment next = null;
    private static long sMaxSize = 262144;
    private static boolean sSizeInited = false;
    private static final ReentrantReadWriteLock sSizeLock = new ReentrantReadWriteLock();
    private static final AtomicLong segmentAllocateTimes = new AtomicLong(0);
    private static final AtomicLong segmentReuseTimes = new AtomicLong(0);
    private static final AtomicLong segmentRequestTimes = new AtomicLong(0);

    private static long getSize() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSizeLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            sSizeInited = true;
            long j2 = sMaxSize;
            reentrantReadWriteLock.readLock().unlock();
            return j2;
        } catch (Throwable th) {
            sSizeLock.readLock().unlock();
            throw th;
        }
    }

    public static void setSize(long j2) {
        if (sSizeInited) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSizeLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            if (!sSizeInited) {
                sSizeInited = true;
                sMaxSize = j2;
            }
            sSizeInited = true;
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            sSizeLock.writeLock().unlock();
            throw th;
        }
    }

    private SegmentPool() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Segment take() {
        AtomicLong atomicLong;
        AtomicLong atomicLong2;
        synchronized (SegmentPool.class) {
            if (Logger.debug()) {
                segmentRequestTimes.incrementAndGet();
            }
            if (next != null) {
                if (Logger.debug()) {
                    segmentReuseTimes.incrementAndGet();
                }
                Segment segment = next;
                next = segment.next;
                segment.next = null;
                byteCount -= Segment.getSize();
                return segment;
            }
            if (Logger.debug()) {
                AtomicLong atomicLong3 = segmentAllocateTimes;
                atomicLong3.incrementAndGet();
                Logger.d("SegmentPool", new StringBuilder().append("state: ").append(segmentRequestTimes.get()).append(" ").append(atomicLong3).append(" ").append(segmentReuseTimes).append(" ").append(atomicLong2.get() / atomicLong.get()).toString());
            }
            return new Segment();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        synchronized (SegmentPool.class) {
            if (byteCount + Segment.getSize() > getSize()) {
                return false;
            }
            byteCount += Segment.getSize();
            segment.next = next;
            next = segment;
            return true;
        }
    }
}
