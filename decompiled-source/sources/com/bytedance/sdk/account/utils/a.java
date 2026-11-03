package com.bytedance.sdk.account.utils;

import com.bytedance.sdk.account.network.dispatcher.e;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AbsApiThread.java */
/* loaded from: classes2.dex */
public abstract class a extends com.bytedance.sdk.account.network.dispatcher.c {
    public static final String KEY_DATA = "data";
    public static final String KEY_MESSAGE = "message";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_EXCEPTION = "exception";
    public static final String STATUS_SUCCESS = "success";

    public static boolean isApiSuccess(JSONObject jSONObject) {
        return jSONObject != null && "success".equals(jSONObject.optString("message"));
    }

    public static boolean isApiSuccess(String str) {
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            return false;
        }
        try {
            return isApiSuccess(new JSONObject(str));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return z;
        }
        int optInt = jSONObject.optInt(str, -1);
        if (optInt >= 1) {
            return true;
        }
        if (optInt == 0) {
            return false;
        }
        return jSONObject.optBoolean(str, z);
    }

    public static boolean getHasMore(JSONObject jSONObject, boolean z) {
        return optBoolean(jSONObject, "has_more", z);
    }

    protected a(String str, e.a aVar) {
        super(str, aVar);
    }

    protected a(String str, boolean z) {
        this(str, z ? e.a.LOW : e.a.NORMAL);
    }

    protected a(String str) {
        this(str, e.a.NORMAL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a() {
        this((String) null, e.a.NORMAL);
    }

    protected a(e.a aVar) {
        this((String) null, aVar);
    }

    protected a(boolean z) {
        this((String) null, z ? e.a.LOW : e.a.NORMAL);
    }
}
