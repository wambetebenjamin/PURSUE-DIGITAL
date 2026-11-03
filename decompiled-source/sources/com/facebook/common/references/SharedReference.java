package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.IdentityHashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class SharedReference<T> {
    private static final Map<Object, Integer> sLiveObjects = new IdentityHashMap();
    private int mRefCount = 1;
    private final ResourceReleaser<T> mResourceReleaser;
    private T mValue;

    public int getRefCount() {
        return this.mRefCount;
    }

    public static Map<Object, Integer> getLiveObjects() {
        return sLiveObjects;
    }

    public SharedReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mValue = (T) Preconditions.checkNotNull(t);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
        addLiveReference(t);
    }

    private static void addLiveReference(Object obj) {
        Map<Object, Integer> map = sLiveObjects;
        synchronized (map) {
            Integer num = map.get(obj);
            if (num == null) {
                map.put(obj, 1);
            } else {
                map.put(obj, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    private static void removeLiveReference(Object obj) {
        Map<Object, Integer> map = sLiveObjects;
        synchronized (map) {
            Integer num = map.get(obj);
            if (num == null) {
                FLog.wtf("SharedReference", "No entry in sLiveObjects for value of type %s", obj.getClass());
            } else if (num.intValue() == 1) {
                map.remove(obj);
            } else {
                map.put(obj, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    public synchronized T get() {
        return this.mValue;
    }

    public synchronized boolean isValid() {
        return this.mRefCount > 0;
    }

    public static boolean isValid(SharedReference<?> sharedReference) {
        return sharedReference != null && sharedReference.isValid();
    }

    public synchronized void addReference() {
        ensureValid();
        this.mRefCount++;
    }

    public void deleteReference() {
        T t;
        if (decreaseRefCount() == 0) {
            synchronized (this) {
                t = this.mValue;
                this.mValue = null;
            }
            this.mResourceReleaser.release(t);
            removeLiveReference(t);
        }
    }

    public synchronized int decreaseRefCount() {
        int i2;
        ensureValid();
        Preconditions.checkArgument(this.mRefCount > 0);
        i2 = this.mRefCount - 1;
        this.mRefCount = i2;
        return i2;
    }

    private void ensureValid() {
        if (!isValid(this)) {
            throw new NullReferenceException();
        }
    }

    public synchronized int getRefCountTestOnly() {
        return this.mRefCount;
    }

    /* loaded from: classes8.dex */
    public static class NullReferenceException extends RuntimeException {
        public NullReferenceException() {
            super("Null shared reference");
        }
    }
}
