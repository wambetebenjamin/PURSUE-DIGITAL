package com.bytedance.sdk.account.bus.util;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
/* compiled from: AccountBusUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\n"}, d2 = {"Lcom/bytedance/sdk/account/bus/util/AccountBusUtils;", "", "()V", "compareVersion", "", "versionName", "", "versionCode", "otherVersionName", "otherVersionCode", "account-sdk-bus_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f12332a = new a();

    private a() {
    }

    @JvmStatic
    public static final int a(String str, String str2) {
        return a(str, 0, str2, 0);
    }

    @JvmStatic
    public static final int a(String str, int i2, String str2, int i3) {
        return new b(str, i2).compareTo(new b(str2, i3));
    }
}
