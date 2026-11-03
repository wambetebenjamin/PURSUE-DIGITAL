package com.bytedance.push.settings;
@com.bytedance.push.settings.annotation.f(storageKey = StatisticsSettings.F_NAME_SP)
/* loaded from: classes2.dex */
public interface StatisticsSettings extends ISettings {
    public static final long DEFAULT_STATS_INTERVAL = 300000;
    public static final String F_NAME_SP = "ttpush_stats_settings";
    public static final String KEY_STATS_BACKGROUND = "stats_back_interval";
    public static final String KEY_STATS_FOREGROUND = "stats_fore_interval";

    long statsBackgroundInterval();

    long statsForegroundInterval();
}
