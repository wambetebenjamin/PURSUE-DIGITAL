package com.bytedance.ttgame.module.share.api.depend;

import org.json.JSONObject;
/* loaded from: classes7.dex */
public interface ITTShareNetworkConfig {
    int checkResponseException(Throwable th);

    String executeGet(int i2, String str) throws Exception;

    String executePost(int i2, String str, JSONObject jSONObject) throws Exception;

    String getHost();
}
