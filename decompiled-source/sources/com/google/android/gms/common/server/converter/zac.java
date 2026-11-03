package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public final class zac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zac> CREATOR = new zae();
    final int zaa;
    final String zab;
    final int zac;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zac(int i2, String str, int i3) {
        this.zaa = i2;
        this.zab = str;
        this.zac = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeString(parcel, 2, this.zab, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zac(String str, int i2) {
        this.zaa = 1;
        this.zab = str;
        this.zac = i2;
    }
}
