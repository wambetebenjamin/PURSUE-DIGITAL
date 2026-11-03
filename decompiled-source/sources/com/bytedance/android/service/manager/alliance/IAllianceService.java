package com.bytedance.android.service.manager.alliance;

import android.content.Context;
import com.bytedance.android.push.service.manager.annotation.a;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface IAllianceService {
    public static final String CONTENT_TYPE_BADGE = "badge";
    public static final String CONTENT_TYPE_LOCAL_PUSH = "local_push";
    public static final String PATH_GET_COMPOSE = "get_compose";
    public static final String PATH_POST_COMPOSE = "post_compose";

    boolean allowStartOthersProcessFromSmp();

    void doAfterProcessIsolationProtected(Runnable runnable);

    boolean hasWaked();

    IsSupportWakeUp isSupportWakeUp(Context context);

    void onApplicationInit(String str, Context context);

    void onEventV3(boolean z, String str, JSONObject jSONObject);

    void onEventV3WithHttp(boolean z, String str, JSONObject jSONObject);

    void onWorkerApplicationStart();

    void updateSettings(Context context, JSONObject jSONObject);

    boolean verifySign(String str, String str2);

    void wakeUpTargetPartner(JSONObject jSONObject);
}
