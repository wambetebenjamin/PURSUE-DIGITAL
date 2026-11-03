package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class ComplianceOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ComplianceOptions> CREATOR;
    public static final ComplianceOptions zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final boolean zze;

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes4.dex */
    public static final class Builder {
        private int zza = -1;
        private int zzb = -1;
        private int zzc = 0;
        private boolean zzd = true;

        public ComplianceOptions build() {
            return new ComplianceOptions(this.zza, this.zzb, this.zzc, this.zzd);
        }

        public Builder setCallerProductId(int i2) {
            this.zza = i2;
            return this;
        }

        public Builder setDataOwnerProductId(int i2) {
            this.zzb = i2;
            return this;
        }

        public Builder setIsUserData(boolean z) {
            this.zzd = z;
            return this;
        }

        public Builder setProcessingReason(int i2) {
            this.zzc = i2;
            return this;
        }
    }

    static {
        Builder newBuilder = newBuilder();
        newBuilder.setCallerProductId(-1);
        newBuilder.setDataOwnerProductId(-1);
        newBuilder.setProcessingReason(0);
        newBuilder.setIsUserData(true);
        zza = newBuilder.build();
        CREATOR = new zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComplianceOptions(int i2, int i3, int i4, boolean z) {
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = z;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final Builder newBuilder(Context context) {
        return newBuilder();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ComplianceOptions) {
            ComplianceOptions complianceOptions = (ComplianceOptions) obj;
            return this.zzb == complianceOptions.zzb && this.zzc == complianceOptions.zzc && this.zzd == complianceOptions.zzd && this.zze == complianceOptions.zze;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), Boolean.valueOf(this.zze));
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.setCallerProductId(this.zzb);
        builder.setDataOwnerProductId(this.zzc);
        builder.setProcessingReason(this.zzd);
        builder.setIsUserData(this.zze);
        return builder;
    }

    public final String toString() {
        int i2 = this.zzb;
        int length = String.valueOf(i2).length();
        int i3 = this.zzc;
        int length2 = String.valueOf(i3).length();
        int i4 = this.zzd;
        int length3 = String.valueOf(i4).length();
        boolean z = this.zze;
        StringBuilder sb = new StringBuilder(length + 55 + length2 + 19 + length3 + 13 + String.valueOf(z).length() + 1);
        sb.append("ComplianceOptions{callerProductId=");
        sb.append(i2);
        sb.append(", dataOwnerProductId=");
        sb.append(i3);
        sb.append(", processingReason=");
        sb.append(i4);
        sb.append(", isUserData=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.zzb;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i3);
        SafeParcelWriter.writeInt(parcel, 2, this.zzc);
        SafeParcelWriter.writeInt(parcel, 3, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
