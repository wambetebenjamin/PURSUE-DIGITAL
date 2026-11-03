package com.bytedance.ttgame.main.internal.device;

import android.telephony.SignalStrength;
import android.telephony.TelephonyCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SignalStrengthManager.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/bytedance/ttgame/main/internal/device/SignalStrengthManager$registerSignalStrengthsListener$1", "Landroid/telephony/TelephonyCallback;", "Landroid/telephony/TelephonyCallback$SignalStrengthsListener;", "onSignalStrengthsChanged", "", "signalStrength", "Landroid/telephony/SignalStrength;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SignalStrengthManager$registerSignalStrengthsListener$1 extends TelephonyCallback implements TelephonyCallback.SignalStrengthsListener {
    @Override // android.telephony.TelephonyCallback.SignalStrengthsListener
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        Intrinsics.checkNotNullParameter(signalStrength, "signalStrength");
        SignalStrengthManager.INSTANCE.calculatePhoneSignal(signalStrength);
    }
}
