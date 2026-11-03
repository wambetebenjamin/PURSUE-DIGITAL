package com.bytedance.ttgame.library.luffy;

import com.bytedance.ttgame.library.luffy.adapter.d;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: InitConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u0001!Be\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001e¨\u0006\""}, d2 = {"Lcom/bytedance/ttgame/library/luffy/InitConfig;", "", "executor", "Ljava/util/concurrent/Executor;", "logger", "Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;", "preloads", "", "", "mainProcess", "", "mainStorage", "Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "secondaryStorage", "request", "Lcom/bytedance/ttgame/library/luffy/adapter/IRequest;", "bindTimeout", "", "(Ljava/util/concurrent/Executor;Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;Ljava/util/List;ZLcom/bytedance/ttgame/library/luffy/adapter/IStorage;Ljava/util/List;Ljava/util/List;J)V", "getBindTimeout", "()J", "getExecutor", "()Ljava/util/concurrent/Executor;", "getLogger", "()Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;", "getMainProcess", "()Z", "getMainStorage", "()Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "getPreloads", "()Ljava/util/List;", "getRequest", "getSecondaryStorage", "Builder", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f14161a;

    /* renamed from: b  reason: collision with root package name */
    private final com.bytedance.ttgame.library.luffy.adapter.b f14162b;

    /* renamed from: c  reason: collision with root package name */
    private final List<String> f14163c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f14164d;

    /* renamed from: e  reason: collision with root package name */
    private final d f14165e;

    /* renamed from: f  reason: collision with root package name */
    private final List<d> f14166f;

    /* renamed from: g  reason: collision with root package name */
    private final List<com.bytedance.ttgame.library.luffy.adapter.c> f14167g;

    /* renamed from: h  reason: collision with root package name */
    private final long f14168h;

    /* JADX WARN: Multi-variable type inference failed */
    private b(Executor executor, com.bytedance.ttgame.library.luffy.adapter.b bVar, List<String> list, boolean z, d dVar, List<? extends d> list2, List<? extends com.bytedance.ttgame.library.luffy.adapter.c> list3, long j2) {
        this.f14161a = executor;
        this.f14162b = bVar;
        this.f14163c = list;
        this.f14164d = z;
        this.f14165e = dVar;
        this.f14166f = list2;
        this.f14167g = list3;
        this.f14168h = j2;
    }

    public /* synthetic */ b(Executor executor, com.bytedance.ttgame.library.luffy.adapter.b bVar, List list, boolean z, d dVar, List list2, List list3, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(executor, bVar, list, z, dVar, list2, list3, j2);
    }

    public final Executor getExecutor() {
        return this.f14161a;
    }

    public final com.bytedance.ttgame.library.luffy.adapter.b getLogger() {
        return this.f14162b;
    }

    public final List<String> getPreloads() {
        return this.f14163c;
    }

    public final boolean getMainProcess() {
        return this.f14164d;
    }

    public final d getMainStorage() {
        return this.f14165e;
    }

    public final List<d> getSecondaryStorage() {
        return this.f14166f;
    }

    public final List<com.bytedance.ttgame.library.luffy.adapter.c> getRequest() {
        return this.f14167g;
    }

    public final long getBindTimeout() {
        return this.f14168h;
    }

    /* compiled from: InitConfig.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0014\u0010\u0010\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eJ\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/InitConfig$Builder;", "", "()V", "bindTimeout", "", "executor", "Ljava/util/concurrent/Executor;", "logger", "Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;", "mainProcess", "", "mainStorage", "Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "preloads", "", "", "request", "Lcom/bytedance/ttgame/library/luffy/adapter/IRequest;", "secondaryStorage", "timeout", "build", "Lcom/bytedance/ttgame/library/luffy/InitConfig;", "checkValid", "", "luffy_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private Executor f14169a;

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.ttgame.library.luffy.adapter.b f14170b;

        /* renamed from: c  reason: collision with root package name */
        private List<String> f14171c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f14172d;

        /* renamed from: e  reason: collision with root package name */
        private d f14173e;

        /* renamed from: f  reason: collision with root package name */
        private List<? extends d> f14174f;

        /* renamed from: g  reason: collision with root package name */
        private List<? extends com.bytedance.ttgame.library.luffy.adapter.c> f14175g;

        /* renamed from: h  reason: collision with root package name */
        private long f14176h = 5000;

        public final a executor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            this.f14169a = executor;
            return this;
        }

        public final a logger(com.bytedance.ttgame.library.luffy.adapter.b logger) {
            Intrinsics.checkNotNullParameter(logger, "logger");
            this.f14170b = logger;
            return this;
        }

        public final a preloads(List<String> preloads) {
            Intrinsics.checkNotNullParameter(preloads, "preloads");
            this.f14171c = preloads;
            return this;
        }

        public final a mainProcess(boolean z) {
            this.f14172d = z;
            return this;
        }

        public final a mainStorage(d mainStorage) {
            Intrinsics.checkNotNullParameter(mainStorage, "mainStorage");
            this.f14173e = mainStorage;
            return this;
        }

        public final a secondaryStorage(List<? extends d> secondaryStorage) {
            Intrinsics.checkNotNullParameter(secondaryStorage, "secondaryStorage");
            this.f14174f = secondaryStorage;
            return this;
        }

        public final a request(List<? extends com.bytedance.ttgame.library.luffy.adapter.c> request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.f14175g = request;
            return this;
        }

        public final a bindTimeout(long j2) {
            this.f14176h = j2;
            return this;
        }

        public final b build() {
            a();
            return new b(this.f14169a, this.f14170b, this.f14171c, this.f14172d, this.f14173e, this.f14174f, this.f14175g, this.f14176h, null);
        }

        private final void a() {
            if (this.f14173e == null) {
                throw new IllegalArgumentException("Must set a storage implementation!");
            }
        }
    }
}
