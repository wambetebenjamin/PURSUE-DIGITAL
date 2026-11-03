package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.facebook.soloader.SysUtil;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    public static final int SOLOADER_ENABLE_DIRECT_SOSOURCE = 64;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_EXPLICITLY_ENABLE_BACKUP_SOSOURCE = 128;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING;
    static final String TAG = "SoLoader";
    @Nullable
    private static ApplicationSoSource sApplicationSoSource;
    @Nullable
    private static UnpackingSoSource[] sBackupSoSources;
    private static int sFlags;
    @Nullable
    static SoFileLoader sSoFileLoader;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    @Nullable
    private static volatile SoSource[] sSoSources = null;
    private static final AtomicInteger sSoSourcesVersion = new AtomicInteger(0);
    private static final HashSet<String> sLoadedLibraries = new HashSet<>();
    private static final Map<String, Object> sLoadingLibraries = new HashMap();
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;
    private static final String[] DEFAULT_DENY_LIST = {System.mapLibraryName("breakpad")};
    private static int sAppType = 0;

    /* loaded from: classes8.dex */
    interface AppType {
        public static final int SYSTEM_APP = 2;
        public static final int THIRD_PARTY_APP = 1;
        public static final int UNSET = 0;
        public static final int UPDATED_SYSTEM_APP = 3;
    }

    static {
        boolean z = false;
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                z = true;
            }
        } catch (NoClassDefFoundError | UnsatisfiedLinkError unused) {
        }
        SYSTRACE_LIBRARY_LOADING = z;
    }

    public static void init(Context context, int i2) throws IOException {
        init(context, i2, null, DEFAULT_DENY_LIST);
    }

    public static void init(Context context, int i2, @Nullable SoFileLoader soFileLoader) throws IOException {
        init(context, i2, soFileLoader, DEFAULT_DENY_LIST);
    }

    public static void init(Context context, int i2, @Nullable SoFileLoader soFileLoader, String[] strArr) throws IOException {
        if (isInitialized()) {
            return;
        }
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            int appType = getAppType(context, i2);
            sAppType = appType;
            if ((i2 & 128) == 0 && SysUtil.isSupportedDirectLoad(context, appType)) {
                i2 |= 72;
            }
            initSoLoader(soFileLoader);
            initSoSources(context, i2, strArr);
            NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0, null, DEFAULT_DENY_LIST);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static void initSoSources(Context context, int i2, String[] strArr) throws IOException {
        if (sSoSources != null) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        if (sSoSources != null) {
            reentrantReadWriteLock.writeLock().unlock();
            return;
        }
        try {
            sFlags = i2;
            ArrayList arrayList = new ArrayList();
            AddSystemLibSoSource(arrayList, strArr);
            if (context != null) {
                if ((i2 & 1) != 0) {
                    sBackupSoSources = null;
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "adding exo package source: lib-main");
                    }
                    arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                } else {
                    if ((i2 & 64) != 0) {
                        addDirectApkSoSource(context, arrayList);
                    }
                    addApplicationSoSource(context, arrayList, getApplicationSoSourceFlags());
                    AddBackupSoSource(context, arrayList, 1);
                }
            }
            SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
            int makePrepareFlags = makePrepareFlags();
            int length = soSourceArr.length;
            while (true) {
                int i3 = length - 1;
                if (length <= 0) {
                    break;
                }
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Preparing SO source: " + soSourceArr[i3]);
                }
                boolean z = SYSTRACE_LIBRARY_LOADING;
                if (z) {
                    Api18TraceUtils.beginTraceSection(TAG, "_", soSourceArr[i3].getClass().getSimpleName());
                }
                soSourceArr[i3].prepare(makePrepareFlags);
                if (z) {
                    Api18TraceUtils.endSection();
                }
                length = i3;
            }
            sSoSources = soSourceArr;
            sSoSourcesVersion.getAndIncrement();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "init finish: " + sSoSources.length + " SO sources prepared");
            }
        } finally {
            sSoSourcesLock.writeLock().unlock();
        }
    }

    private static int getApplicationSoSourceFlags() {
        int i2 = sAppType;
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3) {
                return 1;
            }
            throw new RuntimeException("Unsupported app type, we should not reach here");
        }
        return 0;
    }

    private static void addDirectApkSoSource(Context context, ArrayList<SoSource> arrayList) {
        if (Build.VERSION.SDK_INT >= 21 && context.getApplicationInfo().splitSourceDirs != null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "adding directApk sources from split apks");
            }
            for (String str : context.getApplicationInfo().splitSourceDirs) {
                DirectApkSoSource directApkSoSource = new DirectApkSoSource(new File(str));
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "adding directApk source: " + directApkSoSource.toString());
                }
                arrayList.add(0, directApkSoSource);
            }
        }
        DirectApkSoSource directApkSoSource2 = new DirectApkSoSource(context);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding directApk source: " + directApkSoSource2.toString());
        }
        arrayList.add(0, directApkSoSource2);
    }

    private static void addApplicationSoSource(Context context, ArrayList<SoSource> arrayList, int i2) {
        if (Build.VERSION.SDK_INT <= 17) {
            i2 |= 1;
        }
        sApplicationSoSource = new ApplicationSoSource(context, i2);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding application source: " + sApplicationSoSource.toString());
        }
        arrayList.add(0, sApplicationSoSource);
    }

    private static void AddBackupSoSource(Context context, ArrayList<SoSource> arrayList, int i2) throws IOException {
        if ((sFlags & 8) != 0) {
            sBackupSoSources = null;
            File soStorePath = UnpackingSoSource.getSoStorePath(context, SO_STORE_NAME_MAIN);
            try {
                SysUtil.dumbDeleteRecursive(soStorePath);
                return;
            } catch (IOException e2) {
                Log.w(TAG, "Failed to delete " + soStorePath.getCanonicalPath(), e2);
                return;
            }
        }
        File file = new File(context.getApplicationInfo().sourceDir);
        ArrayList arrayList2 = new ArrayList();
        ApkSoSource apkSoSource = new ApkSoSource(context, file, SO_STORE_NAME_MAIN, i2);
        arrayList2.add(apkSoSource);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding backup source from : " + apkSoSource.toString());
        }
        addBackupSoSourceFromSplitApk(context, i2, arrayList2);
        sBackupSoSources = (UnpackingSoSource[]) arrayList2.toArray(new UnpackingSoSource[arrayList2.size()]);
        arrayList.addAll(0, arrayList2);
    }

    private static void addBackupSoSourceFromSplitApk(Context context, int i2, ArrayList<UnpackingSoSource> arrayList) {
        if (Build.VERSION.SDK_INT < 21 || context.getApplicationInfo().splitSourceDirs == null) {
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "adding backup sources from split apks");
        }
        String[] strArr = context.getApplicationInfo().splitSourceDirs;
        int length = strArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int i5 = i4 + 1;
            ApkSoSource apkSoSource = new ApkSoSource(context, new File(strArr[i3]), SO_STORE_NAME_SPLIT + i4, i2);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "adding backup source: " + apkSoSource.toString());
            }
            arrayList.add(apkSoSource);
            i3++;
            i4 = i5;
        }
    }

    private static void AddSystemLibSoSource(ArrayList<SoSource> arrayList, String[] strArr) {
        String str = SysUtil.is64Bit() ? "/system/lib64:/vendor/lib64" : "/system/lib:/vendor/lib";
        String str2 = System.getenv("LD_LIBRARY_PATH");
        if (str2 != null && !str2.equals("")) {
            str = str + CertificateUtil.DELIMITER + str2;
        }
        for (String str3 : new HashSet(Arrays.asList(str.split(CertificateUtil.DELIMITER)))) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "adding system library source: " + str3);
            }
            arrayList.add(new DirectorySoSource(new File(str3), 2, strArr));
        }
    }

    private static int makePrepareFlags() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            int i2 = (sFlags & 2) != 0 ? 1 : 0;
            reentrantReadWriteLock.writeLock().unlock();
            return i2;
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    private static synchronized void initSoLoader(@Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (soFileLoader == null) {
                if (sSoFileLoader != null) {
                    return;
                }
            }
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
                return;
            }
            final Runtime runtime = Runtime.getRuntime();
            final Method nativeLoadRuntimeMethod = getNativeLoadRuntimeMethod();
            final boolean z = nativeLoadRuntimeMethod != null;
            final String classLoaderLdLoadLibrary = z ? SysUtil.Api14Utils.getClassLoaderLdLoadLibrary() : null;
            final String makeNonZipPath = makeNonZipPath(classLoaderLdLoadLibrary);
            sSoFileLoader = new SoFileLoader() { // from class: com.facebook.soloader.SoLoader.1
                @Override // com.facebook.soloader.SoFileLoader
                public void loadBytes(String str, ElfByteChannel elfByteChannel, int i2) {
                    throw new UnsupportedOperationException();
                }

                /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
                    if (r1 == null) goto L21;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
                    android.util.Log.e(com.facebook.soloader.SoLoader.TAG, "Error when loading lib: " + r1 + " lib hash: " + getLibHash(r9) + " search path is " + r10);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
                    return;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
                    return;
                 */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:45:0x00a6  */
                /* JADX WARN: Type inference failed for: r1v0 */
                /* JADX WARN: Type inference failed for: r1v2 */
                @Override // com.facebook.soloader.SoFileLoader
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void load(java.lang.String r9, int r10) {
                    /*
                        Method dump skipped, instructions count: 219
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.AnonymousClass1.load(java.lang.String, int):void");
                }

                private String getLibHash(String str) {
                    try {
                        File file = new File(str);
                        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                        FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read > 0) {
                                    messageDigest.update(bArr, 0, read);
                                } else {
                                    String format = String.format("%32x", new BigInteger(1, messageDigest.digest()));
                                    fileInputStream.close();
                                    return format;
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException | SecurityException | NoSuchAlgorithmException e2) {
                        return e2.toString();
                    }
                }
            };
        }
    }

    @Nullable
    private static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT <= 27) {
            try {
                Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", String.class, ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException | SecurityException e2) {
                Log.w(TAG, "Cannot get nativeLoad method", e2);
            }
        }
        return null;
    }

    private static int getAppType(Context context, int i2) {
        int i3 = sAppType;
        if (i3 != 0) {
            return i3;
        }
        int i4 = 1;
        if ((i2 & 32) == 0 && context != null) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if ((applicationInfo.flags & 1) != 0) {
                i4 = (applicationInfo.flags & 128) != 0 ? 3 : 2;
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "ApplicationInfo.flags is: " + applicationInfo.flags + " appType is: " + i4);
            }
        }
        return i4;
    }

    public static void setInTestMode() {
        TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources(null);
    }

    /* loaded from: classes8.dex */
    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void setSoSources(SoSource[] soSourceArr) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = soSourceArr;
                SoLoader.sSoSourcesVersion.getAndIncrement();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }

        static void setSoFileLoader(SoFileLoader soFileLoader) {
            SoLoader.sSoFileLoader = soFileLoader;
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
            }
            setSoSources(null);
        }
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
        sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
    }

    /* loaded from: classes8.dex */
    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    @Nullable
    public static String getLibraryPath(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String str2 = null;
            if (sSoSources != null) {
                int i2 = 0;
                while (str2 == null) {
                    if (i2 >= sSoSources.length) {
                        break;
                    }
                    str2 = sSoSources[i2].getLibraryPath(str);
                    i2++;
                }
            }
            return str2;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String[] strArr = null;
            if (sSoSources != null) {
                int i2 = 0;
                while (strArr == null) {
                    if (i2 >= sSoSources.length) {
                        break;
                    }
                    strArr = sSoSources[i2].getLibraryDependencies(str);
                    i2++;
                }
            }
            return strArr;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static File getSoFile(String str) {
        File soFileByName;
        String mapLibName = MergedSoMapping.mapLibName(str);
        if (mapLibName != null) {
            str = mapLibName;
        }
        String mapLibraryName = System.mapLibraryName(str);
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                for (int i2 = 0; i2 < sSoSources.length; i2++) {
                    try {
                        soFileByName = sSoSources[i2].getSoFileByName(mapLibraryName);
                    } catch (IOException unused) {
                    }
                    if (soFileByName != null) {
                        return soFileByName;
                    }
                }
            }
            sSoSourcesLock.readLock().unlock();
            return null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean loadLibrary(String str) {
        return loadLibrary(str, 0);
    }

    public static boolean loadLibrary(String str, int i2) throws UnsatisfiedLinkError {
        SystemLoadLibraryWrapper systemLoadLibraryWrapper;
        Boolean loadLibraryOnNonAndroid = loadLibraryOnNonAndroid(str);
        if (loadLibraryOnNonAndroid != null) {
            return loadLibraryOnNonAndroid.booleanValue();
        }
        int i3 = sAppType;
        if ((i3 == 2 || i3 == 3) && (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) != null) {
            systemLoadLibraryWrapper.loadLibrary(str);
            return true;
        }
        String mapLibName = MergedSoMapping.mapLibName(str);
        return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i2, null);
    }

    @Nullable
    private static Boolean loadLibraryOnNonAndroid(String str) {
        Boolean valueOf;
        if (sSoSources == null) {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.readLock().lock();
            try {
                if (sSoSources == null) {
                    if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                        assertInitialized();
                    } else {
                        synchronized (SoLoader.class) {
                            boolean z = !sLoadedLibraries.contains(str);
                            if (z) {
                                SystemLoadLibraryWrapper systemLoadLibraryWrapper = sSystemLoadLibraryWrapper;
                                if (systemLoadLibraryWrapper != null) {
                                    systemLoadLibraryWrapper.loadLibrary(str);
                                } else {
                                    System.loadLibrary(str);
                                }
                            }
                            valueOf = Boolean.valueOf(z);
                        }
                        reentrantReadWriteLock.readLock().unlock();
                        return valueOf;
                    }
                }
                reentrantReadWriteLock.readLock().unlock();
                return null;
            } catch (Throwable th) {
                sSoSourcesLock.readLock().unlock();
                throw th;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibraryBySoName(String str, int i2, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, null, null, i2, threadPolicy);
    }

    private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        boolean z2 = false;
        do {
            try {
                z2 = loadLibraryBySoNameImpl(str, str2, str3, i2, threadPolicy);
                z = false;
                continue;
            } catch (UnsatisfiedLinkError e2) {
                int i3 = sSoSourcesVersion.get();
                sSoSourcesLock.writeLock().lock();
                try {
                    try {
                        if (sApplicationSoSource == null || !sApplicationSoSource.checkAndMaybeUpdate()) {
                            z = false;
                        } else {
                            Log.w(TAG, "sApplicationSoSource updated during load: " + str + ", attempting load again.");
                            sSoSourcesVersion.getAndIncrement();
                            z = true;
                        }
                        sSoSourcesLock.writeLock().unlock();
                        if (sSoSourcesVersion.get() == i3) {
                            throw e2;
                        }
                    } catch (IOException e3) {
                        throw new RuntimeException(e3);
                    }
                } catch (Throwable th) {
                    sSoSourcesLock.writeLock().unlock();
                    throw th;
                }
            }
        } while (z);
        return z2;
    }

    private static boolean loadLibraryBySoNameImpl(String str, @Nullable String str2, @Nullable String str3, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        Object obj;
        boolean z2 = false;
        if (TextUtils.isEmpty(str2) || !sLoadedAndMergedLibraries.contains(str2)) {
            synchronized (SoLoader.class) {
                HashSet<String> hashSet = sLoadedLibraries;
                if (!hashSet.contains(str)) {
                    z = false;
                } else if (str3 == null) {
                    return false;
                } else {
                    z = true;
                }
                Map<String, Object> map = sLoadingLibraries;
                if (map.containsKey(str)) {
                    obj = map.get(str);
                } else {
                    Object obj2 = new Object();
                    map.put(str, obj2);
                    obj = obj2;
                }
                ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
                reentrantReadWriteLock.readLock().lock();
                try {
                    synchronized (obj) {
                        if (!z) {
                            synchronized (SoLoader.class) {
                                if (hashSet.contains(str)) {
                                    if (str3 == null) {
                                        reentrantReadWriteLock.readLock().unlock();
                                        return false;
                                    }
                                    z = true;
                                }
                                if (!z) {
                                    try {
                                        if (Log.isLoggable(TAG, 3)) {
                                            Log.d(TAG, "About to load: " + str);
                                        }
                                        doLoadLibraryBySoName(str, i2, threadPolicy);
                                        if (Log.isLoggable(TAG, 3)) {
                                            Log.d(TAG, "Loaded: " + str);
                                        }
                                        synchronized (SoLoader.class) {
                                            hashSet.add(str);
                                        }
                                    } catch (UnsatisfiedLinkError e2) {
                                        String message = e2.getMessage();
                                        if (message != null && message.contains("unexpected e_machine:")) {
                                            throw new WrongAbiError(e2, message.substring(message.lastIndexOf("unexpected e_machine:")));
                                        }
                                        throw e2;
                                    }
                                }
                            }
                        }
                        if ((i2 & 16) == 0) {
                            if (!TextUtils.isEmpty(str2) && sLoadedAndMergedLibraries.contains(str2)) {
                                z2 = true;
                            }
                            if (str3 != null && !z2) {
                                boolean z3 = SYSTRACE_LIBRARY_LOADING;
                                if (z3) {
                                    Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", str2, "]");
                                }
                                try {
                                    if (Log.isLoggable(TAG, 3)) {
                                        Log.d(TAG, "About to merge: " + str2 + " / " + str);
                                    }
                                    MergedSoMapping.invokeJniOnload(str2);
                                    sLoadedAndMergedLibraries.add(str2);
                                    if (z3) {
                                        Api18TraceUtils.endSection();
                                    }
                                } catch (UnsatisfiedLinkError e3) {
                                    throw new RuntimeException("Failed to call JNI_OnLoad from '" + str2 + "', which has been merged into '" + str + "'.  See comment for details.", e3);
                                }
                            }
                        }
                        reentrantReadWriteLock.readLock().unlock();
                        return !z;
                    }
                } catch (Throwable th) {
                    sSoSourcesLock.readLock().unlock();
                    throw th;
                }
            }
        }
        return false;
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static void doLoadLibraryBySoName(String str, int i2, @Nullable StrictMode.ThreadPolicy threadPolicy) throws UnsatisfiedLinkError {
        boolean z;
        ReentrantReadWriteLock reentrantReadWriteLock;
        UnpackingSoSource[] unpackingSoSourceArr;
        ReentrantReadWriteLock reentrantReadWriteLock2 = sSoSourcesLock;
        reentrantReadWriteLock2.readLock().lock();
        try {
            if (sSoSources == null) {
                Log.e(TAG, "Could not load: " + str + " because no SO source exists");
                throw new UnsatisfiedLinkError("couldn't find DSO to load: " + str);
            }
            reentrantReadWriteLock2.readLock().unlock();
            int i3 = 0;
            if (threadPolicy == null) {
                threadPolicy = StrictMode.allowThreadDiskReads();
                z = true;
            } else {
                z = false;
            }
            if (SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
            }
            try {
                reentrantReadWriteLock2.readLock().lock();
                int i4 = 0;
                for (int i5 = 0; i4 == 0 && i5 < sSoSources.length; i5++) {
                    i4 = sSoSources[i5].loadLibrary(str, i2, threadPolicy);
                    if (i4 == 3 && sBackupSoSources != null) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "Trying backup SoSource for " + str);
                        }
                        for (UnpackingSoSource unpackingSoSource : sBackupSoSources) {
                            unpackingSoSource.prepare(str);
                            int loadLibrary = unpackingSoSource.loadLibrary(str, i2, threadPolicy);
                            if (loadLibrary == 1) {
                                i4 = loadLibrary;
                                break;
                            }
                        }
                    }
                }
                try {
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    if (i4 == 0 || i4 == 3) {
                        StringBuilder append = new StringBuilder().append("couldn't find DSO to load: ").append(str);
                        reentrantReadWriteLock.readLock().lock();
                        while (i3 < sSoSources.length) {
                            append.append("\n\tSoSource ").append(i3).append(": ").append(sSoSources[i3].toString());
                            i3++;
                        }
                        ApplicationSoSource applicationSoSource = sApplicationSoSource;
                        if (applicationSoSource != null) {
                            append.append("\n\tNative lib dir: ").append(ApplicationSoSource.getNativeLibDirFromContext(applicationSoSource.getUpdatedContext()).getAbsolutePath()).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                        sSoSourcesLock.readLock().unlock();
                        append.append(" result: ").append(i4);
                        String sb = append.toString();
                        Log.e(TAG, sb);
                        throw new UnsatisfiedLinkError(sb);
                    }
                } catch (Throwable th) {
                    th = th;
                    i3 = i4;
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    if (i3 == 0 || i3 == 3) {
                        StringBuilder append2 = new StringBuilder().append("couldn't find DSO to load: ").append(str);
                        String message = th.getMessage();
                        if (message == null) {
                            message = th.toString();
                        }
                        append2.append(" caused by: ").append(message);
                        th.printStackTrace();
                        append2.append(" result: ").append(i3);
                        String sb2 = append2.toString();
                        Log.e(TAG, sb2);
                        UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(sb2);
                        unsatisfiedLinkError.initCause(th);
                        throw unsatisfiedLinkError;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static String makeNonZipPath(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(CertificateUtil.DELIMITER);
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(CertificateUtil.DELIMITER, arrayList);
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource soSource : sSoSources) {
                File unpackLibrary = soSource.unpackLibrary(str);
                if (unpackLibrary != null) {
                    return unpackLibrary;
                }
            }
            sSoSourcesLock.readLock().unlock();
            throw new FileNotFoundException(str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        if (sSoSources != null) {
            return true;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            boolean z = sSoSources != null;
            reentrantReadWriteLock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion.get();
    }

    public static void prependSoSource(SoSource soSource) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            assertInitialized();
            soSource.prepare(makePrepareFlags());
            SoSource[] soSourceArr = new SoSource[sSoSources.length + 1];
            soSourceArr[0] = soSource;
            System.arraycopy(sSoSources, 0, soSourceArr, 1, sSoSources.length);
            sSoSources = soSourceArr;
            sSoSourcesVersion.getAndIncrement();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Prepended to SO sources: " + soSource);
            }
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            ArrayList arrayList = new ArrayList();
            SoSource[] soSourceArr = sSoSources;
            if (soSourceArr != null) {
                for (SoSource soSource : soSourceArr) {
                    soSource.addToLdLibraryPath(arrayList);
                }
            }
            String join = TextUtils.join(CertificateUtil.DELIMITER, arrayList);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "makeLdLibraryPath final path: " + join);
            }
            return join;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean areSoSourcesAbisSupported() {
        String[] soSourceAbis;
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                String[] supportedAbis = SysUtil.getSupportedAbis();
                for (SoSource soSource : sSoSources) {
                    for (String str : soSource.getSoSourceAbis()) {
                        boolean z = false;
                        for (int i2 = 0; i2 < supportedAbis.length && !z; i2++) {
                            z = str.equals(supportedAbis[i2]);
                        }
                        if (!z) {
                            Log.e(TAG, "abi not supported: " + str);
                            reentrantReadWriteLock = sSoSourcesLock;
                        }
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return true;
            }
            reentrantReadWriteLock.readLock().unlock();
            return false;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static boolean useDepsFile(Context context, boolean z, boolean z2) {
        return NativeDeps.useDepsFile(context, z, z2);
    }

    public static int getLoadedLibrariesCount() {
        return sLoadedLibraries.size();
    }
}
