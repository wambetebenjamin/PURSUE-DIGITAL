package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import com.bytedance.common.utility.Logger;
import org.json.JSONObject;
/* compiled from: DispatchStrategy.java */
/* loaded from: classes2.dex */
public abstract class f {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8191a = "f";

    /* renamed from: b  reason: collision with root package name */
    private a f8192b;

    /* compiled from: DispatchStrategy.java */
    /* loaded from: classes2.dex */
    public enum a {
        UNKNOWN_DISPATCH_STRATEGY,
        STATIC_DISPATCH_STRATEGY,
        WRR_DISPATCH_STRATEGY,
        CONSERVATIVE_DISPATCH_STRATEGY,
        OPTIMIZED_DISPATCH_STRATEGY,
        ROUTE_SELECTION_DISPATCH_STRATEGY,
        REQUEST_HEADER_DISPATCH_STRATEGY,
        DISPATCH_STRATEGY_SUPPORTED_LAST
    }

    public abstract String a(Uri uri);

    public abstract void a(l lVar);

    public abstract void a(String str, boolean z, int i2);

    public abstract boolean a();

    public f(a aVar) {
        this.f8192b = aVar;
    }

    public a b() {
        return this.f8192b;
    }

    /* compiled from: DispatchStrategy.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8193a;

        static {
            int[] iArr = new int[a.values().length];
            f8193a = iArr;
            try {
                iArr[a.STATIC_DISPATCH_STRATEGY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8193a[a.REQUEST_HEADER_DISPATCH_STRATEGY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static f a(a aVar, JSONObject jSONObject, String str, long j2, int i2) {
        int i3 = AnonymousClass1.f8193a[aVar.ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                return new m(jSONObject);
            }
            if (Logger.debug()) {
                Logger.d(f8191a, "dispatch strategy " + aVar + " is not supported, fallback to default strategy");
            }
            return new b();
        }
        return new h(jSONObject);
    }
}
