package com.facebook.react;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import com.ttnet.org.chromium.net.NetError;
/* loaded from: classes8.dex */
public class OrientationSensorListener implements SensorEventListener {
    public static final int ORIENTATION_UNKNOWN = -1;
    public static final int SCREEN_ORIENTATION = 1008;
    private static final int _DATA_X = 0;
    private static final int _DATA_Y = 1;
    private static final int _DATA_Z = 2;
    private Handler rotateHandler;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    public OrientationSensorListener(Handler handler) {
        this.rotateHandler = handler;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent == null) {
            return;
        }
        float[] fArr = sensorEvent.values;
        int i2 = -1;
        float f2 = -fArr[0];
        float f3 = -fArr[1];
        float f4 = -fArr[2];
        if (((f2 * f2) + (f3 * f3)) * 4.0f >= f4 * f4) {
            int round = 90 - Math.round(((float) Math.atan2(-f3, f2)) * 57.29578f);
            while (round >= 360) {
                round += NetError.ERR_HTTP2_INADEQUATE_TRANSPORT_SECURITY;
            }
            i2 = round;
            while (i2 < 0) {
                i2 += 360;
            }
        }
        Handler handler = this.rotateHandler;
        if (handler != null) {
            handler.obtainMessage(1008, i2, 0).sendToTarget();
        }
    }
}
