package com.bytedance.geckox.statistic.model;

import com.bytedance.apm.constant.l;
import com.bytedance.bdinstall.e;
import com.bytedance.geckox.b;
import com.bytedance.geckox.model.Common;
import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class StatisticModel {
    @SerializedName("common")
    public Common common;
    @SerializedName("packages")
    public List<PackageStatisticModel> packages = new ArrayList();

    /* loaded from: classes2.dex */
    public static class PackageStatisticModel {
        @SerializedName(e.aE)
        public String ac;
        @SerializedName("access_key")
        public String accessKey;
        @SerializedName("active_check_duration")
        public Long activeCheckDuration;
        @SerializedName(b.g.f8910c)
        public String apiVersion;
        @SerializedName("apply_duration")
        public Long applyDuration;
        @SerializedName("channel")
        public String channel;
        @SerializedName("clean_duration")
        public Long cleanDuration;
        @SerializedName("clean_strategy")
        public Integer cleanStrategy;
        @SerializedName("clean_type")
        public Integer cleanType;
        @SerializedName("download_duration")
        public Long downloadDuration;
        @SerializedName("download_fail_records")
        public List<DownloadFailRecords> downloadFailRecords;
        @SerializedName("download_retry_times")
        public Integer downloadRetryTimes;
        @SerializedName("download_url")
        public String downloadUrl;
        @SerializedName("err_code")
        public String errCode;
        @SerializedName("err_msg")
        public String errMsg;
        @SerializedName("group_name")
        public String groupName;
        @SerializedName("id")
        public Long id;
        @SerializedName(l.KEY_LOG_ID)
        public String logId;
        @SerializedName("patch_id")
        public Long patchId;
        @SerializedName(b.g.f8908a)
        public int reqType;
        @SerializedName("stats_type")
        public Integer statsType;
        @SerializedName(b.g.f8909b)
        public int syncTaskId;
        @SerializedName("total_duration")
        public Long totalDuration;

        public PackageStatisticModel() {
        }

        public PackageStatisticModel(String str, String str2, Integer num, Integer num2, Long l, Integer num3, String str3, String str4) {
            this.channel = str2;
            this.accessKey = str;
            this.cleanType = num;
            this.cleanStrategy = num2;
            this.cleanDuration = l;
            this.statsType = num3;
            this.errCode = str3;
            this.errMsg = str4;
        }

        /* loaded from: classes2.dex */
        public static class DownloadFailRecords {
            @SerializedName("domain")
            public String domain;
            @SerializedName("duration")
            public Long duration;
            @SerializedName(c.de)
            public String reason;

            public DownloadFailRecords(String str, String str2) {
                this.domain = str;
                this.reason = str2;
            }

            public DownloadFailRecords(String str, String str2, Long l) {
                this.domain = str;
                this.reason = str2;
                this.duration = l;
            }
        }
    }
}
