package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appsflyer.AFLogger;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class AFh1dSDK extends AFh1cSDK {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFh1dSDK(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "");
    }

    @Override // com.appsflyer.internal.AFh1cSDK
    public final boolean AFInAppEventType() {
        boolean z;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkNotNullExpressionValue(networkInterfaces, "");
            ArrayList list = Collections.list(networkInterfaces);
            Intrinsics.checkNotNullExpressionValue(list, "");
            ArrayList<NetworkInterface> arrayList = list;
            if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
                for (NetworkInterface networkInterface : arrayList) {
                    if (networkInterface.isUp() && Intrinsics.areEqual(networkInterface.getName(), "tun0")) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            AFLogger.afErrorLog("Failed collecting ivc data", e2);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFh1cSDK
    protected final String values() {
        ConnectivityManager connectivityManager = this.AFInAppEventType;
        if (connectivityManager != null) {
            if (valueOf(connectivityManager.getNetworkInfo(1))) {
                return "WIFI";
            }
            if (valueOf(connectivityManager.getNetworkInfo(0))) {
                return "MOBILE";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                return type != 0 ? type != 1 ? "unknown" : "WIFI" : "MOBILE";
            }
        }
        return "unknown";
    }
}
