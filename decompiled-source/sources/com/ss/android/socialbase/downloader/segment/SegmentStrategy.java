package com.ss.android.socialbase.downloader.segment;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class SegmentStrategy {
    private static final int MAX_THREAD_COUNT = 16;
    private static final long MIN_CONNECT_TIMEOUT = 2000;
    private static final long MIN_READ_TIMEOUT = 4000;
    private static final long SEGMENT_MIN_INIT_SIZE = 5242880;
    private static final long SEGMENT_MIN_SIZE = 65536;
    public static final String TAG = "SegmentStrategy";
    private final JSONObject config;
    private int threadCount;

    private SegmentStrategy(JSONObject jSONObject) {
        this.config = jSONObject;
    }

    public void updateUrlCount(int i2) {
        this.threadCount = calculateThreadCount(i2);
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    private int getUrlBalance() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.URL_BALANCE, 2);
    }

    public boolean urlBalance() {
        return getUrlBalance() > 0;
    }

    public boolean urlBalanceStrictly() {
        return getUrlBalance() == 1;
    }

    public int getBufferCount() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.BUFFER_COUNT, 512);
    }

    public int getBufferSize() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.BUFFER_SIZE, 8192);
    }

    public boolean segmentOneByOne() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MODE, 1) == 0;
    }

    public long getSegmentMinSize() {
        long optInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MIN_KB, 512) * 1024;
        if (optInt < 65536) {
            return 65536L;
        }
        return optInt;
    }

    public long getSegmentMinInitSize() {
        long optInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MIN_INIT_MB, 10) * 1048576;
        return optInt < SEGMENT_MIN_INIT_SIZE ? SEGMENT_MIN_INIT_SIZE : optInt;
    }

    public long getSegmentMaxSize() {
        long optInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MAX_KB, 0) * 1048576;
        if (optInt < getSegmentMinSize()) {
            return -1L;
        }
        return optInt;
    }

    public long getConnectTimeout() {
        long optInt = this.config.optInt("connect_timeout", -1);
        if (optInt >= 2000) {
            return optInt;
        }
        return -1L;
    }

    public long getReadTimeout() {
        long optInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.READ_TIMEOUT, -1);
        if (optInt >= MIN_READ_TIMEOUT) {
            return optInt;
        }
        return -1L;
    }

    public int getIpStrategy() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.IP_STRATEGY, 0);
    }

    private int calculateThreadCount(int i2) {
        int optInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.THREAD_COUNT, 4);
        if (optInt > 16) {
            optInt = 16;
        }
        if (optInt > 0) {
            return getUrlBalance() == 1 ? Math.min(optInt, i2) : optInt;
        } else if (getUrlBalance() > 0) {
            return i2;
        } else {
            return 1;
        }
    }

    public float getMainRatio() {
        return (float) this.config.optDouble(DownloadSettingKeys.SegmentConfig.MAIN_RATIO, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public int getRatioSegmentStrategy() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.RATIO_SEGMENT, 0);
    }

    public float getPoorSpeedRatio() {
        return Math.min(Math.max(0.0f, (float) this.config.optDouble(DownloadSettingKeys.SegmentConfig.POOR_SPEED_RATIO, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)), 1.0f);
    }

    public static SegmentStrategy from(JSONObject jSONObject) {
        return new SegmentStrategy(jSONObject);
    }
}
