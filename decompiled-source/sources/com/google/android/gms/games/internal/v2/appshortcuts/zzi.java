package com.google.android.gms.games.internal.v2.appshortcuts;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzi> CREATOR = new zzj();
    private final String zza;
    private final PersistableBundle zzb;
    private final Boolean zzc;
    private final Boolean zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(String str, PersistableBundle persistableBundle, Boolean bool, Boolean bool2) {
        this.zza = str;
        this.zzb = persistableBundle;
        this.zzc = bool;
        this.zzd = bool2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i2, false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeBooleanObject(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        return this.zza;
    }
}
