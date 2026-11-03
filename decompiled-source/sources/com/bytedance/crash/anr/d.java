package com.bytedance.crash.anr;

import android.app.ActivityManager;
import android.text.TextUtils;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.q;
import com.bytedance.crash.util.y;
import com.bytedance.librarian.c;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: AnrInfoParser.java */
/* loaded from: classes2.dex */
public class d {
    private static final String A = "Input dispatching timed out";
    private static final String B = "executing service";
    private static final String C = "Broadcast of Intent";
    private static final String D = "keyDispatchingTimedOut";
    private static final String E = "not then call Service.startForeground";

    /* renamed from: a  reason: collision with root package name */
    public static final String f6199a = "anr_info";

    /* renamed from: e  reason: collision with root package name */
    private static final String f6200e = "anr_info.txt";

    /* renamed from: f  reason: collision with root package name */
    private static final String f6201f = "|------------- processErrorStateInfo--------------|\n";

    /* renamed from: g  reason: collision with root package name */
    private static final String f6202g = "-----------------------end----------------------------";

    /* renamed from: h  reason: collision with root package name */
    private static final String f6203h = "condition: ";

    /* renamed from: i  reason: collision with root package name */
    private static final String f6204i = "processName: ";

    /* renamed from: j  reason: collision with root package name */
    private static final String f6205j = "pid: ";
    private static final String k = "uid: ";
    private static final String l = "tag: ";
    private static final String m = "Load: ";
    private static final String n = "shortMsg : ";
    private static final String o = "longMsg : ";
    private static final String z = "unknown";

    /* renamed from: b  reason: collision with root package name */
    a f6206b;

    /* renamed from: c  reason: collision with root package name */
    a f6207c;

    /* renamed from: d  reason: collision with root package name */
    a f6208d;
    private final String p;
    private final boolean q;
    private String r;
    private int s;
    private String t;
    private String u;
    private String v;
    private int w;
    private int x;
    private final float[] y;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a() {
        return f6200e;
    }

    public d(String str) {
        this.y = new float[]{-1.0f, -1.0f, -1.0f};
        this.p = str;
        this.q = false;
        a(str);
    }

    public d(File file) {
        this.y = new float[]{-1.0f, -1.0f, -1.0f};
        String d2 = k.d(new File(file, f6200e));
        if (d2 != null && !d2.isEmpty()) {
            if (d2.equals(e.f6228a)) {
                this.u = com.bytedance.crash.constants.c.H;
            } else if (d2.equals(e.f6229b)) {
                this.u = "Not report anrInfo";
            } else {
                int a2 = a(d2);
                if (a2 < 0) {
                    y.e("NPTH", "Anr Info Parse Error:" + a2);
                }
                this.u = g();
            }
            this.v = h();
            k.c(new File(file, f6200e), a(d2, this.r));
        }
        this.p = d2;
        this.q = !new File(file, com.bytedance.crash.anr.c.f6178a).exists();
    }

    public static String a(String str, String str2) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str3 : str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
                int indexOf = str3.indexOf(47);
                int indexOf2 = str3.indexOf(": ");
                if (indexOf != -1 && indexOf2 != -1 && indexOf < indexOf2) {
                    String substring = str3.substring(indexOf, indexOf2);
                    if (!substring.contains(str2) && (substring.contains("com.") || substring.contains(".google"))) {
                        str3 = str3.replace(substring, "/*.*.*");
                    }
                }
                sb.append(str3).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            return sb.toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(ActivityManager.ProcessErrorStateInfo processErrorStateInfo) {
        return f6201f + f6203h + processErrorStateInfo.condition + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + f6204i + processErrorStateInfo.processName + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + f6205j + processErrorStateInfo.pid + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + k + processErrorStateInfo.uid + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + l + processErrorStateInfo.tag + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + n + processErrorStateInfo.shortMsg + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + o + processErrorStateInfo.longMsg + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + f6202g;
    }

