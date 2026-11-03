package com.bytedance.notification.helper;

import android.util.Log;
import android.view.View;
import com.bytedance.common.notification.R;
import com.bytedance.notification.activity.BannerActivity;
import java.lang.ref.WeakReference;
/* compiled from: BannerActivityHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static int f9883a = R.anim.banner_out_to_right;

    /* renamed from: b  reason: collision with root package name */
    public static int f9884b = R.anim.banner_out_to_left;

    /* renamed from: c  reason: collision with root package name */
    public static int f9885c = R.anim.banner_out_to_top;

    /* renamed from: j  reason: collision with root package name */
    private static volatile b f9886j;

    /* renamed from: d  reason: collision with root package name */
    private WeakReference<BannerActivity> f9887d;

    /* renamed from: e  reason: collision with root package name */
    private WeakReference<View> f9888e;

    /* renamed from: f  reason: collision with root package name */
    private int f9889f;

    /* renamed from: g  reason: collision with root package name */
    private long f9890g;

    /* renamed from: h  reason: collision with root package name */
    private long f9891h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f9892i = false;

    public static b a() {
        if (f9886j == null) {
            synchronized (b.class) {
                if (f9886j == null) {
                    f9886j = new b();
                }
            }
        }
        return f9886j;
    }

    private b() {
    }

    public WeakReference<BannerActivity> b() {
        Log.d("BannerActivityTag", "getBannerActivityRef ");
        return this.f9887d;
    }

    public b a(WeakReference<BannerActivity> weakReference) {
        Log.d("BannerActivityTag", "setBannerActivityRef:" + weakReference);
        this.f9887d = weakReference;
        return this;
    }

    public WeakReference<View> c() {
        return this.f9888e;
    }

    public b b(WeakReference<View> weakReference) {
        this.f9888e = weakReference;
        return this;
    }

    public int d() {
        return this.f9889f;
    }

    public b a(int i2) {
        this.f9889f = i2;
        return this;
    }

    public long e() {
        return this.f9890g;
    }

    public b a(long j2) {
        this.f9890g = j2;
        return this;
    }

    public long f() {
        return this.f9891h;
    }

    public b b(long j2) {
        this.f9891h = j2;
        return this;
    }

    public boolean g() {
        return this.f9892i;
    }

    public b a(boolean z) {
        this.f9892i = z;
        return this;
    }

    public void h() {
        this.f9892i = false;
        this.f9891h = 0L;
        this.f9890g = 0L;
        this.f9887d = null;
        this.f9888e = null;
    }
}
