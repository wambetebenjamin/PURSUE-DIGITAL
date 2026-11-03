package com.bytedance.geckox.model;

import com.bytedance.geckox.b;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class CheckRequestBodyModel {
    @SerializedName("common")
    private Common common;
    @SerializedName("custom")
    private Map<String, Map<String, Object>> custom;
    @SerializedName("deployment")
    private Map<String, List<ChannelInfo>> deployment;
    @SerializedName("deployments")
    private Map<String, Object> deployments;
    @SerializedName("local")
    private Map<String, Map<String, LocalChannel>> local;
    @SerializedName("req_meta")
    private RequestMeta requestMeta;

    /* loaded from: classes2.dex */
    public static class Channels {
        @SerializedName("channels")
        public List<Channel> channels = new ArrayList();
    }

    /* loaded from: classes2.dex */
    public static class Group {
        @SerializedName("group_name")
        public String groupName;
        @SerializedName("target_channels")
        public List<TargetChannel> targetChannels;
    }

    /* loaded from: classes2.dex */
    public static class LocalChannel {
        @SerializedName("l_v")
        public Long localVersion;
    }

    /* loaded from: classes2.dex */
    public static class ProcessorParams {
        @SerializedName("domain")
        public String domain;
    }

    public void setRequestMeta(RequestMeta requestMeta) {
        this.requestMeta = requestMeta;
    }

    public void setDeployments(Map<String, Object> map) {
        this.deployments = map;
    }

    public void setLocal(Map<String, Map<String, LocalChannel>> map) {
        this.local = map;
    }

    public void setCustom(Map<String, Map<String, Object>> map) {
        this.custom = map;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public void putChannelInfo(String str, List<ChannelInfo> list) {
        if (this.deployment == null) {
            this.deployment = new HashMap();
        }
        this.deployment.put(str, list);
    }

    /* loaded from: classes2.dex */
    public static class ChannelInfo {
        @SerializedName("channel")
        private String channel;
        @SerializedName("local_version")
        private long localVersion;

        public ChannelInfo(String str, long j2) {
            this.channel = str;
            this.localVersion = j2;
        }
    }

    /* loaded from: classes2.dex */
    public enum GroupType {
        NORMAL("normal"),
        HIGHPRIORITY("high_priority");
        
        private String value;

        GroupType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public static class TargetChannel {
        @SerializedName("c")
        public String channelName;
        @SerializedName("from")
        public List<String> from;
        @SerializedName("t_v")
        public Long targetVersion;

        public TargetChannel() {
        }

        public TargetChannel(String str) {
            this.channelName = str;
        }

        public TargetChannel(String str, Long l) {
            this.channelName = str;
            this.targetVersion = l;
        }
    }

    /* loaded from: classes2.dex */
    public static class Channel {
        @SerializedName("c")
        String channelName;
        @SerializedName("l_v")
        public String localVersion;

        public Channel(String str) {
            this.channelName = str;
        }
    }

    /* loaded from: classes2.dex */
    public static class RequestMeta {
        @SerializedName("combine_level")
        private String combineLevel;
        @SerializedName(b.g.f8908a)
        private int reqType;
        @SerializedName(b.g.f8909b)
        private int syncTaskId;

        public RequestMeta(int i2) {
            this.reqType = i2;
        }

        public String getCombineLevel() {
            return this.combineLevel;
        }

        public void setCombineLevel(String str) {
            this.combineLevel = str;
        }

        public int getReqType() {
            return this.reqType;
        }

        public void setReqType(int i2) {
            this.reqType = i2;
        }

        public int getSyncTaskId() {
            return this.syncTaskId;
        }

        public void setSyncTaskId(int i2) {
            this.syncTaskId = i2;
        }
    }
}
