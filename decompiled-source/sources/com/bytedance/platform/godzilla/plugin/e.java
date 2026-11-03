package com.bytedance.platform.godzilla.plugin;

import com.bytedance.platform.godzilla.common.k;
import com.bytedance.platform.godzilla.common.m;
/* compiled from: UncaughtExceptionPlugin.java */
/* loaded from: classes2.dex */
public abstract class e extends a implements m {
    @Override // com.bytedance.platform.godzilla.common.m
    public abstract boolean consumeUncaughtException(Thread thread, Throwable th);

    public abstract boolean shouldEnableOpt();

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        super.start();
        if (shouldEnableOpt()) {
            com.bytedance.platform.godzilla.b.INSTANCE.addUncaughtExceptionConsumer(this);
            k.e(getName(), "start called.");
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public d startType() {
        return d.REGISTER_EXCEPTION;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
        super.stop();
        if (shouldEnableOpt()) {
            com.bytedance.platform.godzilla.b.INSTANCE.removeUncaughtExceptionConsumer(this);
            k.e(getName(), "stop called.");
        }
    }
}
