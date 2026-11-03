package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzbx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbx> CREATOR = new zzby();
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;

    public zzbx(int i2, int i3, int i4, int i5) {
        Preconditions.checkState(i2 >= 0 && i2 <= 23, "Start hour must be in range [0, 23].");
        Preconditions.checkState(i3 >= 0 && i3 <= 59, "Start minute must be in range [0, 59].");
        Preconditions.checkState(i4 >= 0 && i4 <= 23, "End hour must be in range [0, 23].");
        Preconditions.checkState(i5 >= 0 && i5 <= 59, "End minute must be in range [0, 59].");
        Preconditions.checkState(((i2 + i3) + i4) + i5 > 0, "Parameters can't be all 0.");
        this.zza = i2;
        this.zzb = i3;
        this.zzc = i4;
        this.zzd = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbx) {
            zzbx zzbxVar = (zzbx) obj;
            return this.zza == zzbxVar.zza && this.zzb == zzbxVar.zzb && this.zzc == zzbxVar.zzc && this.zzd == zzbxVar.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd));
    }

    public final String toString() {
        int i2 = this.zza;
        int i3 = this.zzb;
        int i4 = this.zzc;
        int i5 = this.zzd;
        StringBuilder sb = new StringBuilder(117);
        sb.append("UserPreferredSleepWindow [startHour=");
        sb.append(i2);
        sb.append(", startMinute=");
        sb.append(i3);
        sb.append(", endHour=");
        sb.append(i4);
        sb.append(", endMinute=");
        sb.append(i5);
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
