package com.bytedance.applog.store;

import android.content.ContentValues;
import android.database.Cursor;
import com.bytedance.applog.collector.d;
import com.bytedance.applog.log.o;
import com.bytedance.applog.monitor.v3.n;
import com.bytedance.applog.util.q;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BaseData.java */
/* loaded from: classes.dex */
public abstract class a implements Cloneable {
    private static final String Q = "k_cls";

    /* renamed from: a  reason: collision with root package name */
    static final String f3435a = "_id";

    /* renamed from: b  reason: collision with root package name */
    static final String f3436b = "local_time_ms";

    /* renamed from: c  reason: collision with root package name */
    static final String f3437c = "tea_event_index";

    /* renamed from: d  reason: collision with root package name */
    static final String f3438d = "session_id";

    /* renamed from: e  reason: collision with root package name */
    static final String f3439e = "user_id";

    /* renamed from: f  reason: collision with root package name */
    static final String f3440f = "uid";

    /* renamed from: g  reason: collision with root package name */
    static final String f3441g = "user_unique_id";

    /* renamed from: h  reason: collision with root package name */
    static final String f3442h = "nt";

    /* renamed from: i  reason: collision with root package name */
    static final String f3443i = "ab_sdk_version";

    /* renamed from: j  reason: collision with root package name */
    static final String f3444j = "_app_id";
    static final String k = "_local_event_id";
    static final String l = "monitor_id";
    static final String m = "event_stained";
    static final String n = "datetime";
    static final String o = "user_type";
    static final String p = "user_is_login";
    static final String q = "user_is_auth";
    static final String r = "priority";
    static final String s = "forward";
    static final String t = "event_id";
    public String A;
    public String B;
    public int C;
    protected String G;
    String H;
    protected List<String> N;
    protected d.a O;
    long u;
    public long v;
    public long w;
    public String x;
    public long y;
    public long z;
    private static final SimpleDateFormat P = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final com.bytedance.applog.log.a<HashMap<String, a>> R = new com.bytedance.applog.log.a<HashMap<String, a>>() { // from class: com.bytedance.applog.store.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.applog.log.a
        /* renamed from: a */
        public HashMap<String, a> create(Object... objArr) {
            return a.c();
        }
    };
    public int D = 0;
    public int E = 0;
    public int F = 0;
    int I = -1;
    @Deprecated

    /* renamed from: J  reason: collision with root package name */
    int f3445J = 0;
    public long K = 0;
    public long L = 0;
    public int M = 0;

    protected abstract JSONObject f();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String h();

    public abstract String i();

    public String o() {
        return null;
    }

    public a() {
        a(0L);
        a();
        this.N = Collections.singletonList(h());
    }

    public void a() {
        long a2 = com.bytedance.applog.monitor.v3.i.a();
        this.K = a2;
        this.L = a2;
    }

    public String b() {
        return this.G;
    }

    public void a(String str) {
        this.G = str;
    }

    public void a(long j2) {
        if (j2 == 0) {
            j2 = System.currentTimeMillis();
        }
        this.v = j2;
    }

    public void a(d.a aVar) {
        this.O = aVar;
    }

    public static HashMap<String, a> c() {
        HashMap<String, a> hashMap = new HashMap<>();
        a(hashMap, new i());
        a(hashMap, new g());
        a(hashMap, new j());
        a(hashMap, new h());
        for (a aVar : d()) {
            a(hashMap, aVar);
        }
        return hashMap;
    }

    public static a[] d() {
        return new a[]{new c(), new e(null, null, false, null), new d(null, "", new JSONObject())};
    }

    private static void a(HashMap<String, a> hashMap, a aVar) {
        hashMap.put(aVar.h(), aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> e() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "tea_event_index", "integer", f3442h, "integer", "user_id", "integer", "uid", "integer", "session_id", "varchar", "user_unique_id", "varchar", "ab_sdk_version", "varchar", "user_type", "integer", "user_is_login", "integer", "user_is_auth", "integer", f3444j, "varchar", "priority", "integer", "forward", "integer", k, "varchar", "monitor_id", "integer", "event_stained", "integer");
    }

