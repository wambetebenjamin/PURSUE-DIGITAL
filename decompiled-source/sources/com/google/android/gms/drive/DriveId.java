package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
@Deprecated
/* loaded from: classes4.dex */
public class DriveId extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new zzb();
    final String zza;
    final long zzb;
    final long zzc;
    final int zzd;
    volatile String zze = null;

    public DriveId(String str, long j2, long j3, int i2) {
        this.zza = str;
        boolean z = true;
        Preconditions.checkArgument(!"".equals(str));
        if (str == null && j2 == -1) {
            z = false;
            j2 = -1;
        }
        Preconditions.checkArgument(z);
        this.zzb = j2;
        this.zzc = j3;
        this.zzd = i2;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj != null && obj.getClass() == DriveId.class) {
            DriveId driveId = (DriveId) obj;
            if (driveId.zzc != this.zzc) {
                return false;
            }
            long j2 = driveId.zzb;
            if (j2 == -1) {
                if (this.zzb == -1) {
                    return ((String) Preconditions.checkNotNull(driveId.zza)).equals(this.zza);
                }
                j2 = -1;
            }
            String str2 = this.zza;
            if (str2 != null && (str = driveId.zza) != null) {
                return j2 == this.zzb && str.equals(str2);
            } else if (j2 == this.zzb) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.zzb;
        if (j2 == -1) {
            String str = this.zza;
            Preconditions.checkNotNull(str);
            return str.hashCode();
        }
        long j3 = this.zzc;
        String valueOf = String.valueOf(j2);
        StringBuilder sb = new StringBuilder(String.valueOf(j3).length() + String.valueOf(valueOf).length());
        sb.append(j3);
        sb.append(valueOf);
        return sb.toString().hashCode();
    }

    public final String toString() {
        return super.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, str, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeLong(parcel, 4, this.zzc);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
