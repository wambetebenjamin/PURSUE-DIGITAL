package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzon extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzon> CREATOR = new zzoq();
    public final String zza;
    public final long zzb;
    public final Long zzc;
    public final String zzd;
    public final String zze;
    public final Double zzf;
    private final int zzg;

    public final Object zza() {
        Long l = this.zzc;
        if (l != null) {
            return l;
        }
        Double d2 = this.zzf;
        if (d2 != null) {
            return d2;
        }
        String str = this.zzd;
        if (str != null) {
            return str;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzon(zzop zzopVar) {
        this(zzopVar.zzc, zzopVar.zzd, zzopVar.zze, zzopVar.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzon(String str, long j2, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.zzg = 2;
        this.zza = str;
        this.zzb = j2;
        this.zze = str2;
        if (obj == null) {
            this.zzc = null;
            this.zzf = null;
            this.zzd = null;
        } else if (obj instanceof Long) {
            this.zzc = (Long) obj;
            this.zzf = null;
            this.zzd = null;
        } else if (obj instanceof String) {
            this.zzc = null;
            this.zzf = null;
            this.zzd = (String) obj;
        } else if (obj instanceof Double) {
            this.zzc = null;
            this.zzf = (Double) obj;
            this.zzd = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzon(int i2, String str, long j2, Long l, Float f2, String str2, String str3, Double d2) {
        this.zzg = i2;
        this.zza = str;
        this.zzb = j2;
        this.zzc = l;
        if (i2 == 1) {
            this.zzf = f2 != null ? Double.valueOf(f2.doubleValue()) : null;
        } else {
            this.zzf = d2;
        }
        this.zzd = str2;
        this.zze = str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeLongObject(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 7, this.zze, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
