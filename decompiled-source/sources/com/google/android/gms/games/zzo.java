package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzo extends com.google.android.gms.games.internal.zzg implements PlayerRelationshipInfo {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    public zzo(int i2, String str, String str2, String str3) {
        this.zza = i2;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(PlayerRelationshipInfo playerRelationshipInfo) {
        return Objects.hashCode(Integer.valueOf(playerRelationshipInfo.getFriendStatus()), playerRelationshipInfo.zza(), playerRelationshipInfo.zzb(), playerRelationshipInfo.zzc());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(PlayerRelationshipInfo playerRelationshipInfo, Object obj) {
        if (obj instanceof PlayerRelationshipInfo) {
            if (obj == playerRelationshipInfo) {
                return true;
            }
            PlayerRelationshipInfo playerRelationshipInfo2 = (PlayerRelationshipInfo) obj;
            return playerRelationshipInfo2.getFriendStatus() == playerRelationshipInfo.getFriendStatus() && Objects.equal(playerRelationshipInfo2.zza(), playerRelationshipInfo.zza()) && Objects.equal(playerRelationshipInfo2.zzb(), playerRelationshipInfo.zzb()) && Objects.equal(playerRelationshipInfo2.zzc(), playerRelationshipInfo.zzc());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzf(PlayerRelationshipInfo playerRelationshipInfo) {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(playerRelationshipInfo);
        stringHelper.add("FriendStatus", Integer.valueOf(playerRelationshipInfo.getFriendStatus()));
        if (playerRelationshipInfo.zza() != null) {
            stringHelper.add("Nickname", playerRelationshipInfo.zza());
        }
        if (playerRelationshipInfo.zzb() != null) {
            stringHelper.add("InvitationNickname", playerRelationshipInfo.zzb());
        }
        if (playerRelationshipInfo.zzc() != null) {
            stringHelper.add("NicknameAbuseReportToken", playerRelationshipInfo.zzb());
        }
        return stringHelper.toString();
    }

    public final boolean equals(Object obj) {
        return zze(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ PlayerRelationshipInfo freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final int getFriendStatus() {
        return this.zza;
    }

    public final int hashCode() {
        return zzd(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzf(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzp.zza(this, parcel, i2);
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.games.PlayerRelationshipInfo
    public final String zzc() {
        return this.zzd;
    }

    public zzo(PlayerRelationshipInfo playerRelationshipInfo) {
        this.zza = playerRelationshipInfo.getFriendStatus();
        this.zzb = playerRelationshipInfo.zza();
        this.zzc = playerRelationshipInfo.zzb();
        this.zzd = playerRelationshipInfo.zzc();
    }
}
