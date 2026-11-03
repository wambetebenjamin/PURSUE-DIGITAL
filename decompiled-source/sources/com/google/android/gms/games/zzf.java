package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public class zzf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new GameEntity[i2];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zza */
    public GameEntity createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 28) {
                z9 = SafeParcelReader.readBoolean(parcel, readHeader);
            } else {
                switch (fieldId) {
                    case 1:
                        str = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 2:
                        str2 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 3:
                        str3 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 4:
                        str4 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 5:
                        str5 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 6:
                        str6 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 7:
                        uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                        continue;
                    case 8:
                        uri2 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                        continue;
                    case 9:
                        uri3 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                        continue;
                    case 10:
                        z = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 11:
                        z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 12:
                        str7 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 13:
                        i2 = SafeParcelReader.readInt(parcel, readHeader);
                        continue;
                    case 14:
                        i3 = SafeParcelReader.readInt(parcel, readHeader);
                        continue;
                    case 15:
                        i4 = SafeParcelReader.readInt(parcel, readHeader);
                        continue;
                    case 16:
                        z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 17:
                        z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 18:
                        str8 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 19:
                        str9 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 20:
                        str10 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 21:
                        z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 22:
                        z6 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 23:
                        z7 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    case 24:
                        str11 = SafeParcelReader.createString(parcel, readHeader);
                        continue;
                    case 25:
                        z8 = SafeParcelReader.readBoolean(parcel, readHeader);
                        continue;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GameEntity(str, str2, str3, str4, str5, str6, uri, uri2, uri3, z, z2, str7, i2, i3, i4, z3, z4, str8, str9, str10, z5, z6, z7, str11, z8, z9);
    }
}
