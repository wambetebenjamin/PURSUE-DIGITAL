package com.ttnet.org.chromium.net.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.meituan.robust.Constants;
import com.ttnet.org.chromium.net.TTNetVersion;
import java.util.Locale;
/* loaded from: classes9.dex */
public final class UserAgent {
    private static final int VERSION_CODE_UNINITIALIZED = 0;
    private static final Object sLock = new Object();
    private static int sVersionCode;

    private UserAgent() {
    }

    public static String from(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        sb.append('/');
        sb.append(versionFromContext(context));
        sb.append(" (Linux; U; Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("; ");
        sb.append(Locale.getDefault().toString());
        String str = Build.MODEL;
        if (str.length() > 0) {
            sb.append("; ");
            sb.append(str);
        }
        String str2 = Build.ID;
        if (str2.length() > 0) {
            sb.append("; Build/");
            sb.append(str2);
        }
        sb.append(Constants.PACKNAME_END);
        appendCronetVersion(sb);
        sb.append(')');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getQuicUserAgentIdFrom(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getPackageName());
        appendCronetVersion(sb);
        return sb.toString();
    }

    private static int versionFromContext(Context context) {
        int i2;
        synchronized (sLock) {
            if (sVersionCode == 0) {
                try {
                    sVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            i2 = sVersionCode;
        }
        return i2;
    }

    private static void appendCronetVersion(StringBuilder sb) {
        sb.append(" Cronet/");
        sb.append(TTNetVersion.VERSION);
    }
}
