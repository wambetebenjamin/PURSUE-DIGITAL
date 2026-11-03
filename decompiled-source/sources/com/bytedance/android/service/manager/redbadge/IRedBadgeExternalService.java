package com.bytedance.android.service.manager.redbadge;

import android.content.Context;
import com.bytedance.android.push.service.manager.annotation.a;
import com.ss.android.pushmanager.b;
import java.util.List;
import org.json.JSONObject;
@a
/* loaded from: classes.dex */
public interface IRedBadgeExternalService extends IRedBadgeService {
    public static final int BADGE_NUMBER_UNKNOWN = -1;

    int getBadgeNumberWhenAppLaunch(Context context);

    JSONObject getRedBadgeRequestBody(Context context, boolean z);

    List<Long> getRedBadgeShowHistoryList(Context context);

    String getRedBadgeShowHistoryStr(Context context);

    void startOnWorkerProcess(b bVar);
}
