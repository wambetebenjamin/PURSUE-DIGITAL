package com.bytedance.apm6;

import com.bytedance.apm6.hub.config.f;
/* compiled from: DefaultMemoryConfigService.java */
/* loaded from: classes.dex */
class c extends f {
    @Override // com.bytedance.apm6.hub.config.f, com.bytedance.apm6.memory.config.b
    public com.bytedance.apm6.memory.config.a getConfig() {
        com.bytedance.apm6.memory.config.a config = super.getConfig();
        if (config != null) {
            config.setRealTimeMemEnable(com.bytedance.apm.c.isDeviceInfoOnPerfDataEnabled());
            config.setStopWhenBackground(com.bytedance.apm.c.isStopWhenBackground());
        }
        return config;
    }
}
