package com.bytedance.apm6.cpu.exception;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Iterator;
/* compiled from: CpuExceptionJudgeHelper.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static String f2044a;

    public static void initScene(String str) {
        f2044a = str;
    }

    public static boolean isNeedSkipJudge() {
        return a();
    }

    public static boolean isForeMatchScene(com.bytedance.apm6.cpu.config.c cVar) {
        if (cVar.getForeSceneMaxSpeedMap().isEmpty()) {
            return false;
        }
        Iterator<String> it = com.bytedance.apm6.cpu.a.getInstance().getCpuSceneSet().iterator();
        while (it.hasNext()) {
            if (cVar.getForeSceneMaxSpeedMap().containsKey(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean a() {
        f2044a = com.bytedance.apm6.cpu.a.getInstance().getCpuSceneString();
        return false;
    }

    public static String getCacheScene() {
        return f2044a;
    }

    public static boolean judge(com.bytedance.apm6.cpu.config.c cVar, double d2, boolean z) {
        double d3;
        double d4;
        if (z) {
            if (cVar.getBackSceneMaxSpeedMap().isEmpty()) {
                d4 = 0.0d;
            } else {
                Iterator<String> it = com.bytedance.apm6.cpu.a.getInstance().getCpuSceneSet().iterator();
                d4 = 0.0d;
                while (it.hasNext()) {
                    String next = it.next();
                    if (cVar.getBackSceneMaxSpeedMap().containsKey(next)) {
                        double doubleValue = cVar.getBackSceneMaxSpeedMap().get(next).doubleValue();
                        if (doubleValue >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d4 <= doubleValue) {
                            d4 = doubleValue;
                        }
                    }
                }
            }
            if (d4 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d4 = cVar.getMaxProcessBackCpuSpeed();
            }
            return d2 > d4;
        }
        if (cVar.getForeSceneMaxSpeedMap().isEmpty()) {
            d3 = 0.0d;
        } else {
            Iterator<String> it2 = com.bytedance.apm6.cpu.a.getInstance().getCpuSceneSet().iterator();
            d3 = 0.0d;
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (cVar.getForeSceneMaxSpeedMap().containsKey(next2)) {
                    double doubleValue2 = cVar.getForeSceneMaxSpeedMap().get(next2).doubleValue();
                    if (doubleValue2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d3 <= doubleValue2) {
                        d3 = doubleValue2;
                    }
                }
            }
        }
        if (d3 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d3 = cVar.getMaxProcessForeCpuSpeed();
        }
        return d2 > d3;
    }
}
