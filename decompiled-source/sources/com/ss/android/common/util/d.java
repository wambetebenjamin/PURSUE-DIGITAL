package com.ss.android.common.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.common.utility.reflect.Reflect;
import com.facebook.internal.security.CertificateUtil;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ttnet.org.chromium.net.NetError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Set;
/* compiled from: ToolUtils.java */
/* loaded from: classes9.dex */
public final class d {
    public static final String NODEX_PROCESS_SUFFIX = ":nodex";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18098a = 16777216;

    /* renamed from: b  reason: collision with root package name */
    private static String f18099b = null;

    /* renamed from: c  reason: collision with root package name */
    private static String f18100c = null;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f18101d = true;
    public static String mMsgProcessSuffix = ":push";
    public static boolean sIsInited = false;
    public static boolean sIsMiui = false;

    private d() {
    }

    public static void setMessageProcessSuffix(String str) {
        mMsgProcessSuffix = str;
    }

    public static Intent getLaunchIntentForPackage(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return launchIntentForPackage;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            Logger.d("ToolUtils", "add category LAUNCHER in launch intent");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        return launchIntentForPackage;
    }

    public static boolean isInstalledApp(Context context, String str) {
        if (context == null || o.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 16777216) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isInstalledApp(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        return (intent == null || (queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536)) == null || queryIntentActivities.size() <= 0) ? false : true;
    }

    public static void runApplication(Context context, String str, String str2) {
        Intent launchIntentForPackage;
        if ((!o.isEmpty(str) ? isInstalledApp(context, str) : false) && (launchIntentForPackage = getLaunchIntentForPackage(context, str)) != null) {
            context.startActivity(launchIntentForPackage);
            return;
        }
        if (!o.isEmpty(str2)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
                return;
            } catch (Exception unused) {
            }
        }
        if (o.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        try {
            context.startActivity(intent);
        } catch (Exception unused2) {
        }
    }