    private static boolean b(String str, String str2) {
        if (str == null || str2 == null) {
            return str == null && str2 == null;
        }
        return str.equals(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(ActivityManager.ProcessErrorStateInfo processErrorStateInfo, ActivityManager.ProcessErrorStateInfo processErrorStateInfo2) {
        return processErrorStateInfo.condition == processErrorStateInfo2.condition && processErrorStateInfo.pid == processErrorStateInfo2.pid && processErrorStateInfo.uid == processErrorStateInfo2.uid && b(processErrorStateInfo.processName, processErrorStateInfo2.processName) && b(processErrorStateInfo.tag, processErrorStateInfo2.tag) && b(processErrorStateInfo.shortMsg, processErrorStateInfo2.shortMsg) && b(processErrorStateInfo.longMsg, processErrorStateInfo2.longMsg);
    }

    String b() {
        return a(this.p, this.r);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return !TextUtils.isEmpty(this.p);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        long e2 = e();
        long optLong = jSONObject.optLong("crash_time");
        if (e2 > 0 && e2 < optLong) {
            q.a(jSONObject, "crash_time", Long.valueOf(e2));
        }
        q.a(jSONObject, f6199a, (Object) b());
        e(jSONObject);
        c(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        return this.s;
    }

    long e() {
        a aVar = this.f6208d;
        if (aVar != null) {
            return aVar.a();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AnrInfoParser.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        String f6219a;

        /* renamed from: b  reason: collision with root package name */
        int f6220b = 0;

        b(String str) {
            this.f6219a = str;
        }

        void a(int i2) {
            this.f6220b = i2;
        }

        int a() {
            return this.f6220b;
        }

        void b(int i2) {
            this.f6220b += i2;
        }

        String a(String str, String str2) {
            int i2 = this.f6220b;
            if (str != null) {
                int indexOf = this.f6219a.indexOf(str, i2);
                if (indexOf < 0) {
                    return null;
                }
                this.f6220b = indexOf + str.length();
            }
            if (str2 != null) {
                int indexOf2 = this.f6219a.indexOf(str2, this.f6220b);
                if (indexOf2 < 0) {
                    this.f6220b = i2;
                    return null;
                }
                String substring = this.f6219a.substring(this.f6220b, indexOf2);
                this.f6220b = indexOf2 + str2.length();
                return substring;
            }
            String substring2 = this.f6219a.substring(this.f6220b);
            this.f6220b = this.f6219a.length() - 1;
            return substring2;
        }
    }

    int a(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && str.startsWith(f6201f) && str.endsWith(f6202g)) {
            try {
                b bVar = new b(str);
                bVar.a(52);
                if (bVar.a(f6203h, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) == null) {
                    return -2;
                }
                String a2 = bVar.a(f6204i, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                if (a2 == null) {
                    return -3;
                }
                this.r = a2;
                String a3 = bVar.a(f6205j, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                if (a3 == null) {
                    return -4;
                }
                try {
                    this.s = Integer.parseInt(a3);
                    if (bVar.a(k, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) == null) {
                        return -6;
                    }
                    String a4 = bVar.a(l, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    if (a4 == null) {
                        return -7;
                    }
                    this.v = a4;
                    int indexOf2 = str.indexOf(n, bVar.a());
                    if (indexOf2 < 0) {
                        return -8;
                    }
                    int i2 = indexOf2 + 11;
                    int indexOf3 = str.indexOf(10, i2);
                    if (indexOf3 < 0) {
                        return -9;
                    }
                    int indexOf4 = str.indexOf(o, indexOf3);
                    if (indexOf4 < 0) {
                        return -10;
                    }
                    this.w = indexOf4 + 10;
                    this.t = str.substring(i2, indexOf4 - 1);
                    int length = str.length() - 54;
                    this.x = length;
                    if (length != 0) {
                        int i3 = this.w;
                        while (true) {
                            if (i3 >= this.x || (indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, i3)) < 0) {
                                break;
                            }
                            int[] a5 = a(str, i3);
                            if (a5 != null) {
                                a aVar = new a(str, a5);
                                if (aVar.k) {
                                    if (this.f6206b == null) {
                                        this.f6206b = aVar;
                                        aVar.d();
                                    } else if (this.f6207c == null) {
                                        this.f6207c = aVar;
                                        aVar.d();
                                        break;
                                    }
                                }
                                i3 = a5[3] + 1;
                            } else {
                                i3 = indexOf + 1;
                            }
                        }
                        a aVar2 = this.f6206b;
                        this.f6208d = aVar2;
                        if (this.f6207c != null && aVar2.f6218j && Math.abs(this.f6208d.f6214f + this.f6208d.f6215g) > Math.abs(this.f6207c.f6214f + this.f6207c.f6215g)) {
                            this.f6208d = this.f6207c;
                        }
                        a aVar3 = this.f6208d;
                        if (aVar3 != null) {
                            aVar3.h();
                        }
                    }
                    String a6 = bVar.a(m, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    if (a6 != null) {
                        String[] split = a6.split(c.a.SEPARATOR);
                        if (this.y.length == split.length) {
                            for (int i4 = 0; i4 < split.length; i4++) {
                                this.y[i4] = c(split[i4].trim()).floatValue();
                            }
                        }
                    }
                    return 0;
                } catch (Throwable unused) {
                    return -5;
                }
            } catch (Throwable th) {
                com.bytedance.crash.diagnose.b.b("NPTH_ANR_ERROR", th);
                return -11;
            }
        }
        return -1;
    }

    private static Float c(String str) {
        try {
            return Float.valueOf(str);
        } catch (Exception unused) {
            return Float.valueOf(-1.0f);
        }
    }

    private static String a(float f2) {
        return f2 < 0.0f ? "invalid" : f2 > 100.0f ? "> 100" : new String[]{"0~10", "10~20", "20~30", "30~40", "40~50", "50~60", "60~70", "70~80", "80~90", "90~100"}[((int) f2) / 10];
    }

    private static String b(float f2) {
        if (f2 < 0.0f) {
            return "invalid";
        }
        float[] fArr = {2000.0f, 3000.0f, 4000.0f, 5000.0f, 10000.0f, 20000.0f, 30000.0f, 40000.0f, 50000.0f, 60000.0f, 70000.0f, 80000.0f, 90000.0f, 100000.0f, 150000.0f, 200000.0f, 300000.0f, 400000.0f, 500000.0f};
        String[] strArr = {"0~2k", "2k~3k", "3k~4k", "4k~5k", "5k~1w", "1w~2w", "2w~3w", "3w~4w", "4w~5w", "5w~6w", "7w~8w", "8w~9w", "9w~10w", "10w~15w", "15w~20w", "20w~30w", "30w~40w", "40w~50w", "> 50w"};
        for (int i2 = 0; i2 < 19; i2++) {
            if (f2 <= fArr[i2]) {
                return strArr[i2];
            }
        }
        return strArr[18];
    }

    c a(a aVar) {
        if (aVar.r.size() != 0) {
            return aVar.r.get(0);
        }
        return null;
    }

    c b(a aVar) {
        Iterator<c> it = aVar.r.iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (next.f6227g == this.s) {
                return next;
            }
        }
        return null;
    }

    public void b(JSONObject jSONObject) {
        try {
            jSONObject.put("anr_tag", this.v);
            jSONObject.put(com.bytedance.crash.constants.c.C, this.u);
            jSONObject.put("is_current_process", this.q);
            jSONObject.put("input_timeout_type", f());
            if (!TextUtils.isEmpty(this.p) && !this.p.equals(e.f6228a)) {
                jSONObject.put("has_anrinfo", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else {
                jSONObject.put("has_anrinfo", "false");
            }
            a aVar = this.f6206b;
            jSONObject.put("anr_has_ago", String.valueOf(aVar != null && aVar.f6218j));
            jSONObject.put("success_end_anrinfo", String.valueOf(this.f6206b != null));
            jSONObject.put("anr_cpu_load_1", a(this.y[0]));
            jSONObject.put("anr_cpu_load_5", a(this.y[1]));
            jSONObject.put("anr_cpu_load_15", a(this.y[2]));
            a aVar2 = this.f6206b;
            if (aVar2 != null) {
                jSONObject.put("anr_cpu_total", a((float) aVar2.l));
                jSONObject.put("anr_cpu_user", a((float) this.f6206b.m));
                jSONObject.put("anr_cpu_kernel", a((float) this.f6206b.n));
                jSONObject.put("anr_cpu_iowait", a((float) this.f6206b.o));
                c b2 = b(this.f6206b);
                if (b2 != null) {
                    jSONObject.put("anr_app_cpu_total", a((float) b2.f6222b));
                    jSONObject.put("anr_app_cpu_user", a((float) b2.f6223c));
                    jSONObject.put("anr_app_cpu_kernel", a((float) b2.f6224d));
                    jSONObject.put("anr_faults_minor", b((float) b2.f6225e));
                    jSONObject.put("anr_faults_major", b((float) b2.f6226f));
                }
                c a2 = a(this.f6206b);
                if (a2 != null) {
                    jSONObject.put("anr_top_cpu_process", a2.f6221a);
                }
            } else if (this.f6207c != null) {
                jSONObject.put("anr_cpu_total", a((float) aVar2.l));
                jSONObject.put("anr_cpu_user", a((float) this.f6206b.m));
                jSONObject.put("anr_cpu_kernel", a((float) this.f6206b.n));
                jSONObject.put("anr_cpu_iowait", a((float) this.f6206b.o));
                c b3 = b(this.f6207c);
                if (b3 != null) {
                    jSONObject.put("anr_app_cpu_total", a((float) b3.f6222b));
                    jSONObject.put("anr_app_cpu_user", a((float) b3.f6223c));
                    jSONObject.put("anr_app_cpu_kernel", a((float) b3.f6224d));
                    jSONObject.put("anr_faults_minor", b((float) b3.f6225e));
                    jSONObject.put("anr_faults_major", b((float) b3.f6226f));
                }
                c a3 = a(this.f6206b);
                if (a3 != null) {
                    jSONObject.put("anr_top_cpu_process", a3.f6221a);
                }
            }
        } catch (Throwable th) {
            y.i(th);
        }
    }

    public void c(JSONObject jSONObject) {
        JSONObject a2 = q.a(jSONObject, "features_str");
        JSONObject a3 = q.a(jSONObject, "features_num");
        a aVar = this.f6208d;
        if (aVar != null) {
            aVar.a(a3, a2);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("app_memory_info");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("max_memory");
            long optLong2 = optJSONObject.optLong("free_memory");
            long optLong3 = optJSONObject.optLong("total_memory");
            if (optLong > 0) {
                q.a(a3, "java_heap_usage", Long.valueOf(((optLong3 - optLong2) * 100) / optLong));
            }
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("memory_info");
        if (optJSONObject2 != null) {
            q.a(a3, "total_pss", Long.valueOf(optJSONObject2.optLong("totalPss") / 1048576));
        }
    }

    private void e(JSONObject jSONObject) {
        JSONObject a2 = q.a(jSONObject, "anr_atribute");
        JSONObject jSONObject2 = new JSONObject();
        try {
            d(jSONObject2);
            a aVar = this.f6206b;
            if (aVar != null) {
                aVar.a(jSONObject2, true);
            }
            a aVar2 = this.f6207c;
            if (aVar2 != null) {
                aVar2.a(jSONObject2, false);
            }
            int i2 = -1;
            i2 = -1;
            a aVar3 = this.f6208d;
            if (aVar3 != null) {
                aVar3.a(jSONObject2);
                c e2 = this.f6208d.e();
                if (e2 != null) {
                    jSONObject2.put("top_cpu_process_name", e2.f6221a);
                    i2 = this.r.equals(e2.f6221a);
                }
            }
            jSONObject2.put("top_cpu_process_is_anrapp", i2);
            jSONObject2.put("process_name", this.r);
            a2.put("cpu", jSONObject2);
        } catch (Throwable th) {
            y.i(th);
        }
    }

    private String g() {
        if (TextUtils.isEmpty(this.t) || "null".equals(this.t)) {
            return "unknown";
        }
        if (this.t.startsWith("ANR ")) {
            if (this.t.startsWith(A, 4)) {
                return d(this.t);
            }
            return this.t.startsWith(B, 4) ? B : this.t.startsWith(C, 4) ? C : this.t.startsWith(D, 4) ? D : this.t.contains(E) ? E : b(this.t.substring(4));
        }
        return this.t;
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.replaceAll("\\d+", "xxx");
        } catch (Throwable unused) {
            return str;
        }
    }

    private static String d(String str) {
        return (str.contains("Waiting to send non-key event") && str.contains("finished processing certain input events that were delivered")) ? "Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over xxms ago.  Wait queue length: xx.  Wait queue head age: xxms.)" : str.contains("Application does not have a focused window") ? "Input dispatching timed out (Application does not have a focused window)" : str.contains("Waiting because no window has focus but there is a focused") ? "Input dispatching timed out (Waiting because no window has focus but there is a focused application that may eventually add a window when it finishes starting up.)" : str.contains("window's input channel is full.") ? "Input dispatching timed out (Waiting because the touched window input channel is full.Outbound queue length: XXX.Wait queue length: XX.)" : A;
    }

    private String h() {
        String trim;
        if (!TextUtils.isEmpty(this.v) && !"null".equals(this.v)) {
            return this.v;
        }
        if (TextUtils.isEmpty(this.t) || "null".equals(this.t)) {
            return "unknown";
        }
        if (this.t.contains(B)) {
            String str = this.t;
            trim = str.substring(str.indexOf("service ") + 8).trim();
            String[] split = trim.split(c.a.SEPARATOR);
            if (split.length > 1) {
                trim = split[split.length - 1];
            }
        } else if (!this.t.contains(C)) {
            return "unknown";
        } else {
            String str2 = this.t;
            trim = str2.substring(str2.indexOf("{") + 1, this.t.indexOf("}")).trim();
            String[] split2 = trim.split(c.a.SEPARATOR);
            if (split2.length > 1) {
                trim = split2[split2.length - 1];
            }
        }
        return trim;
    }

    String f() {
        String str = this.u;
        if (str == null || !str.equals(A)) {
            return "invalid";
        }
        String lowerCase = this.t.toLowerCase();
        return (lowerCase.contains("no window has focus but there is a focused application") || lowerCase.contains("does not have a focused window")) ? "no focused window" : lowerCase.contains("waiting to send non-key event because the touched window has not finished") ? "non-key event not finished" : lowerCase.contains("waiting to send key event because the focused window has not finished") ? "key event not finished" : lowerCase.contains("window is paused") ? "window is paused" : lowerCase.contains("window’s input channel is not registered") ? "input channel not registered" : lowerCase.contains("window’s input connection is") ? "window being removed" : lowerCase.contains("window’s input channel is full") ? "input channel is full" : lowerCase.contains("is not responding. waited") ? "activity is not responding" : "invalid";
    }

    void d(JSONObject jSONObject) {
        String str;
        int indexOf;
        int i2 = this.w;
        while (i2 < this.x && (indexOf = this.p.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, i2)) >= 0) {
            if (this.p.startsWith(m, i2)) {
                str = this.p.substring(i2 + 6, indexOf);
                break;
            }
            i2 = indexOf + 1;
        }
        str = null;
        if (str != null) {
            String[] split = str.split(" / ");
            if (split.length == 3) {
                try {
                    double parseDouble = Double.parseDouble(split[0].trim());
                    double parseDouble2 = Double.parseDouble(split[1].trim());
                    double parseDouble3 = Double.parseDouble(split[2].trim());
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(parseDouble);
                    jSONArray.put(parseDouble2);
                    jSONArray.put(parseDouble3);
                    jSONObject.put("load", jSONArray);
                } catch (Throwable unused) {
                }
            }
        }
    }

    private static int[] a(String str, int i2) {
        int[] iArr = new int[4];
        while (true) {
            int indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, i2);
            if (indexOf < 0) {
                return null;
            }
            if (str.startsWith("CPU usage from", i2)) {
                iArr[0] = i2;
                iArr[1] = indexOf;
                int indexOf2 = str.indexOf("% TOTAL: ", indexOf + 1);
                if (indexOf2 > 0) {
                    int lastIndexOf = str.lastIndexOf(10, indexOf2) + 1;
                    int indexOf3 = str.indexOf(10, indexOf2);
                    if (lastIndexOf > 0 && indexOf3 > 0) {
                        iArr[2] = lastIndexOf;
                        iArr[3] = indexOf3;
                        return iArr;
                    }
                }
                return null;
            }
            i2 = indexOf + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AnrInfoParser.java */
    /* loaded from: classes2.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        String f6209a;

        /* renamed from: b  reason: collision with root package name */
        int f6210b;

        /* renamed from: c  reason: collision with root package name */
        int f6211c;

        /* renamed from: d  reason: collision with root package name */
        int f6212d;

        /* renamed from: e  reason: collision with root package name */
        int f6213e;

        /* renamed from: f  reason: collision with root package name */
        long f6214f;

        /* renamed from: g  reason: collision with root package name */
        long f6215g;

        /* renamed from: h  reason: collision with root package name */
        String f6216h;

        /* renamed from: i  reason: collision with root package name */
        String f6217i;

        /* renamed from: j  reason: collision with root package name */
        boolean f6218j;
        boolean k;
        double l;
        double m;
        double n;
        double o;
        double p;
        double q;
        double s;
        double t;
        double u;
        double v;
        double w;
        ArrayList<c> r = new ArrayList<>();
        ArrayList<Double> x = new ArrayList<>();

        a(String str, int[] iArr) {
            this.f6209a = str;
            boolean z = false;
            this.f6210b = iArr[0];
            this.f6211c = iArr[1];
            this.f6212d = iArr[2];
            this.f6213e = iArr[3];
            if (b() && c()) {
                z = true;
            }
            this.k = z;
        }

        long a() {
            long j2 = 0;
            try {
                long a2 = com.bytedance.crash.util.e.a(this.f6216h);
                if (this.f6218j) {
                    j2 = a2 + this.f6214f;
                } else {
                    j2 = a2 - this.f6214f;
                }
            } catch (Throwable unused) {
            }
            return j2;
        }

        boolean b() {
            String a2;
            String a3;
            String a4;
            String a5;
            b bVar = new b(this.f6209a.substring(this.f6210b, this.f6211c));
            String a6 = bVar.a("CPU usage from ", "ms");
            if (a6 == null || (a2 = bVar.a(" to ", "ms")) == null || (a3 = bVar.a(" ", " ")) == null || (a4 = bVar.a("(", " t")) == null || (a5 = bVar.a("o ", ")")) == null) {
                return false;
            }
            try {
                this.f6214f = Long.parseLong(a6);
                this.f6215g = Long.parseLong(a2);
                this.f6218j = "ago".equals(a3);
                this.f6216h = a4;
                this.f6217i = a5;
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }

        boolean c() {
            b bVar = new b(this.f6209a.substring(this.f6212d, this.f6213e));
            String a2 = bVar.a(null, "% TOTAL:");
            if (a2 == null) {
                return false;
            }
            String a3 = bVar.a(" ", "% user");
            if (a3 != null) {
                bVar.b(2);
            }
            String a4 = bVar.a(" ", "% kernel");
            if (a4 != null) {
                bVar.b(2);
            }
            String a5 = bVar.a(" ", "% iowait");
            if (a5 != null) {
                bVar.b(2);
            }
            String a6 = bVar.a(" ", "% irq");
            if (a6 != null) {
                bVar.b(2);
            }
            String a7 = bVar.a(" ", "% softirq");
            if (a7 == null) {
                bVar.b(2);
            }
            try {
                this.l = Double.parseDouble(a2);
                double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                this.m = a3 == null ? 0.0d : Double.parseDouble(a3);
                this.n = a4 == null ? 0.0d : Double.parseDouble(a4);
                this.o = a5 == null ? 0.0d : Double.parseDouble(a5);
                this.p = a6 == null ? 0.0d : Double.parseDouble(a6);
                if (a7 != null) {
                    d2 = Double.parseDouble(a7);
                }
                this.q = d2;
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }

        void d() {
            int indexOf;
            int i2 = this.f6211c + 1;
            while (i2 < this.f6212d && (indexOf = this.f6209a.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, i2)) >= 0) {
                if (indexOf - i2 <= 4 || this.f6209a.charAt(i2 + 2) != ' ') {
                    c cVar = null;
                    try {
                        cVar = a(this.f6209a, i2, indexOf);
                    } catch (Throwable unused) {
                    }
                    if (cVar == null) {
                        continue;
                    } else if (cVar.f6222b < 1.0d) {
                        return;
                    } else {
                        this.r.add(cVar);
                    }
                }
                i2 = indexOf + 1;
            }
        }

        c e() {
            if (this.r.size() != 0) {
                return this.r.get(0);
            }
            return null;
        }

        c f() {
            Iterator<c> it = this.r.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next.f6227g == d.this.s) {
                    return next;
                }
            }
            return null;
        }

        void a(JSONObject jSONObject) {
            try {
                jSONObject.put("kswapd", this.s);
                jSONObject.put("mmcqd", this.t);
                jSONObject.put("kwork", this.u);
                jSONObject.put("system_server", this.v);
                jSONObject.put("lmkd", this.w);
                if (this.x.isEmpty()) {
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<Double> it = this.x.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("dex2oat", jSONArray);
            } catch (Throwable unused) {
            }
        }

        void a(JSONObject jSONObject, JSONObject jSONObject2) {
            q.a(jSONObject, "cpu_total", Double.valueOf(this.l));
            q.a(jSONObject, "cpu_user", Double.valueOf(this.m));
            q.a(jSONObject, "cpu_kernel", Double.valueOf(this.n));
            q.a(jSONObject, "cpu_iowait", Double.valueOf(this.o));
            q.a(jSONObject, "cpu_irq", Double.valueOf(this.p));
            q.a(jSONObject, "cpu_softirq", Double.valueOf(this.q));
            c e2 = e();
            if (e2 != null) {
                q.a(jSONObject2, "top_cpu_process_name", (Object) e2.f6221a);
            }
            c f2 = f();
            if (f2 != null) {
                q.a(jSONObject, "cpu_app_total", Double.valueOf(f2.f6222b));
                q.a(jSONObject, "cpu_app_user", Double.valueOf(f2.f6223c));
                q.a(jSONObject, "cpu_app_kernel", Double.valueOf(f2.f6224d));
                q.a(jSONObject, "minor_rate", Long.valueOf(f2.f6225e));
                q.a(jSONObject, "major_rate", Long.valueOf(f2.f6226f));
            }
            try {
                jSONObject.put("cpu_kswapd", this.s);
                jSONObject.put("cpu_mmcqd", this.t);
                jSONObject.put("cpu_kwork", this.u);
                jSONObject.put("cpu_system_server", this.v);
                jSONObject.put("cpu_lmkd", this.w);
                float f3 = 0.0f;
                Iterator<Double> it = this.x.iterator();
                while (it.hasNext()) {
                    f3 = (float) (f3 + it.next().doubleValue());
                }
                jSONObject.put("cpu_dex2oat", f3);
            } catch (Throwable unused) {
            }
        }

        void a(JSONObject jSONObject, boolean z) {
            try {
                jSONObject.put(z ? "user" : "user2", this.m);
                jSONObject.put(z ? "sys" : "sys2", this.n);
                jSONObject.put(z ? "iowait" : "iowait2", this.o);
                jSONObject.put(z ? "irq" : "irq2", this.p);
                jSONObject.put(z ? "softirq" : "softirq2", this.q);
                c f2 = f();
                if (f2 != null) {
                    jSONObject.put(z ? "minor" : "minor2", f2.f6225e);
                    jSONObject.put(z ? "major" : "major2", f2.f6226f);
                }
                jSONObject.put(z ? "before_cpu_list" : "after_cpu_list", g());
            } catch (Throwable unused) {
            }
        }

        JSONObject g() {
            JSONObject jSONObject = new JSONObject();
            Iterator<c> it = this.r.iterator();
            while (it.hasNext()) {
                c next = it.next();
                q.a(jSONObject, String.valueOf(next.f6227g), q.a(q.a(new JSONObject(), "process_name", (Object) next.f6221a), "cpu", Double.valueOf(next.f6222b)));
            }
            return jSONObject;
        }

        c a(String str, int i2, int i3) {
            int i4;
            int indexOf;
            int i5 = i2 + 2;
            int indexOf2 = str.indexOf(37, i5);
            c cVar = null;
            if (indexOf2 >= 0 && indexOf2 <= i3) {
                double parseDouble = Double.parseDouble(str.substring(i5, indexOf2));
                int i6 = indexOf2 + 2;
                int indexOf3 = str.indexOf(47, i6);
                if (indexOf3 >= 0 && indexOf3 <= i3) {
                    int parseInt = Integer.parseInt(str.substring(i6, indexOf3));
                    int i7 = indexOf3 + 1;
                    int indexOf4 = str.indexOf(": ", i7);
                    if (indexOf4 >= 0 && indexOf4 <= i3) {
                        cVar = new c(parseInt, str.substring(i7, indexOf4), parseDouble);
                        if (parseInt == d.this.s && (indexOf = str.indexOf("% user + ", (i4 = indexOf4 + 2))) >= 0 && indexOf <= i3) {
                            cVar.f6223c = Double.parseDouble(str.substring(i4, indexOf));
                            int i8 = indexOf + 9;
                            int indexOf5 = str.indexOf("% kernel", i8);
                            if (indexOf5 >= 0 && indexOf5 <= i3) {
                                cVar.f6224d = Double.parseDouble(str.substring(i8, indexOf5));
                                int indexOf6 = str.indexOf("faults: ", indexOf5);
                                if (indexOf6 >= 0 && indexOf6 <= i3) {
                                    int i9 = indexOf6 + 8;
                                    int indexOf7 = str.indexOf(" minor", i9);
                                    if (indexOf7 > 0 && indexOf7 < i3) {
                                        cVar.f6225e = Long.parseLong(str.substring(i9, indexOf7));
                                        i9 = indexOf7 + 7;
                                    }
                                    int indexOf8 = str.indexOf(" major", i9);
                                    if (indexOf8 > 0 && indexOf8 < i3) {
                                        cVar.f6226f = Long.parseLong(str.substring(i9, indexOf8));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return cVar;
        }

        void h() {
            Iterator<c> it = this.r.iterator();
            while (it.hasNext()) {
                c next = it.next();
                String str = next.f6221a;
                double d2 = next.f6222b;
                if (str.contains("kworker")) {
                    this.u += d2;
                } else if (str.contains("kswapd")) {
                    this.s += d2;
                } else if (str.contains("mmcqd")) {
                    this.t += d2;
                } else if (str.contains("system_server")) {
                    this.v += d2;
                } else if (str.contains("lmkd")) {
                    this.w += d2;
                } else if (str.contains("dex2oat")) {
                    this.x.add(Double.valueOf(d2));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AnrInfoParser.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        String f6221a;

        /* renamed from: b  reason: collision with root package name */
        double f6222b;

        /* renamed from: c  reason: collision with root package name */
        double f6223c;

        /* renamed from: d  reason: collision with root package name */
        double f6224d;

        /* renamed from: e  reason: collision with root package name */
        long f6225e;

        /* renamed from: f  reason: collision with root package name */
        long f6226f;

        /* renamed from: g  reason: collision with root package name */
        int f6227g;

        c(int i2, String str, double d2) {
            this.f6227g = i2;
            this.f6221a = str;
            this.f6222b = d2;
        }
    }
}
