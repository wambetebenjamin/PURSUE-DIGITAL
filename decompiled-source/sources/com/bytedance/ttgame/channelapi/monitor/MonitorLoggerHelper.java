package com.bytedance.ttgame.channelapi.monitor;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MonitorLoggerHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/MonitorLoggerHelper;", "", "()V", "Companion", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MonitorLoggerHelper {
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    public static final void monitorLog(IMonitorLogger iMonitorLogger, String str, int i2) {
        Companion.monitorLog(iMonitorLogger, str, i2);
    }

    @JvmStatic
    public static final void monitorLog(IMonitorLogger iMonitorLogger, String str, int i2, int i3) {
        Companion.monitorLog(iMonitorLogger, str, i2, i3);
    }

    @JvmStatic
    public static final void monitorLog(IMonitorLogger iMonitorLogger, String str, int i2, int i3, String str2) {
        Companion.monitorLog(iMonitorLogger, str, i2, i3, str2);
    }

    /* compiled from: MonitorLoggerHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/MonitorLoggerHelper$Companion;", "", "()V", "monitorLog", "", "monitorLogger", "Lcom/bytedance/ttgame/channelapi/monitor/IMonitorLogger;", "event", "", "status", "", "code", "msg", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void monitorLog(IMonitorLogger iMonitorLogger, String event, int i2) {
            Intrinsics.checkNotNullParameter(event, "event");
            monitorLog$default(this, iMonitorLogger, event, i2, 0, null, 24, null);
        }

        @JvmStatic
        public final void monitorLog(IMonitorLogger iMonitorLogger, String event, int i2, int i3) {
            Intrinsics.checkNotNullParameter(event, "event");
            monitorLog$default(this, iMonitorLogger, event, i2, i3, null, 16, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void monitorLog$default(Companion companion, IMonitorLogger iMonitorLogger, String str, int i2, int i3, String str2, int i4, Object obj) {
            if ((i4 & 8) != 0) {
                i3 = Integer.MIN_VALUE;
            }
            int i5 = i3;
            if ((i4 & 16) != 0) {
                str2 = null;
            }
            companion.monitorLog(iMonitorLogger, str, i2, i5, str2);
        }

        @JvmStatic
        public final void monitorLog(IMonitorLogger iMonitorLogger, String event, int i2, int i3, String str) {
            Intrinsics.checkNotNullParameter(event, "event");
            Map<String, ? extends Object> mapOf = i3 == Integer.MIN_VALUE ? null : MapsKt.mapOf(TuplesKt.to(MonitorConstants.KEY_CODE, Integer.valueOf(i3)));
            Map<String, ? extends Object> mapOf2 = str != null ? MapsKt.mapOf(TuplesKt.to(MonitorConstants.KEY_MSG, str)) : null;
            if (iMonitorLogger != null) {
                iMonitorLogger.log(event, i2, mapOf, mapOf2);
            }
        }
    }
}
