package com.bytedance.ttgame.module.compress.api;

import android.content.SharedPreferences;
import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CompressConfig.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u000fB%\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/module/compress/api/CompressConfig;", "", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "preferences", "Landroid/content/SharedPreferences;", "monitor", "Lcom/bytedance/ttgame/module/compress/api/IMonitor;", "(Ljava/util/concurrent/ThreadPoolExecutor;Landroid/content/SharedPreferences;Lcom/bytedance/ttgame/module/compress/api/IMonitor;)V", "getExecutor", "()Ljava/util/concurrent/ThreadPoolExecutor;", "getMonitor", "()Lcom/bytedance/ttgame/module/compress/api/IMonitor;", "getPreferences", "()Landroid/content/SharedPreferences;", "Builder", "quality_api_compress_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CompressConfig {
    private final ThreadPoolExecutor executor;
    private final IMonitor monitor;
    private final SharedPreferences preferences;

    public /* synthetic */ CompressConfig(ThreadPoolExecutor threadPoolExecutor, SharedPreferences sharedPreferences, IMonitor iMonitor, DefaultConstructorMarker defaultConstructorMarker) {
        this(threadPoolExecutor, sharedPreferences, iMonitor);
    }

    private CompressConfig(ThreadPoolExecutor threadPoolExecutor, SharedPreferences sharedPreferences, IMonitor iMonitor) {
        this.executor = threadPoolExecutor;
        this.preferences = sharedPreferences;
        this.monitor = iMonitor;
    }

    public final ThreadPoolExecutor getExecutor() {
        return this.executor;
    }

    public final SharedPreferences getPreferences() {
        return this.preferences;
    }

    public final IMonitor getMonitor() {
        return this.monitor;
    }

    /* compiled from: CompressConfig.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/module/compress/api/CompressConfig$Builder;", "", "()V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "monitor", "Lcom/bytedance/ttgame/module/compress/api/IMonitor;", "preferences", "Landroid/content/SharedPreferences;", "build", "Lcom/bytedance/ttgame/module/compress/api/CompressConfig;", "quality_api_compress_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Builder {
        private ThreadPoolExecutor executor;
        private IMonitor monitor;
        private SharedPreferences preferences;

        public final Builder executor(ThreadPoolExecutor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            this.executor = executor;
            return this;
        }

        public final Builder preferences(SharedPreferences preferences) {
            Intrinsics.checkNotNullParameter(preferences, "preferences");
            this.preferences = preferences;
            return this;
        }

        public final Builder monitor(IMonitor monitor) {
            Intrinsics.checkNotNullParameter(monitor, "monitor");
            this.monitor = monitor;
            return this;
        }

        public final CompressConfig build() {
            if (this.preferences == null) {
                throw new IllegalArgumentException("Must set a executor and preferences implementation!");
            }
            return new CompressConfig(this.executor, this.preferences, this.monitor, null);
        }
    }
}
