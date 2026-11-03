package com.bytedance.hades.downloader.api.data;

import com.bytedance.hades.downloader.api.listener.IDownloadListener;
import com.bytedance.hades.downloader.api.listener.IDownloadMultiListener;
import java.util.List;
/* loaded from: classes2.dex */
public class DownloadMultiConfig {
    public boolean autoResume;
    public List<DownloadConfig> configList;
    public IDownloadListener downloadListener;
    public IDownloadMultiListener downloadMultiListener;
    public String notificationName;
    public boolean showNotification;

    /* loaded from: classes2.dex */
    public static class Builder {
        public List<DownloadConfig> configList;
        public IDownloadListener downloadListener;
        public IDownloadMultiListener downloadMultiListener;
        public String notificationName;
        public boolean showNotification = false;
        public boolean autoResume = false;

        public Builder(List<DownloadConfig> list) {
            this.configList = list;
        }

        public DownloadMultiConfig build() {
            return new DownloadMultiConfig(this);
        }

        public Builder setAutoResume(boolean z) {
            this.autoResume = z;
            return this;
        }

        public Builder setDownloadListener(IDownloadListener iDownloadListener) {
            this.downloadListener = iDownloadListener;
            return this;
        }

        public Builder setDownloadMultiListener(IDownloadMultiListener iDownloadMultiListener) {
            this.downloadMultiListener = iDownloadMultiListener;
            return this;
        }

        public Builder setNotificationName(String str) {
            this.notificationName = str;
            return this;
        }

        public Builder setShowNotification(boolean z) {
            this.showNotification = z;
            return this;
        }
    }

    public List<DownloadConfig> getConfigList() {
        return this.configList;
    }

    public IDownloadListener getDownloadListener() {
        return this.downloadListener;
    }

    public IDownloadMultiListener getDownloadMultiListener() {
        return this.downloadMultiListener;
    }

    public String getNotificationName() {
        return this.notificationName;
    }

    public boolean isAutoResume() {
        return this.autoResume;
    }

    public boolean isShowNotification() {
        return this.showNotification;
    }

    private DownloadMultiConfig(Builder builder) {
        this.showNotification = builder.showNotification;
        this.notificationName = builder.notificationName;
        this.configList = builder.configList;
        this.downloadListener = builder.downloadListener;
        this.downloadMultiListener = builder.downloadMultiListener;
        this.autoResume = builder.autoResume;
    }
}
