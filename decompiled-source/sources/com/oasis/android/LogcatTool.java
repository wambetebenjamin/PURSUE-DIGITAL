package com.oasis.android;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import com.oasis.Logger.Logger;
import com.oasis.channel.CrashListener;
import com.oasis.crash.CrashAgent;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes9.dex */
public class LogcatTool {
    public static final String TAG = "LogcatTool";

    /* renamed from: a  reason: collision with root package name */
    private static WeakReference<Activity> f17378a;

    /* renamed from: b  reason: collision with root package name */
    private static c f17379b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ InputStream f17380a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Process f17381b;

        a(InputStream inputStream, Process process) {
            this.f17380a = inputStream;
            this.f17381b = process;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0026  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0043  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r5 = this;
                java.lang.String r0 = "LogcatTool"
                r1 = 0
                r2 = 0
                java.io.InputStream r3 = r5.f17380a     // Catch: java.io.IOException -> L1a
                int r3 = r3.available()     // Catch: java.io.IOException -> L1a
                byte[] r3 = new byte[r3]     // Catch: java.io.IOException -> L1a
                java.io.InputStream r4 = r5.f17380a     // Catch: java.io.IOException -> L18
                r4.read(r3)     // Catch: java.io.IOException -> L18
                java.io.InputStream r4 = r5.f17380a     // Catch: java.io.IOException -> L18
                r4.close()     // Catch: java.io.IOException -> L18
                r4 = 1
                goto L24
            L18:
                r4 = move-exception
                goto L1c
            L1a:
                r4 = move-exception
                r3 = r2
            L1c:
                java.lang.String r4 = r4.toString()
                com.oasis.Logger.Logger.e(r0, r4)
                r4 = r1
            L24:
                if (r3 == 0) goto L2b
                java.lang.String r2 = new java.lang.String
                r2.<init>(r3)
            L2b:
                if (r2 == 0) goto L37
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L37
                com.oasis.Logger.Logger.e(r0, r2)
                goto L38
            L37:
                r1 = r4
            L38:
                java.lang.Process r2 = r5.f17381b
                r2.destroy()
                if (r1 == 0) goto L43
                java.lang.String r1 = "记录日志完成!"
                goto L46
            L43:
                java.lang.String r1 = "日志记录失败!"
            L46:
                com.oasis.Logger.Logger.i(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oasis.android.LogcatTool.a.run():void");
        }
    }

    /* loaded from: classes9.dex */
    class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ String f17382a;

        /* loaded from: classes9.dex */
        class a implements CrashListener {
            a() {
            }

            @Override // com.oasis.channel.CrashListener
            public void onUploadFile(String str) {
                Logger.i(LogcatTool.TAG, str);
            }
        }

        b(String str) {
            this.f17382a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            CrashAgent.getInstance().uploadFileAtPath("logcat_file", com.bytedance.crash.constants.c.x, "{\"Upload\":\"log\"}", this.f17382a, new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class c extends Handler {
        private c() {
        }
    }

    private static String a(Context context, String str) {
        StringBuilder sb;
        File filesDir;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            sb = new StringBuilder();
            filesDir = context.getExternalFilesDir(com.bytedance.crash.constants.c.x);
        } else {
            sb = new StringBuilder();
            filesDir = context.getFilesDir();
        }
        String sb2 = sb.append(filesDir.getAbsolutePath()).append(File.separator).append(str).toString();
        Logger.i(TAG, "filePath == " + sb2);
        return sb2;
    }

    public static boolean getLoggerEnable() {
        return Logger.getLoggerEnable();
    }

    public static void reportLogcatFile() {
        String saveLogcatToFile = saveLogcatToFile();
        if (f17379b == null) {
            f17379b = new c();
        }
        f17379b.postDelayed(new b(saveLogcatToFile), 7000L);
    }

    public static String saveLogcatToFile() {
        if (f17378a == null) {
            f17378a = new WeakReference<>(ActivityManager.getActivity());
        }
        File file = new File("logcat_" + f17378a.get().getPackageName() + "_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date(System.currentTimeMillis())) + ".txt");
        if (file.exists()) {
            file.delete();
        }
        return saveLogcatToFile(file.toString(), com.bytedance.bdturing.c.DEFAULT_EVENT_COUNT);
    }

    public static String saveLogcatToFile(String str, int i2) {
        if (f17378a.get() == null) {
            f17378a = new WeakReference<>(ActivityManager.getActivity());
        }
        if (f17379b == null) {
            f17379b = new c();
        }
        String a2 = a(f17378a.get(), str);
        Logger.d(TAG, "日志文件存储位置: " + a2);
        try {
            String str2 = "logcat -f " + a2;
            Process exec = Runtime.getRuntime().exec(str2);
            InputStream errorStream = exec.getErrorStream();
            Logger.i(TAG, "开始记录日志! 执行指令: " + str2);
            f17379b.postDelayed(new a(errorStream, exec), i2);
        } catch (Exception e2) {
            Logger.e(TAG, e2.toString());
        }
        return a2;
    }

    public static void setLoggerEnable(boolean z) {
        Logger.setLoggerEnable(z);
    }
}
