package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
import android.os.Build;
/* compiled from: UbsanOptPlugin.java */
/* loaded from: classes2.dex */
public class l extends com.bytedance.platform.godzilla.plugin.a {
    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "UbsanOptPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
    }

    public static String hexStringToString(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String replace = str.replace(" ", "");
        int length = replace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            try {
                bArr[i2] = (byte) (Integer.parseInt(replace.substring(i3, i3 + 2), 16) & 255);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (Exception e3) {
            e3.printStackTrace();
            return replace;
        }
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        com.bytedance.platform.godzilla.common.k.e(getName(), "start");
        String hexStringToString = hexStringToString(Long.toHexString(79531005658441L));
        if (hexStringToString != null && Build.MANUFACTURER.toUpperCase().contains(hexStringToString) && Build.VERSION.SDK_INT == 29) {
            UbsanOpt.start();
        }
    }
}
