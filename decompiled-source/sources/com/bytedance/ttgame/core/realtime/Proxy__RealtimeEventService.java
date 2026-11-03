package com.bytedance.ttgame.core.realtime;

import com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService;
import com.meituan.robust.Constants;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class Proxy__RealtimeEventService implements IRealtimeEventService {
    private RealtimeEventService proxy = new RealtimeEventService();

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public List dumpPendingRealtimeEvents() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "dumpPendingRealtimeEvents", new String[0], "java.util.List");
        return this.proxy.dumpPendingRealtimeEvents();
    }

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public void flushPendingAfterDidReady() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "flushPendingAfterDidReady", new String[0], Constants.VOID);
        this.proxy.flushPendingAfterDidReady();
    }

    @Override // com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService
    public void report(String str, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", "com.bytedance.ttgame.core.realtime.RealtimeEventService", "report", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.report(str, jSONObject);
    }

    public IRealtimeEventService getProxy() {
        return this.proxy;
    }
}
