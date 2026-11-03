package com.bytedance.pipo.iap.common.ability.support.service;

import android.text.TextUtils;
import com.bytedance.pipo.iap.common.ability.model.api.entity.BaseResponse;
import com.bytedance.pipo.iap.common.ability.model.api.entity.CloseTradeData;
import com.bytedance.pipo.iap.common.ability.model.api.entity.CloseTradeRequest;
import com.bytedance.pipo.iap.common.ability.model.api.entity.DynamicErrorsData;
import com.bytedance.pipo.iap.common.ability.model.api.entity.ValidateReceiptData;
import com.bytedance.pipo.iap.common.ability.model.api.entity.ValidateReceiptRequest;
import com.bytedance.pipo.payment.common.lib.callback.d;
import java.util.Map;
/* compiled from: IapHttpService.java */
/* loaded from: classes2.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private final String f10680a = "b";

    /* compiled from: IapHttpService.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final String f10681a = "/pipo/trade/subscription/v3/report_device_info";

        /* renamed from: b  reason: collision with root package name */
        public static final String f10682b = "/pipo/trade/subscription/v3/close_trade";

        /* renamed from: c  reason: collision with root package name */
        public static final String f10683c = "/pipo/trade/subscription/v3/validate_receipt";

        /* renamed from: d  reason: collision with root package name */
        public static final String f10684d = "/pipo/trade/subscription/v3/query_errors_map";
    }

    public abstract void a(String str, CloseTradeRequest closeTradeRequest, String str2, com.bytedance.pipo.payment.common.lib.callback.a<CloseTradeData> aVar);

    public abstract void a(String str, ValidateReceiptRequest validateReceiptRequest, com.bytedance.pipo.payment.common.lib.callback.a<BaseResponse<ValidateReceiptData>> aVar);

    public abstract void a(String str, com.bytedance.pipo.payment.common.lib.callback.a<BaseResponse<DynamicErrorsData>> aVar);

    public abstract void a(String str, String str2);

    private String a() {
        com.bytedance.pipo.payment.common.lib.configuration.c cVar = com.bytedance.pipo.payment.common.lib.support.a.a().h().c().f10873d;
        if (cVar != null && !TextUtils.isEmpty(cVar.d())) {
            return cVar.d();
        }
        String f2 = com.bytedance.pipo.payment.common.lib.support.a.a().g().c().f();
        if (!TextUtils.isEmpty(f2)) {
            com.bytedance.pipo.payment.common.lib.support.a.a().e().b(this.f10680a, "getHost from cache return :" + f2);
            return f2;
        }
        com.bytedance.pipo.payment.common.lib.support.a.a().e().b(this.f10680a, "getHost return :" + com.bytedance.pipo.payment.common.lib.support.a.a().f().a());
        return com.bytedance.pipo.payment.common.lib.support.a.a().f().a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return a() + a.f10682b;
        }
        return str + a.f10682b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return a() + a.f10683c;
        }
        return str + a.f10683c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b() {
        return a() + a.f10681a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String c() {
        return a() + a.f10684d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, Map<String, String> map, d dVar) {
        com.bytedance.pipo.payment.common.lib.support.a.a().f().a(str, map, dVar);
    }
}
