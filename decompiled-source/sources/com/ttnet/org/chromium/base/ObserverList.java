package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.ThreadUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/* loaded from: classes9.dex */
public class ObserverList<E> implements Iterable<E> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int mCount;
    private int mIterationDepth;
    private boolean mNeedsCompact;
    public final List<E> mObservers = new ArrayList();
    private boolean mEnableThreadAsserts = true;
    private final ThreadUtils.ThreadChecker mThreadChecker = new ThreadUtils.ThreadChecker();

    /* loaded from: classes9.dex */
    public interface RewindableIterator<E> extends Iterator<E> {
        void rewind();
    }

    public void disableThreadAsserts() {
        this.mEnableThreadAsserts = false;
    }

    public boolean addObserver(E e2) {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        if (e2 == null || this.mObservers.contains(e2)) {
            return false;
        }
        this.mObservers.add(e2);
        this.mCount++;
        return true;
    }

    public boolean removeObserver(E e2) {
        int indexOf;
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        if (e2 == null || (indexOf = this.mObservers.indexOf(e2)) == -1) {
            return false;
        }
        if (this.mIterationDepth == 0) {
            this.mObservers.remove(indexOf);
        } else {
            this.mNeedsCompact = true;
            this.mObservers.set(indexOf, null);
        }
        this.mCount--;
        return true;
    }

    public boolean hasObserver(E e2) {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        return this.mObservers.contains(e2);
    }

    public void clear() {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        this.mCount = 0;
        if (this.mIterationDepth == 0) {
            this.mObservers.clear();
            return;
        }
        int size = this.mObservers.size();
        this.mNeedsCompact |= size != 0;
        for (int i2 = 0; i2 < size; i2++) {
            this.mObservers.set(i2, null);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        return new ObserverListIterator();
    }

    public RewindableIterator<E> rewindableIterator() {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        return new ObserverListIterator();
    }

    public int size() {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        return this.mCount;
    }

    public boolean isEmpty() {
        if (this.mEnableThreadAsserts) {
            this.mThreadChecker.assertOnValidThread();
        }
        return this.mCount == 0;
    }

    private void compact() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            if (this.mObservers.get(size) == null) {
                this.mObservers.remove(size);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementIterationDepth() {
        this.mIterationDepth++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementIterationDepthAndCompactIfNeeded() {
        int i2 = this.mIterationDepth - 1;
        this.mIterationDepth = i2;
        if (i2 <= 0 && this.mNeedsCompact) {
            this.mNeedsCompact = false;
            compact();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int capacity() {
        return this.mObservers.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public E getObserverAt(int i2) {
        return this.mObservers.get(i2);
    }

    /* loaded from: classes9.dex */
    private class ObserverListIterator implements RewindableIterator<E> {
        private int mIndex;
        private boolean mIsExhausted;
        private int mListEndMarker;

        private ObserverListIterator() {
            ObserverList.this.incrementIterationDepth();
            this.mListEndMarker = ObserverList.this.capacity();
        }

        @Override // com.ttnet.org.chromium.base.ObserverList.RewindableIterator
        public void rewind() {
            if (ObserverList.this.mEnableThreadAsserts) {
                ObserverList.this.mThreadChecker.assertOnValidThread();
            }
            compactListIfNeeded();
            ObserverList.this.incrementIterationDepth();
            this.mListEndMarker = ObserverList.this.capacity();
            this.mIsExhausted = false;
            this.mIndex = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (ObserverList.this.mEnableThreadAsserts) {
                ObserverList.this.mThreadChecker.assertOnValidThread();
            }
            int i2 = this.mIndex;
            while (i2 < this.mListEndMarker && ObserverList.this.getObserverAt(i2) == null) {
                i2++;
            }
            if (i2 < this.mListEndMarker) {
                return true;
            }
            compactListIfNeeded();
            return false;
        }

        @Override // java.util.Iterator
        public E next() {
            if (ObserverList.this.mEnableThreadAsserts) {
                ObserverList.this.mThreadChecker.assertOnValidThread();
            }
            while (true) {
                int i2 = this.mIndex;
                if (i2 >= this.mListEndMarker || ObserverList.this.getObserverAt(i2) != null) {
                    break;
                }
                this.mIndex++;
            }
            int i3 = this.mIndex;
            if (i3 < this.mListEndMarker) {
                ObserverList observerList = ObserverList.this;
                this.mIndex = i3 + 1;
                return (E) observerList.getObserverAt(i3);
            }
            compactListIfNeeded();
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void compactListIfNeeded() {
            if (this.mIsExhausted) {
                return;
            }
            this.mIsExhausted = true;
            ObserverList.this.decrementIterationDepthAndCompactIfNeeded();
        }
    }
}
