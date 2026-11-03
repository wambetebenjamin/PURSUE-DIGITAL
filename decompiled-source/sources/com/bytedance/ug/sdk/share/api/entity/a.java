package com.bytedance.ug.sdk.share.api.entity;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* compiled from: AuthorInfo.java */
/* loaded from: classes8.dex */
public class a implements Serializable {
    @SerializedName("name")

    /* renamed from: a  reason: collision with root package name */
    private String f15555a;
    @SerializedName(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL)

    /* renamed from: b  reason: collision with root package name */
    private String f15556b;
    @SerializedName("extra")

    /* renamed from: c  reason: collision with root package name */
    private JsonObject f15557c;

    public String getName() {
        return this.f15555a;
    }

    public void setName(String str) {
        this.f15555a = str;
    }

    public String getAvatarUrl() {
        return this.f15556b;
    }

    public void setAvatarUrl(String str) {
        this.f15556b = str;
    }

    public JsonObject getExtra() {
        return this.f15557c;
    }

    public void setExtra(JsonObject jsonObject) {
        this.f15557c = jsonObject;
    }
}
