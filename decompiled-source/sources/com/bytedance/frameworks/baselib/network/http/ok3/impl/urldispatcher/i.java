package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.a;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;
/* compiled from: TCTypedAction.java */
/* loaded from: classes2.dex */
public class i extends j {

    /* renamed from: i  reason: collision with root package name */
    private static final String f8207i = "i";
    public static final int n = -555;
    private static final int s = -5559;
    private static final int t = -5551;

    /* renamed from: j  reason: collision with root package name */
    private String f8208j;
    private String k;
    private String l;
    private String m;
    private String u;
    private boolean o = false;
    private int v = -555;
    private int p = 100;
    private boolean q = false;

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    protected void a(String str, boolean z, int i2) {
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    public int e() {
        return -1;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    public a.EnumC0115a a(l lVar, String str, List<String> list) {
        if (Logger.debug()) {
            Logger.d(f8207i, "tc action");
        }
        Uri parse = Uri.parse(str);
        if (this.q || !a(parse)) {
            list.set(0, str);
            return a.EnumC0115a.DISPATCH_NONE;
        }
        list.set(0, str);
        if (this.o) {
            if (Logger.debug()) {
                Logger.d(f8207i, "drop action");
            }
            if (g()) {
                list.set(0, "");
                return a.EnumC0115a.DISPATCH_DROP;
            }
            return a.EnumC0115a.DISPATCH_NONE;
        } else if (!this.k.isEmpty() || !this.f8208j.isEmpty() || !this.l.isEmpty()) {
            if (parse.getScheme() != null && a(parse.getScheme(), this.f8208j)) {
                str = str.replaceFirst(parse.getScheme(), this.f8208j);
            }
            if (parse.getHost() != null && !TextUtils.isEmpty(this.k)) {
                str = str.replaceFirst(parse.getHost(), this.k);
            }
            if (parse.getPath() != null && !TextUtils.isEmpty(this.l)) {
                str = str.replaceFirst(parse.getPath(), this.l);
            }
            list.set(0, str);
            return a.EnumC0115a.DISPATCH_HIT;
        } else if (!TextUtils.isEmpty(this.m) && !TextUtils.isEmpty(this.u)) {
            list.set(0, str.replaceAll(this.u, this.m));
            return a.EnumC0115a.DISPATCH_HIT;
        } else {
            return a.EnumC0115a.DISPATCH_NONE;
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    protected boolean a(JSONObject jSONObject, List<Boolean> list, String str, long j2) {
        a(jSONObject);
        this.f8170g = jSONObject.optString("service_name");
        this.f8208j = jSONObject.optString("scheme_replace");
        this.k = jSONObject.optString("host_replace");
        this.l = jSONObject.optString("path_replace");
        String optString = jSONObject.optString("replace");
        this.m = optString;
        if (!TextUtils.isEmpty(optString)) {
            String optString2 = jSONObject.optString("regex");
            if (a(optString2)) {
                this.u = optString2;
            } else {
                this.q = true;
                return true;
            }
        }
        this.o = jSONObject.optInt("drop") == 1;
        int optInt = jSONObject.optInt("drop_code", -1);
        if (optInt == -555 || (optInt >= -5559 && optInt <= -5551)) {
            this.v = optInt;
        }
        int optInt2 = jSONObject.optInt("possibility", 100);
        if (optInt2 >= 0 && optInt2 <= 100) {
            this.p = optInt2;
        }
        if ((this.f8165b.isEmpty() && this.f8166c.isEmpty() && this.f8167d.isEmpty() && this.f8168e.isEmpty() && this.f8169f.isEmpty()) || (!this.o && this.f8208j.isEmpty() && this.k.isEmpty() && this.l.isEmpty() && this.m.isEmpty())) {
            this.q = true;
        }
        list.set(0, false);
        return true;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    public int h() {
        return this.v;
    }

    private boolean g() {
        int i2 = this.p;
        if (i2 >= 100) {
            return true;
        }
        if (i2 <= 0) {
            return false;
        }
        if (new Random().nextInt(100) < this.p) {
            return true;
        }
        if (Logger.debug()) {
            Logger.d(f8207i, "tc drop action was probabilistic discard");
        }
        return false;
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        return ((str.equals("http") || str.equals("https")) && (str2.equals("http") || str2.equals("https"))) || ((str.equals("ws") || str.equals("wss")) && (str2.equals("ws") || str2.equals("wss")));
    }
}
