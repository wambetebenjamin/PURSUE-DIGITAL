package com.bytedance.push.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
/* compiled from: TelephonyUtils.java */
/* loaded from: classes2.dex */
public class w {
    public static boolean a(Context context) {
        int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
        return (simState == 0 || simState == 1) ? false : true;
    }
}
