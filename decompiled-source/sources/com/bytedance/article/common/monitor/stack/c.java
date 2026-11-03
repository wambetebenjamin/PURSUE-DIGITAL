package com.bytedance.article.common.monitor.stack;

import android.text.TextUtils;
import com.bytedance.apm.internal.ApmDelegate;
import com.bytedance.apm.thread.b;
import com.bytedance.apm.util.am;
import com.bytedance.article.common.monitor.stack.f;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ExceptionMonitorManager.java */
/* loaded from: classes.dex */
public class c implements b.InterfaceC0021b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3634a = "exception_filter_network";

    /* renamed from: b  reason: collision with root package name */
    private static String f3635b = "https://i.isnssdk.com/monitor/collect/c/exception";

    /* renamed from: c  reason: collision with root package name */
    private static final long f3636c = 1200000;

    /* renamed from: d  reason: collision with root package name */
    private static final String f3637d = "application/json; charset=utf-8";

    /* renamed from: e  reason: collision with root package name */
    private static final long f3638e = 1800000;

    /* renamed from: f  reason: collision with root package name */
    private static final long f3639f = 1048576;

    /* renamed from: g  reason: collision with root package name */
    private static final int f3640g = 10240;

    /* renamed from: h  reason: collision with root package name */
    private static final int f3641h = 20;
    private static final Object n = new Object();
    private static volatile c o;
    private static volatile a q;

    /* renamed from: i  reason: collision with root package name */
    private volatile long f3642i;

    /* renamed from: j  reason: collision with root package name */
    private volatile int f3643j;
    private volatile boolean k;
    private volatile long l;
    private volatile JSONObject m;
    private final LinkedList<h> p = new LinkedList<>();
    private volatile com.bytedance.article.common.monitor.stack.a r;

    /* compiled from: ExceptionMonitorManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void writeLogToFile(String str);
    }

    @Override // com.bytedance.apm.thread.b.InterfaceC0021b
    public void onTimeEvent(long j2) {
        try {
            if (this.r != null) {
                this.r.a();
            }
            long currentTimeMillis = System.currentTimeMillis();
            if ((currentTimeMillis - this.f3642i > 1200000 && this.f3643j > 0) || this.f3643j > 20) {
                c();
            }
            if (!this.k || currentTimeMillis - this.l <= 1800000) {
                return;
            }
            this.k = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static c getInstance() {
        if (o == null) {
            synchronized (n) {
                if (o == null) {
                    o = new c();
                }
            }
        }
        return o;
    }

    public static void setUploadUrl(String str) {
        f3635b = str;
    }

    private c() {
        com.bytedance.apm.thread.b.getInstance().addTimeTask(this);
        this.r = new com.bytedance.article.common.monitor.stack.a();
    }

    public static void registerLogToFileModule(a aVar) {
        if (q == null) {
            q = aVar;
        }
    }

    boolean a(String str) {
        return ApmDelegate.getInstance().getLogTypeSwitch(str);
    }

    public void enqueue(String str, String str2, String str3, boolean z) {
        boolean z2;
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (z) {
                    JSONObject jSONObject = new JSONObject(str2);
                    jSONObject.put("log_type", "log_exception");
                    if (str3 != null) {
                        if (str3.length() > f3640g) {
                            jSONObject.put("extraMessage", str3.substring(0, f3640g));
                        } else {
                            jSONObject.put("extraMessage", str3);
                        }
                    }
                    if (q != null) {
                        q.writeLogToFile(jSONObject.toString());
                    }
                }
                if (!ApmDelegate.getInstance().isConfigReady()) {
                    if (this.r != null) {
                        this.r.a(str, str2);
                        return;
                    }
                    return;
                }
                boolean a2 = a(str);
                boolean serviceNameSwitch = ApmDelegate.getInstance().getServiceNameSwitch(str3);
                if ((a2 || serviceNameSwitch) && !this.k) {
                    synchronized (n) {
                        int size = this.p.size();
                        z2 = size >= 20;
                        this.p.add(new h(str, str2));
                        this.f3643j = size + 1;
                    }
                    if (z2) {
                        c();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void directReport(StackTraceElement stackTraceElement, String str, String str2) {
        try {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", "exception");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put(com.bytedance.crash.event.f.f6711c, className);
            jSONObject.put("method", methodName);
            jSONObject.put(com.bytedance.crash.event.f.f6713e, lineNumber);
            jSONObject.put("stack", str);
            jSONObject.put(com.bytedance.apm.perf.traffic.a.KEY_EXCEPTION_TYPE, 1);
            jSONObject.put("is_core", 1);
            jSONObject.put("message", str2);
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            jSONObject2.put("data", jSONArray);
            if (this.m == null) {
                this.m = com.bytedance.apm.c.getHeader();
            }
            jSONObject2.put("header", this.m);
            f.excutePost(1048576L, am.addParamsToURL(f3635b, com.bytedance.apm.c.getQueryParamsMap()), jSONObject2.toString().getBytes(), f.a.GZIP, "application/json; charset=utf-8", true);
        } catch (Throwable unused) {
        }
    }

    public void directReport(Throwable th, String str) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            String className = stackTrace[0].getClassName();
            String methodName = stackTrace[0].getMethodName();
            int lineNumber = stackTrace[0].getLineNumber();
            String exceptionStack = g.getExceptionStack(th);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", "exception");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put(com.bytedance.crash.event.f.f6711c, className);
            jSONObject.put("method", methodName);
            jSONObject.put(com.bytedance.crash.event.f.f6713e, lineNumber);
            jSONObject.put("stack", exceptionStack);
            jSONObject.put(com.bytedance.apm.perf.traffic.a.KEY_EXCEPTION_TYPE, 1);
            jSONObject.put("is_core", 1);
            jSONObject.put("message", str);
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            jSONObject2.put("data", jSONArray);
            if (this.m == null) {
                this.m = com.bytedance.apm.c.getHeader();
            }
            jSONObject2.put("header", this.m);
            f.excutePost(1048576L, am.addParamsToURL(f3635b, com.bytedance.apm.c.getQueryParamsMap()), jSONObject2.toString().getBytes(), f.a.GZIP, "application/json; charset=utf-8", true);
        } catch (Throwable unused) {
        }
    }

    private void c() {
        this.f3642i = System.currentTimeMillis();
        com.bytedance.apm.thread.b.getInstance().submitTask(new Runnable() { // from class: com.bytedance.article.common.monitor.stack.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LinkedList linkedList = new LinkedList();
                    synchronized (c.n) {
                        linkedList.addAll(c.this.p);
                        c.this.p.clear();
                        c.this.f3643j = 0;
                    }
                    if (linkedList.isEmpty()) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    while (!linkedList.isEmpty()) {
                        h hVar = (h) linkedList.poll();
                        if (hVar != null) {
                            jSONArray.put(new JSONObject(hVar.value));
                        }
                    }
                    jSONObject.put("data", jSONArray);
                    if (c.this.m == null) {
                        c.this.m = com.bytedance.apm.c.getHeader();
                    }
                    jSONObject.put("header", c.this.m);
                    c.this.a(c.f3635b, jSONObject.toString());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        try {
            if (ApmDelegate.getInstance().isConfigReady()) {
                f.excutePost(1048576L, am.addParamsToURL(str, com.bytedance.apm.c.getQueryParamsMap()), str2.getBytes(), f.a.GZIP, "application/json; charset=utf-8", true);
            }
        } catch (Throwable th) {
            int statusCode = th instanceof e ? th.getStatusCode() : -1;
            if (statusCode < 500 || statusCode > 600) {
                return;
            }
            this.l = System.currentTimeMillis();
            this.k = true;
        }
    }

    public boolean getExceptionFilterSwitch() {
        return ApmDelegate.getInstance().isConfigReady() && !ApmDelegate.getInstance().getLogTypeSwitch(f3634a);
    }
}
