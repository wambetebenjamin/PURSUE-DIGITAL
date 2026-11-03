package com.appsflyer.internal;

import java.util.HashMap;
/* loaded from: classes.dex */
public class AFa1nSDK extends HashMap<Integer, String> {
    private static AFa1nSDK AFKeystoreWrapper;
    private final Object valueOf = new Object();

    private AFa1nSDK() {
    }

    public static synchronized AFa1nSDK afErrorLog() {
        AFa1nSDK aFa1nSDK;
        synchronized (AFa1nSDK.class) {
            if (AFKeystoreWrapper == null) {
                AFKeystoreWrapper = new AFa1nSDK();
            }
            aFa1nSDK = AFKeystoreWrapper;
        }
        return aFa1nSDK;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public String put(Integer num, String str) {
        String str2;
        synchronized (this.valueOf) {
            str2 = (String) super.put((AFa1nSDK) num, (Integer) str);
        }
        return str2;
    }

    @Override // java.util.HashMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        boolean remove;
        synchronized (this.valueOf) {
            remove = super.remove(obj, obj2);
        }
        return remove;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public String remove(Object obj) {
        String str;
        synchronized (this.valueOf) {
            str = (String) super.remove(obj);
        }
        return str;
    }
}
