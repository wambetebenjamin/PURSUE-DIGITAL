package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.api.call.b;
import org.json.JSONObject;
/* compiled from: AbsApiCall.java */
/* loaded from: classes2.dex */
public abstract class a<T extends b> extends com.bytedance.sdk.account.impl.a {

    /* renamed from: b  reason: collision with root package name */
    private boolean f12245b;

    public abstract void onResponse(T t);

    public JSONObject onSendEvent(String str) {
        return null;
    }

    public boolean isCancel() {
        return this.f12245b;
    }

    public void cancel() {
        this.f12245b = true;
        a();
    }

    public final void dispatchOnResponse(T t) {
        if (isCancel()) {
            return;
        }
        onResponse(t);
    }
}
