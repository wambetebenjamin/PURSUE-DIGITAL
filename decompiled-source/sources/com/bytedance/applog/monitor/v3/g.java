package com.bytedance.applog.monitor.v3;

import android.content.Context;
import com.bytedance.bdinstall.ai;
import java.util.concurrent.ExecutorService;
/* compiled from: MonitorV3Config.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private final Context f2946a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2947b;

    /* renamed from: c  reason: collision with root package name */
    private long f2948c;

    /* renamed from: d  reason: collision with root package name */
    private final ai f2949d;

    /* renamed from: e  reason: collision with root package name */
    private final ExecutorService f2950e;

    /* renamed from: f  reason: collision with root package name */
    private final String f2951f;

    /* renamed from: g  reason: collision with root package name */
    private final c f2952g;

    public String toString() {
        return "MonitorV3Config{context=" + this.f2946a + ", appLogConfig=" + this.f2947b + ", eventReportInterval=" + this.f2948c + ", networkClient=" + this.f2949d + ", executor=" + this.f2950e + ", reportUrl='" + this.f2951f + "'}";
    }

    private g(a aVar) {
        this.f2948c = 60000L;
        this.f2946a = aVar.f2953a;
        this.f2947b = aVar.f2954b;
        this.f2948c = aVar.f2955c;
        this.f2949d = aVar.f2956d;
        this.f2951f = aVar.f2957e;
        this.f2950e = aVar.f2958f;
        this.f2952g = aVar.f2959g;
    }

    public Context a() {
        return this.f2946a;
    }

    public f b() {
        return this.f2947b;
    }

    public long c() {
        return this.f2948c;
    }

    public ai d() {
        return this.f2949d;
    }

    public String e() {
        return this.f2951f;
    }

    public ExecutorService f() {
        return this.f2950e;
    }

    public c g() {
        return this.f2952g;
    }

    /* compiled from: MonitorV3Config.java */
    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private Context f2953a;

        /* renamed from: b  reason: collision with root package name */
        private f f2954b;

        /* renamed from: c  reason: collision with root package name */
        private long f2955c;

        /* renamed from: d  reason: collision with root package name */
        private ai f2956d;

        /* renamed from: e  reason: collision with root package name */
        private String f2957e;

        /* renamed from: f  reason: collision with root package name */
        private ExecutorService f2958f;

        /* renamed from: g  reason: collision with root package name */
        private c f2959g;

        public a a(Context context) {
            this.f2953a = context;
            return this;
        }

        public a a(f fVar) {
            this.f2954b = fVar;
            return this;
        }

        public a a(long j2) {
            this.f2955c = j2;
            return this;
        }

        public a a(ai aiVar) {
            this.f2956d = aiVar;
            return this;
        }

        public a a(String str) {
            this.f2957e = str;
            return this;
        }

        public a a(ExecutorService executorService) {
            this.f2958f = executorService;
            return this;
        }

        public a a(c cVar) {
            this.f2959g = cVar;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }
}
