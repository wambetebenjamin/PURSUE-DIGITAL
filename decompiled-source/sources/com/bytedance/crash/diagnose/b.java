package com.bytedance.crash.diagnose;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import com.bytedance.crash.CrashType;
import com.bytedance.crash.Global;
import com.bytedance.crash.config.g;
import com.bytedance.crash.entity.Header;
import com.bytedance.crash.event.e;
import com.bytedance.crash.monitor.f;
import com.bytedance.crash.monitor.h;
import com.bytedance.crash.upload.d;
import com.bytedance.crash.util.q;
import java.io.File;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: NpthMonitor.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6486a = "2010";

    /* renamed from: b  reason: collision with root package name */
    private static b f6487b;

    /* renamed from: c  reason: collision with root package name */
    private final f f6488c;

    static /* synthetic */ b c() {
        return e();
    }

    private b(Context context) {
        f a2 = h.a(context, f6486a, 4020188L, 4020188L, com.bytedance.crash.config.h.f6371a);
        this.f6488c = a2;
        a2.c("release");
        com.bytedance.crash.monitor.a a3 = h.a();
        if (a3 != null) {
            a2.d(a3.b("0"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public f d() {
        return this.f6488c;
    }

    public static void a() {
        if (f6487b == null) {
            synchronized (b.class) {
                if (f6487b == null) {
                    f6487b = new b(Global.c());
                }
            }
        }
    }

    private static b e() {
        return f6487b;
    }

    /* compiled from: NpthMonitor.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final JSONObject f6491a;

        /* renamed from: b  reason: collision with root package name */
        private JSONObject f6492b;

        /* renamed from: c  reason: collision with root package name */
        private final String f6493c;

        public a(String str) {
            this.f6492b = new JSONObject();
            this.f6493c = str;
            this.f6491a = new JSONObject();
        }

        public a(String str, JSONObject jSONObject) {
            this.f6492b = new JSONObject();
            this.f6493c = str;
            if (jSONObject != null) {
                this.f6491a = jSONObject;
            } else {
                this.f6491a = new JSONObject();
            }
        }

        public a a(Object... objArr) {
            q.addKVs(this.f6491a, objArr);
            return this;
        }

        public a b(Object... objArr) {
            q.addKVs(this.f6492b, objArr);
            return this;
        }

        public a a(JSONObject jSONObject) {
            this.f6492b = jSONObject;
            return this;
        }

        public void a() {
            b c2 = b.c();
            if (c2 != null) {
                b();
                c2.d().a(this.f6493c, 1, this.f6491a, this.f6492b);
            }
        }

        private void b() {
            com.bytedance.crash.monitor.a a2 = h.a();
            if (a2 != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("aid", a2.f());
                hashMap.put("device_id", a2.b("0"));
                hashMap.put("update_version_code", String.valueOf(a2.k()));
                hashMap.put("version_code", String.valueOf(a2.j()));
                hashMap.put("app_version", String.valueOf(a2.m()));
                hashMap.put("channel", a2.g());
                hashMap.put("npth_version", com.bytedance.crash.config.h.f6371a);
                q.a(this.f6491a, hashMap);
            }
        }
    }

    public static void a(String str, Throwable th) {
        boolean z = b() < 25165824;
        b e2 = e();
        if (e2 != null) {
            e2.d().a(str + "_" + z, th);
        }
    }

    public static void b(String str, Throwable th) {
        b e2 = e();
        if (e2 != null) {
            e2.d().a(str, th);
        }
    }

    public static void c(String str, Throwable th) {
        b e2 = e();
        if (e2 == null) {
            return;
        }
        f d2 = e2.d();
        try {
            com.bytedance.crash.event.f fVar = new com.bytedance.crash.event.f(th, null, 0, str, true, null, Thread.currentThread().getName(), e.f6696a, "core_exception_monitor", "exception");
            final JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(fVar.a(d2));
            jSONObject.put("data", jSONArray);
            JSONObject b2 = Header.a(d2, System.currentTimeMillis(), CrashType.ENSURE, Process.myPid()).b();
            jSONObject.put("header", b2);
            final String a2 = com.bytedance.crash.upload.e.a(g.f(), b2);
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.diagnose.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (com.bytedance.crash.upload.g.a(a2, jSONObject)) {
                                d.a().c();
                            }
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Throwable th) {
        b("NPTH_CATCH_NEW", th);
    }

    public static long b() {
        try {
            return a(Environment.getDataDirectory());
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getFreeBytes();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
