package com.bytedance.ttgame.module.dynamic.api;

import com.google.gson.annotations.SerializedName;
import gsdk.impl.dynamic.DEFAULT.j;
import java.util.List;
/* loaded from: classes5.dex */
public class BundlePackage {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public GeckoBundle data;
    @SerializedName("message")
    public String message;

    /* loaded from: classes5.dex */
    public class GeckoBundle {
        @SerializedName("list")
        public List<j> packages;

        public GeckoBundle() {
        }
    }
}
