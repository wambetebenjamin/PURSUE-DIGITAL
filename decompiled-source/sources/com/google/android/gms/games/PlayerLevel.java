package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class PlayerLevel extends com.google.android.gms.games.internal.zzg {
    public static final Parcelable.Creator<PlayerLevel> CREATOR = new zzm();
    private final int zza;
    private final long zzb;
    private final long zzc;

    public PlayerLevel(int i2, long j2, long j3) {
        Preconditions.checkState(j2 >= 0, "Min XP must be positive!");
        Preconditions.checkState(j3 > j2, "Max XP must be more than min XP!");
        this.zza = i2;
        this.zzb = j2;
        this.zzc = j3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PlayerLevel) {
            if (this == obj) {
                return true;
            }
            PlayerLevel playerLevel = (PlayerLevel) obj;
            return Objects.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && Objects.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && Objects.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
        }
        return false;
    }

    public int getLevelNumber() {
        return this.zza;
    }

    public long getMaxXp() {
        return this.zzc;
    }

    public long getMinXp() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Long.valueOf(this.zzb), Long.valueOf(this.zzc));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("LevelNumber", Integer.valueOf(getLevelNumber())).add("MinXp", Long.valueOf(getMinXp())).add("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getLevelNumber());
        SafeParcelWriter.writeLong(parcel, 2, getMinXp());
        SafeParcelWriter.writeLong(parcel, 3, getMaxXp());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
