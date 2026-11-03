package com.bytedance.sdk.account.api;

import android.content.ContentResolver;
/* compiled from: IAccountShare.java */
/* loaded from: classes2.dex */
public interface g {
    void queryShareAccount(String str, ContentResolver contentResolver, boolean z, r rVar);

    void queryShareAccount(String str, r rVar);

    void queryShareAccountData(String str, String str2, r rVar);
}
