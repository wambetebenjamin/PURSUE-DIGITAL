package com.bytedance.ttgame.core.realtime;

import com.bytedance.ttgame.core.SdkConfig;
import gsdk.impl.core.DEFAULT.a;
/* loaded from: classes3.dex */
public final class RealtimeEventConfig {
    public static final String APP_KEY = "9f5aa8315a04d70f864bca9b07cedae5";
    public static final int BATCH_LIMIT = 50;
    public static final long CACHE_TTL_MS = 432000000;
    private static final String REALTIME_EVENT_REPORT_URL = "realtime_event_report_url";
    private static final String REALTIME_EVENT_REPORT_URL_SANDBOX = "realtime_event_report_url_sandbox";

    private RealtimeEventConfig() {
    }

    public static String getEndpoint(SdkConfig sdkConfig) {
        return a.a(sdkConfig != null && sdkConfig.mIsSandBox ? REALTIME_EVENT_REPORT_URL_SANDBOX : REALTIME_EVENT_REPORT_URL);
    }
}
