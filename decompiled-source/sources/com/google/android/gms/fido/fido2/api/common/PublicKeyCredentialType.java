package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes4.dex */
public enum PublicKeyCredentialType implements Parcelable {
    PUBLIC_KEY("public-key");
    
    public static final Parcelable.Creator<PublicKeyCredentialType> CREATOR = new Parcelable.Creator() { // from class: com.google.android.gms.fido.fido2.api.common.zzaq
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
            try {
                return PublicKeyCredentialType.fromString(parcel.readString());
            } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i2) {
            return new PublicKeyCredentialType[i2];
        }
    };
    private final String zzb = "public-key";

    /* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
    /* loaded from: classes4.dex */
    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(String str) {
            super(str);
        }
    }

    PublicKeyCredentialType(String str) {
    }

    public static PublicKeyCredentialType fromString(String str) throws UnsupportedPublicKeyCredTypeException {
        PublicKeyCredentialType[] values;
        for (PublicKeyCredentialType publicKeyCredentialType : values()) {
            if (str.equals(publicKeyCredentialType.zzb)) {
                return publicKeyCredentialType;
            }
        }
        throw new UnsupportedPublicKeyCredTypeException(String.format("PublicKeyCredentialType %s not supported", str));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.zzb);
    }
}
