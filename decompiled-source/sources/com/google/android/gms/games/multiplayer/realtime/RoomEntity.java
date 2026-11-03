package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
@Deprecated
/* loaded from: classes5.dex */
public final class RoomEntity extends GamesDowngradeableSafeParcel {
    public static final Parcelable.Creator<RoomEntity> CREATOR = new zzc();

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (!shouldDowngrade()) {
            SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
            return;
        }
        parcel.writeString("unsupported");
        parcel.writeString("unsupported");
        parcel.writeLong(0L);
        parcel.writeInt(0);
        parcel.writeString("unsupported");
        parcel.writeInt(-1);
        parcel.writeBundle(null);
        parcel.writeInt(0);
    }
}
