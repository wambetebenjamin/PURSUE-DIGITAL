package com.bytedance.ttnet.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.l;
import java.util.List;
import org.json.JSONObject;
/* compiled from: ImageStrategyController.java */
/* loaded from: classes8.dex */
public class c implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15074a = "image_opt_table";

    /* renamed from: b  reason: collision with root package name */
    private static final String f15075b = "image_opt_switch";

    /* renamed from: c  reason: collision with root package name */
    private static final String f15076c = "image_opt_black_interval";

    /* renamed from: d  reason: collision with root package name */
    private static final String f15077d = "image_opt_failed_times";

    /* renamed from: e  reason: collision with root package name */
    private static final String f15078e = "image_opt_limit_count";

    /* renamed from: f  reason: collision with root package name */
    private static volatile c f15079f;

    /* renamed from: g  reason: collision with root package name */
    private static final Object f15080g = new Object();

    /* renamed from: i  reason: collision with root package name */
    private static volatile SharedPreferences f15081i;

    /* renamed from: h  reason: collision with root package name */
    private volatile Context f15082h;

    /* renamed from: j  reason: collision with root package name */
    private volatile int f15083j;
    private volatile long k;
    private volatile int l;
    private volatile int m;

    @Override // com.bytedance.ttnet.config.b
    public List<String> a(String str) {
        return null;
    }

    public static c a(Context context) {
        if (f15079f == null) {
            synchronized (f15080g) {
                if (f15079f == null) {
                    f15079f = new c(context);
                }
            }
        }
        return f15079f;
    }

    private c(Context context) {
        if (context != null) {
            this.f15082h = context.getApplicationContext();
        }
        g();
    }

    public static c f() {
        return f15079f;
    }

    private void g() {
        try {
            SharedPreferences h2 = h();
            this.f15083j = h2.getInt(f15075b, 0);
            this.k = h2.getLong(f15076c, 0L);
            this.l = h2.getInt(f15077d, 0);
            this.m = h2.getInt(f15078e, 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() <= 0) {
                    return;
                }
                int optInt = jSONObject.optInt(f15075b, 0);
                long optLong = jSONObject.optLong(f15076c, 0L);
                int optInt2 = jSONObject.optInt(f15077d, 0);
                int optInt3 = jSONObject.optInt(f15078e, 0);
                SharedPreferences.Editor edit = h().edit();
                if (optInt != this.f15083j) {
                    edit.putInt(f15075b, optInt);
                }
                if (optLong != this.k) {
                    edit.putLong(f15076c, optLong);
                }
                if (optInt2 != this.l) {
                    edit.putInt(f15077d, optInt2);
                }
                if (optInt3 != this.m) {
                    edit.putInt(f15078e, optInt3);
                }
                edit.apply();
                this.f15083j = optInt;
                this.k = optLong;
                this.l = optInt2;
                this.m = optInt3;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private SharedPreferences h() {
        if (f15081i == null) {
            f15081i = this.f15082h.getSharedPreferences(f15074a, 0);
        }
        return f15081i;
    }

    @Override // com.bytedance.ttnet.config.b
    public int a() {
        return this.f15083j;
    }

    @Override // com.bytedance.ttnet.config.b
    public long b() {
        return this.k;
    }

    @Override // com.bytedance.ttnet.config.b
    public int c() {
        return this.l;
    }

    @Override // com.bytedance.ttnet.config.b
    public int d() {
        return this.m;
    }

    @Override // com.bytedance.ttnet.config.b
    public l.b e() {
        return l.getNetworkType(this.f15082h);
    }
}
