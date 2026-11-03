package com.bytedance.apm.samplers;
/* compiled from: EmptySampler.java */
/* loaded from: classes.dex */
public class a implements b {
    @Override // com.bytedance.apm.samplers.b
    public boolean getLogTypeSwitch(String str) {
        return false;
    }

    @Override // com.bytedance.apm.samplers.b
    public boolean getMetricSwitch(String str) {
        return false;
    }

    @Override // com.bytedance.apm.samplers.b
    public boolean getPerfAllowSwitch(String str) {
        return false;
    }

    @Override // com.bytedance.apm.samplers.b
    public boolean getPerfFpsAllowSwitch(String str, String str2) {
        return false;
    }

    @Override // com.bytedance.apm.samplers.b
    public boolean getPerfSecondStageSwitch(String str) {
        return false;
    }

    @Override // com.bytedance.apm.samplers.b
    public boolean getServiceSwitch(String str) {
        return false;
    }
}
