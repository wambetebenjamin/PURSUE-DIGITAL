package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbf> CREATOR = new zzbi();
    public final String zza;
    public final zzbe zzb;
    public final String zzc;
    public final long zzd;

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        return "origin=" + str + ",name=" + str2 + ",params=" + String.valueOf(this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbf(zzbf zzbfVar, long j2) {
        Preconditions.checkNotNull(zzbfVar);
        this.zza = zzbfVar.zza;
        this.zzb = zzbfVar.zzb;
        this.zzc = zzbfVar.zzc;
        this.zzd = j2;
    }

    public zzbf(String str, zzbe zzbeVar, String str2, long j2) {
        this.zza = str;
        this.zzb = zzbeVar;
        this.zzc = str2;
        this.zzd = j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i2, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
