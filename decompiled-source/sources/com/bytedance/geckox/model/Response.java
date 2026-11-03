package com.bytedance.geckox.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class Response<T> {
    @SerializedName("data")
    public T data;
    @SerializedName("status")
    public int status;
}
