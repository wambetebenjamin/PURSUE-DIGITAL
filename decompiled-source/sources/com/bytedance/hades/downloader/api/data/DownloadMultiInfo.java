package com.bytedance.hades.downloader.api.data;

import android.text.TextUtils;
import com.bytedance.hades.downloader.impl.DownloaderImpl;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DownloadMultiInfo {
    private String notificationName;
    private int status;
    private int id = 0;
    private long curBytes = 0;
    private long totalBytes = 0;
    private int downloadedFilesCount = 0;
    private int totalFilesCount = 0;
    private int transientSpeed = 0;
    private int showNotification = -1;

    /* loaded from: classes2.dex */
    public interface PropertyIndex {
        public static final int DOWNLOADED_FILES_COUNT = 3;
        public static final int ERROR_CODE = 1;
        public static final int ERROR_MSG = 2;
        public static final int NOTIFICATION_NAME = 6;
        public static final int SHOW_NOTIFICATION = 5;
        public static final int TOTAL_FILES_COUNT = 4;
    }

    public static DownloadMultiInfo fromJson(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public long getCurBytes() {
        return this.curBytes;
    }

    public int getDownloadedFilesCount() {
        try {
            this.downloadedFilesCount = Integer.parseInt(getProperty(this.id, 3));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.downloadedFilesCount;
    }

    public int getErrCode() {
        try {
            return Integer.parseInt(getProperty(this.id, 1));
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public String getErrMsg() {
        return getProperty(this.id, 2);
    }

    public int getId() {
        return this.id;
    }

    public String getNotificationName() {
        if (TextUtils.isEmpty(this.notificationName)) {
            this.notificationName = getProperty(this.id, 6);
        }
        return this.notificationName;
    }

    public String getProperty(int i2, int i3) {
        return DownloaderImpl.a().getProperty(i2, i3, 1);
    }

    public int getStatus() {
        return this.status;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public int getTotalFilesCount() {
        if (this.totalFilesCount == 0) {
            try {
                this.totalFilesCount = Integer.parseInt(getProperty(this.id, 4));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return this.totalFilesCount;
    }

    public int getTransientSpeed() {
        return this.transientSpeed;
    }

    public boolean isShowNotification() {
        if (this.showNotification == -1) {
            try {
                this.showNotification = Integer.parseInt(getProperty(this.id, 5));
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
        return "id= " + getId() + "\nstatus= " + getStatus() + "\ncurBytes= " + getCurBytes() + "\ntotalBytes= " + getTotalBytes();
    }

    public static DownloadMultiInfo fromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            DownloadMultiInfo downloadMultiInfo = new DownloadMultiInfo();
            downloadMultiInfo.id = jSONObject.optInt("id");
            downloadMultiInfo.status = jSONObject.optInt("status");
            downloadMultiInfo.curBytes = jSONObject.optLong(MonitorConstants.EXTRA_DOWNLOAD_CUR_BYTES);
            downloadMultiInfo.totalBytes = jSONObject.optLong(MonitorConstants.EXTRA_DOWNLOAD_TOTAL_BYTES);
            downloadMultiInfo.transientSpeed = jSONObject.optInt("transient_speed");
            return downloadMultiInfo;
        }
        return null;
    }
}
