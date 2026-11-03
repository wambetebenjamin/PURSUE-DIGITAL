package com.bytedance.sdk.account.bus;

import android.os.Bundle;
import gsdk.impl.main.DEFAULT.g;
import kotlin.Metadata;
/* compiled from: IAccountSdkInfoService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H&¨\u0006\f"}, d2 = {"Lcom/bytedance/sdk/account/bus/IAccountSdkInfoService;", "", g.b.f20441a, "Landroid/os/Bundle;", "getVersionCode", "", "getVersionName", "", "isLargeThanTargetVersion", "", "targetVersion", "Companion", "account-sdk-bus_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes2.dex */
public interface e {

    /* renamed from: b  reason: collision with root package name */
    public static final a f12326b = a.f12331c;

    /* renamed from: c  reason: collision with root package name */
    public static final String f12327c = "version_code";

    /* renamed from: d  reason: collision with root package name */
    public static final String f12328d = "version_name";

    int a();

    boolean a(String str);

    String b();

    Bundle c();

    /* compiled from: IAccountSdkInfoService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/sdk/account/bus/IAccountSdkInfoService$Companion;", "", "()V", "VERSION_CODE", "", "VERSION_NAME", "account-sdk-bus_release"}, k = 1, mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final String f12329a = "version_code";

        /* renamed from: b  reason: collision with root package name */
        public static final String f12330b = "version_name";

        /* renamed from: c  reason: collision with root package name */
        static final /* synthetic */ a f12331c = new a();

        private a() {
        }
    }
}
