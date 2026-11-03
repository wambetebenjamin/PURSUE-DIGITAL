package com.google.android.gms.games.internal.v2.appshortcuts;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private final long zza;
    private final String zzb;
    private final int zzc;
    private final ComponentName zzd;
    private final String zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(long j2, String str, int i2, ComponentName componentName, String str2) {
        this.zza = j2;
        this.zzb = str;
        this.zzc = i2;
        this.zzd = componentName;
        this.zze = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i2, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zze;
    }
}
