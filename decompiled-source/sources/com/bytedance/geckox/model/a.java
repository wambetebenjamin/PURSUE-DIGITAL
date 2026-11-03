package com.bytedance.geckox.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* compiled from: CleanPolicyModel.java */
/* loaded from: classes2.dex */
public class a {
    @SerializedName("specified_clean")

    /* renamed from: a  reason: collision with root package name */
    public List<C0132a> f9217a;
    @SerializedName("group_clean")

    /* renamed from: b  reason: collision with root package name */
    public b f9218b;

    /* compiled from: CleanPolicyModel.java */
    /* loaded from: classes2.dex */
    public static class b {
        @SerializedName("rule")

        /* renamed from: a  reason: collision with root package name */
        public int f9229a;
        @SerializedName("policy")

        /* renamed from: b  reason: collision with root package name */
        public int f9230b;
        @SerializedName("limit")

        /* renamed from: c  reason: collision with root package name */
        public int f9231c;
    }

    /* compiled from: CleanPolicyModel.java */
    /* renamed from: com.bytedance.geckox.model.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0132a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f9219a = 1;

        /* renamed from: b  reason: collision with root package name */
        public static final int f9220b = 2;

        /* renamed from: c  reason: collision with root package name */
        public static final int f9221c = 3;
        @SerializedName("c")

        /* renamed from: d  reason: collision with root package name */
        public String f9222d;
        @SerializedName("clean_type")

        /* renamed from: e  reason: collision with root package name */
        public int f9223e;
        @SerializedName("version")

        /* renamed from: f  reason: collision with root package name */
        public List<Long> f9224f;
        @SerializedName("status")

        /* renamed from: g  reason: collision with root package name */
        public int f9225g;
        @SerializedName("pkg_id")

        /* renamed from: h  reason: collision with root package name */
        public int f9226h;
        @SerializedName("err_code")

        /* renamed from: i  reason: collision with root package name */
        public int f9227i;
        @SerializedName("err_msg")

        /* renamed from: j  reason: collision with root package name */
        public String f9228j;

        public C0132a() {
        }
    }
}
