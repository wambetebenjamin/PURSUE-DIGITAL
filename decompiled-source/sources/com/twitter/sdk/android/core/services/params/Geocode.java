package com.twitter.sdk.android.core.services.params;
/* loaded from: classes10.dex */
public class Geocode {
    public final Distance distance;
    public final double latitude;
    public final double longitude;
    public final int radius;

    /* loaded from: classes10.dex */
    public enum Distance {
        MILES("mi"),
        KILOMETERS("km");
        
        public final String identifier;

        Distance(String str) {
            this.identifier = str;
        }
    }

    public Geocode(double d2, double d3, int i2, Distance distance) {
        this.latitude = d2;
        this.longitude = d3;
        this.radius = i2;
        this.distance = distance;
    }

    public String toString() {
        return this.latitude + "," + this.longitude + "," + this.radius + this.distance.identifier;
    }
}
