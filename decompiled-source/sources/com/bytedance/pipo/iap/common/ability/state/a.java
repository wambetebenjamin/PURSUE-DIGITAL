package com.bytedance.pipo.iap.common.ability.state;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.iap.common.ability.enums.PayState;
import com.bytedance.pipo.iap.common.ability.model.OrderData;
import com.bytedance.pipo.iap.common.ability.monitor.d;
import com.bytedance.pipo.iap.common.ability.support.service.c;
/* compiled from: BaseState.java */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    protected OrderData f10662a;

    /* renamed from: b  reason: collision with root package name */
    protected c f10663b;

    /* renamed from: c  reason: collision with root package name */
    private final String f10664c = "a";

    public abstract PayState c();

    public a(c cVar) {
        this.f10663b = cVar;
    }

    public void a(OrderData orderData) {
        this.f10662a = orderData;
    }

    public final void a(e eVar) {
        if (this.f10662a.isValidateAsync() && (this.f10662a.getPayState() == PayState.ValidateReceipt || this.f10662a.getPayState() == PayState.ExtraValidateReceipt || this.f10662a.getPayState() == PayState.Consume)) {
            return;
        }
        this.f10662a.finish();
        b(eVar);
        this.f10663b.onIapOrderFinished(this.f10662a);
    }

    private void b(e eVar) {
        d iapPayMonitor = this.f10662a.getIapPayMonitor();
        if (iapPayMonitor != null) {
            iapPayMonitor.a(eVar, c());
        }
    }

    public OrderData a() {
        return this.f10662a;
    }
}
