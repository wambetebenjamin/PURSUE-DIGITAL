package com.bytedance.ttgame.core.event;

import com.bytedance.ttgame.main.event.IEventClient;
import com.bytedance.ttgame.main.event.IEventService;
import com.meituan.robust.Constants;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class Proxy__EventService implements IEventService {
    private EventService proxy = new EventService();

    @Override // com.bytedance.ttgame.main.event.IEventService
    public void onEvent(String str, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "onEvent", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.onEvent(str, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.event.IEventService
    public void onEvent(boolean z, String str, JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "onEvent", new String[]{"boolean", "java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        this.proxy.onEvent(z, str, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.event.IEventService
    public void setEventClient(IEventClient iEventClient) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "setEventClient", new String[]{"com.bytedance.ttgame.main.event.IEventClient"}, Constants.VOID);
        this.proxy.setEventClient(iEventClient);
    }

    public IEventService getProxy() {
        return this.proxy;
    }
}
