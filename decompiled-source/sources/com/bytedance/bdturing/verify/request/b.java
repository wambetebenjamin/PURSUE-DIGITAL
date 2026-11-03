package com.bytedance.bdturing.verify.request;

import android.text.TextUtils;
import com.bytedance.bdturing.c;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DownSmsRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/bytedance/bdturing/verify/request/DownSmsRequest;", "Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "verifyTicket", "", "mobile", "(Ljava/lang/String;Ljava/lang/String;)V", "getMobile", "()Ljava/lang/String;", "setMobile", "(Ljava/lang/String;)V", "getVerifyTicket", "setVerifyTicket", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "", "getServiceType", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private String f4386a;

    /* renamed from: b  reason: collision with root package name */
    private String f4387b;

    public b() {
        this(null, null, 3, null);
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int h() {
        return 7;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public String i() {
        return com.bytedance.bdturing.setting.g.m;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int j() {
        return c.DEFAULT_EVENT_COUNT;
    }

    public b(String str, String str2) {
        this.f4386a = str;
        this.f4387b = str2;
    }

    public /* synthetic */ b(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }

    public final void b(String str) {
        this.f4386a = str;
    }

    public final void c(String str) {
        this.f4387b = str;
    }

    public final String l() {
        return this.f4386a;
    }

    public final String m() {
        return this.f4387b;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public void a(StringBuilder queryBuilder) {
        Intrinsics.checkNotNullParameter(queryBuilder, "queryBuilder");
        e.a(queryBuilder, "decision_config", "block-sms");
        e.a(queryBuilder, "is_turing", 1);
        if (!TextUtils.isEmpty(this.f4386a)) {
            e.a(queryBuilder, "verify_ticket", this.f4386a);
        }
        if (!TextUtils.isEmpty(this.f4387b)) {
            e.a(queryBuilder, "show_mobile", this.f4387b);
        }
        e.a(queryBuilder, "use_turing_bridge", 1);
    }
}
