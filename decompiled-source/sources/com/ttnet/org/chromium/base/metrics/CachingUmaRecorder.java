package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.Callback;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.build.BuildConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class CachingUmaRecorder implements UmaRecorder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_HISTOGRAM_COUNT = 256;
    static final int MAX_USER_ACTION_COUNT = 256;
    private static final String TAG = "CachingUmaRecorder";
    private UmaRecorder mDelegate;
    private int mDroppedUserActionCount;
    private List<Callback<String>> mUserActionCallbacksForTesting;
    private final ReentrantReadWriteLock mRwLock = new ReentrantReadWriteLock(false);
    private Map<String, Histogram> mHistogramByName = new HashMap();
    private AtomicInteger mDroppedHistogramSampleCount = new AtomicInteger();
    private List<UserAction> mUserActions = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class Histogram {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int MAX_SAMPLE_COUNT = 256;
        private final int mMax;
        private final int mMin;
        private final String mName;
        private final int mNumBuckets;
        private final List<Integer> mSamples = new ArrayList(1);
        private final int mType;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes9.dex */
        @interface Type {
            public static final int BOOLEAN = 1;
            public static final int EXPONENTIAL = 2;
            public static final int LINEAR = 3;
            public static final int SPARSE = 4;
        }

        Histogram(int i2, String str, int i3, int i4, int i5) {
            this.mType = i2;
            this.mName = str;
            this.mMin = i3;
            this.mMax = i4;
            this.mNumBuckets = i5;
        }

        synchronized boolean addSample(int i2, String str, int i3, int i4, int i5, int i6) {
            if (this.mSamples.size() >= 256) {
                return false;
            }
            this.mSamples.add(Integer.valueOf(i3));
            return true;
        }

        synchronized int flushTo(UmaRecorder umaRecorder) {
            int size;
            int i2 = this.mType;
            int i3 = 0;
            if (i2 == 1) {
                for (int i4 = 0; i4 < this.mSamples.size(); i4++) {
                    umaRecorder.recordBooleanHistogram(this.mName, this.mSamples.get(i4).intValue() != 0);
                }
            } else if (i2 == 2) {
                while (i3 < this.mSamples.size()) {
                    umaRecorder.recordExponentialHistogram(this.mName, this.mSamples.get(i3).intValue(), this.mMin, this.mMax, this.mNumBuckets);
                    i3++;
                }
            } else if (i2 == 3) {
                while (i3 < this.mSamples.size()) {
                    umaRecorder.recordLinearHistogram(this.mName, this.mSamples.get(i3).intValue(), this.mMin, this.mMax, this.mNumBuckets);
                    i3++;
                }
            } else if (i2 == 4) {
                while (i3 < this.mSamples.size()) {
                    umaRecorder.recordSparseHistogram(this.mName, this.mSamples.get(i3).intValue());
                    i3++;
                }
            }
            size = this.mSamples.size();
            this.mSamples.clear();
            return size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class UserAction {
        private final long mElapsedRealtimeMillis;
        private final String mName;

        UserAction(String str, long j2) {
            this.mName = str;
            this.mElapsedRealtimeMillis = j2;
        }

        void flushTo(UmaRecorder umaRecorder) {
            umaRecorder.recordUserAction(this.mName, this.mElapsedRealtimeMillis);
        }
    }

    public UmaRecorder setDelegate(UmaRecorder umaRecorder) {
        Map<String, Histogram> map;
        int i2;
        this.mRwLock.writeLock().lock();
        try {
            UmaRecorder umaRecorder2 = this.mDelegate;
            this.mDelegate = umaRecorder;
            if (BuildConfig.IS_FOR_TEST) {
                swapUserActionCallbacksForTesting(umaRecorder2, umaRecorder);
            }
            if (umaRecorder == null) {
                return umaRecorder2;
            }
            List<UserAction> list = null;
            int i3 = 0;
            if (this.mHistogramByName.isEmpty()) {
                map = null;
                i2 = 0;
            } else {
                map = this.mHistogramByName;
                this.mHistogramByName = new HashMap();
                i2 = this.mDroppedHistogramSampleCount.getAndSet(0);
            }
            if (!this.mUserActions.isEmpty()) {
                list = this.mUserActions;
                this.mUserActions = new ArrayList();
                int i4 = this.mDroppedUserActionCount;
                this.mDroppedUserActionCount = 0;
                i3 = i4;
            }
            this.mRwLock.readLock().lock();
            if (map != null) {
                try {
                    flushHistogramsAlreadyLocked(map, i2);
                } catch (Throwable th) {
                    this.mRwLock.readLock().unlock();
                    throw th;
                }
            }
            if (list != null) {
                flushUserActionsAlreadyLocked(list, i3);
            }
            this.mRwLock.readLock().unlock();
            return umaRecorder2;
        } finally {
            this.mRwLock.writeLock().unlock();
        }
    }

    private void flushHistogramsAlreadyLocked(Map<String, Histogram> map, int i2) {
        int size = map.size();
        int i3 = 0;
        for (Histogram histogram : map.values()) {
            i3 += histogram.flushTo(this.mDelegate);
        }
        Log.i(TAG, "Flushed %d samples from %d histograms.", Integer.valueOf(i3), Integer.valueOf(size));
        this.mDelegate.recordExponentialHistogram("UMA.JavaCachingRecorder.DroppedHistogramSampleCount", i2, 1, 1000000, 50);
        this.mDelegate.recordExponentialHistogram("UMA.JavaCachingRecorder.FlushedHistogramCount", size, 1, 100000, 50);
        this.mDelegate.recordExponentialHistogram("UMA.JavaCachingRecorder.InputHistogramSampleCount", i3 + i2, 1, 1000000, 50);
    }

    private void flushUserActionsAlreadyLocked(List<UserAction> list, int i2) {
        for (UserAction userAction : list) {
            userAction.flushTo(this.mDelegate);
        }
        this.mDelegate.recordExponentialHistogram("UMA.JavaCachingRecorder.DroppedUserActionCount", i2, 1, 1000, 50);
        this.mDelegate.recordExponentialHistogram("UMA.JavaCachingRecorder.InputUserActionCount", list.size() + i2, 1, 10000, 50);
    }

    private void cacheOrRecordHistogramSample(int i2, String str, int i3, int i4, int i5, int i6) {
        if (tryAppendOrRecordSample(i2, str, i3, i4, i5, i6)) {
            return;
        }
        this.mRwLock.writeLock().lock();
        try {
            if (this.mDelegate == null) {
                cacheHistogramSampleAlreadyWriteLocked(i2, str, i3, i4, i5, i6);
                return;
            }
            this.mRwLock.readLock().lock();
            try {
                recordHistogramSampleAlreadyLocked(i2, str, i3, i4, i5, i6);
            } finally {
                this.mRwLock.readLock().unlock();
            }
        } finally {
            this.mRwLock.writeLock().unlock();
        }
    }

    private boolean tryAppendOrRecordSample(int i2, String str, int i3, int i4, int i5, int i6) {
        this.mRwLock.readLock().lock();
        try {
            if (this.mDelegate != null) {
                recordHistogramSampleAlreadyLocked(i2, str, i3, i4, i5, i6);
            } else {
                Histogram histogram = this.mHistogramByName.get(str);
                if (histogram != null) {
                    if (!histogram.addSample(i2, str, i3, i4, i5, i6)) {
                        this.mDroppedHistogramSampleCount.incrementAndGet();
                    }
                } else {
                    return false;
                }
            }
            return true;
        } finally {
            this.mRwLock.readLock().unlock();
        }
    }

    private void cacheHistogramSampleAlreadyWriteLocked(int i2, String str, int i3, int i4, int i5, int i6) {
        Histogram histogram = this.mHistogramByName.get(str);
        if (histogram == null) {
            if (this.mHistogramByName.size() >= 256) {
                this.mDroppedHistogramSampleCount.incrementAndGet();
                return;
            }
            Histogram histogram2 = new Histogram(i2, str, i4, i5, i6);
            this.mHistogramByName.put(str, histogram2);
            histogram = histogram2;
        }
        if (histogram.addSample(i2, str, i3, i4, i5, i6)) {
            return;
        }
        this.mDroppedHistogramSampleCount.incrementAndGet();
    }

    private void recordHistogramSampleAlreadyLocked(int i2, String str, int i3, int i4, int i5, int i6) {
        if (i2 == 1) {
            this.mDelegate.recordBooleanHistogram(str, i3 != 0);
        } else if (i2 == 2) {
            this.mDelegate.recordExponentialHistogram(str, i3, i4, i5, i6);
        } else if (i2 == 3) {
            this.mDelegate.recordLinearHistogram(str, i3, i4, i5, i6);
        } else if (i2 == 4) {
            this.mDelegate.recordSparseHistogram(str, i3);
        } else {
            throw new UnsupportedOperationException("Unknown histogram type " + i2);
        }
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordBooleanHistogram(String str, boolean z) {
        cacheOrRecordHistogramSample(1, str, z ? 1 : 0, 0, 0, 0);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordExponentialHistogram(String str, int i2, int i3, int i4, int i5) {
        cacheOrRecordHistogramSample(2, str, i2, i3, i4, i5);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordLinearHistogram(String str, int i2, int i3, int i4, int i5) {
        cacheOrRecordHistogramSample(3, str, i2, i3, i4, i5);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordSparseHistogram(String str, int i2) {
        cacheOrRecordHistogramSample(4, str, i2, 0, 0, 0);
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void recordUserAction(String str, long j2) {
        this.mRwLock.readLock().lock();
        try {
            UmaRecorder umaRecorder = this.mDelegate;
            if (umaRecorder != null) {
                umaRecorder.recordUserAction(str, j2);
                return;
            }
            this.mRwLock.readLock().unlock();
            this.mRwLock.writeLock().lock();
            try {
                if (this.mDelegate == null) {
                    if (this.mUserActions.size() < 256) {
                        this.mUserActions.add(new UserAction(str, j2));
                    } else {
                        this.mDroppedUserActionCount++;
                    }
                    if (this.mUserActionCallbacksForTesting != null) {
                        for (int i2 = 0; i2 < this.mUserActionCallbacksForTesting.size(); i2++) {
                            this.mUserActionCallbacksForTesting.get(i2).onResult(str);
                        }
                    }
                    return;
                }
                this.mRwLock.readLock().lock();
                try {
                    this.mDelegate.recordUserAction(str, j2);
                } finally {
                }
            } finally {
                this.mRwLock.writeLock().unlock();
            }
        } finally {
        }
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramValueCountForTesting(String str, int i2) {
        int i3;
        this.mRwLock.readLock().lock();
        try {
            UmaRecorder umaRecorder = this.mDelegate;
            if (umaRecorder != null) {
                return umaRecorder.getHistogramValueCountForTesting(str, i2);
            }
            Histogram histogram = this.mHistogramByName.get(str);
            if (histogram == null) {
                return 0;
            }
            synchronized (histogram) {
                i3 = 0;
                for (int i4 = 0; i4 < histogram.mSamples.size(); i4++) {
                    if (((Integer) histogram.mSamples.get(i4)).intValue() == i2) {
                        i3++;
                    }
                }
            }
            return i3;
        } finally {
            this.mRwLock.readLock().unlock();
        }
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public int getHistogramTotalCountForTesting(String str) {
        int size;
        int i2;
        this.mRwLock.readLock().lock();
        try {
            UmaRecorder umaRecorder = this.mDelegate;
            if (umaRecorder != null) {
                i2 = umaRecorder.getHistogramTotalCountForTesting(str);
            } else {
                Histogram histogram = this.mHistogramByName.get(str);
                if (histogram != null) {
                    synchronized (histogram) {
                        size = histogram.mSamples.size();
                    }
                    return size;
                }
                i2 = 0;
            }
            return i2;
        } finally {
            this.mRwLock.readLock().unlock();
        }
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void addUserActionCallbackForTesting(Callback<String> callback) {
        this.mRwLock.writeLock().lock();
        try {
            if (this.mUserActionCallbacksForTesting == null) {
                this.mUserActionCallbacksForTesting = new ArrayList();
            }
            this.mUserActionCallbacksForTesting.add(callback);
            UmaRecorder umaRecorder = this.mDelegate;
            if (umaRecorder != null) {
                umaRecorder.addUserActionCallbackForTesting(callback);
            }
        } finally {
            this.mRwLock.writeLock().unlock();
        }
    }

    @Override // com.ttnet.org.chromium.base.metrics.UmaRecorder
    public void removeUserActionCallbackForTesting(Callback<String> callback) {
        this.mRwLock.writeLock().lock();
        try {
            List<Callback<String>> list = this.mUserActionCallbacksForTesting;
            if (list == null) {
                return;
            }
            list.remove(callback);
            UmaRecorder umaRecorder = this.mDelegate;
            if (umaRecorder != null) {
                umaRecorder.removeUserActionCallbackForTesting(callback);
            }
        } finally {
            this.mRwLock.writeLock().unlock();
        }
    }

    private void swapUserActionCallbacksForTesting(UmaRecorder umaRecorder, UmaRecorder umaRecorder2) {
        if (this.mUserActionCallbacksForTesting == null) {
            return;
        }
        for (int i2 = 0; i2 < this.mUserActionCallbacksForTesting.size(); i2++) {
            if (umaRecorder != null) {
                umaRecorder.removeUserActionCallbackForTesting(this.mUserActionCallbacksForTesting.get(i2));
            }
            if (umaRecorder2 != null) {
                umaRecorder2.addUserActionCallbackForTesting(this.mUserActionCallbacksForTesting.get(i2));
            }
        }
    }
}
