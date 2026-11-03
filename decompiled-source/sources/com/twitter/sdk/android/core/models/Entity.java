package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
class Entity implements Serializable {
    private static final int END_INDEX = 1;
    private static final int START_INDEX = 0;
    @SerializedName("indices")
    public final List<Integer> indices;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Entity(int i2, int i3) {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(0, Integer.valueOf(i2));
        arrayList.add(1, Integer.valueOf(i3));
        this.indices = Collections.unmodifiableList(arrayList);
    }

    public int getStart() {
        return this.indices.get(0).intValue();
    }

    public int getEnd() {
        return this.indices.get(1).intValue();
    }
}
