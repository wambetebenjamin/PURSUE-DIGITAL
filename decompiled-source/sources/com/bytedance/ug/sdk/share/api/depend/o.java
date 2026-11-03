package com.bytedance.ug.sdk.share.api.depend;

import android.app.Activity;
/* compiled from: IShareTokenConfig.java */
/* loaded from: classes8.dex */
public interface o {
    boolean disableRecognizeToken(Activity activity);

    boolean filterRecognizeToken(Activity activity);

    boolean handleRecognizeToken(Activity activity, com.bytedance.ug.sdk.share.api.entity.q qVar);

    void handleTokenCheckCallback(boolean z, String str, String str2);

    boolean interceptRecognizeTokenDialog(com.bytedance.ug.sdk.share.api.ui.d dVar);

    void onRecognizeTokenDialogClickEvent(com.bytedance.ug.sdk.share.api.ui.d dVar, com.bytedance.ug.sdk.share.api.entity.f fVar, com.bytedance.ug.sdk.share.api.entity.q qVar);

    void onRecognizeTokenDialogDismissEvent(com.bytedance.ug.sdk.share.api.ui.d dVar, com.bytedance.ug.sdk.share.api.entity.q qVar);

    void onRecognizeTokenDialogShowEvent(com.bytedance.ug.sdk.share.api.ui.d dVar, com.bytedance.ug.sdk.share.api.entity.q qVar);
}
