package com.bytedance.ug.sdk.share.api.depend;

import org.json.JSONObject;
/* compiled from: IShareNetworkConfig.java */
/* loaded from: classes8.dex */
public interface k {
    int checkResponseException(Throwable th);

    String executeGet(int i2, String str);

    String executePost(int i2, String str, JSONObject jSONObject);

    String getHost();
}
