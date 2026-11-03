package com.oasis.ug;
/* loaded from: classes9.dex */
public interface UGInitListener {
    void onCheckClipboard(String str, boolean z);

    void onDealwithClipboard(String str);

    void onDealwithScheme(String str);

    void onProcessCustomParameter(String str);

    void onResultForCheckFission(boolean z, String str);

    void onResultForInvitationCode(boolean z, String str);
}
