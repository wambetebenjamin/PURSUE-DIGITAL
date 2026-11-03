package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class MentionEntity extends Entity {
    @SerializedName("id")
    public final long id;
    @SerializedName("id_str")
    public final String idStr;
    @SerializedName("name")
    public final String name;
    @SerializedName("screen_name")
    public final String screenName;

    @Override // com.twitter.sdk.android.core.models.Entity
    public /* bridge */ /* synthetic */ int getEnd() {
        return super.getEnd();
    }

    @Override // com.twitter.sdk.android.core.models.Entity
    public /* bridge */ /* synthetic */ int getStart() {
        return super.getStart();
    }

    public MentionEntity(long j2, String str, String str2, String str3, int i2, int i3) {
        super(i2, i3);
        this.id = j2;
        this.idStr = str;
        this.name = str2;
        this.screenName = str3;
    }
}
