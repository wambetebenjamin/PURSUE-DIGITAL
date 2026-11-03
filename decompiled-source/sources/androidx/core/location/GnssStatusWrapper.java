package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
class GnssStatusWrapper extends GnssStatusCompat {
    private final GnssStatus mWrapped;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GnssStatusWrapper(Object obj) {
        this.mWrapped = (GnssStatus) Preconditions.checkNotNull((GnssStatus) obj);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int i2) {
        return this.mWrapped.getConstellationType(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int i2) {
        return this.mWrapped.getSvid(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int i2) {
        return this.mWrapped.getCn0DbHz(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int i2) {
        return this.mWrapped.getElevationDegrees(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int i2) {
        return this.mWrapped.getAzimuthDegrees(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int i2) {
        return this.mWrapped.hasEphemerisData(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int i2) {
        return this.mWrapped.hasAlmanacData(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int i2) {
        return this.mWrapped.usedInFix(i2);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.hasCarrierFrequencyHz(this.mWrapped, i2);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getCarrierFrequencyHz(this.mWrapped, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int i2) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.hasBasebandCn0DbHz(this.mWrapped, i2);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int i2) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getBasebandCn0DbHz(this.mWrapped, i2);
        }
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GnssStatusWrapper) {
            return this.mWrapped.equals(((GnssStatusWrapper) obj).mWrapped);
        }
        return false;
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    /* loaded from: classes.dex */
    static class Api26Impl {
        private Api26Impl() {
        }

        static float getCarrierFrequencyHz(GnssStatus gnssStatus, int i2) {
            return gnssStatus.getCarrierFrequencyHz(i2);
        }

        static boolean hasCarrierFrequencyHz(GnssStatus gnssStatus, int i2) {
            return gnssStatus.hasCarrierFrequencyHz(i2);
        }
    }

    /* loaded from: classes.dex */
    static class Api30Impl {
        private Api30Impl() {
        }

        static boolean hasBasebandCn0DbHz(GnssStatus gnssStatus, int i2) {
            return gnssStatus.hasBasebandCn0DbHz(i2);
        }

        static float getBasebandCn0DbHz(GnssStatus gnssStatus, int i2) {
            return gnssStatus.getBasebandCn0DbHz(i2);
        }
    }
}
