package com.bytedance.sdk.account.bus;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AccountSdkServiceBus.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/bytedance/sdk/account/bus/AccountSdkServiceBus;", "", "()V", "KEY_ACCOUNT_SDK_CORE", "", "infoService", "Ljava/util/HashMap;", "Lcom/bytedance/sdk/account/bus/IAccountSdkInfoService;", "Lkotlin/collections/HashMap;", "getInfoService", "key", "registerInfoService", "", "service", "account-sdk-bus_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12316a = "account-sdk-core";

    /* renamed from: b  reason: collision with root package name */
    public static final c f12317b = new c();

    /* renamed from: c  reason: collision with root package name */
    private static final HashMap<String, e> f12318c = new HashMap<>();

    private c() {
    }

    public final void a(String key, e service) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(service, "service");
        if (key.length() == 0) {
            return;
        }
        f12318c.put(key, service);
    }

    public final e a(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return f12318c.get(key);
    }
}
