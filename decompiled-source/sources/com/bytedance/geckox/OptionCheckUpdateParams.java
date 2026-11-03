package com.bytedance.geckox;

import com.bytedance.geckox.policy.loop.model.LoopInterval;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class OptionCheckUpdateParams {
    private com.bytedance.geckox.listener.a listener;
    private LoopInterval.a loopLevel;
    private boolean enableThrottle = true;
    private Map<String, Map<String, Object>> customParam = new HashMap();
    private boolean enableRetry = true;

    /* loaded from: classes2.dex */
    public interface CustomValue {
        Object getValue();
    }

    public boolean isEnableThrottle() {
        return this.enableThrottle;
    }

    public com.bytedance.geckox.listener.a getListener() {
        return this.listener;
    }

    public Map<String, Map<String, Object>> getCustomParam() {
        return this.customParam;
    }

    public boolean isEnableRetry() {
        return this.enableRetry;
    }

    public LoopInterval.a getLoopLevel() {
        return this.loopLevel;
    }

    public OptionCheckUpdateParams setEnableThrottle(boolean z) {
        this.enableThrottle = z;
        return this;
    }

    public OptionCheckUpdateParams setListener(com.bytedance.geckox.listener.a aVar) {
        this.listener = aVar;
        return this;
    }

    public OptionCheckUpdateParams setCustomParam(Map<String, Map<String, Object>> map) {
        this.customParam = map;
        return this;
    }

    public OptionCheckUpdateParams setEnableRetry(boolean z) {
        this.enableRetry = z;
        return this;
    }

    public OptionCheckUpdateParams setLoopLevel(LoopInterval.a aVar) {
        this.loopLevel = aVar;
        return this;
    }
}
