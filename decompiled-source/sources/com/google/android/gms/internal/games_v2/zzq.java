package com.google.android.gms.internal.games_v2;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    private final int zza;
    private final zzx zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(int i2, zzx zzxVar) {
        this.zza = i2;
        this.zzb = zzxVar;
    }

    public static zzq zza(int i2) {
        return new zzq(i2, null);
    }

    public static zzq zzb(int i2, zzx zzxVar) {
        return new zzq(i2, zzxVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzq) {
            zzq zzqVar = (zzq) obj;
            return this.zza == zzqVar.zza && Objects.equal(this.zzb, zzqVar.zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), this.zzb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("signInType", Integer.valueOf(this.zza)).add("previousStepResolutionResult", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zzc() {
        return this.zza;
    }

    public final boolean zzd() {
        return this.zzb == null;
    }
}
