package com.bytedance.frameworks.baselib.network.http.cronet;
/* compiled from: TTNetThreadConfig.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: d  reason: collision with root package name */
    private static final int f7668d = 20;

    /* renamed from: a  reason: collision with root package name */
    public c f7669a;

    /* renamed from: b  reason: collision with root package name */
    public int f7670b = 20;

    /* renamed from: c  reason: collision with root package name */
    public int f7671c = 0;

    /* compiled from: TTNetThreadConfig.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(int i2);

        void b(int i2);

        void c(int i2);
    }

    /* compiled from: TTNetThreadConfig.java */
    /* loaded from: classes2.dex */
    public enum b {
        CALLED_MULIT_TIMES,
        PROCESS_NAME_NULL,
        TNC_DISABLED,
        CRASH_HAPPENED,
        OPT_EFFECTIVE,
        FALLBACK_FIX,
        HAS_EXCEPTION
    }

    /* compiled from: TTNetThreadConfig.java */
    /* loaded from: classes2.dex */
    public enum c {
        INIT_THREAD,
        NETWORK_THREAD,
        FILE_THREAD,
        THREAD_POOL_SERVICE_THREAD,
        WORKER_THREAD
    }
}
