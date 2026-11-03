package tt.g.p.f.n;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.JProtect;
import org.json.JSONObject;
@JProtect
/* loaded from: classes10.dex */
public class bk implements bh {

    /* renamed from: j  reason: collision with root package name */
    private static int f21207j = 2000;
    private static String k = bj.f21204h;
    private static String l = bj.f21197a;
    private static String m = bj.f21205i;

    /* renamed from: a  reason: collision with root package name */
    public String f21208a;

    /* renamed from: b  reason: collision with root package name */
    public String f21209b;

    /* renamed from: c  reason: collision with root package name */
    public String f21210c;

    /* renamed from: d  reason: collision with root package name */
    public String f21211d;

    /* renamed from: e  reason: collision with root package name */
    public String f21212e;

    /* renamed from: f  reason: collision with root package name */
    public String f21213f;

    /* renamed from: g  reason: collision with root package name */
    public String f21214g;

    /* renamed from: h  reason: collision with root package name */
    public String f21215h;

    /* renamed from: i  reason: collision with root package name */
    public String f21216i = "";

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0085, code lost:
        if (r7 == 1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
        if (r7 == 2) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0089, code lost:
        if (r7 == 3) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008c, code lost:
        r0.f21214g = r4[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
        r0.f21213f = r4[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0096, code lost:
        r0.f21211d = r4[1];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected static tt.g.p.f.n.bk a(android.content.Intent r12) {
        /*
            java.lang.String r12 = r12.getDataString()     // Catch: java.lang.Throwable -> Lad
            boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Throwable -> Lad
            if (r0 != 0) goto Lad
            java.lang.String r0 = tt.g.p.f.n.bk.m     // Catch: java.lang.Throwable -> Lad
            boolean r0 = r12.startsWith(r0)     // Catch: java.lang.Throwable -> Lad
            if (r0 == 0) goto Lad
            tt.g.p.f.n.bk r0 = new tt.g.p.f.n.bk     // Catch: java.lang.Throwable -> Lad
            r0.<init>()     // Catch: java.lang.Throwable -> Lad
            r0.f21215h = r12     // Catch: java.lang.Throwable -> Lad
            java.lang.String r1 = tt.g.p.f.n.bk.m     // Catch: java.lang.Throwable -> Lad
            int r1 = r1.length()     // Catch: java.lang.Throwable -> Lad
            java.lang.String r12 = r12.substring(r1)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r12 = java.net.URLDecoder.decode(r12)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r1 = "[&]"
            java.lang.String[] r12 = r12.split(r1)     // Catch: java.lang.Throwable -> Lad
            int r1 = r12.length     // Catch: java.lang.Throwable -> Lad
            r2 = 0
            r3 = r2
        L30:
            if (r3 >= r1) goto La2
            r4 = r12[r3]     // Catch: java.lang.Throwable -> Lad
            java.lang.String r5 = "[=]"
            java.lang.String[] r4 = r4.split(r5)     // Catch: java.lang.Throwable -> Lad
            int r5 = r4.length     // Catch: java.lang.Throwable -> Lad
            r6 = 2
            if (r5 != r6) goto L9f
            r5 = r4[r2]     // Catch: java.lang.Throwable -> Lad
            r7 = -1
            int r8 = r5.hashCode()     // Catch: java.lang.Throwable -> Lad
            r9 = -1279545600(0xffffffffb3bbaf00, float:-8.7396984E-8)
            r10 = 3
            r11 = 1
            if (r8 == r9) goto L7a
            r9 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            if (r8 == r9) goto L70
            r9 = 3530173(0x35ddbd, float:4.946826E-39)
            if (r8 == r9) goto L66
            r9 = 1408027618(0x53eccbe2, float:2.0340657E12)
            if (r8 == r9) goto L5c
            goto L83
        L5c:
            java.lang.String r8 = "noncestr"
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> Lad
            if (r5 == 0) goto L83
            r7 = r11
            goto L83
        L66:
            java.lang.String r8 = "sign"
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> Lad
            if (r5 == 0) goto L83
            r7 = r6
            goto L83
        L70:
            java.lang.String r8 = "package"
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> Lad
            if (r5 == 0) goto L83
            r7 = r10
            goto L83
        L7a:
            java.lang.String r8 = "prepayid"
            boolean r5 = r5.equals(r8)     // Catch: java.lang.Throwable -> Lad
            if (r5 == 0) goto L83
            r7 = r2
        L83:
            if (r7 == 0) goto L9b
            if (r7 == r11) goto L96
            if (r7 == r6) goto L91
            if (r7 == r10) goto L8c
            goto L9f
        L8c:
            r4 = r4[r11]     // Catch: java.lang.Throwable -> Lad
            r0.f21214g = r4     // Catch: java.lang.Throwable -> Lad
            goto L9f
        L91:
            r4 = r4[r11]     // Catch: java.lang.Throwable -> Lad
            r0.f21213f = r4     // Catch: java.lang.Throwable -> Lad
            goto L9f
        L96:
            r4 = r4[r11]     // Catch: java.lang.Throwable -> Lad
            r0.f21211d = r4     // Catch: java.lang.Throwable -> Lad
            goto L9f
        L9b:
            r4 = r4[r11]     // Catch: java.lang.Throwable -> Lad
            r0.f21210c = r4     // Catch: java.lang.Throwable -> Lad
        L9f:
            int r3 = r3 + 1
            goto L30
        La2:
            java.lang.String r12 = tt.g.p.f.n.al.a()     // Catch: java.lang.Throwable -> Lad
            r0.f21212e = r12     // Catch: java.lang.Throwable -> Lad
            tt.g.p.f.n.bk r12 = r0.c()     // Catch: java.lang.Throwable -> Lad
            return r12
        Lad:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: tt.g.p.f.n.bk.a(android.content.Intent):tt.g.p.f.n.bk");
    }

    public static bk a(Intent intent, String str) {
        bk b2 = b(intent);
        if (b2 == null) {
            b2 = a(intent);
        }
        if (b2 != null) {
            b2.a(str);
        }
        return b2;
    }

    protected static bk b(Intent intent) {
        Bundle extras = intent.getExtras();
        ComponentName component = intent.getComponent();
        if (component != null) {
            try {
                if (component.getPackageName().equalsIgnoreCase(k) && component.getClassName().contains(l) && extras != null) {
                    bk bkVar = new bk();
                    bkVar.f21208a = extras.getString(bj.f21198b);
                    bkVar.f21209b = extras.getString(bj.f21199c);
                    bkVar.f21211d = extras.getString(bj.f21200d);
                    bkVar.f21212e = extras.getString(bj.f21201e);
                    bkVar.f21213f = extras.getString(bj.r);
                    bkVar.f21210c = extras.getString(bj.f21202f);
                    bkVar.f21214g = extras.getString(bj.f21203g);
                    bkVar.f21215h = extras.toString();
                    return bkVar.c();
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private bk c() {
        if (TextUtils.isEmpty(this.f21210c)) {
            this.f21210c = "null";
            return null;
        }
        String str = this.f21208a;
        if (str == null) {
            str = "";
        }
        this.f21208a = str;
        String str2 = this.f21209b;
        if (str2 == null) {
            str2 = "";
        }
        this.f21209b = str2;
        String str3 = this.f21211d;
        if (str3 == null) {
            str3 = "";
        }
        this.f21211d = str3;
        String str4 = this.f21212e;
        if (str4 == null) {
            str4 = "";
        }
        this.f21212e = str4;
        String str5 = this.f21213f;
        if (str5 == null) {
            str5 = "";
        }
        this.f21213f = str5;
        String str6 = this.f21214g;
        if (str6 == null) {
            str6 = "";
        }
        this.f21214g = str6;
        String str7 = this.f21215h;
        this.f21215h = str7 != null ? str7 : "";
        return this;
    }

    @Override // tt.g.p.f.n.bh
    public JSONObject a() {
        JSONObject a2 = a(true, true, true, true, true, true, true);
        try {
            if (a2.toString().length() > f21207j) {
                a2 = a(false, true, true, true, true, true, true);
            }
            if (a2.toString().length() > f21207j) {
                a2 = a(false, false, true, true, true, true, true);
            }
            if (a2.toString().length() > f21207j) {
                a2 = a(false, false, false, true, true, true, true);
            }
            if (a2.toString().length() > f21207j) {
                a2 = a(false, false, false, false, true, true, true);
            }
            if (a2.toString().length() > f21207j) {
                a2 = a(false, false, false, false, false, true, true);
            }
            if (a2.toString().length() > f21207j) {
                a2 = a(false, false, false, false, false, false, true);
            }
            int length = a2.toString().length();
            int i2 = f21207j;
            if (length > i2) {
                if (length - i2 > this.f21216i.length()) {
                    return a(false, false, false, false, false, false, false);
                }
                String str = this.f21216i;
                this.f21216i = str.substring(0, str.length() - (length - f21207j));
                return a(false, false, false, false, false, false, true);
            }
            return a2;
        } catch (Throwable unused) {
            return a2;
        }
    }

    protected JSONObject a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        JSONObject jSONObject = new JSONObject();
        if (z2) {
            try {
                jSONObject.put(bj.f21206j, this.f21208a);
            } catch (Throwable unused) {
            }
        }
        if (z3) {
            jSONObject.put(bj.k, this.f21209b);
        }
        jSONObject.put(bj.l, this.f21210c);
        if (z4) {
            jSONObject.put(bj.m, this.f21211d);
        }
        jSONObject.put(bj.n, this.f21212e);
        if (z5) {
            jSONObject.put(bj.o, this.f21213f);
        }
        if (z6) {
            jSONObject.put(bj.p, this.f21214g);
        }
        if (z) {
            jSONObject.put(bj.q, this.f21215h);
        }
        if (z7) {
            jSONObject.put("st", this.f21216i);
        }
        return jSONObject;
    }

    public void a(String str) {
        this.f21216i = str;
    }

    @Override // tt.g.p.f.n.bh
    public int b() {
        return 1;
    }
}
