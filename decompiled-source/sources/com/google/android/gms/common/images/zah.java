package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public final class zah implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        int i3 = 0;
        Uri uri = null;
        int i4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
            } else if (fieldId == 3) {
                i4 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new WebImage(i2, uri, i4, i3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new WebImage[i2];
    }
}
