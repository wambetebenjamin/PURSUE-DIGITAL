package com.bytedance.push;

import android.app.Application;
import android.util.Pair;
/* compiled from: DefaultKeyConfiguration.java */
/* loaded from: classes2.dex */
public final class d implements com.ss.android.pushmanager.c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Application application) {
    }

    @Override // com.ss.android.pushmanager.c
    public Pair<String, String> getMiPushConfig() {
        return null;
    }

    @Override // com.ss.android.pushmanager.c
    public Pair<String, String> getMzPushConfig() {
        return null;
    }

    @Override // com.ss.android.pushmanager.c
    public com.ss.android.push.e<String, String, String> getUmPushConfig() {
        return null;
    }

    public d(boolean z, String str) {
    }

    @Override // com.ss.android.pushmanager.c
    public Pair<String, String> getOpPushConfig() {
        return new Pair<>(Key.OPPO_PUSH_APP_KEY, Key.OPPO_PUSH_APP_SECRET);
    }
}
