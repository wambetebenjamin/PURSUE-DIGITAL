package com.google.android.gms.games.internal.v2.appshortcuts;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzg> CREATOR = new zzh();
    private final List zza;
    private final List zzb;
    private final List zzc;
    private final List zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(List list, List list2, List list3, List list4) {
        this.zza = list;
        this.zzb = list2;
        this.zzc = list3;
        this.zzd = list4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, list, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List zza() {
        return this.zza;
    }

    public final List zzb() {
        return this.zzb;
    }

    public final List zzc() {
        return this.zzc;
    }

    public final List zzd() {
        return this.zzd;
    }
}
