package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
/* loaded from: classes5.dex */
public final class zzm implements Parcelable.Creator<ActivityTransitionEvent> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ ActivityTransitionEvent createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i2 = 0;
        long j2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ActivityTransitionEvent(i2, i3, j2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ ActivityTransitionEvent[] newArray(int i2) {
        return new ActivityTransitionEvent[i2];
    }
}
