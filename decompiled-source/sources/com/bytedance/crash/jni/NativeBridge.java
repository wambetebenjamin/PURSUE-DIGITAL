package com.bytedance.crash.jni;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.crash.Global;
import com.bytedance.crash.anr.e;
import com.bytedance.crash.config.f;
import com.bytedance.crash.crash.NativeCrashSummary;
import com.bytedance.crash.crash.b;
import com.bytedance.crash.runtime.n;
import com.bytedance.crash.terminate.d;
import com.bytedance.crash.util.ad;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
public class NativeBridge {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6803a = "unknown";

    /* renamed from: b  reason: collision with root package name */
    private static final int f6804b = 1000;

    /* renamed from: c  reason: collision with root package name */
    private static final double f6805c = 1.4d;

    /* renamed from: d  reason: collision with root package name */
    private static long f6806d = 100;

    /* renamed from: e  reason: collision with root package name */
    private static final long f6807e = 3600000;
    private static volatile boolean isSoLoaded;

    private static native void doRegister(String str, String str2);

    private static native void doSetDropDataState(int i2);

    private static native void nAnrDumpNativeInfo(long j2);

    private static native long nAnrDumpNativeInit(String str);

    private static native void nAnrDumpNativeRelease(long j2);

    private static native void nAnrDumpTrace(String str);

    private static native void nAnrEnterMonitorLooper();

    private static native void nAnrInitOnMainThread();

    public static native void nAnrNativeProfilerDump(long j2, String str, long j3);

    public static native void nAnrNativeProfilerExit(long j2);

    public static native void nAnrNativeProfilerFormat(String str, long[] jArr);

    public static native int nAnrNativeProfilerJvmStart(long j2);

    public static native String nAnrNativeProfilerRunTest(String str);

    public static native long nAnrNativeProfilerStart();

    public static native void nAnrNativeProfilerStop(long j2);

    private static native void nCheckSigHandler();

    private static native void nCoredumpNativeInit(String str);

    private static native long nCrashDumpNativeInfo(long j2);

    private static native void nDumpLogcat(String str, int i2);

    private static native void nDumpOsMemory(String str);

    private static native void nDumperLateInit();

    private static native void nEnablePrioriryParams(boolean z, boolean z2, int i2);

    private static native int nFlock(String str);

    private static native int nGetApexVersion();

    private static native String nGetBuildID(String str);

    private static native int nGetFdCount(String str);

    private static native String nGetFdLeakReason(String str);

    private static native int nGetFdLimit();

    private static native int nGetJvmMonitorState();

    private static native String nGetMallocType();

    private static native String nGetNativePthreadKeyLeakLibrary(String str);

    private static native String[] nGetOOMReason(String str);

    private static native String nGetStackTrace(boolean z);

    private static native int nGetThreadCount(String str);

    private static native long nGetThreadCpuTimeMills(int i2);

    private static native String nGetThreadLeakLibrary(String str);

    private static native String nGetThreadLeakName(String str);

    private static native long nGetVmRss(String str);

    private static native long nGetVmSize(String str, int i2);

    private static native int nGetVmaCount(String str);

    private static native boolean nIncreaseFdLimit();

    private static native boolean nIs64BitRuntime();

    private static native String nLoadNativeCrashAbortReason(String str);

    private static native String nLoadNativeCrashBacktrace(String str);

    private static native NativeCrashSummary nLoadNativeCrashSummary(String str);

    private static native int nNativePthreadKeyCount(String str);

    private static native void nNotifyUploadDone();

    private static native int nOpenNativeCrashMonitor();

    private static native void nParseSmaps(String str);

    private static native void nPriorityMonitorInit(String str);

    private static native void nRecoverSignalHandler();

    private static native void nResetNativeInfoLatches();

    private static native void nSet64Bit(boolean z);

    private static native void nSetAlogFlushAddr(long j2);

    private static native void nSetAnrDumpAsync(boolean z);

    private static native void nSetAnrResendSigquit(boolean z);

    private static native void nSetAppVersion(String str);

    private static native void nSetDumpTraceTryCatch(boolean z);

