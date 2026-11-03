package com.bytedance.pipo.iap.common.ability.monitor;

import android.os.SystemClock;
import com.bytedance.pipo.iap.model.IapPaymentMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChannelMonitor.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\bH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/monitor/ChannelMonitor;", "Lcom/bytedance/pipo/payment/common/lib/BaseJson;", "channel", "Lcom/bytedance/pipo/iap/model/IapPaymentMethod;", "(Lcom/bytedance/pipo/iap/model/IapPaymentMethod;)V", "getChannel", "()Lcom/bytedance/pipo/iap/model/IapPaymentMethod;", "mBeginMills", "", "tag", "", "kotlin.jvm.PlatformType", "beginMonitorChannelInitStart", "", "getPayDuration", "Companion", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class a extends com.bytedance.pipo.payment.common.lib.a {

    /* renamed from: a  reason: collision with root package name */
    public static final C0152a f10645a = new C0152a(null);

    /* renamed from: e  reason: collision with root package name */
    private static final String f10646e = "pipo_channel_init";

    /* renamed from: b  reason: collision with root package name */
    private final String f10647b;

    /* renamed from: c  reason: collision with root package name */
    private long f10648c;

    /* renamed from: d  reason: collision with root package name */
    private final IapPaymentMethod f10649d;

    public a(IapPaymentMethod channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        this.f10649d = channel;
        this.f10647b = "a";
    }

    public final IapPaymentMethod b() {
        return this.f10649d;
    }

    /* compiled from: ChannelMonitor.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/monitor/ChannelMonitor$Companion;", "", "()V", "CHANNEL_INIT", "", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.bytedance.pipo.iap.common.ability.monitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C0152a {
        public /* synthetic */ C0152a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C0152a() {
        }
    }

    public final void a() {
        this.f10648c = SystemClock.uptimeMillis();
    }

    private final long c() {
        long uptimeMillis = this.f10648c > 0 ? SystemClock.uptimeMillis() - this.f10648c : 0L;
        this.f10648c = 0L;
        return uptimeMillis;
    }
}
