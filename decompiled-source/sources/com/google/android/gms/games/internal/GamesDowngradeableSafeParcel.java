package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;
import com.google.android.gms.common.util.GmsVersion;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class GamesDowngradeableSafeParcel extends DowngradeableSafeParcel {
    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean zzp(Integer num) {
        if (num == null) {
            return false;
        }
        return GmsVersion.isAtLeastFenacho(num.intValue());
    }

    @Override // com.google.android.gms.common.internal.DowngradeableSafeParcel
    public final boolean prepareForClientVersion(int i2) {
        setShouldDowngrade(!zzp(Integer.valueOf(i2)));
        return true;
    }
}
