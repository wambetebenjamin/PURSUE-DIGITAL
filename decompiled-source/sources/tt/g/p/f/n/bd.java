package tt.g.p.f.n;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.JProtect;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
@JProtect
/* loaded from: classes10.dex */
public class bd implements bh {
    private static int m = 2000;
    private static String n = bf.f21185a;
    private static String o = bf.f21186b;

    /* renamed from: a  reason: collision with root package name */
    public String f21175a = "";

    /* renamed from: b  reason: collision with root package name */
    public String f21176b = "";

    /* renamed from: c  reason: collision with root package name */
    public String f21177c = "";

    /* renamed from: d  reason: collision with root package name */
    public String f21178d = "";

    /* renamed from: e  reason: collision with root package name */
    public String f21179e = "";

    /* renamed from: f  reason: collision with root package name */
    public String f21180f = "";

    /* renamed from: g  reason: collision with root package name */
    public String f21181g = "";

    /* renamed from: h  reason: collision with root package name */
    public String f21182h = "";

    /* renamed from: i  reason: collision with root package name */
    public String f21183i = "";

    /* renamed from: j  reason: collision with root package name */
    public String f21184j = "";
    public String k = "";
    public String l = "";

    protected static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        try {
            for (String str2 : str.split("[&]")) {
                String[] split = str2.split("[=]");
                if (split.length == 2) {
                    hashMap.put(split[0], URLDecoder.decode(split[1]));
                }
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    protected static bd a(Intent intent) {
        String dataString = intent.getDataString();
        if (TextUtils.isEmpty(dataString) || !dataString.startsWith(o)) {
            return null;
        }
        bd bdVar = new bd();
        bdVar.k = dataString;
        Map<String, String> a2 = a(dataString.substring(o.length()));
        String str = bf.p;
        bdVar.f21175a = a2.containsKey(str) ? a2.get(str) : "";
        String str2 = bf.q;
        if (a2.containsKey(str2)) {
            try {
                bdVar.c(URLDecoder.decode(new JSONObject(new String(Base64.decode(a2.get(str2), 0))).optString(bf.r)));
                return bdVar.c();
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static bd a(Intent intent, String str) {
        bd b2 = b(intent);
        if (b2 == null) {
            b2 = a(intent);
        }
        if (b2 != null) {
            b2.b(str);
        }
        return b2;
    }

    public static bd a(String str, String str2) {
        bd bdVar = new bd();
        bdVar.k = str;
        bdVar.c(str);
        bdVar.l = str2;
        return bdVar.c();
    }

    protected static bd b(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                String string = extras.getString(bf.s);
                if (TextUtils.isEmpty(string) || !string.startsWith(n)) {
                    return null;
                }
                bd bdVar = new bd();
                String substring = string.substring(n.length());
                bdVar.k = string;
                Map<String, String> a2 = a(substring);
                String str = bf.f21189e;
                bdVar.f21175a = a2.containsKey(str) ? a2.get(str) : "";
                String str2 = bf.f21192h;
                bdVar.f21178d = a2.containsKey(str2) ? a2.get(str2) : "";
                String str3 = bf.f21190f;
                String str4 = a2.containsKey(str3) ? a2.get(str3) : "";
                bdVar.f21177c = str4;
                bdVar.f21177c = al.a(str4);
                String str5 = bf.t;
                if (a2.containsKey(str5) && bdVar.d(a2.get(str5))) {
                    return bdVar.c();
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private bd c() {
        if (TextUtils.isEmpty(this.f21179e)) {
            this.f21179e = "null";
            return null;
        }
        String str = this.f21175a;
        if (str == null) {
            str = "";
        }
        this.f21175a = str;
        String str2 = this.f21178d;
        if (str2 == null) {
            str2 = "";
        }
        this.f21178d = str2;
        String str3 = this.f21184j;
        if (str3 == null) {
            str3 = "";
        }
        this.f21184j = str3;
        this.f21177c = TextUtils.isEmpty(this.f21177c) ? al.a() : this.f21177c;
        String str4 = this.f21182h;
        if (str4 == null) {
            str4 = "";
        }
        this.f21182h = str4;
        String str5 = this.f21183i;
        if (str5 == null) {
            str5 = "";
        }
        this.f21183i = str5;
        String str6 = this.f21176b;
        if (str6 == null) {
            str6 = "";
        }
        this.f21176b = str6;
        String str7 = this.f21180f;
        if (str7 == null) {
            str7 = "";
        }
        this.f21180f = str7;
        String str8 = this.f21181g;
        this.f21181g = str8 != null ? str8 : "";
        return this;
    }

    @Override // tt.g.p.f.n.bh
    public JSONObject a() {
        JSONObject a2 = a(true, true, true, true, true);
        try {
            if (a2.toString().length() > m) {
                a2 = a(false, true, true, true, true);
            }
            if (a2.toString().length() > m) {
                a2 = a(false, false, true, true, true);
            }
            if (a2.toString().length() > m) {
                a2 = a(false, false, false, true, true);
            }
            if (a2.toString().length() > m) {
                a2 = a(false, false, false, false, true);
            }
            int length = a2.toString().length();
            int i2 = m;
            if (length > i2) {
                if (length - i2 > this.l.length()) {
                    return a(false, false, false, false, false);
                }
                String str = this.l;
                this.l = str.substring(0, str.length() - (length - m));
                return a(false, false, false, false, true);
            }
            return a2;
        } catch (Throwable unused) {
            return a2;
        }
    }

    protected JSONObject a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(bf.f21193i, this.f21179e);
            jSONObject.put(bf.f21189e, this.f21175a);
            jSONObject.put(bf.f21190f, this.f21177c);
            jSONObject.put(bf.f21191g, this.f21176b);
            if (z2) {
                jSONObject.put(bf.f21192h, this.f21178d);
            }
            jSONObject.put(bf.f21194j, this.f21180f);
            jSONObject.put(bf.k, this.f21181g);
            if (z3) {
                jSONObject.put(bf.l, this.f21182h);
            }
            if (z4) {
                jSONObject.put(bf.m, this.f21183i);
            }
            jSONObject.put(bf.n, this.f21184j);
            if (z) {
                jSONObject.put(bf.o, this.k);
            }
            if (z5) {
                jSONObject.put("st", this.l);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // tt.g.p.f.n.bh
    public int b() {
        return 2;
    }

    public void b(String str) {
        this.l = str;
    }

    protected boolean c(String str) {
        try {
            Map<String, String> a2 = a(str);
            String str2 = bf.f21189e;
            this.f21175a = a2.containsKey(str2) ? a2.get(str2).replace("\"", "") : this.f21175a;
            String str3 = bf.f21192h;
            this.f21178d = a2.containsKey(str3) ? a2.get(str3).replace("\"", "") : "";
            String str4 = bf.f21190f;
            String replace = a2.containsKey(str4) ? a2.get(str4).replace("\"", "") : "";
            this.f21177c = replace;
            this.f21177c = al.a(replace);
            String str5 = bf.t;
            if (a2.containsKey(str5)) {
                d(a2.get(str5));
            } else {
                String str6 = bf.u;
                this.f21176b = a2.containsKey(str6) ? a2.get(str6).replace("\"", "") : "";
                String str7 = bf.f21193i;
                this.f21179e = a2.containsKey(str7) ? a2.get(str7).replace("\"", "") : "";
                String str8 = bf.f21194j;
                this.f21180f = a2.containsKey(str8) ? a2.get(str8).replace("\"", "") : "";
                String str9 = bf.l;
                this.f21182h = a2.containsKey(str9) ? a2.get(str9).replace("\"", "") : "";
                String str10 = bf.m;
                this.f21183i = a2.containsKey(str10) ? a2.get(str10).replace("\"", "") : "";
                String str11 = bf.n;
                this.f21184j = a2.containsKey(str11) ? a2.get(str11).replace("\"", "") : "";
                String str12 = bf.k;
                this.f21181g = a2.containsKey(str12) ? a2.get(str12).replace("\"", "") : "";
            }
            this.f21178d = URLDecoder.decode(this.f21178d);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    protected boolean d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = bf.f21193i;
            if (jSONObject.has(str2)) {
                this.f21179e = jSONObject.optString(str2);
            }
            String str3 = bf.v;
            if (jSONObject.has(str3)) {
                this.f21184j = jSONObject.optString(str3);
            }
            String str4 = bf.l;
            if (jSONObject.has(str4)) {
                this.f21182h = jSONObject.optString(str4);
            }
            String str5 = bf.m;
            if (jSONObject.has(str5)) {
                this.f21183i = jSONObject.optString(str5);
            }
            String str6 = bf.k;
            if (jSONObject.has(str6)) {
                this.f21181g = jSONObject.optString(str6);
                return true;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
