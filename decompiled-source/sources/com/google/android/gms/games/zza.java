package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zza extends com.google.android.gms.games.internal.zzg implements CurrentPlayerInfo {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private final int zza;

    public zza(int i2) {
        this.zza = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(CurrentPlayerInfo currentPlayerInfo) {
        return Objects.hashCode(Integer.valueOf(currentPlayerInfo.getFriendsListVisibilityStatus()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzb(CurrentPlayerInfo currentPlayerInfo, Object obj) {
        if (obj instanceof CurrentPlayerInfo) {
            return obj == currentPlayerInfo || ((CurrentPlayerInfo) obj).getFriendsListVisibilityStatus() == currentPlayerInfo.getFriendsListVisibilityStatus();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzc(CurrentPlayerInfo currentPlayerInfo) {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(currentPlayerInfo);
        stringHelper.add("FriendsListVisibilityStatus", Integer.valueOf(currentPlayerInfo.getFriendsListVisibilityStatus()));
        return stringHelper.toString();
    }

    public final boolean equals(Object obj) {
        return zzb(this, obj);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ CurrentPlayerInfo freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.CurrentPlayerInfo
    public final int getFriendsListVisibilityStatus() {
        return this.zza;
    }

    public final int hashCode() {
        return zza(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzc(this);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        zzb.zza(this, parcel, i2);
    }

    public zza(CurrentPlayerInfo currentPlayerInfo) {
        this.zza = currentPlayerInfo.getFriendsListVisibilityStatus();
    }
}
