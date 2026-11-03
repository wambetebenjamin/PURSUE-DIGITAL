package com.bytedance.bdturing.verify.request;

import android.text.TextUtils;
import com.bytedance.bdturing.c;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UpSmsRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/bytedance/bdturing/verify/request/UpSmsRequest;", "Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "verifyTicket", "", "channelMobile", "smsContent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannelMobile", "()Ljava/lang/String;", "setChannelMobile", "(Ljava/lang/String;)V", "getSmsContent", "setSmsContent", "getVerifyTicket", "setVerifyTicket", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "", "getServiceType", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class n extends a {

    /* renamed from: a  reason: collision with root package name */
    private String f4395a;

    /* renamed from: b  reason: collision with root package name */
    private String f4396b;

    /* renamed from: c  reason: collision with root package name */
    private String f4397c;

    public n() {
        this(null, null, null, 7, null);
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int h() {
        return 6;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public String i() {
        return com.bytedance.bdturing.setting.g.m;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int j() {
        return c.DEFAULT_EVENT_COUNT;
    }

    public n(String verifyTicket, String channelMobile, String smsContent) {
        Intrinsics.checkNotNullParameter(verifyTicket, "verifyTicket");
        Intrinsics.checkNotNullParameter(channelMobile, "channelMobile");
        Intrinsics.checkNotNullParameter(smsContent, "smsContent");
        this.f4395a = verifyTicket;
        this.f4396b = channelMobile;
        this.f4397c = smsContent;
    }

    public /* synthetic */ n(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3);
    }

    public final void b(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4395a = str;
    }

    public final void c(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4396b = str;
    }

    public final void d(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4397c = str;
    }

    public final String l() {
        return this.f4395a;
    }

    public final String m() {
        return this.f4396b;
    }

    public final String n() {
        return this.f4397c;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public void a(StringBuilder queryBuilder) {
        Intrinsics.checkNotNullParameter(queryBuilder, "queryBuilder");
        e.a(queryBuilder, "decision_config", "block-upsms");
        e.a(queryBuilder, "is_turing", 1);
        if (!TextUtils.isEmpty(this.f4395a)) {
            e.a(queryBuilder, "verify_ticket", this.f4395a);
        }
        e.a(queryBuilder, "channel_mobile", this.f4396b);
        e.a(queryBuilder, "sms_content", this.f4397c);
        e.a(queryBuilder, "use_turing_bridge", 1);
    }
}
