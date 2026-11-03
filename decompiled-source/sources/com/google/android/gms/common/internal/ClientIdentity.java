package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.security.CertificateUtil;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ClientIdentity> CREATOR = new zaa();
    public final String packageName;
    public final int uid;

    public ClientIdentity(int i2, String str) {
        this.uid = i2;
        this.packageName = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ClientIdentity) {
            ClientIdentity clientIdentity = (ClientIdentity) obj;
            return clientIdentity.uid == this.uid && Objects.equal(clientIdentity.packageName, this.packageName);
        }
        return false;
    }

    public final int hashCode() {
        return this.uid;
    }

    public final String toString() {
        return this.uid + CertificateUtil.DELIMITER + this.packageName;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.uid;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
