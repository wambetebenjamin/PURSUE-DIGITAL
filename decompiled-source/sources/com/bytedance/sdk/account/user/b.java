package com.bytedance.sdk.account.user;

import com.bytedance.sdk.account.user.c;
import org.json.JSONObject;
/* compiled from: IBDAccountEntityFactory.java */
/* loaded from: classes2.dex */
public interface b<T extends c> {
    T parseUserInfo(JSONObject jSONObject);

    T parseUserInfo(JSONObject jSONObject, JSONObject jSONObject2);
}
