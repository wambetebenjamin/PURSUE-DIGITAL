package com.bytedance.ug.sdk.share.api.depend;

import android.content.Context;
import org.json.JSONObject;
/* compiled from: IShareAppConfig.java */
/* loaded from: classes8.dex */
public interface a {
    String getAppId();

    String getDeviceId();

    JSONObject getExtraConfig();

    void openPage(Context context, String str);
}
