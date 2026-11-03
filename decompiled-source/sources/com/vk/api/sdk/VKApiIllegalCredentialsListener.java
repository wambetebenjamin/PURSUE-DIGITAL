package com.vk.api.sdk;

import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: VKApiIllegalCredentialsListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/vk/api/sdk/VKApiIllegalCredentialsListener;", "", "onInvalidCredentials", "", "methodName", "", "userBanInfo", "Lorg/json/JSONObject;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public interface VKApiIllegalCredentialsListener {
    void onInvalidCredentials(String str, JSONObject jSONObject);
}
