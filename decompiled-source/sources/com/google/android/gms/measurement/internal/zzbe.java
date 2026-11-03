package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzbe extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzbe> CREATOR = new zzbg();
    private final Bundle zza;

    public final int zza() {
        return this.zza.size();
    }

    public final Bundle zzb() {
        return new Bundle(this.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Double zza(String str) {
        return Double.valueOf(this.zza.getDouble(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Long zzb(String str) {
        return Long.valueOf(this.zza.getLong(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzc(String str) {
        return this.zza.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzd(String str) {
        return this.zza.getString(str);
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzbd(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbe(Bundle bundle) {
        this.zza = bundle;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzb(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
