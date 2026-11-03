package com.bytedance.ttgame.main.internal.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.ttnet.org.chromium.net.NetError;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SignalStrengthManager.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/main/internal/device/SignalStrengthManager;", "", "()V", "phoneSignal", "", "registered", "", "calculatePhoneSignal", "", "signalStrength", "Landroid/telephony/SignalStrength;", "getCellLevelFromDbm", "dbm", "getRssiDbmFromAsu", "asu", "getSignalStrength", "context", "Landroid/content/Context;", "getWifiLevel", "isWifi", "registerSignalStrengthsListener", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SignalStrengthManager {
    public static final SignalStrengthManager INSTANCE = new SignalStrengthManager();
    private static int phoneSignal = -1;
    private static boolean registered;

    private final int getCellLevelFromDbm(int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return -1;
        }
        if (i2 >= -75) {
            return 4;
        }
        if (i2 >= -85) {
            return 3;
        }
        if (i2 >= -95) {
            return 2;
        }
        return i2 >= -100 ? 1 : 0;
    }

    private SignalStrengthManager() {
    }

    public final int getSignalStrength(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        registerSignalStrengthsListener(context);
        if (isWifi(context)) {
            return getWifiLevel();
        }
        return phoneSignal;
    }

    private final void registerSignalStrengthsListener(Context context) {
        if (registered) {
            return;
        }
        registered = true;
        Object systemService = context.getSystemService("phone");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        if (Build.VERSION.SDK_INT >= 31) {
            telephonyManager.registerTelephonyCallback(context.getMainExecutor(), new SignalStrengthManager$registerSignalStrengthsListener$1());
        } else {
            telephonyManager.listen(new PhoneStateListener() { // from class: com.bytedance.ttgame.main.internal.device.SignalStrengthManager$registerSignalStrengthsListener$2
                @Override // android.telephony.PhoneStateListener
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    SignalStrengthManager.INSTANCE.calculatePhoneSignal(signalStrength);
                }
            }, 256);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calculatePhoneSignal(SignalStrength signalStrength) {
        int i2 = -1;
        if (signalStrength != null) {
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    i2 = signalStrength.getLevel();
                } else {
                    int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                    if (gsmSignalStrength != Integer.MAX_VALUE) {
                        i2 = getCellLevelFromDbm(getRssiDbmFromAsu(gsmSignalStrength));
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        phoneSignal = i2;
    }

    private final int getRssiDbmFromAsu(int i2) {
        if (i2 > 31 || i2 < 0) {
            int i3 = Build.VERSION.SDK_INT;
            return Integer.MAX_VALUE;
        }
        return (i2 * 2) + NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH;
    }

    private final boolean isWifi(Context context) {
        Object systemService = context.getSystemService("connectivity");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    private final int getWifiLevel() {
        IModuleApi service$default = ModuleManager.getService$default(ModuleManager.INSTANCE, ICoreInternalService.class, false, (String) null, 6, (Object) null);
        Intrinsics.checkNotNull(service$default);
        Object systemService = ((ICoreInternalService) service$default).getAppContext().getApplicationContext().getSystemService("wifi");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        return WifiManager.calculateSignalLevel(((WifiManager) systemService).getConnectionInfo().getRssi(), 5);
    }
}
