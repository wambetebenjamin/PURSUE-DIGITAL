package com.oasis.channel;
/* loaded from: classes9.dex */
public interface AccountListener {
    void onLoginCancel();

    void onLoginFail(String str);

    void onLoginSuccess(String str);

    void onLogout(String str);
}
