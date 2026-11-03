package com.bytedance.keva;

import android.os.Looper;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class KevaAsyncWriter extends KevaFuture {
    private static final long MAIN_THREAD_ID = Looper.getMainLooper().getThread().getId();
    private static final String WAL_FLAG_DIR_NAME = "wal";
    private static final Map<String, Boolean> sIgnoreMap;
    private static final CopyOnWriteArrayList<OnFlushListener> sListeners;
    private static final Keva sWalFlagKeva;
    private final LinkedHashMap<String, AsyncWriteObject> mMap;
    private volatile boolean mNeedClear;
    private final ReentrantReadWriteLock mReentrantReadWriteLock;
    private volatile Stage mStage;
    private final Object mSyncLock;
    private volatile boolean mWalCheckReady;
    private final OnFlushListener onFlushListener;

    /* loaded from: classes2.dex */
    public interface OnFlushListener {
        void onFlush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum Stage {
        LOADING,
        LOAD,
        FLUSH,
        SYNC
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.keva.KevaFuture
    public void initTask(Callable<KevaImpl> callable) {
    }

    static {
        KevaPrivateImpl kevaPrivateImpl = new KevaPrivateImpl(WAL_FLAG_DIR_NAME, null, 0);
        sWalFlagKeva = kevaPrivateImpl;
        sListeners = new CopyOnWriteArrayList<>();
        sIgnoreMap = new ConcurrentHashMap();
        kevaPrivateImpl.init(false);
        uploadFailedStatistics();
    }

    private static void uploadFailedStatistics() {
        final Set<Map.Entry<String, ?>> entrySet = sWalFlagKeva.getAll().entrySet();
        if (entrySet.size() == 0) {
            return;
        }
        KevaImpl.sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaAsyncWriter.1
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry entry : entrySet) {
                    String str = (String) entry.getKey();
                    Boolean bool = (Boolean) KevaAsyncWriter.sIgnoreMap.get(str);
                    if (bool == null || !bool.booleanValue()) {
                        sb.setLength(0);
                        sb.append("fileName = ").append(str);
                        KevaAsyncWriter.sWalFlagKeva.erase(str);
                        KevaImpl.sMonitor.reportWarning(6, str, "", "", sb.toString());
                        KevaAsyncWriter.sIgnoreMap.remove(str);
                    }
                }
            }
        });
    }

    static void registerOnFlushListener(OnFlushListener onFlushListener) {
        sListeners.add(onFlushListener);
    }

    static void unregisterOnFlushListener(OnFlushListener onFlushListener) {
        sListeners.remove(onFlushListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void onFlush() {
        Iterator<OnFlushListener> it = sListeners.iterator();
        while (it.hasNext()) {
            it.next().onFlush();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KevaAsyncWriter(String str, String str2, int i2, Callable<KevaImpl> callable, boolean z) {
        super(str, str2, i2, callable, z);
        this.mSyncLock = new Object();
        this.mMap = new LinkedHashMap<>();
        this.mReentrantReadWriteLock = new ReentrantReadWriteLock();
        OnFlushListener onFlushListener = new OnFlushListener() { // from class: com.bytedance.keva.KevaAsyncWriter.2
            @Override // com.bytedance.keva.KevaAsyncWriter.OnFlushListener
            public void onFlush() {
                KevaAsyncWriter.this.onFlushImpl();
            }
        };
        this.onFlushListener = onFlushListener;
        registerOnFlushListener(onFlushListener);
        this.mStage = Stage.LOADING;
        super.initTask(callable);
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeDouble(String str, double d2) {
        if (isAllReady()) {
            super.storeDouble(str, d2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.DOUBLE, AsyncWriteObject.Action.INSERT, Double.valueOf(d2)));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeDouble(str, d2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeLong(String str, long j2) {
        if (isAllReady()) {
            super.storeLong(str, j2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.LONG, AsyncWriteObject.Action.INSERT, Long.valueOf(j2)));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeLong(str, j2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeInt(String str, int i2) {
        if (isAllReady()) {
            super.storeInt(str, i2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.INT, AsyncWriteObject.Action.INSERT, Integer.valueOf(i2)));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeInt(str, i2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeFloat(String str, float f2) {
        if (isAllReady()) {
            super.storeFloat(str, f2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.FLOAT, AsyncWriteObject.Action.INSERT, Float.valueOf(f2)));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeFloat(str, f2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeBoolean(String str, boolean z) {
        if (isAllReady()) {
            super.storeBoolean(str, z);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.BOOLEAN, AsyncWriteObject.Action.INSERT, Boolean.valueOf(z)));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeBoolean(str, z);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeString(String str, String str2) {
        if (isAllReady()) {
            super.storeString(str, str2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING, AsyncWriteObject.Action.INSERT, str2));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeString(str, str2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeBytes(String str, byte[] bArr) {
        if (isAllReady()) {
            super.storeBytes(str, bArr);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.BYTES, AsyncWriteObject.Action.INSERT, bArr));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeBytes(str, bArr);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeStringSet(String str, Set<String> set) {
        if (isAllReady()) {
            super.storeStringSet(str, set);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING_SET, AsyncWriteObject.Action.INSERT, set));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeStringSet(str, set);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeStringArray(String str, String[] strArr) {
        if (isAllReady()) {
            super.storeStringArray(str, strArr);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING_ARRAY, AsyncWriteObject.Action.INSERT, strArr));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeStringArray(str, strArr);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeBytesJustDisk(String str, byte[] bArr) {
        if (isAllReady()) {
            super.storeBytesJustDisk(str, bArr);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.BYTES_JUST_DISK, AsyncWriteObject.Action.INSERT, bArr));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeBytesJustDisk(str, bArr);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeStringArrayJustDisk(String str, String[] strArr) {
        if (isAllReady()) {
            super.storeStringArrayJustDisk(str, strArr);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING_ARRAY_JUST_DISK, AsyncWriteObject.Action.INSERT, strArr));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeStringArrayJustDisk(str, strArr);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeStringJustDisk(String str, String str2) {
        if (isAllReady()) {
            super.storeStringJustDisk(str, str2);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING_JUST_DISK, AsyncWriteObject.Action.INSERT, str2));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeStringJustDisk(str, str2);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void storeStringSetJustDisk(String str, Set<String> set) {
        if (isAllReady()) {
            super.storeStringSetJustDisk(str, set);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(AsyncWriteObject.Type.STRING_SET_JUST_DISK, AsyncWriteObject.Action.INSERT, set));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.storeStringSetJustDisk(str, set);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void erase(String str) {
        if (isAllReady()) {
            super.erase(str);
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.put(str, new AsyncWriteObject(null, AsyncWriteObject.Action.ERASE, null));
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.erase(str);
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void clear() {
        if (isAllReady()) {
            super.clear();
            return;
        }
        try {
            this.mReentrantReadWriteLock.writeLock().lock();
            if (shouldAsyncWrite()) {
                this.mMap.clear();
                this.mNeedClear = true;
                return;
            }
            this.mReentrantReadWriteLock.writeLock().unlock();
            checkRepoAllReady();
            super.clear();
        } finally {
            this.mReentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public boolean getBoolean(String str, boolean z) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getBoolean(str, z);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.BOOLEAN) {
                return ((Boolean) asyncWriteObject.value).booleanValue();
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getBoolean(str, z);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public byte[] getBytes(String str, byte[] bArr) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getBytes(str, bArr);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.BYTES) {
                return (byte[]) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getBytes(str, bArr);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public byte[] getBytesJustDisk(String str, byte[] bArr) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getBytesJustDisk(str, bArr);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.BYTES_JUST_DISK) {
                return (byte[]) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getBytesJustDisk(str, bArr);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public double getDouble(String str, double d2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getDouble(str, d2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.DOUBLE) {
                return ((Double) asyncWriteObject.value).doubleValue();
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getDouble(str, d2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public float getFloat(String str, float f2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getFloat(str, f2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.FLOAT) {
                return ((Float) asyncWriteObject.value).floatValue();
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getFloat(str, f2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public int getInt(String str, int i2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getInt(str, i2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.INT) {
                return ((Integer) asyncWriteObject.value).intValue();
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getInt(str, i2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public long getLong(String str, long j2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            super.getLong(str, j2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.LONG) {
                return ((Long) asyncWriteObject.value).longValue();
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getLong(str, j2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public Set<String> getStringSet(String str, Set<String> set) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getStringSet(str, set);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING_SET) {
                return (Set) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getStringSet(str, set);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public Set<String> getStringSetJustDisk(String str, Set<String> set) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getStringSetJustDisk(str, set);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING_SET_JUST_DISK) {
                return (Set) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getStringSetJustDisk(str, set);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public String getString(String str, String str2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getString(str, str2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING) {
                return (String) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getString(str, str2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public String getStringJustDisk(String str, String str2) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getStringJustDisk(str, str2);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING_JUST_DISK) {
                return (String) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getStringJustDisk(str, str2);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public String[] getStringArray(String str, String[] strArr) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getStringArray(str, strArr);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING_ARRAY) {
                return (String[]) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getStringArray(str, strArr);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public String[] getStringArrayJustDisk(String str, String[] strArr) {
        AsyncWriteObject asyncWriteObject;
        if (isAllReady()) {
            return super.getStringArrayJustDisk(str, strArr);
        }
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (shouldReadFromMem() && (asyncWriteObject = this.mMap.get(str)) != null && asyncWriteObject.type == AsyncWriteObject.Type.STRING_ARRAY_JUST_DISK) {
                return (String[]) asyncWriteObject.value;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            checkRepoAllReady();
            return super.getStringArrayJustDisk(str, strArr);
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    private void checkRepoAllReady() {
        if (isAllReady()) {
            return;
        }
        synchronized (this.mSyncLock) {
            while (!isAllReady()) {
                try {
                    this.mSyncLock.wait();
                } catch (Throwable unused) {
                }
            }
        }
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public Map<String, ?> getAll() {
        checkRepoAllReady();
        return super.getAll();
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public void dump() {
        checkRepoAllReady();
        super.dump();
    }

    @Override // com.bytedance.keva.KevaFuture, com.bytedance.keva.Keva
    public int count() {
        checkRepoAllReady();
        return super.count();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.keva.KevaFuture
    public void onRepoLoad() {
        super.onRepoLoad();
        this.mStage = Stage.LOAD;
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (this.mMap.isEmpty()) {
                setFinalState();
                return;
            }
            this.mReentrantReadWriteLock.readLock().unlock();
            KevaImpl.sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaAsyncWriter.3
                @Override // java.lang.Runnable
                public void run() {
                    KevaAsyncWriter.this.commitWAL();
                }
            });
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitWAL() {
        commit();
        setFinalState();
    }

    private void setFinalState() {
        deleteWalFlag();
        this.mStage = Stage.SYNC;
        synchronized (this.mSyncLock) {
            this.mSyncLock.notifyAll();
        }
        unregisterOnFlushListener(this.onFlushListener);
    }

    private void commit() {
        try {
            this.mReentrantReadWriteLock.readLock().lock();
            if (this.mMap.isEmpty()) {
                return;
            }
            refreshToDisk();
            try {
                this.mReentrantReadWriteLock.writeLock().lock();
                this.mMap.clear();
            } finally {
                this.mReentrantReadWriteLock.writeLock().unlock();
            }
        } finally {
            this.mReentrantReadWriteLock.readLock().unlock();
        }
    }

    private boolean isAllReady() {
        return this.mStage == Stage.SYNC;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFlushImpl() {
        boolean z;
        synchronized (this) {
            int i2 = AnonymousClass4.$SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage[this.mStage.ordinal()];
            if (i2 == 3) {
                this.mStage = Stage.FLUSH;
                z = true;
            } else if (i2 != 4) {
                return;
            } else {
                this.mStage = Stage.FLUSH;
                z = false;
            }
            if (z) {
                commitWAL();
            } else {
                checkRepoAllReady();
            }
        }
    }

    private void refreshToDisk() {
        if (this.mNeedClear) {
            super.clear();
            this.mNeedClear = false;
        }
        for (String str : this.mMap.keySet()) {
            AsyncWriteObject asyncWriteObject = this.mMap.get(str);
            if (asyncWriteObject != null) {
                if (asyncWriteObject.action == AsyncWriteObject.Action.ERASE) {
                    super.erase(str);
                } else {
                    switch (AnonymousClass4.$SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[asyncWriteObject.type.ordinal()]) {
                        case 1:
                            super.storeDouble(str, ((Double) asyncWriteObject.value).doubleValue());
                            continue;
                        case 2:
                            super.storeLong(str, ((Long) asyncWriteObject.value).longValue());
                            continue;
                        case 3:
                            super.storeInt(str, ((Integer) asyncWriteObject.value).intValue());
                            continue;
                        case 4:
                            super.storeFloat(str, ((Float) asyncWriteObject.value).floatValue());
                            continue;
                        case 5:
                            super.storeBytes(str, (byte[]) asyncWriteObject.value);
                            continue;
                        case 6:
                            super.storeString(str, (String) asyncWriteObject.value);
                            continue;
                        case 7:
                            super.storeStringArray(str, (String[]) asyncWriteObject.value);
                            continue;
                        case 8:
                            super.storeBoolean(str, ((Boolean) asyncWriteObject.value).booleanValue());
                            continue;
                        case 9:
                            super.storeStringSet(str, (Set) asyncWriteObject.value);
                            continue;
                        case 10:
                            super.storeBytesJustDisk(str, (byte[]) asyncWriteObject.value);
                            continue;
                        case 11:
                            super.storeStringJustDisk(str, (String) asyncWriteObject.value);
                            continue;
                        case 12:
                            super.storeStringSetJustDisk(str, (Set) asyncWriteObject.value);
                            continue;
                        case 13:
                            super.storeStringArrayJustDisk(str, (String[]) asyncWriteObject.value);
                            continue;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.keva.KevaAsyncWriter$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type;
        static final /* synthetic */ int[] $SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage;

        static {
            int[] iArr = new int[AsyncWriteObject.Type.values().length];
            $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type = iArr;
            try {
                iArr[AsyncWriteObject.Type.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.BYTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING_ARRAY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING_SET.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.BYTES_JUST_DISK.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING_JUST_DISK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING_SET_JUST_DISK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$AsyncWriteObject$Type[AsyncWriteObject.Type.STRING_ARRAY_JUST_DISK.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr2 = new int[Stage.values().length];
            $SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage = iArr2;
            try {
                iArr2[Stage.FLUSH.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage[Stage.SYNC.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage[Stage.LOADING.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$bytedance$keva$KevaAsyncWriter$Stage[Stage.LOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private void deleteWalFlag() {
        sWalFlagKeva.erase(this.mName);
    }

    private boolean shouldAsyncWrite() {
        boolean z = this.mStage == Stage.LOADING && MAIN_THREAD_ID == Thread.currentThread().getId();
        if (z && !this.mWalCheckReady) {
            this.mWalCheckReady = true;
            Keva keva = sWalFlagKeva;
            if (keva.getBoolean(this.mName, false)) {
                sIgnoreMap.put(this.mName, true);
                KevaImpl.sMonitor.reportWarning(8, this.mName, "", "", "the last async write action is not work correct");
            } else {
                keva.storeBoolean(this.mName, true);
            }
        }
        return z;
    }

    private boolean shouldReadFromMem() {
        return this.mStage == Stage.LOADING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class AsyncWriteObject {
        final Action action;
        final Type type;
        final Object value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public enum Action {
            INSERT,
            ERASE,
            CLEAR
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes2.dex */
        public enum Type {
            INT,
            DOUBLE,
            LONG,
            FLOAT,
            BOOLEAN,
            STRING,
            BYTES,
            STRING_SET,
            STRING_ARRAY,
            BYTES_JUST_DISK,
            STRING_ARRAY_JUST_DISK,
            STRING_JUST_DISK,
            STRING_SET_JUST_DISK
        }

        AsyncWriteObject(Type type, Action action, Object obj) {
            this.type = type;
            this.action = action;
            this.value = obj;
        }
    }
}
