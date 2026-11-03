package com.bytedance.bdturing.verify.request;

import com.bytedance.bdturing.c;
import com.bytedance.bdturing.utils.e;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: InfoVerifyRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lcom/bytedance/bdturing/verify/request/InfoVerifyRequest;", "Lcom/bytedance/bdturing/verify/request/AbstractRequest;", "()V", "buildUrl", "", "queryBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEventLimits", "", "getServiceType", "", "getType", "turing_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class h extends a {
    @Override // com.bytedance.bdturing.verify.request.a
    public int h() {
        return 10;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public String i() {
        return com.bytedance.bdturing.setting.g.m;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public int j() {
        return c.DEFAULT_EVENT_COUNT;
    }

    @Override // com.bytedance.bdturing.verify.request.a
    public void a(StringBuilder queryBuilder) {
        Intrinsics.checkNotNullParameter(queryBuilder, "queryBuilder");
        e.a(queryBuilder, "decision_config", "block-info_verify");
        e.a(queryBuilder, "use_turing_bridge", 1);
    }
}
