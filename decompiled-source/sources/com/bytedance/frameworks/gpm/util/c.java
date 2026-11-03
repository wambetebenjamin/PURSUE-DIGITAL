package com.bytedance.frameworks.gpm.util;

import android.text.TextUtils;
import java.io.Closeable;
/* compiled from: CommonUtil.java */
/* loaded from: classes2.dex */
class c {
    c() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= str.length()) {
                i3 = 0;
                i2 = 0;
                break;
            } else if (Character.isDigit(str.charAt(i3))) {
                i2 = i3 + 1;
                while (i2 < str.length() && Character.isDigit(str.charAt(i2))) {
                    i2++;
                }
            } else {
                i3++;
            }
        }
        try {
            return Integer.parseInt(str.substring(i3, i2));
        } catch (Exception unused) {
            return 0;
        }
    }
}
