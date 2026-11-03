package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzq extends zzg implements PlayerRelationshipInfo {
    private final com.google.android.gms.games.internal.player.zzd zza;

    public zzq(DataHolder dataHolder, int i2, com.google.android.gms.games.internal.player.zzd zzdVar) {
        super(dataHolder, i2);
        this.zza = zzdVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final boolean equals(Object obj) {
        return zzo.zze(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* synthetic */ PlayerRelationshipInfo freeze() {
        return new zzo(this);
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final int getFriendStatus() {
        return zzr(this.zza.zzH, -1);
    }

    @Override // com.google.android.gms.common.data.DataBufferRef
    public final int hashCode() {
        return zzo.zzd(this);
    }

    public final String toString() {
        return zzo.zzf(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzp.zza(new zzo(this), parcel, i2);
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zza() {
        return zzj(this.zza.zzI, null);
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zzb() {
        return zzj(this.zza.zzJ, null);
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zzc() {
        return zzj(this.zza.zzK, null);
    }
}
