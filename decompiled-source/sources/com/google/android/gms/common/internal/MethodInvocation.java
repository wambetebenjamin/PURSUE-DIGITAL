package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public class MethodInvocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new zan();
    private final int zaa;
    private final int zab;
    private final int zac;
    private final long zad;
    private final long zae;
    private final String zaf;
    private final String zag;
    private final int zah;
    private final int zai;

    @Deprecated
    public MethodInvocation(int i2, int i3, int i4, long j2, long j3, String str, String str2, int i5) {
        this(i2, i3, i4, j2, j3, str, str2, i5, -1);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, this.zab);
        SafeParcelWriter.writeInt(parcel, 3, this.zac);
        SafeParcelWriter.writeLong(parcel, 4, this.zad);
        SafeParcelWriter.writeLong(parcel, 5, this.zae);
        SafeParcelWriter.writeString(parcel, 6, this.zaf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zag, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zah);
        SafeParcelWriter.writeInt(parcel, 9, this.zai);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public MethodInvocation(int i2, int i3, int i4, long j2, long j3, String str, String str2, int i5, int i6) {
        this.zaa = i2;
        this.zab = i3;
        this.zac = i4;
        this.zad = j2;
        this.zae = j3;
        this.zaf = str;
        this.zag = str2;
        this.zah = i5;
        this.zai = i6;
    }
}
