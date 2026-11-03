package com.bytedance.ttgame.channelapi.monitor;

import kotlin.Metadata;
/* compiled from: MonitorLoggerFactory.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/MonitorLoggerFactory;", "", "()V", "TYPE_GSDK", "", "newMonitorLogger", "Lcom/bytedance/ttgame/channelapi/monitor/IMonitorLogger;", "type", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonitorLoggerFactory {
    public static final MonitorLoggerFactory INSTANCE = new MonitorLoggerFactory();
    public static final int TYPE_GSDK = 1;

    private MonitorLoggerFactory() {
    }

    public final IMonitorLogger newMonitorLogger(int i2) {
        if (i2 == 1) {
            return new GsdkMonitorLogger();
        }
        return new GsdkMonitorLogger();
    }
}