    private static native boolean nSignalToProcess(int i2, int i3);

    private static native void nStartDumperThread();

    private static native int nStartNativeCrashMonitor(int i2, String str, String str2, String str3, String str4, String str5, int i3, long j2, long j3, String str6, int i4);

    private static native void nStartProfilerJavaLockMonitor();

    private static native long nStringDumperCreate(String str, int i2);

    private static native void nStringDumperDumpByteArray(long j2, byte[] bArr, int i2);

    private static native void nStringDumperDumpCharArray(long j2, char[] cArr, int i2);

    private static native void nStringDumperDumpString(long j2, String str, int i2);

    private static native void nStringDumperFlushBuffer(long j2);

    private static native void nStringDumperRelease(long j2);

    private static native void nUnFlock(int i2);

    private static native int nativeDumpHprof(int i2, String str);

    private static native void nativeDumpTags(String str);

    private static native String[] nativeGetFdListForAPM();

    private static native String[] nativeGetTags();

    private static native void unRegister(String str);

    public static void a() {
        ad.loadLib("npth");
    }

    public static boolean b() {
        return isSoLoaded;
    }

    public static void a(Context context, File file) {
        if (b()) {
            String absolutePath = file.getAbsolutePath();
            String str = context.getApplicationInfo().nativeLibraryDir;
            File b2 = f.b();
            if (!b2.exists()) {
                b2.mkdirs();
            }
            nStartNativeCrashMonitor(Build.VERSION.SDK_INT, str, absolutePath, new File(b2, b.c()).getAbsolutePath(), d.d(), Global.a(context).getAbsolutePath(), 4020188, Global.g(), Global.i(), Global.j(), Global.m() ? 1 : 0);
            return;
        }
        com.bytedance.crash.diagnose.b.b("npth so load fail", new Exception());
    }

    public static void c() {
        if (b()) {
            nStartDumperThread();
        }
    }

    public static void d() {
        if (b()) {
            nDumperLateInit();
        }
    }

    public static void e() {
        if (b()) {
            nResetNativeInfoLatches();
        }
    }

    public static long a(long j2) {
        if (b()) {
            return nCrashDumpNativeInfo(j2);
        }
        return 0L;
    }

    public static void b(long j2) {
        if (b()) {
            nAnrDumpNativeInfo(j2);
        }
    }

    public static void c(long j2) {
        if (b()) {
            nAnrDumpNativeRelease(j2);
        }
    }

    public static long a(String str) {
        if (b()) {
            return nAnrDumpNativeInit(str);
        }
        return 0L;
    }

    public static int b(String str) {
        if (b()) {
            return nFlock(str);
        }
        return -1;
    }

    public static void a(int i2) {
        if (b()) {
            nUnFlock(i2);
        }
    }

    public static void d(long j2) {
        if (b()) {
            nSetAlogFlushAddr(j2);
        }
    }

    public static void c(String str) {
        if (b()) {
            nCoredumpNativeInit(str);
        }
    }

    private static void handleCrashFromDumperThread(long j2, int i2) {
        b.b(j2, i2);
    }

    private static void reportAnrEventFromNative(int i2) {
        e.a(i2);
    }

    public static void f() {
        if (b()) {
            nNotifyUploadDone();
        }
    }

    public static void g() {
        if (b()) {
            nRecoverSignalHandler();
        }
    }

    public static NativeCrashSummary d(String str) {
        if (b()) {
            return nLoadNativeCrashSummary(str);
        }
        return null;
    }

