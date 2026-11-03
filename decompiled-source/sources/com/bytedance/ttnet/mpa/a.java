package com.bytedance.ttnet.mpa;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.reflect.Reflect;
import com.bytedance.frameworks.baselib.network.http.cronet.mpa.a;
import java.util.List;
/* compiled from: TTNetMpaService.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15343a = "TTNetMpaService";

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f15344b = true;

    /* renamed from: c  reason: collision with root package name */
    private static final String f15345c = "org.chromium.mpa.CronetMpaServiceImpl";

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.frameworks.baselib.network.http.cronet.mpa.a f15346d;

    /* compiled from: TTNetMpaService.java */
    /* loaded from: classes8.dex */
    public enum b {
        SYMBOL,
        BYTE,
        SHADOW
    }

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    /* compiled from: TTNetMpaService.java */
    /* renamed from: com.bytedance.ttnet.mpa.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    private static class C0245a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f15348a = new a(null);

        private C0245a() {
        }
    }

    private a() {
    }

    public static a a() {
        return C0245a.f15348a;
    }

    public void a(a.InterfaceC0111a interfaceC0111a) {
        a(interfaceC0111a, b.SYMBOL);
    }

    public void a(a.InterfaceC0111a interfaceC0111a, b bVar) {
        if (!f15344b && interfaceC0111a == null) {
            throw new AssertionError();
        }
        if (!b()) {
            interfaceC0111a.a(false, "Load CronetMpaServiceImpl Failed");
            return;
        }
        int i2 = -1;
        int i3 = AnonymousClass1.f15347a[bVar.ordinal()];
        if (i3 == 1) {
            try {
                i2 = ((Integer) Reflect.on(Class.forName("com.bytedance.android.bytehook.ByteHook").newInstance()).call("init").get()).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (i3 == 2) {
            try {
                i2 = ((Integer) Reflect.on(Class.forName("com.bytedance.shadowhook.ShadowHook").newInstance()).call("init").get()).intValue();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        } else if (i3 == 3) {
            i2 = 0;
        }
        if (i2 != 0) {
            interfaceC0111a.a(false, "Hook Init Error " + i2);
        } else {
            this.f15346d.init(interfaceC0111a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TTNetMpaService.java */
    /* renamed from: com.bytedance.ttnet.mpa.a$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f15347a;

        static {
            int[] iArr = new int[b.values().length];
            f15347a = iArr;
            try {
                iArr[b.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15347a[b.SHADOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15347a[b.SYMBOL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void a(List<String> list, a.InterfaceC0111a interfaceC0111a) {
        if (list == null || list.isEmpty()) {
            if (interfaceC0111a != null) {
                interfaceC0111a.a(false, "Address error");
                return;
            }
            return;
        }
        com.bytedance.frameworks.baselib.network.http.cronet.mpa.a aVar = this.f15346d;
        if (aVar != null) {
            aVar.setAccAddress(list, interfaceC0111a);
        } else if (interfaceC0111a != null) {
            interfaceC0111a.a(false, "MpsService is null");
        }
    }

    private boolean b() {
        if (this.f15346d != null) {
            return true;
        }
        try {
            this.f15346d = (com.bytedance.frameworks.baselib.network.http.cronet.mpa.a) Class.forName(f15345c).newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f15346d != null;
    }

    public void a(String str) {
        com.bytedance.frameworks.baselib.network.http.cronet.mpa.a aVar = this.f15346d;
        if (aVar != null) {
            aVar.start();
            a("begin_user_log", str);
        }
    }

    public void b(String str) {
        if (this.f15346d != null) {
            a("end_user_log", str);
            this.f15346d.stop();
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Logger.e(f15343a, "error command:" + str + " extraMessage:" + str2);
            return;
        }
        com.bytedance.frameworks.baselib.network.http.cronet.mpa.a aVar = this.f15346d;
        if (aVar != null) {
            aVar.command(str, str2);
        }
    }
}
