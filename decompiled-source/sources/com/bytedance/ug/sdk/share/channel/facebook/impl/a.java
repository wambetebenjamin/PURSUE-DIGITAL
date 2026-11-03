package com.bytedance.ug.sdk.share.channel.facebook.impl;

import com.facebook.CallbackManager;
/* compiled from: FacebookCallbackManager.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private CallbackManager f15671a;

    private a() {
    }

    /* compiled from: FacebookCallbackManager.java */
    /* renamed from: com.bytedance.ug.sdk.share.channel.facebook.impl.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    private static class C0250a {

        /* renamed from: a  reason: collision with root package name */
        private static a f15672a = new a();

        private C0250a() {
        }
    }

    public static a getInstance() {
        return C0250a.f15672a;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.f15671a = callbackManager;
    }

    public CallbackManager getCallbackManager() {
        return this.f15671a;
    }

    public void resetCallbackManager() {
        this.f15671a = null;
    }
}
