package com.bytedance.ttgame.library.boot_manager;

import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class BootConfig {
    private Context context;
    private boolean downgrade;
    private Executor idleExecutor;
    private Executor mainExecutor;
    private SdkConfig sdkConfig;
    private Executor serialExecutor;
    private IBootTaskMonitor taskMonitor = null;

    public Executor getMainExecutor() {
        return this.mainExecutor;
    }

    public Executor getSerialExecutor() {
        return this.serialExecutor;
    }

    public Executor getIdleExecutor() {
        return this.idleExecutor;
    }

    public boolean isDowngrade() {
        return this.downgrade;
    }

    public IBootTaskMonitor getTaskMonitor() {
        return this.taskMonitor;
    }

    public Context getContext() {
        return this.context;
    }

    public SdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    public BootConfig(Executor executor, Executor executor2, Executor executor3, boolean z, Context context, SdkConfig sdkConfig) {
        this.mainExecutor = null;
        this.serialExecutor = null;
        this.idleExecutor = null;
        this.downgrade = false;
        this.context = null;
        this.sdkConfig = null;
        this.mainExecutor = executor;
        this.serialExecutor = executor2;
        this.idleExecutor = executor3;
        this.downgrade = z;
        this.context = context;
        this.sdkConfig = sdkConfig;
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private Executor mainExecutor = null;
        private Executor serialExecutor = null;
        private Executor idleExecutor = null;
        private boolean downgrade = false;
        private Context context = null;
        private SdkConfig sdkConfig = null;
        private IBootTaskMonitor taskMonitor = null;

        public Builder mainExecutor(Executor executor) {
            this.mainExecutor = executor;
            return this;
        }

        public Builder serialExecutor(Executor executor) {
            this.serialExecutor = executor;
            return this;
        }

        public Builder idleExecutor(Executor executor) {
            this.idleExecutor = executor;
            return this;
        }

        public Builder downgrade(boolean z) {
            this.downgrade = z;
            return this;
        }

        public Builder context(Context context) {
            this.context = context.getApplicationContext();
            return this;
        }

        public Builder sdkConfig(SdkConfig sdkConfig) {
            this.sdkConfig = sdkConfig;
            return this;
        }

        public Builder setTaskMonitorImpl(IBootTaskMonitor iBootTaskMonitor) {
            this.taskMonitor = iBootTaskMonitor;
            return this;
        }

        public BootConfig build() {
            if (this.mainExecutor == null) {
                throw new IllegalArgumentException("mainExecutor不能为空");
            }
            if (this.serialExecutor == null) {
                throw new IllegalArgumentException("serialExecutor不能为空");
            }
            if (this.idleExecutor == null) {
                throw new IllegalArgumentException("idleExecutor不能为空");
            }
            if (this.context == null) {
                throw new IllegalArgumentException("context不能为空");
            }
            if (this.sdkConfig == null) {
                throw new IllegalArgumentException("sdkConfig不能为空");
            }
            BootConfig bootConfig = new BootConfig(this.mainExecutor, this.serialExecutor, this.idleExecutor, this.downgrade, this.context, this.sdkConfig);
            IBootTaskMonitor iBootTaskMonitor = this.taskMonitor;
            if (iBootTaskMonitor != null) {
                bootConfig.taskMonitor = iBootTaskMonitor;
            }
            return bootConfig;
        }
    }
}
