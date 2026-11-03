package com.bytedance.geckox.policy.v4.model;

import com.bytedance.geckox.model.CheckRequestBodyModel;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class V4RequestModel {
    @SerializedName("custom")
    private Map<String, Object> custom;
    private V4DeploymentModel deployment;

    public V4RequestModel(Map<String, Object> map, V4DeploymentModel v4DeploymentModel) {
        this.custom = map;
        this.deployment = v4DeploymentModel;
    }

    public Map<String, Object> getCustom() {
        return this.custom;
    }

    public void setCustom(Map<String, Object> map) {
        this.custom = map;
    }

    public V4DeploymentModel getDeployment() {
        return this.deployment;
    }

    public void setDeployment(V4DeploymentModel v4DeploymentModel) {
        this.deployment = v4DeploymentModel;
    }

    /* loaded from: classes2.dex */
    public static class V4DeploymentModel {
        @SerializedName("group_name")
        private List<a> groupName;
        @SerializedName("target_channels")
        private List<CheckRequestBodyModel.TargetChannel> targetChannels;

        public V4DeploymentModel() {
            this.groupName = new ArrayList();
            this.targetChannels = new ArrayList();
        }

        public V4DeploymentModel(List<a> list, List<CheckRequestBodyModel.TargetChannel> list2) {
            this.groupName = list;
            this.targetChannels = list2;
        }

        public List<a> getGroupName() {
            return this.groupName;
        }

        public List<CheckRequestBodyModel.TargetChannel> getTargetChannels() {
            return this.targetChannels;
        }

        public void setTargetChannels(List<CheckRequestBodyModel.TargetChannel> list) {
            this.targetChannels = list;
        }
    }

    /* loaded from: classes2.dex */
    public static class a {
        @SerializedName("name")

        /* renamed from: a  reason: collision with root package name */
        private String f9324a;
        @SerializedName("from")

        /* renamed from: b  reason: collision with root package name */
        private String f9325b;

        public a() {
        }

        public a(String str, String str2) {
            this.f9324a = str;
            this.f9325b = str2;
        }

        public String a() {
            return this.f9324a;
        }

        public void a(String str) {
            this.f9324a = str;
        }

        public String b() {
            return this.f9325b;
        }

        public void b(String str) {
            this.f9325b = str;
        }
    }
}
