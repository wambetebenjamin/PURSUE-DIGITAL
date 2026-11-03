package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zza extends zzb {
    @Override // com.google.android.gms.games.multiplayer.zzb, android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return createFromParcel(parcel);
    }

    @Override // com.google.android.gms.games.multiplayer.zzb
    public final ParticipantEntity zza(Parcel parcel) {
        Integer unparcelClientVersion;
        boolean zzp;
        boolean canUnparcelSafely;
        unparcelClientVersion = ParticipantEntity.getUnparcelClientVersion();
        zzp = ParticipantEntity.zzp(unparcelClientVersion);
        if (!zzp) {
            canUnparcelSafely = ParticipantEntity.canUnparcelSafely(ParticipantEntity.class.getCanonicalName());
            if (!canUnparcelSafely) {
                return new ParticipantEntity();
            }
        }
        return super.createFromParcel(parcel);
    }
}
