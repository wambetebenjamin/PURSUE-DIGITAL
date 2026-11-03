package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zza implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = null;
        GameEntity gameEntity = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, readHeader, GameEntity.CREATOR);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    j4 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 11:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ExperienceEventEntity(str, gameEntity, str2, str3, str4, uri, j2, j3, j4, i2, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new ExperienceEventEntity[i2];
    }
}
