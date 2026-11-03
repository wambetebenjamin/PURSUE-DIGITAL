package com.oasis.downloader;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes9.dex */
public abstract class DownloaderAgent implements ActivityListener, ApplicationListener {
    public static final String Tag = "DownloaderAgent";

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f17525a = true;
    protected static WeakReference<Context> appContext;
    protected static final Set<Integer> downloadingTaskIdSet = new HashSet();
    protected static final Map<String, Object> downloadingUrlMap = new HashMap();
    protected static Map<Integer, DownloaderAgent> instances;

    public DownloaderAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r3, com.oasis.downloader.ESupportDownloaderType r4, int r5) {
        /*
            java.lang.String r0 = "DownloaderAgent"
            r1 = 0
            java.lang.String r3 = r3.getString(r5)     // Catch: java.lang.Exception -> L47
            int r5 = r3.length()     // Catch: java.lang.Exception -> L47
            if (r5 <= 0) goto L62
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L47
            r5.<init>()     // Catch: java.lang.Exception -> L47
            java.lang.String r2 = r4.name()     // Catch: java.lang.Exception -> L47
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch: java.lang.Exception -> L47
            java.lang.String r2 = ": Create the DownloaderAgent:"
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch: java.lang.Exception -> L47
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch: java.lang.Exception -> L47
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L47
            android.util.Log.i(r0, r5)     // Catch: java.lang.Exception -> L47
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Exception -> L47
            java.lang.Object r3 = r3.newInstance()     // Catch: java.lang.Exception -> L47
            com.oasis.downloader.DownloaderAgent r3 = (com.oasis.downloader.DownloaderAgent) r3     // Catch: java.lang.Exception -> L47
            java.util.Map<java.lang.Integer, com.oasis.downloader.DownloaderAgent> r5 = com.oasis.downloader.DownloaderAgent.instances     // Catch: java.lang.Exception -> L44
            int r1 = r4.ordinal()     // Catch: java.lang.Exception -> L44
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L44
            r5.put(r1, r3)     // Catch: java.lang.Exception -> L44
            r1 = r3
            goto L62
        L44:
            r5 = move-exception
            r1 = r3
            goto L48
        L47:
            r5 = move-exception
        L48:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r2 = r4.name()
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.String r2 = ": Create downloader agent failed."
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r0, r3, r5)
        L62:
            if (r1 != 0) goto L90
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = r4.name()
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r5 = ": Create default DownloaderAgent."
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            android.util.Log.i(r0, r3)
            com.oasis.downloader.a r3 = new com.oasis.downloader.a
            r3.<init>()
            java.util.Map<java.lang.Integer, com.oasis.downloader.DownloaderAgent> r5 = com.oasis.downloader.DownloaderAgent.instances
            int r4 = r4.ordinal()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.put(r4, r3)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oasis.downloader.DownloaderAgent.a(android.content.Context, com.oasis.downloader.ESupportDownloaderType, int):void");
    }

    public static void createInstance(Context context) {
        if (!f17525a && instances != null) {
            throw new AssertionError();
        }
        instances = new HashMap();
        a(context, ESupportDownloaderType.Hades, R.string.hades_downloader_agent_class);
        a(context, ESupportDownloaderType.TGRP, R.string.tgrp_downloader_agent_class);
        appContext = new WeakReference<>(context);
    }

    public static Set<Integer> downloadingTaskIdSet() {
        return downloadingTaskIdSet;
    }

    public static Map<String, Object> getDownloadingUrlMap() {
        return downloadingUrlMap;
    }

    public static DownloaderAgent getInstance(int i2) {
        if (f17525a || instances != null) {
            return instances.get(Integer.valueOf(i2));
        }
        throw new AssertionError();
    }

    public boolean checkIfResFileCorrect(String str) {
        Logger.d(Tag, "checkIfResFileCorrect: path == " + str);
        return true;
    }

    public boolean checkIfResFileDownloaded(String str) {
        Logger.d(Tag, "checkIfResFileDownloaded: path == " + str);
        return true;
    }

    public String checkPathList(String str) {
        Logger.d(Tag, "getIllegalPath: sourcePathList == " + str);
        return "{}";
    }

    public boolean deleteRes(String str) {
        Logger.d(Tag, "deleteRes: path == " + str);
        return true;
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public String getDownloadedResPathList() {
        Logger.d(Tag, "getDownloadedResPathList");
        return "{}";
    }

    public long getGlobalDownloaderSpeed() {
        Logger.d(Tag, "getGlobalDownloaderSpeed");
        return 0L;
    }

    public String getNotDownloadedResPathList() {
        Logger.d(Tag, "getNotDownloadedResPathList");
        return "{}";
    }

    public long getResCount() {
        Logger.d(Tag, "getResCount");
        return 0L;
    }

    public String getResInfo(String str) {
        Logger.d(Tag, "getResInfo: path == " + str);
        return "{}";
    }

    public String getResInfoList() {
        Logger.d(Tag, "getResInfoList");
        return "{}";
    }

    public String getResPathList() {
        Logger.d(Tag, "getResPathList");
        return "{}";
    }

    public String getResPathListByLabel(String str) {
        Logger.d(Tag, "getResPathListByLabel: label == " + str);
        return "{}";
    }

    public String getVersion() {
        return "";
    }

    public abstract int getWorkerCount();

    public long init(String str, IDownloaderInitListener iDownloaderInitListener) {
        Logger.d(Tag, "init: params == " + str);
        return 1L;
    }

    public abstract boolean isUseDefault();

    public void pauseDownload(int i2) {
        Logger.d(Tag, "pauseDownload: taskId == " + i2);
    }

    public void pauseDownload(String str) {
        Logger.d(Tag, "pauseDownload: url == " + str);
    }

    public void resumeDownload(int i2) {
        Logger.d(Tag, "resumeDownload: taskId == " + i2);
    }

    public void resumeDownload(String str) {
        Logger.d(Tag, "resumeDownload: url == " + str);
    }

    public boolean selectDownloaderInstance(long j2) {
        Logger.d(Tag, "selectDownloaderInstance: downloaderId == " + j2);
        return true;
    }

    public void setLogLevel(int i2) {
        Logger.d(Tag, "setLogLevel: level == " + i2);
    }

    public long setMaxDownloadSpeed(long j2) {
        Logger.d(Tag, "setMaxDownloadSpeed: maxSpeed == " + j2);
        return 0L;
    }

    public void setOnlyWifi(int i2, boolean z) {
        Logger.d(Tag, "setOnlyWifi: taskId == " + i2 + ", isOnlyWifi == " + z);
    }

    public void setOnlyWifi(String str, boolean z) {
        Logger.d(Tag, "setOnlyWifi: url == " + str + ", isOnlyWifi == " + z);
    }

    public void setOnlyWifi(boolean z) {
        Logger.d(Tag, "setOnlyWifi: isOnlyWifi == " + z);
    }

    public void setPriority(int i2, int i3, int i4) {
        Logger.d(Tag, "setPriority: taskId == " + i2 + ", priority == " + i3 + "policy == " + i4);
    }

    public void setThrottleNetSpeed(int i2, long j2) {
        Logger.d(Tag, "setThrottleNetSpeed: taskId == " + i2 + ", throttleNetSpeed == " + j2);
    }

    public void setThrottleNetSpeed(String str, long j2) {
        Logger.d(Tag, "setThrottleNetSpeed: url == " + str + ", throttleNetSpeed == " + j2);
    }

    public abstract void setWorkerCount(int i2);

    public abstract int startBatchDownload(String str, boolean z, IDownloaderListener iDownloaderListener);

    public int startDownload(String str, IDownloaderListener iDownloaderListener) {
        Logger.d(Tag, "startDownload: downloadParams == " + str + ", IDownloaderListener == " + iDownloaderListener);
        return 0;
    }

    public abstract void stopAll(boolean z);

    public void stopDownload(int i2) {
        Logger.d(Tag, "stopDownload: taskId == " + i2);
    }

    public void stopDownload(String str) {
        Logger.d(Tag, "stopDownload: url == " + str);
    }

    public void unInit(String str) {
        Logger.d(Tag, "unInit: params == " + str);
    }
}
