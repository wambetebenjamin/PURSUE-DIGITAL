package com.bytedance.apm.samplers;
/* compiled from: SamplerHelper.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static b f1448a = new a();

    public static void setSampler(b bVar) {
        f1448a = bVar;
    }

    public static boolean getLogTypeSwitch(String str) {
        return f1448a.getLogTypeSwitch(str);
    }

    public static boolean getServiceSwitch(String str) {
        return f1448a.getServiceSwitch(str);
    }

    public static boolean getMetricSwitch(String str) {
        return f1448a.getMetricSwitch(str);
    }

    public static boolean getPerfAllowSwitch(String str) {
        return f1448a.getPerfAllowSwitch(str);
    }

    public static boolean getPerfFpsAllowSwitch(String str, String str2) {
        return f1448a.getPerfFpsAllowSwitch(str, str2);
    }

    public static boolean getPerfSecondStageSwitch(String str) {
        return f1448a.getPerfSecondStageSwitch(str);
    }
}
