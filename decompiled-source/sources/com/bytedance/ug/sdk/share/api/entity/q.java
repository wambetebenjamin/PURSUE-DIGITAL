package com.bytedance.ug.sdk.share.api.entity;

import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* compiled from: TokenInfoBean.java */
/* loaded from: classes8.dex */
public class q implements Serializable {
    @SerializedName("title")

    /* renamed from: a  reason: collision with root package name */
    private String f15605a;
    @SerializedName("token")

    /* renamed from: b  reason: collision with root package name */
    private String f15606b;
    @SerializedName("open_url")

    /* renamed from: c  reason: collision with root package name */
    private String f15607c;
    @SerializedName("share_user_info")

    /* renamed from: d  reason: collision with root package name */
    private t f15608d;
    @SerializedName("pics")

    /* renamed from: e  reason: collision with root package name */
    private List<p> f15609e;
    @SerializedName("pic_cnt")

    /* renamed from: f  reason: collision with root package name */
    private int f15610f;
    @SerializedName("media_type")

    /* renamed from: g  reason: collision with root package name */
    private int f15611g;
    @SerializedName("token_type")

    /* renamed from: h  reason: collision with root package name */
    private int f15612h;
    @SerializedName("video_duration")

    /* renamed from: i  reason: collision with root package name */
    private int f15613i;
    @SerializedName("log_info")

    /* renamed from: j  reason: collision with root package name */
    private r f15614j;
    @SerializedName("button_text")
    private String k;
    @SerializedName("client_extra")
    private String l;
    @SerializedName(ConstantKt.DESC)
    private String m;
    @SerializedName("author_info")
    private a n;
    private String o;

    public String getFrom() {
        return this.o;
    }

    public void setFrom(String str) {
        this.o = str;
    }

    public String getTitle() {
        return this.f15605a;
    }

    public void setTitle(String str) {
        this.f15605a = str;
    }

    public String getToken() {
        return this.f15606b;
    }

    public void setToken(String str) {
        this.f15606b = str;
    }

    public String getOpenUrl() {
        return this.f15607c;
    }

    public void setOpenUrl(String str) {
        this.f15607c = str;
    }

    public t getShareUserInfo() {
        return this.f15608d;
    }

    public void setShareUserInfo(t tVar) {
        this.f15608d = tVar;
    }

    public List<p> getPics() {
        return this.f15609e;
    }

    public void setPics(List<p> list) {
        this.f15609e = list;
    }

    public int getPicCnt() {
        return this.f15610f;
    }

    public void setPicCnt(int i2) {
        this.f15610f = i2;
    }

    public int getMediaType() {
        return this.f15611g;
    }

    public void setMediaType(int i2) {
        this.f15611g = i2;
    }

    public int getTokenType() {
        return this.f15612h;
    }

    public void setTokenType(int i2) {
        this.f15612h = i2;
    }

    public int getVideoDuration() {
        return this.f15613i;
    }

    public void setVideoDuration(int i2) {
        this.f15613i = i2;
    }

    public r getLogInfo() {
        return this.f15614j;
    }

    public void setLogInfo(r rVar) {
        this.f15614j = rVar;
    }

    public String getButtonText() {
        return this.k;
    }

    public void setButtonText(String str) {
        this.k = str;
    }

    public String getClientExtra() {
        return this.l;
    }

    public void setClientExtra(String str) {
        this.l = str;
    }

    public String getDesc() {
        return this.m;
    }

    public void setDesc(String str) {
        this.m = str;
    }

    public a getAuthorInfo() {
        return this.n;
    }

    public void setAuthorInfo(a aVar) {
        this.n = aVar;
    }
}
