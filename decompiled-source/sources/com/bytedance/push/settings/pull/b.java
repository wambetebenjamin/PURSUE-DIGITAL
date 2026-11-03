package com.bytedance.push.settings.pull;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* compiled from: PullSettingsModel.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11853a = "app_launch";

    /* renamed from: b  reason: collision with root package name */
    public static final String f11854b = "depths";

    /* renamed from: c  reason: collision with root package name */
    public static final String f11855c = "switch_to_background";

    /* renamed from: d  reason: collision with root package name */
    public static final String f11856d = "host_invoke";
    @SerializedName("background_min_interval_in_mill")

    /* renamed from: e  reason: collision with root package name */
    public long f11857e = 5000;
    @SerializedName("allow_scene_list")

    /* renamed from: f  reason: collision with root package name */
    public List<String> f11858f = new ArrayList();
    @SerializedName("allow_branch_scene_list")

    /* renamed from: g  reason: collision with root package name */
    public List<String> f11859g = new ArrayList();
}
