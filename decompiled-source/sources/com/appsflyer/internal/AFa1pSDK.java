package com.appsflyer.internal;

import android.net.Uri;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class AFa1pSDK {
    public Map<String, Object> AFInAppEventParameterName;
    public String AFInAppEventType;
    public final Map<String, Object> AFKeystoreWrapper;
    public String AFLogger;

    /* renamed from: d  reason: collision with root package name */
    public String f35d;

    /* renamed from: e  reason: collision with root package name */
    public String f36e;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f37i;
    public int registerClient;
    public String unregisterClient;
    public AppsFlyerRequestListener valueOf;
    public String values;
    private final boolean w;

    public abstract AFe1bSDK AFInAppEventParameterName();

    public boolean AFLogger() {
        return true;
    }

    public boolean d() {
        return true;
    }

    public boolean e() {
        return true;
    }

    public boolean registerClient() {
        return false;
    }

    public AFa1pSDK() {
        this(null, null, null);
    }

    public AFa1pSDK(String str, String str2, Boolean bool) {
        this.AFKeystoreWrapper = new HashMap();
        this.AFLogger = str;
        this.unregisterClient = str2;
        this.w = bool != null ? bool.booleanValue() : true;
    }

    public AFa1pSDK AFKeystoreWrapper(String str) {
        this.unregisterClient = str;
        return this;
    }

    public final boolean valueOf() {
        return this.AFLogger == null && this.values == null;
    }

    public final AFa1pSDK AFInAppEventType(Map<String, ?> map) {
        synchronized (map) {
            this.AFKeystoreWrapper.putAll(map);
        }
        return this;
    }

    public final AFa1pSDK AFKeystoreWrapper(String str, Object obj) {
        synchronized (this.AFKeystoreWrapper) {
            this.AFKeystoreWrapper.put(str, obj);
        }
        return this;
    }

    public final Map<String, Object> AFInAppEventType() {
        return this.AFKeystoreWrapper;
    }

    public final AFa1pSDK AFInAppEventType(int i2) {
        this.registerClient = i2;
        synchronized (this.AFKeystoreWrapper) {
            if (this.AFKeystoreWrapper.containsKey("counter")) {
                this.AFKeystoreWrapper.put("counter", Integer.toString(i2));
            }
            if (this.AFKeystoreWrapper.containsKey("launch_counter")) {
                this.AFKeystoreWrapper.put("launch_counter", Integer.toString(i2));
            }
        }
        return this;
    }

    public final AFa1pSDK values(byte[] bArr) {
        this.f37i = bArr;
        return this;
    }

    public final byte[] values() {
        return this.f37i;
    }

    public final boolean AFKeystoreWrapper() {
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String valueOf(String str) {
        String values = AFb1vSDK.valueOf().AFInAppEventType().AFInAppEventType().values();
        return values != null ? Uri.parse(str).buildUpon().appendQueryParameter("channel", values).build().toString() : str;
    }

    public static boolean values(double d2) {
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d2 >= 1.0d) {
            return false;
        }
        if (d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return true;
        }
        int i2 = (int) (1.0d / d2);
        int i3 = i2 + 1;
        if (i3 > 0) {
            return ((int) ((Math.random() * ((double) (i3 - 1))) + 1.0d)) != i2;
        }
        throw new IllegalArgumentException("Unsupported max value");
    }
}
