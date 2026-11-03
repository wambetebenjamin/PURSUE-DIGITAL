package com.bytedance.applog.log;

import android.util.Log;
import java.util.List;
/* compiled from: NativeLoggerImpl.java */
/* loaded from: classes.dex */
public class p extends o {

    /* renamed from: a  reason: collision with root package name */
    private String f2795a;

    public p() {
        this.f2795a = "NativeLoggerImpl";
    }

    public p(String str) {
        this.f2795a = "NativeLoggerImpl";
        this.f2795a = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.log.b
    public void a(int i2, int i3, List<String> list, Throwable th, String str, Object... objArr) {
        j a2 = j.a().b(i2).a(i3).b(Thread.currentThread().getName()).a(th).a(a(list)).c(a(str, objArr)).a();
        int e2 = a2.e();
        if (e2 == 0) {
            Log.v(this.f2795a, a2.o(), a2.i());
        } else if (e2 == 2) {
            Log.i(this.f2795a, a2.o(), a2.i());
        } else if (e2 == 3) {
            Log.w(this.f2795a, a2.o(), a2.i());
        } else if (e2 == 4 || e2 == 5) {
            Log.e(this.f2795a, a2.o(), a2.i());
        } else {
            Log.d(this.f2795a, a2.o(), a2.i());
        }
    }
}
