package com.bytedance.ttgame.module.share.api.entity;

import java.util.ArrayList;
/* loaded from: classes7.dex */
public class TTShareDouYinExtendModel {
    private TTDYAnchorAppInfo mDyAnchorAppInfo;
    private TTDYMicroAppInfo mDyMicroAppInfo;
    private ArrayList<String> mHashTag;

    public TTShareDouYinExtendModel(ArrayList<String> arrayList, TTDYMicroAppInfo tTDYMicroAppInfo, TTDYAnchorAppInfo tTDYAnchorAppInfo) {
        this.mHashTag = arrayList;
        this.mDyMicroAppInfo = tTDYMicroAppInfo;
        this.mDyAnchorAppInfo = tTDYAnchorAppInfo;
    }

    public TTShareDouYinExtendModel(ArrayList<String> arrayList, TTDYAnchorAppInfo tTDYAnchorAppInfo) {
        this.mHashTag = arrayList;
        this.mDyAnchorAppInfo = tTDYAnchorAppInfo;
    }

    public TTShareDouYinExtendModel(ArrayList<String> arrayList) {
        this.mHashTag = arrayList;
    }

    public TTShareDouYinExtendModel() {
    }

    public ArrayList<String> getHashTag() {
        return this.mHashTag;
    }

    public void setHashTag(ArrayList<String> arrayList) {
        this.mHashTag = arrayList;
    }

    public TTDYMicroAppInfo getDyMicroAppInfo() {
        return this.mDyMicroAppInfo;
    }

    public void setDyMicroAppInfo(TTDYMicroAppInfo tTDYMicroAppInfo) {
        this.mDyMicroAppInfo = tTDYMicroAppInfo;
    }

    public TTDYAnchorAppInfo getDyAnchorAppInfo() {
        return this.mDyAnchorAppInfo;
    }

    public void setDyAnchorAppInfo(TTDYAnchorAppInfo tTDYAnchorAppInfo) {
        this.mDyAnchorAppInfo = tTDYAnchorAppInfo;
    }
}
