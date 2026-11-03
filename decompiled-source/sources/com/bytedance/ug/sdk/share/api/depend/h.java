package com.bytedance.ug.sdk.share.api.depend;

import android.app.Activity;
import org.json.JSONObject;
/* compiled from: IShareInterceptConfig.java */
/* loaded from: classes8.dex */
public interface h {
    String executeGet(int i2, String str);

    String executePost(int i2, String str, JSONObject jSONObject);

    JSONObject getExtraConfig();

    boolean handleQrScanResult(Activity activity, String str);

    boolean interceptNetwork(String str);
}
