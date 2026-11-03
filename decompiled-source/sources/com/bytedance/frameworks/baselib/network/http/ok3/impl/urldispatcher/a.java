package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.util.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: BaseDispatchAction.java */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: i  reason: collision with root package name */
    private static final String f8163i = "a";

    /* renamed from: g  reason: collision with root package name */
    protected String f8170g;

    /* renamed from: h  reason: collision with root package name */
    protected int f8171h;

    /* renamed from: j  reason: collision with root package name */
    private long f8172j;
    private long k;
    private int l;
    private long m;

    /* renamed from: a  reason: collision with root package name */
    protected List<String> f8164a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    protected List<String> f8165b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    protected List<String> f8166c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    protected List<String> f8167d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    protected List<String> f8168e = new ArrayList();

    /* renamed from: f  reason: collision with root package name */
    protected List<String> f8169f = new ArrayList();
    protected List<String> r = new ArrayList();
    private List<String> n = new ArrayList();

    /* compiled from: BaseDispatchAction.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0115a {
        DISPATCH_NONE,
        DISPATCH_HIT,
        DISPATCH_DROP,
        DISPATCH_DELAY
    }

    public abstract boolean a(JSONObject jSONObject, String str, long j2);

    public static a a(String str, JSONObject jSONObject, int i2, long j2, long j3, long j4, String str2, long j5, List<String> list, int i3) {
        a iVar = str.equals("tc") ? new i() : null;
        if (str.equals("dispatch")) {
            iVar = new g();
        }
        if (str.equals(com.bytedance.apm.constant.k.RESPONSE_DELAY)) {
            iVar = new c();
        }
        if (iVar == null) {
            return null;
        }
        iVar.a(i2);
        iVar.a(j2, j3);
        iVar.a(j4);
        iVar.a(list);
        iVar.b(i3);
        if (iVar.a(jSONObject, str2, j5)) {
            return iVar;
        }
        return null;
    }

    private void a(int i2) {
        this.l = i2;
    }

    private void a(long j2, long j3) {
        this.f8172j = j2;
        this.k = j3;
    }

    private void a(long j2) {
        this.m = j2;
    }

    private void a(List<String> list) {
        this.n = list;
    }

    private void b(int i2) {
        if (i2 < 0) {
            this.f8171h = Integer.MAX_VALUE;
        } else {
            this.f8171h = i2;
        }
    }

    public int a() {
        return this.l;
    }

    public long b() {
        return this.m;
    }

    public String c() {
        return this.f8170g;
    }

    public boolean a(l lVar) {
        if (lVar.c() > this.l) {
            if (Logger.debug()) {
                Logger.d(f8163i, "jump action: " + this.f8170g + ", dispatchPriority: " + lVar.c() + ", actionPriority: " + this.l);
            }
            return false;
        } else if (!this.n.isEmpty() && !TextUtils.isEmpty(lVar.a()) && !this.n.contains(lVar.a())) {
            if (Logger.debug()) {
                Logger.d(f8163i, "request method not support: " + lVar.a());
            }
            return false;
        } else {
            return e();
        }
    }

    public boolean d() {
        return e();
    }

    private boolean e() {
        long j2 = this.f8172j;
        if (j2 == 0 && this.k == 0) {
            return true;
        }
        if (j2 != -1 && this.k != -1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > this.f8172j && currentTimeMillis < this.k) {
                return true;
            }
            if (Logger.debug()) {
                Logger.d(f8163i, "current time is out action lifecycle");
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JSONObject jSONObject) {
        a(jSONObject, "host_group", this.f8164a, false);
        a(jSONObject, "equal_group", this.f8165b, false);
        a(jSONObject, "prefixes_group", this.f8166c, false);
        a(jSONObject, "contain_group", this.f8167d, false);
        a(jSONObject, "pattern_group", this.f8168e, true);
        a(jSONObject, "url_group", this.f8169f, true);
        a(jSONObject, "path_contain", this.r, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(Uri uri) {
        boolean z;
        boolean z2;
        if (this.f8164a.isEmpty()) {
            z = true;
            break;
        }
        for (String str : this.f8164a) {
            if (p.a(uri.getHost(), str)) {
                z = true;
                break;
            }
        }
        z = false;
        if (!z) {
            if (Logger.debug()) {
                Logger.d(f8163i, "host group is not match");
            }
            return false;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        if (!this.f8165b.isEmpty()) {
            for (String str2 : this.f8165b) {
                if (path.equals(str2)) {
                    if (Logger.debug()) {
                        Logger.d(f8163i, "path equal hit: " + str2);
                    }
                    return true;
                }
            }
        }
        if (!this.f8166c.isEmpty()) {
            for (String str3 : this.f8166c) {
                if (path.startsWith(str3)) {
                    if (Logger.debug()) {
                        Logger.d(f8163i, "path prefix hit: " + str3);
                    }
                    return true;
                }
            }
        }
        if (!this.f8167d.isEmpty()) {
            for (String str4 : this.f8167d) {
                if (path.contains(str4)) {
                    if (Logger.debug()) {
                        Logger.d(f8163i, "path contain hit: " + str4);
                    }
                    return true;
                }
            }
        }
        if (!this.f8168e.isEmpty()) {
            for (String str5 : this.f8168e) {
                if (path.matches(str5)) {
                    if (Logger.debug()) {
                        Logger.d(f8163i, "path pattern hit: " + str5);
                    }
                    return true;
                }
            }
        }
        if (!this.f8169f.isEmpty()) {
            if (!this.r.isEmpty()) {
                Iterator<String> it = this.r.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (path.contains(it.next())) {
                            z2 = true;
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
                if (!z2) {
                    return false;
                }
            }
            for (String str6 : this.f8169f) {
                if (uri.toString().matches(str6)) {
                    if (Logger.debug()) {
                        Logger.d(f8163i, "full url hit: " + str6);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void a(JSONObject jSONObject, String str, List<String> list, boolean z) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    if (z) {
                        if (a(optString)) {
                            list.add(optString);
                        }
                    } else {
                        list.add(optString);
                    }
                }
            }
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Pattern.compile(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
