package androidx.core.location;

import android.location.LocationRequest;
import android.os.Build;
import androidx.core.util.Preconditions;
import androidx.core.util.TimeUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class LocationRequestCompat {
    private static final long IMPLICIT_MIN_UPDATE_INTERVAL = -1;
    public static final long PASSIVE_INTERVAL = Long.MAX_VALUE;
    public static final int QUALITY_BALANCED_POWER_ACCURACY = 102;
    public static final int QUALITY_HIGH_ACCURACY = 100;
    public static final int QUALITY_LOW_POWER = 104;
    final long mDurationMillis;
    final long mIntervalMillis;
    final long mMaxUpdateDelayMillis;
    final int mMaxUpdates;
    final float mMinUpdateDistanceMeters;
    final long mMinUpdateIntervalMillis;
    final int mQuality;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Quality {
    }

    LocationRequestCompat(long j2, int i2, long j3, int i3, long j4, float f2, long j5) {
        this.mIntervalMillis = j2;
        this.mQuality = i2;
        this.mMinUpdateIntervalMillis = j4;
        this.mDurationMillis = j3;
        this.mMaxUpdates = i3;
        this.mMinUpdateDistanceMeters = f2;
        this.mMaxUpdateDelayMillis = j5;
    }

    public int getQuality() {
        return this.mQuality;
    }

    public long getIntervalMillis() {
        return this.mIntervalMillis;
    }

    public long getMinUpdateIntervalMillis() {
        long j2 = this.mMinUpdateIntervalMillis;
        return j2 == -1 ? this.mIntervalMillis : j2;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public int getMaxUpdates() {
        return this.mMaxUpdates;
    }

    public float getMinUpdateDistanceMeters() {
        return this.mMinUpdateDistanceMeters;
    }

    public long getMaxUpdateDelayMillis() {
        return this.mMaxUpdateDelayMillis;
    }

    public LocationRequest toLocationRequest() {
        return Api31Impl.toLocationRequest(this);
    }

    public LocationRequest toLocationRequest(String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            return toLocationRequest();
        }
        return (LocationRequest) Api19Impl.toLocationRequest(this, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocationRequestCompat) {
            LocationRequestCompat locationRequestCompat = (LocationRequestCompat) obj;
            return this.mQuality == locationRequestCompat.mQuality && this.mIntervalMillis == locationRequestCompat.mIntervalMillis && this.mMinUpdateIntervalMillis == locationRequestCompat.mMinUpdateIntervalMillis && this.mDurationMillis == locationRequestCompat.mDurationMillis && this.mMaxUpdates == locationRequestCompat.mMaxUpdates && Float.compare(locationRequestCompat.mMinUpdateDistanceMeters, this.mMinUpdateDistanceMeters) == 0 && this.mMaxUpdateDelayMillis == locationRequestCompat.mMaxUpdateDelayMillis;
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.mIntervalMillis;
        long j3 = this.mMinUpdateIntervalMillis;
        return (((this.mQuality * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        if (this.mIntervalMillis != Long.MAX_VALUE) {
            sb.append("@");
            TimeUtils.formatDuration(this.mIntervalMillis, sb);
            int i2 = this.mQuality;
            if (i2 == 100) {
                sb.append(" HIGH_ACCURACY");
            } else if (i2 == 102) {
                sb.append(" BALANCED");
            } else if (i2 == 104) {
                sb.append(" LOW_POWER");
            }
        } else {
            sb.append("PASSIVE");
        }
        if (this.mDurationMillis != Long.MAX_VALUE) {
            sb.append(", duration=");
            TimeUtils.formatDuration(this.mDurationMillis, sb);
        }
        if (this.mMaxUpdates != Integer.MAX_VALUE) {
            sb.append(", maxUpdates=").append(this.mMaxUpdates);
        }
        long j2 = this.mMinUpdateIntervalMillis;
        if (j2 != -1 && j2 < this.mIntervalMillis) {
            sb.append(", minUpdateInterval=");
            TimeUtils.formatDuration(this.mMinUpdateIntervalMillis, sb);
        }
        if (this.mMinUpdateDistanceMeters > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            sb.append(", minUpdateDistance=").append(this.mMinUpdateDistanceMeters);
        }
        if (this.mMaxUpdateDelayMillis / 2 > this.mIntervalMillis) {
            sb.append(", maxUpdateDelay=");
            TimeUtils.formatDuration(this.mMaxUpdateDelayMillis, sb);
        }
        sb.append(']');
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private long mDurationMillis;
        private long mIntervalMillis;
        private long mMaxUpdateDelayMillis;
        private int mMaxUpdates;
        private float mMinUpdateDistanceMeters;
        private long mMinUpdateIntervalMillis;
        private int mQuality;

        public Builder(long j2) {
            setIntervalMillis(j2);
            this.mQuality = 102;
            this.mDurationMillis = Long.MAX_VALUE;
            this.mMaxUpdates = Integer.MAX_VALUE;
            this.mMinUpdateIntervalMillis = -1L;
            this.mMinUpdateDistanceMeters = 0.0f;
            this.mMaxUpdateDelayMillis = 0L;
        }

        public Builder(LocationRequestCompat locationRequestCompat) {
            this.mIntervalMillis = locationRequestCompat.mIntervalMillis;
            this.mQuality = locationRequestCompat.mQuality;
            this.mDurationMillis = locationRequestCompat.mDurationMillis;
            this.mMaxUpdates = locationRequestCompat.mMaxUpdates;
            this.mMinUpdateIntervalMillis = locationRequestCompat.mMinUpdateIntervalMillis;
            this.mMinUpdateDistanceMeters = locationRequestCompat.mMinUpdateDistanceMeters;
            this.mMaxUpdateDelayMillis = locationRequestCompat.mMaxUpdateDelayMillis;
        }

        public Builder setIntervalMillis(long j2) {
            this.mIntervalMillis = Preconditions.checkArgumentInRange(j2, 0L, Long.MAX_VALUE, "intervalMillis");
            return this;
        }

        public Builder setQuality(int i2) {
            Preconditions.checkArgument(i2 == 104 || i2 == 102 || i2 == 100, "quality must be a defined QUALITY constant, not %d", Integer.valueOf(i2));
            this.mQuality = i2;
            return this;
        }

        public Builder setDurationMillis(long j2) {
            this.mDurationMillis = Preconditions.checkArgumentInRange(j2, 1L, Long.MAX_VALUE, "durationMillis");
            return this;
        }

        public Builder setMaxUpdates(int i2) {
            this.mMaxUpdates = Preconditions.checkArgumentInRange(i2, 1, Integer.MAX_VALUE, "maxUpdates");
            return this;
        }

        public Builder setMinUpdateIntervalMillis(long j2) {
            this.mMinUpdateIntervalMillis = Preconditions.checkArgumentInRange(j2, 0L, Long.MAX_VALUE, "minUpdateIntervalMillis");
            return this;
        }

        public Builder clearMinUpdateIntervalMillis() {
            this.mMinUpdateIntervalMillis = -1L;
            return this;
        }

        public Builder setMinUpdateDistanceMeters(float f2) {
            this.mMinUpdateDistanceMeters = f2;
            this.mMinUpdateDistanceMeters = Preconditions.checkArgumentInRange(f2, 0.0f, Float.MAX_VALUE, "minUpdateDistanceMeters");
            return this;
        }

        public Builder setMaxUpdateDelayMillis(long j2) {
            this.mMaxUpdateDelayMillis = j2;
            this.mMaxUpdateDelayMillis = Preconditions.checkArgumentInRange(j2, 0L, Long.MAX_VALUE, "maxUpdateDelayMillis");
            return this;
        }

        public LocationRequestCompat build() {
            Preconditions.checkState((this.mIntervalMillis == Long.MAX_VALUE && this.mMinUpdateIntervalMillis == -1) ? false : true, "passive location requests must have an explicit minimum update interval");
            long j2 = this.mIntervalMillis;
            return new LocationRequestCompat(j2, this.mQuality, this.mDurationMillis, this.mMaxUpdates, Math.min(this.mMinUpdateIntervalMillis, j2), this.mMinUpdateDistanceMeters, this.mMaxUpdateDelayMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api31Impl {
        private Api31Impl() {
        }

        public static LocationRequest toLocationRequest(LocationRequestCompat locationRequestCompat) {
            return new LocationRequest.Builder(locationRequestCompat.getIntervalMillis()).setQuality(locationRequestCompat.getQuality()).setMinUpdateIntervalMillis(locationRequestCompat.getMinUpdateIntervalMillis()).setDurationMillis(locationRequestCompat.getDurationMillis()).setMaxUpdates(locationRequestCompat.getMaxUpdates()).setMinUpdateDistanceMeters(locationRequestCompat.getMinUpdateDistanceMeters()).setMaxUpdateDelayMillis(locationRequestCompat.getMaxUpdateDelayMillis()).build();
        }
    }

    /* loaded from: classes.dex */
    private static class Api19Impl {
        private static Method sCreateFromDeprecatedProviderMethod;
        private static Class<?> sLocationRequestClass;
        private static Method sSetExpireInMethod;
        private static Method sSetFastestIntervalMethod;
        private static Method sSetNumUpdatesMethod;
        private static Method sSetQualityMethod;

        private Api19Impl() {
        }

        public static Object toLocationRequest(LocationRequestCompat locationRequestCompat, String str) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (sLocationRequestClass == null) {
                        sLocationRequestClass = Class.forName("android.location.LocationRequest");
                    }
                    if (sCreateFromDeprecatedProviderMethod == null) {
                        Method declaredMethod = sLocationRequestClass.getDeclaredMethod("createFromDeprecatedProvider", String.class, Long.TYPE, Float.TYPE, Boolean.TYPE);
                        sCreateFromDeprecatedProviderMethod = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    Object invoke = sCreateFromDeprecatedProviderMethod.invoke(null, str, Long.valueOf(locationRequestCompat.getIntervalMillis()), Float.valueOf(locationRequestCompat.getMinUpdateDistanceMeters()), false);
                    if (invoke == null) {
                        return null;
                    }
                    if (sSetQualityMethod == null) {
                        Method declaredMethod2 = sLocationRequestClass.getDeclaredMethod("setQuality", Integer.TYPE);
                        sSetQualityMethod = declaredMethod2;
                        declaredMethod2.setAccessible(true);
                    }
                    sSetQualityMethod.invoke(invoke, Integer.valueOf(locationRequestCompat.getQuality()));
                    if (sSetFastestIntervalMethod == null) {
                        Method declaredMethod3 = sLocationRequestClass.getDeclaredMethod("setFastestInterval", Long.TYPE);
                        sSetFastestIntervalMethod = declaredMethod3;
                        declaredMethod3.setAccessible(true);
                    }
                    sSetFastestIntervalMethod.invoke(invoke, Long.valueOf(locationRequestCompat.getMinUpdateIntervalMillis()));
                    if (locationRequestCompat.getMaxUpdates() < Integer.MAX_VALUE) {
                        if (sSetNumUpdatesMethod == null) {
                            Method declaredMethod4 = sLocationRequestClass.getDeclaredMethod("setNumUpdates", Integer.TYPE);
                            sSetNumUpdatesMethod = declaredMethod4;
                            declaredMethod4.setAccessible(true);
                        }
                        sSetNumUpdatesMethod.invoke(invoke, Integer.valueOf(locationRequestCompat.getMaxUpdates()));
                    }
                    if (locationRequestCompat.getDurationMillis() < Long.MAX_VALUE) {
                        if (sSetExpireInMethod == null) {
                            Method declaredMethod5 = sLocationRequestClass.getDeclaredMethod("setExpireIn", Long.TYPE);
                            sSetExpireInMethod = declaredMethod5;
                            declaredMethod5.setAccessible(true);
                        }
                        sSetExpireInMethod.invoke(invoke, Long.valueOf(locationRequestCompat.getDurationMillis()));
                    }
                    return invoke;
                } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                }
            }
            return null;
        }
    }
}