    public static boolean isHuaweiDevice() {
        try {
            if (o.isEmpty(Build.BRAND) || !Build.BRAND.toLowerCase(Locale.getDefault()).startsWith("huawei")) {
                if (o.isEmpty(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase(Locale.getDefault()).startsWith("huawei")) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isEmui(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getEmuiInfo();
        }
        return (!TextUtils.isEmpty(str) && str.toLowerCase(Locale.getDefault()).startsWith("emotionui")) || isHuaweiDevice();
    }

    public static String getEmuiInfo() {
        return a("ro.build.version.emui");
    }

    private static String a(String str) {
        Throwable th;
        String str2;
        BufferedReader bufferedReader;
        if (!o.isEmpty(f18099b)) {
            return f18099b;
        }
        BufferedReader bufferedReader2 = null;
        String str3 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
        } catch (Throwable th2) {
            th = th2;
            str2 = null;
        }
        try {
            str3 = bufferedReader.readLine();
            bufferedReader.close();
            f18099b = str3;
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                Logger.e("ToolUtils", "Exception while closing InputStream", e2);
            }
            return str3;
        } catch (Throwable th3) {
            str2 = str3;
            bufferedReader2 = bufferedReader;
            th = th3;
            try {
                Logger.e("ToolUtils", "Unable to read sysprop " + str, th);
                return str2;
            } finally {
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                        Logger.e("ToolUtils", "Exception while closing InputStream", e3);
                    }
                }
            }
        }
    }

    public static boolean isMiui() {
        if (!sIsInited) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    sIsMiui = true;
                    sIsInited = true;
                    return true;
                }
            } catch (Exception unused) {
            }
            sIsInited = true;
        }
        return sIsMiui;
    }

    public static boolean isFlyme() {
        if (o.isEmpty(Build.DISPLAY) || Build.DISPLAY.indexOf("Flyme") < 0) {
            return !o.isEmpty(Build.USER) && Build.USER.equals("flyme");
        }
        return true;
    }

    public static void installShortcut(Context context, String str) {
        if (context == null) {
            return;
        }
        try {
            Intent launchIntentForPackage = getLaunchIntentForPackage(context, str);
            if (launchIntentForPackage == null) {
                return;
            }
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
            String resourceName = resourcesForApplication.getResourceName(applicationInfo.icon);
            CharSequence charSequence = null;
            try {
                int i2 = packageManager.getActivityInfo(launchIntentForPackage.getComponent(), 0).labelRes;
                if (i2 > 0) {
                    charSequence = resourcesForApplication.getString(i2);
                }
            } catch (Resources.NotFoundException unused) {
                charSequence = packageManager.getApplicationLabel(applicationInfo);
            }
            if (charSequence == null) {
                return;
            }
            Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent.putExtra("android.intent.extra.shortcut.NAME", charSequence);
            intent.putExtra("android.intent.extra.shortcut.INTENT", launchIntentForPackage);
            intent.putExtra("launch_from", 1);
            Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
            shortcutIconResource.packageName = str;
            shortcutIconResource.resourceName = resourceName;
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", shortcutIconResource);
            intent.putExtra("duplicate", false);
            context.sendBroadcast(intent);
        } catch (Exception e2) {
            Log.w("ToolUtils", "addAppShortcut failed: " + e2);
        }
    }

    public static void clearDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath());
                } else if (listFiles[i2].isFile()) {
                    listFiles[i2].delete();
                }
            }
        }
    }

    public static void removeDir(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath());
                } else {
                    listFiles[i2].delete();
                }
            }
            file.delete();
        }
    }

    public static void clearDir(String str, Set<String> set) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath(), set);
                } else if (listFiles[i2].isFile()) {
                    String name = listFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        listFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static void removeDir(String str, Set<String> set) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    removeDir(listFiles[i2].getAbsolutePath(), set);
                } else {
                    String name = listFiles[i2].getName();
                    if (set == null || !set.contains(name)) {
                        listFiles[i2].delete();
                    }
                }
            }
        }
    }

    public static long getDirectorySize(File file, boolean z) {
        File[] listFiles;
        long length;
        long j2 = 0;
        if (file.exists() || !file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    length = getDirectorySize(file2, z);
                } else if (file2.isFile()) {
                    length = file2.length();
                }
                j2 += length;
            }
            return j2;
        }
        return 0L;
    }

    public static void addImageMedia(Context context, String str) {
        try {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str))));
        } catch (Exception e2) {
            Logger.w("ToolUtils", "add image media exception: " + e2);
        }
    }

    public static void addImageMedia2(Context context, String str) {
        try {
            new a(context, str).startScan();
        } catch (Exception unused) {
        }
    }

    /* compiled from: ToolUtils.java */
    /* loaded from: classes9.dex */
    public static class a implements MediaScannerConnection.MediaScannerConnectionClient {

        /* renamed from: a  reason: collision with root package name */
        private MediaScannerConnection f18102a;

        /* renamed from: b  reason: collision with root package name */
        private Context f18103b;

        /* renamed from: c  reason: collision with root package name */
        private String f18104c;

        public a(Context context, String str) {
            this.f18103b = context;
            this.f18104c = str;
        }

        public void startScan() {
            MediaScannerConnection mediaScannerConnection = this.f18102a;
            if (mediaScannerConnection != null && mediaScannerConnection.isConnected()) {
                this.f18102a.disconnect();
            }
            MediaScannerConnection mediaScannerConnection2 = new MediaScannerConnection(this.f18103b, this);
            this.f18102a = mediaScannerConnection2;
            mediaScannerConnection2.connect();
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            try {
                this.f18102a.scanFile(this.f18104c, "image/*");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            MediaScannerConnection mediaScannerConnection = this.f18102a;
            if (mediaScannerConnection != null) {
                if (mediaScannerConnection.isConnected()) {
                    this.f18102a.disconnect();
                }
                this.f18102a = null;
            }
        }
    }

    public static boolean isPackageMatchApk(Context context, String str, String str2) {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || o.isEmpty(str) || o.isEmpty(str2)) {
            return false;
        }
        try {
            File file = new File(str);
            if (file.exists() && (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1)) != null && packageArchiveInfo.packageName.equals(str2)) {
                int i2 = packageArchiveInfo.versionCode;
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(str2, 1);
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo = null;
                }
                if (packageInfo == null) {
                    return false;
                }
                return i2 == packageInfo.versionCode;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isApkInstalled(Context context, String str) {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || str == null || o.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1)) == null) {
                return false;
            }
            String str2 = packageArchiveInfo.packageName;
            int i2 = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 1);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i2 <= packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isSubProcess(Context context, String str) {
        String curProcessName = getCurProcessName(context);
        return curProcessName != null && curProcessName.endsWith(str);
    }

    public static boolean isMessageProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return curProcessName != null && curProcessName.endsWith(mMsgProcessSuffix);
    }

    public static boolean isNoDexProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return curProcessName != null && curProcessName.endsWith(NODEX_PROCESS_SUFFIX);
    }

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        return (curProcessName == null || !curProcessName.contains(CertificateUtil.DELIMITER)) && curProcessName != null && curProcessName.equals(context.getPackageName());
    }

    public static long isMainProcessRetId(Context context) {
        if (isMainProcess(context)) {
            return Thread.currentThread().getId();
        }
        return 0L;
    }

    public static String getCurProcessName(Context context) {
        String str = f18100c;
        if (o.isEmpty(str)) {
            try {
                int myPid = Process.myPid();
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.pid == myPid) {
                        if (Logger.debug()) {
                            Logger.d("Process", "processName = " + runningAppProcessInfo.processName);
                        }
                        String str2 = runningAppProcessInfo.processName;
                        f18100c = str2;
                        return str2;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            String a2 = a();
            f18100c = a2;
            return a2;
        }
        return str;
    }

    private static String a() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
        } catch (Throwable unused) {
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = bufferedReader.read();
                if (read <= 0) {
                    break;
                }
                sb.append((char) read);
            }
            if (Logger.debug()) {
                Logger.d("Process", "get processName = " + sb.toString());
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused2) {
            }
            return sb2;
        } catch (Throwable unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            return null;
        }
    }

    public static boolean isApplicationForeground(Context context, String str) {
        return ProcessLifecycleOwner.get().getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    public static void deleteShortCut(Context context, Intent intent, String str) {
        if (context != null && intent != null && !o.isEmpty(str)) {
            try {
                Intent intent2 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
                intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
                intent2.putExtra("android.intent.extra.shortcut.NAME", str);
                intent2.putExtra("duplicate", false);
                context.sendBroadcast(intent2);
                if (!Logger.debug()) {
                    return;
                }
                Logger.d("launcher_ad", "deleteShortCut intent " + intent.toUri(0));
            } catch (Exception unused) {
            }
        }
    }

    public static void startPhoneScreen(Context context, String str) {
        if (context != null && !o.isEmpty(str)) {
            try {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + Uri.encode(str)));
                if (!(context instanceof Activity)) {
                    intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                }
                context.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    public static void openInstalledApp(Context context, String str) {
        if (context != null && !o.isEmpty(str)) {
            try {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                context.startActivity(launchIntentForPackage);
            } catch (Exception unused) {
            }
        }
    }

    public static String getProviderAuthority(Context context, String str) {
        ProviderInfo[] providerInfoArr;
        if (context != null && !o.isEmpty(str)) {
            try {
                for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                    if (str.equals(providerInfo.name)) {
                        return providerInfo.authority;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String handleZipEntryGetNameLeak(String str) {
        if (o.isEmpty(str)) {
            return str;
        }
        try {
            if (Logger.debug()) {
                Logger.d("Process", "before handle = " + str);
            }
            str = str.replaceAll(".." + File.separator, "");
            if (Logger.debug()) {
                Logger.d("Process", "after handle = " + str);
            }
        } catch (Exception unused) {
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCacheDirPath(android.content.Context r3) {
        /*
            java.lang.String r0 = "Context is NUll"
            java.util.Objects.requireNonNull(r3, r0)
            r0 = 0
            java.io.File r1 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L16
            java.io.File r3 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
        L14:
            r0 = r3
            goto L3f
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/data/data/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L3f
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/cache/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
            goto L14
        L3f:
            boolean r3 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r3 != 0) goto L46
            return r0
        L46:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Cache Dir"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.common.util.d.getCacheDirPath(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFilesDirPath(android.content.Context r3) {
        /*
            java.lang.String r0 = "Context is NUll"
            java.util.Objects.requireNonNull(r3, r0)
            r0 = 0
            java.io.File r1 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L3f
            if (r1 == 0) goto L16
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
        L14:
            r0 = r3
            goto L3f
        L16:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r1.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/data/data/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L3f
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "/files/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L3f
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L3f
            if (r3 == 0) goto L3f
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L3f
            goto L14
        L3f:
            boolean r3 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r3 != 0) goto L46
            return r0
        L46:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Files Dir"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.common.util.d.getFilesDirPath(android.content.Context):java.lang.String");
    }

    public static boolean isServiceRunning(Context context, String str, String str2) {
        if (context == null || o.isEmpty(str)) {
            return false;
        }
        if (!o.isEmpty(str2)) {
            try {
                List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
                if (runningServices == null || runningServices.size() <= 0) {
                    return false;
                }
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (!str.equals(runningServiceInfo.service.getPackageName()) || !str2.equals(runningServiceInfo.service.getClassName())) {
                    }
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }
        return true;
    }

    public static String getUserSerial(Context context) {
        if (context == null) {
            return null;
        }
        Object systemService = context.getSystemService("user");
        if (systemService == null) {
            Logger.e("ToolUtils", "userManager not exsit !!!");
            return null;
        }
        try {
            Class[] clsArr = null;
            Object[] objArr = null;
            Object invoke = Process.class.getMethod("myUserHandle", null).invoke(Process.class, null);
            return String.valueOf(((Long) systemService.getClass().getMethod("getSerialNumberForUser", invoke.getClass()).invoke(systemService, invoke)).longValue());
        } catch (IllegalAccessException e2) {
            Logger.e("ToolUtils", "", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            Logger.e("ToolUtils", "", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            Logger.e("ToolUtils", "", e4);
            return null;
        } catch (InvocationTargetException e5) {
            Logger.e("ToolUtils", "", e5);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int areNotificationsEnabled(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i2 = applicationInfo.uid;
            Reflect on = Reflect.on((AppOpsManager) context.getSystemService("appops"));
            return ((Integer) on.call("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}, Integer.valueOf(((Integer) on.field("OP_POST_NOTIFICATION", Integer.TYPE).get()).intValue()), Integer.valueOf(i2), packageName).get()).intValue() == 0 ? 1 : 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static void trySetStatusBarWithFullScreen(Window window) {
        if (window == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                window.clearFlags(201326592);
                window.getDecorView().setSystemUiVisibility(1792);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void setCanSetStatusBar(boolean z) {
        f18101d = z;
    }

    public static void trySetStatusBar(Context context, Window window, int i2, boolean z) {
        if (context == null || window == null) {
            return;
        }
        try {
            if (!f18101d) {
                window.clearFlags(Integer.MIN_VALUE);
            } else if (Build.VERSION.SDK_INT >= 21) {
                if (isMiui()) {
                    if (z) {
                        a(false, window);
                    } else {
                        a(true, window);
                    }
                } else if (!isFlyme()) {
                    View decorView = window.getDecorView();
                    int systemUiVisibility = decorView.getSystemUiVisibility();
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (z) {
                            decorView.setSystemUiVisibility(systemUiVisibility & (-8193));
                        } else {
                            decorView.setSystemUiVisibility(systemUiVisibility | 8192);
                        }
                    }
                } else if (z) {
                    b(false, window);
                } else {
                    b(true, window);
                }
                window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(context.getResources().getColor(i2));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(boolean z, Window window) {
        try {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i2 : 0);
            objArr[1] = Integer.valueOf(i2);
            method.invoke(window, objArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void b(boolean z, Window window) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            if (z) {
                declaredField.set(attributes, Integer.valueOf(declaredField.getInt(attributes) | 512));
            } else {
                declaredField.set(attributes, Integer.valueOf(declaredField.getInt(attributes) & NetError.ERR_TTNET_FT_INVALID_STREAM));
            }
            window.setAttributes(attributes);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
