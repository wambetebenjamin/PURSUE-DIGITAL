package com.bytedance.keva;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import com.bytedance.keva.Keva;
import com.bytedance.librarian.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class KevaImpl extends Keva {
    static int sExecuteKevaType;
    static final Executor sExecutor;
    static final boolean sIsAsyncWriteEnable;
    static final boolean sIsEnableArrayMap;
    static final boolean sIsEnableExecutorOpt;
    static int sIsEnableLoadFromNative;
    static final boolean sIsEnableLockOpt;
    static int sIsEnableMultiProcessLoadFromNative;
    static final boolean sIsLoadOptEnable;
    static final KevaMonitor sMonitor;
    private static final ConcurrentHashMap<String, Map<String, Keva>> sPathRepoMap;
    private static final String sPortedSpRepoName;
    private static final Map<String, Keva> sRepoMap;
    private static final File sSharedPrefsDir;
    static final File sWorkDir;
    volatile long mHandle;
    private final boolean mIsMultiProcess;
    final int mMode;
    final String mName;
    String mSpecifiedPath;
    volatile Map<String, KevaValueWrapper> mValueMap = obtainMap();
    private final ConcurrentLinkedQueue<Keva.OnChangeListener> mChangeListeners = new ConcurrentLinkedQueue<>();
    private List<KevaValueWrapper> mDuplicatedOldWrappers = new ArrayList();

    /* loaded from: classes2.dex */
    protected interface PrivateConstants {
        public static final int BLOCK_TYPE_BYTES = 0;
        public static final int BLOCK_TYPE_OBJECT = 4;
        public static final int BLOCK_TYPE_STRING_ARRAY = 3;
        public static final int BLOCK_TYPE_STRING_UTF16 = 2;
        public static final int BLOCK_TYPE_STRING_UTF8 = 1;
        public static final String EMPTY_STRING = "";
        public static final int EXT_TYPE_BYTES = 7;
        public static final int EXT_TYPE_OBJECT = 71;
        public static final int EXT_TYPE_STRING_ARRAY = 55;
        public static final int EXT_TYPE_STRING_UTF16 = 39;
        public static final int EXT_TYPE_STRING_UTF8 = 23;
        public static final String FILES_DIR_NAME = "files";
        public static final String KEVA = "keva";
        public static final String LIB_NAME = "keva";
        public static final String PORTED_SP = "keva_porting_sp";
        public static final int REPORT_VALUE_SIZE_THRESHOLD = 10000;
        public static final String SP_DIR_NAME = "shared_prefs";
        public static final int TYPE_BOOL = 2;
        public static final int TYPE_BYTES = 7;
        public static final int TYPE_DOUBLE = 5;
        public static final int TYPE_FLOAT = 4;
        public static final int TYPE_INT = 1;
        public static final int TYPE_INVALID = 0;
        public static final int TYPE_LONG = 3;
        public static final int TYPE_STRING = 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void checkReportException(long j2);

    private static native void clear(long j2);

    private static native boolean delete(String str);

    private native void dump(long j2);

    private native void erase(long j2, String str, long j3);

    private native void eraseUnusedChunk(long j2, long j3);

    private static native void initialize(String str);

    private native long loadRepo(String str, int i2, boolean z);

    private native long loadRepoWithPath(String str, String str2, int i2);

    private native int protectPortingInterProcess(String str, boolean z, int i2);

    private native long storeBoolean(long j2, String str, long j3, boolean z);

    private native long storeBytes(long j2, String str, long j3, byte[] bArr, int i2);

    private native long storeDouble(long j2, String str, long j3, double d2);

    private native long storeFloat(long j2, String str, long j3, float f2);

    private native long storeInt(long j2, String str, long j3, int i2);

    private native long storeLong(long j2, String str, long j3, long j4);

    private native long storeString(long j2, String str, long j3, String str2);

    private native long storeStringArray(long j2, String str, long j3, String[] strArr, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean contains(long j2, String str, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void fetchAllKey(long j2, Set<String> set);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean fetchBoolean(long j2, String str, long j3, boolean z);

    protected abstract boolean fetchBoolean(String str, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native byte[] fetchBytes(long j2, String str, long j3, byte[] bArr, int i2);

    protected abstract byte[] fetchBytes(String str, byte[] bArr, int i2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native double fetchDouble(long j2, String str, long j3, double d2);

    protected abstract double fetchDouble(String str, double d2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native float fetchFloat(long j2, String str, long j3, float f2);

    protected abstract float fetchFloat(String str, float f2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native int fetchInt(long j2, String str, long j3, int i2);

    protected abstract int fetchInt(String str, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native long fetchLong(long j2, String str, long j3, long j4);

    protected abstract long fetchLong(String str, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native String fetchString(long j2, String str, long j3, String str2);

    protected abstract String fetchString(String str, String str2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native String[] fetchStringArray(long j2, String str, long j3, String[] strArr, int i2);

    protected abstract String[] fetchStringArray(String str, String[] strArr, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void rebuildValueMap(long j2);

    /* JADX INFO: Access modifiers changed from: protected */
    public static Map obtainMap() {
        if (sIsEnableArrayMap) {
            return Build.VERSION.SDK_INT >= 19 ? new ArrayMap() : new HashMap();
        }
        return new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PortedSpRepoHolder {
        private static final KevaImpl sInstance;
        private static final Map<String, ?> sPortedSpMap;

        private PortedSpRepoHolder() {
        }

        static {
            KevaMultiProcessImpl kevaMultiProcessImpl = new KevaMultiProcessImpl(KevaImpl.sPortedSpRepoName, null, 1);
            sInstance = kevaMultiProcessImpl;
            kevaMultiProcessImpl.init(true);
            sPortedSpMap = kevaMultiProcessImpl.buildNewMap(KevaImpl.obtainMap());
        }
    }

    private static Keva getPortedSpRepo() {
        return PortedSpRepoHolder.sInstance;
    }

    static {
        KevaBuilder kevaBuilder = KevaBuilder.getInstance();
        KevaBuilder.clearInstance();
        sRepoMap = obtainMap();
        sPathRepoMap = new ConcurrentHashMap<>();
        Executor executor = kevaBuilder.mExecutor;
        boolean z = true;
        if (executor != null) {
            sExecutor = executor;
        } else {
            int max = Math.max(Runtime.getRuntime().availableProcessors() * 2, 6);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(max, max, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            sExecutor = threadPoolExecutor;
        }
        String str = kevaBuilder.mPortedRepoName;
        if (str == null) {
            str = PrivateConstants.PORTED_SP;
        }
        sPortedSpRepoName = str;
        Context context = kevaBuilder.mContext;
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            filesDir = new File(context.getApplicationInfo().dataDir, PrivateConstants.FILES_DIR_NAME);
        }
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        File file = kevaBuilder.mWorkDir;
        if (file == null) {
            file = new File(filesDir, "keva");
        }
        sSharedPrefsDir = new File(filesDir.getParent(), PrivateConstants.SP_DIR_NAME);
        KevaMonitor kevaMonitor = kevaBuilder.mMonitor;
        if (kevaMonitor == null) {
            kevaMonitor = new KevaMonitor();
        }
        sMonitor = kevaMonitor;
        kevaMonitor.logDebug("Keva version: 1.6.6.SHARED.CXXAPI-alpha.77-tiktok");
        if (!file.exists() && !file.mkdirs()) {
            kevaMonitor.reportThrowable(1, null, null, null, new IllegalStateException("fail to create work dir " + file.getPath()));
        }
        boolean z2 = kevaBuilder.mEnableAsyncWrite;
        sIsAsyncWriteEnable = z2;
        sIsLoadOptEnable = (z2 || !kevaBuilder.mEnableLoadOpt) ? false : false;
        sIsEnableLockOpt = kevaBuilder.mEnableLockOpt;
        sIsEnableExecutorOpt = kevaBuilder.mEnableExecutorOpt;
        sExecuteKevaType = kevaBuilder.mExecuteKevaType;
        sWorkDir = file;
        sIsEnableArrayMap = kevaBuilder.mEnableArrayMap;
        sIsEnableLoadFromNative = kevaBuilder.mIsLoadFromNative;
        sIsEnableMultiProcessLoadFromNative = kevaBuilder.sIsEnableMultiProcessLoadFromNative;
        try {
            kevaMonitor.loadLibrary("keva");
            initialize(file.getAbsolutePath());
        } catch (Throwable th) {
            th.printStackTrace();
            KevaMonitor kevaMonitor2 = sMonitor;
            kevaMonitor2.logDebug("fail to load so and init");
            kevaMonitor2.reportThrowable(1, null, null, null, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void forceInitImpl() {
        Log.println(4, KevaConstants.TAG, "force initializing Keva");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KevaImpl(String str, String str2, int i2) {
        this.mName = str;
        this.mMode = i2;
        this.mSpecifiedPath = str2;
        this.mIsMultiProcess = (i2 & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(boolean z) {
        synchronized (this) {
            if (this.mHandle != 0) {
                return;
            }
            doLoadRepo(z);
        }
    }

    private void cleanUpAfterLoadRepo() {
        if (this.mHandle == 0) {
            sMonitor.reportWarning(1, this.mName, null, null, "free space is " + Environment.getDataDirectory().getFreeSpace());
            return;
        }
        try {
            for (KevaValueWrapper kevaValueWrapper : this.mDuplicatedOldWrappers) {
                eraseUnusedChunk(this.mHandle, kevaValueWrapper.offset);
            }
            this.mDuplicatedOldWrappers = null;
            checkReportException(this.mHandle);
        } catch (Throwable th) {
            sMonitor.reportThrowable(1, this.mName, null, null, th);
        }
    }

    public static Keva getRepo(String str, int i2) {
        return getRepoImpl(str, null, i2, false);
    }

    public static Keva getRepoSync(String str, int i2) {
        return getRepoImpl(str, null, i2, true);
    }

    public static Keva getRepoWithPath(String str, String str2, int i2, boolean z) {
        if (str2 == null || "".equals(str2) || str2.startsWith(c.a.SEPARATOR) || !str2.endsWith(c.a.SEPARATOR)) {
            throw new IllegalArgumentException("The path passed in is wrong!");
        }
        return getRepoImpl(str, str2, i2, z);
    }

    private static KevaImpl getEmptyRepoImplWithPath(String str, String str2, int i2, boolean z) {
        if (i2 < 0 || i2 >= 2) {
            sMonitor.reportThrowable(1, str, null, null, new IllegalArgumentException("mode is not valid! " + i2));
        }
        if ((i2 & 1) != 0) {
            if (!z && sIsEnableMultiProcessLoadFromNative == 1) {
                return new KevaPrivateOnlyNativeImpl(str, str2, i2);
            }
            return new KevaMultiProcessImpl(str, str2, i2);
        } else if (!z) {
            int i3 = sIsEnableLoadFromNative;
            if (i3 == 1) {
                return new KevaPrivateNativeImpl(str, str2, i2);
            }
            if (i3 == 2) {
                return new KevaPrivateOnlyNativeImpl(str, str2, i2);
            }
            return new KevaPrivateImpl(str, str2, i2);
        } else {
            return new KevaPrivateImpl(str, str2, i2);
        }
    }

    private static Keva getRepoImpl(String str, String str2, int i2, boolean z) {
        Map<String, Keva> map;
        Keva keva;
        Map<String, Keva> map2;
        boolean z2 = true;
        if (!sIsEnableLockOpt) {
            if (str2 == null) {
                map2 = sRepoMap;
            } else {
                ConcurrentHashMap<String, Map<String, Keva>> concurrentHashMap = sPathRepoMap;
                concurrentHashMap.putIfAbsent(str2, obtainMap());
                map2 = concurrentHashMap.get(str2);
            }
            synchronized (map2) {
                keva = map2.get(str);
                if (keva != null) {
                    z2 = false;
                }
                if (z2) {
                    keva = z ? getEmptyRepoImplWithPath(str, str2, i2, false) : getAsyncKeva(null, str, str2, i2, false);
                    map2.put(str, keva);
                }
            }
        } else {
            if (str2 == null) {
                map = sRepoMap;
            } else {
                ConcurrentHashMap<String, Map<String, Keva>> concurrentHashMap2 = sPathRepoMap;
                concurrentHashMap2.putIfAbsent(str2, obtainMap());
                map = concurrentHashMap2.get(str2);
            }
            keva = map.get(str);
            if (keva == null) {
                synchronized (map) {
                    keva = map.get(str);
                    if (keva != null) {
                        z2 = false;
                    }
                    if (z2) {
                        Keva emptyRepoImplWithPath = z ? getEmptyRepoImplWithPath(str, str2, i2, false) : getAsyncKeva(null, str, str2, i2, false);
                        map.put(str, emptyRepoImplWithPath);
                        keva = emptyRepoImplWithPath;
                    }
                }
            } else {
                z2 = false;
            }
        }
        if (z2) {
            if (!(keva instanceof KevaFuture)) {
                ((KevaImpl) keva).init(false);
            }
        } else if (!(keva instanceof KevaFuture)) {
            ((KevaImpl) keva).checkMode(i2);
        } else {
            ((KevaFuture) keva).checkMode(i2);
        }
        return keva;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KevaImpl getEmptyRepoImpl(String str, String str2, int i2, boolean z) {
        return getEmptyRepoImplWithPath(str, str2, i2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkMode(int i2) {
        if (this.mHandle == 0 || this.mMode == i2) {
            return;
        }
        sMonitor.reportThrowable(1, this.mName, null, null, new IllegalStateException("mode is different: " + this.mMode + " != " + i2));
    }

    public static Keva getRepoFromSp(Context context, String str, int i2) {
        return getRepoFromSpImpl(context, str, i2, false);
    }

    public static Keva getRepoFromSpSync(Context context, String str, int i2) {
        return getRepoFromSpImpl(context, str, i2, true);
    }

    private static Keva getAsyncKeva(final Context context, final String str, final String str2, final int i2, final boolean z) {
        Callable<KevaImpl> callable = new Callable<KevaImpl>() { // from class: com.bytedance.keva.KevaImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public KevaImpl call() {
                if (z) {
                    KevaImpl emptyRepoImpl = KevaImpl.getEmptyRepoImpl(str, str2, i2, true);
                    emptyRepoImpl.doPortingFromSp(context);
                    return emptyRepoImpl;
                }
                KevaImpl emptyRepoImpl2 = KevaImpl.getEmptyRepoImpl(str, str2, i2, false);
                emptyRepoImpl2.init(false);
                return emptyRepoImpl2;
            }
        };
        if (sIsAsyncWriteEnable && i2 == 0) {
            return new KevaAsyncWriter(str, str2, i2, callable, z);
        }
        return new KevaFuture(str, str2, i2, callable, z);
    }

    private static Keva getRepoFromSpImpl(Context context, String str, int i2, boolean z) {
        Keva keva;
        if (isRepoPorted(str)) {
            return getRepo(str, i2);
        }
        if (!existSharedPrefs(str)) {
            getPortedSpRepo().storeBoolean(str, true);
            return getRepo(str, i2);
        }
        sMonitor.logDebug("do poring from sp: " + str);
        if (!sIsEnableLockOpt) {
            Map<String, Keva> map = sRepoMap;
            synchronized (map) {
                keva = map.get(str);
                r2 = keva == null;
                if (r2) {
                    keva = z ? getEmptyRepoImpl(str, null, i2, true) : getAsyncKeva(context, str, null, i2, true);
                    map.put(str, keva);
                }
            }
        } else {
            Map<String, Keva> map2 = sRepoMap;
            keva = map2.get(str);
            if (keva == null) {
                synchronized (map2) {
                    keva = map2.get(str);
                    r2 = keva == null;
                    if (r2) {
                        Keva emptyRepoImpl = z ? getEmptyRepoImpl(str, null, i2, true) : getAsyncKeva(context, str, null, i2, true);
                        map2.put(str, emptyRepoImpl);
                        keva = emptyRepoImpl;
                    }
                }
            }
        }
        if (r2) {
            if (keva instanceof KevaImpl) {
                try {
                    ((KevaImpl) keva).doPortingFromSp(context);
                } catch (UnsatisfiedLinkError e2) {
                    sMonitor.reportThrowable(1, str, null, null, e2);
                }
            }
        } else if (keva instanceof KevaImpl) {
            ((KevaImpl) keva).checkMode(i2);
        } else {
            ((KevaFuture) keva).checkMode(i2);
        }
        return keva;
    }

    private static boolean existSharedPrefs(String str) {
        return new File(sSharedPrefsDir, str + ".xml").exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doPortingFromSp(android.content.Context r20) {
        /*
            Method dump skipped, instructions count: 559
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.keva.KevaImpl.doPortingFromSp(android.content.Context):void");
    }

    void doLoadRepo(boolean z) {
        try {
            String str = this.mSpecifiedPath;
            if (str == null) {
                this.mHandle = loadRepo(this.mName, this.mMode, z);
            } else {
                this.mHandle = loadRepoWithPath(this.mName, str, this.mMode);
            }
            cleanUpAfterLoadRepo();
            if (z) {
                return;
            }
            sMonitor.onLoadRepo(this.mName, this.mMode);
        } catch (Throwable th) {
            sMonitor.reportThrowable(1, this.mName, null, null, th);
        }
    }

    public static boolean isRepoPorted(String str) {
        if (PortedSpRepoHolder.sPortedSpMap.get(str) != null) {
            return true;
        }
        return getPortedSpRepo().getBoolean(str, false);
    }

    @Override // com.bytedance.keva.Keva
    public String name() {
        return this.mName;
    }

    public int mode() {
        return this.mMode;
    }

    private void addMapObjectWhenLoading(String str, Object obj, long j2, int i2) {
        List<KevaValueWrapper> list;
        if ("".equals(str)) {
            str = null;
        }
        KevaValueWrapper kevaValueWrapper = new KevaValueWrapper();
        kevaValueWrapper.value = obj;
        kevaValueWrapper.offset = j2;
        kevaValueWrapper.type = i2;
        if (!this.mIsMultiProcess) {
            kevaValueWrapper.loaded = true;
        }
        KevaValueWrapper put = this.mValueMap.put(str, kevaValueWrapper);
        if (put == null || (list = this.mDuplicatedOldWrappers) == null) {
            return;
        }
        list.add(put);
    }

    void addMapOffsetWhenLoading(String str, long j2, int i2) {
        List<KevaValueWrapper> list;
        if ("".equals(str)) {
            str = null;
        }
        KevaValueWrapper kevaValueWrapper = new KevaValueWrapper();
        kevaValueWrapper.offset = j2;
        kevaValueWrapper.type = i2;
        KevaValueWrapper put = this.mValueMap.put(str, kevaValueWrapper);
        if (put == null || (list = this.mDuplicatedOldWrappers) == null) {
            return;
        }
        list.add(put);
    }

    void addMapIntWhenLoading(String str, int i2, long j2) {
        addMapObjectWhenLoading(str, Integer.valueOf(i2), j2, 1);
    }

    void addMapBoolWhenLoading(String str, boolean z, long j2) {
        addMapObjectWhenLoading(str, Boolean.valueOf(z), j2, 2);
    }

    void addMapFloatWhenLoading(String str, float f2, long j2) {
        addMapObjectWhenLoading(str, Float.valueOf(f2), j2, 4);
    }

    void addMapDoubleWhenLoading(String str, double d2, long j2) {
        addMapObjectWhenLoading(str, Double.valueOf(d2), j2, 5);
    }

    void addMapLongWhenLoading(String str, long j2, long j3) {
        addMapObjectWhenLoading(str, Long.valueOf(j2), j3, 3);
    }

    static void passWarning(int i2, String str, String str2, Object obj, String str3) {
        sMonitor.reportWarning(i2, str, str2, obj, str3);
    }

    private KevaValueWrapper obtainWrapperLocked(String str) {
        KevaValueWrapper kevaValueWrapper = this.mValueMap.get(str);
        if (kevaValueWrapper == null) {
            KevaValueWrapper kevaValueWrapper2 = new KevaValueWrapper();
            this.mValueMap.put(str, kevaValueWrapper2);
            return kevaValueWrapper2;
        }
        return kevaValueWrapper;
    }

    private void reportBigValue(final String str, final Object obj, final int i2) {
        if (i2 > 10000) {
            sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    KevaImpl.sMonitor.reportWarning(3, KevaImpl.this.mName, str, obj, "value too big, size=" + i2);
                }
            });
        }
    }

    @Override // com.bytedance.keva.Keva
    public Map<String, Object> buildNewMap(Map<String, Object> map) {
        synchronized (this) {
            for (Map.Entry<String, KevaValueWrapper> entry : this.mValueMap.entrySet()) {
                KevaValueWrapper value = entry.getValue();
                if (value.offset != 0) {
                    String key = entry.getKey();
                    if (!value.loaded) {
                        int i2 = value.type;
                        if (i2 == 6) {
                            value.value = fetchString(this.mHandle, key, value.offset, null);
                        } else if (i2 == 7) {
                            value.value = fetchBytes(this.mHandle, key, value.offset, null, 0);
                        } else if (i2 == 23 || i2 == 39) {
                            throw new UnsupportedOperationException("keva has not implemented type " + value.type);
                        } else {
                            if (i2 == 55) {
                                value.value = fetchStringArray(this.mHandle, key, value.offset, null, 3);
                            } else if (!this.mIsMultiProcess || value.type > 5) {
                                throw new UnsupportedOperationException("keva has not implemented type " + value.type);
                            }
                        }
                        if (!this.mIsMultiProcess) {
                            value.loaded = true;
                        }
                    }
                    map.put(key, value.value);
                }
            }
        }
        return map;
    }

    @Override // com.bytedance.keva.Keva
    public byte[] getBytes(String str, byte[] bArr) {
        byte[] fetchBytes;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchBytes = fetchBytes(str2, bArr, 0, false);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchBytes;
    }

    @Override // com.bytedance.keva.Keva
    public String getString(String str, String str2) {
        String fetchString;
        if ("".equals(str)) {
            str = null;
        }
        String str3 = str;
        synchronized (this) {
            try {
                fetchString = fetchString(str3, str2, false);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchString;
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getStringSet(String str, Set<String> set) {
        Set<String> fetchStringSet;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchStringSet = fetchStringSet(str2, set, false);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchStringSet;
    }

    @Override // com.bytedance.keva.Keva
    public String[] getStringArray(String str, String[] strArr) {
        String[] fetchStringArray;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchStringArray = fetchStringArray(str2, strArr, false);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchStringArray;
    }

    @Override // com.bytedance.keva.Keva
    public byte[] getBytesJustDisk(String str, byte[] bArr) {
        byte[] fetchBytes;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchBytes = fetchBytes(str2, bArr, 0, true);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchBytes;
    }

    @Override // com.bytedance.keva.Keva
    public String getStringJustDisk(String str, String str2) {
        String fetchString;
        if ("".equals(str)) {
            str = null;
        }
        String str3 = str;
        synchronized (this) {
            try {
                fetchString = fetchString(str3, str2, true);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchString;
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getStringSetJustDisk(String str, Set<String> set) {
        Set<String> fetchStringSet;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchStringSet = fetchStringSet(str2, set, true);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchStringSet;
    }

    @Override // com.bytedance.keva.Keva
    public String[] getStringArrayJustDisk(String str, String[] strArr) {
        String[] fetchStringArray;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchStringArray = fetchStringArray(str2, strArr, true);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchStringArray;
    }

    @Override // com.bytedance.keva.Keva
    public int getInt(String str, int i2) {
        int fetchInt;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchInt = fetchInt(str2, i2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchInt;
    }

    @Override // com.bytedance.keva.Keva
    public long getLong(String str, long j2) {
        long fetchLong;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchLong = fetchLong(str2, j2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchLong;
    }

    @Override // com.bytedance.keva.Keva
    public double getDouble(String str, double d2) {
        double fetchDouble;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchDouble = fetchDouble(str2, d2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchDouble;
    }

    @Override // com.bytedance.keva.Keva
    public float getFloat(String str, float f2) {
        float fetchFloat;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchFloat = fetchFloat(str2, f2);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchFloat;
    }

    @Override // com.bytedance.keva.Keva
    public boolean getBoolean(String str, boolean z) {
        boolean fetchBoolean;
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        synchronized (this) {
            try {
                fetchBoolean = fetchBoolean(str2, z);
            } catch (Throwable th) {
                throw th;
            }
        }
        return fetchBoolean;
    }

    @Override // com.bytedance.keva.Keva
    public void storeFloat(String str, float f2) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(Float.valueOf(f2))) {
                obtainWrapperLocked.offset = storeFloat(this.mHandle, str, obtainWrapperLocked.offset, f2);
                obtainWrapperLocked.value = Float.valueOf(f2);
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
                notifyChangeListeners(this, str);
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void storeInt(String str, int i2) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(Integer.valueOf(i2))) {
                obtainWrapperLocked.offset = storeInt(this.mHandle, str, obtainWrapperLocked.offset, i2);
                obtainWrapperLocked.value = Integer.valueOf(i2);
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
                notifyChangeListeners(this, str);
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void storeBoolean(String str, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(Boolean.valueOf(z))) {
                obtainWrapperLocked.offset = storeBoolean(this.mHandle, str, obtainWrapperLocked.offset, z);
                obtainWrapperLocked.value = Boolean.valueOf(z);
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
                notifyChangeListeners(this, str);
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void storeLong(String str, long j2) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(Long.valueOf(j2))) {
                obtainWrapperLocked.offset = storeLong(this.mHandle, str, obtainWrapperLocked.offset, j2);
                obtainWrapperLocked.value = Long.valueOf(j2);
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
                notifyChangeListeners(this, str);
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void storeDouble(String str, double d2) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(Double.valueOf(d2))) {
                obtainWrapperLocked.offset = storeDouble(this.mHandle, str, obtainWrapperLocked.offset, d2);
                obtainWrapperLocked.value = Double.valueOf(d2);
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
                notifyChangeListeners(this, str);
            }
        }
    }

    private void storeString(final String str, final String str2, boolean z) {
        final int length;
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            if (this.mIsMultiProcess || !obtainWrapperLocked.loaded || obtainWrapperLocked.value == null || !obtainWrapperLocked.value.equals(str2)) {
                obtainWrapperLocked.offset = storeString(this.mHandle, str, obtainWrapperLocked.offset, str2);
                obtainWrapperLocked.type = 6;
                if (!z) {
                    obtainWrapperLocked.value = str2;
                    if (!this.mIsMultiProcess) {
                        obtainWrapperLocked.loaded = true;
                    }
                } else {
                    obtainWrapperLocked.value = null;
                    obtainWrapperLocked.loaded = false;
                }
                notifyChangeListeners(this, str);
                if (str2 == null || (length = str2.length()) <= 10000) {
                    return;
                }
                sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaImpl.3
                    @Override // java.lang.Runnable
                    public void run() {
                        KevaImpl.sMonitor.reportWarning(3, KevaImpl.this.mName, str, str2, "value too big, size=" + length);
                    }
                });
            }
        }
    }

    private void storeBytes(final String str, byte[] bArr, int i2, boolean z) {
        final int length;
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            obtainWrapperLocked.offset = storeBytes(this.mHandle, str, obtainWrapperLocked.offset, bArr, i2);
            obtainWrapperLocked.type = (i2 << 4) | 7;
            if (!z) {
                obtainWrapperLocked.value = bArr;
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
            } else {
                obtainWrapperLocked.value = null;
                obtainWrapperLocked.loaded = false;
            }
            notifyChangeListeners(this, str);
        }
        if (bArr == null || (length = bArr.length) <= 10000) {
            return;
        }
        sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaImpl.4
            @Override // java.lang.Runnable
            public void run() {
                KevaImpl.sMonitor.reportWarning(3, KevaImpl.this.mName, str, null, "value too big, size=" + length);
            }
        });
    }

    private void storeStringSet(String str, Set<String> set, boolean z) {
        String[] strArr;
        if (set != null) {
            strArr = new String[set.size()];
            set.toArray(strArr);
        } else {
            strArr = null;
        }
        storeStringArray(str, strArr, z);
    }

    private void storeStringArray(final String str, String[] strArr, boolean z) {
        final String arrays;
        final int length;
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
            obtainWrapperLocked.offset = storeStringArray(this.mHandle, str, obtainWrapperLocked.offset, strArr, 3);
            obtainWrapperLocked.type = 55;
            if (!z) {
                obtainWrapperLocked.value = strArr;
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
            } else {
                obtainWrapperLocked.value = null;
                obtainWrapperLocked.loaded = false;
            }
            notifyChangeListeners(this, str);
        }
        if (strArr == null || (length = (arrays = Arrays.toString(strArr)).length()) <= 10000) {
            return;
        }
        sExecutor.execute(new Runnable() { // from class: com.bytedance.keva.KevaImpl.5
            @Override // java.lang.Runnable
            public void run() {
                KevaImpl.sMonitor.reportWarning(3, KevaImpl.this.mName, str, arrays, "value too big, size=" + length);
            }
        });
    }

    @Override // com.bytedance.keva.Keva
    public void storeString(String str, String str2) {
        storeString(str, str2, false);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringSet(String str, Set<String> set) {
        storeStringSet(str, set, false);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringArray(String str, String[] strArr) {
        storeStringArray(str, strArr, false);
    }

    @Override // com.bytedance.keva.Keva
    public void storeBytes(String str, byte[] bArr) {
        storeBytes(str, bArr, 0, false);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringJustDisk(String str, String str2) {
        storeString(str, str2, true);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringSetJustDisk(String str, Set<String> set) {
        storeStringSet(str, set, true);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringArrayJustDisk(String str, String[] strArr) {
        storeStringArray(str, strArr, true);
    }

    @Override // com.bytedance.keva.Keva
    public void storeBytesJustDisk(String str, byte[] bArr) {
        storeBytes(str, bArr, 0, true);
    }

    @Override // com.bytedance.keva.Keva
    public void erase(String str) {
        if ("".equals(str)) {
            str = null;
        }
        synchronized (this) {
            try {
                KevaValueWrapper obtainWrapperLocked = obtainWrapperLocked(str);
                erase(this.mHandle, str, obtainWrapperLocked.offset);
                obtainWrapperLocked.value = null;
                obtainWrapperLocked.offset = 0L;
                if (!this.mIsMultiProcess) {
                    obtainWrapperLocked.loaded = true;
                }
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void clear() {
        synchronized (this) {
            try {
                this.mValueMap.clear();
                clear(this.mHandle);
            }
        }
    }

    @Override // com.bytedance.keva.Keva
    public void dump() {
        synchronized (this) {
            sMonitor.logDebug("start dumping keva " + this.mName + ", size=" + this.mValueMap.size());
            for (Map.Entry<String, KevaValueWrapper> entry : this.mValueMap.entrySet()) {
                KevaValueWrapper value = entry.getValue();
                sMonitor.logDebug("key: " + entry.getKey() + ", value: " + value.value + ", offset: " + value.offset + ", loaded: " + value.loaded);
            }
            sMonitor.logDebug("finish dumping keva " + this.mName + ", size=" + this.mValueMap.size());
        }
    }

    public void dumpNative() {
        synchronized (this) {
            dump(this.mHandle);
        }
    }

    @Override // com.bytedance.keva.Keva
    public void registerChangeListener(Keva.OnChangeListener onChangeListener) {
        if (this.mChangeListeners.contains(onChangeListener)) {
            return;
        }
        this.mChangeListeners.add(onChangeListener);
    }

    @Override // com.bytedance.keva.Keva
    public void unRegisterChangeListener(Keva.OnChangeListener onChangeListener) {
        synchronized (this) {
            this.mChangeListeners.remove(onChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyChangeListeners(Keva keva, String str) {
        if (this.mChangeListeners.isEmpty()) {
            return;
        }
        Iterator<Keva.OnChangeListener> it = this.mChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onChanged(keva, str);
        }
    }

    private Set<String> fetchStringSet(String str, Set<String> set, boolean z) {
        String[] fetchStringArray = fetchStringArray(str, set != null ? (String[]) set.toArray(new String[0]) : null, z);
        if (fetchStringArray != null) {
            return new HashSet(Arrays.asList(fetchStringArray));
        }
        return null;
    }

    private void fetchAllKey(String str, Set<String> set) {
        set.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class KevaValueWrapper {
        volatile boolean loaded;
        volatile long offset;
        volatile int type;
        volatile Object value;

        KevaValueWrapper() {
        }
    }
}
