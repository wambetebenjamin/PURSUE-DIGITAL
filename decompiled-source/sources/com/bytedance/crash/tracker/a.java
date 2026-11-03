package com.bytedance.crash.tracker;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.crash.Flavor;
import com.bytedance.crash.Global;
import com.bytedance.crash.dumper.tools.f;
import com.bytedance.crash.util.e;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.q;
import com.bytedance.librarian.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ActivityLifecycle.java */
/* loaded from: classes2.dex */
public class a {
    private static boolean A = false;
    private static boolean D = false;
    private static volatile a G = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f7151a = "activity_trace";

    /* renamed from: b  reason: collision with root package name */
    public static final String f7152b = "activity_track";

    /* renamed from: d  reason: collision with root package name */
    public static final int f7154d = 1;

    /* renamed from: e  reason: collision with root package name */
    public static final int f7155e = 1;

    /* renamed from: f  reason: collision with root package name */
    public static final int f7156f = 2;

    /* renamed from: g  reason: collision with root package name */
    public static final int f7157g = 3;

    /* renamed from: h  reason: collision with root package name */
    public static final int f7158h = 4;

    /* renamed from: i  reason: collision with root package name */
    public static final String f7159i = "name";

    /* renamed from: j  reason: collision with root package name */
    private static final String f7160j = "alive_activities";
    private static final String k = "finish_activities";
    private static final String l = "time";
    private static final int m = 50;
    private static final int n = 20;
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private static final int s = 4;
    private static final int t = 5;
    private static final int u = 6;
    private static boolean v = true;
    private static int w = 1;
    private static long x = -1;
    private static boolean y;
    private boolean B;
    private int C;
    private final Map<Integer, b> H = new HashMap();
    private final List<b> I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    private final c[] f7161J = new c[6];
    private final LinkedList<d> K = new LinkedList<>();
    private final ArrayList<WeakReference<Activity>> L = new ArrayList<>();
    private com.bytedance.crash.outer.a M;
    private String z;

    /* renamed from: c  reason: collision with root package name */
    public static final String f7153c = "last_resume_activity";
    private static final String[] E = {"last_create_activity", "last_start_activity", f7153c, "last_pause_activity", "last_stop_activity", "last_destroy_activity"};
    private static final String[] F = {"onCreate", com.bytedance.apm.constant.a.ON_START, "onResume", "onPause", "onStop", "onDestroy"};

    private static String b(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "onDestroy" : "onStop" : "onPause" : "onResume" : com.bytedance.apm.constant.a.ON_START : "onCreate";
    }

    static /* synthetic */ int c(a aVar) {
        int i2 = aVar.C;
        aVar.C = i2 + 1;
        return i2;
    }

    static /* synthetic */ int f(a aVar) {
        int i2 = aVar.C;
        aVar.C = i2 - 1;
        return i2;
    }

    public static void a(com.bytedance.crash.outer.a aVar) {
        o().M = aVar;
    }

    public static ArrayList<WeakReference<Activity>> a() {
        return o().L;
    }

    public static void b() {
        o();
    }

    private static a o() {
        if (G == null) {
            synchronized (a.class) {
                if (G == null) {
                    G = new a();
                }
            }
        }
        return G;
    }

    private a() {
        Application d2 = Global.d();
        if (Build.VERSION.SDK_INT >= 24 && d2 != null) {
            d2.registerActivityLifecycleCallbacks(new C0102a());
        }
        Flavor.a();
    }

    private String a(Activity activity) {
        com.bytedance.crash.outer.a aVar = this.M;
        String activityName = aVar != null ? aVar.getActivityName(activity) : null;
        return TextUtils.isEmpty(activityName) ? activity.getClass().getName() : activityName;
    }

    public static void c() {
        D = true;
    }

    public static int d() {
        int i2 = w;
        return i2 == 1 ? D ? 2 : 1 : i2;
    }

