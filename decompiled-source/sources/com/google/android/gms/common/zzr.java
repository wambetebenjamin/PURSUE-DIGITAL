package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private final boolean zza;
    @Nullable
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(boolean z, String str, int i2, int i3, long j2) {
        this.zza = z;
        this.zzb = str;
        this.zzc = zzz.zza(i2) - 1;
        this.zzd = zzc.zza(i3) - 1;
        this.zze = j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeLong(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    public final long zzc() {
        return this.zze;
    }

    public final int zzd() {
        return zzz.zza(this.zzc);
    }

    public final int zze() {
        return zzc.zza(this.zzd);
    }
}
