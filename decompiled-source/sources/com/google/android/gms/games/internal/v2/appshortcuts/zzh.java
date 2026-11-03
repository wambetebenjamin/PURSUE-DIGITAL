package com.google.android.gms.games.internal.v2.appshortcuts;

import android.content.pm.ShortcutInfo;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzh implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<String> arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList<String> arrayList3 = null;
        ArrayList<String> arrayList4 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createStringList(parcel, readHeader);
            } else if (fieldId == 2) {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, ShortcutInfo.CREATOR);
            } else if (fieldId == 3) {
                arrayList3 = SafeParcelReader.createStringList(parcel, readHeader);
            } else if (fieldId == 4) {
                arrayList4 = SafeParcelReader.createStringList(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzg(arrayList, arrayList2, arrayList3, arrayList4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i2) {
        return new zzg[i2];
    }
}
