package com.bytedance.geckox.model;

import com.bytedance.geckox.policy.loop.model.GlobalConfig;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class ComponentModel {
    @SerializedName("global_config")
    private GlobalConfig globalConfig = new GlobalConfig();
    @SerializedName("packages")
    private Map<String, List<UpdatePackage>> packages;
    @SerializedName("universal_strategies")
    private Map<String, a> universalStrategies;

    public Map<String, List<UpdatePackage>> getPackages() {
        return this.packages;
    }

    public Map<String, a> getUniversalStrategies() {
        return this.universalStrategies;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }
}
