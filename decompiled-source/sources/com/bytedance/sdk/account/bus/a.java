package com.bytedance.sdk.account.bus;

import android.os.Bundle;
import gsdk.impl.main.DEFAULT.g;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AbsAccountSdkInfoService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/bytedance/sdk/account/bus/AbsAccountSdkInfoService;", "Lcom/bytedance/sdk/account/bus/IAccountSdkInfoService;", "()V", g.b.f20441a, "Landroid/os/Bundle;", "getVersionCode", "", "getVersionName", "", "isLargeThanTargetVersion", "", "targetVersion", "account-sdk-bus_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public class a implements e {
    @Override // com.bytedance.sdk.account.bus.e
    public int a() {
        return 0;
    }

    @Override // com.bytedance.sdk.account.bus.e
    public boolean a(String targetVersion) {
        Intrinsics.checkParameterIsNotNull(targetVersion, "targetVersion");
        return false;
    }

    @Override // com.bytedance.sdk.account.bus.e
    public String b() {
        return "";
    }

    @Override // com.bytedance.sdk.account.bus.e
    public Bundle c() {
        return new Bundle();
    }
}
