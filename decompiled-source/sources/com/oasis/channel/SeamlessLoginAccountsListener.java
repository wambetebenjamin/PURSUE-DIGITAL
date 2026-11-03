package com.oasis.channel;
/* loaded from: classes9.dex */
public interface SeamlessLoginAccountsListener {
    void onGetSeamlessLoginAccounts(boolean z, String str);

    void onGoogleAuthStatus(boolean z);

    void onGoogleSignCallback(boolean z, String str);

    void onSeamlessLogin(boolean z, String str);
}
