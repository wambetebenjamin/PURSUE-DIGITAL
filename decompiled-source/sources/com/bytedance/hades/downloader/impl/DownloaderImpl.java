package com.bytedance.hades.downloader.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bytedance.hades.downloader.api.Downloader;
import com.bytedance.hades.downloader.api.DownloaderDepend;
import com.bytedance.hades.downloader.api.data.DownloadConfig;
import com.bytedance.hades.downloader.api.data.DownloadInfo;
import com.bytedance.hades.downloader.api.data.DownloadMultiConfig;
import com.bytedance.hades.downloader.api.data.DownloadMultiInfo;
import com.bytedance.hades.downloader.api.listener.IDownloadListener;
import com.bytedance.hades.downloader.api.listener.IDownloadMultiListener;
import com.bytedance.hades.downloader.impl.notification.DownloadNotificationManager;
import com.bytedance.hades.downloader.impl.notification.NotificationInfo;
import com.bytedance.hades.downloader.impl.notification.NotificationListener;
import com.bytedance.hades.downloader.impl.okhttp.OKHttpDownloadConnection;
import com.bytedance.hades.downloader.impl.util.AppStatusManager;
import com.bytedance.ttgame.rocketapi.IRocketApi;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import g.hades.downloader.d;
import g.hades.downloader.e;
import g.hades.downloader.f;
import g.hades.downloader.i;
import g.hades.downloader.j;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DownloaderImpl extends Downloader {

    /* renamed from: f  reason: collision with root package name */
    public static volatile DownloaderImpl f9384f;

    /* renamed from: a  reason: collision with root package name */
    public final Context f9385a;

    /* renamed from: b  reason: collision with root package name */
    public final DownloaderDepend f9386b;

    /* renamed from: c  reason: collision with root package name */
    public Integer f9387c = null;

    /* renamed from: d  reason: collision with root package name */
    public int f9388d = -1;

    /* renamed from: e  reason: collision with root package name */
    public long f9389e = -1;

    /* loaded from: classes2.dex */
    public class a extends DownloaderDepend {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f9390a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f9391b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f9392c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ String f9393d;

        /* renamed from: e  reason: collision with root package name */
        public final /* synthetic */ int f9394e;

        /* renamed from: f  reason: collision with root package name */
        public final /* synthetic */ String f9395f;

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ boolean f9396g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ String f9397h;

        public a(Context context, String str, String str2, String str3, int i2, String str4, boolean z, String str5) {
            this.f9390a = context;
            this.f9391b = str;
            this.f9392c = str2;
            this.f9393d = str3;
            this.f9394e = i2;
            this.f9395f = str4;
            this.f9396g = z;
            this.f9397h = str5;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public boolean canLoadSo() {
            return this.f9396g;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public String getAppChannel() {
            return this.f9392c;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public String getAppId() {
            return this.f9391b;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public Context getContext() {
            return this.f9390a;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public String getDeviceId() {
            return this.f9393d;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public String getDownloadSetting() {
            return this.f9395f;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public String getHadesSoPath() {
            return this.f9397h;
        }

        @Override // com.bytedance.hades.downloader.api.DownloaderDepend
        public int getServerRegion() {
            return this.f9394e;
        }
    }

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, IDownloadListener> f9398a = new ConcurrentHashMap<>();

        /* renamed from: b  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, IDownloadMultiListener> f9399b = new ConcurrentHashMap<>();

        /* renamed from: c  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, Integer> f9400c = new ConcurrentHashMap<>();

        /* renamed from: d  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, Set<Integer>> f9401d = new ConcurrentHashMap<>();

        /* renamed from: e  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, NotificationInfo> f9402e = new ConcurrentHashMap<>();

        /* renamed from: f  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, DownloadInfo> f9403f = new ConcurrentHashMap<>();

        /* renamed from: g  reason: collision with root package name */
        public static final ConcurrentHashMap<Integer, DownloadMultiInfo> f9404g = new ConcurrentHashMap<>();

        /* renamed from: h  reason: collision with root package name */
        public static final Handler f9405h = new Handler(Looper.getMainLooper());

        /* renamed from: i  reason: collision with root package name */
        public static final HashMap<Integer, NotificationListener> f9406i = new HashMap<>();
    }

    public DownloaderImpl(DownloaderDepend downloaderDepend) {
        this.f9386b = downloaderDepend;
        Context applicationContext = downloaderDepend.getContext().getApplicationContext();
        this.f9385a = applicationContext;
        DownloadComponentManager.setAppContext(applicationContext);
        if (downloaderDepend.canLoadSo()) {
            if (!TextUtils.isEmpty(downloaderDepend.getHadesSoPath())) {
                System.load(downloaderDepend.getHadesSoPath());
            } else {
                System.loadLibrary("hades");
            }
        } else {
            Log.i("Hades-J", "init, depend can't load so");
        }
        a(applicationContext, downloaderDepend.getAppId(), downloaderDepend.getAppChannel(), downloaderDepend.getDeviceId(), downloaderDepend.getDownloadSetting(), downloaderDepend.getServerRegion());
    }

    public static boolean b() {
        boolean z = true;
        if (f9384f != null) {
            return true;
        }
        synchronized (DownloaderImpl.class) {
            if (f9384f == null) {
                z = false;
            }
        }
        return z;
    }

    private native int create(String str);

    private native int createMulti(String str, boolean z, String str2, boolean z2);

    private void createOKHttpDownloadClient(int i2, int i3) {
        DownloadComponentManager.createDownloadClient(i2, i3);
    }

    private Object createOKHttpDownloadConnection(long j2) {
        return new OKHttpDownloadConnection(j2);
    }

    private native int download(String str);

    private native int downloadMulti(String str, boolean z, String str2, boolean z2);

    public static String getApiVersion() {
        return "1.16.0.6.lite";
    }

    private native String getDownloadInfoJson(int i2);

    private native String getDownloadMultiInfoJson(int i2);

    private char getNetworkType() {
        return j.a(this.f9385a);
    }

    private long getTotalRxBytes() {
        try {
            return TrafficStats.getTotalRxBytes();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0L;
        }
    }

    private native int globalInit(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, String str9, String str10, int i3);

    public static void init(DownloaderDepend downloaderDepend) {
        downloaderDepend.getClass();
        if (f9384f == null) {
            synchronized (DownloaderImpl.class) {
                if (f9384f == null) {
                    f9384f = new DownloaderImpl(downloaderDepend);
                }
            }
        }
    }

    private native String invoke(String str, String str2);

    private String invokeFromNative(String str, String str2) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void notifyAppStatusChange(int i2);

    private void notifyDownloadStatusFromNative(int i2, int i3, long j2, int i4, long j3) {
        ConcurrentHashMap<Integer, DownloadInfo> concurrentHashMap = b.f9403f;
        DownloadInfo downloadInfo = concurrentHashMap.get(Integer.valueOf(i2));
        if (downloadInfo == null) {
            downloadInfo = new DownloadInfo();
            downloadInfo.setId(i2);
            downloadInfo.setStatus(i3);
            concurrentHashMap.put(Integer.valueOf(i2), downloadInfo);
        } else {
            downloadInfo.setStatus(i3);
        }
        downloadInfo.setTransientSpeed(i4);
        downloadInfo.setCurBytes(j2);
        downloadInfo.setTotalBytes(j3);
        a(i2, i3, downloadInfo);
    }

    private void notifyMultiDownloadStatusFromNative(int i2, int i3, long j2, int i4, long j3) {
        ConcurrentHashMap<Integer, DownloadMultiInfo> concurrentHashMap = b.f9404g;
        DownloadMultiInfo downloadMultiInfo = concurrentHashMap.get(Integer.valueOf(i2));
        if (downloadMultiInfo == null) {
            downloadMultiInfo = new DownloadMultiInfo();
            downloadMultiInfo.setId(i2);
            downloadMultiInfo.setStatus(i3);
            concurrentHashMap.put(Integer.valueOf(i2), downloadMultiInfo);
        } else {
            downloadMultiInfo.setStatus(i3);
        }
        downloadMultiInfo.setTransientSpeed(i4);
        downloadMultiInfo.setCurBytes(j2);
        downloadMultiInfo.setTotalBytes(j3);
        a(i2, i3, downloadMultiInfo);
    }

    private void notifyMultiTaskId(int i2, String str) {
        String[] split = str.split("_");
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < split.length; i3++) {
            if (!TextUtils.isEmpty(split[i3])) {
                int parseInt = Integer.parseInt(split[i3]);
                b.f9400c.put(Integer.valueOf(parseInt), Integer.valueOf(i2));
                hashSet.add(Integer.valueOf(parseInt));
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        b.f9401d.put(Integer.valueOf(i2), hashSet);
    }

    private void reportEvent(String str, String str2) {
        try {
            this.f9386b.reportEvent(str, new JSONObject(str2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void selfInit(Object obj, String str) {
        unityInitInstance((Context) obj, str);
    }

    private int setCoreThread(int i2) {
        return f.a(Process.myTid(), i2);
    }

    private static void unityInitInstance(Context context, String str) {
        try {
            Context applicationContext = context.getApplicationContext();
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_ID, "");
            String optString2 = jSONObject.optString("appChannel", "");
            String optString3 = jSONObject.optString("deviceId", "");
            int optInt = jSONObject.optInt("serverRegion", 0);
            String optString4 = jSONObject.optString("downloadSetting", "");
            boolean z = true;
            if (jSONObject.optInt("canLoadSo", 1) != 1) {
                z = false;
            }
            init(new a(applicationContext, optString, optString2, optString3, optInt, optString4, z, jSONObject.optString("hadesSoPath", "")));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private native void updateDeviceId(String str);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void cancel(int i2, boolean z);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public int create(DownloadConfig downloadConfig) {
        if (downloadConfig == null) {
            return -1;
        }
        int create = create(downloadConfig.toJsonStr());
        IDownloadListener downloadListener = downloadConfig.getDownloadListener();
        if (downloadListener != null) {
            b.f9398a.put(Integer.valueOf(create), downloadListener);
        }
        return create;
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public int download(DownloadConfig downloadConfig) {
        if (downloadConfig == null) {
            return -1;
        }
        int download = download(downloadConfig.toJsonStr());
        if (downloadConfig.getDownloadListener() != null) {
            b.f9398a.put(Integer.valueOf(download), downloadConfig.getDownloadListener());
        }
        return download;
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public int downloadMulti(DownloadMultiConfig downloadMultiConfig) {
        if (downloadMultiConfig == null) {
            return -1;
        }
        List<DownloadConfig> configList = downloadMultiConfig.getConfigList();
        if (configList.isEmpty()) {
            return -1;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < configList.size(); i2++) {
                jSONArray.put(configList.get(i2).toJson());
            }
            int downloadMulti = downloadMulti(jSONArray.toString(), downloadMultiConfig.isShowNotification(), downloadMultiConfig.getNotificationName(), downloadMultiConfig.isAutoResume());
            IDownloadListener downloadListener = downloadMultiConfig.getDownloadListener();
            if (downloadListener != null) {
                b.f9398a.put(Integer.valueOf(downloadMulti), downloadListener);
            }
            IDownloadMultiListener downloadMultiListener = downloadMultiConfig.getDownloadMultiListener();
            if (downloadMultiListener != null) {
                b.f9399b.put(Integer.valueOf(downloadMulti), downloadMultiListener);
            }
            return downloadMulti;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native int getDownloadId(String str, String str2, String str3, String str4);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public DownloadInfo getDownloadInfo(int i2) {
        return DownloadInfo.fromJson(getDownloadInfoJson(i2));
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public DownloadMultiInfo getDownloadMultiInfo(int i2) {
        return DownloadMultiInfo.fromJson(getDownloadMultiInfoJson(i2));
    }

    public native String getProperty(int i2, int i3, int i4);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native String getSdkVersion();

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native boolean isDownloading(int i2);

    public native void notifyNetworkChange(char c2);

    public native void onEvent(String str, String str2);

    public native String optSetting(String str, String str2, int i2);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void pause(int i2);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public void registerDownloadListener(int i2, IDownloadListener iDownloadListener) {
        b.f9398a.put(Integer.valueOf(i2), iDownloadListener);
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public void registerDownloadMultiListener(int i2, IDownloadMultiListener iDownloadMultiListener) {
        b.f9399b.put(Integer.valueOf(i2), iDownloadMultiListener);
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void setLogPriority(int i2);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void setOnlyWifi(int i2, boolean z);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public void setThrottleNetSpeed(int i2, int i3) {
        setThrottleNetSpeed(i2, i3, 0, true);
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void setThrottleNetSpeed(int i2, int i3, int i4, boolean z);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public native void start(int i2);

    @Override // com.bytedance.hades.downloader.api.Downloader
    public void unRegisterDownloadListener(int i2) {
        b.f9398a.remove(Integer.valueOf(i2));
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public void unRegisterDownloadMultiListener(int i2) {
        b.f9399b.remove(Integer.valueOf(i2));
    }

    public native boolean updateGlobalSetting(String str);

    public final void a(final int i2, final int i3, final DownloadInfo downloadInfo) {
        final IDownloadListener iDownloadListener;
        final Integer num;
        final NotificationListener notificationListener;
        final NotificationInfo notificationInfo;
        ConcurrentHashMap<Integer, IDownloadListener> concurrentHashMap = b.f9398a;
        IDownloadListener iDownloadListener2 = concurrentHashMap.get(Integer.valueOf(i2));
        if (iDownloadListener2 == null) {
            Integer num2 = b.f9400c.get(Integer.valueOf(i2));
            if (num2 != null) {
                num = num2;
                iDownloadListener = concurrentHashMap.get(num2);
            } else {
                num = num2;
                iDownloadListener = iDownloadListener2;
            }
        } else {
            iDownloadListener = iDownloadListener2;
            num = null;
        }
        boolean z = DownloadNotificationManager.getInstance().areNotificationsEnabled(this.f9385a) && downloadInfo.isShowNotification();
        if (iDownloadListener != null || z) {
            if (z) {
                ConcurrentHashMap<Integer, NotificationInfo> concurrentHashMap2 = b.f9402e;
                NotificationInfo notificationInfo2 = concurrentHashMap2.get(Integer.valueOf(i2));
                if (notificationInfo2 == null) {
                    notificationInfo2 = new NotificationInfo();
                    notificationInfo2.fileName = downloadInfo.getFileName();
                    notificationInfo2.bizType = 0;
                    notificationInfo2.notificationName = downloadInfo.getNotificationName();
                    concurrentHashMap2.put(Integer.valueOf(i2), notificationInfo2);
                }
                notificationInfo2.status = downloadInfo.getStatus();
                notificationInfo2.curBytes = downloadInfo.getCurBytes();
                notificationInfo2.totalBytes = downloadInfo.getTotalBytes();
                HashMap<Integer, NotificationListener> hashMap = b.f9406i;
                NotificationListener notificationListener2 = hashMap.get(Integer.valueOf(i2));
                if (notificationListener2 == null) {
                    notificationListener2 = new NotificationListener(this.f9385a, i2);
                    hashMap.put(Integer.valueOf(i2), notificationListener2);
                }
                notificationListener = notificationListener2;
                notificationInfo = notificationInfo2;
            } else {
                notificationListener = null;
                notificationInfo = null;
            }
            b.f9405h.post(new Runnable() { // from class: com.bytedance.hades.downloader.impl.DownloaderImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DownloaderImpl.this.a(i3, iDownloadListener, downloadInfo, notificationListener, notificationInfo, i2, num);
                }
            });
        }
    }

    @Override // com.bytedance.hades.downloader.api.Downloader
    public int create(DownloadMultiConfig downloadMultiConfig) {
        if (downloadMultiConfig == null) {
            return -1;
        }
        List<DownloadConfig> configList = downloadMultiConfig.getConfigList();
        if (configList.isEmpty()) {
            return -1;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < configList.size(); i2++) {
                jSONArray.put(configList.get(i2).toJson());
            }
            int createMulti = createMulti(jSONArray.toString(), downloadMultiConfig.isShowNotification(), downloadMultiConfig.getNotificationName(), downloadMultiConfig.isAutoResume());
            IDownloadListener downloadListener = downloadMultiConfig.getDownloadListener();
            if (downloadListener != null) {
                b.f9398a.put(Integer.valueOf(createMulti), downloadListener);
            }
            IDownloadMultiListener downloadMultiListener = downloadMultiConfig.getDownloadMultiListener();
            if (downloadMultiListener != null) {
                b.f9399b.put(Integer.valueOf(createMulti), downloadMultiListener);
            }
            return createMulti;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public final void a(final int i2, final int i3, final DownloadMultiInfo downloadMultiInfo) {
        final NotificationListener notificationListener;
        final NotificationInfo notificationInfo;
        final IDownloadMultiListener iDownloadMultiListener = b.f9399b.get(Integer.valueOf(i2));
        boolean areNotificationsEnabled = DownloadNotificationManager.getInstance().areNotificationsEnabled(this.f9385a);
        if (iDownloadMultiListener != null || areNotificationsEnabled) {
            if (areNotificationsEnabled) {
                ConcurrentHashMap<Integer, NotificationInfo> concurrentHashMap = b.f9402e;
                NotificationInfo notificationInfo2 = concurrentHashMap.get(Integer.valueOf(i2));
                if (notificationInfo2 == null) {
                    notificationInfo2 = new NotificationInfo();
                    notificationInfo2.bizType = 2;
                    notificationInfo2.notificationName = downloadMultiInfo.getNotificationName();
                    concurrentHashMap.put(Integer.valueOf(i2), notificationInfo2);
                }
                notificationInfo2.status = downloadMultiInfo.getStatus();
                notificationInfo2.curBytes = downloadMultiInfo.getCurBytes();
                notificationInfo2.totalBytes = downloadMultiInfo.getTotalBytes();
                HashMap<Integer, NotificationListener> hashMap = b.f9406i;
                NotificationListener notificationListener2 = hashMap.get(Integer.valueOf(i2));
                if (notificationListener2 == null) {
                    notificationListener2 = new NotificationListener(this.f9385a, i2);
                    hashMap.put(Integer.valueOf(i2), notificationListener2);
                }
                notificationInfo = notificationInfo2;
                notificationListener = notificationListener2;
            } else {
                notificationListener = null;
                notificationInfo = null;
            }
            b.f9405h.post(new Runnable() { // from class: com.bytedance.hades.downloader.impl.DownloaderImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DownloaderImpl.this.a(i3, iDownloadMultiListener, downloadMultiInfo, notificationListener, notificationInfo, i2);
                }
            });
        }
    }

    public static DownloaderImpl a() {
        if (f9384f == null) {
            synchronized (DownloaderImpl.class) {
                if (f9384f == null) {
                    throw new IllegalStateException("init not called!");
                }
            }
        }
        return f9384f;
    }

    public final void a(Context context, String str, String str2, String str3, String str4, int i2) {
        String str5;
        String str6;
        try {
            str5 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            th.printStackTrace();
            str5 = "";
        }
        File file = null;
        try {
            file = context.getExternalFilesDir("download");
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        if (file != null) {
            str6 = file.getPath();
        } else {
            str6 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + this.f9385a.getPackageName() + "/files/download";
        }
        String str7 = str6;
        String str8 = Build.BRAND;
        String str9 = Build.MODEL;
        int i3 = Build.VERSION.SDK_INT;
        String string = TextUtils.isEmpty(str3) ? this.f9385a.getSharedPreferences(com.bytedance.applog.manager.b.f2802a, 0).getString("device_id", "") : str3;
        globalInit(str, context.getPackageName(), str5, str2, string, context.getFilesDir().getPath(), str7, str4, i2, str8, str9, i3);
        if (TextUtils.isEmpty(string)) {
            IntentFilter intentFilter = new IntentFilter(IRocketApi.GSDK_DEVICE_INFO_UPDATE_BROADCAST);
            d dVar = new d(this);
            LocalBroadcastManager.getInstance(context).registerReceiver(dVar, intentFilter);
            a(context, dVar);
        }
        context.registerReceiver(new i(), new IntentFilter(NetworkChangeManager.ACTION));
        AppStatusManager.getInstance().init(context);
        AppStatusManager.getInstance().registerAppSwitchListener(new e(this));
    }

    public final void a(Context context, BroadcastReceiver broadcastReceiver) {
        String string = this.f9385a.getSharedPreferences(com.bytedance.applog.manager.b.f2802a, 0).getString("device_id", "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        updateDeviceId(string);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i2, IDownloadListener iDownloadListener, DownloadInfo downloadInfo, NotificationListener notificationListener, NotificationInfo notificationInfo, int i3, Integer num) {
        Set<Integer> set;
        if (i2 == -4) {
            if (num != null && (set = b.f9401d.get(num)) != null) {
                set.remove(Integer.valueOf(i3));
                if (set.isEmpty()) {
                    b.f9398a.remove(num);
                }
            }
            b.f9398a.remove(Integer.valueOf(i3));
            if (iDownloadListener != null) {
                iDownloadListener.OnCancel(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnCancel(notificationInfo);
            }
            b.f9403f.remove(Integer.valueOf(i3));
            b.f9402e.remove(Integer.valueOf(i3));
        } else if (i2 == -3) {
            if (iDownloadListener != null) {
                iDownloadListener.OnSuccess(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnSuccess(notificationInfo);
            }
            b.f9403f.remove(Integer.valueOf(i3));
            b.f9402e.remove(Integer.valueOf(i3));
        } else if (i2 == -2) {
            if (iDownloadListener != null) {
                iDownloadListener.OnPause(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnPause(notificationInfo);
            }
        } else if (i2 == -1) {
            if (iDownloadListener != null) {
                iDownloadListener.OnFail(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnFail(notificationInfo);
            }
        } else if (i2 == 1) {
            if (iDownloadListener != null) {
                iDownloadListener.OnPrepare(downloadInfo);
            }
            DownloadComponentManager.getDownloadServiceHandler().startService(this.f9385a);
        } else if (i2 == 2) {
            if (iDownloadListener != null) {
                iDownloadListener.OnStart(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnStart(notificationInfo);
            }
        } else if (i2 != 4) {
        } else {
            if (iDownloadListener != null) {
                iDownloadListener.OnProgress(downloadInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnProgress(notificationInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i2, IDownloadMultiListener iDownloadMultiListener, DownloadMultiInfo downloadMultiInfo, NotificationListener notificationListener, NotificationInfo notificationInfo, int i3) {
        if (i2 == 2) {
            DownloadComponentManager.getDownloadServiceHandler().startService(this.f9385a);
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiStart(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnStart(notificationInfo);
            }
        } else if (i2 == 4) {
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiProgress(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnProgress(notificationInfo);
            }
        } else if (i2 == -4) {
            if (downloadMultiInfo != null) {
                b.f9399b.remove(Integer.valueOf(downloadMultiInfo.getId()));
            }
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiCancel(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnCancel(notificationInfo);
            }
            b.f9404g.remove(Integer.valueOf(i3));
            b.f9402e.remove(Integer.valueOf(i3));
        } else if (i2 == -3) {
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiSuccess(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnSuccess(notificationInfo);
            }
            b.f9404g.remove(Integer.valueOf(i3));
            b.f9402e.remove(Integer.valueOf(i3));
        } else if (i2 == -2) {
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiPause(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnPause(notificationInfo);
            }
        } else if (i2 != -1) {
        } else {
            if (iDownloadMultiListener != null) {
                iDownloadMultiListener.OnMultiFail(downloadMultiInfo);
            }
            if (notificationListener != null) {
                notificationListener.OnFail(notificationInfo);
            }
        }
    }
}
