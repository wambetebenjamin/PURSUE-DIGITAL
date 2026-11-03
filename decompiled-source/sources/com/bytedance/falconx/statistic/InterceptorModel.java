package com.bytedance.falconx.statistic;

import android.os.SystemClock;
import com.bytedance.apm.constant.l;
import com.bytedance.bdinstall.e;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class InterceptorModel {
    @SerializedName(e.aE)
    public String ac;
    @SerializedName("access_key")
    public String accessKey;
    @SerializedName("channel")
    public String channel;
    @SerializedName("err_code")
    public String errCode;
    @SerializedName("err_msg")
    public String errMsg;
    public boolean isCombo;
    @SerializedName(l.KEY_LOG_ID)
    public String logId;
    @SerializedName("mime_type")
    public String mimeType;
    @SerializedName("offline_duration")
    public Long offlineDuration;
    @SerializedName("offline_rule")
    public String offlineRule;
    @SerializedName("offline_status")
    public Integer offlineStatus;
    @SerializedName("online_duration")
    public Long onlineDuration;
    @SerializedName("page_url")
    public String pageUrl;
    @SerializedName("pkg_version")
    public Long pkgVersion;
    @SerializedName("res_root_dir")
    public String resRootDir;
    public Long startTime = Long.valueOf(SystemClock.uptimeMillis());
    @SerializedName("resource_url")
    public String url;

    public void setErrorCode(String str) {
        this.errCode = str;
    }

    public void setErrorMsg(String str) {
        this.errMsg = str;
    }

    public long getVersion() {
        Long l = this.pkgVersion;
        if (l == null) {
            return -1L;
        }
        return l.longValue();
    }

    public void loadFinish(boolean z) {
        if (z) {
            this.offlineDuration = Long.valueOf(SystemClock.uptimeMillis() - this.startTime.longValue());
            this.offlineStatus = 1;
            return;
        }
        this.offlineStatus = 0;
    }
}
