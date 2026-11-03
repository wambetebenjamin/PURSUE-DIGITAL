package com.bytedance.ttgame.channelapi.monitor;

import java.util.Map;
import kotlin.Metadata;
/* compiled from: IMonitorLogger.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JH\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\tH&¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/channelapi/monitor/IMonitorLogger;", "", "log", "", "event", "", "status", "", "metrics", "", "extras", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IMonitorLogger {
    void log(String str, int i2, Map<String, ? extends Object> map, Map<String, ? extends Object> map2);

    /* compiled from: IMonitorLogger.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void log$default(IMonitorLogger iMonitorLogger, String str, int i2, Map map, Map map2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
            }
            if ((i3 & 4) != 0) {
                map = null;
            }
            if ((i3 & 8) != 0) {
                map2 = null;
            }
            iMonitorLogger.log(str, i2, map, map2);
        }
    }
}
