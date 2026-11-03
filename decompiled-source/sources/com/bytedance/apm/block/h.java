package com.bytedance.apm.block;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
/* compiled from: FluencySceneManager.java */
/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static HashSet<String> f712a = new HashSet<>();

    /* renamed from: b  reason: collision with root package name */
    private static String f713b = "";

    /* renamed from: c  reason: collision with root package name */
    private static boolean f714c = true;

    public static String getInjectScene() {
        if (f714c) {
            try {
                f713b = com.bytedance.apm.util.m.listToString(f712a, ",");
                f714c = false;
            } catch (ConcurrentModificationException unused) {
            }
        }
        return f713b;
    }

    public static void addScene(String str) {
        f714c = true;
        f712a.add(str);
    }

    public static void removeScene(String str) {
        f714c = true;
        f712a.remove(str);
    }
}
