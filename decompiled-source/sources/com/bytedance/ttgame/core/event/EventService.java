package com.bytedance.ttgame.core.event;

import android.text.TextUtils;
import com.bytedance.ttgame.framework.module.util.FlavorUtilKt;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.event.IEventClient;
import com.bytedance.ttgame.main.event.IEventService;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.log.IGLogService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.sdk.module.core.internal.RocketConstants;
import com.meituan.robust.Constants;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class EventService implements IEventService {
    private IEventClient eventClient;
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final IGLogService logService = (IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class);

    @Override // com.bytedance.ttgame.main.event.IEventService
    public void setEventClient(IEventClient iEventClient) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "setEventClient", new String[]{"com.bytedance.ttgame.main.event.IEventClient"}, Constants.VOID);
        this.eventClient = iEventClient;
        if (iEventClient != null) {
            iEventClient.init();
        }
    }

    @Override // com.bytedance.ttgame.main.event.IEventService
    public void onEvent(String str, JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "onEvent", new String[]{"java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        onEvent(false, str, jSONObject);
    }

    @Override // com.bytedance.ttgame.main.event.IEventService
    @InternalApi
    public void onEvent(boolean z, String str, JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.event.IEventService", "com.bytedance.ttgame.core.event.EventService", "onEvent", new String[]{"boolean", "java.lang.String", "org.json.JSONObject"}, Constants.VOID);
        if (TextUtils.isEmpty(str)) {
            this.logService.e(IEventService.TAG, "onEvent meet empty event");
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (z) {
            try {
                if (!str.startsWith("mgame_")) {
                    str = "mgame_" + str;
                    jSONObject.put("params_for_special", FlavorUtilKt.isCnFlavor() ? RocketConstants.XUANWU : RocketConstants.XUANWU_OVERSEAS);
                }
            } catch (JSONException e2) {
                IGLogService iGLogService = this.logService;
                if (iGLogService != null) {
                    iGLogService.e(IEventService.TAG, "onEvent meet JSONException: " + e2.getLocalizedMessage());
                }
            }
        }
        String uuid = UUID.randomUUID().toString();
        String deviceId = ((ICoreInternalService) ModuleManager.INSTANCE.getService(ICoreInternalService.class)).getDeviceId();
        if (deviceId != null) {
            uuid = deviceId + uuid;
        }
        jSONObject.put("gsdk_event_uuid", uuid);
        IEventClient iEventClient = this.eventClient;
        if (iEventClient != null) {
            iEventClient.sendEventData(str, jSONObject);
            return;
        }
        IGLogService iGLogService2 = this.logService;
        if (iGLogService2 != null) {
            iGLogService2.e(IEventService.TAG, "EventClient must not be null, you need check it");
        }
    }
}
