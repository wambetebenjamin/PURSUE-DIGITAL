package com.bytedance.android.service.manager.alive.monitor;

import android.content.Context;
import com.bytedance.android.push.service.manager.annotation.a;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface AliveMonitorService {
    JSONObject getProcessStartInfoObject(Context context);

    boolean hasHookActivityManagerSuccess(Context context);

    boolean hasHookActivityTaskManagerSuccess(Context context);

    void monitorAssociationStart(Context context);

    void monitorAssociationStart(Context context, IMonitorCallback iMonitorCallback);

    void onUserActive();
}
