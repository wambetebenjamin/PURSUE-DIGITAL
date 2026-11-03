package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class Coordinates {
    public static final int INDEX_LATITUDE = 1;
    public static final int INDEX_LONGITUDE = 0;
    @SerializedName("coordinates")
    public final List<Double> coordinates;
    @SerializedName("type")
    public final String type;

    public Coordinates(Double d2, Double d3, String str) {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(0, d2);
        arrayList.add(1, d3);
        this.coordinates = ModelUtils.getSafeList(arrayList);
        this.type = str;
    }

    public Double getLongitude() {
        return this.coordinates.get(0);
    }

    public Double getLatitude() {
        return this.coordinates.get(1);
    }
}
