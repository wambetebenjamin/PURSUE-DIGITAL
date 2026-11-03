package com.bytedance.praisedialoglib.depend;

import android.app.Activity;
import android.content.Context;
import org.json.JSONObject;
/* compiled from: IPraiseDialogAppConfig.java */
/* loaded from: classes2.dex */
public interface a {
    String a();

    String b();

    Activity c();

    String getAppId();

    String getDeviceId();

    String getPackageName();

    void goToFeedback(Context context, String str);

    boolean handleNoAppMarket();

    boolean needShowDefaultDialog();

    void onEvent(String str, JSONObject jSONObject);
}
