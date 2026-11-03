package com.bytedance.apm.block.trace;

import org.json.JSONObject;
/* compiled from: JankTracer.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: b  reason: collision with root package name */
    private static volatile e f773b = null;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f774c = false;

    /* renamed from: d  reason: collision with root package name */
    private static final int f775d = 0;

    /* renamed from: a  reason: collision with root package name */
    private volatile b f776a;

    /* compiled from: JankTracer.java */
    /* loaded from: classes.dex */
    public interface b {
        void collectEnd();

        void doFrame(long j2, long[] jArr);

        boolean needFilter();
    }

    public void reportJank(JSONObject jSONObject) {
    }

    private e() {
    }

    public static e getInstance() {
        if (f773b == null) {
            synchronized (e.class) {
                if (f773b == null) {
                    f773b = new e();
                }
            }
        }
        return f773b;
    }

    public void setJankCalculator(b bVar) {
        if (bVar != null) {
            this.f776a = bVar;
        }
    }

    public void doFrame(final long[] jArr, final long j2) {
        if (this.f776a == null) {
            return;
        }
        if (!this.f776a.needFilter()) {
            g.getMonitor().u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.e.1
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f776a.doFrame(j2, jArr);
                }
            });
            f774c = true;
        } else if (f774c) {
            g.getMonitor().u.a(new Runnable() { // from class: com.bytedance.apm.block.trace.e.2
                @Override // java.lang.Runnable
                public void run() {
                    e.this.f776a.collectEnd();
                }
            });
            f774c = false;
        }
    }

    /* compiled from: JankTracer.java */
    /* loaded from: classes.dex */
    static class a implements b {
        @Override // com.bytedance.apm.block.trace.e.b
        public void collectEnd() {
        }

        @Override // com.bytedance.apm.block.trace.e.b
        public void doFrame(long j2, long[] jArr) {
        }

        @Override // com.bytedance.apm.block.trace.e.b
        public boolean needFilter() {
            return false;
        }

        a() {
        }
    }
}
