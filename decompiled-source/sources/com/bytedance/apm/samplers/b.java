package com.bytedance.apm.samplers;
/* compiled from: ISampleCheck.java */
/* loaded from: classes.dex */
public interface b {
    boolean getLogTypeSwitch(String str);

    boolean getMetricSwitch(String str);

    boolean getPerfAllowSwitch(String str);

    boolean getPerfFpsAllowSwitch(String str, String str2);

    boolean getPerfSecondStageSwitch(String str);

    boolean getServiceSwitch(String str);
}
