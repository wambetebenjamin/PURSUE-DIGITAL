package com.bytedance.geckox.model;

import com.bytedance.geckox.model.CheckRequestBodyModel;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes2.dex */
public class CheckRequestParamModel {
    @SerializedName("group_name")
    private String group;
    @SerializedName("target_channels")
    private List<CheckRequestBodyModel.TargetChannel> targetChannels;

    public CheckRequestParamModel(String str) {
        this.group = str;
    }

    public CheckRequestParamModel(String str, List<CheckRequestBodyModel.TargetChannel> list) {
        this.group = str;
        this.targetChannels = list;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public List<CheckRequestBodyModel.TargetChannel> getTargetChannels() {
        return this.targetChannels;
    }

    public void setTargetChannels(List<CheckRequestBodyModel.TargetChannel> list) {
        this.targetChannels = list;
    }
}
