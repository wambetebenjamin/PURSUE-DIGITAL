package com.bytedance.common.push.interfaze;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.bytedance.common.utility.k;
import com.bytedance.push.utils.h;
/* compiled from: IPushCommonConfiguration.java */
/* loaded from: classes2.dex */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    private final String f5141a = "IPushCommonConfiguration";

    public boolean c() {
        return false;
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return true;
    }

    public boolean g() {
        return true;
    }

    public com.bytedance.push.frontier.interfaze.a getFrontierService() {
        return null;
    }

    public String getSessionId() {
        return "";
    }

    public e h() {
        return null;
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return true;
    }

    public k l() {
        return null;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public com.bytedance.push.frontier.a getFrontierMode() {
        return com.bytedance.push.frontier.a.STRATEGY_USE_HOST;
    }

    public com.bytedance.common.interfaze.a b() {
        return new com.bytedance.common.interfaze.a() { // from class: com.bytedance.common.push.interfaze.d.1
            @Override // com.bytedance.common.interfaze.a
            public Sensor a(SensorManager sensorManager, int i2) {
                h.d("IPushCommonConfiguration", "default SensorAbility#getDefaultSensor");
                return null;
            }

            @Override // com.bytedance.common.interfaze.a
            public boolean a(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor, int i2) {
                h.d("IPushCommonConfiguration", "default SensorAbility#registerListener");
                return false;
            }

            @Override // com.bytedance.common.interfaze.a
            public void a(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor) {
                h.d("IPushCommonConfiguration", "default SensorAbility#unregisterListener");
            }
        };
    }
}
