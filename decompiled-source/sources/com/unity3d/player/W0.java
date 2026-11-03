package com.unity3d.player;
/* loaded from: classes10.dex */
final class W0 {

    /* renamed from: e  reason: collision with root package name */
    private static boolean f18917e = false;

    /* renamed from: a  reason: collision with root package name */
    private boolean f18918a = false;

    /* renamed from: b  reason: collision with root package name */
    private boolean f18919b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18920c = true;

    /* renamed from: d  reason: collision with root package name */
    private boolean f18921d = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d() {
        return f18917e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e() {
        f18917e = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f() {
        f18917e = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a() {
        return this.f18921d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(boolean z) {
        return f18917e && !((!z && !this.f18918a) || this.f18920c || this.f18919b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(boolean z) {
        this.f18918a = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.f18920c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(boolean z) {
        this.f18919b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        return this.f18919b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(boolean z) {
        this.f18921d = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(boolean z) {
        this.f18920c = z;
    }

    public final String toString() {
        return super.toString();
    }
}
