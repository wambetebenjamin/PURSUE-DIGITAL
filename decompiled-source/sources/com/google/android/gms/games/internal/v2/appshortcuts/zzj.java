package com.google.android.gms.games.internal.v2.appshortcuts;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzj implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        PersistableBundle persistableBundle = null;
        Boolean bool = null;
        Boolean bool2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                persistableBundle = (PersistableBundle) SafeParcelReader.createParcelable(parcel, readHeader, PersistableBundle.CREATOR);
            } else if (fieldId == 3) {
                bool = SafeParcelReader.readBooleanObject(parcel, readHeader);
            } else if (fieldId == 4) {
                bool2 = SafeParcelReader.readBooleanObject(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzi(str, persistableBundle, bool, bool2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzi[i2];
    }
}
