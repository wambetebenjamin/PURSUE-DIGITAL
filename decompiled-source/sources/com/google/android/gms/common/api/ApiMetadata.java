package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class ApiMetadata extends AbstractSafeParcelable {
    public static final ApiMetadata zza;
    private final ComplianceOptions zzc;
    private final boolean zzd;
    private boolean zze;
    public static final Parcelable.Creator<ApiMetadata> CREATOR = zza.zza();
    private static final ApiMetadata zzb = newBuilder().build();

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes4.dex */
    public static final class Builder {
        private ComplianceOptions zza;
        private boolean zzb = false;
        private boolean zzc;

        public ApiMetadata build() {
            ApiMetadata apiMetadata = new ApiMetadata(this.zza, this.zzb);
            apiMetadata.zza(this.zzc);
            return apiMetadata;
        }

        public Builder setCallbackSupportEnabled(boolean z) {
            this.zzb = z;
            return this;
        }

        public Builder setComplianceOptions(ComplianceOptions complianceOptions) {
            this.zza = complianceOptions;
            return this;
        }

        final /* synthetic */ Builder zza(boolean z) {
            this.zzc = z;
            return this;
        }
    }

    static {
        Builder newBuilder = newBuilder();
        newBuilder.zza(true);
        zza = newBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiMetadata(ComplianceOptions complianceOptions, boolean z) {
        this.zzc = complianceOptions;
        this.zzd = z;
    }

    public static final ApiMetadata fromComplianceOptions(ComplianceOptions complianceOptions) {
        Builder newBuilder = newBuilder();
        newBuilder.setComplianceOptions(complianceOptions);
        return newBuilder.build();
    }

    public static final ApiMetadata getEmptyInstance() {
        return zzb;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ApiMetadata) {
            ApiMetadata apiMetadata = (ApiMetadata) obj;
            return Objects.equal(this.zzc, apiMetadata.zzc) && this.zze == apiMetadata.zze && this.zzd == apiMetadata.zzd;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzc, Boolean.valueOf(this.zze), Boolean.valueOf(this.zzd));
    }

    public Builder toBuilder() {
        Builder newBuilder = newBuilder();
        newBuilder.setComplianceOptions(this.zzc);
        newBuilder.setCallbackSupportEnabled(this.zzd);
        newBuilder.zza(this.zze);
        return newBuilder;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
        sb.append("ApiMetadata(complianceOptions=");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (this.zze) {
            parcel.setDataPosition(parcel.dataPosition() - 4);
            parcel.setDataSize(parcel.dataSize() - 4);
            return;
        }
        parcel.writeInt(-204102970);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzc, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    final /* synthetic */ void zza(boolean z) {
        this.zze = z;
    }
}
