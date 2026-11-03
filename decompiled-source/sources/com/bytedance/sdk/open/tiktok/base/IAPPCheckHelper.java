package com.bytedance.sdk.open.tiktok.base;
/* loaded from: classes3.dex */
public interface IAPPCheckHelper {
    String getPackageName();

    String getRemoteAuthEntryActivity();

    boolean isAppInstalled();

    boolean isAppSupportAPI(int i2);

    boolean isAppSupportAuthorization();

    boolean isAppSupportShare();

    boolean isShareSupportFileProvider();
}
