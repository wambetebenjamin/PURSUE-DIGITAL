package com.bytedance.hades.downloader.api.data;

import android.text.TextUtils;
import com.bytedance.hades.downloader.impl.DownloaderImpl;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DownloadInfo {
    private String fileName;
    private String notificationName;
    private String savePath;
    private int status;
    private String url;
    private int id = 0;
    private int transientSpeed = 0;
    private long curBytes = 0;
    private long totalBytes = 0;
    private int showNotification = -1;

    /* loaded from: classes2.dex */
    public interface PropertyIndex {
        public static final int CUR_HOST = 7;
        public static final int ERROR_CODE = 5;
        public static final int ERROR_MSG = 6;
        public static final int FILE_NAME = 3;
        public static final int MD5 = 2;
        public static final int NOTIFICATION_NAME = 9;
        public static final int SAVE_PATH = 4;
        public static final int SHOW_NOTIFICATION = 8;
        public static final int URL = 1;
    }

    public static DownloadInfo fromJson(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String getProperty(int i2, int i3) {
        return DownloaderImpl.a().getProperty(i2, i3, 0);
    }

    public long getCurBytes() {
        return this.curBytes;
    }

    public int getErrCode() {
        try {
            return Integer.parseInt(getProperty(this.id, 5));
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public String getErrMsg() {
        return getProperty(this.id, 6);
    }

    public String getFileName() {
        if (TextUtils.isEmpty(this.fileName)) {
            this.fileName = getProperty(this.id, 3);
        }
        return this.fileName;
    }

    public int getId() {
        return this.id;
    }

    public String getMd5() {
        return getProperty(this.id, 2);
    }

    public String getNotificationName() {
        if (TextUtils.isEmpty(this.notificationName)) {
            this.notificationName = getProperty(this.id, 9);
        }
        return this.notificationName;
    }

    public String getSavePath() {
        if (TextUtils.isEmpty(this.savePath)) {
            this.savePath = getProperty(this.id, 4);
        }
        return this.savePath;
    }

    public int getStatus() {
        return this.status;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public int getTransientSpeed() {
        return this.transientSpeed;
    }

    public String getUrl() {
        if (TextUtils.isEmpty(this.url)) {
            this.url = getProperty(this.id, 1);
        }
        return this.url;
    }

    public boolean isShowNotification() {
        if (this.showNotification == -1) {
            try {
                this.showNotification = Integer.parseInt(getProperty(this.id, 8));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this.showNotification == 1;
    }

    public void setCurBytes(long j2) {
        this.curBytes = j2;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public void setTotalBytes(long j2) {
        this.totalBytes = j2;
    }

    public void setTransientSpeed(int i2) {
        this.transientSpeed = i2;
    }

    public String toString() {
        return "id= " + getId() + "\nurl= " + getUrl() + "\nstatus= " + getStatus() + "\ncurBytes= " + getCurBytes() + "\ntotalBytes= " + getTotalBytes();
    }

    public static DownloadInfo fromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            DownloadInfo downloadInfo = new DownloadInfo();
            downloadInfo.id = jSONObject.optInt("id");
            downloadInfo.status = jSONObject.optInt("status");
            downloadInfo.transientSpeed = jSONObject.optInt("transient_speed");
            downloadInfo.curBytes = jSONObject.optLong(MonitorConstants.EXTRA_DOWNLOAD_CUR_BYTES);
            downloadInfo.totalBytes = jSONObject.optLong(MonitorConstants.EXTRA_DOWNLOAD_TOTAL_BYTES);
            return downloadInfo;
        }
        return null;
    }
}
