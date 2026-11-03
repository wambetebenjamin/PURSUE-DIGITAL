package com.google.android.gms.internal.games_v2;

import android.os.IBinder;
import android.os.IInterface;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public abstract class zzak extends zzb implements zzal {
    public static zzal zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.recall.IRecallService");
        return queryLocalInterface instanceof zzal ? (zzal) queryLocalInterface : new zzaj(iBinder);
    }
}
