package com.bytedance.ttgame.module.bridge.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes4.dex */
public class WhiteListInfo {
    @SerializedName("code")
    public int code;
    @SerializedName("data")
    public a data;
    @SerializedName("message")
    public String message;

    /* loaded from: classes4.dex */
    public static class a {
        @SerializedName("list")

        /* renamed from: a  reason: collision with root package name */
        public List<String> f14359a;
    }
}
