package com.bytedance.common.utility.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.bytedance.common.utility.Logger;
/* compiled from: ShakeDetector.java */
/* loaded from: classes2.dex */
public class e implements SensorEventListener {
    public static final boolean ENABLE_SHAKER = false;

    /* renamed from: g  reason: collision with root package name */
    private static final float f5186g = 2.0f;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5187h = 100;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5188i = 500;

    /* renamed from: j  reason: collision with root package name */
    private static final int f5189j = 800;
    private static final int k = 1000;
    private static final int l = 3;
    private static final int m = 1500;
    private static final int q = 10;

    /* renamed from: a  reason: collision with root package name */
    b f5190a;

    /* renamed from: b  reason: collision with root package name */
    SensorManager f5191b;

    /* renamed from: c  reason: collision with root package name */
    Sensor f5192c;

    /* renamed from: d  reason: collision with root package name */
    private float f5193d;

    /* renamed from: e  reason: collision with root package name */
    private float f5194e;

    /* renamed from: f  reason: collision with root package name */
    private float f5195f;
    private long n;
    private long o;
    private a p;

    /* compiled from: ShakeDetector.java */
    /* loaded from: classes2.dex */
    public interface a {
        void onShake();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public e(Context context, a aVar) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor != null && sensorEvent.sensor.getType() == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.o < 1500) {
                return;
            }
            long j2 = currentTimeMillis - this.n;
            if (j2 > 100) {
                this.n = currentTimeMillis;
                float f2 = sensorEvent.values[0];
                float f3 = sensorEvent.values[1];
                float f4 = sensorEvent.values[2];
                this.f5195f = this.f5194e;
                float sqrt = (float) Math.sqrt((f2 * f2) + (f3 * f3) + (f4 * f4));
                this.f5194e = sqrt;
                this.f5193d = (this.f5193d * 0.9f) + (sqrt - this.f5195f);
                Logger.d("Shaker", j2 + " " + this.f5193d + " " + f2 + " " + f3 + " " + f4);
                this.f5190a.a(this.f5193d, currentTimeMillis);
                if (Math.abs(this.f5193d) <= f5186g || !this.f5190a.a(currentTimeMillis)) {
                    return;
                }
                this.f5190a.a();
                this.o = currentTimeMillis;
                a aVar = this.p;
                if (aVar != null) {
                    aVar.onShake();
                }
            }
        }
    }

    /* compiled from: ShakeDetector.java */
    /* loaded from: classes2.dex */
    static class b {

        /* renamed from: a  reason: collision with root package name */
        int f5196a = 0;

        /* renamed from: b  reason: collision with root package name */
        final float[] f5197b;

        /* renamed from: c  reason: collision with root package name */
        final long[] f5198c;

        /* renamed from: d  reason: collision with root package name */
        int f5199d;

        b(int i2) {
            this.f5199d = i2;
            this.f5197b = new float[i2];
            this.f5198c = new long[i2];
        }

        void a() {
            this.f5196a = 0;
            for (int i2 = 0; i2 < this.f5199d; i2++) {
                this.f5198c[i2] = 0;
            }
        }

        void a(float f2, long j2) {
            float[] fArr = this.f5197b;
            int i2 = this.f5196a;
            fArr[i2] = f2;
            this.f5198c[i2] = j2;
            this.f5196a = (i2 + 1) % this.f5199d;
        }

        boolean a(long j2) {
            int i2 = this.f5196a;
            long j3 = 0;
            long j4 = 0;
            boolean z = true;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = this.f5199d;
                if (i3 >= i5) {
                    return false;
                }
                i2--;
                if (i2 < 0) {
                    i2 = i5 - 1;
                }
                long j5 = this.f5198c[i2];
                if (j5 <= 0 || j2 - j5 > 1000) {
                    return false;
                }
                if (j3 > 0 && j3 - j5 > 800) {
                    return false;
                }
                if (j4 > 0 && j4 - j5 > 500) {
                    return false;
                }
                float f2 = this.f5197b[i2];
                if (Math.abs(f2) >= e.f5186g) {
                    boolean z2 = f2 > 0.0f;
                    if (i4 == 0 || z2 != z) {
                        i4++;
                        z = z2;
                        j3 = j5;
                    }
                    if (i4 >= 3) {
                        return true;
                    }
                    j4 = j5;
                }
                i3++;
            }
        }
    }
}
