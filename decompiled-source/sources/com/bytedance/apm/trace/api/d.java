package com.bytedance.apm.trace.api;

import com.bytedance.apm.trace.model.cross.NativeSpan;
import com.bytedance.apm.trace.model.cross.TracingCrossManager;
import java.util.ArrayList;
import java.util.Iterator;
/* compiled from: TracingContext.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private final String f1489a;

    /* renamed from: b  reason: collision with root package name */
    private final long f1490b;

    /* renamed from: c  reason: collision with root package name */
    private final e f1491c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.apm.trace.model.a f1492d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f1493e;

    /* renamed from: f  reason: collision with root package name */
    private long f1494f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1495g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f1496h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f1497i;

    public d(String str, e eVar) {
        this(str, eVar, false);
    }

    public d(String str, e eVar, boolean z) {
        this.f1489a = str;
        this.f1490b = com.bytedance.tracing.internal.utils.a.uniqueId();
        this.f1491c = eVar;
        this.f1493e = z;
    }

    public synchronized void start() {
        if (this.f1495g) {
            return;
        }
        a(false);
    }

    public synchronized void startWithCross() {
        if (this.f1495g) {
            return;
        }
        this.f1497i = true;
        a(true);
    }

    private void a(boolean z) {
        this.f1492d = a(this.f1491c);
        long currentTimeMillis = System.currentTimeMillis();
        this.f1494f = currentTimeMillis;
        this.f1492d.startTrace(currentTimeMillis);
        this.f1495g = true;
        if (z) {
            TracingCrossManager.registerCross(this.f1490b, this);
        }
    }

    public synchronized void end() {
        if (!this.f1496h && this.f1495g) {
            final long currentTimeMillis = System.currentTimeMillis();
            com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.trace.api.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.f1492d.endTrace(currentTimeMillis);
                    d.this.f1492d = null;
                }
            });
            this.f1496h = true;
            if (this.f1497i) {
                TracingCrossManager.unRegisterCross(this.f1490b);
            }
        }
    }

    public synchronized void end(long j2) {
        if (!this.f1496h && this.f1495g) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f1494f > j2) {
                cancel();
            } else {
                com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.trace.api.d.2
                    @Override // java.lang.Runnable
                    public void run() {
                        d.this.f1492d.endTrace(currentTimeMillis);
                        d.this.f1492d = null;
                    }
                });
            }
            this.f1496h = true;
            if (this.f1497i) {
                TracingCrossManager.unRegisterCross(this.f1490b);
            }
        }
    }

    public synchronized void cancel() {
        if (!this.f1496h && this.f1495g) {
            this.f1492d.cancelTrace();
            this.f1492d = null;
            this.f1496h = true;
            if (this.f1497i) {
                TracingCrossManager.unRegisterCross(this.f1490b);
            }
        }
    }

    public synchronized a createSpan(String str) {
        if (!this.f1496h && this.f1495g) {
            return this.f1492d.createSpan(str);
        }
        com.bytedance.apm.launch.b.getInstance().logE(str + " is start after Trace finish or before Trace begin, it will be ignore!!!!");
        return null;
    }

    public synchronized c createWindowSpan(String str) {
        if (!this.f1496h && this.f1495g) {
            return this.f1492d.createWindowSpan(str);
        }
        com.bytedance.apm.launch.b.getInstance().logE(str + " is start after Trace finish or before Trace begin, it will be ignore!!!!");
        return null;
    }

    public synchronized void addTracingTag(String str, String str2) {
        if (!this.f1496h && this.f1495g) {
            this.f1492d.addTag(str, str2);
        }
    }

    public synchronized void assembleAsChild(String str, ArrayList<NativeSpan> arrayList) {
        if (!this.f1496h && this.f1495g) {
            boolean z = true;
            Iterator<NativeSpan> it = arrayList.iterator();
            while (it.hasNext()) {
                NativeSpan next = it.next();
                if (next != null) {
                    c parseToSpan = next.parseToSpan(this.f1492d);
                    if (z) {
                        parseToSpan.setParentId(Long.parseLong(str));
                    }
                    parseToSpan.endWindowSpan(next.getStartTime(), next.getFinishTime());
                    z = false;
                }
            }
        }
    }

    public synchronized void assembleAsReference(String str, ArrayList<NativeSpan> arrayList) {
        if (!this.f1496h && this.f1495g) {
            boolean z = true;
            Iterator<NativeSpan> it = arrayList.iterator();
            while (it.hasNext()) {
                NativeSpan next = it.next();
                if (next != null) {
                    c parseToSpan = next.parseToSpan(this.f1492d);
                    if (z) {
                        parseToSpan.setReferenceId(Long.parseLong(str));
                    }
                    parseToSpan.endWindowSpan(next.getStartTime(), next.getFinishTime());
                    z = false;
                }
            }
        }
    }

    public String getService() {
        return this.f1489a;
    }

    public long getTraceId() {
        return this.f1490b;
    }

    public boolean isForceTrace() {
        return this.f1493e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TracingContext.java */
    /* renamed from: com.bytedance.apm.trace.api.d$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1502a;

        static {
            int[] iArr = new int[e.values().length];
            f1502a = iArr;
            try {
                iArr[e.BATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private com.bytedance.apm.trace.model.a a(e eVar) {
        if (AnonymousClass3.f1502a[eVar.ordinal()] != 1) {
            return null;
        }
        return new com.bytedance.apm.trace.model.b(this);
    }
}
