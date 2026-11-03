package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes4.dex */
public class COSEAlgorithmIdentifier implements Parcelable {
    public static final Parcelable.Creator<COSEAlgorithmIdentifier> CREATOR = new zzp();
    private final Algorithm zza;

    /* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
    /* loaded from: classes4.dex */
    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(int i2) {
            super("Algorithm with COSE value " + i2 + " not supported");
        }
    }

    COSEAlgorithmIdentifier(Algorithm algorithm) {
        this.zza = (Algorithm) Preconditions.checkNotNull(algorithm);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static COSEAlgorithmIdentifier fromCoseValue(int i2) throws UnsupportedAlgorithmIdentifierException {
        EC2Algorithm[] values;
        RSAAlgorithm rSAAlgorithm;
        if (i2 == RSAAlgorithm.LEGACY_RS1.getAlgoValue()) {
            rSAAlgorithm = RSAAlgorithm.RS1;
        } else {
            RSAAlgorithm[] values2 = RSAAlgorithm.values();
            int length = values2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    RSAAlgorithm rSAAlgorithm2 = values2[i3];
                    if (rSAAlgorithm2.getAlgoValue() == i2) {
                        rSAAlgorithm = rSAAlgorithm2;
                        break;
                    }
                    i3++;
                } else {
                    for (RSAAlgorithm rSAAlgorithm3 : EC2Algorithm.values()) {
                        if (rSAAlgorithm3.getAlgoValue() == i2) {
                            rSAAlgorithm = rSAAlgorithm3;
                        }
                    }
                    throw new UnsupportedAlgorithmIdentifierException(i2);
                }
            }
        }
        return new COSEAlgorithmIdentifier(rSAAlgorithm);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof COSEAlgorithmIdentifier) && this.zza.getAlgoValue() == ((COSEAlgorithmIdentifier) obj).zza.getAlgoValue();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public int toCoseValue() {
        return this.zza.getAlgoValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.zza.getAlgoValue());
    }
}
