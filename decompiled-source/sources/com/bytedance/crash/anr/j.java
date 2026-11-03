package com.bytedance.crash.anr;

import android.text.TextUtils;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.crash.dumper.l;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.q;
import com.bytedance.sdk.account.monitor.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AnrTraceParser.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6278a = "trace.txt";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6279b = "trace_by_child.txt";

    /* renamed from: c  reason: collision with root package name */
    private static final String f6280c = "Unwind failed for thread";

    /* renamed from: d  reason: collision with root package name */
    private static final String f6281d = "anr_dump_async";

    /* renamed from: e  reason: collision with root package name */
    private static final int f6282e = 1024;
    private static final String m = "backtrace: \n";

    /* renamed from: f  reason: collision with root package name */
    private final ArrayList<c> f6283f;

    /* renamed from: g  reason: collision with root package name */
    private final int f6284g;

    /* renamed from: h  reason: collision with root package name */
    private final c f6285h;

    /* renamed from: i  reason: collision with root package name */
    private final File f6286i;

    /* renamed from: j  reason: collision with root package name */
    private final b f6287j;
    private final a k;
    private final File l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File a(File file) {
        return new File(file, f6278a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(File file, int i2) {
        ArrayList<c> arrayList = new ArrayList<>();
        this.l = new File(file, f6279b);
        a aVar = new a();
        b bVar = new b();
        c a2 = a(file, i2, arrayList, bVar, aVar);
        this.f6284g = arrayList.size() + (a2 != null ? 1 : 0);
        this.f6285h = a2;
        this.f6283f = arrayList;
        this.f6287j = bVar;
        this.k = aVar;
        this.f6286i = file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.f6285h != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        c cVar = this.f6285h;
        if (cVar != null) {
            return cVar.e();
        }
        return null;
    }

    private static HashMap<String, JSONArray> b(File file) {
        BufferedReader bufferedReader = null;
        JSONArray jSONArray = null;
        if (file == null || !file.exists()) {
            return null;
        }
        HashMap<String, JSONArray> hashMap = new HashMap<>();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!TextUtils.isEmpty(readLine)) {
                        if (readLine.contains("sysTid")) {
                            String substring = readLine.substring(readLine.indexOf(61) + 1);
                            jSONArray = new JSONArray();
                            jSONArray.put(m);
                            hashMap.put(substring, jSONArray);
                        } else if (jSONArray != null) {
                            String trim = readLine.trim();
                            if (trim.startsWith("#")) {
                                jSONArray.put(trim);
                            }
                        }
                    }
                } catch (Throwable unused) {
                    bufferedReader = bufferedReader2;
                    k.a(bufferedReader);
                    return hashMap;
                }
            }
            k.a(bufferedReader2);
        } catch (Throwable unused2) {
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        q.a(jSONObject, l.f6533a, i());
        q.a(jSONObject, "main_thread_cpu_tick", Long.valueOf(d() + c()));
        com.bytedance.crash.dumper.c.b(jSONObject, a(this.f6286i));
        try {
            if (jSONObject.optJSONObject("app_memory_info") != null || this.f6287j.f6291a <= 0) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("app_memory_info", jSONObject2);
            jSONObject2.put("max_memory", this.f6287j.f6291a);
            jSONObject2.put("free_memory", this.f6287j.f6292b);
            jSONObject2.put("total_memory", this.f6287j.f6293c);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        int i2;
        boolean z;
        if (!this.f6283f.isEmpty()) {
            q.a(jSONObject2, "anr_replace_mainstack", (Object) "OnlyJavaStack");
        }
        c cVar = this.f6285h;
        boolean z2 = false;
        if (cVar != null) {
            z = cVar.r;
            i2 = this.f6285h.m;
        } else {
            i2 = -10;
            z = false;
        }
        long[] jArr = {0, 0, 0};
        Iterator<c> it = this.f6283f.iterator();
        String str = "unknown";
        int i3 = 0;
        String str2 = "unknown";
        String str3 = str2;
        while (it.hasNext()) {
            c next = it.next();
            if ("RenderThread".equals(next.f6299c)) {
                q.a(jSONObject2, "anr_renderthread_nice", Integer.valueOf(next.m));
            } else if (next.m < i2 && next.m <= -16) {
                i3++;
            }
            z |= next.r;
            if (next.f6305i > jArr[0]) {
                jArr[0] = next.f6305i;
                str = next.f6299c;
            }
            if (next.f6306j > jArr[1]) {
                jArr[1] = next.f6306j;
                str2 = next.f6299c;
            }
            if (next.f6305i + next.f6306j > jArr[2]) {
                jArr[2] = next.f6305i + next.f6306j;
                str3 = next.f6299c;
            }
        }
        q.a(jSONObject2, "anr_trace_has_mainstack", Boolean.valueOf(a()));
        q.a(jSONObject2, "anr_trace_cost_time", (Object) this.k.f6290c);
        q.a(jSONObject2, "anr_trace_crash", Boolean.valueOf(this.k.f6289b));
        if (this.k.f6288a && this.f6285h != null && !this.f6283f.isEmpty()) {
            z2 = true;
        }
        q.a(jSONObject2, "anr_trace_done", Boolean.valueOf(z2));
        q.a(jSONObject2, "max_utm_thread", (Object) str);
        q.a(jSONObject2, "max_stm_thread", (Object) str2);
        q.a(jSONObject2, "max_utm_stm_thread", (Object) str3);
        q.a(jSONObject2, "anr_backup_native_stack", Boolean.valueOf(z));
        q.a(jSONObject2, "anr_subthread_count_nice<=-16", Integer.valueOf(i3));
        q.a(jSONObject2, "npth_is_wait_for_gc", Boolean.valueOf(h()));
        com.bytedance.crash.entity.a.a(jSONObject2, jSONObject);
        File file = this.l;
        if (file == null || !file.exists()) {
            q.a(jSONObject2, f6281d, (Object) "false");
            return;
        }
        if (this.l.length() > 1024) {
            q.a(jSONObject2, f6281d, (Object) "success");
        } else {
            q.a(jSONObject2, f6281d, (Object) a.InterfaceC0193a.RESULT_FAIL);
        }
        this.l.delete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long c() {
        c cVar = this.f6285h;
        if (cVar != null) {
            return cVar.a();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long d() {
        c cVar = this.f6285h;
        if (cVar != null) {
            return cVar.b();
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        c cVar = this.f6285h;
        return cVar != null && cVar.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        c cVar = this.f6285h;
        return cVar != null && cVar.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        c cVar = this.f6285h;
        return cVar != null && "WaitingPerformingGc".equals(cVar.f6301e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        c cVar = this.f6285h;
        if (cVar == null || !"WaitingForGcToComplete".equals(cVar.f6301e)) {
            Iterator<c> it = this.f6283f.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                c next = it.next();
                if (next.q) {
                    if ("WaitingForGcToComplete".equals(next.f6301e)) {
                        i2++;
                    }
                    i3++;
                }
            }
            return i2 >= 5 || i2 * 4 > i3;
        }
        return true;
    }

    private JSONObject i() {
        JSONArray jSONArray = new JSONArray();
        Iterator<c> it = this.f6283f.iterator();
        while (it.hasNext()) {
            c next = it.next();
            JSONArray jSONArray2 = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            for (int i2 = 1; i2 < next.s.size(); i2++) {
                try {
                    jSONArray2.put(next.s.get(i2));
                } catch (JSONException unused) {
                }
            }
            jSONObject.put("thread_name", next.s.get(0));
            jSONObject.put("thread_stack", jSONArray2);
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("thread_stacks", jSONArray);
            jSONObject2.put("thread_all_count", this.f6284g);
        } catch (JSONException unused2) {
        }
        return jSONObject2;
    }

    public static c a(File file, int i2, ArrayList<c> arrayList, b bVar, a aVar) {
        File a2 = a(file);
        c cVar = null;
        if (a2.exists() && !a2.isDirectory()) {
            HashMap<String, JSONArray> b2 = b(new File(file, f6279b));
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(a2)));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (!readLine.isEmpty() && (aVar == null || !aVar.a(readLine))) {
                            if (bVar == null || !bVar.a(readLine)) {
                                if (readLine.charAt(0) == '\"' && readLine.contains(" prio=") && readLine.contains(" tid=")) {
                                    ArrayList arrayList2 = new ArrayList();
                                    arrayList2.add(readLine);
                                    while (true) {
                                        String readLine2 = bufferedReader.readLine();
                                        if (readLine2 == null || !readLine2.startsWith("  ")) {
                                            break;
                                        }
                                        arrayList2.add(readLine2);
                                    }
                                    c cVar2 = new c(arrayList2, b2);
                                    if (cVar2.f()) {
                                        if (cVar2.l == i2) {
                                            cVar = cVar2;
                                        } else {
                                            arrayList.add(cVar2);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                k.a(bufferedReader);
            } catch (FileNotFoundException unused2) {
            }
        }
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AnrTraceParser.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        JSONArray f6297a = null;

        /* renamed from: b  reason: collision with root package name */
        HashMap<String, JSONArray> f6298b;

        /* renamed from: c  reason: collision with root package name */
        private String f6299c;

        /* renamed from: d  reason: collision with root package name */
        private String f6300d;

        /* renamed from: e  reason: collision with root package name */
        private String f6301e;

        /* renamed from: f  reason: collision with root package name */
        private long f6302f;

        /* renamed from: g  reason: collision with root package name */
        private long f6303g;

        /* renamed from: h  reason: collision with root package name */
        private long f6304h;

        /* renamed from: i  reason: collision with root package name */
        private long f6305i;

        /* renamed from: j  reason: collision with root package name */
        private long f6306j;
        private int k;
        private int l;
        private int m;
        private int n;
        private int o;
        private int p;
        private boolean q;
        private boolean r;
        private final ArrayList<String> s;

        c(ArrayList<String> arrayList, HashMap<String, JSONArray> hashMap) {
            this.f6298b = null;
            this.s = arrayList;
            this.f6298b = hashMap;
        }

        long a() {
            return this.f6305i;
        }

        long b() {
            return this.f6306j;
        }

        boolean c() {
            return this.f6301e.equals("TimedWaiting") || this.f6301e.equals("Waiting") || this.f6301e.equals("Blocked");
        }

        boolean d() {
            if (this.f6301e.equals("Native")) {
                for (int i2 = this.p; i2 < this.s.size(); i2++) {
                    String str = this.s.get(i2);
                    if (str.startsWith("  at")) {
                        return str.contains("BinderProxy.transactNative");
                    }
                    if (str.startsWith("  native: #") && (str.contains("__futex_wait_ex") || str.startsWith("talkWithDriver"))) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String e() {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = this.s.iterator();
            while (it.hasNext()) {
                sb.append(it.next()).append('\n');
            }
            return sb.toString();
        }

        private boolean a(String str) {
            Pattern compile = Pattern.compile("^\"(.*)\"(?: daemon)? prio=-?\\d+ (.*)$");
            Pattern compile2 = Pattern.compile("tid=(\\d+) ([a-zA-Z]+)$");
            Matcher matcher = compile.matcher(str);
            if (matcher.find() && matcher.groupCount() == 2) {
                this.f6299c = matcher.group(1);
                String group = matcher.group(2);
                Matcher matcher2 = compile2.matcher(group);
                if (matcher2.find() && matcher2.groupCount() == 2) {
                    this.k = Integer.parseInt(matcher2.group(1));
                    this.f6301e = matcher2.group(2);
                    this.q = true;
                } else if ("(not attached)".equals(group)) {
                    this.q = false;
                }
                return true;
            }
            return false;
        }

        private boolean b(String str) {
            return str.startsWith("  | group=");
        }

        private boolean c(String str) {
            Matcher matcher = Pattern.compile("^  \\| sysTid=(\\d+) nice=(-?)(\\d+) .*$").matcher(str);
            if (matcher.find() && matcher.groupCount() == 3) {
                this.l = Integer.parseInt(matcher.group(1));
                this.m = Integer.parseInt(matcher.group(3));
                if ("-".equals(matcher.group(2))) {
                    this.m *= -1;
                }
                HashMap<String, JSONArray> hashMap = this.f6298b;
                if (hashMap != null) {
                    this.f6297a = hashMap.get(matcher.group(1));
                }
                return true;
            }
            return false;
        }

        private boolean d(String str) {
            Matcher matcher = Pattern.compile("^  \\| state=([A-Z?]) schedstat=\\((.*)\\) utm=(\\d+) stm=(\\d+) core=(\\d+) HZ=\\d+$").matcher(str);
            if (matcher.find() && matcher.groupCount() == 5) {
                this.f6300d = matcher.group(1);
                this.f6305i = Long.parseLong(matcher.group(3));
                this.f6306j = Long.parseLong(matcher.group(4));
                this.n = Integer.parseInt(matcher.group(5));
                String group = matcher.group(2);
                if (group != null) {
                    String[] split = group.trim().split(" ");
                    if (split.length == 3) {
                        this.f6302f = Long.parseLong(split[0]);
                        this.f6303g = Long.parseLong(split[1]);
                        this.f6304h = Long.parseLong(split[2]);
                    }
                }
                return true;
            }
            return false;
        }

        private boolean e(String str) {
            return str.startsWith("  | stack=");
        }

        private boolean f(String str) {
            return str.startsWith("  | held mutexes=");
        }

        private int a(ArrayList<String> arrayList, int i2) {
            if (i2 >= arrayList.size()) {
                return 0;
            }
            String str = arrayList.get(i2);
            if (this.f6297a == null || !str.contains(j.f6280c)) {
                return 0;
            }
            this.r = true;
            try {
                this.s.remove(i2);
                int i3 = 0;
                while (i3 < this.f6297a.length()) {
                    this.s.add(i2, this.f6297a.getString(i3));
                    i3++;
                    i2++;
                }
                return this.f6297a.length();
            } catch (Throwable unused) {
                return 0;
            }
        }

        private int g(String str) {
            int indexOf;
            if (!str.startsWith("  - waiting to lock") || (indexOf = str.indexOf("held by thread ")) <= 0) {
                return -1;
            }
            return Integer.parseInt(str.substring(indexOf + 15));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean f() {
            int i2;
            if (this.s.size() > 5 && a(this.s.get(0))) {
                if (this.q) {
                    i2 = 2;
                    if (!b(this.s.get(1))) {
                        return false;
                    }
                } else {
                    i2 = 1;
                }
                int i3 = i2 + 1;
                if (c(this.s.get(i2))) {
                    int i4 = i3 + 1;
                    if (d(this.s.get(i3))) {
                        if (this.q) {
                            int i5 = i4 + 1;
                            if (!e(this.s.get(i4))) {
                                return false;
                            }
                            i4 = i5;
                        }
                        if (this.q && f(this.s.get(i4))) {
                            i4++;
                        }
                        this.p = i4 + a(this.s, i4);
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    /* compiled from: AnrTraceParser.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public long f6291a;

        /* renamed from: b  reason: collision with root package name */
        public long f6292b;

        /* renamed from: c  reason: collision with root package name */
        public long f6293c;

        /* renamed from: d  reason: collision with root package name */
        private String f6294d = "Free memory ";

        /* renamed from: e  reason: collision with root package name */
        private String f6295e = "Total memory ";

        /* renamed from: f  reason: collision with root package name */
        private String f6296f = "Max memory ";

        public boolean a(String str) {
            if (str.startsWith(this.f6294d)) {
                long a2 = a(this.f6294d.length(), str);
                if (a2 > 0) {
                    this.f6292b = a2;
                }
                return true;
            } else if (str.startsWith(this.f6295e)) {
                this.f6293c = a(this.f6295e.length(), str);
                return true;
            } else if (str.startsWith(this.f6296f)) {
                this.f6291a = a(this.f6296f.length(), str);
                return true;
            } else {
                return false;
            }
        }

        private long a(int i2, String str) {
            char charAt;
            int i3 = 0;
            while (i2 < str.length() && (charAt = str.charAt(i2)) >= '0' && charAt <= '9') {
                i3 = (i3 * 10) + (charAt - '0');
                i2++;
            }
            return i3 * 1024 * 1024;
        }
    }

    /* compiled from: AnrTraceParser.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6288a = false;

        /* renamed from: b  reason: collision with root package name */
        public boolean f6289b = false;

        /* renamed from: c  reason: collision with root package name */
        public String f6290c = null;

        public boolean a(String str) {
            if (str.startsWith("Dump cost time:")) {
                this.f6288a = true;
                int indexOf = str.indexOf(58);
                if (indexOf != -1) {
                    this.f6290c = b(str.substring(indexOf + 1).trim());
                }
                return true;
            } else if (str.startsWith("Dump crash")) {
                this.f6289b = true;
                return true;
            } else {
                return false;
            }
        }

        private static String b(String str) {
            try {
                long parseLong = Long.parseLong(str);
                return parseLong <= 50 ? "0~50ms" : parseLong <= 100 ? "50~100ms" : parseLong <= 150 ? "100~150ms" : parseLong <= 200 ? "150~200ms" : parseLong <= 300 ? "200~300ms" : parseLong <= 400 ? "300~400ms" : parseLong <= 500 ? "400~500ms" : parseLong <= 700 ? "500~700ms" : parseLong <= 1000 ? "700~1000ms" : parseLong <= 1500 ? "1000~1500ms" : parseLong <= 2000 ? "1500~2000ms" : parseLong <= WsConstants.EXIT_DELAY_TIME ? "2000~3000ms" : parseLong <= 4000 ? "3000~4000ms" : parseLong <= 5000 ? "4000~5000ms" : parseLong <= 7000 ? "5000~7000ms" : parseLong <= 10000 ? "7000~10000ms" : "> 10000ms";
            } catch (Throwable unused) {
                return "none";
            }
        }
    }
}
