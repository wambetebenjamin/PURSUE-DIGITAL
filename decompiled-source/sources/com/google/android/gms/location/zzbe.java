package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzbe implements Parcelable.Creator<LocationAvailability> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationAvailability createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 1;
        int i3 = 1;
        int i4 = 1000;
        long j2 = 0;
        zzbo[] zzboVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (fieldId == 4) {
                i4 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 5) {
                zzboVarArr = (zzbo[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzbo.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationAvailability(i4, i2, i3, j2, zzboVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ LocationAvailability[] newArray(int i2) {
        return new LocationAvailability[i2];
    }
}
