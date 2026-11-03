package com.bytedance.bdturing.verify.request;

import com.bytedance.bdturing.c;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ImageRequest.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, d2 = {"Lcom/bytedance/bdturing/verify/request/ImageRequest;", "Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "challengeCode", "", "(I)V", "getChallengeCode", "()I", "setChallengeCode", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "getServiceType", "", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class g extends a {

    /* renamed from: a  reason: collision with root package name */
    private int f4388a;

    public g() {
        this(0, 1, null);
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int h() {
        return 2;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public String i() {
        return com.bytedance.bdturing.setting.g.VERIFY_SERVICE;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int j() {
        return c.DEFAULT_EVENT_COUNT;
    }

    public g(int i2) {
        this.f4388a = i2;
    }

    public /* synthetic */ g(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }

    public final void a(int i2) {
        this.f4388a = i2;
    }

    public final int l() {
        return this.f4388a;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public void a(StringBuilder queryBuilder) {
        Intrinsics.checkNotNullParameter(queryBuilder, "queryBuilder");
        int i2 = this.f4388a;
        if (i2 != 0) {
            e.a(queryBuilder, "challenge_code", i2);
        }
    }
}
