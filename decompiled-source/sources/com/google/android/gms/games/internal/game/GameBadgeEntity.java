package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements zza {
    public static final Parcelable.Creator<GameBadgeEntity> CREATOR = new zzb();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final Uri zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GameBadgeEntity(int i2, String str, String str2, Uri uri) {
        this.zza = i2;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = uri;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zza) {
            if (this == obj) {
                return true;
            }
            zza zzaVar = (zza) obj;
            if (Objects.equal(Integer.valueOf(zzaVar.zza()), this.zzb) && Objects.equal(zzaVar.zzb(), this.zzd)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("Type", Integer.valueOf(this.zza)).add("Title", this.zzb).add("Description", this.zzc).add("IconImageUri", this.zzd).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (!shouldDowngrade()) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zza);
            SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
            SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
            SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i2, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            return;
        }
        parcel.writeInt(this.zza);
        parcel.writeString(this.zzb);
        parcel.writeString(this.zzc);
        Uri uri = this.zzd;
        parcel.writeString(uri == null ? null : uri.toString());
    }

    @Override // com.google.android.gms.games.internal.game.zza
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.games.internal.game.zza
    public final String zzb() {
        return this.zzc;
    }
}
