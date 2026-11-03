package com.bytedance.react.framework.utils;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import com.facebook.react.OrientationSensorListener;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public class RotateDetect {
    private Activity mActivity;
    private Handler mChangeOrientationHandler;
    private OrientationSensorListener mOrientationSensorListener;
    private RotateDetectListener mRotateDetectListener;
    private int prevOrientation = -100;
    private int prevRotation;

    /* loaded from: classes2.dex */
    public interface RotateDetectListener {
        void onRotateDetect(int i2, int i3);
    }

    /* loaded from: classes2.dex */
    public static class RotateHandler extends Handler {
        private WeakReference<RotateDetect> rotateDetectWeakReference;

        public RotateHandler(RotateDetect rotateDetect) {
            this.rotateDetectWeakReference = new WeakReference<>(rotateDetect);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int rotation;
            if (this.rotateDetectWeakReference.get() == null) {
                return;
            }
            RotateDetect rotateDetect = this.rotateDetectWeakReference.get();
            if (rotateDetect.mActivity == null || message.arg1 == rotateDetect.prevOrientation || (rotation = rotateDetect.mActivity.getWindowManager().getDefaultDisplay().getRotation()) == rotateDetect.prevRotation || rotateDetect.mRotateDetectListener == null) {
                return;
            }
            rotateDetect.mRotateDetectListener.onRotateDetect(rotation, rotateDetect.prevRotation);
            rotateDetect.prevRotation = rotation;
            rotateDetect.prevOrientation = message.arg1;
        }
    }

    public void init(Activity activity, SensorManager sensorManager, RotateDetectListener rotateDetectListener) {
        Sensor defaultSensor;
        this.prevRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (sensorManager != null && (defaultSensor = sensorManager.getDefaultSensor(1)) != null) {
            this.mChangeOrientationHandler = new RotateHandler(this);
            OrientationSensorListener orientationSensorListener = new OrientationSensorListener(this.mChangeOrientationHandler);
            this.mOrientationSensorListener = orientationSensorListener;
            sensorManager.registerListener(orientationSensorListener, defaultSensor, 2);
        }
        this.mRotateDetectListener = rotateDetectListener;
        this.mActivity = activity;
    }

    public void destroy(SensorManager sensorManager) {
        Sensor defaultSensor;
        if (sensorManager != null && this.mOrientationSensorListener != null && (defaultSensor = sensorManager.getDefaultSensor(1)) != null) {
            sensorManager.unregisterListener(this.mOrientationSensorListener, defaultSensor);
        }
        this.mOrientationSensorListener = null;
        this.mActivity = null;
        this.mRotateDetectListener = null;
    }
}
