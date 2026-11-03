package com.ss.android.deviceregister;

import android.content.Context;
/* loaded from: classes9.dex */
public class DeviceRegisterInitiator {
    public static void init(Context context, boolean z, com.ss.android.deviceregister.base.d dVar, boolean z2, boolean z3, boolean z4, boolean z5) {
        DeviceRegisterManager.setForbidReportPhoneDetailInfo(z);
        DeviceRegisterManager.setILogDepend(dVar);
        DeviceRegisterManager.setAnonymous(z2);
        DeviceRegisterManager.setInitWithActivity(z3);
        DeviceRegisterManager.init(context, z4, z5);
    }
}
