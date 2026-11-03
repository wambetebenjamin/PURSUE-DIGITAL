package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes4.dex */
public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzai> CREATOR = new zzaj();
    private final byte[][] zza;

    public zzai(byte[][] bArr) {
        Preconditions.checkArgument(bArr != null);
        Preconditions.checkArgument(1 == ((bArr.length & 1) ^ 1));
        int i2 = 0;
        while (i2 < bArr.length) {
            Preconditions.checkArgument(i2 == 0 || bArr[i2] != null);
            int i3 = i2 + 1;
            Preconditions.checkArgument(bArr[i3] != null);
            int length = bArr[i3].length;
            Preconditions.checkArgument(length == 32 || length == 64);
            i2 += 2;
        }
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzai) {
            return Arrays.deepEquals(this.zza, ((zzai) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        byte[][] bArr = this.zza;
        int length = bArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i2 ^= Objects.hashCode(bArr[i3]);
        }
        return i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArrayArray(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
