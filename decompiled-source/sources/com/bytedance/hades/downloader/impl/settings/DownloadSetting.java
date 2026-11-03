package com.bytedance.hades.downloader.impl.settings;

import com.bytedance.hades.downloader.impl.DownloaderImpl;
/* loaded from: classes2.dex */
public class DownloadSetting {
    private static volatile DownloadSetting instance;

    private DownloadSetting() {
    }

    public static DownloadSetting getInstance() {
        if (instance == null) {
            synchronized (DownloadSetting.class) {
                if (instance == null) {
                    instance = new DownloadSetting();
                }
            }
        }
        return instance;
    }

    public boolean optBool(String str, boolean z) {
        return "1".equals(DownloaderImpl.a().optSetting(str, z ? "1" : "0", 5));
    }

    public double optDouble(String str, double d2) {
        try {
            return Double.parseDouble(DownloaderImpl.a().optSetting(str, String.valueOf(d2), 3));
        } catch (Throwable th) {
            th.printStackTrace();
            return d2;
        }
    }

    public int optInt(String str, int i2) {
        try {
            return Integer.parseInt(DownloaderImpl.a().optSetting(str, String.valueOf(i2), 1));
        } catch (Throwable th) {
            th.printStackTrace();
            return i2;
        }
    }

    public long optLong(String str, long j2) {
        try {
            return Long.parseLong(DownloaderImpl.a().optSetting(str, String.valueOf(j2), 2));
        } catch (Throwable th) {
            th.printStackTrace();
            return j2;
        }
    }

    public String optString(String str, String str2) {
        return DownloaderImpl.a().optSetting(str, str2, 4);
    }
}