    public static long e() {
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ActivityLifecycle.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        b f7174a;

        /* renamed from: b  reason: collision with root package name */
        long f7175b;

        c() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, Activity activity) {
        long currentTimeMillis = System.currentTimeMillis();
        int hashCode = activity.hashCode();
        b bVar = this.H.get(Integer.valueOf(hashCode));
        if (bVar == null) {
            b bVar2 = new b(a(activity), i2 == 0 ? currentTimeMillis : 0L, hashCode);
            this.H.put(Integer.valueOf(hashCode), bVar2);
            bVar = bVar2;
        }
        bVar.a(i2, currentTimeMillis);
        if (com.bytedance.crash.tracker.c.a().c()) {
            a(bVar.f7169a, bVar.f7173e, b(i2), activity.hashCode());
        }
        a(bVar);
        c[] cVarArr = this.f7161J;
        if (cVarArr[i2] == null) {
            cVarArr[i2] = new c();
        }
        this.f7161J[i2].f7174a = bVar;
        this.f7161J[i2].f7175b = bVar.f7172d;
        if (i2 == 5) {
            this.H.remove(Integer.valueOf(bVar.f7170b));
            if (this.I.size() == 20) {
                this.I.remove(19);
            }
            this.I.add(bVar);
        }
    }

    /* compiled from: ActivityLifecycle.java */
    /* renamed from: com.bytedance.crash.tracker.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    class C0102a implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        C0102a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            boolean unused = a.A = bundle != null;
            a.this.a(0, activity);
            boolean unused2 = a.y = true;
            a.this.L.add(new WeakReference(activity));
            com.bytedance.crash.tracker.fragment.b.a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            a.this.a(1, activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            String activityName;
            a.this.a(2, activity);
            if (a.this.M != null) {
                activityName = a.this.M.getActivityName(activity);
            } else {
                activityName = activity.getClass().getName();
            }
            long currentTimeMillis = System.currentTimeMillis();
            a.c(a.this);
            if (a.this.B) {
                return;
            }
            a.this.B = true;
            if (a.v) {
                boolean unused = a.v = false;
                int unused2 = a.w = 1;
                long unused3 = a.x = currentTimeMillis;
            }
            if (activityName.equals(a.this.z)) {
                if (!a.y || a.A) {
                    if (a.y) {
                        return;
                    }
                    int unused4 = a.w = 3;
                    long unused5 = a.x = currentTimeMillis;
                    return;
                }
                int unused6 = a.w = 4;
                long unused7 = a.x = currentTimeMillis;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            String activityName;
            a aVar = a.this;
            if (aVar.M != null) {
                activityName = a.this.M.getActivityName(activity);
            } else {
                activityName = activity.getClass().getName();
            }
            aVar.z = activityName;
            a.f(a.this);
            if (a.this.C == 0) {
                a.this.B = false;
                boolean unused = a.y = false;
            } else if (a.this.C < 0) {
                a.this.C = 0;
                a.this.B = false;
                boolean unused2 = a.y = false;
            }
            a.this.a(3, activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            a.this.a(4, activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            a.this.a(5, activity);
            com.bytedance.crash.tracker.fragment.b.b(activity);
        }
    }

    public static long f() {
        return o().q();
    }

    public static boolean g() {
        if (o().H.isEmpty()) {
            return !p();
        }
        return !o().r();
    }

    public static boolean h() {
        if (o().H.isEmpty()) {
            return !p();
        }
        return !o().r() && System.currentTimeMillis() - f() > 2000;
    }

    private static boolean p() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ComponentName componentName;
        Context c2 = Global.c();
        if (c2 == null) {
            return false;
        }
        String packageName = c2.getPackageName();
        try {
            ActivityManager activityManager = (ActivityManager) c2.getSystemService("activity");
            if (activityManager != null && Build.VERSION.SDK_INT < 21 && (runningTasks = activityManager.getRunningTasks(1)) != null && !runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null) {
                if (packageName.equals(componentName.getPackageName())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private long q() {
        ArrayList arrayList = new ArrayList(this.H.entrySet());
        long j2 = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            b bVar = (b) ((Map.Entry) arrayList.get(i2)).getValue();
            if (bVar.f7171c < 3) {
                return 0L;
            }
            if (j2 < bVar.f7172d) {
                j2 = bVar.f7172d;
            }
        }
        return j2;
    }

    private boolean r() {
        ArrayList arrayList = new ArrayList(this.H.entrySet());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((b) ((Map.Entry) arrayList.get(i2)).getValue()).f7171c < 3) {
                return true;
            }
        }
        return false;
    }

    public static void a(File file) {
        if (h()) {
            k.b(file, "background");
        }
        if (g()) {
            k.b(file, "background_v2");
        }
        o().b(file);
        o().c(file);
        com.bytedance.crash.tracker.fragment.b.a(file);
    }

    public static void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            for (int i2 = 0; i2 < 6; i2++) {
                if (o().f7161J[i2] != null && o().f7161J[i2].f7174a != null) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("name", o().f7161J[i2].f7174a.f7169a);
                    jSONObject3.put("time", o().f7161J[i2].f7175b);
                    jSONObject2.put(E[i2], jSONObject3);
                }
            }
            ArrayList<Map.Entry> arrayList = new ArrayList(o().H.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<Integer, b>>() { // from class: com.bytedance.crash.tracker.a.1
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(Map.Entry<Integer, b> entry, Map.Entry<Integer, b> entry2) {
                    int i3 = ((entry.getValue().f7173e - entry2.getValue().f7173e) > 0L ? 1 : ((entry.getValue().f7173e - entry2.getValue().f7173e) == 0L ? 0 : -1));
                    if (i3 > 0) {
                        return 1;
                    }
                    return i3 < 0 ? -1 : 0;
                }
            });
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry entry : arrayList) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("name", ((b) entry.getValue()).f7169a);
                jSONObject4.put("time", ((b) entry.getValue()).f7173e);
                jSONArray.put(jSONObject4);
            }
            jSONObject2.put(f7160j, jSONArray);
            Object jSONArray2 = new JSONArray();
            for (int size = o().I.size() - 1; size >= 0; size--) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("name", o().I.get(size).f7169a);
                jSONObject5.put("time", o().I.get(size).f7172d);
            }
            jSONObject2.put(k, jSONArray2);
            jSONObject.put(f7151a, jSONObject2);
        } catch (Throwable unused) {
        }
    }

    public static void b(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = new ArrayList(o().K).iterator();
            while (it.hasNext()) {
                jSONArray.put(((d) it.next()).a());
            }
            com.bytedance.crash.entity.c.a(jSONObject, f7152b, jSONArray);
        } catch (Exception unused) {
        }
    }

    public static void a(JSONObject jSONObject, File file) {
        try {
            jSONObject.put(com.bytedance.crash.constants.c.f6389f, new File(file, "background").exists());
        } catch (Throwable unused) {
        }
        String d2 = k.d(new File(file, "activity_trace.json"));
        if (d2 != null) {
            try {
                q.a(jSONObject, new JSONObject(d2));
            } catch (Throwable unused2) {
            }
        }
        try {
            JSONArray jSONArray = new JSONArray();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file, "activity_track.inf")));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String b2 = d.b(readLine);
                if (b2 != null) {
                    jSONArray.put(b2);
                }
            }
            com.bytedance.crash.entity.c.a(jSONObject, f7152b, jSONArray);
        } catch (IOException unused3) {
        }
        try {
            com.bytedance.crash.tracker.fragment.b.a(jSONObject, file);
        } catch (Throwable unused4) {
        }
    }

    public static String c(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject(f7151a);
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject(E[2])) == null) {
            return null;
        }
        return optJSONObject.optString("name");
    }

    private void b(File file) {
        f fVar = new f(file.getAbsolutePath() + c.a.SEPARATOR + f7151a + ".json");
        fVar.a();
        fVar.a(f7151a);
        fVar.a();
        for (int i2 = 0; i2 < 6; i2++) {
            c[] cVarArr = this.f7161J;
            if (cVarArr[i2] != null && cVarArr[i2].f7174a != null) {
                fVar.a(E[i2]);
                fVar.a();
                fVar.a("name").b(this.f7161J[i2].f7174a.f7169a).e();
                fVar.a("time").a(this.f7161J[i2].f7175b);
                fVar.b();
                fVar.e();
            }
        }
        fVar.a(f7160j);
        fVar.c();
        ArrayList<Map.Entry> arrayList = new ArrayList(this.H.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Integer, b>>() { // from class: com.bytedance.crash.tracker.a.2
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Map.Entry<Integer, b> entry, Map.Entry<Integer, b> entry2) {
                int i3 = ((entry.getValue().f7173e - entry2.getValue().f7173e) > 0L ? 1 : ((entry.getValue().f7173e - entry2.getValue().f7173e) == 0L ? 0 : -1));
                if (i3 > 0) {
                    return 1;
                }
                return i3 < 0 ? -1 : 0;
            }
        });
        boolean z = true;
        for (Map.Entry entry : arrayList) {
            if (z) {
                z = false;
            } else {
                fVar.e();
            }
            fVar.a();
            fVar.a("name").b(((b) entry.getValue()).f7169a).e();
            fVar.a("time").a(((b) entry.getValue()).f7173e);
            fVar.b();
        }
        fVar.d().e();
        fVar.a(k);
        fVar.c();
        for (int size = this.I.size() - 1; size >= 0; size--) {
            fVar.a();
            fVar.a("name").b(this.I.get(size).f7169a).e();
            fVar.a("time").a(this.I.get(size).f7172d);
            fVar.b();
            if (size != 0) {
                fVar.e();
            }
        }
        fVar.d();
        fVar.b();
        fVar.b();
        fVar.g();
    }

    private void c(File file) {
        StringBuilder sb = new StringBuilder();
        Iterator<d> it = o().K.iterator();
        while (it.hasNext()) {
            it.next().a(sb);
        }
        if (sb.length() == 0) {
            return;
        }
        k.c(new File(file, "activity_track.inf"), sb.toString());
    }

    public static JSONArray i() {
        JSONArray jSONArray = new JSONArray();
        Iterator it = new ArrayList(o().K).iterator();
        while (it.hasNext()) {
            jSONArray.put(((d) it.next()).a());
        }
        return jSONArray;
    }

    public static String j() {
        c cVar = o().f7161J[2];
        if (cVar != null && cVar.f7174a != null) {
            return cVar.f7174a.f7169a;
        }
        return String.valueOf((char[]) null);
    }

    void a(b bVar) {
        d poll = this.K.size() >= 50 ? this.K.poll() : null;
        if (poll == null) {
            poll = new d();
        }
        this.K.add(poll);
        poll.a(bVar);
    }

    private void a(final String str, final long j2, final String str2, final int i2) {
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.tracker.a.3
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.crash.tracker.c.a().a("activityLifeCycle", str + '.' + str2 + '@' + Long.toHexString(i2), j2);
            }
        });
    }

    /* compiled from: ActivityLifecycle.java */
    /* loaded from: classes2.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        b f7176a;

        /* renamed from: b  reason: collision with root package name */
        long f7177b;

