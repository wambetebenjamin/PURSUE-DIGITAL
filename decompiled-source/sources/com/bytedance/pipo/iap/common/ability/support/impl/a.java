package com.bytedance.pipo.iap.common.ability.support.impl;

import com.bytedance.pipo.iap.common.ability.model.OrderData;
import org.json.JSONObject;
/* compiled from: IOrderCacheHelper.java */
/* loaded from: classes2.dex */
public interface a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10665a = 100;

    OrderData a(String str);

    void a(OrderData orderData);

    void a(JSONObject jSONObject);

    boolean b(OrderData orderData);
}
