package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class VideoConfiguration extends AbstractSafeParcelable {
    public static final int CAPTURE_MODE_FILE = 0;
    public static final int CAPTURE_MODE_STREAM = 1;
    public static final int CAPTURE_MODE_UNKNOWN = -1;
    public static final Parcelable.Creator<VideoConfiguration> CREATOR = new zzb();
    public static final int NUM_CAPTURE_MODE = 2;
    public static final int NUM_QUALITY_LEVEL = 4;
    public static final int QUALITY_LEVEL_FULLHD = 3;
    public static final int QUALITY_LEVEL_HD = 1;
    public static final int QUALITY_LEVEL_SD = 0;
    public static final int QUALITY_LEVEL_UNKNOWN = -1;
    public static final int QUALITY_LEVEL_XHD = 2;
    private final int zza;
    private final int zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final boolean zze;

    /* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
    /* loaded from: classes5.dex */
    public static final class Builder {
        private int zza;
        private int zzb;
        private boolean zzc = true;
        private boolean zzd = true;

        public Builder(int i2, int i3) {
            this.zza = i2;
            this.zzb = i3;
        }

        public VideoConfiguration build() {
            return new VideoConfiguration(this.zza, this.zzb, true, this.zzc, this.zzd);
        }

        public Builder setCameraEnabled(boolean z) {
            this.zzc = z;
            return this;
        }

        public Builder setCaptureMode(int i2) {
            this.zzb = i2;
            return this;
        }

        public Builder setMicEnabled(boolean z) {
            this.zzd = z;
            return this;
        }

        public Builder setQualityLevel(int i2) {
            this.zza = i2;
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes5.dex */
    public @interface ValidCaptureModes {
    }

    public VideoConfiguration(int i2, int i3, boolean z, boolean z2, boolean z3) {
        Preconditions.checkArgument(isValidQualityLevel(i2, false));
        Preconditions.checkArgument(isValidCaptureMode(i3, false));
        this.zza = i2;
        this.zzb = i3;
        this.zzc = z;
        this.zzd = z2;
        this.zze = z3;
    }

    public static boolean isValidCaptureMode(int i2, boolean z) {
        if (i2 != -1) {
            z = true;
            if (i2 != 0 && i2 != 1) {
                return false;
            }
        }
        return z;
    }

    public static boolean isValidQualityLevel(int i2, boolean z) {
        if (i2 != -1) {
            z = true;
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 3) {
                return false;
            }
        }
        return z;
    }

    public boolean getCameraEnabled() {
        return this.zzd;
    }

    public int getCaptureMode() {
        return this.zzb;
    }

    public boolean getMicEnabled() {
        return this.zze;
    }

    public int getQualityLevel() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getQualityLevel());
        SafeParcelWriter.writeInt(parcel, 2, getCaptureMode());
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 8, getCameraEnabled());
        SafeParcelWriter.writeBoolean(parcel, 9, getMicEnabled());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
