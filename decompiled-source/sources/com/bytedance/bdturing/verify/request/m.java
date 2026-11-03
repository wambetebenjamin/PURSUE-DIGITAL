package com.bytedance.bdturing.verify.request;

import com.bytedance.bdturing.c;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SmsRequest.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0014\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/bytedance/bdturing/verify/request/SmsRequest;", "Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "scene", "", "showToastSuccess", "", "(Ljava/lang/String;Z)V", "uid", "(Ljava/lang/String;ZLjava/lang/String;)V", "getScene", "()Ljava/lang/String;", "setScene", "(Ljava/lang/String;)V", "getShowToastSuccess", "()Z", "setShowToastSuccess", "(Z)V", "getUid", "setUid", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "", "getServiceType", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class m extends a {

    /* renamed from: a  reason: collision with root package name */
    private String f4392a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f4393b;

    /* renamed from: c  reason: collision with root package name */
    private String f4394c;

    public m() {
        this(null, false, null, 7, null);
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int h() {
        return 1;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public String i() {
        return "sms";
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int j() {
        return c.DEFAULT_EVENT_COUNT;
    }

    public /* synthetic */ m(String str, boolean z, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? null : str2);
    }

    public final void b(String str) {
        this.f4392a = str;
    }

    public final String l() {
        return this.f4392a;
    }

    public final void f(boolean z) {
        this.f4393b = z;
    }

    public final boolean m() {
        return this.f4393b;
    }

    public final void c(String str) {
        this.f4394c = str;
    }

    public final String n() {
        return this.f4394c;
    }

    public m(String str, boolean z, String str2) {
        this.f4392a = str;
        this.f4393b = z;
        this.f4394c = str2;
        a(true);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public m(String scene, boolean z) {
        this(null, false, null, 7, null);
        Intrinsics.checkNotNullParameter(scene, "scene");
        this.f4392a = scene;
        this.f4393b = z;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public void a(StringBuilder queryBuilder) {
        Intrinsics.checkNotNullParameter(queryBuilder, "queryBuilder");
        e.a(queryBuilder, "scene", this.f4392a);
        e.a(queryBuilder, "show_success_toast", this.f4393b ? 1 : 0);
        e.a(queryBuilder, "uid", this.f4394c);
    }
}
