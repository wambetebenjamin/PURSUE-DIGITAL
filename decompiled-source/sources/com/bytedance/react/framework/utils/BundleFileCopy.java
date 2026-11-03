package com.bytedance.react.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.librarian.c;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.react.framework.core.RNLogUtil;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes2.dex */
public class BundleFileCopy {
    private static final String SP_KEY = "need_retried";
    private static final String SP_NAME = "gsdk_gecko_bundle_file_copy";
    private static final String TAG = "BundleFileCopy";
    private static boolean bundleRunning = false;
    private static boolean ruBundleRunning = false;
    private static List<Runnable> pendingTasks = new ArrayList();
    private static Executor executor = Executors.newSingleThreadExecutor();
    private static List<String> retriedSrc = new ArrayList();
    private static List<String> retriedOnLaunch = new ArrayList();

    /* loaded from: classes2.dex */
    public interface FileCopyListener {
        void onCopyEnd();
    }

    public static void retryOnLaunch(Context context) {
        String[] split;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
            String string = sharedPreferences.getString(SP_KEY, "");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            sharedPreferences.edit().clear().apply();
            for (String str : TextUtils.split(string, Constants.PACKNAME_END)) {
                Log.d(TAG, "retry on launch: " + str);
                String[] split2 = TextUtils.split(str, CertificateUtil.DELIMITER);
                String str2 = split2[0];
                retriedOnLaunch.add(str2);
                copy(str2, split2[1], split2[2], split2[3], new FileCopyListener() { // from class: com.bytedance.react.framework.utils.BundleFileCopy.1
                    @Override // com.bytedance.react.framework.utils.BundleFileCopy.FileCopyListener
                    public void onCopyEnd() {
                    }
                });
            }
        } catch (Throwable th) {
            Log.d(TAG, "retry fail");
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void scheduleRetryNextLaunch(String str, String str2, String str3, String str4) {
        try {
            SharedPreferences sharedPreferences = BRNManager.newInstance().getAppContext().getSharedPreferences(SP_NAME, 0);
            String string = sharedPreferences.getString(SP_KEY, "");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String join = TextUtils.join(CertificateUtil.DELIMITER, new String[]{str, str2, str3, str4});
            if (!string.isEmpty()) {
                string = string + Constants.PACKNAME_END;
            }
            edit.putString(SP_KEY, string + join);
            edit.commit();
        } catch (Throwable th) {
            Log.d(TAG, "schedule fail.");
            th.printStackTrace();
        }
    }

    public static void copy(final String str, final String str2, final String str3, final String str4, final FileCopyListener fileCopyListener) {
        Log.d(TAG, "copy sourceFolder = " + str);
        Log.d(TAG, "copy targetFolder = " + str2);
        if (new File(str).exists()) {
            submitTask(new Runnable() { // from class: com.bytedance.react.framework.utils.BundleFileCopy.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(BundleFileCopy.TAG, "copy " + str3 + " " + str4 + " start in thread " + Thread.currentThread().getName() + ", start time " + System.currentTimeMillis());
                    RNLogUtil.reportLog(RNLogUtil.RN_BUNDLE_COPY_START, "result", str3 + c.a.SEPARATOR + str4 + "time start " + System.currentTimeMillis());
                    boolean copyFolder = BundleFileCopy.copyFolder(str, str2, str3 + c.a.SEPARATOR + str4);
                    if (!copyFolder) {
                        if (BundleFileCopy.retriedSrc.contains(str)) {
                            if (!BundleFileCopy.retriedOnLaunch.contains(str)) {
                                BundleFileCopy.scheduleRetryNextLaunch(str, str2, str3, str4);
                            }
                        } else {
                            Log.d(BundleFileCopy.TAG, "retry at same process.");
                            BundleFileCopy.scheduleRetryLater(str, str2, str3, str4, fileCopyListener);
                            BundleFileCopy.retriedSrc.add(str);
                        }
                    }
                    FileCopyListener fileCopyListener2 = fileCopyListener;
                    if (fileCopyListener2 != null) {
                        fileCopyListener2.onCopyEnd();
                    }
                    RNLogUtil.reportLog(RNLogUtil.RN_BUNDLE_COPY_END, "result", str3 + c.a.SEPARATOR + str4 + "time end " + System.currentTimeMillis());
                    Log.d(BundleFileCopy.TAG, "copy " + str3 + " " + str4 + " end in thread " + Thread.currentThread().getName() + " end time " + System.currentTimeMillis() + ", ret: " + copyFolder);
                }
            });
        }
    }

    private static void submitTask(Runnable runnable) {
        if (!bundleRunning && !ruBundleRunning) {
            Log.d(TAG, "no bundle running, copy directly");
            runTask(runnable);
            return;
        }
        Log.d(TAG, "bundle running, pending");
        pendingTasks.add(runnable);
    }

    private static void runTask(Runnable runnable) {
        executor.execute(runnable);
    }

    public static void setRUBundleRunning(boolean z) {
        Log.d(TAG, "change ru bundle running: " + z);
        ruBundleRunning = z;
        while (!bundleRunning && !ruBundleRunning && !pendingTasks.isEmpty()) {
            runTask(pendingTasks.remove(0));
        }
    }

    public static void setBundleRunning(boolean z) {
        Log.d(TAG, "change rn bundle running: " + z);
        bundleRunning = z;
        while (!bundleRunning && !ruBundleRunning && !pendingTasks.isEmpty()) {
            runTask(pendingTasks.remove(0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void scheduleRetryLater(final String str, final String str2, final String str3, final String str4, final FileCopyListener fileCopyListener) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.bytedance.react.framework.utils.BundleFileCopy.3
            @Override // java.lang.Runnable
            public void run() {
                BundleFileCopy.copy(str, str2, str3, str4, fileCopyListener);
            }
        }, 5000L);
    }

    public static boolean copyFolder(String str, String str2, String str3) {
        File file;
        try {
            new File(str2).mkdirs();
            String[] list = new File(str).list();
            boolean z = true;
            for (int i2 = 0; i2 < list.length; i2++) {
                if (str.endsWith(File.separator)) {
                    file = new File(str + list[i2]);
                } else {
                    file = new File(str + File.separator + list[i2]);
                }
                if (file.isFile()) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + c.a.SEPARATOR + file.getName());
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                }
                if (file.isDirectory() && !(z = copyFolder(str + c.a.SEPARATOR + list[i2], str2 + c.a.SEPARATOR + list[i2], str3))) {
                    return false;
                }
            }
            return z;
        } catch (Exception e2) {
            Log.d(TAG, "copy file fail: " + e2.getMessage() + ", src: " + str + ", dest: " + str2);
            e2.printStackTrace();
            try {
                FileUtils.deleteDir(new File(str2));
            } catch (Exception unused) {
            }
            RNLogUtil.reportLog(RNLogUtil.RN_BUNDLE_COPY_FAILED, "result", str3 + " copy file failed");
            return false;
        }
    }
}
