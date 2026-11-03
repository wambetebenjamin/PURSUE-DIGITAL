package com.bytedance.platform.godzilla.sysopt;

import android.content.Context;
import com.bytedance.sysoptimizer.StackLeakChecker;
/* compiled from: StackLeakCheckPlugin.java */
/* loaded from: classes2.dex */
public class j extends com.bytedance.platform.godzilla.plugin.a {

    /* renamed from: a  reason: collision with root package name */
    private int f11078a;

    /* renamed from: b  reason: collision with root package name */
    private long f11079b;

    /* renamed from: c  reason: collision with root package name */
    private String[] f11080c;

    /* renamed from: d  reason: collision with root package name */
    private StackLeakChecker.StackLeakListener f11081d;

    /* renamed from: e  reason: collision with root package name */
    private Context f11082e;

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "StackCheckPlugin";
    }

    public j(Context context, int i2, long j2, String[] strArr, StackLeakChecker.StackLeakListener stackLeakListener) {
        this.f11078a = 10;
        this.f11079b = 10000000L;
        this.f11082e = context;
        this.f11078a = i2;
        this.f11079b = j2;
        this.f11080c = strArr;
        this.f11081d = stackLeakListener;
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        super.start();
        StackLeakChecker.registerStackLeakListener(this.f11081d);
        StackLeakChecker.setInterval(this.f11078a, this.f11079b);
        StackLeakChecker.enableChecker(this.f11082e, this.f11080c);
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
        super.stop();
        StackLeakChecker.disableChecker();
    }
}
