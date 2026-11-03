package com.bytedance.crash.general;

import android.content.Context;
import com.bytedance.crash.Global;
import com.bytedance.crash.util.k;
import java.io.File;
import org.json.JSONObject;
/* compiled from: GeneralInfoManager.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6755a = "general";

    /* renamed from: b  reason: collision with root package name */
    private static a f6756b;

    /* renamed from: c  reason: collision with root package name */
    private final File f6757c;

    /* renamed from: d  reason: collision with root package name */
    private final Context f6758d;

    /* renamed from: e  reason: collision with root package name */
    private AppInfo f6759e;

    /* renamed from: f  reason: collision with root package name */
    private RomInfo f6760f;

    /* renamed from: g  reason: collision with root package name */
    private HardwareInfo f6761g;

    public static void a() {
        a f2 = f();
        if (f2 != null) {
            f2.i();
        }
    }

    private static a f() {
        if (f6756b == null) {
            synchronized (a.class) {
                if (f6756b == null) {
                    Context c2 = Global.c();
                    File e2 = Global.e();
                    if (c2 != null && e2 != null) {
                        f6756b = new a(c2, e2);
                    }
                }
            }
        }
        return f6756b;
    }

    private a(Context context, File file) {
        this.f6757c = k.a(file, "general");
        this.f6758d = context;
    }

    public File b() {
        return this.f6757c;
    }

    public static long c() {
        a f2 = f();
        if (f2 != null) {
            return f2.g().getJiffy();
        }
        return -1L;
    }

    private HardwareInfo g() {
        if (this.f6761g == null) {
            synchronized (this) {
                if (this.f6761g == null) {
                    this.f6761g = HardwareInfo.get(this.f6758d, this.f6757c);
                }
            }
        }
        return this.f6761g;
    }

    private RomInfo h() {
        if (this.f6760f == null) {
            synchronized (this) {
                if (this.f6760f == null) {
                    this.f6760f = RomInfo.get(this.f6757c);
                }
            }
        }
        return this.f6760f;
    }

    private AppInfo i() {
        if (this.f6759e == null) {
            synchronized (this) {
                if (this.f6759e == null) {
                    this.f6759e = AppInfo.get(this.f6758d, this.f6757c);
                }
            }
        }
        return this.f6759e;
    }

    public static void a(JSONObject jSONObject) {
        a f2 = f();
        if (f2 != null) {
            f2.g().putTo(jSONObject);
        }
    }

    public static void b(JSONObject jSONObject) {
        a f2 = f();
        if (f2 != null) {
            f2.h().putTo(jSONObject);
        }
    }

    public static void a(JSONObject jSONObject, long j2) {
        AppInfo findAppInfo;
        a f2 = f();
        if (f2 != null) {
            AppInfo i2 = f2.i();
            if (j2 > 0 && j2 < i2.mLastUpdateTime && (findAppInfo = AppInfo.findAppInfo(f2.f6757c, j2)) != null) {
                i2 = findAppInfo;
            }
            i2.putTo(jSONObject);
        }
    }

    public static long a(long j2) {
        a f2 = f();
        if (f2 != null) {
            if (j2 == 0) {
                return f2.i().mLastUpdateTime;
            }
            AppInfo findAppInfo = AppInfo.findAppInfo(f2.f6757c, j2);
            if (findAppInfo != null) {
                return findAppInfo.mLastUpdateTime;
            }
        }
        return 0L;
    }

    public static boolean d() {
        return c.a();
    }

    public static String e() {
        a f2 = f();
        if (f2 != null) {
            return f2.h().getRandomDeviceId();
        }
        return c.d();
    }
}
