package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
@Deprecated
/* loaded from: classes4.dex */
public class Contents extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Contents> CREATOR = new zza();
    final ParcelFileDescriptor zza;
    final int zzb;
    final int zzc;
    final DriveId zzd;
    final boolean zze;
    final String zzf;

    public Contents(ParcelFileDescriptor parcelFileDescriptor, int i2, int i3, DriveId driveId, boolean z, String str) {
        this.zza = parcelFileDescriptor;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = driveId;
        this.zze = z;
        this.zzf = str;
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        ParcelFileDescriptor parcelFileDescriptor = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, parcelFileDescriptor, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zze);
        SafeParcelWriter.writeString(parcel, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