        /* renamed from: c  reason: collision with root package name */
        int f7178c;

        void a(b bVar) {
            this.f7176a = bVar;
            this.f7177b = bVar.f7172d;
            this.f7178c = bVar.f7171c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(StringBuilder sb) {
            sb.append(this.f7177b).append(' ').append(this.f7176a.f7169a).append(' ').append(this.f7176a.f7170b).append(' ').append(this.f7178c).append('\n');
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String b(String str) {
            String[] split = str.split(" ");
            if (split.length != 4) {
                return null;
            }
            try {
                long parseLong = Long.parseLong(split[0]);
                String str2 = split[1];
                int parseInt = Integer.parseInt(split[2]);
                int parseInt2 = Integer.parseInt(split[3]);
                if (parseInt2 >= 0 && parseInt2 < a.F.length) {
                    return a(parseLong, str2, parseInt, parseInt2);
                }
            } catch (Exception unused) {
            }
            return null;
        }

        private static String a(long j2, String str, int i2, int i3) {
            return e.b(j2) + " : " + str + '.' + a.F[i3] + '@' + Long.toHexString(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a() {
            return a(this.f7177b, this.f7176a.f7169a, this.f7176a.f7170b, this.f7178c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ActivityLifecycle.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        String f7169a;

        /* renamed from: b  reason: collision with root package name */
        int f7170b;

        /* renamed from: c  reason: collision with root package name */
        int f7171c;

        /* renamed from: d  reason: collision with root package name */
        long f7172d;

        /* renamed from: e  reason: collision with root package name */
        long f7173e;

        public b(String str, long j2, int i2) {
            this.f7169a = str;
            this.f7170b = i2;
            this.f7173e = j2;
        }

        public void a(int i2, long j2) {
            this.f7171c = i2;
            this.f7172d = j2;
        }
    }
}
