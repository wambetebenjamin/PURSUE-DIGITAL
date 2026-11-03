package com.bytedance.ug.sdk.share.channel.facebook.impl;

import android.content.Intent;
import com.facebook.CallbackManager;
/* compiled from: FacebookShareResultImpl.java */
/* loaded from: classes8.dex */
public class d implements com.bytedance.ug.sdk.share.impl.share.api.c {
    @Override // com.bytedance.ug.sdk.share.impl.share.api.c
    public void handleShareResult(int i2, int i3, Intent intent) {
        CallbackManager callbackManager = a.getInstance().getCallbackManager();
        if (callbackManager != null) {
            callbackManager.onActivityResult(i2, i3, intent);
        }
    }
}
