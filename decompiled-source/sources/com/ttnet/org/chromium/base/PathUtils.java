package com.ttnet.org.chromium.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.system.Os;
import android.text.TextUtils;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForQ;
import com.ttnet.org.chromium.base.compat.ApiHelperForR;
import com.ttnet.org.chromium.base.task.AsyncTask;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
@MainDex
/* loaded from: classes9.dex */
public abstract class PathUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CACHE_DIRECTORY = 2;
    private static final int DATA_DIRECTORY = 0;
    private static final int NUM_DIRECTORIES = 3;
    private static final String TAG = "PathUtils";
    private static final int THUMBNAIL_DIRECTORY = 1;
    private static final String THUMBNAIL_DIRECTORY_NAME = "textures";
    private static String sCacheSubDirectory;
    private static String sDataDirectorySuffix;
    private static FutureTask<String[]> sDirPathFetchTask;
    private static final AtomicBoolean sInitializationStarted = new AtomicBoolean();

    private PathUtils() {
    }

    public static void resetForTesting() {
        sInitializationStarted.set(false);
        sDirPathFetchTask = null;
        sDataDirectorySuffix = null;
        sCacheSubDirectory = null;
    }

    private static String[] getOrComputeDirectoryPaths() {
        if (!sDirPathFetchTask.isDone()) {
            StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
            try {
                sDirPathFetchTask.run();
                if (allowDiskWrites != null) {
                    allowDiskWrites.close();
                }
            } catch (Throwable th) {
                if (allowDiskWrites != null) {
                    try {
                        allowDiskWrites.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        try {
            return sDirPathFetchTask.get();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private static void chmod(String str, int i2) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        try {
            Os.chmod(str, i2);
        } catch (Exception unused) {
            Log.e(TAG, "Failed to set permissions for path \"" + str + "\"", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] setPrivateDataDirectorySuffixInternal() {
        String[] strArr = new String[3];
        Context applicationContext = ContextUtils.getApplicationContext();
        strArr[0] = applicationContext.getDir(sDataDirectorySuffix, 0).getPath();
        chmod(strArr[0], 448);
        strArr[1] = applicationContext.getDir(THUMBNAIL_DIRECTORY_NAME, 0).getPath();
        if (applicationContext.getCacheDir() != null) {
            if (sCacheSubDirectory == null) {
                strArr[2] = applicationContext.getCacheDir().getPath();
            } else {
                File file = new File(applicationContext.getCacheDir(), sCacheSubDirectory);
                file.mkdir();
                strArr[2] = file.getPath();
                chmod(strArr[2], 1472);
            }
        }
        return strArr;
    }

    public static void setPrivateDataDirectorySuffix(String str, String str2) {
        if (sInitializationStarted.getAndSet(true)) {
            return;
        }
        sDataDirectorySuffix = str;
        sCacheSubDirectory = str2;
        sDirPathFetchTask = new FutureTask<>(new Callable() { // from class: com.ttnet.org.chromium.base.PathUtils$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String[] privateDataDirectorySuffixInternal;
                privateDataDirectorySuffixInternal = PathUtils.setPrivateDataDirectorySuffixInternal();
                return privateDataDirectorySuffixInternal;
            }
        });
        AsyncTask.THREAD_POOL_EXECUTOR.execute(sDirPathFetchTask);
    }

    public static void setPrivateDataDirectorySuffix(String str) {
        setPrivateDataDirectorySuffix(str, null);
    }

    private static String getDirectoryPath(int i2) {
        return getOrComputeDirectoryPaths()[i2];
    }

    public static String getDataDirectory() {
        return getDirectoryPath(0);
    }

    public static String getCacheDirectory() {
        return getDirectoryPath(2);
    }

    public static String getThumbnailCacheDirectory() {
        return getDirectoryPath(1);
    }

    public static String getDownloadsDirectory() {
        StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                String[] allPrivateDownloadsDirectories = getAllPrivateDownloadsDirectories();
                String str = allPrivateDownloadsDirectories.length == 0 ? "" : allPrivateDownloadsDirectories[0];
                if (allowDiskReads != null) {
                    allowDiskReads.close();
                }
                return str;
            }
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            if (allowDiskReads != null) {
                allowDiskReads.close();
            }
            return path;
        } catch (Throwable th) {
            if (allowDiskReads != null) {
                try {
                    allowDiskReads.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static String[] getAllPrivateDownloadsDirectories() {
        List arrayList = new ArrayList();
        StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
        try {
            File[] externalFilesDirs = ContextUtils.getApplicationContext().getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            if (externalFilesDirs != null) {
                arrayList = Arrays.asList(externalFilesDirs);
            }
            if (allowDiskWrites != null) {
                allowDiskWrites.close();
            }
            return toAbsolutePathStrings(arrayList);
        } catch (Throwable th) {
            if (allowDiskWrites != null) {
                try {
                    allowDiskWrites.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static String[] getExternalDownloadVolumesNames() {
        ArrayList arrayList = new ArrayList();
        for (String str : ApiHelperForQ.getExternalVolumeNames(ContextUtils.getApplicationContext())) {
            if (!TextUtils.isEmpty(str) && !str.contains("external_primary")) {
                File volumeDir = ApiHelperForR.getVolumeDir((StorageManager) ApiHelperForM.getSystemService(ContextUtils.getApplicationContext(), StorageManager.class), MediaStore.Files.getContentUri(str));
                File file = new File(volumeDir, Environment.DIRECTORY_DOWNLOADS);
                if (!file.isDirectory()) {
                    Log.w(TAG, "Download dir missing: %s, parent dir:%s, isDirectory:%s", file.getAbsolutePath(), volumeDir.getAbsolutePath(), Boolean.valueOf(volumeDir.isDirectory()));
                }
                arrayList.add(file);
            }
        }
        return toAbsolutePathStrings(arrayList);
    }

    private static String[] toAbsolutePathStrings(List<File> list) {
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            if (file != null && !TextUtils.isEmpty(file.getAbsolutePath())) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = ContextUtils.getApplicationContext().getApplicationInfo();
        return ((applicationInfo.flags & 128) != 0 || (applicationInfo.flags & 1) == 0) ? applicationInfo.nativeLibraryDir : "/system/lib/";
    }

    public static String getAppExternalCacheDirectory() {
        File externalCacheDir = ContextUtils.getApplicationContext().getExternalCacheDir();
        return externalCacheDir == null ? "" : externalCacheDir.getAbsolutePath();
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
