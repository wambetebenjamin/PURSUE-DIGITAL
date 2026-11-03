package com.bytedance.pipo.game.impl.net.entity;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
/* compiled from: ResponseEntity.java */
/* loaded from: classes2.dex */
public class c {
    @SerializedName("code")

    /* renamed from: b  reason: collision with root package name */
    public int f10467b;
    @SerializedName("message")

    /* renamed from: c  reason: collision with root package name */
    public String f10468c;

    public boolean a() {
        return this.f10467b == 0 && TextUtils.equals("success", this.f10468c);
    }

    public boolean b() {
        return !a();
    }
}
