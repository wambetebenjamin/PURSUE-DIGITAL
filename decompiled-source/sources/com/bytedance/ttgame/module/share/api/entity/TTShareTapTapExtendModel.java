package com.bytedance.ttgame.module.share.api.entity;

import java.util.ArrayList;
/* loaded from: classes7.dex */
public class TTShareTapTapExtendModel {
    private String mGroupLabelId;
    private ArrayList<String> mHashTags;

    private TTShareTapTapExtendModel(Builder builder) {
        this.mHashTags = builder.mHashTags;
        this.mGroupLabelId = builder.mGroupLabelId;
    }

    public ArrayList<String> getHashTags() {
        return this.mHashTags;
    }

    public String getGroupLabelId() {
        return this.mGroupLabelId;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private String mGroupLabelId;
        private ArrayList<String> mHashTags;

        public Builder setHashTags(ArrayList<String> arrayList) {
            this.mHashTags = arrayList;
            return this;
        }

        public Builder setGroupLabelId(String str) {
            this.mGroupLabelId = str;
            return this;
        }

        public TTShareTapTapExtendModel build() {
            return new TTShareTapTapExtendModel(this);
        }
    }

    public String toString() {
        return "TTShareTapTapExtendModel{, mHashTags=" + this.mHashTags + ", mGroupLabelId='" + this.mGroupLabelId + "'}";
    }
}
