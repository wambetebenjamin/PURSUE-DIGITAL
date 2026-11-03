package com.bytedance.common.interfaze;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
/* compiled from: SensorAbility.java */
/* loaded from: classes.dex */
public interface a {
    Sensor a(SensorManager sensorManager, int i2);

    void a(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor);

    boolean a(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor, int i2);
}
