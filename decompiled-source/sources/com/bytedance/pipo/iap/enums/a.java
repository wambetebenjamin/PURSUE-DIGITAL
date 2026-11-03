package com.bytedance.pipo.iap.enums;

import com.facebook.internal.AnalyticsEvents;
import com.google.common.net.HttpHeaders;
import kotlin.Metadata;
/* compiled from: ExtraScene.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/bytedance/pipo/iap/enums/ExtraScene;", "", "index", "", "(Ljava/lang/String;II)V", "getIndex", "()I", "toEventString", "", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN, HttpHeaders.HOST, "Init", "Periodical", "UnFinish", "TimeOut", "OnPurchaseUpdate", "payment_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public enum a {
    Unknown(-1),
    Host(0),
    Init(1),
    Periodical(2),
    UnFinish(3),
    TimeOut(4),
    OnPurchaseUpdate(5);
    

    /* renamed from: i  reason: collision with root package name */
    private final int f10699i;

    a(int i2) {
        this.f10699i = i2;
    }

    public final int b() {
        return this.f10699i;
    }

    public final String a() {
        switch (b.f10700a[ordinal()]) {
            case 1:
                return "client_call_for_replenishment_interface";
            case 2:
                return "client_startup";
            case 3:
                return "client_pay";
            case 4:
                return "client_periodical";
            case 5:
                return "client_channel_callback_time_out";
            case 6:
                return "client_on_purchase_update";
            default:
                return "";
        }
    }
}
