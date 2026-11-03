package com.bytedance.crash.dumper;

import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.util.Map;
import kotlin.text.Typography;
import org.json.JSONObject;
/* compiled from: JavaStack.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6533a = "all_thread_stacks";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6534b = "main_stack.txt";

    /* renamed from: c  reason: collision with root package name */
    private static final String f6535c = "jstack.txt";

    /* renamed from: d  reason: collision with root package name */
    private static final String f6536d = "abort.txt";

    /* renamed from: e  reason: collision with root package name */
    private static final String f6537e = "[FATAL:";

    /* renamed from: f  reason: collision with root package name */
    private static final String f6538f = "Please include Java exception stack in crash report";

    /* renamed from: g  reason: collision with root package name */
    private static final String f6539g = " ttwebview:";

    public static a a(File file) {
        a aVar = new a(Looper.getMainLooper().getThread().getStackTrace());
        com.bytedance.crash.util.k.a(file, f6534b, aVar.b());
        return aVar;
    }

    public static String b(File file) {
        return com.bytedance.crash.util.k.d(new File(file, f6534b));
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("{\"mainStackFromTrace\":\"\\\"main\\\"");
    }

    public static void a(JSONObject jSONObject, File file) {
        String d2;
        File file2 = new File(file, f6535c);
        File file3 = new File(file, f6536d);
        String d3 = file2.exists() ? com.bytedance.crash.util.k.d(file2) : null;
        if (file3.exists() && (d2 = com.bytedance.crash.util.k.d(file3)) != null && d2.startsWith(f6537e) && d2.contains("Please include Java exception stack in crash report ttwebview:")) {
            StringBuilder sb = new StringBuilder();
            sb.append(d3);
            int indexOf = d2.indexOf(f6539g);
            sb.append("Caused by: ").append(f6538f).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(d2.substring(indexOf + 11));
            d3 = sb.toString();
        }
        if (TextUtils.isEmpty(d3)) {
            return;
        }
        com.bytedance.crash.util.q.a(jSONObject, "java_data", (Object) d3);
    }

    public static void c(File file) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces.isEmpty()) {
            return;
        }
        com.bytedance.crash.dumper.tools.f fVar = new com.bytedance.crash.dumper.tools.f(file.getAbsolutePath() + c.a.SEPARATOR + f6533a + ".json");
        fVar.a();
        fVar.a(f6533a);
        fVar.a();
        fVar.a("thread_all_count").a(allStackTraces.size()).e();
        fVar.a("thread_stacks");
        fVar.c();
        boolean z = true;
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            StackTraceElement[] value = entry.getValue();
            if (z) {
                z = false;
            } else {
                fVar.e();
            }
            fVar.a();
            Thread key = entry.getKey();
            fVar.a("thread_name").b(Typography.quote).c(key.getName()).b('(').b(key.getId()).b(')').b(Typography.quote);
            fVar.e();
            fVar.a("thread_stack");
            fVar.c();
            if (value.length > 0) {
                for (int i2 = 0; i2 < value.length; i2++) {
                    StackTraceElement stackTraceElement = value[i2];
                    fVar.b(Typography.quote).c(stackTraceElement.getClassName()).b('.').c(stackTraceElement.getMethodName()).b('(').c(stackTraceElement.getFileName()).b(':').b(stackTraceElement.getLineNumber()).b(')').b(Typography.quote);
                    if (i2 != value.length - 1) {
                        fVar.e();
                    }
                }
            } else {
                fVar.c("\"(Native Method)\"");
            }
            fVar.d();
            fVar.b();
        }
        fVar.d();
        fVar.b();
        fVar.b();
        fVar.g();
    }

    public static void b(JSONObject jSONObject, File file) {
        String d2 = com.bytedance.crash.util.k.d(new File(file, "all_thread_stacks.json"));
        if (d2 != null) {
            try {
                com.bytedance.crash.util.q.a(jSONObject, new JSONObject(d2));
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: JavaStack.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final StackTraceElement[] f6540a;

        /* renamed from: b  reason: collision with root package name */
        private final String f6541b;

        /* renamed from: c  reason: collision with root package name */
        private final long f6542c = System.currentTimeMillis();

        public a(StackTraceElement[] stackTraceElementArr) {
            this.f6540a = stackTraceElementArr;
            this.f6541b = a(stackTraceElementArr);
        }

        private String a(StackTraceElement[] stackTraceElementArr) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                sb.append("  at ").append(stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\n");
            }
            return sb.toString();
        }

        public StackTraceElement[] a() {
            return this.f6540a;
        }

        public String b() {
            return this.f6541b;
        }

        public long c() {
            return this.f6542c;
        }
    }
}
