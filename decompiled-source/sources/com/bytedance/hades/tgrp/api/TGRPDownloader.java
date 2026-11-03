package com.bytedance.hades.tgrp.api;

import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface TGRPDownloader {
    public static final int POLICY_COMBINE = 16;
    public static final int POLICY_EQUAL_INTERRUPT = 4;
    public static final int POLICY_FIFO = 0;
    public static final int POLICY_FLEX_INTERRUPT = 8;
    public static final int POLICY_LIFO = 1;
    public static final int POLICY_LOW_INTERRUPT = 2;

    /* loaded from: classes2.dex */
    public static class BatchDownloadInfo {
        public long downloadedBytes;
        public long totalBytes;
    }

    /* loaded from: classes2.dex */
    public static class InitConfig {
        public String appBaseResJson;
        public String[] backUpHosts;
        public String config;
        public String[] diffPatchUrls;
        public JSONObject headers;
        public String[] incrementalPackUrls;
        public String url;

        /* loaded from: classes2.dex */
        public static class Builder {
            public String appBaseResJson;
            public String[] backUpHosts;
            public String config;
            public String[] diffPatchUrls;
            public JSONObject headers;
            public String[] incrementalPackUrls;
            public String url;

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

            public Builder appBaseResJson(String str) {
                this.appBaseResJson = str;
                return this;
            }

            public Builder backUpHosts(String[] strArr) {
                this.backUpHosts = strArr;
                return this;
            }

            public InitConfig build() {
                return new InitConfig(this);
            }

            public Builder config(String str) {
                this.config = str;
                return this;
            }

            public Builder diffPatchUrls(String[] strArr) {
                this.diffPatchUrls = strArr;
                return this;
            }

            public Builder incrementalPackUrls(String[] strArr) {
                this.incrementalPackUrls = strArr;
                return this;
            }
        }

        public String getAppBaseResJson() {
            return this.appBaseResJson;
        }

        public String[] getBackUpHosts() {
            return this.backUpHosts;
        }

        public String getConfig() {
            return this.config;
        }

        public String[] getDiffPatchUrls() {
            return this.diffPatchUrls;
        }

        public JSONObject getHeaders() {
            return this.headers;
        }

        public String[] getIncrementalPackUrls() {
            return this.incrementalPackUrls;
        }

        public String getUrl() {
            return this.url;
        }

        private InitConfig(Builder builder) {
            String str = builder.url;
            this.url = str == null ? "" : str;
            String[] strArr = builder.backUpHosts;
            this.backUpHosts = strArr == null ? new String[0] : strArr;
            String[] strArr2 = builder.diffPatchUrls;
            this.diffPatchUrls = strArr2 == null ? new String[0] : strArr2;
            String[] strArr3 = builder.incrementalPackUrls;
            this.incrementalPackUrls = strArr3 == null ? new String[0] : strArr3;
            String str2 = builder.appBaseResJson;
            this.appBaseResJson = str2 == null ? "{}" : str2;
            JSONObject jSONObject = builder.headers;
            this.headers = jSONObject == null ? new JSONObject() : jSONObject;
            String str3 = builder.config;
            this.config = str3 != null ? str3 : "";
        }
    }

    void clearBatchInfo();

    void clearBatchInfo(int i2);

    void clearResNotInBatch();

    int createBatchByAllRes();

    int createBatchByAllRes(boolean z);

    int createBatchByDirectory(String str);

    int createBatchByDirectory(String str, boolean z);

    int createBatchByLabel(String str);

    int createBatchByLabel(String str, boolean z);

    int createBatchByResList(ResInfo[] resInfoArr);

    int createBatchByResList(ResInfo[] resInfoArr, boolean z);

    boolean deleteRes(ResInfo resInfo);

    boolean deleteRes(String str);

    @Deprecated
    int downloadAllRes();

    @Deprecated
    int downloadAllRes(TGRPBatchDownloadListener tGRPBatchDownloadListener);

    @Deprecated
    int downloadAllRes(TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z);

    @Deprecated
    int downloadByLabel(String str);

    @Deprecated
    int downloadByLabel(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener);

    @Deprecated
    int downloadByLabel(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z);

    @Deprecated
    int downloadResInDirectory(String str);

    @Deprecated
    int downloadResInDirectory(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener);

    @Deprecated
    int downloadResInDirectory(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z);

    @Deprecated
    int downloadResList(ResInfo[] resInfoArr);

    @Deprecated
    int downloadResList(ResInfo[] resInfoArr, TGRPBatchDownloadListener tGRPBatchDownloadListener);

    @Deprecated
    int downloadResList(ResInfo[] resInfoArr, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z);

    int excludeResFromBatch(int i2, ResInfo[] resInfoArr);

    long getAllResDataDownloadedBytes();

    long getAllResDataTotalBytes();

    BatchDownloadInfo getBatchDownloadInfo(int i2);

    long getDownloadedBytesByLabel(String str);

    ResInfo[] getDownloadedResInfoList();

    int getDownloadingBatchId();

    int[] getDownloadingBatchSequence();

    long getListResDataDownloadedBytes(ResInfo[] resInfoArr);

    long getListResDataTotalBytes(ResInfo[] resInfoArr);

    ResInfo[] getNotDownloadedResInfoList();

    int getResCount();

    ResInfo getResInfo(String str);

    ResInfo[] getResInfoList();

    ResInfo[] getResInfoListByLabel(String str);

    long getTotalBytesByLabel(String str);

    void init(InitConfig initConfig);

    boolean isInited();

    boolean isIniting();

    boolean isResFileCorrect(ResInfo resInfo);

    boolean isResFileCorrect(String str);

    boolean isResFileDownloaded(ResInfo resInfo);

    boolean isResFileDownloaded(String str);

    void pauseDownload();

    void pauseDownload(int i2);

    TGRPDownloader registerBatchListener(int i2, TGRPBatchDownloadListener tGRPBatchDownloadListener);

    TGRPDownloader registerGlobalDownloadListener(TGRPGlobalDownloadListener tGRPGlobalDownloadListener);

    TGRPDownloader registerInitListener(TGRPInitListener tGRPInitListener);

    TGRPDownloader registerPatchListener(int i2, TGRPPatchListener tGRPPatchListener);

    TGRPDownloader registerResCheckListener(int i2, TGRPResCheckListener tGRPResCheckListener);

    int resCheck(int i2);

    void setBatchPreDownload(int i2, boolean z);

    void setBatchStage(int i2, boolean z);

    void setFileSystem(long j2);

    TGRPDownloader setMaxParallelCount(int i2);

    TGRPDownloader setOnlyWifi(boolean z);

    void setPriority(int i2, int i3);

    void setPriority(int i2, int i3, int i4);

    TGRPDownloader setRetryTime(int i2);

    TGRPDownloader setThrottleNetSpeed(long j2);

    void startDownload(int i2);

    void startDownload(int[] iArr);

    TGRPDownloader unregisterBatchListener(int i2);

    TGRPDownloader unregisterGlobalDownloadListener();

    TGRPDownloader unregisterInitListener();

    TGRPDownloader unregisterPatchListener(int i2);

    TGRPDownloader unregisterResCheckListener(int i2);
}
