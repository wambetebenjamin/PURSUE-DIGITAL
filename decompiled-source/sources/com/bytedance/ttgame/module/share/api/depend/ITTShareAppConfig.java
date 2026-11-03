package com.bytedance.ttgame.module.share.api.depend;

import android.content.Context;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public abstract class ITTShareAppConfig {
    public abstract String getAppId();

    public abstract String getDeviceId();

    public abstract JSONObject getExtraConfig();

    public abstract void openPage(Context context, String str);
}
