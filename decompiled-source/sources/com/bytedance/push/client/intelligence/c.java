package com.bytedance.push.client.intelligence;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;
import com.bytedance.apm.constant.i;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ReportHelper.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11243a = "ReportHelper";

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f11244b;

    /* renamed from: c  reason: collision with root package name */
    private BatteryManager f11245c;

    /* renamed from: d  reason: collision with root package name */
    private AudioManager f11246d;

    /* renamed from: e  reason: collision with root package name */
    private SensorManager f11247e;

    /* renamed from: f  reason: collision with root package name */
    private Sensor f11248f;

    /* renamed from: g  reason: collision with root package name */
    private Sensor f11249g;

    /* renamed from: h  reason: collision with root package name */
    private Sensor f11250h;

    /* renamed from: i  reason: collision with root package name */
    private Sensor f11251i;

    /* renamed from: j  reason: collision with root package name */
    private Sensor f11252j;
    private Context k;
    private a l;
    private JSONObject m = new JSONObject();
    private JSONObject n = new JSONObject();

    public static c getInstance() {
        if (f11244b == null) {
            synchronized (c.class) {
                if (f11244b == null) {
                    f11244b = new c();
                }
            }
        }
        return f11244b;
    }

    private c() {
    }

    public void collectData(Context context) {
        this.k = context;
        com.bytedance.common.push.d.a(new Runnable() { // from class: com.bytedance.push.client.intelligence.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.c();
                c.this.a();
                c.this.b();
            }
        });
    }

    public JSONObject reportData() {
        try {
            this.m.put("current_time", System.currentTimeMillis());
        } catch (JSONException e2) {
            Log.e(f11243a, "reportData : " + e2.getMessage());
        }
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f11245c == null) {
            this.f11245c = (BatteryManager) this.k.getSystemService("batterymanager");
        }
        try {
            BatteryManager batteryManager = this.f11245c;
            if (batteryManager != null) {
                this.m.put(i.KEY_CURRENT_CAPACITY, batteryManager.getIntProperty(4));
                if (Build.VERSION.SDK_INT >= 26) {
                    this.m.put("status", this.f11245c.getIntProperty(6));
                }
            }
        } catch (JSONException e2) {
            Log.e(f11243a, "initBattery : " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f11246d == null) {
            this.f11246d = (AudioManager) this.k.getSystemService("audio");
        }
        try {
            AudioManager audioManager = this.f11246d;
            if (audioManager != null) {
                this.m.put("isUsingEarPhone", a(audioManager));
                this.m.put("isMusicActive", this.f11246d.isMusicActive());
                this.m.put("curCallVoice", this.f11246d.getStreamVolume(0));
                this.m.put("maxCallVoice", this.f11246d.getStreamMaxVolume(0));
                this.m.put("curSystemVoice", this.f11246d.getStreamVolume(1));
                this.m.put("maxSystemVoice", this.f11246d.getStreamMaxVolume(1));
                this.m.put("curRingVoice", this.f11246d.getStreamVolume(2));
                this.m.put("maxRingVoice", this.f11246d.getStreamMaxVolume(2));
                this.m.put("curMusicVoice", this.f11246d.getStreamVolume(3));
                this.m.put("maxMusicVoice", this.f11246d.getStreamMaxVolume(3));
                this.m.put("curAlarmVoice", this.f11246d.getStreamVolume(4));
                this.m.put("maxAlarmVoice", this.f11246d.getStreamMaxVolume(4));
            }
        } catch (JSONException e2) {
            Log.e(f11243a, "initAudio : " + e2.getMessage());
        }
    }

    private boolean a(AudioManager audioManager) {
        if (audioManager.isWiredHeadsetOn()) {
            return true;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            int profileConnectionState = defaultAdapter.getProfileConnectionState(2);
            int profileConnectionState2 = defaultAdapter.getProfileConnectionState(1);
            int profileConnectionState3 = defaultAdapter.getProfileConnectionState(3);
            if (profileConnectionState != 2) {
                profileConnectionState = profileConnectionState2 == 2 ? profileConnectionState2 : profileConnectionState3 == 2 ? profileConnectionState3 : -1;
            }
            return profileConnectionState != -1;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.l == null) {
            this.l = new a();
        }
        if (this.f11247e == null) {
            SensorManager sensorManager = (SensorManager) this.k.getSystemService("sensor");
            this.f11247e = sensorManager;
            if (sensorManager != null) {
                this.f11248f = sensorManager.getDefaultSensor(1);
                this.f11249g = this.f11247e.getDefaultSensor(4);
                this.f11251i = this.f11247e.getDefaultSensor(5);
                this.f11252j = this.f11247e.getDefaultSensor(19);
                this.f11250h = this.f11247e.getDefaultSensor(8);
            }
        }
        SensorManager sensorManager2 = this.f11247e;
        if (sensorManager2 != null) {
            sensorManager2.registerListener(this.l, this.f11249g, 0);
            this.f11247e.registerListener(this.l, this.f11248f, 0);
            this.f11247e.registerListener(this.l, this.f11251i, 0);
            this.f11247e.registerListener(this.l, this.f11252j, 0);
            this.f11247e.registerListener(this.l, this.f11250h, 0);
            try {
                this.n.put("registerTime", System.currentTimeMillis());
            } catch (JSONException e2) {
                Log.e(f11243a, "initSensor : " + e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ReportHelper.java */
    /* loaded from: classes2.dex */
    public final class a implements SensorEventListener {

        /* renamed from: a  reason: collision with root package name */
        boolean f11254a;

        /* renamed from: b  reason: collision with root package name */
        boolean f11255b;

        /* renamed from: c  reason: collision with root package name */
        boolean f11256c;

        /* renamed from: d  reason: collision with root package name */
        boolean f11257d;

        /* renamed from: e  reason: collision with root package name */
        boolean f11258e;

        /* renamed from: f  reason: collision with root package name */
        JSONObject f11259f;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        private a() {
            this.f11259f = new JSONObject();
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                if (sensorEvent.sensor.getType() == 4) {
                    this.f11259f.put("xGy", sensorEvent.values[0]);
                    this.f11259f.put("yGy", sensorEvent.values[1]);
                    this.f11259f.put("zGy", sensorEvent.values[2]);
                    c.this.n.put("gyroscopeTime", System.currentTimeMillis());
                    c.this.f11247e.unregisterListener(c.this.l, c.this.f11249g);
                    this.f11254a = true;
                }
                if (sensorEvent.sensor.getType() == 1) {
                    this.f11259f.put("xAc", sensorEvent.values[0]);
                    this.f11259f.put("yAc", sensorEvent.values[1]);
                    this.f11259f.put("zAc", sensorEvent.values[2]);
                    c.this.n.put("accelerometerTime", System.currentTimeMillis());
                    c.this.f11247e.unregisterListener(c.this.l, c.this.f11248f);
                    this.f11255b = true;
                }
                if (sensorEvent.sensor.getType() == 5) {
                    this.f11259f.put("light", sensorEvent.values[0]);
                    c.this.n.put("lightTime", System.currentTimeMillis());
                    c.this.f11247e.unregisterListener(c.this.l, c.this.f11251i);
                    this.f11256c = true;
                }
                if (sensorEvent.sensor.getType() == 19) {
                    this.f11259f.put("stepCount", (int) sensorEvent.values[0]);
                    c.this.n.put("stepcountTime", System.currentTimeMillis());
                    c.this.f11247e.unregisterListener(c.this.l, c.this.f11252j);
                    this.f11257d = true;
                }
                if (sensorEvent.sensor.getType() == 8) {
                    this.f11259f.put("distance", sensorEvent.values[0]);
                    c.this.n.put("distanceTime", System.currentTimeMillis());
                    c.this.f11247e.unregisterListener(c.this.l, c.this.f11250h);
                    this.f11258e = true;
                }
                if (this.f11254a && this.f11256c && this.f11258e && this.f11257d && this.f11255b) {
                    JSONObject jSONObject = c.this.m;
                    c.this.m = this.f11259f;
                    c cVar = c.this;
                    cVar.a(cVar.m, jSONObject);
                    c.this.m.put("sensor_collect_time", c.this.n);
                    this.f11254a = false;
                    this.f11255b = false;
                    this.f11256c = false;
                    this.f11257d = false;
                    this.f11258e = false;
                }
            } catch (JSONException e2) {
                Log.e(c.f11243a, "SensorEventListener : " + e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject.put(next, jSONObject2.getString(next));
        }
    }
}
