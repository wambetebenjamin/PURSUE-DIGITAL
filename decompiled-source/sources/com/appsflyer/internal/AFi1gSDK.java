package com.appsflyer.internal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Looper;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public final class AFi1gSDK implements SensorEventListener {
    private final String AFInAppEventParameterName;
    private final int AFInAppEventType;
    private double AFKeystoreWrapper;
    private final Executor AFLogger;

    /* renamed from: e  reason: collision with root package name */
    private final float[][] f102e = new float[2];
    private final long[] registerClient = new long[2];
    private long unregisterClient;
    private final int valueOf;
    private final String values;

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AFi1gSDK(Sensor sensor, ExecutorService executorService) {
        int type = sensor.getType();
        this.valueOf = type;
        String name = sensor.getName();
        name = name == null ? "" : name;
        this.values = name;
        String vendor = sensor.getVendor();
        String str = vendor != null ? vendor : "";
        this.AFInAppEventParameterName = str;
        this.AFInAppEventType = ((((type + 31) * 31) + name.hashCode()) * 31) + str.hashCode();
        this.AFLogger = executorService;
    }

    private static double AFKeystoreWrapper(float[] fArr, float[] fArr2) {
        int min = Math.min(fArr.length, fArr2.length);
        double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (int i2 = 0; i2 < min; i2++) {
            d2 += StrictMath.pow(fArr[i2] - fArr2[i2], 2.0d);
        }
        return Math.sqrt(d2);
    }

    private static List<Float> AFInAppEventParameterName(float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f2 : fArr) {
            arrayList.add(Float.valueOf(f2));
        }
        return arrayList;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(final SensorEvent sensorEvent) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.AFLogger.execute(new Runnable() { // from class: com.appsflyer.internal.AFi1gSDK$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AFi1gSDK.this.values(sensorEvent);
                }
            });
        } else {
            values(sensorEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: AFInAppEventType */
    public void values(SensorEvent sensorEvent) {
        long j2 = sensorEvent.timestamp;
        float[] fArr = sensorEvent.values;
        long currentTimeMillis = System.currentTimeMillis();
        float[][] fArr2 = this.f102e;
        float[] fArr3 = fArr2[0];
        if (fArr3 == null) {
            fArr2[0] = Arrays.copyOf(fArr, fArr.length);
            this.registerClient[0] = currentTimeMillis;
            return;
        }
        float[] fArr4 = fArr2[1];
        if (fArr4 == null) {
            float[] copyOf = Arrays.copyOf(fArr, fArr.length);
            this.f102e[1] = copyOf;
            this.registerClient[1] = currentTimeMillis;
            this.AFKeystoreWrapper = AFKeystoreWrapper(fArr3, copyOf);
        } else if (50000000 <= j2 - this.unregisterClient) {
            this.unregisterClient = j2;
            if (Arrays.equals(fArr4, fArr)) {
                this.registerClient[1] = currentTimeMillis;
                return;
            }
            double AFKeystoreWrapper = AFKeystoreWrapper(fArr3, fArr);
            if (AFKeystoreWrapper > this.AFKeystoreWrapper) {
                this.f102e[1] = Arrays.copyOf(fArr, fArr.length);
                this.registerClient[1] = currentTimeMillis;
                this.AFKeystoreWrapper = AFKeystoreWrapper;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void AFInAppEventType(Map<AFi1gSDK, Map<String, Object>> map, boolean z) {
        if (AFInAppEventParameterName()) {
            map.put(this, valueOf());
            if (z) {
                int length = this.f102e.length;
                for (int i2 = 0; i2 < length; i2++) {
                    this.f102e[i2] = null;
                }
                int length2 = this.registerClient.length;
                for (int i3 = 0; i3 < length2; i3++) {
                    this.registerClient[i3] = 0;
                }
                this.AFKeystoreWrapper = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                this.unregisterClient = 0L;
            }
        } else if (!map.containsKey(this)) {
            map.put(this, valueOf());
        }
    }

    private boolean AFInAppEventType(int i2, String str, String str2) {
        return this.valueOf == i2 && this.values.equals(str) && this.AFInAppEventParameterName.equals(str2);
    }

    private Map<String, Object> valueOf() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(7);
        concurrentHashMap.put("sT", Integer.valueOf(this.valueOf));
        concurrentHashMap.put("sN", this.values);
        concurrentHashMap.put("sV", this.AFInAppEventParameterName);
        float[] fArr = this.f102e[0];
        if (fArr != null) {
            concurrentHashMap.put("sVS", AFInAppEventParameterName(fArr));
        }
        float[] fArr2 = this.f102e[1];
        if (fArr2 != null) {
            concurrentHashMap.put("sVE", AFInAppEventParameterName(fArr2));
        }
        return concurrentHashMap;
    }

    private boolean AFInAppEventParameterName() {
        return this.f102e[0] != null;
    }

    public final int hashCode() {
        return this.AFInAppEventType;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AFi1gSDK) {
            AFi1gSDK aFi1gSDK = (AFi1gSDK) obj;
            return AFInAppEventType(aFi1gSDK.valueOf, aFi1gSDK.values, aFi1gSDK.AFInAppEventParameterName);
        }
        return false;
    }
}
