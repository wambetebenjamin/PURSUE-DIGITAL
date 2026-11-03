package com.bytedance.android.service.manager.push;

import android.content.Context;
import com.bytedance.android.push.service.manager.annotation.a;
import com.bytedance.android.service.manager.push.client.intelligence.IClientFeatureService;
import com.bytedance.android.service.manager.push.client.intelligence.IClientIntelligenceService;
import com.bytedance.android.service.manager.push.interfaze.IMultiProcessEventSenderService;
import com.bytedance.android.service.manager.push.monitor.IMultiProcessMonitor;
import com.bytedance.android.service.manager.push.monitor.IPushSdkMonitorService;
import com.bytedance.android.service.manager.push.notification.INotificationMonitorService;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface PushExternalService {
    boolean allowStartNonMainProcess();

    String checkAndGetValidChannelId(Context context, String str);

    void createDefaultChannel(Context context);

    boolean curIsWorkerProcess(Context context);

    IClientFeatureService getClientFeatureService();

    IClientIntelligenceService getClientIntelligenceService();

    IMultiProcessMonitor getIMultiProcessMonitor();

    IMultiProcessEventSenderService getMultiProcessEventSenderService();

    INotificationMonitorService getNotificationMonitorService();

    IPushSdkMonitorService getPushSdkMonitorService();

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void onNotificationDelete(long j2, JSONObject jSONObject);

    void showPush(JSONObject jSONObject, int i2, String str);

    void showPush(JSONObject jSONObject, int i2, String str, boolean z);

    void tryUpdateSender();
}
