package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.games.internal.zzg;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class SnapshotMetadataChangeEntity extends zzg implements SnapshotMetadataChange {
    public static final Parcelable.Creator<SnapshotMetadataChangeEntity> CREATOR = new zzc();
    private final String zza;
    private final Long zzb;
    private final Uri zzc;
    private BitmapTeleporter zzd;
    private final Long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotMetadataChangeEntity() {
        this(null, null, null, null, null);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Bitmap getCoverImage() {
        BitmapTeleporter bitmapTeleporter = this.zzd;
        if (bitmapTeleporter == null) {
            return null;
        }
        return bitmapTeleporter.get();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final String getDescription() {
        return this.zza;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getPlayedTimeMillis() {
        return this.zzb;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final Long getProgressValue() {
        return this.zze;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeLongObject(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i2, false);
        SafeParcelWriter.writeLongObject(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadataChange
    public final BitmapTeleporter zza() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotMetadataChangeEntity(String str, Long l, BitmapTeleporter bitmapTeleporter, Uri uri, Long l2) {
        this.zza = str;
        this.zzb = l;
        this.zzd = bitmapTeleporter;
        this.zzc = uri;
        this.zze = l2;
        boolean z = true;
        if (bitmapTeleporter != null && uri != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot set both a URI and an image");
    }
}
