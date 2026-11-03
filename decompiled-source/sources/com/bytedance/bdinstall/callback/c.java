package com.bytedance.bdinstall.callback;
/* compiled from: CallbackWrapper.java */
/* loaded from: classes.dex */
public abstract class c<Event, Listener> {

    /* renamed from: a  reason: collision with root package name */
    private final Listener f3786a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Listener listener) {
        this.f3786a = listener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Listener a() {
        return this.f3786a;
    }
}