    public int a(Cursor cursor) {
        this.u = cursor.getLong(0);
        this.v = cursor.getLong(1);
        this.w = cursor.getLong(2);
        this.C = cursor.getInt(3);
        this.y = cursor.getLong(4);
        this.z = cursor.getLong(5);
        this.x = cursor.getString(6);
        this.A = cursor.getString(7);
        this.B = cursor.getString(8);
        this.D = cursor.getInt(9);
        this.E = cursor.getInt(10);
        this.F = cursor.getInt(11);
        this.G = cursor.getString(12);
        this.I = cursor.getInt(13);
        this.f3445J = cursor.getInt(14);
        String string = cursor.getString(15);
        if (q.a(string)) {
            try {
                this.K = Long.parseLong(string);
            } catch (Throwable unused) {
            }
        }
        this.L = cursor.getLong(16);
        this.M = cursor.getInt(17);
        return 18;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.v));
        contentValues.put("tea_event_index", Long.valueOf(this.w));
        contentValues.put(f3442h, Integer.valueOf(this.C));
        contentValues.put("user_id", Long.valueOf(this.y));
        contentValues.put("uid", Long.valueOf(this.z));
        contentValues.put("session_id", this.x);
        contentValues.put("user_unique_id", this.A);
        contentValues.put("ab_sdk_version", this.B);
        contentValues.put("user_type", Integer.valueOf(this.D));
        contentValues.put("user_is_login", Integer.valueOf(this.E));
        contentValues.put("user_is_auth", Integer.valueOf(this.F));
        contentValues.put(f3444j, this.G);
        contentValues.put("priority", Integer.valueOf(this.I));
        contentValues.put("forward", Integer.valueOf(this.f3445J));
        contentValues.put(k, String.valueOf(this.K));
        contentValues.put("monitor_id", Long.valueOf(this.L));
        contentValues.put("event_stained", Integer.valueOf(this.M));
    }

    public static String b(long j2) {
        return P.format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.v);
        jSONObject.put(f3444j, this.G);
        jSONObject.put("priority", this.I);
        jSONObject.put("forward", this.f3445J);
        jSONObject.put(k, this.K);
        jSONObject.put("monitor_id", this.L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a b(JSONObject jSONObject) {
        this.v = jSONObject.optLong("local_time_ms", 0L);
        this.u = 0L;
        this.w = 0L;
        this.C = 0;
        this.y = 0L;
        this.z = 0L;
        this.x = null;
        this.A = null;
        this.B = null;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = jSONObject.optString(f3444j);
        this.I = jSONObject.optInt("priority", -1);
        this.f3445J = jSONObject.optInt("forward");
        this.K = jSONObject.optLong(k, 0L);
        this.L = jSONObject.optLong("monitor_id", 0L);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ContentValues b(ContentValues contentValues) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        a(contentValues);
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String g() {
        List<String> e2 = e();
        if (e2 != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("create table if not exists ").append(h()).append("(");
            for (int i2 = 0; i2 < e2.size(); i2 += 2) {
                sb.append(e2.get(i2)).append(" ").append(e2.get(i2 + 1)).append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append(")");
            return sb.toString();
        }
        return null;
    }

    public long j() {
        String n2 = n();
        long length = q.a(n2) ? 0 + n2.length() : 0L;
        String o2 = o();
        return q.a(o2) ? length + o2.length() : length;
    }

    public final JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Q, h());
            a(jSONObject);
        } catch (Throwable th) {
            s().a(4, this.N, "toIpcJson failed", th, new Object[0]);
        }
        return jSONObject;
    }

    public final JSONObject l() {
        JSONObject jSONObject = new JSONObject();
        try {
            this.H = b(this.v);
            return f();
        } catch (Throwable th) {
            com.bytedance.applog.b b2 = com.bytedance.applog.a.b(this.G);
            if (b2 != null) {
                b2.ad().a(n.PACK_ERROR_COUNT);
                if (th instanceof JSONException) {
                    b2.ad().a(n.JSON_ERROR_COUNT);
                }
                b2.ad().a(h() + " to pack json failed", th);
            }
            s().a(4, this.N, "toPackJson failed", th, new Object[0]);
            return jSONObject;
        }
    }

    public static a b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            a aVar = R.get(new Object[0]).get(jSONObject.optString(Q, ""));
            if (aVar == null) {
                o.a().d(4, "from ipc class not recognized", new Object[0]);
                return null;
            }
            return aVar.clone().b(jSONObject);
        } catch (Throwable th) {
            o.a().a(4, "from ipc failed", th, new Object[0]);
            return null;
        }
    }

    /* renamed from: m */
    public a clone() {
        try {
            a aVar = (a) super.clone();
            aVar.a();
            return aVar;
        } catch (Throwable th) {
            s().a(4, this.N, "Clone data failed", th, new Object[0]);
            return null;
        }
    }

    public String toString() {
        String h2 = h();
        if (!getClass().getSimpleName().equalsIgnoreCase(h2)) {
            h2 = h2 + ", " + getClass().getSimpleName();
        }
        String str = this.x;
        String str2 = "-";
        if (str != null) {
            int indexOf = str.indexOf("-");
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
            str2 = str;
        }
        return "{" + h2 + ", " + n() + ", " + str2 + ", " + this.v + ", id=" + this.K + "}";
    }

    public String n() {
        return "sid:" + this.x;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(JSONObject jSONObject) {
        com.bytedance.applog.b b2 = com.bytedance.applog.a.b(this.G);
        boolean z = b2 != null && b2.G();
        if (z || this.y > 0) {
            jSONObject.put("user_id", this.y);
        }
        if (z || this.z > 0) {
            jSONObject.put("uid", this.z);
        }
        if (z || this.D > 0) {
            jSONObject.put("user_type", this.D);
        }
        if (z || this.E > 0) {
            jSONObject.put("user_is_login", this.E);
        }
        if (z || this.F > 0) {
            jSONObject.put("user_is_auth", this.F);
        }
    }

    public void a(int i2) {
        this.I = i2;
    }

    public int p() {
        return this.I;
    }

    public void b(int i2) {
        this.f3445J = i2;
    }

    public int q() {
        return this.f3445J;
    }

    public long r() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.bytedance.applog.log.h s() {
        com.bytedance.applog.log.h b2 = com.bytedance.applog.log.b.b(this.G);
        return b2 != null ? b2 : o.a();
    }
}
