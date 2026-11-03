package com.just.agentweb;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashSet;
/* loaded from: classes9.dex */
public class AgentWebCompat {
    public static void setDataDirectorySuffix(Context context) {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            HashSet<String> hashSet = new HashSet();
            String absolutePath = context.getDataDir().getAbsolutePath();
            String currentProcessName = ProcessUtils.getCurrentProcessName(context);
            if (!TextUtils.equals(context.getPackageName(), currentProcessName)) {
                if (TextUtils.isEmpty(currentProcessName)) {
                    currentProcessName = context.getPackageName();
                }
                WebView.setDataDirectorySuffix(currentProcessName);
                String str = "_" + currentProcessName;
                hashSet.add(absolutePath + "/app_webview" + str + "/webview_data.lock");
                if (RomUtils.isHuawei()) {
                    hashSet.add(absolutePath + "/app_hws_webview" + str + "/webview_data.lock");
                }
            } else {
                String str2 = "_" + currentProcessName;
                hashSet.add(absolutePath + "/app_webview/webview_data.lock");
                hashSet.add(absolutePath + "/app_webview" + str2 + "/webview_data.lock");
                if (RomUtils.isHuawei()) {
                    hashSet.add(absolutePath + "/app_hws_webview/webview_data.lock");
                    hashSet.add(absolutePath + "/app_hws_webview" + str2 + "/webview_data.lock");
                }
            }
            for (String str3 : hashSet) {
                File file = new File(str3);
                if (file.exists()) {
                    tryLockOrRecreateFile(file);
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void tryLockOrRecreateFile(File file) {
        try {
            FileLock tryLock = new RandomAccessFile(file, "rw").getChannel().tryLock();
            if (tryLock != null) {
                tryLock.close();
            } else {
                createFile(file, file.delete());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            createFile(file, file.exists() ? file.delete() : false);
        }
    }

    private static void createFile(File file, boolean z) {
        if (z) {
            try {
                if (file.exists()) {
                    return;
                }
                file.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
