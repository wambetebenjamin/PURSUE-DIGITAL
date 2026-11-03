package com.bytedance.applog.log;

import com.bytedance.bdinstall.ah;
/* compiled from: CustomLogProcessor.java */
/* loaded from: classes.dex */
public class e implements i {

    /* renamed from: a  reason: collision with root package name */
    private final ah f2736a;

    public e(ah ahVar) {
        this.f2736a = ahVar;
    }

    @Override // com.bytedance.applog.log.i
    public void a(j jVar) {
        if (this.f2736a != null) {
            int e2 = jVar.e();
            if (e2 == 1) {
                this.f2736a.d(jVar.o(), jVar.i());
            } else if (e2 == 2) {
                this.f2736a.i(jVar.o(), jVar.i());
            } else if (e2 == 3) {
                this.f2736a.w(jVar.o(), jVar.i());
            } else if (e2 != 4) {
                this.f2736a.v(jVar.o(), jVar.i());
            } else {
                this.f2736a.e(jVar.o(), jVar.i());
            }
        }
    }
}
