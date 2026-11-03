package com.google.android.gms.internal.games_v2;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.bytedance.sdk.account.platform.base.i;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzx> CREATOR = new zzy();
    private final Intent zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(Intent intent) {
        this.zza = intent;
    }

    public static zzx zza(Intent intent) {
        return new zzx(intent);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzx) {
            return Objects.equal(this.zza, ((zzx) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        Intent intent = this.zza;
        if (intent != null) {
            return intent.hashCode();
        }
        return 0;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(i.CU_KEY_RESULT_DATA, this.zza).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        Intent intent = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, intent, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
