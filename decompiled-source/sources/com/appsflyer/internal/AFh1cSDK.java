package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0003\u001a\u00020\u0002H%¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH&¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u000fX\u0085\u0002¢\u0006\u0006\n\u0004\b\r\u0010\u0010R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00118\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013"}, d2 = {"Lcom/appsflyer/internal/AFh1cSDK;", "", "", "values", "()Ljava/lang/String;", "Lcom/appsflyer/internal/AFh1eSDK;", "AFInAppEventParameterName", "()Lcom/appsflyer/internal/AFh1eSDK;", "Landroid/net/NetworkInfo;", "p0", "", "valueOf", "(Landroid/net/NetworkInfo;)Z", "AFInAppEventType", "()Z", "Landroid/net/ConnectivityManager;", "Landroid/net/ConnectivityManager;", "Landroid/telephony/TelephonyManager;", "AFKeystoreWrapper", "Landroid/telephony/TelephonyManager;", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "AFa1tSDK"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AFh1cSDK {
    public static final AFa1tSDK AFa1tSDK = new AFa1tSDK(null);
    ConnectivityManager AFInAppEventType;
    private final TelephonyManager AFKeystoreWrapper;

    public abstract boolean AFInAppEventType();

    protected abstract String values();

    public AFh1cSDK(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        Object systemService = context.getSystemService("connectivity");
        this.AFInAppEventType = systemService instanceof ConnectivityManager ? (ConnectivityManager) systemService : null;
        Object systemService2 = context.getSystemService("phone");
        this.AFKeystoreWrapper = systemService2 instanceof TelephonyManager ? (TelephonyManager) systemService2 : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean valueOf(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            return networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001e A[Catch: all -> 0x0028, TryCatch #1 {all -> 0x0028, blocks: (B:6:0x0009, B:8:0x0012, B:14:0x001e), top: B:28:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.appsflyer.internal.AFh1eSDK AFInAppEventParameterName() {
        /*
            r6 = this;
            r0 = 0
            android.telephony.TelephonyManager r1 = r6.AFKeystoreWrapper     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L2e
            java.lang.String r2 = r1.getSimOperatorName()     // Catch: java.lang.Throwable -> L30
            java.lang.String r0 = r1.getNetworkOperatorName()     // Catch: java.lang.Throwable -> L28
            r3 = r0
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L1b
            int r3 = r3.length()     // Catch: java.lang.Throwable -> L28
            if (r3 != 0) goto L19
            goto L1b
        L19:
            r3 = 0
            goto L1c
        L1b:
            r3 = 1
        L1c:
            if (r3 == 0) goto L3a
            int r1 = r1.getPhoneType()     // Catch: java.lang.Throwable -> L28
            r3 = 2
            if (r1 != r3) goto L3a
            java.lang.String r0 = "CDMA"
            goto L3a
        L28:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r2
            r2 = r5
            goto L33
        L2e:
            r2 = r0
            goto L3a
        L30:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L33:
            java.lang.String r3 = "Exception while collecting network info. "
            com.appsflyer.AFLogger.afErrorLog(r3, r2)
            r2 = r0
            r0 = r1
        L3a:
            com.appsflyer.internal.AFh1eSDK r1 = new com.appsflyer.internal.AFh1eSDK
            java.lang.String r3 = r6.values()
            boolean r4 = r6.AFInAppEventType()
            r1.<init>(r3, r0, r2, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFh1cSDK.AFInAppEventParameterName():com.appsflyer.internal.AFh1eSDK");
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcom/appsflyer/internal/AFh1cSDK$AFa1tSDK;", "", "<init>", "()V"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class AFa1tSDK {
        public /* synthetic */ AFa1tSDK(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private AFa1tSDK() {
        }
    }
}
