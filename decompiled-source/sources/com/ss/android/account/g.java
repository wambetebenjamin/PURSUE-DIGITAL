package com.ss.android.account;

import com.bytedance.sdk.account.api.call.h;
/* compiled from: UserBindCallback.java */
/* loaded from: classes9.dex */
public abstract class g extends com.bytedance.sdk.account.api.call.a<h> {
    public abstract void onBindError(h hVar);

    public abstract void onBindExist(h hVar, String str, String str2, String str3);

    public abstract void onBindSuccess(h hVar);

    @Override // com.bytedance.sdk.account.api.call.a
    public void onResponse(h hVar) {
        if (hVar.success) {
            onBindSuccess(hVar);
        } else if (hVar.isBindExist()) {
            if (hVar.authToken != null) {
                onBindExist(hVar, hVar.errorTip, hVar.confirmTip, hVar.authToken);
            } else {
                onBindError(hVar);
            }
        } else {
            onBindError(hVar);
        }
    }
}
