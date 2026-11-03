package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class SymbolEntity extends Entity {
    @SerializedName("text")
    public final String text;

    @Override // com.twitter.sdk.android.core.models.Entity
    public /* bridge */ /* synthetic */ int getEnd() {
        return super.getEnd();
    }

    @Override // com.twitter.sdk.android.core.models.Entity
    public /* bridge */ /* synthetic */ int getStart() {
        return super.getStart();
    }

    public SymbolEntity(String str, int i2, int i3) {
        super(i2, i3);
        this.text = str;
    }
}
