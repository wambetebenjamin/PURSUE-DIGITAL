package com.bytedance.hades.downloader.api.data;

import com.bytedance.hades.downloader.api.listener.IDownloadListener;
import com.bytedance.hades.downloader.impl.settings.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.constants.DbJsonConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DownloadConfig {
    public boolean autoResume;
    public int backupHostRetryCount;
    public List<String> backupHosts;
    public IDownloadListener downloadListener;
    public String downloadSetting;
    public long endOffset;
    public long expectFileLength;
    public String fileName;
    public String headers;
    public String md5;
    public String notificationName;
    public boolean onlyWifi;
    public boolean overWrite;
    public int retryCount;
    public String savePath;
    public boolean showNotification;
    public long startOffset;
    public int throttleNetSpeed;
    public boolean throttleOnlyForeground;
    public int throttleSmoothness;
    public String url;

    /* loaded from: classes2.dex */
    public static class Builder {
        public List<String> backupHosts;
        public String downloadSetting;
        public String fileName;
        public JSONObject headers;
        public String md5;
        public String notificationName;
        public String savePath;
        public String url;
        public boolean onlyWifi = false;
        public boolean overWrite = false;
        public int retryCount = 3;
        public int backupHostRetryCount = 1;
        public int throttleNetSpeed = 0;
        public int throttleSmoothness = 0;
        public long startOffset = 0;
        public long endOffset = 0;
        public IDownloadListener downloadListener = null;
        public boolean showNotification = false;
        public long expectFileLength = 0;
        public boolean throttleOnlyForeground = false;
        public boolean autoResume = false;

        public Builder(String str) {
            this.url = str;
        }

        public Builder addRequestHeader(String str, String str2) {
            if (this.headers == null) {
                this.headers = new JSONObject();
            }
            try {
                this.headers.put(str, str2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return this;
        }

        public DownloadConfig build() {
            return new DownloadConfig(this);
        }

        public Builder setAutoResume(boolean z) {
            this.autoResume = z;
            return this;
        }

        public Builder setBackupHostRetryCount(int i2) {
            this.backupHostRetryCount = i2;
            return this;
        }

        public Builder setBackupHosts(List<String> list) {
            this.backupHosts = list;
            return this;
        }

        public Builder setDownloadListener(IDownloadListener iDownloadListener) {
            this.downloadListener = iDownloadListener;
            return this;
        }

        public Builder setDownloadSetting(String str) {
            this.downloadSetting = str;
            return this;
        }

        public Builder setEndOffset(long j2) {
            this.endOffset = j2;
            return this;
        }

        public Builder setExpectFileLength(long j2) {
            this.expectFileLength = j2;
            return this;
        }

        public Builder setFileName(String str) {
            this.fileName = str;
            return this;
        }

        public Builder setMd5(String str) {
            this.md5 = str;
            return this;
        }

        public Builder setNotificationName(String str) {
            this.notificationName = str;
            return this;
        }

        public Builder setOnlyWifi(boolean z) {
            this.onlyWifi = z;
            return this;
        }

        public Builder setOverWrite(boolean z) {
            this.overWrite = z;
            return this;
        }

        public Builder setRetryCount(int i2) {
            this.retryCount = i2;
            return this;
        }

        public Builder setSavePath(String str) {
            this.savePath = str;
            return this;
        }

        public Builder setShowNotification(boolean z) {
            this.showNotification = z;
            return this;
        }

        public Builder setStartOffset(long j2) {
            this.startOffset = j2;
            return this;
        }

        public Builder setThrottleNetSpeed(int i2) {
            this.throttleNetSpeed = i2;
            return this;
        }

        public Builder setThrottleOnlyForeground(boolean z) {
            this.throttleOnlyForeground = z;
            return this;
        }

        public Builder setThrottleSmoothness(int i2) {
            this.throttleSmoothness = i2;
            return this;
        }
    }

    public IDownloadListener getDownloadListener() {
        return this.downloadListener;
    }

    public long getExpectFileLength() {
        return this.expectFileLength;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getNotificationName() {
        return this.notificationName;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isShowNotification() {
        return this.showNotification;
    }

    public boolean isThrottleOnlyForeground() {
        return this.throttleOnlyForeground;
    }

    public void setOnlyWifi(boolean z) {
        this.onlyWifi = z;
    }

    public void setThrottleOnlyForeground(boolean z) {
        this.throttleOnlyForeground = z;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.url);
            jSONObject.put("md5", this.md5);
            jSONObject.put("file_name", this.fileName);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_SAVE_PATH, this.savePath);
            jSONObject.put(DbJsonConstants.DOWNLOAD_SETTING, this.downloadSetting);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, this.onlyWifi);
            jSONObject.put("over_write", this.overWrite);
            jSONObject.put("retry_count", this.retryCount);
            List<String> list = this.backupHosts;
            if (list != null && list.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < this.backupHosts.size(); i2++) {
                    jSONArray.put(i2, this.backupHosts.get(i2));
                }
                jSONObject.put("backup_hosts", jSONArray);
                jSONObject.put("backup_host_retry_count", this.backupHostRetryCount);
            }
            jSONObject.put("throttle_net_speed", this.throttleNetSpeed);
            jSONObject.put("throttle_smoothness", this.throttleSmoothness);
            jSONObject.put(DbJsonConstants.DBJSON_KEY_START_OFFSET, this.startOffset);
            jSONObject.put(DbJsonConstants.DBJSON_KEY_END_OFFSET, this.endOffset);
            jSONObject.put(DownloadSettingKeys.SHOW_NOTIFICATION, this.showNotification);
            jSONObject.put("notification_name", this.notificationName);
            jSONObject.put("expect_file_length", this.expectFileLength);
            jSONObject.put("throttle_only_foreground", this.throttleOnlyForeground);
            jSONObject.put("headers", this.headers);
            jSONObject.put(com.ss.android.socialbase.downloader.setting.DownloadSettingKeys.AUTO_RESUME, this.autoResume);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public String toJsonStr() {
        return toJson().toString();
    }

    private DownloadConfig(Builder builder) {
        this.url = builder.url;
        this.md5 = builder.md5;
        this.fileName = builder.fileName;
        this.savePath = builder.savePath;
        this.downloadSetting = builder.downloadSetting;
        this.onlyWifi = builder.onlyWifi;
        this.overWrite = builder.overWrite;
        this.retryCount = builder.retryCount;
        this.backupHosts = builder.backupHosts;
        this.backupHostRetryCount = builder.backupHostRetryCount;
        this.throttleNetSpeed = builder.throttleNetSpeed;
        this.throttleSmoothness = builder.throttleSmoothness;
        this.startOffset = builder.startOffset;
        this.endOffset = builder.endOffset;
        this.downloadListener = builder.downloadListener;
        this.showNotification = builder.showNotification;
        this.notificationName = builder.notificationName;
        this.expectFileLength = builder.expectFileLength;
        this.throttleOnlyForeground = builder.throttleOnlyForeground;
        JSONObject jSONObject = builder.headers;
        if (jSONObject != null) {
            this.headers = jSONObject.toString();
        }
        this.autoResume = builder.autoResume;
    }
}