    public static void a(String str, int i2, boolean z) {
        if (b()) {
            try {
                if (Build.VERSION.SDK_INT == 23) {
                    String str2 = Build.MANUFACTURER;
                    if (!TextUtils.isEmpty(str2)) {
                        if (str2.toLowerCase(Locale.getDefault()).contains("oppo")) {
                            return;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
            nDumpLogcat(str, i2);
            if (z) {
                SystemClock.sleep(1000L);
            }
        }
    }

    public static void a(String str, boolean z) {
        if (b()) {
            nDumpOsMemory(str);
            if (z) {
                SystemClock.sleep(1000L);
            }
        }
    }

    public static int e(String str) {
        if (b()) {
            return nGetThreadCount(str);
        }
        return 0;
    }

    public static int f(String str) {
        if (b()) {
            return nGetFdCount(str);
        }
        return 0;
    }

    public static long b(String str, boolean z) {
        if (b()) {
            return nGetVmSize(str, z ? 1 : 0);
        }
        return 0L;
    }

    public static long g(String str) {
        if (b()) {
            return nGetVmRss(str);
        }
        return 0L;
    }

    public static int h() {
        if (b()) {
            return nGetFdLimit();
        }
        return Build.VERSION.SDK_INT > 27 ? 32768 : 1024;
    }

    public static String a(File file) {
        String nGetFdLeakReason = b() ? nGetFdLeakReason(file.getAbsolutePath()) : null;
        return TextUtils.isEmpty(nGetFdLeakReason) ? "unknown" : nGetFdLeakReason;
    }

    public static String b(File file) {
        String nGetNativePthreadKeyLeakLibrary = b() ? nGetNativePthreadKeyLeakLibrary(file.getAbsolutePath()) : null;
        return TextUtils.isEmpty(nGetNativePthreadKeyLeakLibrary) ? "unknown" : nGetNativePthreadKeyLeakLibrary;
    }

    public static String c(File file) {
        String nGetThreadLeakName = b() ? nGetThreadLeakName(file.getAbsolutePath()) : null;
        return TextUtils.isEmpty(nGetThreadLeakName) ? "unknown" : nGetThreadLeakName;
    }

    public static String d(File file) {
        String nGetThreadLeakLibrary = b() ? nGetThreadLeakLibrary(file.getAbsolutePath()) : null;
        return TextUtils.isEmpty(nGetThreadLeakLibrary) ? "unknown" : nGetThreadLeakLibrary;
    }

    public static String h(String str) {
        if (b()) {
            return nLoadNativeCrashBacktrace(str);
        }
        return null;
    }

    public static String i(String str) {
        if (b()) {
            return nLoadNativeCrashAbortReason(str);
        }
        return null;
    }

    public static String j(String str) {
        if (b()) {
            return nGetBuildID(str);
        }
        return null;
    }

    public static boolean i() {
        if (b()) {
            return nIs64BitRuntime();
        }
        return false;
    }

    public static boolean a(int i2, int i3) {
        if (b()) {
            return nSignalToProcess(i2, i3);
        }
        return false;
    }

    public static long b(int i2) {
        if (b()) {
            return nGetThreadCpuTimeMills(i2);
        }
        return -1L;
    }

    public static boolean j() {
        if (b()) {
            return nIncreaseFdLimit();
        }
        return false;
    }

    public static void k() {
        if (b()) {
            nAnrInitOnMainThread();
        }
    }

    public static void l() {
        if (b()) {
            nAnrEnterMonitorLooper();
        }
    }

    public static void k(String str) {
        if (b()) {
            nAnrDumpTrace(str);
        }
    }

    public static void a(long j2, char[] cArr, int i2) {
        if (b()) {
            nStringDumperDumpCharArray(j2, cArr, i2);
        }
    }

    public static void a(long j2, byte[] bArr, int i2) {
        if (b()) {
            nStringDumperDumpByteArray(j2, bArr, i2);
        }
    }

    public static void a(long j2, String str, int i2) {
        if (b()) {
            nStringDumperDumpString(j2, str, i2);
        }
    }

    public static long a(String str, int i2) {
        if (b()) {
            return nStringDumperCreate(str, i2);
        }
        return 0L;
    }

    public static void e(long j2) {
        if (b()) {
            nStringDumperRelease(j2);
        }
    }

    public static void f(long j2) {
        if (b()) {
            nStringDumperFlushBuffer(j2);
        }
    }

    public static void l(String str) {
        if (b()) {
            nSetAppVersion(str);
        }
    }

    @Deprecated
    public static void m() {
        if (b()) {
            n.getDefaultHandler().postDelayed(new Runnable() { // from class: com.bytedance.crash.jni.NativeBridge.1
                @Override // java.lang.Runnable
                public void run() {
                    long unused = NativeBridge.f6806d = (long) (NativeBridge.f6806d * NativeBridge.f6805c);
                    NativeBridge.w();
                    if (NativeBridge.f6806d > 3600000) {
                        return;
                    }
                    n.getDefaultHandler().postDelayed(this, NativeBridge.f6806d);
                }
            }, f6806d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void w() {
        if (b()) {
            nCheckSigHandler();
        }
    }

    @Deprecated
    public static List<String> n() {
        if (b()) {
            try {
                ArrayList arrayList = new ArrayList();
                String[] nativeGetFdListForAPM = nativeGetFdListForAPM();
                if (nativeGetFdListForAPM != null) {
                    arrayList.addAll(Arrays.asList(nativeGetFdListForAPM));
                }
                return arrayList;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static void c(int i2) {
        if (b()) {
            doSetDropDataState(i2);
        }
    }

    public static int m(String str) {
        if (b()) {
            return nNativePthreadKeyCount(str);
        }
        return 0;
    }

    public static int n(String str) {
        if (b()) {
            return nGetVmaCount(str);
        }
        return 0;
    }

    public static String[] o(String str) {
        if (b()) {
            return nGetOOMReason(str);
        }
        return null;
    }

    public static int p(String str) {
        if (b()) {
            try {
                return nativeDumpHprof(Build.VERSION.SDK_INT, str);
            } catch (Throwable unused) {
                return -2;
            }
        }
        return -1;
    }

    public static HashMap<String, String> o() {
        String[] nativeGetTags = nativeGetTags();
        HashMap<String, String> hashMap = new HashMap<>();
        if (nativeGetTags == null) {
            return hashMap;
        }
        if (nativeGetTags.length < 1) {
            return hashMap;
        }
        for (int i2 = 0; i2 < nativeGetTags.length; i2 += 2) {
            hashMap.put(nativeGetTags[i2], nativeGetTags[i2 + 1]);
        }
        return hashMap;
    }

    public static void q(String str) {
        if (!b() || str.length() <= 0) {
            return;
        }
        nPriorityMonitorInit(str);
    }

    public static void a(boolean z, boolean z2, int i2) {
        if (b()) {
            nEnablePrioriryParams(z, z2, i2);
        }
    }

    public static void r(String str) {
        if (b()) {
            nParseSmaps(str);
        }
    }

    public static boolean a(String str, String str2) {
        if (b()) {
            doRegister(str, str2);
            return true;
        }
        return false;
    }

    public static boolean s(String str) {
        if (b() && !TextUtils.isEmpty(str)) {
            unRegister(str);
            return true;
        }
        return false;
    }

    public static void t(String str) {
        if (b()) {
            try {
                nativeDumpTags(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static void p() {
        if (b()) {
            nStartProfilerJavaLockMonitor();
        }
    }

    public static void a(boolean z) {
        if (isSoLoaded) {
            nSet64Bit(z);
        }
    }

    public static void b(boolean z) {
        if (isSoLoaded) {
            nSetAnrDumpAsync(z);
        }
    }

    public static void c(boolean z) {
        if (isSoLoaded) {
            nSetDumpTraceTryCatch(z);
        }
    }

    public static void d(boolean z) {
        if (isSoLoaded) {
            nSetAnrResendSigquit(z);
        }
    }

    public static String e(boolean z) {
        if (b()) {
            try {
                return nGetStackTrace(z);
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static int q() {
        if (isSoLoaded) {
            try {
                return nGetJvmMonitorState();
            } catch (Throwable unused) {
                return -1;
            }
        }
        return -1;
    }

    public static int r() {
        if (isSoLoaded) {
            try {
                return nGetApexVersion();
            } catch (Throwable unused) {
                return -1;
            }
        }
        return -1;
    }

    public static int s() {
        if (isSoLoaded) {
            try {
                return nOpenNativeCrashMonitor();
            } catch (Throwable unused) {
                return -1;
            }
        }
        return -1;
    }

    public static String t() {
        if (b()) {
            try {
                return nGetMallocType();
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }
}
