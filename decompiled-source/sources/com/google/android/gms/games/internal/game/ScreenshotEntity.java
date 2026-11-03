package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.games.internal.zzg;
import com.google.common.net.HttpHeaders;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class ScreenshotEntity extends zzg implements Freezable, Parcelable {
    public static final Parcelable.Creator<ScreenshotEntity> CREATOR = new zzd();
    private final Uri zza;
    private final int zzb;
    private final int zzc;

    public ScreenshotEntity(Uri uri, int i2, int i3) {
        this.zza = uri;
        this.zzb = i2;
        this.zzc = i3;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ScreenshotEntity) {
            if (this == obj) {
                return true;
            }
            ScreenshotEntity screenshotEntity = (ScreenshotEntity) obj;
            if (Objects.equal(screenshotEntity.zza, this.zza) && Objects.equal(Integer.valueOf(screenshotEntity.zzb), Integer.valueOf(this.zzb)) && Objects.equal(Integer.valueOf(screenshotEntity.zzc), Integer.valueOf(this.zzc))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final /* bridge */ /* synthetic */ Object freeze() {
        return this;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, Integer.valueOf(this.zzb), Integer.valueOf(this.zzc));
    }

    @Override // com.google.android.gms.common.data.Freezable
    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("Uri", this.zza).add(HttpHeaders.WIDTH, Integer.valueOf(this.zzb)).add("Height", Integer.valueOf(this.zzc)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        Uri uri = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, uri, i2, false);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
