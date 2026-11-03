package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.facebook.soloader.MinElf;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import dalvik.system.BaseDexClassLoader;
import java.io.DataOutput;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class SysUtil {
    private static final long APK_DEP_BLOCK_METADATA_LENGTH = 20;
    private static final byte APK_SIGNATURE_VERSION = 1;
    private static final String TAG = "SysUtil";

    public static int findAbiScore(String[] strArr, String str) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2] != null && str.equals(strArr[i2])) {
                return i2;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            Log.e(TAG, "Enable write permission failed: " + parentFile);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.getSupportedAbis();
        }
        return Build.VERSION.SDK_INT >= 21 ? LollipopSysdeps.getSupportedAbis() : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j2) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fileDescriptor, j2);
        }
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File file2 : listFiles) {
                dumbDeleteRecursive(file2);
            }
        }
        deleteOrThrow(file);
    }

    /* loaded from: classes8.dex */
    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            try {
                if (is64Bit()) {
                    treeSet.add(MinElf.ISA.AARCH64.toString());
                    treeSet.add(MinElf.ISA.X86_64.toString());
                } else {
                    treeSet.add(MinElf.ISA.ARM.toString());
                    treeSet.add(MinElf.ISA.X86.toString());
                }
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (treeSet.contains(str)) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            } catch (ErrnoException e2) {
                Log.e(SysUtil.TAG, String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(strArr), Integer.valueOf(e2.errno), e2.getMessage()));
                return Build.SUPPORTED_ABIS;
            }
        }

        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j2) throws IOException {
            try {
                Os.posix_fallocate(fileDescriptor, 0L, j2);
            } catch (ErrnoException e2) {
                if (e2.errno != OsConstants.EOPNOTSUPP && e2.errno != OsConstants.ENOSYS && e2.errno != OsConstants.EINVAL) {
                    throw new IOException(e2.toString(), e2);
                }
            }
        }

        public static boolean is64Bit() throws ErrnoException {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    /* loaded from: classes8.dex */
    private static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            if (is64Bit()) {
                treeSet.add(MinElf.ISA.AARCH64.toString());
                treeSet.add(MinElf.ISA.X86_64.toString());
            } else {
                treeSet.add(MinElf.ISA.ARM.toString());
                treeSet.add(MinElf.ISA.X86.toString());
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        public static boolean is64Bit() {
            return Process.is64Bit();
        }

        public static boolean isSupportedDirectLoad(Context context, int i2) throws IOException {
            if (i2 == 2) {
                return isApkUncompressedDso(context);
            }
            return isDisabledExtractNativeLibs(context);
        }

        public static boolean isDisabledExtractNativeLibs(Context context) {
            return context != null && (context.getApplicationInfo().flags & DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP) == 0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
            if (r1.getMethod() != 0) goto L18;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
            r2 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
            r5.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
            return r2;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static boolean isApkUncompressedDso(android.content.Context r5) throws java.io.IOException {
            /*
                java.io.File r0 = new java.io.File
                android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo()
                java.lang.String r5 = r5.sourceDir
                r0.<init>(r5)
                java.util.zip.ZipFile r5 = new java.util.zip.ZipFile
                r5.<init>(r0)
                java.util.Enumeration r0 = r5.entries()     // Catch: java.lang.Throwable -> L4a
            L14:
                boolean r1 = r0.hasMoreElements()     // Catch: java.lang.Throwable -> L4a
                r2 = 0
                if (r1 == 0) goto L46
                java.lang.Object r1 = r0.nextElement()     // Catch: java.lang.Throwable -> L4a
                java.util.zip.ZipEntry r1 = (java.util.zip.ZipEntry) r1     // Catch: java.lang.Throwable -> L4a
                if (r1 == 0) goto L14
                java.lang.String r3 = r1.getName()     // Catch: java.lang.Throwable -> L4a
                java.lang.String r4 = ".so"
                boolean r3 = r3.endsWith(r4)     // Catch: java.lang.Throwable -> L4a
                if (r3 == 0) goto L14
                java.lang.String r3 = r1.getName()     // Catch: java.lang.Throwable -> L4a
                java.lang.String r4 = "/lib"
                boolean r3 = r3.contains(r4)     // Catch: java.lang.Throwable -> L4a
                if (r3 == 0) goto L14
                int r0 = r1.getMethod()     // Catch: java.lang.Throwable -> L4a
                if (r0 != 0) goto L42
                r2 = 1
            L42:
                r5.close()
                return r2
            L46:
                r5.close()
                return r2
            L4a:
                r0 = move-exception
                r5.close()     // Catch: java.lang.Throwable -> L4f
                goto L53
            L4f:
                r5 = move-exception
                r0.addSuppressed(r5)
            L53:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SysUtil.MarshmallowSysdeps.isApkUncompressedDso(android.content.Context):boolean");
        }
    }

    public static void mkdirOrThrow(File file) throws IOException {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int copyBytes(DataOutput dataOutput, InputStream inputStream, int i2, byte[] bArr) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i2 - i3));
            if (read == -1) {
                break;
            }
            dataOutput.write(bArr, 0, read);
            i3 += read;
        }
        return i3;
    }

    public static void fsyncRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("cannot list directory " + file);
            }
            for (File file2 : listFiles) {
                fsyncRecursive(file2);
            }
        } else if (!file.getPath().endsWith("_lock")) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
            try {
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
            } catch (Throwable th) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    private static long getParcelPadSize(long j2) {
        return j2 + ((4 - (j2 % 4)) % 4);
    }

    public static long getApkDepBlockLength(File file) throws IOException {
        return getParcelPadSize((file.getCanonicalFile().getPath().length() + 1) * 2) + 20;
    }

    public static byte[] makeApkDepBlock(File file, Context context) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 1);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(getAppVersionCode(context));
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }

    public static boolean is64Bit() {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.is64Bit();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return LollipopSysdeps.is64Bit();
            } catch (Exception e2) {
                Log.e(TAG, String.format("Could not read /proc/self/exe. Err msg: %s", e2.getMessage()));
                return false;
            }
        }
        return false;
    }

    public static boolean isSupportedDirectLoad(Context context, int i2) throws IOException {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.isSupportedDirectLoad(context, i2);
        }
        return false;
    }

    public static boolean isDisabledExtractNativeLibs(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.isDisabledExtractNativeLibs(context);
        }
        return false;
    }

    @Nullable
    public static FileLocker getOrCreateLockOnDir(File file, File file2, boolean z) throws IOException {
        boolean z2 = true;
        try {
            if (z) {
                return FileLocker.lock(file2);
            }
            return FileLocker.tryLock(file2);
        } catch (FileNotFoundException e2) {
            try {
                if (file.setWritable(true)) {
                    if (z) {
                        FileLocker lock = FileLocker.lock(file2);
                        if (!file.setWritable(false)) {
                            Log.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
                        }
                        return lock;
                    }
                    FileLocker tryLock = FileLocker.tryLock(file2);
                    if (!file.setWritable(false)) {
                        Log.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
                    }
                    return tryLock;
                }
                throw e2;
            } catch (Throwable th) {
                th = th;
                if (z2 && !file.setWritable(false)) {
                    Log.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            if (z2) {
                Log.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
            }
            throw th;
        }
    }

    public static String getBaseName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
    }

    /* loaded from: classes8.dex */
    public static class Api14Utils {
        public static String getClassLoaderLdLoadLibrary() {
            ClassLoader classLoader = SoLoader.class.getClassLoader();
            if (classLoader != null && !(classLoader instanceof BaseDexClassLoader)) {
                throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
            }
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e2) {
                throw new RuntimeException("Cannot call getLdLibraryPath", e2);
            }
        }
    }
}
