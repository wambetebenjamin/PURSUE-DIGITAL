package com.bytedance.platform.godzilla.plugin;

import android.app.Application;
/* compiled from: BasePlugin.java */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private c f11069a;

    public abstract String getName();

    public void init(Application application) {
        this.f11069a = c.INITIALIZED;
    }

    public void start() {
        this.f11069a = c.STARTED;
    }

    public void stop() {
        this.f11069a = c.STOPPED;
    }

    public void destroy() {
        this.f11069a = c.DESTROYED;
    }

    public d startType() {
        return d.IMMEDIATE;
    }

    public final c getState() {
        return this.f11069a;
    }
}
