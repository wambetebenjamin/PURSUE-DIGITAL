package com.bytedance.ttgame.rocketapi.privacy;
/* loaded from: classes7.dex */
public interface IPermissionReqListener {
    void onPermissionRequest(boolean z, String[] strArr, int[] iArr);

    @Deprecated
    default void onPrivacyResult(boolean z, boolean z2) {
    }
}
