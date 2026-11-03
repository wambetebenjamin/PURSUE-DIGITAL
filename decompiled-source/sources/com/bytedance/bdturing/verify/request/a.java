package com.bytedance.bdturing.verify.request;

import android.app.Activity;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AbstractRequest.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\"j\u0002`#H&J\b\u0010$\u001a\u00020%H&J\b\u0010&\u001a\u00020\u001cH&J\b\u0010'\u001a\u00020%H&R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006("}, d2 = {"Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", ReactVideoViewManager.PROP_FULLSCREEN, "", "getFullscreen", "()Z", "setFullscreen", "(Z)V", "loading", "getLoading", "setLoading", "mask", "getMask", "setMask", "maskCancel", "getMaskCancel", "setMaskCancel", "preCreate", "getPreCreate", "setPreCreate", "url", "", "getUrl", "()Ljava/lang/String;", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "", "getServiceType", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private Activity f4380a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f4381b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4382c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f4383d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f4384e = true;

    /* renamed from: f  reason: collision with root package name */
    private boolean f4385f = true;

    public abstract void a(StringBuilder sb);

    public abstract int h();

    public abstract String i();

    public abstract int j();

    public final Activity a() {
        return this.f4380a;
    }

    public final void a(Activity activity) {
        this.f4380a = activity;
    }

    public void a(boolean z) {
        this.f4381b = z;
    }

    public boolean b() {
        return this.f4381b;
    }

    public final void b(boolean z) {
        this.f4382c = z;
    }

    public final boolean c() {
        return this.f4382c;
    }

    public final void c(boolean z) {
        this.f4383d = z;
    }

    public final boolean d() {
        return this.f4383d;
    }

    public final void d(boolean z) {
        this.f4384e = z;
    }

    public final boolean e() {
        return this.f4384e;
    }

    public final void e(boolean z) {
        this.f4385f = z;
    }

    public final boolean f() {
        return this.f4385f;
    }

    public final String k() {
        StringBuilder a2 = com.bytedance.bdturing.domain.b.f4162b.a(h(), i(), this.f4380a);
        if (this.f4382c) {
            e.a(a2, "preload", 1);
        }
        a(a2);
        String sb = a2.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "queryBuilder.toString()");
        return sb;
    }
}
