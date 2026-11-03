package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzb implements Parcelable.Creator {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(MostRecentGameInfoEntity mostRecentGameInfoEntity, Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, mostRecentGameInfoEntity.zza(), false);
        SafeParcelWriter.writeString(parcel, 2, mostRecentGameInfoEntity.zzb(), false);
        SafeParcelWriter.writeLong(parcel, 3, mostRecentGameInfoEntity.zzc());
        SafeParcelWriter.writeParcelable(parcel, 4, mostRecentGameInfoEntity.zzd(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 5, mostRecentGameInfoEntity.zze(), i2, false);
        SafeParcelWriter.writeParcelable(parcel, 6, mostRecentGameInfoEntity.zzf(), i2, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 5:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 6:
                    uri3 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MostRecentGameInfoEntity(str, str2, j2, uri, uri2, uri3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new MostRecentGameInfoEntity[i2];
    }
}
